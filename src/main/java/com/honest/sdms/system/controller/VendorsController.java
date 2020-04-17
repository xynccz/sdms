package com.honest.sdms.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.honest.sdms.basedata.APIResponse;
import com.honest.sdms.basedata.ResultStatus;
import com.honest.sdms.basedata.config.SdmsLog;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.entity.VendorArchives;
import com.honest.sdms.system.entity.VendorWarehouse;
import com.honest.sdms.system.entity.Vendors;
import com.honest.sdms.system.service.IVendorsService;

/**
 * 供应商关系管理
 * @author beisi
 *
 */
@RequestMapping("/vendorsManager")
@RestController
public class VendorsController {

	@Autowired
	private IVendorsService vendorsService;
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces={"application/json;charset=UTF-8;"})
	public @ResponseBody PageInfo<Vendors> search(Vendors cond,int pageNum,int pageSize,String sortName, String sortOrder){
		PageInfo<Vendors> pageInfo = vendorsService.findByCondWithPage(cond, sortName, sortOrder, pageNum, pageSize);
		return pageInfo;
	}
	
	@RequestMapping("/getVendorArchiveListByHouseId")
	public @ResponseBody List<VendorArchives> getVendorArchiveListByHouseId(Long vendorId, Long vendorWarehouseId){
		return vendorsService.getVendorArchiveListByHouseId(vendorId, vendorWarehouseId);
	}
	
	@RequestMapping("/getVendorArchiveListByVendorId")
	public @ResponseBody List<VendorArchives> getVendorArchiveListByVendorId(Long vendorId){
		return vendorsService.getVendorArchiveListByVendorId(vendorId);
	}
	
	@RequestMapping("/getVendorWarehouseListByVendorId")
	public @ResponseBody List<VendorWarehouse> getVendorWarehouseListByVendorId(Long vendorId){
		return vendorsService.getVendorWarehouseListByVendorId(vendorId);
	}
	
	/**
	 * 新增和更新供应商信息                                                                                                                                                          
	 * @throws HSException 
	 * @throws CustomException 
	 */
	@SdmsLog(value = "新增或修改供应商信息")  //这里添加了AOP的自定义注解
	@RequestMapping(value="/saveVendor",method={RequestMethod.POST},produces={"application/json;charset=UTF-8;"})
	public @ResponseBody APIResponse<String> saveVendor(Vendors vendor, @RequestBody List<VendorWarehouse> vendorWarhouses) throws HSException{
		vendorsService.saveOrUpdateVendor(vendor, vendorWarhouses);
		return new APIResponse<String>(ResultStatus.OK);
	}
	
	@SdmsLog(value = "新增或修改供应商档案信息")
	@RequestMapping(value="/saveVendorArchive",method={RequestMethod.POST},produces={"application/json;charset=UTF-8;"})
	public @ResponseBody APIResponse<String> saveVendorArchive(@RequestBody List<VendorArchives> vendorArchives) throws HSException{
		vendorsService.saveVendorArchives(vendorArchives);
		return new APIResponse<String>(ResultStatus.OK);
	}
}
