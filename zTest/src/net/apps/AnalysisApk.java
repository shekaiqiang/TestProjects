package net.apps;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.xmlpull.v1.XmlPullParser;
import android.content.res.AXmlResourceParser;
import android.util.TypedValue;

/**
 * 分析apk文件，获取apk应用的包名、版本信息及图标
 */
public class AnalysisApk {

    private static final float RADIX_MULTS[] = { 0.00390625F, 3.051758E-005F, 1.192093E-007F, 4.656613E-010F };
    private static final String DIMENSION_UNITS[] = { "px", "dip", "sp", "pt", "in", "mm", "", "" };
    private static final String FRACTION_UNITS[] = { "%", "%p", "", "", "", "", "", "" };

    /**
     * 解压.zip文件(apk可以当成一个zip文件)，注意只能解压zip格式的文件，解压.rar文件会出现 java.io.IOException: Negative seek offset 异常
     * @param apkUrl apk应用(zip)文件的绝对路径
     * @param logoUrl 图标生成的地址
     * @throws IOException
     */
    public static String[] unZip(String apkUrl, String logoUrl) {
        String[] apkInfo = new String[3]; // [0]:版本号;[1]版本名;[2]包名
        byte b[] = new byte[1024];
        int length;
        ZipFile zipFile;
        try {
            zipFile = new ZipFile(new File(apkUrl));
            Enumeration<?> enumeration = zipFile.entries();
            ZipEntry zipEntry = null;
            while (enumeration.hasMoreElements()) {
                zipEntry = (ZipEntry) enumeration.nextElement();
                if (!zipEntry.isDirectory()) { // 不是目录，即是文件
                    if ("AndroidManifest.xml".equals(zipEntry.getName())) { // 获取到安卓程序的清单文件
                        try {
                            AXmlResourceParser parser = new AXmlResourceParser();
                            parser.open(zipFile.getInputStream(zipEntry));
                            while (true) {
                                int type = parser.next();
                                if (type == XmlPullParser.END_DOCUMENT) {
                                    break;
                                }
                                switch (type) {
                                    case XmlPullParser.START_TAG: {
                                        for (int i = 0; i != parser.getAttributeCount(); ++i) {
                                            if ("versionCode".equals(parser.getAttributeName(i))) {
                                                apkInfo[0] = getAttributeValue(parser, i); // 版本号码
                                            } else if ("versionName".equals(parser.getAttributeName(i))) {
                                                apkInfo[1] = getAttributeValue(parser, i); // 版本名称
                                            } else if ("package".equals(parser.getAttributeName(i))) {
                                                apkInfo[2] = getAttributeValue(parser, i); // 包名
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(zipEntry.getName());
                    // 向指定路径logoUrl输出应用图标
                    if ("res/drawable/i.jpg".equals(zipEntry.getName())) {
                        OutputStream outputStream = new FileOutputStream(logoUrl);
                        InputStream inputStream = zipFile.getInputStream(zipEntry);
                        while ((length = inputStream.read(b)) > 0) {
                            outputStream.write(b, 0, length);
                        }
                        System.out.println(length);
                        if(outputStream != null) {
                            outputStream.close();
                            outputStream = null;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apkInfo;
    }

    private static String getAttributeValue(AXmlResourceParser parser, int index) {
        int type = parser.getAttributeValueType(index);
        int data = parser.getAttributeValueData(index);
        if (type == TypedValue.TYPE_STRING) {
            return parser.getAttributeValue(index);
        }
        if (type == TypedValue.TYPE_ATTRIBUTE) {
            return String.format("?%s%08X", getPackage(data), data);
        }
        if (type == TypedValue.TYPE_REFERENCE) {
            return String.format("@%s%08X", getPackage(data), data);
        }
        if (type == TypedValue.TYPE_FLOAT) {
            return String.valueOf(Float.intBitsToFloat(data));
        }
        if (type == TypedValue.TYPE_INT_HEX) {
            return String.format("0x%08X", data);
        }
        if (type == TypedValue.TYPE_INT_BOOLEAN) {
            return data == 0 ? "false" : "true";
        }
        if (type == TypedValue.TYPE_DIMENSION) {
            return Float.toString(complexToFloat(data)) + DIMENSION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
        }
        if (type == TypedValue.TYPE_FRACTION) {
            return Float.toString(complexToFloat(data)) + FRACTION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
        }
        if (type >= TypedValue.TYPE_FIRST_COLOR_INT && type <= TypedValue.TYPE_LAST_COLOR_INT) {
            return String.format("#%08X", data);
        }
        if (type >= TypedValue.TYPE_FIRST_INT && type <= TypedValue.TYPE_LAST_INT) {
            return String.valueOf(data);
        }
        return String.format("<0x%X, type 0x%02X>", data, type);
    }

    private static String getPackage(int id) {
        if (id >>> 24 == 1) {
            return "android:";
        }
        return "";
    }

    // ILLEGAL STUFF, DONT LOOK :)
    public static float complexToFloat(int complex) {
        return (float) (complex & 0xFFFFFF00) * RADIX_MULTS[(complex >> 4) & 3];
    }
    
    public static void main(String[] args) {
    	String apkUrl = "D:/Development/Tools/Android/LockScreen/LockScreen6.apk";
    	String logoUrl = "D:/Development/Tools/Android/LockScreen/icon.png";
    	String apkInfo[] = unZip(apkUrl, logoUrl);
    	for (int i = 0; i < apkInfo.length; i++) {
    		System.out.println(apkInfo[i]);
		}
	}
}