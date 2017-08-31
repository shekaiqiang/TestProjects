package net.wallpaper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.win32.StdCallLibrary;

import jp.ne.so_net.ga2.no_ji.jcom.IDispatch;
import jp.ne.so_net.ga2.no_ji.jcom.JComException;
import jp.ne.so_net.ga2.no_ji.jcom.ReleaseManager;

public class SetBingImage {
    
    private static boolean debug = true;

    public static String getUrl(String custom) {
        String infoUrl = "http://cn.bing.com/HPImageArchive.aspx?idx=0&n=1";
        try {
            URL url = new URL(infoUrl);
            URLConnection urlConn = url.openConnection();
            urlConn.setConnectTimeout(3000);
            BufferedReader bufRead = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
            StringBuilder strBud = new StringBuilder();
            String line = null;
            while ((line = bufRead.readLine()) != null) {
                strBud.append(line);
            }
            systemPrintln(strBud.toString());
            Element imgEle = DocumentHelper.parseText(strBud.toString()).getRootElement();
            infoUrl = "http://cn.bing.com" + imgEle.element("image").elementText("url");
            
            if (custom != null && custom.trim() != "") {
                infoUrl = infoUrl.replace("1366x768", custom);
            }
        } catch (SocketTimeoutException e) {
            systemPrintln("连接超时：" + e.getMessage());
        } catch (IOException e) {
            systemPrintln("加载出错：" + e.getMessage());
        } catch (DocumentException e) {
            systemPrintln("解析出错：" + e.getMessage());
        }
        return infoUrl;
    }
    
    public static String setWallpaper() {
        String fileSepar = System.getProperty("file.separator");
        String savePath = System.getProperty("user.home") + fileSepar + "Pictures" + fileSepar + "BingWallpaper";
        systemPrintln(savePath);
        String imageUrl = getUrl("1920x1080");
        systemPrintln(imageUrl);
        try {
            URL url = new URL(imageUrl);
            URLConnection urlConn = url.openConnection();
            urlConn.setConnectTimeout(5000);
            File fileDir = new File(savePath);
            if(!fileDir.exists()){  
                fileDir.mkdirs();  
            }
            
            InputStream is = urlConn.getInputStream();
            byte[] bs = new byte[1024];
            int len;
            String fileName = imageUrl.substring(imageUrl.indexOf("_ZH-CN") + 6, imageUrl.length());
            String filePath = fileDir.getPath() + fileSepar + fileName;
            OutputStream os = new FileOutputStream(filePath);
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            os.close();
            is.close();
            systemPrintln(filePath);
            return filePath;
        } catch (IOException e) {
            systemPrintln("加载出错：" + e.getMessage());
        } finally {
            fileSepar = null;
            savePath = null;
            imageUrl = null;
        }
        return null;
    }
    
    public static boolean setWinWallpaper(String filePath) {
        boolean flag = false;
        
        if (Platform.isWindows()) {
            // 调用 User32 设置桌面背景
            flag = User32.INSTANCE.SystemParametersInfoA(20, 1, filePath, 1);
        } else {
            
        }
        
        return flag;
    }
    
    public static void main(String[] args) {
        // debug = false;
        String filePath = setWallpaper();
        if (setWinWallpaper(filePath)) {
            System.out.println("设置 Windows 系统桌面背景成功！");
        } else {
            System.out.println("设置桌面背景失败，图片位置：" + filePath);
        }
    }
    
    public synchronized static void systemPrintln(Object obj) {
        if (debug) {
            System.out.println(obj);
        }
    }
    
    public static boolean jcom(String progID, String method, Object[] params) {
        IDispatch inface = null;
        try {
            ReleaseManager rm = new ReleaseManager();
            inface = new IDispatch(rm, progID);
            String result = inface.method(method, params).toString();
            System.out.println(result);
            return true;
        } catch (JComException e) {
            e.printStackTrace();
        } finally {
            if (inface != null) {
                inface.release();
                inface = null;
            }
        }
        return false;
    }
    
    public interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary) Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"), CLibrary.class);
  
        void printf(String format, Object... args);
    }
    
    public interface User32 extends StdCallLibrary {
        User32 INSTANCE = (User32) Native.loadLibrary("User32", User32.class);
        
        /**
         * 查询/设置系统级参数
         * @param uAction 要设置的参数: 
         *      6(设置视窗的大小) / 17(开关屏保程序) / 13, 24(改变桌面图标水平和垂直间距) / 15(设置屏保等待时间) / 
         *      20(设置桌面背景墙纸) / 93(开关鼠标轨迹) / 97 (开关Ctrl+Alt+Del窗口)
         * @param uParam 参数
         * @param lpvParam 参数
         * @param fuWinIni 
         * @return 
         */
        public boolean SystemParametersInfoA(int uAction, int uParam, String lpvParam, int fuWinIni);
        
    }

}
