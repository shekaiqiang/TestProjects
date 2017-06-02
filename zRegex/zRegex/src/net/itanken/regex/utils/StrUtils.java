package net.itanken.regex.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtils {
	
	public static final String FILE_SEPAR = System.getProperty("file.separator"); //当前系统 文件分隔符('\')
	public static final String PATH_SEPAR = System.getProperty("path.separator"); // 当前系统路径分隔符(';')
	public static final String LINE_SEPAR = System.getProperty("line.separator"); // 当前系统行分隔符('/n')

	public static final String USER_NAME = System.getProperty("user.name"); // 用户账户名称
	public static final String USER_HOME = System.getProperty("user.home"); // 用户主目录
	public static final String USER_DIR = System.getProperty("user.dir"); // 用户当前工作目录

	public static final String JAVA_HOME = System.getProperty("java.home"); // Java安装目录
	public static final String JAVA_VERSION = System.getProperty("java.version"); // Java版本

	public static final String OS_NAME = System.getProperty("os.name"); // 操作系统名称
	public static final String OS_ARCH = System.getProperty("os.arch"); // 操作系统构架
	public static final String OS_VERSION = System.getProperty("os.version"); // 操作系统版本

	/**
	 * 判断字符s是否包含字符strs中的字符
	 * @param strs
	 * @param s
	 * @return
	 */
	public static boolean isExists(String strs, String s) {
		for (int i = 0; i < strs.length(); i++) {
			if (!s.toUpperCase().contains(strs.toUpperCase().substring(i, i+1))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 判断字符串是否为Int型字符
	 * @param str
	 * @return
	 */
	public static boolean isInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * Whether it is Chinese character
	 * @param inStr
	 * @return
	 */
	public static boolean isCnStr(String inStr) {
		boolean flag = false;
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]"); 
        Matcher m = p.matcher(inStr); 
        if(m.find()){ 
        	flag = true;
        }
        return flag;
	}
	
	/**
	 * 是否是空对象或空字符串
	 * @param inObj
	 * @return
	 */
	public static boolean isEmpty(Object inObj) {
		return inObj == null || "".equals(inObj.toString().trim()) || "null".equalsIgnoreCase(inObj.toString().trim());
	}
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().equals("") || str.trim().toUpperCase().equals("NULL");
	}
	
	/**
	 * 当前详细时间：yyyy-MM-dd HH:mm:ss.SSS
	 * @return
	 */
	public static String nowDateTime() {
		Object[] longArr = {new Long(System.currentTimeMillis())};
		return String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL" , longArr);
	}

	/**
	 * 当前日期：yyyy-MM-dd
	 * @return
	 */
	public static String nowDate() {
		Object[] longArr = {new Long(System.currentTimeMillis())};
		return String.format("%1$tY-%1$tm-%1$td" , longArr);
	}

	/**
	 * 当前时间：HH:mm:ss
	 * @return
	 */
	public static String nowTime() {
		Object[] longArr = {new Long(System.currentTimeMillis())};
		return String.format("%1$tH:%1$tM:%1$tS" , longArr);
	}
	
	public static void main(String[] args) {
		System.out.println(nowDateTime());
		System.out.println(nowDate());
		System.out.println(nowTime());
		System.out.println(isExists("IIIVVV", "IVXLCDM"));
	}
}
