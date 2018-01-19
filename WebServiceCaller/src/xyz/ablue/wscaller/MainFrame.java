package xyz.ablue.wscaller;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import org.apache.axis.wsdl.symbolTable.Parameter;
import org.apache.axis.wsdl.symbolTable.Parameters;

import cn.zixizixi.www.util.ConsoleDialog;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
    private static MainFrame mainFrame = null;
    private boolean exitOpr  = false;
	DynamicInvoker invoker = null;
	String title = null;
	String location = null;
	String serviceName = null;
	String portName = null;
	String operationName = null;
	Parameters parameters = null;
	JTextArea[] txtParameterValues = null;

	JPanel contentPane;
	Border border1;
	BorderLayout borderLayout1 = new BorderLayout();
	JPanel paneNorth = new JPanel();
	JPanel paneSouth = new JPanel();
	JButton btnExit = new JButton();
	JButton btnAbout = new JButton();
	JButton btnTest = new JButton();
	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JTextField txtLocation = new JTextField();
	JButton btnFind = new JButton();
	JScrollPane jScrollPane1 = new JScrollPane();
	Border border2;
	JLabel jLabel4 = new JLabel();
	JComboBox<String> comboService = new JComboBox<String>();
	JLabel jLabel5 = new JLabel();
	JLabel jLabel6 = new JLabel();
	JComboBox<String> comboOperation = new JComboBox<String>();
	JLabel jLabel7 = new JLabel();
	GridBagLayout gridBagLayout1 = new GridBagLayout();
	GridLayout gridLayout1 = new GridLayout();
	Border border3;
	JPanel paneCenter = new JPanel();
	Border border4;
	JTextField txtTimes = new JTextField();

	// Construct the frame
	public MainFrame(String title, String wsdl) {
		super.enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		this.title = title;
		try {
			this.jbInit(wsdl);
			super.setIconImage(new ImageIcon(MainFrame.class.getResource("/cn/zixizixi/www/res/favicon.png")).getImage());
		} catch (Exception e) {
			ConsoleDialog.showError(e.getMessage());
			e.printStackTrace();
		}
		keyManager();
		mainFrame = this;
	}

	// Component initialization
	private void jbInit(String wsdl) throws Exception {
		contentPane = (JPanel) this.getContentPane();
		border1 = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		border2 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		border3 = BorderFactory.createEmptyBorder(0, 3, 0, 5);
		border4 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		contentPane.setBorder(border2);
		contentPane.setMinimumSize(new Dimension(730, 500));
		contentPane.setPreferredSize(new Dimension(730, 500));
		contentPane.setLayout(borderLayout1);
		this.setContentPane(contentPane);
		this.setResizable(false);
		this.setSize(new Dimension(730, 500));
		this.setState(Frame.NORMAL);
		this.setTitle(title);
		paneNorth.setLayout(gridBagLayout1);
		paneSouth.setLayout(gridLayout1);
		btnExit.setText("退出");
		btnExit.addActionListener(new MainFrame_btnExit_actionAdapter(this));
		btnAbout.setText("关于");
		btnAbout.addActionListener(new MainFrame_btnAbout_actionAdapter(this));
		btnTest.setText("调用");
		btnTest.addActionListener(new MainFrame_btnTest_actionAdapter(this));
		jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel1.setText("调用次数:");
		jLabel2.setRequestFocusEnabled(true);
		jLabel2.setToolTipText("");
		jLabel2.setText("");
		jLabel3.setToolTipText("");
		jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel3.setText("WSDL 地址：");
		txtLocation.setMinimumSize(new Dimension(350, 22));
		txtLocation.setPreferredSize(new Dimension(350, 22));
		txtLocation.setText(wsdl);
		btnFind.setText("查找服务");
		btnFind.addActionListener(new MainFrame_btnFind_actionAdapter(this));
		borderLayout1.setHgap(5);
		borderLayout1.setVgap(5);
		jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel4.setText("服务名称：");
		comboService.setMinimumSize(new Dimension(350, 22));
		comboService.setPreferredSize(new Dimension(350, 22));
		comboService.setEditable(false);
		comboService.addActionListener(new MainFrame_comboService_actionAdapter(this));
		jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel6.setText("操作方法：");
		comboOperation.setMinimumSize(new Dimension(350, 22));
		comboOperation.setPreferredSize(new Dimension(350, 22));
		comboOperation.setEditable(false);
		comboOperation.addActionListener(new MainFrame_comboOperation_actionAdapter(this));
		jLabel5.setText("");
		jLabel7.setText("");
		gridLayout1.setColumns(7);
		gridLayout1.setHgap(10);
		paneCenter.setLayout(new VFlowLayout(VFlowLayout.TOP));
		paneCenter.setBackground(Color.white);
		paneCenter.setBorder(border4);
		txtTimes.setText("1");
		txtTimes.setHorizontalAlignment(SwingConstants.RIGHT);
		paneSouth.add(btnAbout, null);
		paneSouth.add(jLabel1, null);
		paneSouth.add(txtTimes, null);
		paneSouth.add(btnTest, null);
		paneSouth.add(jLabel2, null);
		contentPane.add(paneNorth, BorderLayout.NORTH);
		contentPane.add(paneSouth, BorderLayout.SOUTH);
		contentPane.add(jScrollPane1, BorderLayout.CENTER);
		jScrollPane1.getViewport().add(paneCenter, null);
		paneNorth.add(jLabel3, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 3), 30, 9));
		paneNorth.add(txtLocation, new GridBagConstraints(1, 0, 2, 1, 1.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 400, 3));
		paneNorth.add(btnFind, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 30, 0));
		paneNorth.add(jLabel4, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 30, 9));
		paneNorth.add(comboService, new GridBagConstraints(1, 1, 2, 1, 1.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 400, 3));
		paneNorth.add(jLabel5, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 1), 50, 25));
		paneNorth.add(jLabel6, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 30, 9));
		paneNorth.add(comboOperation, new GridBagConstraints(1, 2, 2, 1, 1.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 400, 3));
		paneNorth.add(jLabel7, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 1), 50, 25));
		paneSouth.add(btnExit, null);
	}

	/**
	 * Overridden so we can exit when window is closed
	 * 在窗口关闭时退出
	 */
	protected void processWindowEvent(WindowEvent e) {
		// super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			exitWindow(e);
		}
	}

	/**
	 * 点击关闭按钮退出
	 * @param e
	 */
	void btnExit_actionPerformed(ActionEvent e) {
		exitWindow(null);
	}

	/**
	 * 关于 按钮
	 * @param e
	 */
	void btnAbout_actionPerformed(ActionEvent e) {
		AboutBox dlg = new AboutBox(this);
		dlg.setVisible(true);
		// Invoker.show(dlg);
	}

	void btnFind_actionPerformed(ActionEvent e) {
		String location = txtLocation.getText();
		if (location == null || (location = location.trim()).length() == 0) {
			showMsg("WSDL 地址不能为空！", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			location = txtLocation.getText();
			invoker = new DynamicInvoker(location);
			Vector<?> v = invoker.enumServiceNames();
			comboService.removeAllItems();
			comboOperation.removeAllItems();
			serviceName = portName = operationName = null;
			if (v.size() == 0) {
				showMsg("当前地址找不到相关服务！", JOptionPane.WARNING_MESSAGE);
				return;
			}
			Object[] ss = v.toArray();
			Arrays.sort(ss);
			int len = ss.length;
			for (int i = 0; i < len; i++) {
				comboService.addItem((String) ss[i]);
			}
			// comboService.setSelectedIndex(0); // 在服务名称选择框选中第一个服务 --此处重复
		} catch (Exception ex) {
			showMsg((ex.getClass().getName() + ": " + ex.getMessage()), JOptionPane.ERROR_MESSAGE);
		}
	}

	private String selectEntry(Vector<?> v, String entryName) {
		if (v.size() == 0) {
			return null;
		} else if (v.size() > 1) {
			SelectDialog dlg = new SelectDialog(this, entryName, v, "请在当前列表中选择一个" + entryName + "：");
			dlg.setVisible(true);
			return dlg.result;
		} else {
			return (String) v.elementAt(0);
		}
	}

	void comboService_actionPerformed(ActionEvent e) {
		try {
			serviceName = (String) comboService.getSelectedItem();
			if (serviceName == null) {
				return;
			}
			Vector<?> v = invoker.enumPortNames(serviceName);

			portName = selectEntry(v, "端口");
			if (portName == null) {
				showMsg("在服务 " + serviceName + " 中找不到端口，请选择其他服务！", JOptionPane.WARNING_MESSAGE);
				return;
			}
			v = invoker.enumOperationNames(serviceName, portName);
			comboOperation.removeAllItems();
			Object[] ss = v.toArray();
			Arrays.sort(ss);
			int len = ss.length;
			for (int i = 0; i < len; i++) {
				comboOperation.addItem((String) ss[i]);
			}
			comboOperation.setSelectedIndex(0); // 在操作方法选择框选中第一个操作
		} catch (Exception ex) {
			if(ex instanceof IllegalArgumentException) {
				ex = new Exception("当前端口无相关服务操作，请选择其他端口！");
			}
			showMsg(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}

	void comboOperation_actionPerformed(ActionEvent e) {
		try {
			operationName = (String) comboOperation.getSelectedItem();
			if (serviceName == null || operationName == null || portName == null) {
				return;
			}
			parameters = invoker.enumParameters(serviceName, portName, operationName);
			if (parameters == null) {
				return;
			}
			Vector<?> v = parameters.list;
			int size = v.size();
			if (size == 0) {
				paneCenter.removeAll();
				paneCenter.add(new JLabel("当前服务操作方法无参数，请选择其他操作方法。"));
				btnTest.setEnabled(false);
			} else {
				btnTest.setEnabled(true);
				paneCenter.removeAll();
				txtParameterValues = new JTextArea[size];
				for (int i = 0; i < size; i++) {
					Parameter para = (Parameter) v.elementAt(i);
					JPanel p = new JPanel();
					p.setLayout(new FlowLayout(FlowLayout.LEFT));
					p.setBorder(new EtchedBorder());
					p.add(new JLabel("参数 " + (i + 1) + ":"));
					JTextField tf = new JTextField("", 15);
					tf.setEditable(false);
					tf.setText(para.getQName().getLocalPart());
					p.add(tf);
					p.add(new JLabel("类型:"));
					tf = new JTextField("", 8);
					tf.setEditable(false);

					String wsdlType = para.getType().getQName().getLocalPart();
					String mode = invoker.getParameterModeString(para);
					tf.setText(mode + " " + wsdlType);
					p.add(tf);
					if (para.getMode() != Parameter.OUT) {
						// for IN and INOUT parameters
						p.add(new JLabel("值:"));
						JTextArea txtArea =  new JTextArea("", 1, 27);
                        txtArea.setMinimumSize(new Dimension(355, 22));
						txtArea.setBorder(new LineBorder(Color.lightGray, 1, false));
						txtParameterValues[i] = txtArea;
						p.add(txtParameterValues[i]);
					} else {
						txtParameterValues[i] = null;
					}
					paneCenter.add(p);
				}
			}
			validate();
			repaint();
		} catch (Exception ex) {
			showMsg(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}

	void btnTest_actionPerformed(ActionEvent e) {
		// Begin invoking or testing
		if (invoker == null || serviceName == null || portName == null || operationName == null || parameters == null) {
			showMsg("请指定 WSDL 地址/服务名称/操作方法，并在调用/测试之前对所有参数赋值！", JOptionPane.WARNING_MESSAGE);
			return;
		}
		int times = 0;
		try {
			times = Integer.parseInt(txtTimes.getText());
		} catch (NumberFormatException ex1) {
			times = 0;
		}
		if (times <= 0) {
			showMsg("调用次数必须大于 0 ！", JOptionPane.WARNING_MESSAGE);
			return;
		}

		Vector<String> parameterValues = new Vector<String>();
		Vector<?> v = parameters.list;
		int paraNumbers = v.size();
		if (txtParameterValues != null) {
			for (int i = 0; i < paraNumbers; i++) {
				if (txtParameterValues[i] != null) {
					String value = txtParameterValues[i].getText();
					/*
					if (value == null || (value = value.trim()).length() == 0) {
						JOptionPane.showMessageDialog(this, "请给参数 " + (i + 1) + " 指定一个值！", "警告", JOptionPane.WARNING_MESSAGE);
						return;
					}*/
					parameterValues.addElement(value);
				}
			}
		}
		long timeSpan = 0;
		long begin = Calendar.getInstance().getTime().getTime();
		try {
			Map<?, ?> outputs = null;
			for (int i = 0; i < times; i++) {
				outputs = invoker.invoke(serviceName, portName, operationName, parameterValues);
			}
			long end = Calendar.getInstance().getTime().getTime();
			timeSpan = end - begin;
			ResultDialog dlg = new ResultDialog(this, "执行结果", outputs, timeSpan, times);
			dlg.setVisible(true);
			// dlg.show();
		} catch (Exception ex) {
			ConsoleDialog.showError(ex);
			timeSpan = Calendar.getInstance().getTime().getTime() - begin;
			String errMsg = ex.getMessage();
			if(errMsg.contains("SocketTimeoutException")) {
				ex = new Exception("调用此操作方法获取结果超时！【耗时：" + (timeSpan / 1000) + " 秒 " + (timeSpan % 1000) + " 毫秒】");
			} else if(errMsg.contains("ConnectException")) {
				ex = new Exception("调用失败，连接被拒绝，请检查 WebService 服务是否可用！");
			} else if(errMsg.contains("SocketException")) {
				ex = new Exception("调用失败，请求发生 SocketException 异常！" + (errMsg.contains("Connection reset") ? "【 Connection reset 】" : ""));
			} else if(errMsg.contains("(503)Service Unavailable")) {
				ex = new Exception("调用失败，ERROR 503：当前服务不可用！");
			} /* else if(errMsg.contains("could not find deserializer for type")) {
				ex = new Exception("调用失败，当前操作无请求和返回参数，请选择带有参数的操作方法再进行调用！");
			} */
			showMsg(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void keyManager() {
        KeyboardFocusManager keyManager = KeyboardFocusManager.getCurrentKeyboardFocusManager(); // 得到当前键盘事件的管理器
        keyManager.addKeyEventPostProcessor(new KeyEventPostProcessor() {
            public boolean postProcessKeyEvent(KeyEvent event) {
                if (event.getID() != KeyEvent.KEY_PRESSED) { // 按键按下事件
                    return false;
                }
                if (event.isAltDown() && event.getKeyCode() == KeyEvent.VK_F12) { // 按下 Alt + F12
                    if (ConsoleDialog.dialogState) {
                        ConsoleDialog.close(true);
                        ConsoleDialog.showLog("按下 ‘Alt + F12’ 关闭调试窗口");
                    } else {
                        ConsoleDialog.showConsole();
                        ConsoleDialog.showLog("按下 ‘Alt + F12’ 打开调试窗口");
                    }
                } else if(event.isControlDown() && event.getKeyCode() == KeyEvent.VK_W && !exitOpr) {
                    ConsoleDialog.showDebug("按下 ‘Ctrl + W’ 快捷关闭程序");
                    exitWindow(null); // Alt + W 关闭快捷键
                } else if (!event.isControlDown()) {
                    ConsoleDialog.showLog("KeyCode：" + event.getKeyCode());
                } else {
                    // ConsoleDialog.showLog("\n");
                }
                return true;
            }
        });
	}
	
    public static synchronized void showMsg(Object msgObj, int type) {
        JOptionPane.showMessageDialog(mainFrame, msgObj, " 子兮子兮·提示", type, 
                new ImageIcon(MainFrame.class.getResource("/cn/zixizixi/www/res/logo.png")));
    }
	
    /**
     * 关闭程序
     * 
     * @param e
     */
    private void exitWindow(WindowEvent e) {
        exitOpr = true;
        Icon img = new ImageIcon(MainFrame.class.getResource("/cn/zixizixi/www/res/wen"));
        int result = JOptionPane.showConfirmDialog(this, "是否关闭应用程序？", " iTanken·WSCaller 提示", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, img);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else if (result == JOptionPane.NO_OPTION) {
            exitOpr = false;
            setVisible(true);
            validate();
            ConsoleDialog.showDebug("取消关闭程序");
        }
    }
}

class MainFrame_btnExit_actionAdapter implements java.awt.event.ActionListener {
	MainFrame adaptee;

	MainFrame_btnExit_actionAdapter(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.btnExit_actionPerformed(e);
	}
}

class MainFrame_btnAbout_actionAdapter implements java.awt.event.ActionListener {
	MainFrame adaptee;

	MainFrame_btnAbout_actionAdapter(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.btnAbout_actionPerformed(e);
	}
}

class MainFrame_btnFind_actionAdapter implements java.awt.event.ActionListener {
	MainFrame adaptee;

	MainFrame_btnFind_actionAdapter(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.btnFind_actionPerformed(e);
	}
}

class MainFrame_comboService_actionAdapter implements java.awt.event.ActionListener {
	MainFrame adaptee;

	MainFrame_comboService_actionAdapter(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.comboService_actionPerformed(e);
	}
}

class MainFrame_comboOperation_actionAdapter implements java.awt.event.ActionListener {
	MainFrame adaptee;

	MainFrame_comboOperation_actionAdapter(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.comboOperation_actionPerformed(e);
	}
}

class MainFrame_btnTest_actionAdapter implements java.awt.event.ActionListener {
	MainFrame adaptee;

	MainFrame_btnTest_actionAdapter(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.btnTest_actionPerformed(e);
	}
}
