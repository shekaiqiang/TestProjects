package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

import com.FileLog;;

@SuppressWarnings("unused")
public class ZTest {

	public static void main(String[] args) throws Exception {
		// System.out.println(portProxy("192.168.202.98", "8080", "127.0.0.1", "8080", "delete")); // 删除端口转发
		// System.out.println(portProxy("192.168.202.98", "8080", "127.0.0.1", "8080", "add")); // 添加端口转发
		// System.out.println(portProxy("", "", "", "", "")); // 查询端口转发
		// System.out.println(portProxy("", "", "", "", "add"));
		// System.out.println("v" + (int)(Math.random()*(9999-1000+1)+1000)); // 四位随机数
		String url = "http://m.itanken.cn/qr#";
		url = url.substring("http://m.itanken.cn".length(), url.length());
		System.out.println(url);
		//List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);  
		//numbers.forEach((Integer value) -> System.out.println(value)); 
		
		//ZTest test = new ZTest();
		//System.out.println(test.getSourcePath());
		//System.out.println("str".toUpperCase().contains("STR"));
		/*
		System.out.println(url.substring(0, (url.indexOf("?") < 0 ? 0 :url.indexOf("?"))));
		
		// Java Integer的缓存， -128 to 127
		System.out.println(Integer.valueOf(10) == Integer.valueOf(10)); // true
		System.out.println(Integer.valueOf(127) == Integer.valueOf(127));
		System.out.println(Integer.valueOf(1000) == Integer.valueOf(1000)); // false

		System.out.println(2-1.1); // 0.8999999999999999
		System.out.println(2-1.1f); // 0.9
		
		
		// 在 method 方法中实现调用之后输出 a = 100, b = 200
		int a = 10;
		int b = 10;
		method(a, b);
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		*/
		// YueSeFuLoop();
		// armstrongTest();
		// strLength();

		// TODO: No.1
		//开始写代码，给定一个正整数，用递归的方法计算它的阶乘.main函数已给出，实现Recursion类
		int number;
		number = 12;
		Recursion factorialRecursion = new Recursion();
		System.out.println(number + "! = " + factorialRecursion.recursion(number));	
		
		// ipToLong("192.168.199.1");
		// InsertSortTest();
		// FindNumberTest();
		// vLinks("html", "data.html");
		// vLinks("jplayer", "data.json");
		// emimg();
	}
	
	public static void emimg() {
		for (int i = 1; i < 55; i++) {
			String data = "{\n\t\"icon\": \"<img src=\\\"//itanken.oss-cn-hangzhou.aliyuncs.com/images/emimg/acfun/" +
					i + ".png?x-oss-process=image/resize,h_100\\\">\",\n\t" + "\"text\": \"\"\n},";
			System.out.println(data);
		}
	}
	public static void vLinks(String type, String fileName) throws UnsupportedEncodingException, IOException {
		/** 
		 * 1 ~ 183 &! 5,141,146-171,175,181
		 * 106 - 499
		 * 500 - 3489
		 * 7000 - 7289
		 * 7630 - 7977
		 * 7990 - 11899
		 */
		String filesepar = System.getProperty("file.separator");
		String path = System.getProperty("user.home") + filesepar + "Downloads";
	    String[] pathDir = path.split(filesepar);
	    path = pathDir[0];
	    for (int i = 1; i < pathDir.length; i++) {
	      path = path + filesepar + pathDir[i];
	      File dir = new File(path);
	      if ((!dir.exists()) || (dir.isFile()))
	        dir.mkdir();
	    }
	    String filePath = path + filesepar + fileName;
		new File(filePath);
        FileOutputStream fos = new FileOutputStream(filePath, false);
        if(type.toLowerCase().equals("html")){
            String sHead = "<!DOCTYPE html><html lang=\"zh\"><head><title>iTanken</title><meta charset=\"utf-8\">"
            		+ "<style>a{display:inline-block;width:100px; height: 30px; line-height: 30px; font-size: 20px; }a:hover{background-color:gray;}"
            		+ "</style></head><body>";
            fos.write(sHead.getBytes("UTF-8"));
        }
		int notip[] = {5,141,175,181};
		int ip = 1;
		for (int i = 106; i < 11900; i++) {
			if(Arrays.binarySearch(notip, ip) > -1) {
				ip++;
			} else if(ip > 145 && ip < 172) {
				ip = 172; 
			} else if(ip > 183) {
				ip = 1;
			}
	        if(type.toLowerCase().equals("html")){
		        fos.write(("<a href='http://120.52.73." + ip + "/adultvideo.science/media/videos/iphone/" + i + ".mp4'>" + i + "</a>").getBytes("UTF-8"));
	        } else if(type.toLowerCase().equals("jplayer")) {
		        fos.write(("{title:'" + i + "',ogv:'http://120.52.73." + ip + "/adultvideo.science/media/videos/iphone/" + i + ".mp4'},").getBytes("UTF-8"));
	        }
			ip ++;
		}
		if(type.toLowerCase().equals("html")){
	        fos.write("</body></html>".getBytes("UTF-8"));
        }
        fos.close();
	}
	
	public static void method(int a, int b) {
		a = 100;
		b = 200;
		
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		
		System.exit(0);
	}

	/**
	 * windows系统修改端口映射
	 * @param address
	 * @param port
	 * @param oprType
	 * @return
	 * @author Tanken·L
	 * @Start  2015-11-20
	 * @Done   2015-11-20
	 * @Revision 
	 */
	public static String portProxy(String addressL, String portL, String addressC, String portC
			, String oprType) throws Exception {
		String newLine = System.getProperty("line.separator"); // 当前系统换行符
		FileLog.writeLog("CMD", newLine + " ==================================== 【" + nowStr("yyyy-MM-dd HH:mm:ss.SSS")
				+ "】 ==================================== " + newLine);
		System.out.print(" ==================================== 【");
		System.out.print(nowStr("yyyy-MM-dd HH:mm:ss.SSS"));
		System.out.println("】 ==================================== ");
		if((isEmptyStr(addressL) || isEmptyStr(portL)) && !isEmptyStr(oprType)) {
			FileLog.writeLog("CMD", "【监听地址[addressL] 和 端口[portL] 不能为空！】" + newLine);
			return "【监听地址[addressL] 和 端口[portL] 不能为空！】\n";
		} else if((isEmptyStr(addressC) || isEmptyStr(portC)) && "add".equals(oprType)) {
			FileLog.writeLog("CMD", "【转发地址[addressC] 和 端口[portC] 不能为空！】" + newLine);
			return "【转发地址[addressC] 和 端口[portC] 不能为空！】\n";
		} 
		StringBuilder cmdStr = new StringBuilder(100);
		if("add".equals(oprType)) {
			FileLog.writeLog("CMD", "【添加映射】" + newLine);
			System.out.println("【添加映射】");
			cmdStr.append(" netsh interface portproxy add v4tov4 listenaddress=").append(addressL)
				  .append(" listenport=").append(portL).append(" connectaddress=").append(addressC)
				  .append("  connectport=").append(portC);
		} else if("delete".equals(oprType)) {
			FileLog.writeLog("CMD", "【删除映射】" + newLine);
			System.out.println("【删除映射】");
			cmdStr.append(" netsh interface portproxy delete v4tov4 listenaddress=")
				  .append(addressL).append(" listenport=").append(portL);
		} else {
			FileLog.writeLog("CMD", "【查询映射】" + newLine);
			System.out.println("【查询映射】");
	    	cmdStr.append(" netsh interface portproxy show all ");
		}

        BufferedReader br = null;
		try {
			FileLog.writeLog("CMD", " [执行命令]：" + cmdStr.toString() + newLine);
			System.out.println(" [执行命令]：" + cmdStr.toString());
			Process p = Runtime.getRuntime().exec(cmdStr.toString());
			//StringBuffer sbOut = new StringBuffer(1000); 
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			int i = 0;
            while ((line = br.readLine()) != null) {
            	if(i <= 0) {
            		FileLog.writeLog("CMD", " [输出内容]：");
            		System.out.println(" [输出内容]：");
            		++i;
            	}
            	FileLog.writeLog("CMD", line);
    			if("\r".equals(line)) {
    				System.out.println("---------------------*/*/*/*/*/");
    			}
                System.out.println(line);               
            }
            FileLog.writeLog("CMD", newLine + "【执行结束】" + newLine);
	    	return "【执行结束】\n";
		} catch (Exception e) {
			FileLog.writeLog("CMD", e.toString() + newLine);
			e.printStackTrace();
		}
		FileLog.writeLog("CMD", "【执行失败】" + newLine);
		return "【执行失败】\n";
	}
    
	/**
	 * 检查字符串是否为空： null/"null"/""
	 * @param str
	 * @return boolean
	 */
	public final static boolean isEmptyStr(String str) {
		return ((str == null || str.trim().equalsIgnoreCase("null") || str.trim().equals("")) ? true : false);
	}
	
	/**
	 * 返回指定格式时间字符串
	 * @param inFmt
	 * @return
	 */
	public static String nowStr(String inFmt) {
		return new SimpleDateFormat(inFmt).format(new Date());
	}
	
	/**
	 * 用java代码实现约瑟夫环问题，50个人围成一圈报数，报到3的倍数的离开，求最后剩下的那个人原来站的位置
	 */
	public static void YueSeFuLoop() { 
        LinkedList<Integer> linkedlist = new LinkedList<Integer>();
		//No.1
		//开始写代码，有50人围成一圈报数，报到3的倍数的人离开，求最后剩下的人原来站在第几位，实现removeFromList方法
        for (int i = 1; i <= 50; i++) {  
            linkedlist.add(i);  
        }  
		
        int index = 0;
        while(linkedlist.size() > 0) { // 最后一个人离开之前
        	for (int i = 0; i < (3 - 1); i++) { // 如果不是 3 的倍数
        		System.out.println("before : " + linkedlist.size());
        		int num = linkedlist.remove(0); // 移除不是 3 的倍数的数，第一次开始为 1
				linkedlist.add(num); // 将不是 3 的倍数的数放在最后，第一次开始是把 1 放在 50 之后
        		System.out.println("after  : " + linkedlist.size() + " \t num: " + num);
			}
        	// 未进入 for 循环，说明是 3 的倍数，则从列表中移除
        	index = linkedlist.remove(0); 
    		System.out.println("end    : " + linkedlist.size() + " \t index: " + index);
        }
        // while 循环执行完毕，最后一个人已被移除
        System.out.println(index); // 最后一个人开始所站的位置 11
	}
	
	/**
	 * 例如153=1^3+5^3+3^3的数叫做Armstrong数，用java代码实现输出三位数的Armstrong数
	 */
	public static void armstrongTest() {
		int hundredsDigit,tensDigit,unitsDigit;//hundredsDigit表示数字的百位，tensDigit表示数字的十位，unitsDigit表示数字的个位
		System.out.println("寻找Armstrong数：");
		for (int i = 100; i <= 999; i++) {
			//No.1
			//开始写代码，例如153可以满足1^3 + 5^3 + 3^3 = 153，这样的数称为Armstrong数，输出所有三位数中的Armstrong数
			hundredsDigit = i / 100;
			tensDigit = (i % 100) / 10;
			unitsDigit = i % 10;
			// System.out.println(i + " : " + hundredsDigit + " - " + tensDigit + " - " + unitsDigit + " --- " + (Math.pow(hundredsDigit, 3) + Math.pow(tensDigit, 3) + Math.pow(unitsDigit, 3)));
			
			if ((Math.pow(hundredsDigit, 3) + Math.pow(tensDigit, 3) + Math.pow(unitsDigit, 3)) == i)
				System.out.print(i + " ");
			//end_code
		}
		System.out.println();
	}
	
	/**
	 * 用java代码实现输入一串字符串，统计其中的数字、英文、空格、其他字符个数
	 */
	public static void strLength() {
		int digital = 0;//数字个数
		int character = 0;//英文个数
		int other = 0;//其他字符个数
		int blank = 0;//空格个数
		char[] chars = null;
		System.out.println("这是任意一串字符：");
		String string = "djfiepqo ioghr4 8758495 7123hr37hfjW$@@$@^%!";
		chars = string.toCharArray();
		//No.1
		//开始写代码，计算任意一串字符中的数字个数、英文字母个数、空格个数和其他字符个数
		for (int i = 0; i < chars.length; i++) {
			if(chars[i] >= '0' && chars[i] <= '9') {
				digital++;
			} else if((chars[i] >= 'a' && chars[i] <='z') || (chars[i] >= 'A' && chars[i] <='Z')) {
				character++;
			} else if(chars[i] == ' ') {
				blank++;
			} else {
				other++;
			}
		}
		//end_code
		System.out.println("数字个数: " + digital);
		System.out.println("英文字母个数: " + character);
		System.out.println("空格个数: " + blank);
		System.out.println("其他字符个数:" + other);
	}
	
	// 公约数和公倍数
	
	// 日期比较
	
	// 回型矩阵
	

	/**
	 * 用java代码实现插入排序
	 */
	public static void InsertSortTest() {
		int[] array = { 49, 38, 65, 97, 76, 13, 27, 14, 10 };
		//No.1
		//开始写代码，用java实现插入排序
		if (array == null || array.length == 0) {	//判断数组不存在或为空
			return;
		}
		 
		int target, i, j;
		int len = array.length;
        for (i = 1; i < len; i++) {
            j = i;
            target = array[i];
 
            while (j > 0 && target < array[j - 1]) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = target;
        }
		System.out.println(Arrays.toString(array));
	}

	/**
	 * 使用java代码实现将IPV4的IP地址转化为对应整数并输出，比如192.168.199.1对应整数为3232286465
	 * @param ipAddress
	 * @return
	 */
	public static long ipToLong(String ipAddress) {
		long result = 0;
		String[] ipAddressInArray;
		
		//No.1
		//开始写代码，将IPV4的IP地址转化为相对应的整数
		ipAddressInArray = ipAddress.split("\\.");
		long[] ipLong = new long[4];
		for (int i = 0; i < ipAddressInArray.length; i++) {
			// TODO: something...
			ipLong[i] = Long.parseLong(ipAddressInArray[i]);
		}

        //long[] ip = new long[4];  
        //先找到IP地址字符串中.的位置  
        //int position1 = ipAddress.indexOf(".");  
        //int position2 = ipAddress.indexOf(".", position1 + 1);  
        //int position3 = ipAddress.indexOf(".", position2 + 1);  
        //将每个.之间的字符串转换成整型  
        //ip[0] = Long.parseLong(ipAddress.substring(0, position1));  
        //ip[1] = Long.parseLong(ipAddress.substring(position1+1, position2));  
        //ip[2] = Long.parseLong(ipAddress.substring(position2+1, position3));  
        //ip[3] = Long.parseLong(ipAddress.substring(position3+1));  
        result = (ipLong[0] << 24) + (ipLong[1] << 16) + (ipLong[2] << 8) + ipLong[3];  
		//end_code
        System.out.println(result);
		return result;
	}
	
	/**
	 * 找重复数字和未出现数字
	 * 实现：假设0-10000数字中有2个数字相同，还有1个数字没有出现，仅遍历一次数组找出重复数和未出现的数字。
	 */
	public static void FindNumberTest() {
		int number[] = new int[10001];
		int numCopy[] = new int[10001];
		int repeat = 0, notAppear = 0;
		int sumNumber = 0;
		int i;

		for (i = 0; i < 10001; i++) {//数组初始化
			number[i] = i;
		}
		number[573] = 5236;//设定重复数字5236出现两次，573不出现

		//No.1
		//开始写代码，仅遍历一次数组找出重复数和未出现的数字。 
		for (int j=0; j<number.length; j ++) {
			if(j != number[j]) {
				if(number[j] == number[sumNumber]) {
					System.out.println("rep: " + number[j] + "  nul: " + j);
					repeat = number[j];
					notAppear = j;
				}
			}
			sumNumber++;
		}
		
		//end_code
		System.out.println("重复数字："+repeat + " 未出现数字：" + notAppear);
	}
}

// TODO: No.1
//开始写代码，给定一个正整数，用递归的方法计算它的阶乘.main函数已给出，实现Recursion类
class Recursion {
	/**
	 * 用递归方法计算一个数的阶乘
	 */
    public int recursion(int number) {
        if(number < 0) {
            System.out.println("不支持的输入!");
            return 0;
        } else if(number == 1 || number == 0) {
            return 1;
        } else {
            return number * recursion(number - 1);
        }
    }
}