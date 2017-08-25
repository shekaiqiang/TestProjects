package xyz.ablue.wscaller;

import javax.swing.*;
import java.awt.*;

public class Main {
	public static Font pubFont = new Font("Microsoft Yahei", Font.PLAIN, 12);
	boolean packFrame = false;

	// Construct the application
	public Main() {
		MainFrame frame = new MainFrame();
		// Validate frames that have preset sizes
		// Pack frames that have useful preferred size info, e.g. from their
		// layout
		if (packFrame) {
			frame.pack(); // 自动适应大小
		} else {
			frame.validate(); // 重新调整大小
		}
		// 窗口居中
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		try {
            splash("正在启动，请稍候");
			String version = System.getProperty("java.vm.version");
			if (version != null) {
				// 检查 JVM 版本, 需要 1.3 或更高版本
				try {
					int i = version.indexOf('.');
					int v1 = Integer.parseInt(version.substring(0, i));
					int j = version.indexOf('.', i + 1);
					int v2 = Integer.parseInt(version.substring(i + 1, j));
					if (v1 < 1 || (v1 == 1 && v2 < 3)) {
						JOptionPane.showMessageDialog(null, "需要 JVM 1.3 或更高版本，请更新您的 Java 运行环境！", "错误信息",
								JOptionPane.ERROR_MESSAGE);
						System.exit(-1);
					}
				} catch (Exception ex) {
				}
			}
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				String names[] = { "Label", "MenuItem", "CheckBoxMenuItem",/* "CheckBox", "PopupMenu", "JRadioButtonMenuItem", */
						"ComboBox", "Button", "ScrollPane", "Tree", "TabbedPane", "EditorPane",
						"TitledBorder", "Menu", "TextArea", "OptionPane", "Panel", "ToolTip", "List", 
						/* "MenuBar", "ToolBar", "ToggleButton",  "ProgressBar", "TableHeader", */ 
						/* "ColorChooser", "PasswordField", */"TextField", "Table", "Viewport",
						/* "RadioButtonMenuItem","RadioButton", */ "DesktopPane", "InternalFrame" };
				for (String item : names) {
					UIManager.put(item + ".font", pubFont);
				}
			} catch (Exception e) {
			}
			new Main();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    private static void splash(String message) {
        new Thread() { // 用于运行SplashScreen的线程
            public void run() {
                try {
                    SplashScreen splash = SplashScreen.getSplashScreen();
                    Graphics2D g = splash.createGraphics();
                    g.setColor(new Color(1, 170, 237));
                    g.setFont(new Font("Lucaida Console", Font.PLAIN, 12));
                    // g.drawString(splash.getBounds().toString(), 10, 30); // SplashScreen 在屏幕的位置，大小
                    // g.drawString(splash.getSize().toString(), 10, 50); // SplashScreen 的大小
                    // g.drawString(splash.getImageURL().toString(), 10, 70); // 当前显示的图片
                    
                    String[] sNames = { "Java Version", "User Name", "OS Name", "App Path" };
                    String[] sValue = { (StrUtils.J_VERSION/*+" ("+StrUtils.J_HOME+")"*/), StrUtils.U_NAME, 
                                        (StrUtils.S_NAME + " (" + StrUtils.S_ARCH + ")"), StrUtils.U_DIR };
                    for (int i = 0; i < sNames.length; i++) {
                        g.drawString(sNames[i] + ": " + sValue[i], 10, (210 + i * 20));
                        splash.update(); // 刷新以上内容到屏幕
                    }

                    int fontSize = 14, showX = 10, showY = 20;
                    g.setFont(new Font("Lucaida Console", Font.PLAIN, fontSize));
                    g.drawString(message, showX, showY); // 显示信息
                    splash.update();
                    for (int i = 0; i < 6; i++) {
                        g.drawString("．", (showX + message.length() * fontSize + i * fontSize), showY);
                        splash.update(); // 刷新以上内容到屏幕
                        Thread.sleep(200);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        try {
            Thread.sleep(2000); // 决定 SplashScreen 显示时间长短.
        } catch (Exception e) {
        }
    }
}