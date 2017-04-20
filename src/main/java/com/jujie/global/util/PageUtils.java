package com.jujie.global.util;

import org.springframework.jdbc.core.JdbcTemplate;

public class PageUtils {
	
	
//	public static String createDataTable(String titleStr,String tdWidthStr,String tableAttr,int model){
//		StringBuffer stringBuffer = new StringBuffer();;
//		try {
//			stringBuffer.append("<table id=\"pageDataTable\" align=\"center\" "+tableAttr+">");
//			stringBuffer.append("<tr align=\"center\">");
//			String[] titles = titleStr.split(",");
//			String[] tdWidths = tdWidthStr.split(",");
//			for (int i=0;i<titles.length;i++) {
//				String tdWidth = "";
//				if(!"0".equals(tdWidths[i])){
//					tdWidth = tdWidths[i];
//				}
//				stringBuffer.append("<th width=\""+tdWidth+"\">"+titles[i]+"</th>");
//			}
//			stringBuffer.append("</tr>");
//			stringBuffer.append("</table>");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		switch(model){
//			case 0:
//				stringBuffer.append(selectPageScriptModel());
//				break;
//			case 1:
//				stringBuffer.append(selectPageScriptModelSelf());
//				break;
//		}
//		return stringBuffer.toString();
//	}

//	private static String selectPageScriptModel(){
//		StringBuffer stringBuffer = new StringBuffer("<script type=\"text/javascript\">");
//		stringBuffer.append(StreamUtils.readString(PageUtils.class.getResourceAsStream("page_default.js")));
//		stringBuffer.append("</script>");
//		return stringBuffer.toString();
//	}
//	
//	private static String selectPageScriptModelSelf(){
//		StringBuffer stringBuffer = new StringBuffer("<script type=\"text/javascript\">");
//		stringBuffer.append(StreamUtils.readString(PageUtils.class.getResourceAsStream("page_self.js")));
//		stringBuffer.append("</script>");
//		return stringBuffer.toString();
//	}
//	
//	/**
//	 * ��ѯ��ݿ���ݣ�������json�ַ�
//	 * @param sql ��ѯ���
//	 * @param page ��ҳ����
//	 * @param jdbcTemplate 
//	 * @param DataBaseType ��ݿ�����
//	 * @return
//	 */
//	public static String queryPageData(String sql,Object[] objs,Page page,JdbcTemplate jdbcTemplate,int DataBaseType){
//		page = new Page(DataUtils.getInt(page.getCurrentPage()));
//		String pageSql = "";
//		switch(DataBaseType){
//			case Page.DATABASE_TYPE_MYSQL:
//				pageSql = page.mysqlPageSQL(sql);
//				break;
//			case Page.DATABASE_TYPE_ORACLE:
//				pageSql = page.oraclePageSQL(sql);
//				break;
//			case Page.DATABASE_TYPE_MSSQL:
//				pageSql = page.mssqlPageSQL(sql);
//				break;
//		}
//		List list = jdbcTemplate.queryForList(pageSql);
//		if(objs!=null){
//			page.setMaxSize(jdbcTemplate.queryForInt(page.maxSizeSQL(sql),objs));
//		}else{
//			page.setMaxSize(jdbcTemplate.queryForInt(page.maxSizeSQL(sql)));
//		}
//		page.setList(list);
//		JSONObject array = JSONObject.fromObject(page);
//		return array.toString();
//	}
//	
	public static String fyPage(String sql,Object[] objs,Page page,JdbcTemplate jdbcTemplate,int DataBaseType){
		if(page==null){
			return sql;
		}
		String pageSql = "";
		switch(DataBaseType){
			case Page.DATABASE_TYPE_MYSQL:
				pageSql = page.mysqlPageSQL(sql);
				page.setMaxSize(jdbcTemplate.queryForInt(page.maxSizeSQL(sql),objs));
				break;
			case Page.DATABASE_TYPE_ORACLE:
				pageSql = page.oraclePageSQL(sql);
				page.setMaxSize(jdbcTemplate.queryForInt(page.maxSizeSQL(sql),objs));
				break;
			case Page.DATABASE_TYPE_MSSQL:
				page.setMaxSize(jdbcTemplate.queryForInt(page.maxSizeSQL2(sql),objs));
				pageSql = page.mssqlPageSQL(sql);
				break;
		}
		return pageSql;
	}
}
