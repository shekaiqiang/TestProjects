package net.files;

import java.io.File;

public class FileUtils {

	/**
	 * 根据指定路径创建目录
	 * @param dirPath  目录绝对路径
	 * @return
	 */
	public static boolean makeDir(String dirPath) {
		File dirFile = new File(dirPath);
    	if(!dirFile.exists() || dirFile.isFile()) { // 目录不存在
    		dirFile.mkdir(); // 创建目录
    		return true; // 创建成功
    	}
		return false; // 目录已存在
	}
}
