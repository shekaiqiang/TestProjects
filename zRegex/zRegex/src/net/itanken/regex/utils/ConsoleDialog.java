package net.itanken.regex.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * 控制台窗口
 * @author iTanken
 * @version 2016-11-02 19:27:55
 */
public class ConsoleDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JFrame dialog;
	private static JTextPane text = new JTextPane();
	private static DefaultStyledDocument doc = (DefaultStyledDocument) text.getStyledDocument();
	private static MutableAttributeSet attr = new SimpleAttributeSet();
	public static Toolkit toolkit = Toolkit.getDefaultToolkit();
	public static boolean dialogState = false;

	/**
	 * 使用默认标题
	 */
	public ConsoleDialog() {
		super();
		dialog = this;
		dialogState = true;
		super.setTitle("调试控制台");
		this.setStyle();
		this.addTextPane();
		dialog.setVisible(true); // 显示窗口
	}

	/**
	 * 使用自定义标题
	 * @param title
	 */
	public ConsoleDialog(String title) {
		super();
		dialog = this;
		this.setTitle(title);
		this.setStyle();
		this.addTextPane();
        dialogState = true;
		dialog.setVisible(true); // 显示窗口
	}

	/**
	 * 使用自定义标题同时控制是否显示
	 * @param title 标题
	 * @param show 是否显示
	 * @param jframe MainFrame
	 */
	public ConsoleDialog(String title, boolean show) {
		super();
		dialog = this;
		this.setTitle(title);
		this.setStyle();
		this.addTextPane();
		dialogState = show;
		dialog.setVisible(show); // 是否显示窗口
	}
	
	/**
	 * 设置标题
	 */
	public void setTitle (String title) {
		super.setTitle("【Ctrl+F12】调试控制台：" + title);
	}

	/**
	 * 设置界面UI样式
	 */
	private void setStyle() {
        Font smallFont = new Font("微软雅黑", Font.PLAIN, 12);
        Font bigFont = new Font("微软雅黑", Font.PLAIN, 14);
        UIManager.put("PopupMenu.font", smallFont);
        UIManager.put("Panel.font", smallFont); 	// Panel 字体
        UIManager.put("Label.font",smallFont); 		// Label 字体
        UIManager.put("ComboBox.font",bigFont); 	// combox 字体
        UIManager.put("ComboBox.background",new Color(163,184,204));
        UIManager.put("ComboBox.foreground",Color.WHITE);
        UIManager.put("Component.font",smallFont);	// component 字体
        UIManager.put("Button.font",smallFont); 	// button 字体
        UIManager.put("Button.background",new Color(163,184,204));
        UIManager.put("Button.borderPainted", Boolean.valueOf(false));
        UIManager.put("ScrollBar.foreground",new Color(163,184,204));
        UIManager.put("ScrollBar.background",new Color(204,204,204));
        UIManager.put("ScrollBar.thumb", Color.black);
        
	}
	
	/**
	 * 添加文本窗格，用于显示信息
	 */
	private void addTextPane() {
		try {
	        text.setBackground(new Color(199,237,204)); // 设置背景色为护眼色
			JScrollPane scrollPane = new JScrollPane(text);
			scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
			this.getContentPane().setLayout(new BorderLayout());
			this.getContentPane().add(scrollPane);

			// 对话框基本样式
			this.setIconImage(new ImageIcon(ConsoleDialog.class.getResource("../res/logo.png")).getImage());
			int width = 470, x = (toolkit.getScreenSize().width - width);
			int height = 300, y = (toolkit.getScreenSize().height - height);
			this.setLocation(x, y);
			this.setSize(width, height);
			this.setResizable(true);
			this.setAlwaysOnTop(true); // 窗口始终在最前
			this.setDefaultCloseOperation(0); // 取消默认关闭事件
			this.addWindowListener(new WindowAdapter() { // 关闭程序
				public void windowClosing(WindowEvent e) {
					if(close(0)) {
						dialogState = false;
					}
				}
			});
			StyleConstants.setFontFamily(attr, "Microsoft Yahei");
			StyleConstants.setFontSize(attr, 12);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭控制台（隐藏）
	 * @param e
	 * @return
	 */
	public static boolean close(int type) {
	    boolean isClose = false;
		if(type == 1) {
		    isClose = CloseUtil.exit(false, dialog, false, null);
		} else {
		    isClose = CloseUtil.exitFrame(false, dialog, "是否关闭控制台？");
		}
		if (isClose) {
		    dialogState = false;
		}
        // return CloseUtil.exit(dialog, true, "是否关闭控制台？");
		return isClose;
	}
	
	/**
	 * 显示控制台
	 */
	public static void showConsole() {
        dialogState = true;
        dialog.setVisible(true); // 显示窗口
	}
	
	/**
	 * 显示日志信息(GREEN)
	 * @param log 信息文本
	 */
	public synchronized static void showLog(Object log) {
		String info = (("调试信息：".equals(log)) ? log.toString() : ("【" + StrUtils.nowDateTime() + "】" + StrUtils.LINE_SEPAR + "\t" + log));
		try {
			StyleConstants.setForeground(attr, new Color(0, 102, 0));
			doc.insertString(doc.getLength(), info, attr);
			doc.insertString(doc.getLength(), StrUtils.LINE_SEPAR, null);
			text.setCaretPosition(doc.getLength());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 显示调试信息(BLUE)
	 * @param log 信息文本
	 */
	public synchronized static void showDebug(Object log) {
		String debug = ("【" + StrUtils.nowDateTime() + "】" + StrUtils.LINE_SEPAR + "\t" + log);
		try {
			StyleConstants.setForeground(attr, new Color(51, 0, 255));
			doc.insertString(doc.getLength(), debug, attr);
			doc.insertString(doc.getLength(), StrUtils.LINE_SEPAR, null);
			text.setCaretPosition(doc.getLength());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 显示警告信息(ORANGE)
	 * @param log 信息文本
	 */
	public synchronized static void showWarn(Object log) {
		String warn = ("【" + StrUtils.nowDateTime() + "】" + StrUtils.LINE_SEPAR + "\t" + log);
		try {
			StyleConstants.setForeground(attr, new Color(255, 102, 0));
			doc.insertString(doc.getLength(), warn, attr);
			doc.insertString(doc.getLength(), StrUtils.LINE_SEPAR, null);
			text.setCaretPosition(doc.getLength());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 显示错误信息(RED)
	 * @param log 信息文本
	 */
	public synchronized static void showError(Object log) {
		String error = ("【" + StrUtils.nowDateTime() + "】" + StrUtils.LINE_SEPAR + "\t" + log);
		try {
			StyleConstants.setForeground(attr, new Color(255, 0, 51));
			doc.insertString(doc.getLength(), error, attr);
			doc.insertString(doc.getLength(), StrUtils.LINE_SEPAR, null);
			text.setCaretPosition(doc.getLength());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 显示时间信息(BLACK)
	 */
	public synchronized static void showTime() {
		String time = ("【" + StrUtils.nowDateTime() + "】" + StrUtils.LINE_SEPAR + "\t");
		try {
			StyleConstants.setForeground(attr, new Color(0, 0, 0));
			doc.insertString(doc.getLength(), time, attr);
			doc.insertString(doc.getLength(), StrUtils.LINE_SEPAR, null);
			text.setCaretPosition(doc.getLength());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		new ConsoleDialog("Test");
	}
}
