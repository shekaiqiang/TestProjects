package net.Send;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 发送端
 * @author Tanken·L
 * @Start 2016年4月4日
 * @Done  2016年4月4日
 * 特点：取得本机ip，然后根据本机ip尝试连接本机所在网段（例：192.168.132.*）的所有ip，
 * 连接成功后开始监听固定盘符下的固定文件，当发现该文件，立即发送！
 */
public class S {
	private static String localIp = null; // 本机ip
	private static String ip = "192.168.1."; // 网段 默认
	private static int port = 80; // 连接端口
	private static final File FILE = new File("d:\\1.zip"); // 监听发送的文件
	private static int count = 1; // ip的自增

	static {
		try {
			localIp = null; //InetAddress.getLocalHost().getHostAddress().toString(); // 得到本机ip,可以吗？
			System.out.println(localIp);
			if (null != localIp) {
				ip = localIp.substring(0, localIp.lastIndexOf('.')) + "."; // 根据本机ip得到所在网段
			}
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) {
		boolean flag = true;
		Socket socket = null;

		// 循环尝试连接，直到连接成功
		while (flag) {
			if (count > 255) { // 如果ip最后一位超过255 则重新计数
				count = 1;
			}
			try {
				System.out.println("尝试连接" + ip + count);
				// socket = new Socket(ip+count, port);
				socket = new Socket();
				socket.connect(new InetSocketAddress(ip + count, port), 4000); // 尝试连接，最长尝试4s
			} catch (UnknownHostException e) {
				count++; // 如果连接不上则ip最后一位加1，继续连接
				continue;
			} catch (IOException e) {
				count++;
				continue;
			}
			System.out.println("已连接");
			break;
		}

		// 循环监听文件，直到发现文件
		while (flag) {
			if (FILE.exists()) {
				System.out.println("发现文件");
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 发送文件
		if (null != socket) {
			try {
				OutputStream out = socket.getOutputStream();
				InputStream in = new FileInputStream(FILE);
				byte[] by = new byte[512];
				int len = 0;
				while (-1 != (len = in.read(by))) {
					out.write(by, 0, len);
				}
				out.flush();
				out.close();
				in.close();
				System.out.println("发送成功");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}