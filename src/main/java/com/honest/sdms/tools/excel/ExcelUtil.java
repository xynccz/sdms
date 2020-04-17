package com.honest.sdms.tools.excel;

import com.alibaba.excel.EasyExcel;
import com.honest.sdms.order.service.imp.promote.OrderModleDataListener;

/**
 * Excel文档操作类
 * @author beisi
 */
public class ExcelUtil {

	public static void main(String[] args) {
		String file = "/Users/beisi/Desktop/test.xlsx";
		try {
			// 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭,读取第一个sheet 同步读取会自动finish
//		    EasyExcel.read(file, DemoData.class, new OrderExcelDataListener(null)).sheet().doRead();
			EasyExcel.read(file, new OrderModleDataListener(null,null,null,null,null)).sheet().doRead();
		    // 写法2：
//		    ExcelReader excelReader = EasyExcel.read(file, DemoData.class, new OrderExcelDataListener()).build();
//		    ReadSheet readSheet = EasyExcel.readSheet(0).build();
//		    excelReader.read(readSheet);
//		    // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
//		    excelReader.finish();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}


