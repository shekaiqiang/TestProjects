package net.Send;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * 一个简单的java爬虫 
 * java下载网络照片
 * @author Tanken
 */
public class JavaNetData{
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
           String imageUrl = "http://imgsrc.baidu.com/forum/w%3D580/sign=e8381d2f75f082022d9291377bfafb8a/60adf5600c338744ae8bd670570fd9f9d62aa0fe.jpg";
           URL url = new URL(imageUrl);
           //打开网络输入流
           DataInputStream dis = new DataInputStream(url.openStream());
           String newImageName="D:/Users/Administrator/Desktop/1.jpg";
            //建立一个新的文件
           FileOutputStream fos = new FileOutputStream(new File(newImageName));
           byte[] buffer = new byte[1024];
           int length;
           //开始填充数据
           while((length = dis.read(buffer))>0){
           fos.write(buffer,0,length);
           }
           dis.close();
           fos.close();     
    }
}