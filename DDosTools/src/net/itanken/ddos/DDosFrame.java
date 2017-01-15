package net.itanken.ddos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Insets;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URI;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.itanken.ddos.utils.ConsoleDialog;
import net.itanken.ddos.utils.StrUtils;
import net.itanken.ddos.utils.ButtonRadio;
import net.itanken.ddos.utils.CloseUtil;

/**
 * DDos攻击测试工具
 * @author StarSevenSky
 * @author Tianqi Liu
 * @version 2016年11月26日
 */
public class DDosFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final String title = "[TestTools]DDos";
	public static JTextArea jtaInfo = new JTextArea();
	public static DDosFrame ddos = new DDosFrame("");
    public static ConsoleDialog console = new ConsoleDialog(title, false);
	private DDos dos = null;

	public DDosFrame(String sTit) {
		super("".equals(sTit) ? title : sTit); // 程序 标题
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { console.showError("样式设置失败：" + e.getMessage()); }
		super.setResizable(false); // 不允许改变窗口大小
		// get container 容器
		Container c = getContentPane();
		c.setLayout(null);

		// 请求地址
		JLabel jlTestRegex = new SelJLabel(); // 标题
		c.add(jlTestRegex);
		jlTestRegex.setSize(105, 25);
		jlTestRegex.setLocation(20, 20);
		jlTestRegex.setText("请　求　地　址:");
		final JTextField jtfTestRegex = new JTextField(); // 内容
		jtfTestRegex.setText("https://www.baidu.com/");
		c.add(jtfTestRegex);
		jtfTestRegex.setSize(300, 25);
		jtfTestRegex.setLocation(130, 20);
		
		// 请求间隔
		JLabel jlDelayStr = new SelJLabel();
		c.add(jlDelayStr);
		jlDelayStr.setSize(105, 25);
		jlDelayStr.setLocation(20, 60);
		jlDelayStr.setText("线　程　数　量:");
		final int defaultCount = 6;
        ButtonGroup reqDelayBG = new ButtonGroup();
        final ButtonRadio brDelDef = new ButtonRadio("默认" + defaultCount + "个");
        ButtonRadio brDelCus = new ButtonRadio("自定义");
        brDelDef.setSize(90, 20);
        brDelCus.setSize(80, 20);
        reqDelayBG.add(brDelDef);
        reqDelayBG.add(brDelCus);
        brDelDef.setSelected(true);
        c.add(brDelDef);
        c.add(brDelCus);
        brDelDef.setLocation(130, 62);
        brDelCus.setLocation(220, 62);
        
		final JTextField jtfTCounStr = new JTextField();
		c.add(jtfTCounStr);
		jtfTCounStr.setSize(130, 25);
		jtfTCounStr.setLocation(300, 60);
		jtfTCounStr.setEnabled(false);

		ChangeListener tCounListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//取出滑动条的值，并在文本中显示出来  
				JSlider source = (JSlider) e.getSource();  
				jtfTCounStr.setText("" + source.getValue());				
			}
		};
		final JSlider sliderTCoun = new JSlider(0, 20); // 范围为0~20
		sliderTCoun.setValue(defaultCount); // 初始值 
		//设置滑块必须停在刻度处  
		sliderTCoun.setSnapToTicks(true);  
		//设置绘制刻度  
		sliderTCoun.setPaintTicks(true);  
		//设置主、次刻度的间距  
		sliderTCoun.setMajorTickSpacing(2);
		sliderTCoun.setMinorTickSpacing(1); 
		//设置绘制刻度标签，默认绘制数值刻度标签  
		sliderTCoun.setPaintLabels(true);
		sliderTCoun.addChangeListener(tCounListener);
		c.add(sliderTCoun);
		sliderTCoun.setSize(200, 40);
		sliderTCoun.setLocation(220, 90);
		sliderTCoun.setEnabled(false);

		brDelDef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
            	DDosFrame.console.showLog("已选中 ‘默认’线程数量");
                // 使用默认
            	// jtfTCounStr.setEnabled(false);
                sliderTCoun.setEnabled(false);
            	jtfTCounStr.setText("" + defaultCount);
            }
        });
		brDelCus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
            	DDosFrame.console.showLog("已选中 ‘自定义’线程数量");
                // 获取自定义的值传递过去
            	// jtfTCounStr.setEnabled(true);
                sliderTCoun.setEnabled(true);
                jtfTCounStr.setText("" + sliderTCoun.getValue());
            }
        });
		
		// 请求次数
		JLabel jlTestStr = new SelJLabel();
		c.add(jlTestStr);
		jlTestStr.setSize(105, 25);
		jlTestStr.setLocation(20, 140);
		jlTestStr.setText("单线程请求次数:");

        ButtonGroup reqCountBG = new ButtonGroup();
        final ButtonRadio brDef = new ButtonRadio("默认一直");
        ButtonRadio brCus = new ButtonRadio("自定义");
        brDef.setSize(90, 20);
        brCus.setSize(80, 20);
        reqCountBG.add(brDef);
        reqCountBG.add(brCus);
        brDef.setSelected(true);
        c.add(brDef);
        c.add(brCus);
        brDef.setLocation(130, 142);
        brCus.setLocation(220, 142);
        
		final JTextField jtfTestStr = new JTextField();
		c.add(jtfTestStr);
		jtfTestStr.setSize(130, 25);
		jtfTestStr.setLocation(300, 140);
    	jtfTestStr.setEnabled(false);
    	 
    	ChangeListener countListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//取出滑动条的值，并在文本中显示出来  
                JSlider source = (JSlider) e.getSource();  
                jtfTestStr.setText("" + source.getValue());				
			}
        };
        final JSlider sliderCount = new JSlider(0, 10000); 
        sliderCount.setValue(500);
        //设置滑块必须停在刻度处  
        // sliderCount.setSnapToTicks(true);  
        //设置绘制刻度  
        sliderCount.setPaintTicks(true);  
        //设置主、次刻度的间距  
        sliderCount.setMajorTickSpacing(2000);  
        sliderCount.setMinorTickSpacing(1000);  
        //设置绘制刻度标签，默认绘制数值刻度标签  
        sliderCount.setPaintLabels(true);  
        sliderCount.addChangeListener(countListener);
        c.add(sliderCount);
        sliderCount.setSize(200, 40);
        sliderCount.setLocation(220, 170);
        sliderCount.setEnabled(false);

		brDef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
            	DDosFrame.console.showLog("已选中 ‘默认’请求次数");
                // 使用默认
            	//jtfTestStr.setEnabled(false);
                sliderCount.setEnabled(false);
            	jtfTestStr.setText("");
            }
        });
		brCus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
            	DDosFrame.console.showLog("已选中 ‘自定义’请求次数");
                // 获取自定义的值传递过去
            	//jtfTestStr.setEnabled(true);
                sliderCount.setEnabled(true);
                jtfTestStr.setText("" + sliderCount.getValue());
            	
            }
        });
		
		// 请求信息
		JLabel jlInfoStr = new SelJLabel();
		c.add(jlInfoStr);
		jlInfoStr.setSize(105, 25);
		jlInfoStr.setLocation(20, 220);
		jlInfoStr.setText("请　求　信　息:");

		jtaInfo.setEnabled(false);
		jtaInfo.setBackground(Color.BLACK);
		jtaInfo.setForeground(Color.black);
		JScrollPane jspInfo = new JScrollPane(jtaInfo);
		c.add(jspInfo);
		jspInfo.setSize(297, 150);
		jspInfo.setLocation(130, 220);

		final JButton jbRun = new SelJButton("开始");
		final JButton jbStop = new SelJButton("停止");
		final JButton jbExit = new SelJButton("关闭");
		c.add(jbRun);
		jbRun.setSize(75, 25);
		jbRun.setLocation(130, 380);
		// 按钮动作监听 
		jbRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(StrUtils.isEmpty(jtfTestRegex.getText())) {
					Icon img = new ImageIcon(DDosFrame.class.getResource("res/wen"));
                	JOptionPane.showMessageDialog (ddos, "　请输入请求地址！", "提示(¬_¬)", JOptionPane.PLAIN_MESSAGE, img);
				} else {
					jbRun.setEnabled(false);
					jbStop.setEnabled(true);
					jbExit.setEnabled(false);
					appendInfo("开始DDOS攻击：" + jtfTestRegex.getText() + StrUtils.LINE_SEPAR);
					try {
						int tCount = brDelDef.isSelected() ? defaultCount : sliderTCoun.getValue();
						int sCount = brDef.isSelected() ? 0 : sliderCount.getValue();
						dos = new DDos();
						dos.doDDos(tCount, sCount);
					} catch (InterruptedException e1) {
						console.showError(e1);
					}
				}
			}
		});
		c.add(jbStop);
		jbStop.setSize(75, 25);
		jbStop.setLocation(230, 380);
		jbStop.setEnabled(false);
		// 按钮动作监听 
		jbStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbRun.setEnabled(true);
				jbStop.setEnabled(false);
				jbExit.setEnabled(true);
				if(dos.stopDDos()) {
					appendInfo("停止成功！" + StrUtils.LINE_SEPAR);
				}
			}
		});

		c.add(jbExit);
		jbExit.setSize(75, 25);
		jbExit.setLocation(330, 380);
		// 按钮动作监听 
		jbExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseUtil.exitFrame(true, ddos, "是否关闭测试工具？");
			}
		});
		
		// ICON 
		JLabel jlTankenUri = new iTankenJLabel();
		c.add(jlTankenUri);
		jlTankenUri.setSize(100, 100);
		jlTankenUri.setLocation(20, 260);

		super.setIconImage(new ImageIcon(DDosFrame.class.getResource("res/logo.png")).getImage());
		super.setLocation(136, 64); // 设置窗口位置
		super.setSize(460, 440); // 设置窗口大小
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
	 * 拼接信息
	 * @param content
	 */
	public synchronized static void appendInfo(String content) {
		jtaInfo.append(content);
		// console.showLog(content);
		jtaInfo.setCaretPosition(jtaInfo.getDocument().getLength());
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
        });
	} 
	
	/**
	 * 关闭程序
	 * @param e
	 */
	public void exitWindow(WindowEvent e) {
		Icon img = new ImageIcon(DDosFrame.class.getResource("res/wen"));
        int result = JOptionPane.showConfirmDialog(this, "是否关闭测试工具？", " iTanken·DDos提示", 
        	JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, img);
        if(result == JOptionPane.YES_OPTION) { 
        	System.exit(0);
        } else  if(result == JOptionPane.NO_OPTION)  { 
            setVisible(true); 
            validate(); 
        } 
    } 

	/**
	 * Run Method
	 * @param args
	 */
	public static void main(String args[]) {
		DDosFrame rt = ddos;
		if(rt != null) {
			console.showLog("欢迎使用iTanken DDos攻击测试工具！更多信息请浏览 http://www.zixizixi.com/");
		}
	}
}

/**
 * 图标链接
 * @author StarSevenSky
 * @author Tianqi Liu
 * @version 2016年11月27日
 */
class iTankenJLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	//private static DDosFrame df = null;

	public iTankenJLabel() {
		super.setIcon(new ImageIcon(DDosFrame.class.getResource("res/i")));
		super.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
        		try {
        			URI uri = new URI("http://zixizixi.com/");
					Desktop dtp = Desktop.getDesktop();
					if(Desktop.isDesktopSupported() && dtp.isSupported(Desktop.Action.BROWSE)){ // 判断是否支持
						dtp.browse(uri);/*
						if(df == null) {
							df = new DDosFrame(DDosFrame.title + " & RomanInt");
							df.jtfRomanInt.setEditable(true);
							df.jtfIntRoman.setEditable(true);
							df.jbRomanInt.setEnabled(true);
							df.jlRomanInt.setToolTipText(null);
							df.jlRomanInt.setForeground(Color.black); 
							df.jlRoman.setForeground(Color.black); 
							df.jlInt.setForeground(Color.black); 
							DDosFrame.rTest.setVisible(false); 
						}*/
					}
				} catch (Exception ex) { DDosFrame.console.showError(" labelUriiTanken:不能打开浏览器 - "+ex); }
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