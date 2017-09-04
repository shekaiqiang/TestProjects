package xyz.ablue.wscaller;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.net.URI;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AboutBox extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	Frame parent = null;

	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel insetsPanel1 = new JPanel();
	JPanel insetsPanel2 = new JPanel();
	JPanel insetsPanel3 = new JPanel();
	JButton button1 = new JButton();
	JLabel label3 = new JLabel();
	ImageIcon image1 = new ImageIcon();
	BorderLayout borderLayout1 = new BorderLayout();
	FlowLayout flowLayout1 = new FlowLayout();
	GridLayout gridLayout1 = new GridLayout();
	FlowLayout flowLayout2 = new FlowLayout();

	JLabel itankenLabel = new iTankenJLabel("icon.png", "http://www.itanken.cn/", "星柒天iTanken");
	JLabel iconLabel = new iTankenJLabel("favicon.png", "https://zixizixi.cn/", "子兮子兮");
	JLabel gitLabel = new iTankenJLabel("github.png", "https://github.com/iTanken/Projects/tree/master/WebServiceCaller", "GitHub");
	JLabel weiboLabel = new iTankenJLabel("weibo.png", "http://www.weibo.com/itanken/", "新浪微博");

	public AboutBox(Frame parent) {
		super(parent);
		this.parent = parent;
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			jbInit();
			pack();
			centerDialog();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void centerDialog() {
		Point parentLocation;
		Dimension parentSize;
		if (parent == null) {
			parentLocation = new Point(0, 0);
			parentSize = Toolkit.getDefaultToolkit().getScreenSize();
		} else {
			parentLocation = getParent().getLocation();
			parentSize = getParent().getSize();
		}
		Dimension size = getSize();
		setLocation(parentLocation.x + parentSize.width / 2 - size.width / 2,
				parentLocation.y + parentSize.height / 2 - size.height / 2);
	}

	// Component initialization
	private void jbInit() throws Exception {
		/* try {
			image1 = new ImageIcon(xyz.ablue.wscaller.MainFrame.class.getResource("icom_50.png"));
			imageLabel.setIcon(image1);
		} catch (Exception e) {} */
		this.setModal(true);
		this.setTitle("About WebService Caller");
		panel1.setLayout(borderLayout1);
		panel2.setLayout(flowLayout2);
		insetsPanel1.setLayout(flowLayout1);
		insetsPanel2.setLayout(flowLayout1);
		insetsPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		gridLayout1.setRows(2);
		gridLayout1.setVgap(5);
		gridLayout1.setColumns(1);
		gridLayout1.setHgap(0);
		// label3.setMaximumSize(new Dimension(220, 228));
		label3.setMinimumSize(new Dimension(220, 200));
		label3.setHorizontalAlignment(SwingConstants.LEFT);
		label3.setHorizontalTextPosition(SwingConstants.LEFT);
		label3.setText("<html>WebService Caller 2.0<br>"
				+ "<br>　&copy; 2003 Wang Yong Gang wsCaller 1.0<br>"
				+ "<br>　Revised by StarSevenSky 2017"
				+ "<br>　子兮子兮 <a href=\"https://zixizixi.cn/\">https://zixizixi.cn/</a></html>");
		insetsPanel3.setLayout(gridLayout1);
		insetsPanel3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		insetsPanel3.setMinimumSize(new Dimension(220, 200));
		insetsPanel3.setPreferredSize(new Dimension(280, 280));
		insetsPanel3.setRequestFocusEnabled(true);
		button1.setText("知道了，消失吧");
		button1.addActionListener(this);
		panel2.setMinimumSize(new Dimension(350, 230));
		panel2.setPreferredSize(new Dimension(350, 230));
		panel2.add(insetsPanel2, null);
		
		insetsPanel2.add(itankenLabel, null);
		insetsPanel2.add(iconLabel, null);
		insetsPanel2.add(gitLabel, null);
		insetsPanel2.add(weiboLabel, null);
		
		panel1.setMinimumSize(new Dimension(350, 260));
		panel1.setPreferredSize(new Dimension(350, 260));
		insetsPanel3.add(label3, null);
		panel2.add(insetsPanel3, null);
		insetsPanel3.setBackground(new Color(204, 204, 204));
		insetsPanel1.add(button1, null);
		panel1.add(insetsPanel1, BorderLayout.SOUTH);
		panel1.add(panel2, BorderLayout.CENTER);
		this.getContentPane().add(panel1, null);
		this.setSize(350, 240);
		setResizable(false);
	}

	// Overridden so we can exit when window is closed
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			cancel();
		}
		super.processWindowEvent(e);
	}

	// Close the dialog
	void cancel() {
		dispose();
	}

	// Close the dialog on a button event
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			cancel();
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

	public iTankenJLabel(String fileName, String url, String title) {
		super.setIcon(new ImageIcon(AboutBox.class.getResource("/cn/zixizixi/www/res/" + fileName)));
		super.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
        		try {
        			URI uri = new URI(url);
					Desktop dtp = Desktop.getDesktop();
					if(Desktop.isDesktopSupported() && dtp.isSupported(Desktop.Action.BROWSE)){
						dtp.browse(uri);
					}
				} catch (Exception ex) {}
        	}
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				setToolTipText(title);
        	}
		});
	}
}
