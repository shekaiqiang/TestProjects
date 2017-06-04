package test;

import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

// import java.util.Arrays;

public class test {

	public static void main(String[] args) {
		String rowStr = "";
		//rowStr = "<Row CSYLSH=\"1605000008\" IXH=\"1\" CZYH=\"16050726\" CXM=\"何开云\" CKSBM=\"\" CKSMC=\"新院手足外科\" CWPBM=\"00151\" CWPMC=\"大清创(or)\" CXDDH=\"1605000163\" CBLSH=\"1605000675\" CBTM=\"1605000675\" DXDRQ=\"2016-05-09 15:32:24\" DYXQ=\"2016-05-16 15:32:24\" DSYRQ=\"2016-05-16 11:30:21\" NSL=\"1\" MDJ=\"0\" />";
		//rowStr = "<Row CKSBM=\"W50\" CKSMC=\"六病区\" CWPBM=\"0034\" CBLSH=\"\" CBTM=\"B\" CMC=\"洁牙机\" CLB=\"可循环使用\" CXH=\"\" NSL=\"1\" MDJ=\"0.0000\" DYXQ=\"2016-08-18 11:06:58\" CXDDH=\"1608000037\" DXDRQ=\"2018-08-18 11:06:58\" CDW=\"套\" />";
		//rowStr = "<Row CSBBH=\"设备编号\" CSBBM=\"设备编码\" CSBMC=\"设备名称\" CSBXH=\"设备型号\" CSBDW=\"设备单位\" CPYM=\"拼音码\" CGZBH=\"固资编号\" CGZFL=\"固资分类\" CSBFL=\"设备分类\" CGLFS=\"管理方式\" NSL=\"数量\" DCCRQ=\"出厂日期\" CCCBH=\"出厂编号\" DGRRQ=\"购入日期\" CSCCJ=\"生产厂家\" CGB=\"国别\" CGHS=\"供货商\" CSBWZ=\"设备位置\" CWZBH=\"(设备所在)位置编号\" CBGR=\"保管人\" CPP=\"品牌\" CSYKS=\"使用科室\" DRKSJ=\"入库时间\" DSYRQ=\"使用日期\" CKY=\"款源\" MSBYZ=\"设备原值\" MSBJZ=\"设备净值\" NSYNX=\"使用年限\" MYTZJ=\"已提折旧\" CSYKSBM=\"使用科室编码\" CGZFLBM=\"固资分类编码\" CSBFLBM=\"设备分类编码\" />";
		//convertRow(rowStr, 1);
		//rowStr = "<Row><CCRD>出入单号</CCRD><IKWBM>库位编码</IKWBM><IKCBM>库存编码</IKCBM><CBM>药品编码</CBM><CMC>药品名称</CMC><CYPTXM>药品条形码</CYPTXM><CGHDW>供货单位</CGHDW><IXH>序号</IXH><NSL>数量</NSL><CSCCJ>生产厂家</CSCCJ><DYXQ>有效期</DYXQ><MPFJ>批发价</MPFJ><MGRJ>购入价</MGRJ><MLSJ>零售价</MLSJ><CZBLX>招标类型</CZBLX><CYPGG>换算系数</CYPGG><CZXDW>最小单位</CZXDW><CYKDW>药库单位</CYKDW><IHSXS>药库发药换算系数</IHSXS><CPH>批号</CPH><DYXQ>有效期</DYXQ><MPCPFJ>批发价</MPCPFJ><MPCGRJ>购入价</MPCGRJ><MPCLSJ>零售价</MPCLSJ><CPZWH>批准文号</CPZWH><CPCGHDW>供货单位</CPCGHDW><CPCCCH>药库单位</CPCCCH><CCZYGH>操作员工号</CCZYGH><CCZYXM>操作员姓名</CCZYXM></Row>";
		//rowStr = "<Row><IKCBM>库存编码</IKCBM><CWPBM>物品名称</CWPBM><IHSXS>换算系数</IHSXS><CWPBZ>物品包装</CWPBZ><CGB>国别</CGB><CCD>产地</CCD><CSCCJ>生产厂家</CSCCJ><CHW>存储货位</CHW><DYXQ>有效期</DYXQ><CKCDW>库存单位</CKCDW><CZXDW>最小单位</CZXDW><NSL>库存量</NSL><MPFJ>批发价</MPFJ><MGRJ>购入价</MGRJ><MLSJ>零售价</MLSJ><BENABLE>可用性0 1 </BENABLE><CPH>批号</CPH><DXDRQ>消毒日期</DXDRQ><IXDZT>消毒状态 0 1 </IXDZT><CCJQK>抽检情况</CCJQK><IKCXH>库存序号</IKCXH><IDEFAULT>默认批次 0 1</IDEFAULT><DCJRQ>创建日期</DCJRQ><CPZWH>批准文号</CPZWH><CBZ>备注</CBZ><MXJ>限价</MXJ><CPCTXM>批次条形码</CPCTXM><CNBTXM>内部条形码</CNBTXM></Row>";
		//rowStr = "<Row><IKCBM>库存编码</IKCBM><CWPBM>物品编码</CWPBM><CWPMC>物品名称</CWPMC><CWPLB>物品类别</CWPLB><IGLFS>管理方式1 材料 2 低值易耗 3 高值耗材</IGLFS><IHSXS>换算系数</IHSXS><CWPBZ>物品包装</CWPBZ><CGB>国别</CGB><CCD>产地</CCD><CSCCJ>生产厂家</CSCCJ><CHW>存储货位</CHW><DYXQ>有效期</DYXQ><CKCDW>库存单位</CKCDW><CZXDW>最小单位</CZXDW><NSL>库存量</NSL><MPFJ>批发价</MPFJ><MGRJ>购入价</MGRJ><MLSJ>零售价</MLSJ><BENABLE>可用性0 1 </BENABLE><CPH>批号</CPH><DXDRQ>消毒日期</DXDRQ><IXDZT>消毒状态 0 1 </IXDZT><CCJQK>抽检情况</CCJQK><IKCXH>库存序号</IKCXH><IDEFAULT>默认批次 0 1</IDEFAULT><DCJRQ>创建日期</DCJRQ><CPZWH>批准文号</CPZWH><CBZ>备注</CBZ><MXJ>限价</MXJ></Row>";
		// rowStr = "<Row><CCRD>出入库单</CCRD><IKWBM>库位编码</IKWBM><CKWMC>库位名称</CKWMC><CCRFS>出入方式</CCRFS><DCRSJ>出入时间</DCRSJ><IDFKWBM>对方库位编码</IDFKWBM><CDFKWMC>对方库位名称</CDFKWMC><CJSR>经手人/经发人</CJSR><CBGR>保管人/经领人</CBGR><CCZYGH>操作员工号</CCZYGH><CCZYXM>操作员姓名</CCZYXM><CBZ>备注</CBZ></Row>  ";
		// convertRow(rowStr, 0);
		
		// rowStr = "<Row><CCRD>出入库单</CCRD><ID>序号</ID><IKWBM>库位编码</IKWBM><CKW>库位名称</CKW><IKCBM>库存编码</IKCBM><CYPBM>药品编码</CYPBM><CYPMC>药品名称</CYPMC><CYPBZ>物品包装</CYPBZ><CYPGG>规格</CYPGG><DYXQ>有效期</DYXQ><CYFDW>药房单位</CYFDW><CYKDW>库存单位</CYKDW><IHSXS>换算系数</IHSXS><CPH>批号</CPH><NSL>数量</NSL><CFS>出入方式</CFS><DSJ>出入时间</DSJ><IDFKW>对方库位编码</IDFKW><CDFKW>对方库位名称/供货单位</CDFKW><MPFJ>批发价</MPFJ><MGRJ>购入价</MGRJ><MLSJ>零售价</MLSJ><MTYJ>退货价</MTYJ><CDJH>单据号</CDJH><CSCCJ>生产厂家</CSCCJ><CDWMC>供货单位</CDWMC><IPCBH>供货单位</IPCBH><NKCL>出入库后库存量</NKCL><CCZY>经手人/经发人</CCZY><CBZ>备注</CBZ><CPZWH>批准文号</CPZWH><BFK>付款标记 0 1</BFK><CJSR>结算人</CJSR><DJSSJ>结算时间</DJSSJ></Row>";
		//rowStr = "<Row><CCRD>出入库单</CCRD><ID>序号</ID><IKWBM>库位编码</IKWBM><CKW>库位名称</CKW><IKCBM>库存编码</IKCBM><CYPBM>药品编码</CYPBM><CYPMC>药品名称</CYPMC><CYPBZ>物品包装</CYPBZ><CYPGG>规格</CYPGG><DYXQ>有效期</DYXQ><CYFDW>药房单位</CYFDW><CYKDW>库存单位</CYKDW><IHSXS>换算系数</IHSXS><CPH>批号</CPH><NSL>数量</NSL><CFS>出入方式</CFS><DSJ>出入时间</DSJ><IDFKW>对方库位编码</IDFKW><CDFKW>对方库位名称/供货单位</CDFKW><MPFJ>批发价</MPFJ><MGRJ>购入价</MGRJ><MLSJ>零售价</MLSJ><MTYJ>退货价</MTYJ><CDJH>单据号</CDJH><CSCCJ>生产厂家</CSCCJ><CDWMC>供货单位</CDWMC><IPCBH>供货单位</IPCBH><NKCL>出入库后库存量</NKCL><CCZY>经手人/经发人</CCZY><CBZ>备注</CBZ><CPZWH>批准文号</CPZWH><BFK>付款标记 0 1</BFK><CJSR>结算人</CJSR><DJSSJ>结算时间</DJSSJ></Row>";
		//rowStr = "<Row><CCRD>出入库单</CCRD><ID>序号</ID><IKWBM>库位编码</IKWBM><CKW>库位名称</CKW><IKCBM>库存编码</IKCBM><CYPBM>药品编码</CYPBM><CYPMC>药品名称</CYPMC><CYPBZ>物品包装</CYPBZ><CYPGG>规格</CYPGG><DYXQ>有效期</DYXQ><CYFDW>药房单位</CYFDW><CYKDW>库存单位</CYKDW><IHSXS>换算系数</IHSXS><CPH>批号</CPH><NSL>数量</NSL><CFS>出入方式</CFS><DSJ>出入时间</DSJ><IDFKW>对方库位编码</IDFKW><CDFKW>对方库位名称/供货单位</CDFKW><MPFJ>批发价</MPFJ><MGRJ>购入价</MGRJ><MLSJ>零售价</MLSJ><MTYJ>退货价</MTYJ><CDJH>单据号</CDJH><CSCCJ>生产厂家</CSCCJ><CDWMC>供货单位</CDWMC><IPCBH>供货单位</IPCBH><NKCL>出入库后库存量</NKCL><CCZY>经手人/经发人</CCZY><CBZ>备注</CBZ><CPZWH>批准文号</CPZWH><BFK>付款标记 0 1</BFK><CJSR>结算人</CJSR><DJSSJ>结算时间</DJSSJ></Row>";
		//eleToAttr(rowStr);
		
		// rowStr = "CCRD|出入库单号-IXH|序号-IKWBM|库位编码-CKWMC|库位名称-IPCBH|盘存编号-IKCBM|库存编码-CWPBM|物品编码-CWPMC|物品名称-CWPBZ|物品包装-CXH|型号-DYXQ|有效期-CZXDW|最小单位-CKCDW|库存单位-IHSXS|换算系数-CPH|批号-NSL|数量-CCRFS|出入方式-DCRSJ|出入时间-IDFKWBM|对方库位编码-CDFKWMC|对方库位名称-MPFJ|批发价-MGRJ|购入价-MLSJ|零售价-MTYJ|退物价-CDJH|单据号-CSCCJ|生产厂家-CCD|产地-NKCL|库存量（出入库后库存量）-CJSR|经手人-CBGR|保管人-CCZY|操作员-CSYDD|使用地址-CBZ|备注-ITJFS|调价方式-IDFKCBM|对方库位编码-BFK|是否付款-CHW|货位-CSHR|审核人-DSHRQ|审核日期-CFPH|发票号-CJSH|结算号-CJSCZY|结算员-DJSRQ|结算日期";
		// convertTable(rowStr, 0);
		
		// rowStr = "CPSDBH|配送单编码-CGYSBM|供应商编码-CGYSMC|供应商名称-IPSDFL|配送单类别-DPSSJ|配送时间-CTM|配送单条码-BDY|条码是否打印标志-BYYSMBZ|医院扫码标志-IZT|状态-CWLGSMC|物流公司名称-DFHSJ|发货时间-CYYLXRXM|医院联系人姓名-CYYLXRDH|医院联系人电话-CJLSJ|记录时间-CJLRGH|记录人工号-CJLRXM|记录人姓名-CBZ|备注";
		// rowStr = "CPSDBH|配送单编码-CYPBM|药品编码-CYPPH|药品批次表编码-CYPPCH|药品批号-CYPMC|药品名称-CYPGG|药品规格-NJYL|进药量-CTM|条码-CYDY|已打印情况-CYSM|已扫描打码情况-CYFDW|药房单位-NPSSL|配送数量-MPSJG|配送价格-CBH|编码-CYPBM|药品编码-CYPMC|药品名称-CYPGG|药品规格-NJYL|进药量-CYPJX|药品剂型-CYFDW|药房单位-IKWBM|库位编码-CKWMC|库位名称-DSJ|时间-CCZY|操作员-ISX|上限-IXX|下限-NXHL|消耗量-NKCL|库存量-CJYDW|进药单位-CSCCJ|生产厂家-MGRJ|购入价-MGJJE|购价金额-MLSJ|零售价-MLJJE|零售金额-CGHDW|供货单位-CYPBZ|药品备注-DJLSJ|记录时间-CJLRGH|记录人工号-CJLRXM|记录人姓名-CGYSBM|供应商编码-CGYSMC|供应商名称-CBZ|备注";
		//rowStr = "CPSDBH|配送单编码-CWPBM|物品编码-CWPPH|物品批次表编码-CWPPCH|批次号-CWPMC|物品名称-CWPBZ|物品包装-NCGSL|采购数量-CTM|条码-CYDY|已打印情况-CYSM|已扫描打码情况-CKCDW|库存单位-CZXDW|最小单位-IHSXS|换算系数-NPSSL|配送数量-MPSJG|配送价格-CCGD|采购单号-IXH|序号-IKWBM|库位编码-CKWMC|库位名称-IPCBH|盘存编号-IKCBM|库存编码-CXH|型号-MPFJ|批发价-MGRJ|购入价-MLSJ|零售价-CSCCJ|生产厂家-CCD|产地-NKCL|库存量-CJYDW|进货单位-DSJ|计划申请时间 -CCZY|操作员-CSQR|申请人-CSQKS|申请科室-DJLSJ|记录时间-CJLRGH|记录人工号-CJLRXM|记录人姓名-CGYSBM|供应商编码-CGYSMC|供应商名称-CBZ|备注";
		//convertTable(rowStr, 1);
		testWebService();
		System.out.println(rowStr);
	}
	
	private static void testWebService() {
		String actionName = "Execute";
		String code = "QryWPXX"; // 物品信息
		String inVal = "<MSG><ASK><PAR>"
				+ "<IKWBM>2</IKWBM><CBM>10002</CBM><CPYM></CPYM><CWPTXM></CWPTXM>"
				+ "<CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM>"
				+ "</PAR></ASK></MSG>";
		
		code = "QryWPZSMXJL"; // 物资追溯查询
		inVal = "<MSG><ASK><PAR>"
				+ "<BEGINDATE>2017-01-01 01:00:00</BEGINDATE><ENDDATE>2017-05-13 16:48:59</ENDDATE>"
				+ "<IKWBM>2</IKWBM><CNBTXM/><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM>"
				+ "</PAR></ASK></MSG>"; 
		
		code = "WPLWCK"; // 物资出库
		inVal = "<MSG><ASK><PAR>"
				+ "<Rows><ROW><IKWBM>2</IKWBM><IDFKWBM>1</IDFKWBM><CDFKWMC>内一科</CDFKWMC><IXH>1</IXH><IKCBM>52</IKCBM>"
				+ "<NSL>1</NSL><MPFJ>90</MPFJ><MGRJ>95</MGRJ><MLSJ>99.9</MLSJ><DCRSJ>2017-05-13 15:05:13</DCRSJ>"
				+ "<DCZSJ>2017-05-13 15:05:01</DCZSJ><CJSR>刘天琪</CJSR><CBGR>施良锋</CBGR><CCZYGH>M63</CCZYGH>"
				+ "<CCZYXM>管理员</CCZYXM><CPCTXM/><CNBTXM/></ROW></Rows>"
				+ "</PAR></ASK></MSG>"; 

		code = "WPTHCK"; // 物资退货
		inVal = "<MSG><ASK><PAR>"
				+ "<Rows><ROW><CDFKWMC>上海圣荷西医疗用品有限公司</CDFKWMC><IKWBM>2</IKWBM><IXH>1</IXH><IKCBM>52</IKCBM>"
				+ "<NSL>1</NSL><MPFJ>90</MPFJ><MGRJ>95</MGRJ><MLSJ>99</MLSJ><MTYJ>90</MTYJ>"
				+ "<DCRSJ>2017-05-13 15:05:13</DCRSJ><DCZSJ>2017-05-13 15:05:01</DCZSJ><CJSR>刘天琪</CJSR><CBGR>施良锋</CBGR>"
				+ "<CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM><CPCTXM/><CNBTXM/></ROW></Rows>"
				+ "</PAR></ASK></MSG>"; 

		code = "QryWPPCXX"; // 批次信息
		inVal = "<MSG><ASK><PAR>"
				+ "<IKWBM>7</IKWBM><IKCBM></IKCBM><CBM></CBM><CPYM></CPYM><CWPTXM></CWPTXM><CPCTXM></CPCTXM><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM>"
				+ "</PAR></ASK></MSG>";
		
		code = "QryWZCRKSQD"; // 出入库申请单明细查询
		inVal = "<MSG><ASK><PAR>"
				+ "<CALLCODE>2</CALLCODE><IKWBM>7</IKWBM><CCRD>2012R000017</CCRD><IDFKWBM></IDFKWBM><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM>"
				+ "</PAR></ASK></MSG>";
		
		code = "QryWZCRKSQDHZ"; // 出入库申请单汇总查询
		inVal = "<MSG><ASK><PAR><CALLCODE>2</CALLCODE><IKWBM>7</IKWBM><IDFKWBM></IDFKWBM><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM></PAR></ASK></MSG>";
		
		code = "GZHCSSTZJL"; // 高值耗材手术使用记账（物资病人使用） 
		// inVal = "<MSG><ASK><PAR><CKSMC>内三病区</CKSMC><CKWMC>内一科</CKWMC><CCZYXM>管理员</CCZYXM><CJZBH>16000042</CJZBH><CYSBM/><CYSMC>马国威</CYSMC><CSSBH/><DCZSJ>2017-05-26 16:46</DCZSJ><IBRLX>2</IBRLX><CCZYGH>M63</CCZYGH><CKSBM>3</CKSBM><IKWBM>7</IKWBM><Rows><Row IXH=\"1\" CPCTXM=\"201701240003\"/><Row IXH=\"2\" CPCTXM=\"201701240001\" /></Rows></PAR></ASK></MSG>";
		inVal = "<MSG><ASK><PAR><CKSMC>内三病区</CKSMC><CKWMC>内一科</CKWMC><CCZYXM>管理员</CCZYXM><CJZBH>16000042</CJZBH><CYSBM/><CSSBH/><CYSMC>马国威</CYSMC><DCZSJ>2017-05-27 10:41</DCZSJ><IBRLX>0</IBRLX><CCZYGH>M63</CCZYGH><IKWBM>7</IKWBM><CKSBM>3</CKSBM><Rows><Row><IXH>1</IXH><CPCTXM>201701240003</CPCTXM></Row><Row><IXH>2</IXH><CPCTXM>201701240001</CPCTXM></Row></Rows></PAR></ASK></MSG>";
		/**
		 * 设备管理
		 *
		code = "QrySBXX";  // 设备信息
		inVal = "<MSG><ASK><PAR><IKWBM>4</IKWBM><CSBBH></CSBBH><CSYKS></CSYKS></PAR></ASK></MSG>";
		
		code = "QrySBWX";  // 设备维修查询
		inVal = "<MSG><ASK><PAR>"
				+ "<DBEGIN>2016-01-01 13:14:00</DBEGIN>"
				+ "<DEND>2017-05-14 13:14:48</DEND>"
				+ "<IKWBM>4</IKWBM>"
				+ "<CSBBH></CSBBH>"
				+ "<CWXDH></CWXDH>"
				+ "<ICXLX>1</ICXLX>"
				+ "</PAR></ASK></MSG>";
		/*
		// 供应室
		code = "QryGYSBWPXX"; // 获取包内物品信息
		inVal = "<MSG><MSH><MSH.1>HT10003</MSH.1><MSH.2>packInfoQuery</MSH.2></MSH><ASK><PAR><CBTM></CBTM><CBBM></CBBM><CBMC></CBMC><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM></PAR></ASK></MSG>";
		
		code = "GYSBWPXD"; // 医用包消毒
		inVal = "<MSG><ASK><PAR><NXDWD>0.0</NXDWD><DXDRQ>2017-05-24 16:16</DXDRQ><NZGGC>1</NZGGC><NMJSC>0.0</NMJSC><CBTM>1705095845</CBTM><CCZYGH>M63</CCZYGH><CXDRQ/><CXDYBM/><CCZYXM>管理员</CCZYXM><NXDYL>0.0</NXDYL><CQXFS/><NXDGC>1</NXDGC><CXDY>管理员</CXDY><CXDFS/><CZGGH/><CXDGH/><DGXRQ>2017-05-31 16:16</DGXRQ></PAR></ASK></MSG>";
		
		code = "QryZYBRJBXX"; // 获取在院病人信息
		inVal = "<MSG><ASK><PAR><CZYH>15000003123</CZYH><CCZYGH>M6</CCZYGH><CCZYXM>管理员</CCZYXM></PAR></ASK></MSG>";
		
		code = "QryMZBRJBXX"; // 获取门诊病人信息
		inVal = "<MSG><ASK><PAR><CMZH>1612000003</CMZH><DGHKSRQ>2015-12-27</DGHKSRQ><DGHJSRQ>2016-12-31</DGHJSRQ><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM></PAR></ASK></MSG>";
		
		*/
		
		/**
		 * 药品管理
		 */
		/*
		code = "QryYPCGJHSQMX"; // 采购申请单信息查询
		inVal = "<MSG><ASK><PAR><BEGINDATE>2016-05-27 10:09:59</BEGINDATE><ENDDATE>2017-05-27 10:09:59</ENDDATE><IKWBM>1</IKWBM><CCGD></CCGD><CJYDW></CJYDW><CCZYGH>M63</CCZYGH><CCZYXM>操作员姓名</CCZYXM></PAR></ASK></MSG>";
		
		code = "QryYPXX"; // 药品信息
		inVal = "<MSG><ASK><PAR><CBM></CBM><CWPTXM></CWPTXM><CCZYGH>M63</CCZYGH><CCZYXM>操作员姓名</CCZYXM></PAR></ASK></MSG>";
		
		code = "QryYPCRKMX"; // 查询药品出入库明细
		inVal = "<MSG><ASK><PAR><BEGINDATE>2016-05-27 17:58:20</BEGINDATE><ENDDATE>2017-05-27 17:58:20</ENDDATE><IKWBM>1</IKWBM><CCRD></CCRD><IDFKWBM></IDFKWBM><CCZYGH>M63</CCZYGH><CCZYXM>操作员姓名</CCZYXM></PAR></ASK></MSG>";
		
		code = "QryCGPSD"; // 采购配送单查询
		inVal = "<MSG><ASK><PAR><CTM>123</CTM><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM></PAR></ASK></MSG>";
*/
		code = "QryCGPSDBZ"; // 采购配送大包装查询
		inVal = "<MSG><ASK><PAR><CTM>123</CTM><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM></PAR></ASK></MSG>";
		
		code = "QryYPPSMX"; //  药品配送明细查询 
		inVal = "<MSG><ASK><PAR><CPSDBH>17052400</CPSDBH><CTM></CTM><CCZYGH>操作员工号</CCZYGH><CCZYXM>操作员姓名</CCZYXM></PAR></ASK></MSG>";

		code = "QryYPCRCKQRHZ";
		inVal = "<MSG><ASK><PAR><BEGINDATE>2016-05-27 17:58:20</BEGINDATE><ENDDATE>2017-05-27 17:58:20</ENDDATE><IKWBM>1</IKWBM><CCRD/><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM></PAR></ASK></MSG>";
		
		/**
		 * 试剂
		 **/
		code = "ReagConsumConfirm"; // 试剂消耗 通过
		inVal = "<MSG><ASK><PAR><Rows><Row><CSBTM>1001</CSBTM><CSJTM>123456</CSJTM><NSL>1</NSL></Row></Rows><CCZYGH>M63</CCZYGH><CCZYXM>操作员姓名</CCZYXM></PAR></ASK></MSG>";

		code = "GetReagConInfo"; // 查询试剂消耗信息 通过 NO-DATA
		inVal = "<MSG><ASK><PAR><CSBBH/><DXHRQEnd>2017-06-30</DXHRQEnd><CCZYXM>管理员</CCZYXM><CSJTM/><DXHRQBegin>2016-01-01</DXHRQBegin><CSJMC/><CSBMC/><CCZYGH>M63</CCZYGH></PAR></ASK></MSG>";

		code = "GetReagStockInfo"; // 查询试剂库存信息 通过
		inVal = "<MSG><ASK><PAR><CCZYXM>管理员</CCZYXM><CCZYGH>M63</CCZYGH><CSJMC/></PAR></ASK></MSG>";

		code = "ReagOutConfirm"; // 试剂出库 通过
		inVal = "<MSG><ASK><PAR><CCZYXM>管理员</CCZYXM><CCZYGH>M63</CCZYGH><BZC>0</BZC><Rows><Row><CLQBM>生化组</CLQBM><index>1</index><CLQBMBM>1</CLQBMBM><CSJMC>总胆汁酸</CSJMC><NSL>2</NSL><CSJBM>0008</CSJBM><CSJTM>123456</CSJTM></Row></Rows></PAR></ASK></MSG>";

		code = "ReagInConfirm"; // 试剂入库 通过
		inVal = "<MSG><ASK><PAR><CCZYGH>M63</CCZYGH><CCZYXM>操作员</CCZYXM><Rows><Row><CCRD>TEST11111111</CCRD> <IXH>1</IXH> <IKWBM>1</IKWBM> <CKWMC>库位名称</CKWMC> <IPCBH>1</IPCBH> <IKCBM>1</IKCBM><CWPBM>物品编码</CWPBM><CWPMC>物品名称</CWPMC> <CWPBZ>物品包装</CWPBZ> <CXH>型号</CXH><DYXQ>2017-05-28 15:35:24</DYXQ> <CZXDW>最小单位</CZXDW> <CKCDW>库存单位</CKCDW><IHSXS>1</IHSXS> <CPH>批号</CPH> <NSL>1.0</NSL><CCRFS>出入方式</CCRFS><DCRSJ>2017-05-28 15:35:24</DCRSJ> <IDFKWBM>2</IDFKWBM><CDFKWMC>对方库位名称</CDFKWMC><MPFJ>99.0</MPFJ> <MGRJ>99.0</MGRJ> <MLSJ>99.0</MLSJ> <MTYJ>99.0</MTYJ> <CDJH>单据号</CDJH> <CSCCJ>生产厂家</CSCCJ><CCD>产地</CCD> <NKCL>9</NKCL><CJSR>经手人</CJSR><CBGR>保管人</CBGR> <CCZY>操作员</CCZY> <CSYDD>使用地址</CSYDD> <CBZ>备注</CBZ><ITJFS>1</ITJFS><IDFKCBM>1</IDFKCBM><BFK>1</BFK><CHW>货位</CHW> <CSHR>审核人</CSHR><DSHRQ>2017-05-28 15:35:24</DSHRQ><CFPH>发票号</CFPH> <CJSH>结算号</CJSH><CJSCZY>结算员</CJSCZY><DJSRQ>2017-05-28 15:35:24</DJSRQ><CPCTXM>1111111|2222222</CPCTXM><CNBTXM>1111111|2222222</CNBTXM></Row></Rows></PAR></ASK></MSG>";
		inVal = "<MSG><MSH><MSH.1>HT10005</MSH.1><MSH.2>reagInConfirm</MSH.2></MSH><ASK><PAR><CCZYXM>管理员</CCZYXM><CCZYGH>M63</CCZYGH><RowCount>2</RowCount><Rows><Row><CYSCZY/><ITK/><CHGZBH/><CCZYGH>1319</CCZYGH><CCWH/><DCRSJ>2017-05-31 09:47:07</DCRSJ><DYXQ>1899-12-30 00:00:00</DYXQ><IPCBH>1</IPCBH><CKL>1</CKL><CBRXM/><LWSQSHR/><IDFKWBM>1</IDFKWBM><NSL>1</NSL><CXH/><CJSR>管理员</CJSR><CZCMC/><CWPBZ>1套/套</CWPBZ><CGG/><IKCBM>1248</IKCBM><MLSJ>598.5</MLSJ><BFK>0</BFK><LWSQSHRGH/><CCZYXM>管理员</CCZYXM><CCRD>2017R000005</CCRD><CCRFS>联网入库</CCRFS><CSHR/><CBGR>付菊芳</CBGR><NKCL>0</NKCL><IDFKCBM>988</IDFKCBM><CSYDD/><DJSRQ>1899-12-30 00:00:00</DJSRQ><CJSCZY/><IHSXS>1</IHSXS><IXH>1</IXH><CJSH/><CDFKWMC>医用物资库</CDFKWMC><CPH/><NTKL/><KSFHC/><CWPBM>100518</CWPBM><CFPH/><MPFJ>0</MPFJ><IDYCS/><DLWSQSHRQ>1899-12-30 00:00:00</DLWSQSHRQ><CPZWH/><IKWBM>27</IKWBM><ITJFS>3</ITJFS><CBZ4/><CKCDW>套</CKCDW><CWXR/><CCD>国药集团河北医疗器械有限公司</CCD><CSCCJ>廊坊市爱尔血液</CSCCJ><CYCRDMX/><CZXDW>套</CZXDW><ISMBJ>1</ISMBJ><CDJH>2017C000203</CDJH><CZYH/><CWPCZ/><CBZ3/><CWZFL/><CMXBZ/><IYS/><CKWMC>检验科</CKWMC><DYSSJ>1899-12-30 00:00:00</DYSSJ><DSHRQ>1899-12-30 00:00:00</DSHRQ><CZCLB/><CWPMC>一次性使用血液灌流器</CWPMC><CHW/><CBZ/><MTYJ>0</MTYJ><MGRJ>570</MGRJ></Row><Row><CYSCZY/><ITK/><CHGZBH/><CCZYGH>1319</CCZYGH><CCWH/><DCRSJ>2017-05-31 09:47:07</DCRSJ><DYXQ>1899-12-30 00:00:00</DYXQ><IPCBH>1</IPCBH><CKL>1</CKL><CBRXM/><LWSQSHR/><IDFKWBM>1</IDFKWBM><NSL>1</NSL><CXH/><CJSR>管理员</CJSR><CZCMC/><CWPBZ>1套/套</CWPBZ><CGG/><IKCBM>56</IKCBM><MLSJ>1932</MLSJ><BFK>0</BFK><LWSQSHRGH/><CCZYXM>管理员</CCZYXM><CCRD>2017R000005</CCRD><CCRFS>联网入库</CCRFS><CSHR/><CBGR>付菊芳</CBGR><NKCL>0</NKCL><IDFKCBM>56</IDFKCBM><CSYDD/><DJSRQ>1899-12-30 00:00:00</DJSRQ><CJSCZY/><IHSXS>1</IHSXS><IXH>2</IXH><CJSH/><CDFKWMC>医用物资库</CDFKWMC><CPH/><NTKL/><KSFHC/><CWPBM>100213</CWPBM><CFPH/><MPFJ>0</MPFJ><IDYCS/><DLWSQSHRQ>1899-12-30 00:00:00</DLWSQSHRQ><CPZWH/><IKWBM>27</IKWBM><ITJFS>3</ITJFS><CBZ4/><CKCDW>套</CKCDW><CWXR/><CCD/><CSCCJ>张家港</CSCCJ><CYCRDMX/><CZXDW>套</CZXDW><ISMBJ>1</ISMBJ><CDJH>2017C000203</CDJH><CZYH/><CWPCZ/><CBZ3/><CWZFL/><CMXBZ/><IYS/><CKWMC>检验科</CKWMC><DYSSJ>1899-12-30 00:00:00</DYSSJ><DSHRQ>1899-12-30 00:00:00</DSHRQ><CZCLB/><CWPMC>高尔夫钢板</CWPMC><CHW/><CBZ/><MTYJ>0</MTYJ><MGRJ>1840</MGRJ></Row></Rows></PAR></ASK></MSG>";
		inVal = "<MSG><ASK><PAR><CCZYXM>管理员</CCZYXM><CCZYGH>M63</CCZYGH><Rows><Row><CNBTXM>123</CNBTXM><CXSDH></CXSDH><CYSCZY></CYSCZY><ITK></ITK><CHGZBH></CHGZBH><CSYKS></CSYKS><CCZYGH>1319</CCZYGH><CKY></CKY><CCWH></CCWH><DCRSJ>2017-06-01 09:11:47</DCRSJ><DYXQ>1899-12-30 00:00:00</DYXQ><IPCBH>1</IPCBH><CKL>1</CKL><CBRXM></CBRXM><LWSQSHR></LWSQSHR><IDFKWBM>1</IDFKWBM><NSL>1</NSL><CXH></CXH><CJSR>管理员</CJSR><CZCMC></CZCMC><CWPBZ>1套/套</CWPBZ><CGG></CGG><IKCBM>1248</IKCBM><MLSJ>598.5</MLSJ><BFK>0</BFK><LWSQSHRGH></LWSQSHRGH><CCZYXM>管理员</CCZYXM><CCRD>2017R000006</CCRD><CCRFS>联网入库</CCRFS><CSHR></CSHR><CBGR>付菊芳</CBGR><NKCL>0</NKCL><IDFKCBM>988</IDFKCBM><CSYDD></CSYDD><DJSRQ>1899-12-30 00:00:00</DJSRQ><CJSCZY></CJSCZY><IHSXS>1</IHSXS><IXH>1</IXH><CJSH></CJSH><CDFKWMC>医用物资库</CDFKWMC><CPH></CPH><NTKL></NTKL><KSFHC></KSFHC><CWPBM>100518</CWPBM><CFPH></CFPH><MPFJ>0</MPFJ><IDYCS></IDYCS><DLWSQSHRQ>1899-12-30 00:00:00</DLWSQSHRQ><DKPRQ>1899-12-30 00:00:00</DKPRQ><CPZWH></CPZWH><IKWBM>27</IKWBM><ITJFS>3</ITJFS><CBZ4></CBZ4><CKCDW>套</CKCDW><DXSRQ>1899-12-30 00:00:00</DXSRQ><CWXR></CWXR><CCD>国药集团河北医疗器械有限公司</CCD><CSCCJ>廊坊市爱尔血液</CSCCJ><CYCRDMX></CYCRDMX><CZXDW>套</CZXDW><ISMBJ>1</ISMBJ><CDJH>2017C000204</CDJH><CZYH></CZYH><CWPCZ></CWPCZ><CBZ3></CBZ3><CWZFL></CWZFL><CMXBZ></CMXBZ><IYS></IYS><CKWMC>检验科</CKWMC><DYSSJ>1899-12-30 00:00:00</DYSSJ><DSHRQ>1899-12-30 00:00:00</DSHRQ><CZCLB></CZCLB><CWPMC>一次性使用血液灌流器</CWPMC><CHW></CHW><CBZ></CBZ><MTYJ>0</MTYJ><MGRJ>570</MGRJ></Row></Rows></PAR></ASK></MSG>";
		/**/
		code = "GetReagInfo"; // 获取试剂信息 通过
		inVal = "<MSG><MSH><MSH.1>HT10005</MSH.1><MSH.2>getReagInfo</MSH.2></MSH><ASK><PAR><CCZYXM>管理员</CCZYXM><CCZYGH>M63</CCZYGH><CSJTM>123456</CSJTM></PAR></ASK></MSG>";
		
		//HttpClientManager.setPostUrl("http://192.168.200.24:4567/webservice1.asmx"); // 供应室
		//HttpClientManager.setPostUrl("http://192.168.200.24:4568/webservice.asmx"); // 设备
		//HttpClientManager.setPostUrl("http://192.168.200.24:4569/webservice1.asmx"); // 物资
		//HttpClientManager.setPostUrl("http://192.168.200.24:7890/webservice1.asmx"); // 药品
		HttpClientManager.setPostUrl("http://192.168.200.24:7899/webservice1.asmx"); // 试剂
		try {
			System.out.println(inVal);
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
	 * 子节点转属性
	 * @param row
	 */
	public static void eleToAttr(String row) {
		try {
			Element rowEle = DocumentHelper.parseText(row).getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> childs = rowEle.elements();
			for(Element childEle : childs) {
				rowEle.addAttribute(childEle.getName(), childEle.getText());
				rowEle.remove(childEle);
			}
			System.out.println(rowEle.asXML());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 属性转子节点
	 * @param row
	 */
	public static void attrToEle(String row) {
		try {
			Element rowEle = DocumentHelper.parseText(row).getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> childs = rowEle.elements();
			for(Element childEle : childs) {
				rowEle.addAttribute(childEle.getName(), childEle.getText());
				rowEle.remove(childEle);
			}
			System.out.println(rowEle.asXML());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static void convertTable(String tableStr, int type) {
		String[] strs = tableStr.split("-");
		// System.out.println(Arrays.toString(strs));
		if(type == 0) {
			for (String s : strs) {
				System.out.println("row.addAttribute(\"" + s.split("\\|")[0] + "\", \"" + s.split("\\|")[1] + "\");");
			}
		} else {
			try {
				Element ele = DocumentHelper.parseText("<Row/>").getRootElement();
				for (String s : strs) {
					ele.addAttribute(s.split("\\|")[0], s.split("\\|")[1]);
				}
				System.out.println(ele.asXML());
			} catch (DocumentException e) {
				e.printStackTrace();
			}
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
