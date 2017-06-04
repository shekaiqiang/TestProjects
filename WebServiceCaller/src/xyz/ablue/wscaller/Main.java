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
}