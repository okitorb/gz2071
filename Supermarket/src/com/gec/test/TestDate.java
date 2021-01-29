package com.gec.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
	public static void main(String[] args) {
		SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd" );
        Date d1= new Date();
        String str1 = sdf1.format(d1);
        System.out.println("当前时间通过 yyyy-MM-dd 格式化后的输出: "+str1);
        String s = str1.replaceAll("-", "");
        System.out.println(s);
        
        int i = 0;
        String s1 = "vip"+s+"000"+ i;
        System.out.println(s1);
	}
}
