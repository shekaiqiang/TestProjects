package com;

import jp.ne.so_net.ga2.no_ji.jcom.IDispatch;
import jp.ne.so_net.ga2.no_ji.jcom.ReleaseManager;

public class JCom {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        icDecode();
    }

    public static void icDecode() throws Exception {
        ReleaseManager rm = new ReleaseManager();
        IDispatch vbcom = new IDispatch(rm, "YYTYBJK.YBJK"); // YYTYBJK.YBJK // AHisYLK.JK
        System.out.println(vbcom.toString());

        Object[] param = { "XXX", "<InValue><ZDBH>ZDBH001</ZDBH></InValue>" };
        String outVal = vbcom.method("SysInit", param).toString();
        System.out.println(outVal);
        String errInfo = (String) vbcom.get("LastError");
        if (!isEmptyStr(errInfo)) {
            throw new Exception(errInfo);
        }
        System.out.println(vbcom.get("Rstr").toString());
        vbcom.release();
    }

    public static boolean isEmptyStr(String value) {
        return value == null || "".equals(value) || "".equals(value.trim()) || "null".equalsIgnoreCase(value);
    }
}
