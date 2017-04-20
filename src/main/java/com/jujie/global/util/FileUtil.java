package com.jujie.global.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

public class FileUtil {
	
	public static String fillZero(int nval){
		if(nval<10){
			return 0+""+nval;
		}
		return nval+"";
	}
	
	public static String getYMD(String prex){
		Date date = new Date();
		return (1970+date.getYear())+prex+fillZero(date.getMonth())+prex+fillZero(date.getDate());
	}
	
	public static String resetFileName(String name){
		Random random = new Random();
		int value = 0;
		while(value < 1000){
			value = random.nextInt(10000);
		}
		String ymd = getYMD("");
		if(name!=null&&!"".equals(name)){
			return ymd+value+name.substring(name.lastIndexOf("."));
		}
		return null;
	} 
	
	public static File createTempFile(String directory,String content){
		String fileName = FileUtil.resetFileName("X.mht");
		String fileDirectory = directory;
        File file = new File(fileDirectory,fileName);
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			String s = content;
			OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			outputStream.write(s,0,s.length());
			outputStream.close();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return file;
	}
	
	public static String getWebUrl(HttpServletRequest request,String fileName){
		String webPath = request.getRequestURL().toString();
		webPath = webPath.substring(0,webPath.indexOf("/",30));
		webPath += "/upload/file/"+fileName;
		return webPath;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
