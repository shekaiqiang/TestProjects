package net.Regex;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.regex.*;

@SuppressWarnings("rawtypes")
public class RegexTest extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JLabel jlTestRegex; // 标签 测试表达式
	private JLabel jlTestStr; // 标签 测试字符串
	private JLabel jlMethod; // 标签 方法
	private JLabel jlBeReplaced; // 标签 目的表达式
	private JLabel jlResult; // 标签 目的结果
	private JLabel jlProperties; // 卷标 属性
	private JLabel jlStart; // 标签 开始
	private JLabel jlEnd; // 标签 结束
	private JLabel jlGroupCount; // 标签 组数
	private JLabel jlGroup; // 标签 组
	private JTextField jtfTestRegex; // 输入框 要测试的表达式
	private JTextField jtfTestStr; // 输入框 要测试的字符串
	private JTextField jtfBeReplaced; // 输入框 目的表达式
	private JTextArea jtaResult; // 输入框 目的结果
	private JTextField jtfStart; // 输入框 开始
	private JTextField jtfEnd; // 输入框 结束
	private JTextField jtfGroupCount; // 输入框 组数
	private JTextArea jtaGroup; // 输入框 组
    private JComboBox jcbMethod; // 列表框 方法
	private JButton jbRun; // 按钮 进行测试
	private JScrollPane jspResult; // 存放 TextArea的Pane, 加滚动条
	private JScrollPane jspGroup; // 存放 TextArea的Pane, 加滚动条
	
	int i = 0; // 循环控制变量
	String method = ""; // 字符串 保存方法字符串
	boolean bRs = false; // 布尔类型的结果
	private Pattern p; // 表达式 模板
	private Matcher m; // 表达式 匹配器

	@SuppressWarnings("unchecked")
    public RegexTest() {
		super("Regex测试·iTanken"); // 程序 标题
		setResizable(false); // 不允许改变窗口大小
		// get container
		Container c = getContentPane();
		c.setLayout(null);

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
		jlBeReplaced.setText("被　替　换：");
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
		jlProperties.setLocation(20, 300);
		jlProperties.setText("属　性：");
		jlStart = new SelJLabel();
		c.add(jlStart);
		jlStart.setSize(60, 25);
		jlStart.setLocation(130, 300);
		jlStart.setText("开　始：");
		jlEnd = new SelJLabel();
		c.add(jlEnd);
		jlEnd.setSize(60, 25);
		jlEnd.setLocation(130, 340);
		jlEnd.setText("结　束：");
		jlGroupCount = new SelJLabel();
		c.add(jlGroupCount);
		jlGroupCount.setSize(60, 25);
		jlGroupCount.setLocation(130, 380);
		jlGroupCount.setText("组　数：");
		jlGroup = new SelJLabel();
		c.add(jlGroup);
		jlGroup.setSize(60, 25);
		jlGroup.setLocation(130, 420);
		jlGroup.setText("集　合：");

		// new ui control
		jtfTestRegex = new JTextField();
		c.add(jtfTestRegex);
		jtfTestRegex.setSize(300, 25);
		jtfTestRegex.setLocation(130, 20);
		jtfTestStr = new JTextField();
		c.add(jtfTestStr);
		jtfTestStr.setSize(300, 25);
		jtfTestStr.setLocation(130, 60);
		jtfBeReplaced = new JTextField();
		c.add(jtfBeReplaced);
		jtfBeReplaced.setSize(300, 25);
		jtfBeReplaced.setLocation(130, 100);

		jcbMethod = new SelJComboBox();
		c.add(jcbMethod);
		jcbMethod.setSize(200, 25);
		jcbMethod.setLocation(130, 140);
		jbRun = new SelJButton("运行");
		c.add(jbRun);
		jbRun.setSize(60, 25);
		jbRun.setLocation(370, 140);

		jtaResult = new JTextArea();
		jspResult = new JScrollPane(jtaResult);
		c.add(jspResult);
		jspResult.setSize(300, 100);
		jspResult.setLocation(130, 180);

		jtfStart = new JTextField();
		c.add(jtfStart);
		jtfStart.setSize(240, 25);
		jtfStart.setLocation(190, 300);
		jtfEnd = new JTextField();
		c.add(jtfEnd);
		jtfEnd.setSize(240, 25);
		jtfEnd.setLocation(190, 340);
		jtfGroupCount = new JTextField();
		c.add(jtfGroupCount);
		jtfGroupCount.setSize(240, 25);
		jtfGroupCount.setLocation(190, 380);

		jtaGroup = new JTextArea();
		jspGroup = new JScrollPane(jtaGroup);
		c.add(jspGroup);
		jspGroup.setSize(240, 100);
		jspGroup.setLocation(190, 420);

		// set editable
		jtfBeReplaced.setEditable(false);
		jtaResult.setEditable(false);
		jtfStart.setEditable(false);
		jtfEnd.setEditable(false);
		jtfGroupCount.setEditable(false);
		jtaGroup.setEditable(false);

		// add item
		jcbMethod.addItem("find");
		jcbMethod.addItem("split");
		jcbMethod.addItem("matches");
		jcbMethod.addItem("lookingAt");
		jcbMethod.addItem("replaceAll");
		jcbMethod.addItem("replaceFirst");

		jcbMethod.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				method = (String) jcbMethod.getSelectedItem();
				if (method.equals("replaceFirst") || method.equals("replaceAll")) {
					jtfBeReplaced.setEditable(true);
				} else {
					jtfBeReplaced.setEditable(false);
				}
			}
		});

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

				method = (String) jcbMethod.getSelectedItem();

				if (method.equals("find")) {
					bRs = m.find();
					jtaResult.setText(Boolean.toString(bRs));
				} else if (method.equals("split")) {
					String[] list = p.split(jtfTestStr.getText());
					if (list != null) {
						for (i = 0; i < list.length; i++) {
							jtaResult.append(list[i] + "\n");
						}
					}
				} else if (method.equals("matches")) {
					bRs = m.matches();
					jtaResult.setText(Boolean.toString(bRs));
				} else if (method.equals("lookingAt")) {
					bRs = m.lookingAt();
					jtaResult.setText(Boolean.toString(bRs));
				} else if (method.equals("replaceAll")) {
					jtaResult.setText(m.replaceAll(jtfBeReplaced.getText()));
				} else if (method.equals("replaceFirst")) {
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
		setLocation(136, 64); // 设置窗口位置
		setSize(460, 566); // 设置窗口大小
		setVisible(true); // 显示窗口
	}

	public static void main(String args[]) throws Exception {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		RegexTest app = new RegexTest();
		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}

class SelJLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	public SelJLabel() {
		super.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
	}
}

class SelJButton extends JButton {
	private static final long serialVersionUID = 1L;
	public SelJButton(String name) {
		super.setText(name);
		super.setFont(new Font("Microsoft Yahei", Font.PLAIN, 12));
		// super.setContentAreaFilled(false);
	}
}

@SuppressWarnings("rawtypes")
class SelJComboBox extends JComboBox {
	private static final long serialVersionUID = 1L;
	public SelJComboBox() {
		super.setFont(new Font("Microsoft Yahei", Font.PLAIN, 12));
	}
}
