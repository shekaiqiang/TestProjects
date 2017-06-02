package com.keytool;

import org.junit.Test;

public class ExportCertFormKeystoreTest {

	@Test
	public void genkeyTest() {
		//生成密钥测试
		new ExportCertFormKeystore().genkey("36600", "1024", "itanken", "RSA", "D:/Development/Test/itanken.keystore", "", "");;
	}
	@Test
	public void exportTest() {
		//导出证书文件测试
		new ExportCertFormKeystore().export("D:/Development/Test/itanken.keystore", "itanken", "D:/Development/Test/itanken.cer", "");
	}
}