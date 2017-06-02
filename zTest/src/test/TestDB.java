package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import utils.DBUtil;

public class TestDB {

	public Connection conn = DBUtil.getConn("YXHIS", "192.168.200.32", "1433", "sa", "123123");
	public PreparedStatement pstmt = null;
	public ResultSet rs = null;

	/**
	 * @throws Exception
	 */
	public void query() throws Exception {
		String sql = "SELECT CJS, CBH, CMC, CGH, CSRM FROM(" +
   "SELECT CBM CJS, '' CBH, CMC, '' CGH, '' CSRM FROM YXHIS..TBSYSJS Z WHERE CHARINDEX('M6', CXT) > 0" +
    " UNION ALL " +
   "SELECT S.CJS, CBH, C.CMC, C.CGH, C.CSRM FROM YXHIS..TBSYSCZY S LEFT JOIN YXHIS..TBCZY C ON S.CUID=C.CSRM" +
   " WHERE S.CJS IN (SELECT CBM FROM YXHIS..TBSYSJS WHERE CHARINDEX('M6', CXT) > 0) AND C.CCXBH ='M6'" +
   ") T ORDER BY CJS, CONVERT(INT, SUBSTRING(CGH, 2, LEN(CGH))) ";
		Element rows = DocumentHelper.createDocument().addElement("Rows");
		try {
			Element mxRows = null;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("CMC"));
				if(isEmptyStr(rs.getString("CBH")) && isEmptyStr(rs.getString("CGH")) && isEmptyStr(rs.getString("CSRM"))) {
					Element row = rows.addElement("Row");
					row.addAttribute("CJS", rs.getString("CJS"));
					row.addAttribute("CMC", rs.getString("CMC"));
					mxRows = row.addElement("MXRows");
				} else if(mxRows != null) {
					Element mxRow = mxRows.addElement("MXRow");
					mxRow.addAttribute("CJS", rs.getString("CJS"));
					mxRow.addAttribute("CBH", rs.getString("CBH"));
					mxRow.addAttribute("CMC", rs.getString("CMC"));
					mxRow.addAttribute("CGH", rs.getString("CGH"));
					mxRow.addAttribute("CSRM", rs.getString("CSRM"));
				}
			}
			System.out.println(retRowEle(rows).asXML());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				rs.close();
				rs = null;
				pstmt.close();
				pstmt = null;
				conn.close();
				conn = null;
			}
		}
	}

	/**
	 * 字符串是否为空 ""/null
	 * 
	 * @param inStr
	 * @return
	 */
	public static Boolean isEmptyStr(String inStr) {
		return inStr == null || "".equals(inStr.trim()) || "null".equalsIgnoreCase(inStr.trim());
	}
	
	/**
	 * 返回请求成功消息格式
	 * @param rows 相关数据 Rows 节点
	 * @return
	 */
	public Element retRowEle(Element rows) {
		Document rtnDoc = DocumentHelper.createDocument();
		Element rtnEle = rtnDoc.addElement("MSG");
		rtnEle.addAttribute("Version", "1.6");
		Element rtnRes = rtnEle.addElement("RES");
		String state = "1";
		if(rows != null) {
			Element rtnRows = rtnRes.addElement("Rows");
			rtnRows.appendContent(rows);
		} else {
			state = "0";
			rtnRes.addElement("ERR").addText("获取不到相关信息！");
		}
		rtnRes.addElement("RES.1").addText(nowStr("yyyy-MM-dd HH:mm:ss"));
		rtnRes.addElement("RES.2").addText(state);
		return rtnEle;
	}
	
	/**
	 * 返回格式化当前系统时间
	 * @param inFmt 
	 * @return
	 */
	public static String nowStr(String inFmt) {
		inFmt = isEmptyStr(inFmt) ? "yyyy-MM-dd HH:mm:ss.SSS" : inFmt;
		return new SimpleDateFormat(inFmt).format(System.currentTimeMillis());
	}

	public static void main(String[] args) throws Exception {
		TestDB test = new TestDB();
		test.query();
	}
}