package com.honest.sdms.transaction.service.imp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.service.imp.BaseServiceImp;
import com.honest.sdms.tools.DateTimeUtil;
import com.honest.sdms.tools.StringUtil;
import com.honest.sdms.transaction.dao.PoHeaderMapper;
import com.honest.sdms.transaction.entity.Item;
import com.honest.sdms.transaction.entity.PoHeader;
import com.honest.sdms.transaction.entity.PoLine;
import com.honest.sdms.transaction.service.IItemService;
import com.honest.sdms.transaction.service.IPoHeaderService;
import com.honest.sdms.transaction.service.IPoLineService;

/**
 * PO相关操作服务类
 * @author beisi
 *
 */
@Service
public class PoHeaderServiceImp extends BaseServiceImp<PoHeader, Long> implements IPoHeaderService{
	private static final Logger logger = LoggerFactory.getLogger(PoHeaderServiceImp.class);
	
	private PoHeaderMapper poHeaderMapper;
	@Autowired
	private IPoLineService poLineService;
	@Autowired
	private IItemService itemService;

	@Autowired
	@Qualifier("poHeaderMapper")
	@Override
	public void setBaseDao(IBaseMapper<PoHeader, Long> baseMapper) {
		this.baseMapper = baseMapper;
		poHeaderMapper = (PoHeaderMapper)baseMapper;
	}

	@Override
	public void savePo(PoHeader poHeader, List<PoLine> poLines) throws HSException {
		try {
			Long itemId = poHeader.getItemId();
			Item item = itemService.getItemByItemId(itemId);
			poHeader.setItem(item.getItem());
			poHeader.setIsClosed("N");
			poHeader.setPoNumber(this.createPoNumber(poHeader.getItem()));
			insertSelective(poHeader);
			for(PoLine line : poLines) {
				line.setHeaderId(poHeader.getHeaderId());
				poLineService.savePoLine(line);
			}
		}catch(Exception e) {
			StringUtil.writeStackTraceToLog(logger, e);
			throw new HSException("创建PO信息异常,"+e.getMessage());
		}
		
	}

	/**
	 * 创建订单号
	 * @return
	 */
	@Override
	public String createPoNumber(String item) {
		StringBuilder sBuilder = new StringBuilder();
		Long nextVal = poHeaderMapper.nextSeqNumber();
		String time = new DateTimeUtil().toString("yyyymmdd");
		String _item = StringUtil.ToPinyin(item).toUpperCase();
		sBuilder.append("P").append(_item).append(time).append(nextVal);
		return sBuilder.toString();
	}
	
}
