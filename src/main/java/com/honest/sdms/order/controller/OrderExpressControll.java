package com.honest.sdms.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.honest.sdms.basedata.APIResponse;
import com.honest.sdms.basedata.ResultStatus;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.order.entity.OrderTraces;
import com.honest.sdms.order.entity.OrderTrancesReturn;
import com.honest.sdms.order.service.IKdniaoApiService;

/**
 * 物流相关操作接口
 * @author beisi
 *
 */
@RestController
@RequestMapping("/orderExpressManagement")
public class OrderExpressControll {

	@Autowired
	private IKdniaoApiService kdniaoApiService;
	
	/**
	 * 查询物流信息
	 * @return
	 */
	@RequestMapping(value = "/getOrderTraces",method = RequestMethod.POST)
	public APIResponse<JSONArray> getOrderTraces(OrderTraces orderTraces)throws HSException{
		APIResponse<JSONArray> result = new APIResponse<JSONArray>();
		OrderTrancesReturn orderReturn = kdniaoApiService.getOrderTraces(orderTraces);
		if(orderReturn != null){
			JSONArray array = new JSONArray();
			List<OrderTrancesReturn.Trace> trances = orderReturn.getTraces();
			if(trances != null && trances.size() > 0){
				for(OrderTrancesReturn.Trace trance : trances){
					JSONObject json = new JSONObject();
					json.put("content", trance.getAcceptStation());
					json.put("acceptTime", trance.getAcceptTime());
					array.add(json);
				}
				result.setCode(ResultStatus.OK.getValue());
				result.setData(array);
			}else{
				result.setCode(ResultStatus.ERROR.getValue());
				result.setMsg(orderReturn.getReason());
			}
		}
		return result;
	}
}
