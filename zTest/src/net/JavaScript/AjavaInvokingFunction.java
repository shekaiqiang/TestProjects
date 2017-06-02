package net.JavaScript;

import javax.script.Invocable;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import java.io.File;
import java.io.Reader;
import java.io.FileReader;

/**
 * Java调用JS脚本里的方法
 * @author Tanken·L
 * @Start 2016年4月4日
 * @Done  2016年4月4日
 */
public class AjavaInvokingFunction {
	public static void main(String[] args) {
		// 获得一个JavaScript脚本引擎，也可以是ECMAScript脚本引擎
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		String titlescript = "function sayTitle() {"
				+ " println('使用javax.script调用JS脚本里的方法');"
				+ " println('--------------------------------------------------');"
				+ "}";
		try {
			// 调用内部脚本执行-----------------------------------------
			engine.eval(titlescript);
			// 转换为Invocable
			Invocable invocableEngine = (Invocable) engine;
			// 不带参数调用sayTitle方法
			invocableEngine.invokeFunction("sayTitle");
			// 调用外部脚本执行------------------------------------------
			// 创建JS文件的File对象，并读入流
			File functionscript = new File(AjavaInvokingFunction.class.getResource("ajava.js").getFile());
			Reader reader = new FileReader(functionscript);
			// 开始执行ajava.js里的程序
			engine.eval(reader);
			// 不带参数调用sayHello方法
			invocableEngine.invokeFunction("sayHello");
			// 带参数调用sayHello方法
			invocableEngine.invokeFunction("sayHello", "mark");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}