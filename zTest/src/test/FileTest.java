package test;

import java.io.File;
import java.util.LinkedList;

public class FileTest {
	
	public static void main(String[] args) {
		String pathSimpleface = "/Users/StarSevenSky/Pictures/Saved Pictures/simpleface";
		readAllFilesName(pathSimpleface);
	}
	
	public static void readAllFilesName(String path) {
        int fileNum = 0, folderNum = 0;
        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    // System.out.println("文件夹:" + file2.getAbsolutePath());
                    list.add(file2);
                    folderNum++;
                } else {
                	String data = "{\n\t\"icon\": \"<img src=\\\"//itanken.oss-cn-hangzhou.aliyuncs.com/images/emimg/simple/" + file2.getName() +
                			"?x-oss-process=image/resize,h_100\\\">\",\n\t" + "\"text\": \"\"\n},";
                    System.out.println(data);
                    fileNum++;
                }
            }/*// 子目录
            File temp_file;
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        list.add(file2);
                        fileNum++;
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());
                        folderNum++;
                    }
                }
            }*/
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("\n文件夹共有:" + folderNum + ",文件共有:" + fileNum);

    }
}
