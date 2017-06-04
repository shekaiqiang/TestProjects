package net.apps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.files.FileUtils;

/**
 * 纯java环境下获取apk的信息：包名、版本号、系统要求最低版本、应用名、系统使用的权限。
 * 获取应用名目前只知道这种获取方法，其他的信息还有另一种方法
 */
public class GetApkInfo {

	public static String[] getApkName(String apkPath, String aaptPath) {
		String apkName = ""; // 应用名称
		String packageName = ""; // 包名
		String versionCode = ""; // 版本号
		String versionName = ""; // 版本名
		String iconName = ""; // 图标文件名
		try {
			Runtime rt = Runtime.getRuntime();
			String order = aaptPath + "aapt.exe d badging \"" + apkPath + "\"";
			Process proc = rt.exec(order);
			InputStream inputStream = proc.getInputStream(); // cmd 输入流
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String line = null;
			while((line = bufferedReader.readLine()) != null){
				System.out.println(line);
				if(line.contains("package:")) {
					packageName = line.substring(line.indexOf(": name='") + 8, line.indexOf("' versionCode='"));
					// System.err.println("\n packageName: " + packageName);
					versionCode = line.substring(line.indexOf("' versionCode='") + 15, line.indexOf("' versionName='"));
					// System.err.println("\n versionCode: " + versionCode);
					versionName = line.substring(line.indexOf("' versionName='") + 15, line.lastIndexOf("'"));
					// System.err.println("\n versionName: " + versionName);
				} else if(line.contains("application:")){//application: label='手机管家' icon='res/drawable-hdpi/icon.png'
					// String str1 = line.substring(line.indexOf("'")+1);
					// apkName = str1.substring(0, str1.indexOf("'"));
					apkName = line.substring(line.indexOf("'") + 1, line.indexOf("' icon='"));
					iconName = line.substring(line.lastIndexOf("/") + 1, line.lastIndexOf("'"));
					// System.err.println(iconName);
					// break;
				}
			}
			inputStream.close();
			bufferedReader.close();
			
			/********************************************************************/
			
	        byte b[] = new byte[1024];
	        int length;
	        ZipFile zipFile;
	        try {
	            zipFile = new ZipFile(new File(apkPath));
	            Enumeration<?> enumeration = zipFile.entries();
	            ZipEntry zipEntry = null;
	            while (enumeration.hasMoreElements()) {
	                zipEntry = (ZipEntry) enumeration.nextElement();
	                if (!zipEntry.isDirectory()) { // 不是目录，即是文件
	                    // 向指定路径logoUrl输出应用图标
	                    if ("res/drawable/i.jpg".equals(zipEntry.getName())) {
	            			String logoPath = apkPath.substring(0, apkPath.lastIndexOf("/") + 1) + "/icon/"; // 图标文件存放目录
	                    	FileUtils.makeDir(logoPath); // 判断图标目录是否存在创建目录
	                    	logoPath += packageName; 
	                    	FileUtils.makeDir(logoPath); // 创建包名目录
	                    	logoPath += ("/" + versionCode); 
	                    	FileUtils.makeDir(logoPath); // 创建版本目录
	                        OutputStream outputStream = new FileOutputStream(logoPath + "/" + iconName); // 应用文件输出流
	                        InputStream zipInputStream = zipFile.getInputStream(zipEntry); // 应用文件输入流
	                        while ((length = zipInputStream.read(b)) > 0) {
	                            outputStream.write(b, 0, length);
	                        }
	                        if(zipInputStream != null) {
	                        	zipInputStream.close();
	                        	zipInputStream = null;
	                        }
	                        if(outputStream != null) {
	                            outputStream.close();
	                            outputStream = null;
	                        }
	                    }
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		} catch (Throwable e) {
			e.printStackTrace();
		}
		String[] apkInfo = {apkName, packageName, versionCode, versionName, iconName};
		return apkInfo;
	}

	public static void main(String[] args) {
		String[] apkInfo = GetApkInfo.getApkName("D:/Development/Tools/Android/LockScreen/LockScreen7.apk", "./src/test/apps/exe/");
		// Arrays.sort(apkInfo, new InfoComparator()); // 自定义排序
		System.err.println(Arrays.toString(apkInfo));
	}
}

class InfoComparator implements Comparator<String>{
	public int compare(String arg0, String arg1) {
		// return arg0.length() - arg1.length(); // 根据字符长度排序
		return arg0.getBytes().length - arg1.getBytes().length; // 根据字节长度排序
	}  
}
