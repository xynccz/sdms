package com.honest.sdms.order.service.imp;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.honest.sdms.Constants;
import com.honest.sdms.basedata.APIResponse;
import com.honest.sdms.order.dao.OrderHeaderMapper;
import com.honest.sdms.order.entity.OrderHeader;
import com.honest.sdms.order.service.IOrderHeaderService;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.entity.DownloadRecords;
import com.honest.sdms.system.service.IDownloadRecordsService;
import com.honest.sdms.system.service.imp.BaseServiceImp;
import com.honest.sdms.tools.FileUtil;
import com.honest.sdms.tools.StringUtil;

/**
 * 订单头服务处理类
 * @author beisi
 */
@Service
public class OrderHeaderServiceImp extends BaseServiceImp<OrderHeader, Long> implements IOrderHeaderService{
	private static final Logger logger = LoggerFactory.getLogger(OrderHeaderServiceImp.class);
	private OrderHeaderMapper orderHeaderMapper;
	@Autowired
	private IDownloadRecordsService downloadRecordsService;
	
	@Autowired
	@Qualifier("orderHeaderMapper")
	@Override
	public void setBaseDao(IBaseMapper<OrderHeader, Long> baseMapper) {
		this.baseMapper = baseMapper;
		orderHeaderMapper = (OrderHeaderMapper)baseMapper;
	}

	/**
	 * 解析订单
	 * @param file 上传附件
	 * @param shopCode 商家类型
	 */
	@Override
	public APIResponse<String> saveOrdersFromFiles(MultipartFile[] files, String shopCode) {
		APIResponse<String> result = new APIResponse<String>();
		StringBuilder sBuilder = new StringBuilder(50);
		if(files != null && files.length > 0 && !StringUtil.isNullOrEmpty(shopCode)){
			for(MultipartFile file : files){
				String fileName = file.getOriginalFilename();
				DownloadRecords record = new DownloadRecords();
				record.setFileName(fileName);
				record.setSourceFileName(fileName);
				record.setCustomer(shopCode);
				record.setFileType(Constants.FileType.ORDER_TYPE);
				try{
					//1、先将前台上传文件保存到服务端
					File resultFile = FileUtil.saveFile(file, Constants.ORDER_PATH_KEY);
					//2、如果文件下载成功后，就做解析
					if(resultFile != null && resultFile.length() > 0) {
						record.setFileMd5(FileUtil.getFileMd5(resultFile.getCanonicalPath()));
						record.setFilePath(resultFile.getParent());
						record.setFileSize(resultFile.length());
						record.setStatus(Constants.Status.P);
					} 
				}catch(Exception e){
					StringUtil.writeStackTraceToLog(logger, e);
					record.setStatus(Constants.Status.E);
					record.setDescription(e.getMessage());
					sBuilder.append(fileName+"导入异常，"+e.getMessage()).append(";");
				}finally {
					downloadRecordsService.insert(record);
				}
			}
		}
		result.setMsg(sBuilder.toString());
		return result;
	}
	
	

}
