package test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Properties;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NoHttpResponseException;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang3.StringEscapeUtils;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * Http 客户端请求 WebService 测试
 */
public class HttpClientManager {

	private static final HttpConnectionManager connectionManager;
	private static final HttpClient client;

	private static String httpHost = ""; // 主机地址
	private static String httpUrl = ""; // HTTP 请求地址
	private static String contentType = "text/xml; charset=utf-8";
	private static String soapNamespace = "http://tempuri.org/";
	private static String soapActionName = "Execute";
	
	static {
		HttpConnectionManagerParams params = loadHttpConfFromFile();
		connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.setParams(params);
		client = new HttpClient(connectionManager);
	}

	/**
	 * Post 请求  WebService
	 * @param param 请求内容
	 * @param code 调用方法名称
	 * @param actionName 服务操作名称
	 * @return 服务器的响应内容
	 * @throws Exception
	 */
	public static String postWebService(String param, String code, String actionName) throws Exception {
		isEmptyStr(httpUrl, "HTTP 请求地址不能为空，请检查配置！");
		param = soapParam(param, code);
		actionName = isEmptyStr(actionName) ? soapActionName : actionName;
		InputStream in = null;
		StringBuilder sb = null;
		System.out.println("【POST URL】 : " + httpUrl);
		System.out.println("【Request Body】 : " + param);
		PostMethod post = new PostMethod(httpUrl);
		Element resEle = null;
		try {
			RequestEntity entity = new StringRequestEntity(param, "text/xml", "UTF-8");
			post.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
			post.addRequestHeader("Content-Type", contentType);
			System.out.println("【Content-Type】 : " + contentType);
			post.addRequestHeader("SOAPAction", soapNamespace + actionName);
			System.out.println("【SOAPAction】 : " + soapNamespace + actionName);
			post.addRequestHeader("Host", httpHost);
			System.out.println("【Host】 : " + httpHost);
			post.setRequestEntity(entity);
			int statusCode = client.executeMethod(post);
			if (statusCode == HttpStatus.SC_OK) {
				System.out.println("【HTTP Status】 : " + statusCode);
				try {
					in = post.getResponseBodyAsStream();
					sb = inStreamStr(in);
				} catch (IOException e) {
					throw new Exception("HTTP ERROR 504:服务器处理成功，响应出现 IOException。");
				}
			} else {
				throw new Exception("HTTP ERROR " + statusCode + ":执行Http Post时，发生异常。");
			}
			resEle = DocumentHelper.parseText(sb.toString()).getRootElement();
		} catch (ConnectException e) {
			throw new ConnectException("HTTP ERROR 404:与服务器建立连接时，发生异常！");
		} catch (ConnectTimeoutException e) {
			throw new ConnectTimeoutException("HTTP ERROR 404:与服务器建立连接超时！");
		} catch (SocketTimeoutException e) {
			throw new SocketTimeoutException("HTTP ERROR 504:从服务器获取响应数据超时！");
		} catch (NoHttpResponseException e) {
			throw new NoHttpResponseException("HTTP ERROR 504:服务器无响应！");
		} catch (SocketException e) {
			System.out.println("执行Http Post请求时，发生SocketException异常！");
			throw e;
		} catch (Exception e) {
			System.out.println("执行Http Post请求时，发生异常！");
			throw e; 
		} finally {
			post.releaseConnection();
			if (in != null)
				in.close();
		}
		return soapResult(resEle, actionName);
	}

	/**
	 * 配置参数
	 * @return
	 */
	private static HttpConnectionManagerParams loadHttpConfFromFile() {
        Properties p = new Properties();
        try {
			InputStream fis = HttpClientManager.class.getResourceAsStream("httpclientmanager.properties");
        	p.load(fis);
		} catch (Exception e) {
			System.out.println("读取  httpclientmanager.properties 配置失败...");
		}
        // Http Config
		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		int ctimeout = Integer.parseInt(p.getProperty("http.connection.timeout", "30000"));
		params.setConnectionTimeout(ctimeout);
		int stimeout = Integer.parseInt(p.getProperty("http.so.timeout", "30000"));
		params.setSoTimeout(stimeout);
		boolean enabled = Boolean.parseBoolean(p.getProperty("http.stale.check.enabled", "true"));
		params.setStaleCheckingEnabled(enabled);
		boolean delay = Boolean.parseBoolean(p.getProperty("http.tcp.no.delay", "true"));
		params.setTcpNoDelay(delay);
		int perhost = Integer.parseInt(p.getProperty("http.default.max.connections.per.host", "100"));
		params.setDefaultMaxConnectionsPerHost(perhost);
		int maxtotal = Integer.parseInt(p.getProperty("http.max.total.connections", "1000"));
		params.setMaxTotalConnections(maxtotal);
		
		// WebService Config
		httpHost = p.getProperty("http.webservice.host");
		httpUrl = p.getProperty("http.webservice.url");
		contentType = p.getProperty("http.webservice.content.type", "text/xml; charset=utf-8");
		soapNamespace = p.getProperty("http.webservice.soapaction.namespace", "http://tempuri.org/");
		soapActionName = p.getProperty("http.webservice.soapaction.name", "Execute");
		
		return params;
	}

	/**
	 * 读取 HTTP 响应内容
	 * @param instream
	 * @return
	 * @throws IOException
	 */
	public static StringBuilder inStreamStr(InputStream instream) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(instream, "UTF-8"));
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		return sb;
	}
	
	/**
	 * WebService 请求体
	 * @param inVal
	 * @param code
	 * @return
	 */
	public static String soapParam(String inVal, String code) {
		inVal = StringEscapeUtils.escapeXml11(inVal);
		String param = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
								   + " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\""
								   + " xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" + 
						  "<soap:Body>"+
						    "<Execute xmlns=\"" + soapNamespace + "\">"+
						      "<BusiCode>" + code + "</BusiCode>"+
						      "<Value>" + inVal + "</Value>" +
						    "</Execute>" +
						  "</soap:Body>" +
					   "</soap:Envelope>";
		return param;
	}
	
	/**
	 * WebService 返回结果
	 * @param inEle
	 * @return
	 */
	private static String soapResult(Element inEle, String actionName) {
		System.out.println("\n【Response XML】 ：" + docToStr(inEle));
		Element exeRes = inEle.element("Body").element(actionName + "Response");
		if(exeRes.element("OutValue") == null) {
			return exeRes.elementText(actionName + "Result");
		} else {
			return exeRes.elementText("OutValue");
		}
	}
	
	/**
	 * 验证字符串是否为空，并抛出异常
	 */
	public static void isEmptyStr(Object inStr, String exception) throws Exception {
		if (inStr == null || "".equals(inStr)) {
			throw new Exception(exception);
		}
	}
	
	/**
	 * 字符串是否为空 ""/null
	 * @param inStr
	 * @return
	 */
	public static Boolean isEmptyStr(String inStr) {
		return inStr == null || "".equals(inStr.trim()) || "null".equalsIgnoreCase(inStr.trim());
	}
	
	/**
	 * 修改 httpUrl
	 * @param url WebService请求地址
	 */
	public static void setPostUrl(String url) {
		httpUrl = url;
	}

	/**
	 * 格式化 Document 并转化为 String (Test use)
	 * @param doc
	 * @return
	 * @author Tanken·L
	 * @version 2015-7-27
	 */
	public final static String docToStr(Element doc) {
		String s = "";
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream(); // 使用输出流进行转化
			OutputFormat format = new OutputFormat("  ", true, "UTF-8"); // 使用两个空格缩进、换行true、  UTF-8  格式编码
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(doc);
			s = out.toString("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
}