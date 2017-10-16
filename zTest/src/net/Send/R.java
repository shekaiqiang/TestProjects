package net.Send;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// 接收端
public class R implements Runnable {
	private Socket socket = null;
	private File file = new File("d:\\2.zip"); // 接收到保存的文件

	public R(Socket socket) {
		this.socket = socket;
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs(); // 创建目录
		}
	}

	public void run() {
		System.out.println("已连接");
		try {
			InputStream in = socket.getInputStream();
			OutputStream out = new FileOutputStream(this.file);
			byte[] by = new byte[512];
			int len = 0;
			while (-1 != (len = in.read(by))) {
				out.write(by, 0, len);
			}
			out.flush();
			out.close();
			in.close();
			System.out.println("接受完毕");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8888);
			boolean flag = true;
			while (flag) {
				new Thread(new R(server.accept())).start(); // 阻塞
			}
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}