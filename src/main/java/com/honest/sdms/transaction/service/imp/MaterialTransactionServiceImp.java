package com.honest.sdms.transaction.service.imp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.Constants;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.service.imp.BaseServiceImp;
import com.honest.sdms.tools.DateTimeUtil;
import com.honest.sdms.tools.ObjectUtil;
import com.honest.sdms.tools.StringUtil;
import com.honest.sdms.transaction.dao.MaterialTransactionMapper;
import com.honest.sdms.transaction.entity.MaterialStoreage;
import com.honest.sdms.transaction.entity.MaterialTransaction;
import com.honest.sdms.transaction.entity.PoLine;
import com.honest.sdms.transaction.service.IMaterialStorageService;
import com.honest.sdms.transaction.service.IMaterialTransactionService;
import com.honest.sdms.transaction.service.IPoLineService;

/**
 * 交易信息服务操作类
 * @author beisi
 */
@Service
public class MaterialTransactionServiceImp extends BaseServiceImp<MaterialTransaction, Long> implements IMaterialTransactionService{

	private static final Logger logger = LoggerFactory.getLogger(MaterialTransaction.class);
	
	private MaterialTransactionMapper materialTransactionMapper;
	
	@Autowired
	private IMaterialStorageService materialStorageService;
	@Autowired
	private IPoLineService poLineService;

	@Autowired
	@Qualifier("materialTransactionMapper")
	@Override
	public void setBaseDao(IBaseMapper<MaterialTransaction, Long> baseMapper) {
		this.baseMapper = baseMapper;
		materialTransactionMapper = (MaterialTransactionMapper)baseMapper;
	}
	
	@Override
	public void saveList(List<MaterialTransaction> list) throws HSException {
		if(list != null && list.size() > 0) {
			for(MaterialTransaction trans : list) {
				insert(trans);
			}
		}
	}

	@Override
	public void updateList(List<MaterialTransaction> list) throws HSException {
		if(list != null && list.size() > 0) {
			for(MaterialTransaction trans : list) {
				updateByPrimaryKey(trans);
			}
		}
	}

	@Override
	public List<MaterialTransaction> findMaterialTransactionsByIds(Long[] transactionIds) {
		return materialTransactionMapper.findMaterialTransactionsByIds(transactionIds, Constants.getCurrentOrganizationId());
	}

	/**
	 * PO批量生成交易信息，场景：整个PO在一辆车上
	 */
	@Override
	public void createMaterialTransactions(MaterialTransaction cond) throws HSException {
		try {
			String[] strArr = StringUtil.stringToArray(cond.getPoHeaderIds());
			if(strArr != null && strArr.length > 0) {
				
				//预计到货日期需等于或大于发车日期
				Timestamp shipDate = cond.getShipDate();
				Timestamp scheduleDate = cond.getScheduledArrivalDate();
				if(!shipDate.equals(scheduleDate) && scheduleDate.before(shipDate)) {
					throw new HSException("预计到货日期需大于发车日期");
				}
				
				//转换long类型的数组
				Long[] poHeaderIds = (Long[]) ConvertUtils.convert(strArr,Long.class);
				List<PoLine> poLines = poLineService.findPoLinesByPoHeaderIds(poHeaderIds);
				for(PoLine poLine : poLines) {
					String isShip = poLine.getIsShip();
					if(Constants.Status.Y.equals(isShip))
						throw new HSException("此PO已生成发车信息，不允许重复创建发车信息");
							
					//生成交易记录
					MaterialTransaction trans = new MaterialTransaction();
					//属性拷贝
					ObjectUtil.copyProperties(cond, trans); 
					trans.setPoHeaderId(poLine.getHeaderId());
					trans.setPoLine(poLine.getLineId());
					trans.setIoStatus(Constants.Status.N);
					trans.setIoType(Constants.IoType.PO_SHIP);//在途
					trans.setTransactionPiece(poLine.getPieceNum());
					trans.setItem(poLine.getItem());
					trans.setItemId(poLine.getItemId());
					trans.setTransactionWeight(poLine.getWeight());//po行净重
					insert(trans);
					
					//标准此po行为已生成发车信息状态
					poLine.setIsShip(Constants.Status.Y);
					poLineService.updateByPrimaryKey(poLine);
				}
			}
		}catch(Exception e) {
			StringUtil.writeStackTraceToLog(logger, e);
			throw new HSException("createMaterialTransactions 出错，"+e.getMessage());
		}
	}

	/**
	 * 创建库存信息，在途信息生成原料库存，注意事项：只有已收货且未形成库存的才可以入库,同时更新交易信息相关字段
	 */
	@Override
	public void createStocks(String transactionIds) throws HSException {
		try {
			String[] strArr = StringUtil.stringToArray(transactionIds);
			if(strArr != null && strArr.length > 0) {
				List<MaterialStoreage> storeList = new ArrayList<MaterialStoreage>();
				
				Long[] _transactionIds = (Long[]) ConvertUtils.convert(strArr,Long.class);
				List<MaterialTransaction> list = this.findMaterialTransactionsByIds(_transactionIds);
				for(MaterialTransaction trans : list) {
					if(Constants.Status.N.equals(trans.getIoStatus()) 
							&& Constants.IoType.PO_SHIP.equals(trans.getIoType())) {
						trans.setIoStatus(Constants.Status.Y);
						trans.setIoType(Constants.IoType.PO_IN);
						trans.setActualArrivalDate(new DateTimeUtil().toTimestamp());
						trans.setTransactionDate(trans.getActualArrivalDate());
						
						MaterialStoreage store = new MaterialStoreage();
						//属性拷贝
						ObjectUtil.copyProperties(trans, store); 
						store.setPieceNum(trans.getTransactionPiece());
						store.setInventoryTypeId(Constants.InventoryType.rawStock);
						store.setNetWeight(trans.getTransactionWeight());
						store.setCreateTransactionId(trans.getTransactionId());
						storeList.add(store);
					}else {
						throw new HSException("只有在途PO才可以生成接收入库");
					}
				}
				
				updateList(list);
				materialStorageService.saveList(storeList);
			}
		}catch(Exception e) {
			throw new HSException("创建库存信息出错,"+e.getMessage());
		}
	}
	
}
