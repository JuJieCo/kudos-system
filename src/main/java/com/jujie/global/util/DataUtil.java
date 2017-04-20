package com.jujie.global.util;

public class DataUtil {
	
	public static int getInt(Object obj){
		if(obj==null){
			return 0;
		}else{
			try{
				return Integer.parseInt(obj.toString());
			}catch(Exception e){
				e.printStackTrace();
			}
			return 0;
		}
	}
	
	public static String getString(Object obj){
		if(obj==null){
			return null;
		}else{
			try{
				return obj.toString();
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
	}
	
	public static String getStringK(Object obj){
		if(obj==null){
			return "";
		}else{
			try{
				return obj.toString();
			}catch(Exception e){
				e.printStackTrace();
			}
			return "";
		}
	}
	
}
