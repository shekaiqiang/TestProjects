package net.Ping;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;

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
 * GBK
 * http://www.java3z.com/article/article20/200019.html?id=4805
 * @author Tanken°§L
 * @Start 2016/4/4
 * @Done  2016/4/4
 */
public class PingTest {
	public static void main(String[] args) {
		
		String webSite = "zixizixi.cn";
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JFrame frame = new JFrame();
			JTextPane text = new JTextPane();
			frame.getContentPane().setLayout(new BorderLayout());
			frame.getContentPane().add(new JScrollPane(text));
			frame.setLocation(136, 64);
			frame.setTitle("Õ¯ÀŸ≤‚ ‘" + webSite);
			frame.setSize(500, 366);
			frame.setVisible(true);

			String[] cmd = new String[] { "cmd.exe", "/c", "ping " + webSite + " -t" };
			Process process = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String info = "";

			DefaultStyledDocument doc = (DefaultStyledDocument) text.getStyledDocument();
			MutableAttributeSet attr = new SimpleAttributeSet();
			StyleConstants.setForeground(attr, new Color(0, 102, 0));
			StyleConstants.setFontFamily(attr, "Microsoft Yahei");
			StyleConstants.setFontSize(attr, 14);

			while ((info = br.readLine()) != null) {
				if (!"".equals(info)) {
					try {
						doc.insertString(doc.getLength(), info, attr);
						doc.insertString(doc.getLength(), System.getProperty("line.separator"), null);
					} catch (BadLocationException e) {
						e.printStackTrace();
					}
					text.setCaretPosition(doc.getLength());
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}