package com.honest.sdms.tools.excel;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import net.sf.json.JSONObject;

/**
 * 订单Excel数据处理监听类
 * 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
 * @author beisi
 */
public class OrderExcelDataListener extends AnalysisEventListener<DemoData>{

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderExcelDataListener.class);
	/**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 500;
    List<DemoData> list = new ArrayList<DemoData>();
	
    /**
     * 这个每一条数据解析都会来调用
     */
	@Override
	public void invoke(DemoData data, AnalysisContext context) {
		LOGGER.info("解析到一条数据:{}", JSONObject.fromObject(data).toString());
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
        	LOGGER.info("进来了*******");
            // 存储完成清理 list
            list.clear();
        }
	}

	/**
     * 所有数据解析完成了 都会来调用
     */
	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		LOGGER.info("所有数据解析完成！"+context.readRowHolder().getRowIndex());
	}

}


