package test;

import java.util.HashMap;
import java.util.Map;

public class YxStationTest {
	private static final String path = "http://192.168.202.98:8280/YxStation/YXStationServlet";
	private static final String YWM = "99211";
	private static final String DYM = "10";
	public static void main(String[] args) {
 
		String InXML = 
		   	"<ZYX>" +
				"<ZYX.1>1200432</ZYX.1>" +
				"<ZYX.16>100</ZYX.16>" +
			"</ZYX>" +
			"<MBH>00156</MBH>" +
			"<XSE>" +
				"<XSE.1></XSE.1>" +
			"</XSE>" +
			"<TAG>0</TAG>" +
			"<PGH>" +
				"<PGH.1>1500000006</PGH.1>" +
				"<PGH.4>01</PGH.4>" +
				"<PGH.5>方案11</PGH.5>" +
				"<PGH.12/>" +
				"<PGM Type=\"TAB\">" +
					"<PGM Type=\"ROW\" index=\"1\">\"" +
						"<PGM.1>1500000006</PGM.1>" +
						"<PGM.2>01</PGM.2>" +
						"<PGM.3>2</PGM.3>" +
						"<PGM.4>体温</PGM.4>" +
						"<PGM.5>36.5</PGM.5>" +
					"</PGM>" +
					"<PGM Type=\"ROW\" index=\"2\">" +
						"<PGM.1>1500000006</PGM.1>" +
						"<PGM.2>01</PGM.2>" +
						"<PGM.3>3</PGM.3>" +
						"<PGM.4>脉搏</PGM.4>" +
						"<PGM.5>86</PGM.5>" +
					"</PGM>" +
				"</PGM>" +
			"</PGH>"+"<PDN>2015-07-09 17:09:19.990</PDN>";
		InXML = "<PDN>2015-11-4</PDN>";
		String xml = 
			"<MSG>" + 
				"<MSH>" + 
					"<MSH.1>" + YWM + "</MSH.1>" + 
					"<MSH.2>" + DYM + "</MSH.2>" + 
					"<MSH.3>99999</MSH.3>" + 
					"<MSH.4>34:23:BA:B3:FC:AB</MSH.4>" + 
					"<MSH.5>hy</MSH.5>" + 
					"<MSH.6>99999</MSH.6>" + 
					"<MSH.7>192.168.100.187</MSH.7>" + 
				"</MSH>" + 
				"<ASK>" + 
					"<DAT>" + InXML + "</DAT>" + 
				"</ASK>" + 
			"</MSG>";
		
		xml = 
			"<MSG>" + 
				"<MSH>" + 
					"<MSH.1>" + YWM + "</MSH.1>" + 
					"<MSH.2>" + DYM + "</MSH.2>" + 
					"<MSH.3/>" + 
					"<MSH.4/>" + 
					"<MSH.5/>" + 
					"<MSH.6/>" + 
					"<MSH.7/>" + 
				"</MSH>" + 
				"<ASK>" + 
					"<DAT>" + InXML + "</DAT>" + 
				"</ASK>" + 
			"</MSG>";
		System.out.println("begin");
	    Map<String, String> params = new HashMap<String, String>();
		params.put("code", YWM+"_"+DYM);
		params.put("inValue", xml);
		String encode = "utf-8";
		String resp = HttpServlet.sendMessage(params, encode, path);
	    String Outvalue=resp;
	    System.out.println(Outvalue);
	  
   }
}