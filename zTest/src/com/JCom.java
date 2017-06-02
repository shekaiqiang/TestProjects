package com;

import jp.ne.so_net.ga2.no_ji.jcom.IDispatch;
import jp.ne.so_net.ga2.no_ji.jcom.ReleaseManager;

public class JCom {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		JCom jc = new JCom();
		jc.icDecode("");
	}


	  public String icDecode(String cICID) throws Exception {
	    ReleaseManager rm = new ReleaseManager();
	    IDispatch vbcom = new IDispatch(rm, "AHisYLK.JK");

	    Object[] param = { "", "", cICID, "" };
	    vbcom.method("checked", param);
	    String errInfo = (String)vbcom.get("LastError");
	    if (!IsEmptyStr(errInfo)) {
	      throw new Exception(errInfo);
	    }
	    cICID = (String)vbcom.get("Rstr");
	    vbcom.release();
	    return cICID;
	  }
		public static boolean IsEmptyStr(String value) {
			return value==null||"".equals(value)||"".equals(value.trim())||"null".equalsIgnoreCase(value);
		}
}
