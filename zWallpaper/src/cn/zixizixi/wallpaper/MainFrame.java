package cn.zixizixi.wallpaper;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.SplashScreen;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import cn.zixizixi.wallpaper.util.ConsoleDialog;
import cn.zixizixi.wallpaper.util.StrUtils;

/**
 * 设置必应每日壁纸
 * @author Tanken·L
 * @version 20170901
 */
public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * 是否在进行退出操作
     */
    private boolean exitOpr  = false;
    
    /**
     * get container 容器
     */
    private Container c = super.getContentPane();

    private static final String title = "[SystemTools]设置每日必应桌面壁纸 · 子兮子兮";
    
    /**
     * 公用颜色
     */
    private static final Color PUB_COLOR = new Color(240, 240, 240);

    private static int width = 192 * 4;
    private static int height = 108 * 4;
    private static JLabel imageLabel = new JLabel();
    private static volatile boolean success = false;
    private static MainFrame mainFrame = null;
    
    public MainFrame() {
        super(title);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            ImageIcon icon = new ImageIcon(MainFrame.class.getResource("res/logo.png"));
            super.setIconImage(icon.getImage());
            ConsoleDialog.showLog("'logo.png' image load status(2=aborted; 4=errored; 8=complete): " + icon.getImageLoadStatus());
        } catch (Exception e) {
            ConsoleDialog.showError("样式设置失败：" + e.getMessage());
        }

        c.setLayout(null);
        c.setBackground(PUB_COLOR);
        imageLabel.setSize(width, height);
        imageLabel.setLocation(0, 0);
        ImageIcon icon = new ImageIcon(MainFrame.class.getResource("res/loading.gif"));
        imageLabel.setIcon(icon);
        ConsoleDialog.showLog("'loading.gif' image load status(2=aborted; 4=errored; 8=complete): " + icon.getImageLoadStatus());
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        c.add(imageLabel);
        
        super.setResizable(false); // 不允许改变窗口大小
        super.setBounds(136, 64, width, (height + 30));
        super.setVisible(true); // 显示窗口
        super.setDefaultCloseOperation(0); // 取消默认关闭
        
        super.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                exitWindow(e);
            }
        });

        KeyboardFocusManager keyManager = KeyboardFocusManager.getCurrentKeyboardFocusManager(); // 得到当前键盘事件的管理器
        keyManager.addKeyEventPostProcessor(new KeyEventPostProcessor() {
            public boolean postProcessKeyEvent(KeyEvent event) {
                if (event.getID() != KeyEvent.KEY_PRESSED) { // 按键按下事件
                    return false;
                }
                ConsoleDialog.showLog("KeyCode：" + event.getKeyCode());
                if (event.isAltDown() && event.getKeyCode() == KeyEvent.VK_F12) { // 按下 Alt + F12
                    if (ConsoleDialog.dialogState) {
                        ConsoleDialog.close(true);
                        ConsoleDialog.showLog("按下 ‘Alt + F12’ 关闭调试窗口");
                    } else {
                        ConsoleDialog.showConsole();
                        ConsoleDialog.showLog("按下 ‘Alt + F12’ 打开调试窗口");
                    }
                } else if(event.isAltDown() && event.getKeyCode() == KeyEvent.VK_W && !exitOpr) {
                    ConsoleDialog.showDebug("按下 ‘Alt + W’ 快捷关闭程序");
                    exitWindow(null); // Alt + W 关闭快捷键
                } else {
                    ConsoleDialog.showLog("\n");
                }
                return true;
            }
        });
    }
    
    public static synchronized void showMsg(Object msgObj, int type) {
        JOptionPane.showMessageDialog(mainFrame, msgObj, " 子兮子兮·提示", type, 
                new ImageIcon(MainFrame.class.getResource("res/logo.png")));
    }
    
    public static void main(String[] args) {
        String filePath = splash(); // 显示启动界面并获取图片
        mainFrame = new MainFrame(); // 初始化应用主界面
        setWallpaper(filePath); // 设置桌面壁纸
        showBaseInfo(); // 显示系统基本信息
        refresh(10000); // 设置失败 10s 后重试
    }

    private static String splash() {
        new Thread() {
            public void run() {
                try {
                    SplashScreen splash = SplashScreen.getSplashScreen();
                    Graphics2D g = splash.createGraphics();
                    g.setColor(Color.cyan);
                    int fontSize = 14;
                    g.setFont(new Font("Microsoft Yahei", Font.PLAIN, fontSize));
                    g.drawString("正在设置壁纸，请稍候", 10, 250); // 当前显示的图片
                    splash.update();
                    for (int i = 0; i < 11; i++) {
                        g.drawString(".", (10 + 8 * fontSize + i * fontSize), 250);
                        splash.update(); // 刷新以上内容到屏幕
                        Thread.sleep(150);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        
        new ConsoleDialog(title, false); // 初始化并隐藏日志窗口
        
        String imageUrl = SetBingImage.getUrl("1920x1080");
        String filePath = SetBingImage.downloadImage(imageUrl);
        
        ConsoleDialog.showDebug("必应桌面壁纸链接：" + StrUtils.L_SEPAR + imageUrl);
        ConsoleDialog.showDebug("壁纸图片保存位置：" + StrUtils.L_SEPAR + filePath);
        
        try {
            Thread.sleep(1200); // 这儿决定 SplashScreen 显示时间长短.
        } catch (Exception e) {
        }
        return filePath;
    }
    
    private static void setWallpaper(String filePath) {
        String msg = "";
        if (filePath == null) {
            success = false;
            msg = "下载图片失败，请检查网络或按 Alt + F12 查看控制台信息。";
        } else {
            success = true;
            ImageIcon image = new ImageIcon(filePath);
            image.setImage(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
            // JLabel imageLabel = new JLabel(image);
            MainFrame.imageLabel.setIcon(image);
            
            if (SetBingImage.setWinWallpaper(filePath)) {
                msg = "设置 Windows 系统桌面背景成功！";
            } else {
                msg = "设置桌面背景失败，图片保存位置：" + StrUtils.L_SEPAR + filePath;
            }
        }

        ConsoleDialog.showDebug(msg);
        showMsg(msg, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private static void refresh(int ms) {
        if (!success) {
            String s = (ms / 1000) + "s ";
            ConsoleDialog.showDebug(s + "后重试...");
            Thread thread = new Thread() {
                public void run() {
                    while (!success) {
                        try {
                            Thread.sleep(ms);
                        } catch (Exception e) {
                            ConsoleDialog.showError("Thread.sleep：" + e.getMessage());
                        }
                        ConsoleDialog.showDebug("正在重试...");

                        String filePath = SetBingImage.downloadImage(SetBingImage.getUrl("1920x1080"));
                        if (filePath != null) {
                            setWallpaper(filePath);
                        }
                        if (success) {
                            ConsoleDialog.showDebug("设置成功，停止重试。");
                        } else {
                            ConsoleDialog.showError("设置失败，" + s + "后再次重试...");
                        }
                    }
                }
            };
            thread.start();
            // thread.interrupt();
        }
    }
    
    private static void showBaseInfo() {
        String[] sNames = { "User Name", "OS Name", "App Path", "Java Version" };
        String[] sValue = { StrUtils.U_NAME, (StrUtils.S_NAME + " (" + StrUtils.S_ARCH + ")"), 
                             StrUtils.U_DIR, (StrUtils.J_VERSION + " (" + StrUtils.J_HOME + ")") };
        for (int i = 0; i < sNames.length; i++) {
            ConsoleDialog.showDebug(sNames[i] + ": " + sValue[i]);
        }
        
        ConsoleDialog.showDebug("屏幕分辨率：" + ConsoleDialog.toolkit.getScreenSize().width
                                      + " * " + ConsoleDialog.toolkit.getScreenSize().height);
        ConsoleDialog.showLog("欢迎使用 iTanken 设置每日必应桌面壁纸工具！更多信息请浏览 https://zixizixi.cn/");
    }
    
    /**
     * 关闭程序
     * 
     * @param e
     */
    private void exitWindow(WindowEvent e) {
        exitOpr = true;
        Icon img = new ImageIcon(MainFrame.class.getResource("res/wen"));
        int result = JOptionPane.showConfirmDialog(this, "是否关闭应用程序？", " iTanken·iWallpaper 提示", 
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
