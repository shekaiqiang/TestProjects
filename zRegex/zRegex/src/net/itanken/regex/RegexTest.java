package net.itanken.regex;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import net.itanken.regex.utils.ConsoleDialog;
import net.itanken.regex.utils.StrUtils;

import java.net.URI;
import java.util.regex.*;

/**
 * 正则表达式测试工具
 * @author Tanken·L
 * @Start 2016年04月04日
 * @Done  2016年05月20日
 * @Revis 2016年05月25日<br>添加罗马数字Int互转
 */
public class RegexTest extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final String title = "[TestTools]Regex · iTanken";
	public static RegexTest rTest = new RegexTest("");
    public static ConsoleDialog console = new ConsoleDialog(title, false);
	/**
	 * 标签 测试表达式
	 */
	private JLabel jlTestRegex; 
	/**
	 * 标签 测试字符串
	 */
	private JLabel jlTestStr;
	/**
	 * 标签 方法
	 */
	private JLabel jlMethod; 
	/**
	 * 标签 目的表达式
	 */
	private JLabel jlBeReplaced; 
	/**
	 * 标签 目的结果
	 */
	private JLabel jlResult; 
	/**
	 * 卷标 属性
	 */
	private JLabel jlProperties; 
	/**
	 * 标签 开始
	 */
	private JLabel jlStart; 
	/**
	 * 标签 结束
	 */
	private JLabel jlEnd; 
	/**
	 * 标签 组数
	 */
	private JLabel jlGroupCount; 
	/**
	 * 标签 组
	 */
	private JLabel jlGroup; 
	/**
	 * 标签 iTankenUrl
	 */
	private JLabel jlTankenUri; 
	/**
	 * 标签 RTI Title
	 */
	public JLabel jlRomanInt; 
	/**
	 * 标签 Int
	 */
	public JLabel jlInt; 
	/**
	 * 标签 Roman
	 */
	public JLabel jlRoman; 
	/**
	 * 输入框 要测试的表达式
	 */
	private JTextField jtfTestRegex; 
	/**
	 * 输入框 要测试的字符串
	 */
	private JTextField jtfTestStr; 
	/**
	 * 输入框 目的表达式
	 */
	private JTextField jtfBeReplaced;
	/**
	 * 输入框 目的结果
	 */
	private JTextArea jtaResult; 
	/**
	 * 输入框 开始
	 */
	private JTextField jtfStart; 
	/**
	 * 输入框 结束
	 */
	private JTextField jtfEnd; 
	/**
	 * 输入框 组数
	 */
	private JTextField jtfGroupCount; 
	/**
	 * 输入框 组
	 */
	private JTextArea jtaGroup; 
	/**
	 * 列表框 方法
	 */
	private JComboBox jcbMethod; 
	/**
	 * RTI.T
	 */
	public JTextField jtfRomanInt; 
	/**
	 * RTI.B
	 */
	public JTextField jtfIntRoman; 
	/**
	 * 按钮 进行测试
	 */
	private JButton jbRun; 
	/**
	 * 按钮 Roman&Int转换
	 */
	public JButton jbRomanInt; 
	/**
	 * 存放 TextArea的Pane, 加滚动条
	 */
	private JScrollPane jspResult; 
	/**
	 * 存放 TextArea的Pane, 加滚动条
	 */
	private JScrollPane jspGroup; 
	/**
	 * 循环控制变量
	 */
	int i = 0; 
	/**
	 * 保存方法字符串
	 */
	String method = ""; 
	/**
	 * 布尔型结果
	 */
	boolean bRs = false; 
	/**
	 * 表达式 模式
	 */
	private Pattern p; 
	/**
	 * 表达式 匹配器
	 */
	private Matcher m; 
	/**
	 * get container 容器
	 */
	private Container c = getContentPane();

	public RegexTest(String sTit) {
		super("".equals(sTit) ? title : sTit); // 程序 标题
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { console.showError("样式设置失败：" + e.getMessage()); }
		super.setResizable(false); // 不允许改变窗口大小
		c.setLayout(null);
		// 公用颜色
		Color pubColor = new Color(240, 240, 240); 

		jlTestRegex = new SelJLabel();
		c.add(jlTestRegex);
		jlTestRegex.setSize(100, 25);
		jlTestRegex.setLocation(20, 20);
		jlTestRegex.setText("正则表达式：");
		jlTestStr = new SelJLabel();
		c.add(jlTestStr);
		jlTestStr.setSize(100, 25);
		jlTestStr.setLocation(20, 60);
		jlTestStr.setText("字　符　串：");
		jlBeReplaced = new SelJLabel();
		c.add(jlBeReplaced);
		jlBeReplaced.setSize(100, 25);
		jlBeReplaced.setLocation(20, 100);
		jlBeReplaced.setText("替换为字符：");
		
		jlMethod = new SelJLabel();
		c.add(jlMethod);
		jlMethod.setSize(100, 25);
		jlMethod.setLocation(20, 140);
		jlMethod.setText("方　法：");
		
		jlResult = new SelJLabel();
		c.add(jlResult);
		jlResult.setSize(100, 25);
		jlResult.setLocation(20, 180);
		jlResult.setText("结　果：");
		
		jlProperties = new SelJLabel();
		c.add(jlProperties);
		jlProperties.setSize(100, 25);
		jlProperties.setLocation(20, 280);
		jlProperties.setText("属　性：");
		
		jlStart = new SelJLabel();
		c.add(jlStart);
		jlStart.setSize(60, 25);
		jlStart.setLocation(130, 280);
		jlStart.setText("开　始：");
		
		jlEnd = new SelJLabel();
		c.add(jlEnd);
		jlEnd.setSize(60, 25);
		jlEnd.setLocation(130, 320);
		jlEnd.setText("结　束：");
		
		jlGroupCount = new SelJLabel();
		c.add(jlGroupCount);
		jlGroupCount.setSize(60, 25);
		jlGroupCount.setLocation(130, 360);
		jlGroupCount.setText("组　数：");
		
		jlGroup = new SelJLabel();
		c.add(jlGroup);
		jlGroup.setSize(60, 25);
		jlGroup.setLocation(130, 400);
		jlGroup.setText("　　组：");
		// RomanToInt Title
		jlRomanInt = new SelJLabel();
		c.add(jlRomanInt);
		jlRomanInt.setSize(160, 25);
		jlRomanInt.setLocation(10, 430);
		jlRomanInt.setText("1~3999 罗马数字转换：");
		jlRomanInt.setToolTipText("点击图标后可用");
		jlRomanInt.setForeground(Color.gray);
		jlInt = new SelJLabel();
		c.add(jlInt);
		jlInt.setSize(50, 25);
		jlInt.setLocation(10, 460);
		jlInt.setText("内容：");
		jlInt.setForeground(Color.gray);
		// INT
		jtfRomanInt = new JTextField();
		c.add(jtfRomanInt);
		jtfRomanInt.setSize(86, 25);
		jtfRomanInt.setLocation(50, 460);
		jlRoman = new SelJLabel();
		c.add(jlRoman);
		jlRoman.setSize(50, 25);
		jlRoman.setLocation(10, 495);
		jlRoman.setText("结果：");
		jlRoman.setForeground(Color.gray); 
		// ROMAN
		jtfIntRoman = new JTextField(); 
		c.add(jtfIntRoman);
		jtfIntRoman.setSize(86, 25);
		jtfIntRoman.setLocation(50, 495);
		// 转换按钮
		jbRomanInt = new SelJButton("转", 6);
		c.add(jbRomanInt);
		jbRomanInt.setSize(30, 60);
		jbRomanInt.setLocation(146, 460);
		// ICON 
		jlTankenUri = new iTankenJLabel();
		c.add(jlTankenUri);
		jlTankenUri.setSize(100, 100);
		jlTankenUri.setLocation(10, 316);
		// 正则表达式
		jtfTestRegex = new JTextField(); 
		c.add(jtfTestRegex);
		jtfTestRegex.setSize(300, 25);
		jtfTestRegex.setLocation(130, 20);
		// 字符串
		jtfTestStr = new JTextField();
		c.add(jtfTestStr);
		jtfTestStr.setSize(300, 25);
		jtfTestStr.setLocation(130, 60);
		// 被替换字符串
		jtfBeReplaced = new JTextField();
		c.add(jtfBeReplaced);
		jtfBeReplaced.setSize(300, 25);
		jtfBeReplaced.setLocation(130, 100);
		// 方法
		jcbMethod = new SelJComboBox();
		c.add(jcbMethod);
		jcbMethod.setSize(205, 25);
		jcbMethod.setLocation(130, 140);
		jbRun = new SelJButton("执行");
		c.add(jbRun);
		jbRun.setSize(75, 25);
		jbRun.setLocation(355, 140);
		// 结果
		jtaResult = new JTextArea();
		jtaResult.setBackground(pubColor);
		jspResult = new JScrollPane(jtaResult);
		c.add(jspResult);
		jspResult.setSize(300, 80);
		jspResult.setLocation(130, 180);
		// 开始
		jtfStart = new JTextField();
		c.add(jtfStart);
		jtfStart.setSize(240, 25);
		jtfStart.setLocation(190, 280);
		// 结束
		jtfEnd = new JTextField();
		c.add(jtfEnd);
		jtfEnd.setSize(240, 25);
		jtfEnd.setLocation(190, 320);
		//组数
		jtfGroupCount = new JTextField();
		c.add(jtfGroupCount);
		jtfGroupCount.setSize(240, 25);
		jtfGroupCount.setLocation(190, 360);
		// 组
		jtaGroup = new JTextArea();
		jtaGroup.setBackground(pubColor);
		jspGroup = new JScrollPane(jtaGroup);
		c.add(jspGroup);
		jspGroup.setSize(240, 120);
		jspGroup.setLocation(190, 400);
		// 设置可用性
		jtfBeReplaced.setEditable(false);
		jtaResult.setEditable(false);
		jtfStart.setEditable(false);
		jtfEnd.setEditable(false);
		jtfGroupCount.setEditable(false);
		jtaGroup.setEditable(false);
		jtfRomanInt.setEditable(false);
		jtfIntRoman.setEditable(false);
		jbRomanInt.setEnabled(false);

		// set cursor type
		addMouLis(jcbMethod, Cursor.HAND_CURSOR);
		addMouLis(jbRun, Cursor.HAND_CURSOR);
		addMouLis(jtaResult, Cursor.TEXT_CURSOR);
		addMouLis(jtfStart, Cursor.TEXT_CURSOR);
		addMouLis(jtfEnd, Cursor.TEXT_CURSOR);
		addMouLis(jtfGroupCount, Cursor.TEXT_CURSOR);
		addMouLis(jtaGroup, Cursor.TEXT_CURSOR);
		addMouLis(jtfRomanInt, Cursor.TEXT_CURSOR);
		addMouLis(jtfIntRoman, Cursor.TEXT_CURSOR);
		addMouLis(jbRomanInt, Cursor.HAND_CURSOR);
		
		// add item
		jcbMethod.addItem("find - 查找");
		jcbMethod.addItem("split - 分组");
		jcbMethod.addItem("matches - 匹配整体");
		jcbMethod.addItem("lookingAt - 匹配开头");
		jcbMethod.addItem("replaceAll - 替换全部");
		jcbMethod.addItem("replaceFirst - 替换首项");

		jcbMethod.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				method = (String) jcbMethod.getSelectedItem();
				if (method.equals("replaceFirst - 替换首项") || method.equals("replaceAll - 替换全部")) {
					jtfBeReplaced.setEditable(true); 
				} else {
					jtfBeReplaced.setEditable(false);
					jtfBeReplaced.setText("");
				}
			}
		});
		// 按钮动作监听 
		jbRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bRs = false;
				jtaResult.setText("");
				jtfStart.setText("");
				jtfEnd.setText("");
				jtfGroupCount.setText("");
				jtaGroup.setText("");

				p = Pattern.compile(jtfTestRegex.getText());
				m = p.matcher(jtfTestStr.getText());
				console.showDebug("表达式：" + jtfTestRegex.getText());
				console.showDebug("字符串：" + jtfTestStr.getText());

				method = (String) jcbMethod.getSelectedItem();
				console.showDebug("方　法：" + method);

				if (method.equals("find - 查找")) {
					bRs = m.find();
					jtaResult.setText(Boolean.toString(bRs));
				} else if (method.equals("split - 分组")) {
					String[] list = p.split(jtfTestStr.getText());
					if (list != null) {
						for (i = 0; i < list.length; i++) {
							jtaResult.append(list[i] + "\n");
						}
					}
				} else if (method.equals("matches - 匹配整体")) {
					bRs = m.matches();
					jtaResult.setText(Boolean.toString(bRs));
				} else if (method.equals("lookingAt - 匹配开头")) {
					bRs = m.lookingAt();
					jtaResult.setText(Boolean.toString(bRs));
				} else if (method.equals("replaceAll - 替换全部")) {
					console.showDebug("替换为：" + jtfBeReplaced.getText());
					jtaResult.setText(m.replaceAll(jtfBeReplaced.getText()));
				} else if (method.equals("replaceFirst - 替换首项")) {
					console.showDebug("替换为：" + jtfBeReplaced.getText());
					jtaResult.setText(m.replaceFirst(jtfBeReplaced.getText()));
				}

				if (bRs) {
					jtfStart.setText(Integer.toString(m.start()));
					jtfEnd.setText(Integer.toString(m.end()));
					jtfGroupCount.setText(Integer.toString(m.groupCount()));
					for (i = 0; i <= m.groupCount(); i++) {
						jtaGroup.append(m.group(i) + "\n");
					}
				}
			}
		});
		// 转换按钮动作
		jbRomanInt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sContent = jtfRomanInt.getText();
				if(StrUtils.isEmpty(sContent) || !StrUtils.isExists(sContent.trim(), "IVXLCDM") && !StrUtils.isInt(sContent.trim()) || (StrUtils.isInt(sContent.trim()) && (Integer.parseInt(sContent.trim()) < 1 || Integer.parseInt(sContent.trim()) > 3999))) {
					JOptionPane.showMessageDialog(c, "只能输入1~3999的整数或罗马数字！", " iTanken·Regex提示", JOptionPane.ERROR_MESSAGE, new ImageIcon(RegexTest.class.getResource("res/logo.png")));
					return;
				}
				String sResult = "";
				if(StrUtils.isInt(sContent.trim())) { // 整数转罗马数字
					sResult = Ri.intToRoman(Integer.parseInt(sContent.trim()));
				} else { // 罗马数字转整数
					sResult = String.valueOf(Ri.romanToInt(sContent.trim())); 
				}
				jtfIntRoman.setText(sResult);
			}
		});
		super.setIconImage(new ImageIcon(RegexTest.class.getResource("res/logo.png")).getImage());
		// super.setLocation(136, 64); // 设置窗口位置
		// super.setSize(460, 566); // 设置窗口大小
		super.setBounds(136, 64, 460, 566);
		super.setVisible(true); // 显示窗口
		super.setDefaultCloseOperation(0); // 取消默认关闭
		super.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitWindow(e);
			}
		});
		KeyboardFocusManager keyManager = KeyboardFocusManager.getCurrentKeyboardFocusManager(); //得到当前键盘事件的管理器
		keyManager.addKeyEventPostProcessor(new KeyEventPostProcessor() {  
			public boolean postProcessKeyEvent(KeyEvent event) {
				if(event.getID() != KeyEvent.KEY_PRESSED) { // 按键按下事件
					return false;  
				}
				console.showLog("KeyCode：" + event.getKeyCode());
				if(event.isControlDown() && event.getKeyCode() == KeyEvent.VK_F12) { // 按下 Ctrl + F12
					if(console.dialogState) {
						if(console.close(1)) {
							console.dialogState = false;
						}
					} else {
            		   console = new ConsoleDialog(title);
            		   console.showLog("按下‘Ctrl + F12’快速打开关闭调试窗口");
					}
				}
				return true;  
			}  
		});
	}
	
	/**
	 * 鼠标效果
	 * @param comObj 控件对象
	 * @param type default/text/pointer/help...
	 */
	public void addMouLis(final Component comObj, final int type) {
		comObj.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent mouseEvent) {
            	comObj.setCursor(new Cursor(type)); //鼠标进入Text区后变为文本输入指针
            }
            public void mouseExited(MouseEvent mouseEvent) {
            	comObj.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //鼠标离开Text区后恢复默认形态
            }
        });/*
		comObj.getCaret().addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	comObj.getCaret().setVisible(true); //使Text区的文本光标显示
            }
        });*/
	}
    
	/**
	 * 关闭程序
	 * @param e
	 */
	public void exitWindow(WindowEvent e) {
		Icon img = new ImageIcon(RegexTest.class.getResource("res/wen"));
        int result = JOptionPane.showConfirmDialog(this, "是否关闭测试工具？", " iTanken·Regex提示", 
        	JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, img);
        if(result == JOptionPane.YES_OPTION) { 
        	System.exit(0);
        } else  if(result == JOptionPane.NO_OPTION)  { 
            setVisible(true); 
            validate(); 
        } 
    } 

	public static void main(String args[]) {
		RegexTest rt = rTest;
		if(rt != null) {
			console.showLog("欢迎使用iTanken正则表达式测试工具！更多信息请浏览 https://zixizixi.cn/");
		}
	}
}

/**
 * 链接
 * @author Tanken·L
 * @Start 2016年4月9日
 * @Done  2016年4月9日
 */
class iTankenJLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	private static RegexTest rt = null;

	public iTankenJLabel() {
		super.setIcon(new ImageIcon(RegexTest.class.getResource("res/i")));
		super.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
        		try {
        			URI uri = new URI("https://zixizixi.cn/");
					Desktop dtp = Desktop.getDesktop();
					if(Desktop.isDesktopSupported() && dtp.isSupported(Desktop.Action.BROWSE)){ // 判断是否支持
						dtp.browse(uri);
						if(rt == null) {
							rt = new RegexTest(RegexTest.title + " & RomanInt");
							rt.jtfRomanInt.setEditable(true);
							rt.jtfIntRoman.setEditable(true);
							rt.jbRomanInt.setEnabled(true);
							rt.jlRomanInt.setToolTipText(null);
							rt.jlRomanInt.setForeground(Color.black); 
							rt.jlRoman.setForeground(Color.black); 
							rt.jlInt.setForeground(Color.black); 
							RegexTest.rTest.setVisible(false); 
						}
					}
				} catch (Exception ex) { RegexTest.console.showError(" labelUriiTanken:不能打开浏览器 - "+ex); }
        	}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR)); // 设置鼠标按时
				setToolTipText("星柒天iTanken"); // 鼠标悬浮文字
        	}
		});
	}
}

/**
 * 自定义标签
 */
class SelJLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	public SelJLabel() {
		super.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
	}
}

/**
 * 自定义按钮
 */
class SelJButton extends JButton {
	private static final long serialVersionUID = 1L;
	public SelJButton(String name) {
		super.setText(name);
		super.setFont(new Font("Microsoft Yahei", Font.PLAIN, 12));
		// super.setContentAreaFilled(false);
	}

	public SelJButton(String name, int m) {
		super.setText(name);
		super.setFont(new Font("Microsoft Yahei", Font.PLAIN, 12));
		super.setMargin(new Insets(m, m, m, m));
		// super.setContentAreaFilled(false);
	}
}

/**
 * 自定义下拉框
 */
class SelJComboBox extends JComboBox {
	private static final long serialVersionUID = 1L;
	public SelJComboBox() {
		super.setFont(new Font("Microsoft Yahei", Font.PLAIN, 12));
	}
}
