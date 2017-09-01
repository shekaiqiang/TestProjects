package cn.zixizixi.wallpaper.util;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import cn.zixizixi.wallpaper.MainFrame;

/**
 * 自定义 swing 界面元素
 * @author Tanken·L
 * @version 20170901
 */
public class CustomElement {

    /**
     * 点击图标打开链接
     * @param jframe
     * @return
     */
    public static JLabel iTankenJLabel(final JFrame jframe) {
        return new iTankenJLabel("res/i", "https://zixizixi.cn/", jframe);
    }
    
    /**
     * 自定义样式标签
     * @return
     */
    public static JLabel selJLabel() {
        return new SelJLabel("Microsoft Yahei");
    }
    
    /**
     * 自定义样式按钮
     * @param text
     * @param margin
     * @return
     */
    public static JButton selJButton(String text, int margin) {
        if (margin == 0) {
            return new SelJButton(text);
        }
        return new SelJButton(text, margin);
    }
    
    /**
     * 自定义样式下拉框
     * @return
     */
    public static JComboBox<Object> SelJComboBox() {
        return new SelJComboBox("Microsoft Yahei");
    }
}

/**
 * 链接
 * 
 * @author Tanken·L
 * @Start 2016年4月9日
 * @Done 2016年4月9日
 */
class iTankenJLabel extends JLabel {
    private static final long serialVersionUID = 1L;

    public iTankenJLabel(String imagePath, String url, final JFrame jframe) {
        super.setIcon(new ImageIcon(MainFrame.class.getResource(imagePath)));
        super.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    URI uri = new URI(url);
                    Desktop dtp = Desktop.getDesktop();
                    if (Desktop.isDesktopSupported() && dtp.isSupported(Desktop.Action.BROWSE)) { // 判断是否支持
                        dtp.browse(uri);
                        ConsoleDialog.showLog("打开网站：" + uri.toString());
                    }
                } catch (Exception ex) {
                    ConsoleDialog.showError(" labelUriiTanken:不能打开浏览器 - " + ex);
                }
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

    public SelJLabel(String fontFamily) {
        super.setFont(new Font(fontFamily, Font.PLAIN, 14));
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
class SelJComboBox extends JComboBox<Object> {
    private static final long serialVersionUID = 1L;

    public SelJComboBox(String fontFamily) {
        super.setFont(new Font(fontFamily, Font.PLAIN, 12));
    }
}
