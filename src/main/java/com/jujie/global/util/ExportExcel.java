package com.jujie.global.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



/**
 * 创建Excel文件，使用Apache POI的HSSF实现
 * 
 * @author 
 * 
 */
public class ExportExcel {

	private static Logger log = Logger.getLogger(ExportExcel.class);

	/**
	 * 将数据写入Excel文档并下载
	 * 
	 * @param wb
	 * @param os
	 */
	public static void writeDataToExcel(HSSFWorkbook wb, OutputStream os) {
		try {
			wb.write(os);
			os.close();
			log.info("创建Excel文件结束");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error("Excel文件没有发现异常", e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("IO异常", e);
			e.printStackTrace();
		}
	}

	
	@SuppressWarnings("deprecation")
	public static void exportExcel(String head,List<String> titles,List<List<Object>> values,OutputStream os) {
		
		HSSFWorkbook wb = new HSSFWorkbook();
		/** <创建一个sheet>* */
		HSSFSheet sheet = wb.createSheet("报表");
		/** <创建一行row>* */
		HSSFRow row0 = sheet.createRow((short) 0);
		HSSFCellStyle style=wb.createCellStyle();
	    HSSFFont font = wb.createFont();   //设置字体的样式
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   //字体加粗
    	style.setFont(font);
	
		HSSFCell cell0 = row0.createCell((short)3);
		cell0.setCellStyle(style);
		cell0.setCellValue(head);
		
		HSSFRow row1 = sheet.createRow((short) 1);
		/** <添加头部数据信息第二行>* */
		for(int i = 0 ; i < titles.size() ; i++){
			row1.createCell((short)i).setCellValue(titles.get(i));
		}
		
		for (int i=0;i<values.size();i++) {
			HSSFRow row = sheet.createRow((short) (i+2));
			for(int j=0;j<values.get(i).size();j++){
				row.createCell((short)j).setCellValue(values.get(i).get(j)+"");
			}
		}
		log.info("开始创建Excel文件内容信息");
		writeDataToExcel(wb, os);
	}
	
public static void exportExcel(String head,List<Map<String,List<Object>>> values,OutputStream os) {
		
		HSSFWorkbook wb = new HSSFWorkbook();
		/** <创建一个sheet>* */
		HSSFSheet sheet = wb.createSheet("内容");
		/** <创建一行row>* */
		HSSFRow row0 = sheet.createRow((short) 0);
		HSSFCellStyle style=wb.createCellStyle();
	    HSSFFont font = wb.createFont();   //设置字体的样式
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   //字体加粗
    	style.setFont(font);
	
		HSSFCell cell0 = row0.createCell((short)0);
		cell0.setCellStyle(style);
		System.out.println(head);
		cell0.setCellValue(head);

		int rn = 0;
		
		for (int i=0;i<values.size();i++) {
			rn++;
			Map<String,List<Object>> map = values.get(i);
			if(map.get("htitle")!=null){
				HSSFRow row = sheet.createRow((short) (++rn));
				HSSFCell cell = row.createCell((short)0);
				System.out.println(map.get("htitle").get(0)+"");
				cell.setCellValue(map.get("htitle").get(0)+"");
			}
			if(map.get("title")!=null){
				HSSFRow row = sheet.createRow((short) (++rn));
				for(int j=0;j<map.get("title").size();j++){
					System.out.println(map.get("title").get(j)+"");
					row.createCell((short)j).setCellValue(map.get("title").get(j)+"");
				}
			}
			if(map.get("value")!=null){
				for(int j=0;j<map.get("value").size();j++){
					HSSFRow row = sheet.createRow((short) (++rn));
					List<Object> list = (List<Object>)map.get("value").get(j);
					for(int n=0;n<list.size();n++){
						System.out.println(list.get(n)+"");
						row.createCell((short)n).setCellValue(list.get(n)+"");
					}
				}
			}
			
		}
		log.info("开始创建Excel文件内容信息");
		writeDataToExcel(wb, os);
	}
	
	public static void exportMoreExcel(List<Map<String,Object>> list,OutputStream os){
		HSSFWorkbook wb = new HSSFWorkbook();
		for (Map<String, Object> map : list) {
			HSSFSheet sheet = wb.createSheet(map.get("head")+"");
			HSSFRow row0 = sheet.createRow((short) 0);
			HSSFCellStyle style=wb.createCellStyle();
		    HSSFFont font = wb.createFont();   //设置字体的样式
		    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   //字体加粗
	    	style.setFont(font);
	    	HSSFCell cell0 = row0.createCell((short)0);
			cell0.setCellStyle(style);
			cell0.setCellValue(map.get("head")+"");
			
			int rn = 0;
			List<String> title_list = (List<String>)map.get("title");
			HSSFRow row = sheet.createRow((short) (++rn));
			for(int j=0;j<title_list.size();j++){
				row.createCell((short)j).setCellValue(title_list.get(j)+"");
			}
			
			List<List<String>> value_list = (List<List<String>>)map.get("value");
			for(int j=0;j<value_list.size();j++){
				HSSFRow r = sheet.createRow((short) (++rn));
				List<String> vl = value_list.get(j);
				for(int n=0;n<vl.size();n++){
					r.createCell((short)n).setCellValue(vl.get(n)+"");
				}
			}
		}
		log.info("开始创建Excel文件内容信息");
		writeDataToExcel(wb, os);
	}

	/** *<测试主体部分>** */
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println(df.format(0/0.0));;
	}
}
