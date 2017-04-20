package com.jujie.task.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CreatTaskCode {
	
	public static String creatCode(int prex){
		String px = "P";
		switch(prex){
			case 1 : px+="A";break;
			case 2 : px+="B";break;
			case 3 : px+="C";break;
		}
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyMMdd");
		String dateStr = sf.format(date);
		Random random = new Random();
		int v = 0;
		while(v <1000){
			v = random.nextInt(10000);
		}
		return px+dateStr+v;
	}
	
	public static void main(String[] args) {
		for(int i = 0 ; i < 20 ; i++){
			System.out.println(CreatTaskCode.creatCode(1));
		}
	}
	
}
