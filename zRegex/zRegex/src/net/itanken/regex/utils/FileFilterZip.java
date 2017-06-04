package net.itanken.regex.utils;

import java.io.File;

/**
 * .zip 过滤器
 * @author Tanken·L
 * @version 20150205
 */
public class FileFilterZip extends javax.swing.filechooser.FileFilter {

	public boolean accept(File f) {
        if (f.isDirectory())return true;
        return f.getName().endsWith(".zip");  //设置为选择以.zip为后缀的文件
	}
	public String getDescription() {
        return ".zip";
	}
}