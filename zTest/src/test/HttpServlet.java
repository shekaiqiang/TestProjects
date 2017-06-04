package test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpServlet {
	// 请求服务URL
	// private final static String PATH =
	// "http://10.0.2.2:8000/TaxiServlet/login";
	private static URL url;

	public HttpServlet() { }

	/**
	 * 向服务端发送请求
	 * @param params url参数
	 * @param encode 字节编码
	 * @param PATH servlet地址
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String sendMessage(Map<String, String> params, String encode,
			String PATH) {
		// 初始化URL
		StringBuffer buffer = new StringBuffer();

		if (null != params && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				buffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue())).append("&");
			}	
			// 删除多余的&
			buffer.deleteCharAt(buffer.length() - 1);
		}

		try {
			url = new URL(PATH);
			if (null != url) {
				HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
				if (null == urlCon) {
					return "ERROR";
				}
				urlCon.setConnectTimeout(3000);
				urlCon.setRequestMethod("POST"); // 设置请求方式
				urlCon.setDoInput(true); // 表示从服务器获取数据
				urlCon.setDoOutput(true); // 表示向服务器发送数据

				byte[] data = buffer.toString().getBytes();
				// 设置请求体的是文本类型
				urlCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				// urlCon.setRequestProperty("Content-Length",
				// String.valueOf(data.length));
				// 获得输出流
				OutputStream outputStream = urlCon.getOutputStream();
				outputStream.write(data);
				outputStream.close();
				// 获得服务器的响应结果和状态码
				int responseCode = urlCon.getResponseCode();
				if (200 == responseCode) {
					return changeInputStream(urlCon.getInputStream(), encode);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "网络异常,请重试";
	}

	/**
	 * 获得回XML
	 * @param inputStream
	 * @param encode
	 * @return
	 */
	private static String changeInputStream(InputStream inputStream, String encode) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		String result = "";
		if (null != inputStream) {
			try {
				while ((len = inputStream.read(data)) != -1) {
					outputStream.write(data, 0, len);
				}
				result = new String(outputStream.toByteArray(), encode);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}