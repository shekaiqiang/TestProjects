package test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

public class HttpTester {

	public static void main(String[] args) {
		HttpClient http = new HttpClient();
		GetMethod get = new GetMethod("https://zixizixi.cn/");
		// PostMethod post = new PostMethod("https://zixizixi.cn/");
		try {
		    // System.out.println(post.getRequestHeader("User-Agent"));
		    HttpClientParams params = new HttpClientParams();
		    params.setConnectionManagerTimeout(10000);
		    http.setParams(params);
			//get.getParams().setContentCharset("utf-8");
			//get.addRequestHeader("accept-encoding", "utf-8,gbk,gzip,default");
			get.addRequestHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.3226.400 iBrowser/0.0.1");
			int er = http.executeMethod(get);
			if (er == 200) {
				System.out.println("ResponseContentLength: " + get.getResponseContentLength());
				//String html = get.getResponseBodyAsString();
				InputStream in = get.getResponseBodyAsStream();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				byte[] b = new byte[1024];
				int len = -1;
				while ((len = in.read(b)) != -1) {
				    out.write(b, 0, len);
                }
				out.close();
				in.close();
				
				String html = new String(out.toByteArray());
				//html = new String(html.getBytes(), "utf-8");
				System.out.println(html);
				System.out.println("write length: " + html.getBytes().length);
			} else {
			    System.err.println("HTTP ERROR " + er);
			}
		} catch(Exception e) {
		    e.printStackTrace();
		} finally {
			get.releaseConnection();
		}
	}
}
