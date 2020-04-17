package com.honest.sdms.order.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.honest.sdms.Constants;
import com.honest.sdms.basedata.APIResponse;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.order.entity.OrderHeader;
import com.honest.sdms.order.service.IOrderHeaderService;
import com.honest.sdms.system.entity.DownloadRecords;
import com.honest.sdms.system.service.IDownloadRecordsService;
import com.honest.sdms.tools.FileUtil;

/**
 * 订单管理控制类
 * @author beisi
 */
@RequestMapping("/orderManagement")
@RestController
public class OrderManagerController {

	@Autowired
	private IOrderHeaderService orderHeaderService;
	@Autowired
	private IDownloadRecordsService downloadRecordsService;
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces={"application/json;charset=UTF-8;"})
	public @ResponseBody PageInfo<OrderHeader> search(OrderHeader cond,int pageNum,int pageSize,String sortName, String sortOrder){
		PageInfo<OrderHeader> pageInfo = orderHeaderService.findByCondWithPage(cond, sortName, sortOrder, pageNum, pageSize);
		return pageInfo;
	}

	/**
	 * Excel格式订单附件上传及解析
	 * @param files 订单附件
	 * @param customerId 客户ID
	 * @return
	 * @throws HSException
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public APIResponse<String> uploadOrder(@RequestParam("file")MultipartFile[]  files, @RequestParam("customerId")Long customerId)throws HSException{
		if(files != null && files.length > 0 && customerId != null){
			List<DownloadRecords> records = new ArrayList<DownloadRecords>();
			//1、先将前台上传文件保存到服务端
			for(MultipartFile file : files){
				String fileName = file.getOriginalFilename();
				DownloadRecords record = new DownloadRecords();
				try{
					record.setFileName(fileName);
					record.setCustomerId(customerId);
					record.setSourceFileName(fileName);
					record.setFileType(Constants.FileType.ORDER_TYPE);
					List<DownloadRecords> list = downloadRecordsService.findByCond(record);
					if(list == null || list.size() == 0){
						File resultFile = FileUtil.saveFile(file, Constants.ORDER_PATH_KEY);
						if(resultFile != null && resultFile.length() > 0) {
							record.setFileMd5(FileUtil.getFileMd5(resultFile.getCanonicalPath()));
							record.setFilePath(resultFile.getParent());
							record.setFileSize(resultFile.length());
							record.setStatus(Constants.Status.P);
						}
					}else{
						return new APIResponse<String>(Constants.DEFAULT_ID,fileName+"文件已存在请检查！");
					}
					
				}catch(Exception e){
					record.setStatus(Constants.Status.E);
					record.setDescription(e.getMessage());
					return new APIResponse<String>(Constants.DEFAULT_ID, e.getMessage());
				}finally{
					if(record.getFilePath() != null){
						downloadRecordsService.insert(record);
						records.add(record);
					}
				}
			}
			//2、如果文件下载成功后，再做解析
			return orderHeaderService.saveOrdersFromFiles(records);
		}else{
			return new APIResponse<String>(Constants.DEFAULT_ID,"无数据导入！");
		}
	}
}
