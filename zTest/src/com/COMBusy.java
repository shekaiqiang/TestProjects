package com;

import jp.ne.so_net.ga2.no_ji.jcom.IDispatch;
import jp.ne.so_net.ga2.no_ji.jcom.JComException;
import jp.ne.so_net.ga2.no_ji.jcom.ReleaseManager;

public class COMBusy {

    private static IDispatch inface = null; // COM接口

    public COMBusy(String progID) throws Exception {
        if (inface == null) {
            try {
                ReleaseManager rm = new ReleaseManager();
                inface = new IDispatch(rm, progID);
            } catch (JComException e) {
                inface = null;
                throw new Exception("调用 COM 接口：COM 接口初始化失败：" + e.getMessage());
            }
            System.out.println("调用 COM 接口：COM 初始化成功：" + IDispatch.IID);
        }
    }

    public Object callHisCom(String method, String inEle) throws Exception {
        String reqStr = "<?xml version=\"1.0\" encoding=\"GBK\"?>" + inEle;
        Object[] param = new Object[] { reqStr };
        try {
            String OutValue = inface.method(method, param).toString();
            /*
            if ("0".equals(OutValue.substring(0, 1))) {
                System.out.println("调用 COM 接口：执行（" + method + "）失败！" + OutValue.substring(1, OutValue.length()));
            }*/
            return OutValue;
        } catch (JComException e) {
            e.printStackTrace();
            return "调用COM接口：执行Execute（"+ method +"）失败！";
        } finally {
            inface.release();
        }
    }

    /**
     * Test 
     * @param args inArgs
     */
    public static void main(String[] args) {
        COMBusy com = null;
        Object obj = null;
        try {
            com = new COMBusy("YXCISWrit.LisReport");
            obj = com.callHisCom("HTMLExecute", 
                  "<MSG><ASK><PAR>"
                + "<CCXID>28</CCXID>"
                + "<CCZYBH>973350</CCZYBH>"
                + "<CBRH>17000011</CBRH>"
                + "<CBLBM>520</CBLBM>"
                + "<CBH>0000205813</CBH>"
                + "</PAR></ASK></MSG>");
            System.out.println("————————————\n返回内容：│" + obj + "│\n————————————");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            com = null;
        }
    }
}
