package com.keytool;

public class ExportCertFormKeystore {

	public void execCommand(String[] arstringCommand) {
		for (int i = 0; i < arstringCommand.length; i++) {
			System.out.print(arstringCommand[i] + " ");
		}
		try {
			Runtime.getRuntime().exec(arstringCommand);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void execCommand(String arstringCommand) {
		try {
			Runtime.getRuntime().exec(arstringCommand);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 生成密钥
	 * @param validity 指定证书有效期(单位：天)，默认36000天
	 * @param keysize 指定密钥长度，默认1024
	 * @param alias 指定别名，默认itanken
	 * @param keyalg 指定密钥的算法，默认RSA，如 RSA、DSA（如果不指定默认采用DSA）
	 * @param keystoreUrl 指定存储位置，默认C:/itanken.keystore
	 * @param storepass 指定密钥库的密码(获取keystore信息所需的密码)
	 * @param keypass 显示密钥库中的证书详细信息
	 */
	public void genkey(String validity, String keysize, String alias, String keyalg, String keystoreUrl,
			String storepass, String keypass) {
		String[] arstringCommand = new String[] {

		"cmd ", "/k",
				"start", // cmd Shell命令
				"keytool",
				"-genkey", // -genkey表示生成密钥
				"-validity", // -validity 指定证书有效期
				isEmptyStr(validity) ? "3600" : validity,
				"-keysize",// 指定密钥长度
				isEmptyStr(keysize) ? "1024" : keysize,
				"-alias", // -alias 指定别名
				isEmptyStr(alias) ? "itanken" : alias,
				"-keyalg", // -keyalg 指定密钥的算法 (如 RSA DSA（如果不指定默认采用DSA）)
				isEmptyStr(keyalg) ? "RSA" : keyalg,
				"-keystore", // -keystore指定存储位置
				isEmptyStr(keyalg) ? "C:/itanken.keystore" : keystoreUrl,
				"-dname",// CN=(名字与姓氏), OU=(组织单位名称), O=(组织名称), L=(城市或区域名称),
							// ST=(州或省份名称), C=(单位的两字母国家代码)"
				"CN=(iZ284bmqi1fZ), OU=(iTanken), O=(StarSevenSky), L=(ChiFeng), ST=(NeiMengGu), C=(CN)",
				"-storepass", // 指定密钥库的密码(获取keystore信息所需的密码)
				isEmptyStr(storepass) ? "itanken" : storepass, 
				"-keypass",// 指定别名条目的密码(私钥的密码)
				isEmptyStr(keypass) ? "itanken" : keypass, 
				"-v"// -v 显示密钥库中的证书详细信息
		};
		execCommand(arstringCommand);
	}

	/**
	 * 导出证书文件
	 * @param keystoreUrl 指定keystore文件路径，默认C:/itanken.keystore
	 * @param alias 指定别名，默认itanken
	 * @param cerUrl 指定导出路径，默认 C:/itanken.cer
	 * @param storepass 指定密钥库的密码
	 */
	public void export(String keystoreUrl, String alias, String cerUrl, String storepass) {
		String[] arstringCommand = new String[] {
		"cmd ", "/k",
				"start", // cmd Shell命令
				"keytool",
				"-export", // - export指定为导出操作 
				"-keystore", // -keystore指定keystore文件
				isEmptyStr(keystoreUrl) ? "C:/itanken.keystore" : keystoreUrl,
				"-alias", // -alias指定别名
				isEmptyStr(alias) ? "itanken" : alias,
				"-file",//-file指定导出路径
				isEmptyStr(cerUrl) ? " C:/itanken.cer" : cerUrl,
				"-storepass",// 指定密钥库的密码
				isEmptyStr(storepass) ? "itanken" : storepass
		};
		execCommand(arstringCommand);
	}
	
	public static void main(String[] args) {
		new ExportCertFormKeystore().genkey("36600", "1024", "itanken", "RSA", "D:/Development/Test/itanken.keystore", "", "");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new ExportCertFormKeystore().export("D:/Development/Test/itanken.keystore", "itanken", "D:/Development/Test/itanken.cer", "");
	}
	
	/**
	 * 检查字符串是否为空： null/"null"/""
	 * @param str
	 * @return boolean
	 */
	public final static boolean isEmptyStr(String str) {
		return ((str == null || str.trim().equalsIgnoreCase("null") || str.trim().equals("")) ? true : false);
	}
}
