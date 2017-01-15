package cn.itanken.jcomp.utils;

import java.io.File;

/**
 * .img 过滤器
 * @author Tanken·L
 * @version 20150205
 */
public class FileFilterImg extends javax.swing.filechooser.FileFilter {
	public boolean accept(File f) {
        if (f.isDirectory())return true;
        return f.getName().endsWith(".img");  //设置为选择以.img为后缀的文件
	}

	public String getDescription() {
        return ".img";
	}
}