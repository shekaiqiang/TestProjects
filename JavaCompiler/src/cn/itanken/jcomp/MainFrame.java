package cn.itanken.jcomp;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import cn.itanken.jcomp.utils.CloseUtil;
import cn.itanken.jcomp.utils.ScrollBarUI;
import cn.itanken.jcomp.utils.StrUtils;

/**
 * 简易Java编译工具
 * @author Tianqi Liu
 * @version 2017年1月13日
 */
public class MainFrame extends JFrame implements ActionListener, Runnable {

	private static final long serialVersionUID = 1L;
	
	public MainFrame mFrame = this;
	
	Thread compiler = null; // 编译线程
	Thread runProm = null; // 运行线程
	boolean flag = true;
	CardLayout mycard; // 布局
	File fileSaved = null;
	// 按钮
	JButton btnInputTxt,
			btnCompilerText,
			btnCompiler,
			btnRunProm,
			btnSeeDoswin;
	
	JPanel panIO = new JPanel(); // IO区
	JTextArea inputText = new JTextArea(); // 程序输入区
	JTextArea compilerText = new JTextArea(); // 编译错误显示区
	JTextArea dosOutText = new JTextArea(); // 程序的输出信息
	
	JTextArea inputFileNameText = new JTextArea(); 
	JTextArea runFileNameText = new JTextArea();
	
	public MainFrame() {
		// TODO Auto-generated constructor stub
		super("简易Java编译工具");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		
		mycard = new CardLayout();
		compiler = new Thread(this);
		runProm = new Thread(this);
		
		btnInputTxt = new JButton("程序输入区（灰）");
		btnCompilerText = new JButton("编译结果区（粉）");
		btnSeeDoswin = new JButton("程序运行结果（蓝）");
		btnCompiler = new JButton("编译程序"); 
		btnRunProm = new JButton("运行程序");
		
		JPanel panOperation = new JPanel(); // 操作区
		panOperation.setLayout(new GridLayout(3, 3));
		// 添加组件
		panOperation.add(btnInputTxt);
		panOperation.add(btnCompilerText);
		panOperation.add(btnSeeDoswin);
		panOperation.add(new Label(" 输入编译文件名（.java）:"));
		// inputFileNameText.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 0));
		panOperation.add(inputFileNameText);
		panOperation.add(btnCompiler);
		panOperation.add(new Label(" 输入应用程序主类名:"));
		panOperation.add(runFileNameText);
		panOperation.add(btnRunProm);
		add(panOperation, "North");
		// 定义事件
		btnInputTxt.addActionListener(this);
		btnCompiler.addActionListener(this);
		btnCompilerText.addActionListener(this);
		btnSeeDoswin.addActionListener(this);
		btnRunProm.addActionListener(this);
		
		panIO.setLayout(mycard); // 将面板设置为卡片布局
		JScrollPane scrollPane1 = new JScrollPane(inputText); // 内容超出时显示滚动条
		scrollPane1.getVerticalScrollBar().setUI(new ScrollBarUI());
		panIO.add("input", scrollPane1); // 定义卡片名称
		JScrollPane scrollPane2 = new JScrollPane(compilerText);
		scrollPane2.getVerticalScrollBar().setUI(new ScrollBarUI());
		panIO.add("compiler", scrollPane2); 
		JScrollPane scrollPane3 = new JScrollPane(dosOutText);
		scrollPane3.getVerticalScrollBar().setUI(new ScrollBarUI());
		scrollPane3.getHorizontalScrollBar().setUI(new ScrollBarUI());
		// scrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 隐藏水平滚动条
		panIO.add("dos", scrollPane3);
		add(panIO, "Center"); // 将操作区面板添加到框架
		// 设置背景颜色
		inputText.setBackground(new Color(250, 250, 250));
		compilerText.setBackground(new Color(255, 204, 204));
		dosOutText.setBackground(new Color(204, 255, 255));

		setMinimumSize(new Dimension(470,230)); // 设置窗口最小宽高
		setBounds(50, 100, 650, 430); // 设置窗口初始位置及大小
		setVisible(true);
		// pack(); // 调整窗口
		setDefaultCloseOperation(0); // 取消默认关闭
		// 监听窗口关闭事件
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				CloseUtil.exit(true, mFrame, true, "是否关闭编译器？");
			}
		});
	}

	public void run() {
		// TODO 线程
		if(Thread.currentThread() == compiler) {
			compilerText.setText(null);
			compilerText.append("开始编译，请稍后..." + StrUtils.LINE_SEPAR);
			String temp = inputText.getText().trim(); // 获取代码
			byte[] buffer = temp.getBytes();
			int b = buffer.length;
			String fileName = inputFileNameText.getText().trim();
			
			try {
				fileSaved = new File(fileName);
				FileOutputStream writeFile = new FileOutputStream(fileSaved);
				writeFile.write(buffer, 0, b);
				writeFile.close();
			} catch (Exception e) {
				System.out.println("ERROR!");
				compilerText.append("编译异常：" + StrUtils.LINE_SEPAR + e);
			}
			try {
				// 获得该进程的错误流，才可以知道运行结果到底是失败了还是成功。
				Runtime rt = Runtime.getRuntime();
				// 通过 Runtime 调用 javac 命令编译 .java 文件。注意：“javac ”这个字符串是有一个空格的！
				InputStream in = rt.exec("javac " + fileName).getErrorStream();
				BufferedInputStream bufIn = new BufferedInputStream(in);
				byte[] arr = new byte[100];
				int n = 0;
				boolean flag = true;
				// 输入错误信息
				while((n = bufIn.read(arr, 0, arr.length)) != -1) {
					String s = new String(arr, 0, n);
					compilerText.append(s);
					if(s != null) {
						flag = false;
					}
				}
				// 判断是否编译成功
				if(flag) {
					compilerText.append(StrUtils.LINE_SEPAR + "编译完成！");
				}
			} catch (Exception e1) {
				compilerText.append("编译异常：" + StrUtils.LINE_SEPAR + e1);
			}
			// this.pack();
		} else if(Thread.currentThread() == runProm) {
			// 运行文件，并将结果输出到 dosOutText
			dosOutText.setText(null);
			dosOutText.append("开始运行：" + StrUtils.LINE_SEPAR);
			try {
				Runtime rt = Runtime.getRuntime();
				String path = runFileNameText.getText().trim();
				dosOutText.append("类名：" + path + StrUtils.LINE_SEPAR);
				Process stream = rt.exec("java " + path); // 调用 Java 命令执行程序
				InputStream in = stream.getInputStream();
				BufferedInputStream bisErr = new BufferedInputStream(stream.getErrorStream()); 
				BufferedInputStream bisIn = new BufferedInputStream(in);
				
				byte[] inbuf = new byte[150];
				byte[] errBuf = new byte[150];
				
				int m = 0;
				int i = 0;
				String inStr = null;
				String errStr = null;
				// 打印编译信息及错误信息
				while((m = bisIn.read(inbuf, 0, 150)) != -1) {
					inStr = new String(inbuf, 0, m);
					dosOutText.append(inStr);
				}
				while((i = bisErr.read(errBuf, 0, 150)) != -1) {
					errStr = new String(errBuf, 0, i);
					dosOutText.append(errStr);
				}
			} catch (Exception e) {
				compilerText.append("运行异常：" + StrUtils.LINE_SEPAR + e);
			}
			dosOutText.append(StrUtils.LINE_SEPAR + "运行结束。");
			// this.pack();
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO 按钮动作
		if(e.getSource() == btnInputTxt) {
			mycard.show(panIO, "input"); // 显示输入区
		} else if(e.getSource() == btnCompilerText) {
			mycard.show(panIO, "compiler"); // 显示编译区
		} else if(e.getSource() == btnSeeDoswin) {
			mycard.show(panIO, "dos"); // 显示结果区
		} else if(e.getSource() == btnCompiler) {
			if(!compiler.isAlive()) { // 如果编译线程执行结束
				compiler = new Thread(this); // 创建一个新的编译线程
			}
			try {
				compiler.start();
			} catch (Exception e1) {
				e1.printStackTrace(); // 启动编译线程异常
			}
			mycard.show(panIO, "compiler"); // 显示编译区
		} else if(e.getSource() == btnRunProm) {
			if(!runProm.isAlive()) { // 如果运行线程执行结束
				runProm = new Thread(this); // 创建一个新的运行线程
			}
			try {
				runProm.start();
			} catch (Exception e1) {
				e1.printStackTrace(); // 启动运行线程异常
			}
			mycard.show(panIO, "dos"); // 显示结果区
		}
	}

	public static void main(String[] args) {
		new MainFrame();
	}
}
