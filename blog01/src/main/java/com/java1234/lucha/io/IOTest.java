package com.java1234.lucha.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class IOTest {
	
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		
//		Scanner scanner = new Scanner(System.in);
//		System.out.print("请输入姓名:");
//		String name = scanner.next();
//		System.out.print("请输入学号:");
//		String number = scanner.next();
//		
//		FileOutputStream os = new FileOutputStream("student.txt");
//		os.write(name.getBytes("utf-8"));
//		os.write("\r\n".getBytes());
//		//os.write(10);
//		//os.write(13);
//		os.write(number.getBytes("utf-8"));
//		
//		os.close();
//		scanner.close();
		String str = "啊啊	啊s v　v v 　　s";
		System.out.println(str.replaceAll("\\s*", ""));
		System.out.println(str.replaceAll("　| ", "").replaceAll("\\s*", ""));
	}
	
	  static String myTrim(String source, String toTrim) {//将字符串两边的半角空格、全角空格去掉，其他也可以
	        StringBuffer sb = new StringBuffer(source);
	        while (toTrim.indexOf(new Character(sb.charAt(0)).toString()) != -1) {
	            sb.deleteCharAt(0);
	        }
	        while (toTrim.indexOf(new Character(sb.charAt(sb.length() - 1))
	                .toString()) != -1) {
	            sb.deleteCharAt(sb.length() - 1);
	        }
	        return sb.toString();
	    }

}
