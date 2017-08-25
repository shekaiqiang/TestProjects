package test;

import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

// import java.util.Arrays;

public class testYP {

	public static void main(String[] args) {
		testWebService();
	}
	
	private static void testWebService() {
		String actionName = "Execute";
		/**
		 * 药品管理
		 */
		String code = "QryYPCGJHSQMX"; // 采购申请单信息查询-STOP
		String inVal = "<MSG><ASK><PAR><BEGINDATE>2016-05-27 10:09:59</BEGINDATE><ENDDATE>2017-05-27 10:09:59</ENDDATE><IKWBM>1</IKWBM><CCGD></CCGD><CJYDW></CJYDW><CCZYGH>M63</CCZYGH><CCZYXM>操作员姓名</CCZYXM></PAR></ASK></MSG>";

		code = "QryYPCRKMX"; // 查询药品出入库明细
		inVal = "<MSG><ASK><PAR><CCRD>2017C00920</CCRD><BEGINDATE>2017-01-01 17:58:20</BEGINDATE><ENDDATE>2017-07-27 17:58:20</ENDDATE><IKWBM>1</IKWBM><CCRD></CCRD><IDFKWBM></IDFKWBM><CCZYGH>M63</CCZYGH><CCZYXM>操作员姓名</CCZYXM></PAR></ASK></MSG>";

		code = "QryYPXX"; // 药品信息 通过
		inVal = "<MSG><ASK><PAR><CBM></CBM><CWPTXM>4106006</CWPTXM><CCZYGH>M63</CCZYGH><CCZYXM>操作员姓名</CCZYXM></PAR></ASK></MSG>";

		/*
		code = "QryCGPSD"; // 采购配送单查询 通过
		inVal = "<MSG><ASK><PAR><CTM>11117052401</CTM><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM></PAR></ASK></MSG>";
		
		code = "QryCGPSDBZ"; // 采购配送大包装查询  通过
		inVal = "<MSG><ASK><PAR><CTM>2117052401</CTM><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM></PAR></ASK></MSG>";
		
		code = "QryYPCRCKQR"; // 查询药库药品待出库明细  药库出库确认使用 通过
		inVal = "<MSG><ASK><PAR><CCZYXM>管理员</CCZYXM><CCZYGH>M63</CCZYGH><IKWBM>1</IKWBM><CCRD>2010C00001</CCRD></PAR></ASK></MSG>";
		
		code = "QryYPCRCKQRHZ"; // 查询药品出库确认汇总  药库出库确认使用 通过
		inVal = "<MSG><ASK><PAR><BEGINDATE>2016-05-27 17:58:20</BEGINDATE><ENDDATE>2017-06-27 17:58:20</ENDDATE><IKWBM>1</IKWBM><CCRD/><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM></PAR></ASK></MSG>";
		
		code = "QryYPCRRKQRHZ"; // 查询药品入库确认汇总 药房入库确认使用 通过
		inVal = "<MSG><ASK><PAR><CCZYXM>管理员</CCZYXM><ENDDATE/><CCZYGH>M63</CCZYGH><CCRD/><IKWBM>3</IKWBM><BEGINDATE/></PAR></ASK></MSG>";
		
		code = "QryYPCRRKQR"; // 查询药房药品待入库明细   药房入库确认使用 通过
		inVal = "<MSG><MSH><MSH.1>HT10004</MSH.1><MSH.2>drugsInStorageDetail</MSH.2></MSH><ASK><PAR><IKWBM>3</IKWBM><CCRD>2017R00513</CCRD><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM></PAR></ASK></MSG>";
*/
		code = "QryYPPCXX"; // 获取药品批次信息
		inVal = "<MSG><ASK><PAR><CBM>0101013</CBM><CCZYXM>管理员</CCZYXM><CYPTXM>0101013</CYPTXM><CCZYGH>M63</CCZYGH><IKWBM>1</IKWBM><IKCBM/></PAR></ASK><MSH><MSH.1>HT10004</MSH.1><MSH.2>drugsBatchInfoQuery</MSH.2></MSH></MSG>";
		
		code = "YPLWCK"; // 药库药品联网出库
		inVal = "<MSG><MSH><MSH.1>HT10004</MSH.1><MSH.2>drugsOutOfStorage</MSH.2></MSH><ASK><PAR><Rows><Row><CDWMC>单位名称</CDWMC><CSCCJ>生产厂家</CSCCJ><DSCRQ>2016-06-01 08:47:16</DSCRQ><CBM>0101013</CBM><IKWBM>1</IKWBM><IDFKWBM>3</IDFKWBM><CDFKWMC>测试出库库位</CDFKWMC><IXH>1</IXH><IKCBM>16</IKCBM><NSL>1</NSL><MPFJ>90.0</MPFJ><MGRJ>23.8261</MGRJ><MLSJ>23.8261</MLSJ><CYPGG>规格</CYPGG><CZXDW>支</CZXDW><CYKDW>盒</CYKDW><IHSXS>1</IHSXS><CPH>批号</CPH><DYXQ>2019-05-31 15:43:57</DYXQ><MPCPFJ>90.0</MPCPFJ><MPCGRJ>90.0</MPCGRJ><MPCLSJ>99.0</MPCLSJ><CPZWH>批准文号</CPZWH><CPCGHDW>供货单位</CPCGHDW><CPCCCH>盒</CPCCCH><DSJ>2017-05-31 15:43:57</DSJ><CCZYGH>M63</CCZYGH><CCZYXM>测试管理员</CCZYXM><CPCTXM>TESTPCTM001</CPCTXM> </Row></Rows></PAR></ASK></MSG>";
		inVal = "<MSG><MSH><MSH.1>HT10004</MSH.1><MSH.2>drugsOutOfStorage</MSH.2></MSH><ASK><PAR><Rows><Row><CBM>2017060501</CBM><DSJ>2017-06-06 09:43</DSJ><IDFKWBM>2</IDFKWBM><CPCTXM/><MLSJ>23</MLSJ><MPFJ>0</MPFJ><IKWBM>2</IKWBM><NSL>2</NSL><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM><IKCBM>44380</IKCBM><CDFKWMC>门诊西药房</CDFKWMC><MGRJ>20</MGRJ><IXH>1</IXH></Row></Rows><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM></PAR></ASK></MSG>";
		/*
		code = "YPGHRK"; //  药库药品购货入库
		inVal = "<MSG><MSH><MSH.1>HT10004</MSH.1><MSH.2>drugsIntoStorage</MSH.2></MSH><ASK><PAR><Rows><Row><CDWMC></CDWMC><DSCRQ>2016-06-01 08:47:16</DSCRQ><CCRD></CCRD><IKWBM>1</IKWBM><IKCBM>16</IKCBM><CBM>0101013</CBM><CMC>人参茎叶皂苷胶囊</CMC><CGHDW>供货单位</CGHDW><IXH>1</IXH><NSL>1</NSL><CSCCJ>生产厂家</CSCCJ><DYXQ>2007-10-20 00:00:00.000</DYXQ><MPFJ>90.0</MPFJ><MGRJ>23.8261</MGRJ><MLSJ>23.8261</MLSJ><CZBLX>招标类型</CZBLX><CYPGG>规格</CYPGG><CZXDW>最小单位</CZXDW><CYKDW>药库单位</CYKDW><IHSXS>1</IHSXS><CPH></CPH><MPCPFJ>90.0</MPCPFJ><MPCGRJ>90.0</MPCGRJ><MPCLSJ>99.0</MPCLSJ><CPZWH>批准文号</CPZWH><CPCGHDW>供货单位</CPCGHDW><CPCCCH>药库单位</CPCCCH><DSJ>2017-05-31 15:33:27</DSJ><CCZYGH>M63</CCZYGH><CCZYXM>测试管理员</CCZYXM></Row></Rows></PAR></ASK></MSG>";

		code = "QryYPPSMX"; //  药品配送明细查询  通过 
		inVal = "<MSG><ASK><PAR><CPSDBH>17052401</CPSDBH><CTM></CTM><CCZYGH>操作员工号</CCZYGH><CCZYXM>操作员姓名</CCZYXM></PAR></ASK></MSG>";
		*/

		code = "YPLWCKQR"; // 联网出库确认
		inVal = "<MSG><MSH><MSH.1>HT10004</MSH.1><MSH.2>drugOutConfirm</MSH.2></MSH><ASK><PAR><IDFKWBM>2</IDFKWBM><CCZYGH>M63</CCZYGH><CCRD>2017C00954</CCRD><CCZYXM>管理员</CCZYXM><IKWBM>1</IKWBM></PAR></ASK></MSG>";

		code = "YPLWRKQR"; // 联网入库确认
		inVal = "<MSG><MSH><MSH.1>HT10004</MSH.1><MSH.2>drugIntoConfirm</MSH.2></MSH><ASK><PAR><IDFKWBM>1</IDFKWBM><CCZYXM>管理员</CCZYXM><CCZYGH>M63</CCZYGH><CCRD>2017R00539</CCRD><IKWBM>2</IKWBM></PAR></ASK></MSG>";

		HttpClientManager.setPostUrl("http://192.168.200.24:7890/webservice1.asmx"); // 药品
		try {
			String res = HttpClientManager.postWebService(inVal, code, actionName);
			System.out.println("\n【Response】 ： " + res);
			Element valEle = DocumentHelper.parseText(res).getRootElement();
			System.out.println("\n【outValue】 ： " + HttpClientManager.docToStr(valEle) + "\n");
		} catch (DocumentException e) {
			// e.printStackTrace();
			System.out.println("\n返回消息格式异常！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 转换Row节点
	 * @param row Row 节点
	 * @param type 转换类型 1-属性，其他-节点
	 */
	@SuppressWarnings("unchecked")
	public static void convertRow(String row, int type) {
		try {
			Element rowEle = DocumentHelper.parseText(row).getRootElement();
			if(type==1) {
				List<Attribute> attrs = rowEle.attributes();
				for (Attribute attr : attrs) {
					System.out.println("row.addAttribute(\""+ attr.getName() + "\", \""+ attr.getValue() + "\");");
				}
			} else {
				List<Element> eles = rowEle.elements();
				for (Element ele : eles) {
					System.out.println("row.addAttribute(\""+ ele.getName() + "\", \""+ ele.getText() + "\");");
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 字符串是否为空 ""/null
	 * @param inStr
	 * @return
	 */
	public static Boolean isEmptyStr(String inStr) {
		return inStr == null || "".equals(inStr.trim()) || "null".equalsIgnoreCase(inStr.trim());
	}
}
