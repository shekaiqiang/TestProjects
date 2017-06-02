package xyz.ablue.wscaller;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.border.*;
import java.awt.event.*;

public class ResultDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	Frame parent = null;
	Map<?, ?> result = null;
	long timeSpan = 0;
	int times = 0;

	JPanel panel1 = new JPanel();
	BorderLayout borderLayout1 = new BorderLayout();
	JPanel jPanel1 = new JPanel();
	JButton btnExit = new JButton();
	JScrollPane jScrollPane1 = new JScrollPane();
	JTextArea textResult = new JTextArea();
	Border border1;
	Border border2;
	JTextField txtTimeSpan = new JTextField();
	Border border3;

	public ResultDialog(Frame frame, String title, Map<?, ?> result, long timeSpan, int times) {
		super(frame, title);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		this.result = result;
		this.parent = frame;
		this.timeSpan = timeSpan;
		this.times = times;
		try {
			jbInit();
			pack();
			centerDialog();
		} catch (Exception ex) {
			ex.printStackTrace();
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
		setLocation(parentLocation.x + parentSize.width / 2 - size.width / 2, parentLocation.y + parentSize.height / 2 - size.height / 2);
	}

	private void jbInit() throws Exception {
		border1 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		border2 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		border3 = new EtchedBorder(EtchedBorder.RAISED, Color.white, new Color(148, 145, 140));
		panel1.setLayout(borderLayout1);
		this.setModal(true);
		this.setResizable(false);
		btnExit.setText("确定");
		btnExit.addActionListener(new ResultDialog_btnExit_actionAdapter(this));
		textResult.setFont(new java.awt.Font("DialogInput", 0, 12));
		textResult.setEditable(false);
		textResult.setText("");
		jPanel1.setBorder(null);
		jPanel1.setDebugGraphicsOptions(0);
		panel1.setBorder(border2);
		txtTimeSpan.setBorder(border3);
		txtTimeSpan.setCaretColor(Color.black);
		txtTimeSpan.setEditable(false);
		long avgTime = (long) ((double) timeSpan / (double) times);
		txtTimeSpan.setText("总时间: " + (timeSpan / 1000) + " 秒 " + (timeSpan % 1000) + " 毫秒.\t"
				+ "平均时间: " + (avgTime / 1000) + " 秒 " + (avgTime % 1000) + " 毫秒.");
		borderLayout1.setHgap(3);
		borderLayout1.setVgap(3);
		jScrollPane1.setMinimumSize(new Dimension(500, 300));
		jScrollPane1.setPreferredSize(new Dimension(500, 300));
		getContentPane().add(panel1);
		panel1.add(jPanel1, BorderLayout.SOUTH);
		jPanel1.add(btnExit, null);
		panel1.add(jScrollPane1, BorderLayout.CENTER);
		panel1.add(txtTimeSpan, BorderLayout.NORTH);
		jScrollPane1.getViewport().add(textResult, null);

		textResult.append("返回值和输出参数:\n");
		textResult.append("______________________________________________________________________\n\n");
		for (Iterator<?> i = result.keySet().iterator(); i.hasNext();) {
			String name = (String) i.next();
			Object value = result.get(name);
			String line;
			if (value != null) {
				line = name + "=" + result.get(name).toString() + "\n";
			} else {
				line = name + "=(null)\n";
			}
			textResult.append(line);
		}
	}

	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			dispose();
		}
		super.processWindowEvent(e);
	}

	void btnExit_actionPerformed(ActionEvent e) {
		dispose();
	}
}

class ResultDialog_btnExit_actionAdapter implements java.awt.event.ActionListener {
	ResultDialog adaptee;

	ResultDialog_btnExit_actionAdapter(ResultDialog adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.btnExit_actionPerformed(e);
	}
}