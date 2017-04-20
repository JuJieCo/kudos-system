package com.jujie.global.util;

import java.text.SimpleDateFormat;
import java.util.Date;



public class DateUtil {

	public static String DateUtilFormat(Date date,String prex){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy"+prex+"MM"+prex+"dd");
		try {
			return sf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static Date DateUtilFormat(String str,String prex){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy"+prex+"MM"+prex+"dd");
		try {
			Date date = sf.parse(str);
			return date;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	public static java.sql.Date getSqlDate(Object obj){
		if(obj==null){
			return null;
		}
		if(obj instanceof Date){
			return new java.sql.Date(((Date) obj).getTime());
		}
		return null;
	}
	
	public static java.sql.Timestamp getTimestamp(Object obj){
		if(obj==null){
			return null;
		}
		if(obj instanceof Date){
			return new java.sql.Timestamp(((Date) obj).getTime());
		}
		if(obj instanceof java.sql.Timestamp){
			return (java.sql.Timestamp)obj;
		}
		return null;
	}
	
}
