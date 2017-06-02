package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件日志
 * @author Tanken·L
 * @Start  2015-11-25
 * @Done   2015-11-25
 * @Revision 
 */
public class FileLog {

	/**
	 * 写日志文件
	 * @param logDir  日志存放文件夹名称
	 * @param content 日志内容
	 */
	public static void writeLog(String logDir, String content) {
		writeFileByStream("C://AreaLog//" + logDir, nowStr("yyyy-MM-dd") + "Log.txt", content);
	}
	
	/** 
     * 用字节流的方式写入文本文件 <br>
     * 文件输出流的创建方式有两种，File对象或者文件绝对路径。 
	 * @param fileDir 文件存放文件夹的绝对路径
	 * @param fileName 文件名称
	 * @param content  文件内容
	 */
    public static void writeFileByStream(String fileDir, String fileName, String content){  
        try {
        	File dir = new File(fileDir);
        	if(!dir.exists() && !dir.isDirectory()) {
        		dir.mkdir();
        	}
            FileOutputStream out = new FileOutputStream(fileDir + "/" + fileName, true); // true:追加
            //另一种方式  
            //FileOutputStream out = new FileOutputStream(new File(filepath)); 
            out.write(content.getBytes()); // "UTF-8"
            out.close();
            if(setFileAttr(fileDir + "/" + fileName, "+A")) {
            	// System.out.println("成功修改文件属性！");
            }
        } catch (FileNotFoundException e) {
        	System.out.println("系统找不到指定的文件路径，或没有访问权限！" + e);
        } catch (IOException e) {
        	System.out.println("文件创建失败！" + e);
        }
    }

    /**
     * 修改文件属性<br>
     *  R ： 只读文件属性。A：存档文件属性。S：系统文件属性。H：隐藏文件属性<br>
     *  +：设置属性；-：取消属性；设为只读、隐藏或系统文件属性后，可能会由于权限原因不能再次修改文件属性。
     * @param filePath 要修改属性的文件路径
     * @param attrs 可以同时修改多个属性，用空格隔开，如：+S +H(受系统保护的隐藏文件)
     * @return
     */
    public static boolean setFileAttr(String filePath, String attr) {
    	File file = new File(filePath);
    	StringBuilder cmdStr = new StringBuilder(100);
    	cmdStr.append(" attrib ").append(attr).append(" ").append(file.getAbsolutePath());
		try {
			Runtime.getRuntime().exec(cmdStr.toString());
	    	return true;
		} catch (IOException e) {
        	System.out.println("修改文件属性失败：" + e);
		}
    	return false;
    }
	
	/**
	 * 返回指定格式时间字符串
	 * @param inFmt
	 * @return
	 */
	public static String nowStr(String inFmt) {
		return new SimpleDateFormat(inFmt).format(new Date());
	}
}
