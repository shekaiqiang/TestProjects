package net.String;

import java.util.Date;

/**
 * 字符串格式化时间
 * <br><a href="http://blog.csdn.net/luoweifu/article/details/13775921">
 * http://blog.csdn.net/luoweifu/article/details/13775921</a>
 * @author Tanken
 */
public class StringFormatDate {
	
	public static final String STR_CONSTANT = "常量的值不可改变，必须在定义的同时赋值！"; // 常量
	public static String str_variable; // 变量

	public static void main(String[] args) {
		System.out.println("====================");
		System.out.println("使用 String 的 format 方法格式化 Date：");
		//格式化
		String tmstr = String.format("yyyy-MM-dd HH:mm:ss\t→ %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS" , new Date());
		//输出格式 yyyy-MM-dd HH:mm:ss
		System.out.println(tmstr);
		// yyyy-MM-dd
		tmstr = String.format("yyyy-MM-dd\t\t→ %1$tY-%1$tm-%1$td" , new Date());
		System.out.println(tmstr);
		// yyyy-MM-dd HH:mm:ss.SSS
		tmstr = String.format("yyyy-MM-dd HH:mm:ss.SSS\t→ %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL" , new Date());
		System.out.println(tmstr);
		System.out.println("====================");
		System.out.println(STR_CONSTANT);
		str_variable = "变量的值可以改变，定义的时侯可以不赋值。";
		System.out.println(str_variable);
		String var;
		var = "变量使用前必须先赋值！";
		System.out.println(var);
		System.out.println("====================");
		int initNum = 111111;
		int num = initNum;
        int count = 0;
        if(num > 0 && num <= 999999999) {
        	while(num != 0) {
        		count ++;
        		num /= 10;
        	}
        	System.out.println(initNum + " 是个"+ count+"位的整数！");
        } else {
        	System.out.println("输入有误");
        }
		System.out.println("====================");
        System.out.println("字符串中是否包含指定字符串：" + ("frame0".contains("frame")));
        
		System.out.println("====================");
	}
	
	public void test() {
        
	}
}
