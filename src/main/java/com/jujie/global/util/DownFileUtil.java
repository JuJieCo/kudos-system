package com.jujie.global.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

public class DownFileUtil {
private static DownFileUtil downFileUtil;
	
	private DownFileUtil(){}
	
	public static DownFileUtil getInstance(){
		if(downFileUtil==null){
			return new DownFileUtil();
		}
		return downFileUtil;
	}

	
	public  void downFile(String path,String name,HttpServletResponse response){
        try {
			String filepath = path;
			File file=new File(filepath);
			if(!file.exists()){
				return ;
			}
			String tempName = path.substring(path.lastIndexOf("/")+1);
			String fileName = name;
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/plain");
			response.setHeader("Location", fileName);
			response.setHeader("Content-Disposition", "attachment; filename="+tempName);
			OutputStream outputStream = response.getOutputStream();
			InputStream inputStream = new FileInputStream(filepath);
			
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, i);
			}
			outputStream.flush();
			outputStream.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void downHttpFile(String path,HttpServletResponse response){
		URL url = null;
        try {
            url = new URL(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection huc = null;
        try {

            huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("GET");
            huc.setRequestProperty("Content-Type",
                    "application/vnd.syncml.dm+xml");
            // application/x-www-form-urlencoded
            // application/vnd.syncml.dm+xml
            huc.setDoOutput(true);
            huc.setRequestProperty("Cache-Control", "private");
            huc.setRequestProperty("Accept-Charset", "utf-8");
            huc.setRequestProperty("Accept", "application/vnd.syncml.dm+xml");
 //           huc.setRequestProperty("Content-Length", "1024");

        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream in = null;
        try {
        	huc.connect();
            in = huc.getInputStream();
            if(in!=null){
            	String fileName = path.substring(path.lastIndexOf("/")+1);
    			response.setContentType("text/plain");
    			response.setHeader("Location", fileName);
    			response.setHeader("Content-Disposition", "attachment; filename="+fileName);
    			OutputStream outputStream = response.getOutputStream();
    			
    			
    			byte[] buffer = new byte[1024];
    			int i = -1;
    			while ((i = in.read(buffer)) != -1) {
    				outputStream.write(buffer, 0, i);
    			}
    			outputStream.flush();
    			outputStream.close();
            }  
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public static void main(String[] args) {
	//	DownFileUtil.getInstance().downHttpFile("http://113.142.1.253/2/cs/yw/ppt/200010301419520125.pdf", null);
	//	DownFileUtil.getInstance().downHttpFile("http://113.142.1.254/preview/upload/2/cs/yw/ppt/200010301419520125.pdf", null);
		DownFileUtil.getInstance().downHttpFile("http://img1.cache.netease.com/cnews/2010/9/7/201009071234281e930.jpg", null);
		
	}
}
