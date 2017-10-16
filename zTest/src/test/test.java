package test;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FilenameFilter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringEscapeUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class test {

	public static void main(String[] args) {
        String rowStr = "";
		try {
		    rowStr = "10:56";
            float strToFloat = Float.valueOf(rowStr);  //Float.parseFloat(rowStr);
            System.out.println(strToFloat);
        } catch (NumberFormatException e1) {
        }
		// rowStr = "<Row CSYLSH=\"1605000008\" IXH=\"1\" CZYH=\"16050726\"
		// CXM=\"何开云\" CKSBM=\"\" CKSMC=\"新院手足外科\" CWPBM=\"00151\"
		// CWPMC=\"大清创(or)\" CXDDH=\"1605000163\" CBLSH=\"1605000675\"
		// CBTM=\"1605000675\" DXDRQ=\"2016-05-09 15:32:24\" DYXQ=\"2016-05-16
		// 15:32:24\" DSYRQ=\"2016-05-16 11:30:21\" NSL=\"1\" MDJ=\"0\" />";
		// rowStr = "<Row CKSBM=\"W50\" CKSMC=\"六病区\" CWPBM=\"0034\" CBLSH=\"\"
		// CBTM=\"B\" CMC=\"洁牙机\" CLB=\"可循环使用\" CXH=\"\" NSL=\"1\"
		// MDJ=\"0.0000\" DYXQ=\"2016-08-18 11:06:58\" CXDDH=\"1608000037\"
		// DXDRQ=\"2018-08-18 11:06:58\" CDW=\"套\" />";
		// rowStr = "<Row CSBBH=\"设备编号\" CSBBM=\"设备编码\" CSBMC=\"设备名称\"
		// CSBXH=\"设备型号\" CSBDW=\"设备单位\" CPYM=\"拼音码\" CGZBH=\"固资编号\"
		// CGZFL=\"固资分类\" CSBFL=\"设备分类\" CGLFS=\"管理方式\" NSL=\"数量\"
		// DCCRQ=\"出厂日期\" CCCBH=\"出厂编号\" DGRRQ=\"购入日期\" CSCCJ=\"生产厂家\"
		// CGB=\"国别\" CGHS=\"供货商\" CSBWZ=\"设备位置\" CWZBH=\"(设备所在)位置编号\"
		// CBGR=\"保管人\" CPP=\"品牌\" CSYKS=\"使用科室\" DRKSJ=\"入库时间\" DSYRQ=\"使用日期\"
		// CKY=\"款源\" MSBYZ=\"设备原值\" MSBJZ=\"设备净值\" NSYNX=\"使用年限\"
		// MYTZJ=\"已提折旧\" CSYKSBM=\"使用科室编码\" CGZFLBM=\"固资分类编码\"
		// CSBFLBM=\"设备分类编码\" />";
		// convertRow(rowStr, 1);
		// rowStr =
		// "<Row><CCRD>出入单号</CCRD><IKWBM>库位编码</IKWBM><IKCBM>库存编码</IKCBM><CBM>药品编码</CBM><CMC>药品名称</CMC><CYPTXM>药品条形码</CYPTXM><CGHDW>供货单位</CGHDW><IXH>序号</IXH><NSL>数量</NSL><CSCCJ>生产厂家</CSCCJ><DYXQ>有效期</DYXQ><MPFJ>批发价</MPFJ><MGRJ>购入价</MGRJ><MLSJ>零售价</MLSJ><CZBLX>招标类型</CZBLX><CYPGG>换算系数</CYPGG><CZXDW>最小单位</CZXDW><CYKDW>药库单位</CYKDW><IHSXS>药库发药换算系数</IHSXS><CPH>批号</CPH><DYXQ>有效期</DYXQ><MPCPFJ>批发价</MPCPFJ><MPCGRJ>购入价</MPCGRJ><MPCLSJ>零售价</MPCLSJ><CPZWH>批准文号</CPZWH><CPCGHDW>供货单位</CPCGHDW><CPCCCH>药库单位</CPCCCH><CCZYGH>操作员工号</CCZYGH><CCZYXM>操作员姓名</CCZYXM></Row>";
		// rowStr =
		// "<Row><IKCBM>库存编码</IKCBM><CWPBM>物品名称</CWPBM><IHSXS>换算系数</IHSXS><CWPBZ>物品包装</CWPBZ><CGB>国别</CGB><CCD>产地</CCD><CSCCJ>生产厂家</CSCCJ><CHW>存储货位</CHW><DYXQ>有效期</DYXQ><CKCDW>库存单位</CKCDW><CZXDW>最小单位</CZXDW><NSL>库存量</NSL><MPFJ>批发价</MPFJ><MGRJ>购入价</MGRJ><MLSJ>零售价</MLSJ><BENABLE>可用性0
		// 1 </BENABLE><CPH>批号</CPH><DXDRQ>消毒日期</DXDRQ><IXDZT>消毒状态 0 1
		// </IXDZT><CCJQK>抽检情况</CCJQK><IKCXH>库存序号</IKCXH><IDEFAULT>默认批次 0
		// 1</IDEFAULT><DCJRQ>创建日期</DCJRQ><CPZWH>批准文号</CPZWH><CBZ>备注</CBZ><MXJ>限价</MXJ><CPCTXM>批次条形码</CPCTXM><CNBTXM>内部条形码</CNBTXM></Row>";
		// rowStr =
		// "<Row><IKCBM>库存编码</IKCBM><CWPBM>物品编码</CWPBM><CWPMC>物品名称</CWPMC><CWPLB>物品类别</CWPLB><IGLFS>管理方式1
		// 材料 2 低值易耗 3
		// 高值耗材</IGLFS><IHSXS>换算系数</IHSXS><CWPBZ>物品包装</CWPBZ><CGB>国别</CGB><CCD>产地</CCD><CSCCJ>生产厂家</CSCCJ><CHW>存储货位</CHW><DYXQ>有效期</DYXQ><CKCDW>库存单位</CKCDW><CZXDW>最小单位</CZXDW><NSL>库存量</NSL><MPFJ>批发价</MPFJ><MGRJ>购入价</MGRJ><MLSJ>零售价</MLSJ><BENABLE>可用性0
		// 1 </BENABLE><CPH>批号</CPH><DXDRQ>消毒日期</DXDRQ><IXDZT>消毒状态 0 1
		// </IXDZT><CCJQK>抽检情况</CCJQK><IKCXH>库存序号</IKCXH><IDEFAULT>默认批次 0
		// 1</IDEFAULT><DCJRQ>创建日期</DCJRQ><CPZWH>批准文号</CPZWH><CBZ>备注</CBZ><MXJ>限价</MXJ></Row>";
		// rowStr =
		// "<Row><CCRD>出入库单</CCRD><IKWBM>库位编码</IKWBM><CKWMC>库位名称</CKWMC><CCRFS>出入方式</CCRFS><DCRSJ>出入时间</DCRSJ><IDFKWBM>对方库位编码</IDFKWBM><CDFKWMC>对方库位名称</CDFKWMC><CJSR>经手人/经发人</CJSR><CBGR>保管人/经领人</CBGR><CCZYGH>操作员工号</CCZYGH><CCZYXM>操作员姓名</CCZYXM><CBZ>备注</CBZ></Row>
		// ";
		// convertRow(rowStr, 0);

		// rowStr =
		// "<Row><CCRD>出入库单</CCRD><ID>序号</ID><IKWBM>库位编码</IKWBM><CKW>库位名称</CKW><IKCBM>库存编码</IKCBM><CYPBM>药品编码</CYPBM><CYPMC>药品名称</CYPMC><CYPBZ>物品包装</CYPBZ><CYPGG>规格</CYPGG><DYXQ>有效期</DYXQ><CYFDW>药房单位</CYFDW><CYKDW>库存单位</CYKDW><IHSXS>换算系数</IHSXS><CPH>批号</CPH><NSL>数量</NSL><CFS>出入方式</CFS><DSJ>出入时间</DSJ><IDFKW>对方库位编码</IDFKW><CDFKW>对方库位名称/供货单位</CDFKW><MPFJ>批发价</MPFJ><MGRJ>购入价</MGRJ><MLSJ>零售价</MLSJ><MTYJ>退货价</MTYJ><CDJH>单据号</CDJH><CSCCJ>生产厂家</CSCCJ><CDWMC>供货单位</CDWMC><IPCBH>供货单位</IPCBH><NKCL>出入库后库存量</NKCL><CCZY>经手人/经发人</CCZY><CBZ>备注</CBZ><CPZWH>批准文号</CPZWH><BFK>付款标记
		// 0 1</BFK><CJSR>结算人</CJSR><DJSSJ>结算时间</DJSSJ></Row>";
		// rowStr =
		// "<Row><CCRD>出入库单</CCRD><ID>序号</ID><IKWBM>库位编码</IKWBM><CKW>库位名称</CKW><IKCBM>库存编码</IKCBM><CYPBM>药品编码</CYPBM><CYPMC>药品名称</CYPMC><CYPBZ>物品包装</CYPBZ><CYPGG>规格</CYPGG><DYXQ>有效期</DYXQ><CYFDW>药房单位</CYFDW><CYKDW>库存单位</CYKDW><IHSXS>换算系数</IHSXS><CPH>批号</CPH><NSL>数量</NSL><CFS>出入方式</CFS><DSJ>出入时间</DSJ><IDFKW>对方库位编码</IDFKW><CDFKW>对方库位名称/供货单位</CDFKW><MPFJ>批发价</MPFJ><MGRJ>购入价</MGRJ><MLSJ>零售价</MLSJ><MTYJ>退货价</MTYJ><CDJH>单据号</CDJH><CSCCJ>生产厂家</CSCCJ><CDWMC>供货单位</CDWMC><IPCBH>供货单位</IPCBH><NKCL>出入库后库存量</NKCL><CCZY>经手人/经发人</CCZY><CBZ>备注</CBZ><CPZWH>批准文号</CPZWH><BFK>付款标记
		// 0 1</BFK><CJSR>结算人</CJSR><DJSSJ>结算时间</DJSSJ></Row>";
		// rowStr =
		// "<Row><CCRD>出入库单</CCRD><ID>序号</ID><IKWBM>库位编码</IKWBM><CKW>库位名称</CKW><IKCBM>库存编码</IKCBM><CYPBM>药品编码</CYPBM><CYPMC>药品名称</CYPMC><CYPBZ>物品包装</CYPBZ><CYPGG>规格</CYPGG><DYXQ>有效期</DYXQ><CYFDW>药房单位</CYFDW><CYKDW>库存单位</CYKDW><IHSXS>换算系数</IHSXS><CPH>批号</CPH><NSL>数量</NSL><CFS>出入方式</CFS><DSJ>出入时间</DSJ><IDFKW>对方库位编码</IDFKW><CDFKW>对方库位名称/供货单位</CDFKW><MPFJ>批发价</MPFJ><MGRJ>购入价</MGRJ><MLSJ>零售价</MLSJ><MTYJ>退货价</MTYJ><CDJH>单据号</CDJH><CSCCJ>生产厂家</CSCCJ><CDWMC>供货单位</CDWMC><IPCBH>供货单位</IPCBH><NKCL>出入库后库存量</NKCL><CCZY>经手人/经发人</CCZY><CBZ>备注</CBZ><CPZWH>批准文号</CPZWH><BFK>付款标记
		// 0 1</BFK><CJSR>结算人</CJSR><DJSSJ>结算时间</DJSSJ></Row>";
		// eleToAttr(rowStr);

		// rowStr =
		// "CCRD|出入库单号-IXH|序号-IKWBM|库位编码-CKWMC|库位名称-IPCBH|盘存编号-IKCBM|库存编码-CWPBM|物品编码-CWPMC|物品名称-CWPBZ|物品包装-CXH|型号-DYXQ|有效期-CZXDW|最小单位-CKCDW|库存单位-IHSXS|换算系数-CPH|批号-NSL|数量-CCRFS|出入方式-DCRSJ|出入时间-IDFKWBM|对方库位编码-CDFKWMC|对方库位名称-MPFJ|批发价-MGRJ|购入价-MLSJ|零售价-MTYJ|退物价-CDJH|单据号-CSCCJ|生产厂家-CCD|产地-NKCL|库存量（出入库后库存量）-CJSR|经手人-CBGR|保管人-CCZY|操作员-CSYDD|使用地址-CBZ|备注-ITJFS|调价方式-IDFKCBM|对方库位编码-BFK|是否付款-CHW|货位-CSHR|审核人-DSHRQ|审核日期-CFPH|发票号-CJSH|结算号-CJSCZY|结算员-DJSRQ|结算日期";
		// convertTable(rowStr, 0);

		// rowStr =
		// "CPSDBH|配送单编码-CGYSBM|供应商编码-CGYSMC|供应商名称-IPSDFL|配送单类别-DPSSJ|配送时间-CTM|配送单条码-BDY|条码是否打印标志-BYYSMBZ|医院扫码标志-IZT|状态-CWLGSMC|物流公司名称-DFHSJ|发货时间-CYYLXRXM|医院联系人姓名-CYYLXRDH|医院联系人电话-CJLSJ|记录时间-CJLRGH|记录人工号-CJLRXM|记录人姓名-CBZ|备注";
		// rowStr =
		// "CPSDBH|配送单编码-CYPBM|药品编码-CYPPH|药品批次表编码-CYPPCH|药品批号-CYPMC|药品名称-CYPGG|药品规格-NJYL|进药量-CTM|条码-CYDY|已打印情况-CYSM|已扫描打码情况-CYFDW|药房单位-NPSSL|配送数量-MPSJG|配送价格-CBH|编码-CYPBM|药品编码-CYPMC|药品名称-CYPGG|药品规格-NJYL|进药量-CYPJX|药品剂型-CYFDW|药房单位-IKWBM|库位编码-CKWMC|库位名称-DSJ|时间-CCZY|操作员-ISX|上限-IXX|下限-NXHL|消耗量-NKCL|库存量-CJYDW|进药单位-CSCCJ|生产厂家-MGRJ|购入价-MGJJE|购价金额-MLSJ|零售价-MLJJE|零售金额-CGHDW|供货单位-CYPBZ|药品备注-DJLSJ|记录时间-CJLRGH|记录人工号-CJLRXM|记录人姓名-CGYSBM|供应商编码-CGYSMC|供应商名称-CBZ|备注";
		// rowStr =
		// "CPSDBH|配送单编码-CWPBM|物品编码-CWPPH|物品批次表编码-CWPPCH|批次号-CWPMC|物品名称-CWPBZ|物品包装-NCGSL|采购数量-CTM|条码-CYDY|已打印情况-CYSM|已扫描打码情况-CKCDW|库存单位-CZXDW|最小单位-IHSXS|换算系数-NPSSL|配送数量-MPSJG|配送价格-CCGD|采购单号-IXH|序号-IKWBM|库位编码-CKWMC|库位名称-IPCBH|盘存编号-IKCBM|库存编码-CXH|型号-MPFJ|批发价-MGRJ|购入价-MLSJ|零售价-CSCCJ|生产厂家-CCD|产地-NKCL|库存量-CJYDW|进货单位-DSJ|计划申请时间
		// -CCZY|操作员-CSQR|申请人-CSQKS|申请科室-DJLSJ|记录时间-CJLRGH|记录人工号-CJLRXM|记录人姓名-CGYSBM|供应商编码-CGYSMC|供应商名称-CBZ|备注";
		// convertTable(rowStr, 1);
		// testWebService();
		// System.out.println(rowStr);
		// testSQL();
		// System.out.println(Arrays.toString("| | | ".split("\\|")));
		/*
		String[] params = { "0", "0" }; // [0]:是否写备注;[1]:是否写停止时间
		params[0] = "1"; // 默认不写备注
		params[1] = "2"; // 默认不写时间
		System.out.println(Arrays.toString(params));
		System.out.println("12345".replaceAll(".", "|$0"));
		*/
		String path = "E:\\Work\\YXKJ\\Project\\10、后勤条码管理系统\\msg\\";
		String lastFileName = getLastFile(path, ".xml");
		System.out.println("最新 XML ： " + lastFileName);
		/*
		path = "D:\\Development\\Tools\\Servers\\wildfly-8.2.1.Final\\standalone\\deployments\\YxHQTM.war\\resources\\apps\\";
		lastFileName = getLastFile(path, ".apk");
		System.out.println("最新 APK ： " + lastFileName);
		
		try {
		    Calendar c = Calendar.getInstance();
	        StringBuffer datetime = new StringBuffer(20);
	        datetime.append(c.get(Calendar.YEAR)).append("-")
	                .append(c.get(Calendar.MONTH) + 1).append("-")
	                .append(c.get(Calendar.DATE) + 3).append(" ")
	                .append("00:00:00");
	        
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datetime.toString());
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
            
        } catch (ParseException e) {
        }
        
		// strToTable("YXHIS..TBMZYSKQSJJL..医生考勤记录表", "[CPBBH]", "4", "[CPBBH], [DPBRQ], [CKSSJ], [CJSSJ], [DQDSJ], [DQTSJ], [IKSBM], [CKSMC], [IYSBM], [CYSMC], [IKQLX], [CKQLX], [BCD], [BZT], [ITZTZ], [ITZYS], [CTZYS], [CDLIP], [CZSBM], [CZSMC], [BXZJG], [CXZYY], [DXZSJ], [CXZRGH], [CXZRXM], [CCZYGH], [CCZYXM], [DTJSJ]:[varchar](20), [datetime], [time], [time], [datetime], [datetime], [int], [varchar](50), [int], [varchar](20), [tinyint], [varchar](10), [bit], [bit], [tinyint], [int], [varchar](20), [varchar](50), [varchar](10), [varchar](50), [bit], [varchar](200), [datetime], [varchar](10), [varchar](50), [varchar](10), [varchar](50), [datetime]:排班编号, 排班日期  , 开始时间, 结束时间, 签到时间, 签退时间, 科室编码, 科室名称, 医生编码, 医生名称, 考勤类型编码, 考勤类型名称, 迟到标识, 早退标识, 停诊替诊标识, 替诊医生编码, 替诊医生名称, 登录IP, 诊室编码, 诊室名称, 修正标识, 修正原因, 修正时间, 修正人工号, 修正人姓名, 操作员工号, 操作员姓名, 统计时间");
		// strToTable("YXHIS..TBYSKHSJPZB..医生考核时间配置表", "[ISJD], [IYSBM]", "", "[ISJD], [IYSBM], [CYSMC], [CKSSJ], [CJSSJ], [BSXSJ], [DSXSJ]:[tinyint], [int], [varchar](20), [time], [time], [bit], [datetime]:考勤时间段, 医生编码, 医生名称, 开始时间, 结束时间, 使用生效时间, 生效时间");
		// strToTable("YXHIS..TBTSKSKHPZB..特殊科室考核配置表", "[ISJD], [IKSBM]", "", "[ISJD], [IKSBM], [CKSMC], [BKH], [CKSSJ], [CJSSJ], [BSXSJ], [DSXSJ]:[tinyint], [int], [varchar](50), [bit], [time], [time], [bit], [datetime]:考勤时间段, 科室编码, 科室名称, 是否考核, 考核开始时间, 考核结束时间, 使用生效时间, 生效时间");
		// strToTable("YXHIS..TBYSDLDZPZB..医生登录地址配置表", "[CUID]", "", "[CUID], [CXM], [IYSBM], [CIP]:[varchar](20), [varchar](20), [int], [varchar](500):医生账号, 医生姓名, 医生编码, 可用IP");
		// strToTable("YXHIS..TBZSDLDZPZ..诊室登录地址配置表", "[CZSBM]", "", "[CZSBM], [CZSMC], [CIP]:[varchar](20), [varchar](50), [varchar](50):诊室编码, 诊室名称, IP地址");
		// strToTable("YXHIS..TBZDMZZXKS..门诊中心科室字典表", "[IZXBM]", "", "[IZXBM], [CZXMC], [CPYM], [CKSBM], [CKSMC], [CBZ], [BENABLE]:[int], [varchar](50), [varchar](50), [varchar](1000), [varchar](1000), [varchar](500), [bit]:中心编码, 中心名称, 拼音码, 科室编码, 科室名称, 备注, 是否可用");
		// getTables();
		*/
		System.out.println("1|2|3".replaceAll("\\|", ", "));
		String str = "`~!@#$%^&*()-_=+,.<>/?:;\\|[]{}\"''";
        System.out.println(str);
		str = StringEscapeUtils.escapeSql(str); // 防止 SQL 注入 -> .replaceAll("'", "''")
        System.out.println(str);
        /*
        testDate();
		testAutoTaskSQL();
		System.out.println(getMinDiff("08:00:00", "09:59:00"));
        System.out.println(getMinDiff("08:00:00", "08:01:00"));
        System.out.println((int) 80 / 60);
        System.out.println((int) 80 % 60);
        */
        // testAppendSQL();
		Math.random();
		Random rand = new Random();
		System.out.println(Math.abs(rand.nextInt()));
        System.out.println(rand.nextLong());
        System.out.println(System.currentTimeMillis()); // 毫秒数
        System.out.println(System.nanoTime()); // 纳秒级时间值
        Instant now = Instant.now();
        System.out.println(now);
        System.out.println(Instant.parse(now.toString()));

        /*
        String str = "1234567890qwertyuiop";
        str = str.substring(0, 10) + " - " + str.substring(10, str.length());
        System.out.println(str);
        
        str = "82.123";
        System.out.println(str.substring(str.indexOf(".") + 1, str.length()));
        str = str.substring(0, str.indexOf(".") + 3);
        System.out.println(str);
        */
        // testInetAddress();
        
        // System.out.println(Objects.equals("", null));
        
        // javaProperty();
        /*
		String[] strs = {"a", "A", "a", "A"};
		Arrays.sort(strs);
		System.out.println(Arrays.binarySearch(strs, "a"));
        */
		systemFonts("Lucida Console");
		
		// instanceofTest();

        try {
            URI uri = new URI("https://zixizixi.cn/test/?key=value");
            System.out.println(uri.getAuthority());
            System.out.println(uri.getRawAuthority());
            System.out.println(uri.toString());
            // System.out.println(uri.getFragment());
            System.out.println(uri.getScheme());
            System.out.println(uri.getSchemeSpecificPart());
            System.out.println(uri.getHost());
            // System.out.println(uri.getPort());
            System.out.println(uri.getPath());
            System.out.println(uri.getQuery());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
        System.out.println((String.valueOf((char) 127)).getBytes().length);
        System.out.println((char) 127);
        System.out.println(Integer.valueOf("zZA9-123".charAt(0)));
        System.out.println(new StringBuffer().toString() + "-");
        System.out.println(Float.valueOf(0).toString());
        String url = "/YxCheck/modules/sign/?CGH=1819";
        url = url.substring(url.indexOf("YxCheck/") + 7, url.length());
        System.out.println(url);
        System.out.println(nowStr("YYMMdd"));
        
        List<?> list = new ArrayList<String>();
        System.out.println("list.size : " + list.size());
        /*
        try {
            Document ele = DocumentHelper.parseText("<MSG><Rows><Row i=\"1\" /><Row i=\"2\" /><Row i=\"3\" /></Rows></MSG>");
            System.out.println(ele.asXML());
        } catch (DocumentException e) {
            e.printStackTrace();
        }*/
        System.out.println("|1|6|9|10|12|".indexOf("|12|"));
        System.out.println("|1|6|9|10|12|".indexOf("|13|"));
        System.out.println("|1|6|9|10|12|".indexOf(("|" + 1 + "|")));
	}
	
	public static void instanceofTest() {
	    try {
	        Object str = "<MSG><MSH></MSH></MSG>";
	        Object doc = DocumentHelper.parseText(str.toString());
            Object obj = ((Document)doc).getRootElement();
            System.out.println(str instanceof Document);
            System.out.println(doc instanceof Document);
            System.out.println(obj instanceof Document);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void javaProperty() {
        System.out.println(" --------------- javaProperty --------------- ");
        
        System.out.println(System.getProperty("java.vendor")); // 环境供应商 ('Oracle Corporation')
        System.out.println(System.getProperty("java.vendor.url")); // 供应商链接 ('http://java.oracle.com/')
        System.out.println(System.getProperty("java.vm.specification.version")); // 虚拟机规范版本 ('1.8')
        System.out.println(System.getProperty("java.vm.specification.vendor")); // 虚拟机规范供应商 ('Oracle Corporation')
        System.out.println(System.getProperty("java.vm.specification.name")); // 虚拟机规范名称 ('Java Virtual Machine Specification')
        System.out.println(System.getProperty("java.vm.version")); // 虚拟机实现版本 ('25.91-b15')
        System.out.println(System.getProperty("java.vm.vendor")); // 虚拟机实现供应商 ('Oracle Corporation')
        System.out.println(System.getProperty("java.vm.name")); // 虚拟机实现名称 ('Java HotSpot(TM) Client VM')
        System.out.println(System.getProperty("java.specification.version")); // 运行时环境规范版本 ('1.8')
        System.out.println(System.getProperty("java.specification.vendor")); // 运行时环境规范供应商 ('Oracle Corporation')
        System.out.println(System.getProperty("java.specification.name")); // 运行时环境规范名称 ('Java Platform API Specification')
        System.out.println(System.getProperty("java.class.version")); // 类格式版本号 ('52.0')
        System.out.println(System.getProperty("java.class.path")); // 类路径 (Build Path)
        System.out.println(System.getProperty("java.library.path")); // 加载库时搜索的路径列表（环境变量Path）
        System.out.println(System.getProperty("java.io.tmpdir")); // 默认临时文件路径 ('C:\Users\ADMINI~1\AppData\Local\Temp\')
        System.out.println(System.getProperty("java.ext.dirs")); // 一或多个扩展目录的路径 ('D:\Oracle\JDK\java32\jre\lib\ext')
        
        System.out.println(System.getProperty("file.separator", "")); //当前系统 文件分隔符('\')
        System.out.println(System.getProperty("path.separator", "")); // 当前系统路径分隔符(';')
        System.out.println(System.getProperty("line.separator", "")); // 当前系统行分隔符('/n')

        System.out.println(System.getProperty("user.name", "")); // 用户账户名称 ('Administrator')
        System.out.println(System.getProperty("user.home", "")); // 用户主目录 ('C:\Users\Administrator')
        System.out.println(System.getProperty("user.dir", "")); // 用户当前工作目录 ('E:\Work\Workspaces\Git\Projects\zTest')

        System.out.println(System.getProperty("java.home", "")); // Java安装目录 ('D:\Oracle\JDK\java32\jre')
        System.out.println(System.getProperty("java.version", "")); // Java版本 ('1.8.0_91')

        System.out.println(System.getProperty("os.name", "")); // 操作系统名称 ('Windows 10')
        System.out.println(System.getProperty("os.arch", "")); // 操作系统构架 ('x86')
        System.out.println(System.getProperty("os.version", "")); // 操作系统版本 ('10.0')
        

	}
	
	public static void testInetAddress() {
	    System.out.println(" --------------- testInetAddress --------------- ");
        try {
            InetAddress localHost = InetAddress.getLocalHost(); // 本机地址：主机名/IP
            System.out.println(localHost);
            System.out.println(localHost.getCanonicalHostName()); // 规范的主机名
            System.out.println(localHost.getHostAddress()); // 主机IP地址
            System.out.println(localHost.getHostName()); // 主机名
            
            NetworkInterface network = NetworkInterface.getByInetAddress(localHost);
            System.out.println(network.getDisplayName()); // 网卡驱动描述
            System.out.println(network.getName()); // 主机名
            
            StringBuffer strBuf = new StringBuffer();
            byte[] mac = network.getHardwareAddress();
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    strBuf.append("-");
                }
                String s = Integer.toHexString(mac[i] & 0xFF);
                strBuf.append(s.length() == 1 ? 0 + s : s);
            }
            System.out.println(strBuf.toString().toUpperCase()); // 物理地址
            /*
            Enumeration<InetAddress> enumAddr = network.getInetAddresses();
            while (enumAddr.hasMoreElements()) {
                InetAddress inetAddr = enumAddr.nextElement();
                System.out.println("getCanonicalHostName：" + inetAddr.getCanonicalHostName());
                System.out.println("getHostAddress：" + inetAddr.getHostAddress());
                System.out.println("getHostName：" + inetAddr.getHostName());
            }*/
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
	}
	
	public static void systemFonts(String fontName) {
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = e.getAvailableFontFamilyNames();
        if (isEmptyStr(fontName)) {
            for (String font : fonts) {
                System.out.println(font);
            }
        } else {
            Arrays.sort(fonts);
            int index = Arrays.binarySearch(fonts, fontName);
            if (index > -1) {
                System.out.println("找到系统字体：" + fonts[index]);
            } else {
                System.out.println("系统不存在字体：" + fontName);
            }
        }
	}
	
	public static void testDate() {
        System.out.println(nowStr("yyyy年MM月dd日 E"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1); // 昨天
        System.out.println(calendar.getTime().getTime());
        System.out.println(calendar.getTimeInMillis());
        String date = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTimeInMillis());
        System.out.println(date);
	}
	
	public static void getTables() {
        String select = "SELECT * FROM "; 
        String union = " UNION ALL "; 
        StringBuffer sTables = new StringBuffer(100);
        sTables.append("(");
         for(String tbName : new String[]{"T0", "T1"}) { 
             sTables.append(" UNION ALL SELECT * FROM ").append(tbName).append(" WITH(NOLOCK)"); 
         }
         String table = sTables.delete(1, 12).append(")").toString(); 
         System.out.println(select.length());
         System.out.println(union.length());
         System.out.println(table);
	}

	/**
	 * 获取目录下指定类型的最新文件
	 * @param path 文件所在目录
	 * @param extName 文件扩展名 .xml
	 */
	public static String getLastFile(String path, String extName) {
		String fileName = "";
		File file = new File(path);
		if (file.exists()) {
			// 获取 path 下所有符合条件的文件
			File files[] = file.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if(isEmptyStr(extName) || name.toLowerCase().endsWith(extName.toLowerCase())) {
					    return true;
					}
					return false;
				}
			});
			
			long[] lastTimes = new long[files.length];
			for (int i = 0; i < files.length; i++) {
				lastTimes[i] = files[i].lastModified();
			}
			
			int last = lastTimes.length - 1;
			lastTimes = quickSort(lastTimes, 0, last); // 快速排序
			System.out.println(new SimpleDateFormat("G yyyy-MM-dd HH:mm:ss.SSS E W F K L X Z").format(lastTimes[last]));
			
			for (int i = 0; i < lastTimes.length; i++) {
				if(files[i].lastModified() == lastTimes[last]) {
					fileName = files[i].getName();
					break;
				}
			}
		}
		return fileName;
	}

	public static long[] quickSort(long a[], int low, int high) {
		if (low < high) {
			int result = partition(a, low, high); // 中点
			quickSort(a, low, result - 1);
			quickSort(a, result + 1, high);
		}
		return a;
	}

	public static int partition(long a[], int low, int high) {
		long key = a[low];
		while (low < high) {
			while (low < high && a[high] >= key)
				high--;
			a[low] = a[high];

			while (low < high && a[low] <= key)
				low++;
			a[high] = a[low];
		}
		a[low] = key;
		return low;
	}

	public static void testSQL() {
		String[] fields = { "CYYZPH", "IXH", "CKSBM", "CKSMC", "CBQBM", "CBQMC", "CSFXMBM", "CSFXM", "CSTOPYY", "CYZNR",
				"BBYYZ", "ISQDZL", "CSQDBH", "IYZZXTJ" };

		StringBuffer cYSQL = new StringBuffer(100);
		cYSQL.append("SELECT ");
		for (int i = 0; i < fields.length; i++) {
			cYSQL.append(fields[i]);
			if (i < fields.length - 1) {
				cYSQL.append(", ");
			}
		}
		cYSQL.append(" FROM ").append("TBYZBYZYLBQ101").append(" WITH(NOLOCK) WHERE CZYH='").append("")
				.append("' AND CYZH = '").append("").append("' ");

		StringBuffer cSQL = new StringBuffer(200);
		cSQL.append("INSERT ").append("TBYZBYZYLBQ101")
				.append("(CYZPH, CYZH, CYYZH, CZYH, DXD, CYZZL, ICQ, IZT, BHDBJ, BYSTY, BSF, DLRSJ, ILRFS, ");

		for (int i = 0; i < fields.length; i++) {
			cSQL.append(fields[i]);
			if (i < fields.length - 1) {
				cSQL.append(", ");
			}
		}
		cSQL.append(") VALUES ('").append("").append("', '").append("").append("', '").append("").append("', '")
				.append("").append("', '5', 0, 0, 0, 0, 0, GETDATE(), '0', ");
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].equals("CYZNR")) {
				cSQL.append("('停止:'+CHAR(13)+CHAR(10)+").append("''").append(")");
			} else {
				cSQL.append("'").append("").append("'");
			}
			if (i < fields.length - 1) {
				cSQL.append(", ");
			}
		}
		cSQL.append(")");
		System.out.println(cSQL.toString());
	}
	
	public static void testAutoTaskSQL() {
	    String sCPBBH = "TEST10001";
	    String sTBMZYSKQSJJL = "YXHIS2017..TBMZYSKQSJJL201701";
        StringBuffer cSQL = new StringBuffer(300);
        String fields = "CPBBH, DPBRQ, CKSSJ, CJSSJ, CSJD, IKSBM, CKSMC, CZSBM, CZSMC, IYSBM, CYSMC";
        cSQL.append("IF NOT EXISTS (SELECT 1 FROM ").append(sTBMZYSKQSJJL).append(" WITH(NOLOCK) WHERE ")
            .append("CPBBH='").append(sCPBBH).append("') BEGIN INSERT INTO ").append(sTBMZYSKQSJJL).append("(")
            .append(fields).append(", DQDSJ, DQTSJ, IKQLX, BCD, BZT, ITZTZ, ITZYS, CTZYS, CTZPBH, CDLIP, DTJSJ, ")
            .append("CCZYGH, CCZYXM) VALUES ('").append(sCPBBH).append("', '").append("2017-01-01").append("', '")
            .append("").append("', '").append("").append("', '").append("").append("', ").append("1").append(", '")
            .append("CSKS").append("', '").append("101").append("', '").append("CSZS").append("', ")
            .append("1").append(", '").append("CSYS").append("', ").append("NULL").append(", ")
            .append("NULL").append(", ").append("NULL").append(", ").append("0").append(", ").append("0")
            .append(", ").append("0").append(", ").append("NULL").append(", ").append("NULL").append(", ")
            .append("NULL").append(", ").append("NULL").append(", GETDATE(), '")
            .append("TEST").append("', '").append("TestAdmin").append("'); END ");
        // System.out.println(cSQL.toString());
        
        cSQL.setLength(0);
        cSQL.append("SELECT BTZ, BTZBJ, CPBBH, DPBRQ, P.CKSSJ, P.CJSSJ, CSJD, P.IKSBM, P.CKSMC, CZSBM, CZSMC, ")
            .append("IYSBM, CYSMC, CASE WHEN K.BKH = 1 AND CONVERT(DATE, DSXSJ, 120) <= CONVERT(DATE, ")
            .append("DPBRQ, 120) THEN K.CKSSJ ELSE NULL END CTKSSJ, CASE WHEN K.BKH = 1 AND CONVERT(DATE, ")
            .append("DSXSJ, 120) <= CONVERT(DATE, DPBRQ, 120) THEN K.CJSSJ ELSE NULL END CTJSSJ FROM ")
            .append("YXHIS..").append("..TBMZYSPB P WITH(NOLOCK) LEFT JOIN ")
            .append("YXHIS..").append("..TBTSKSKHPZB K WITH(NOLOCK) ON P.IKSBM = K.IKSBM AND ")
            .append("(CASE P.CSJD WHEN '上午' THEN 1 WHEN '下午' THEN 2 END) = K.ISJD WHERE (ISNULL(K.BKH, '1')")
            .append(" <> 0 OR K.BKH = 0 AND K.BSXSJ = 0 OR K.BKH = 0 AND K.BSXSJ = 1 AND CONVERT(DATE, ")
            .append("K.DSXSJ, 120) > CONVERT(DATE, P.DPBRQ, 120)) AND DPBRQ BETWEEN '").append("2017-07-09")
            .append("' AND '").append("2017-07-20").append("' ORDER BY DPBRQ, IYSBM");
        // System.out.println(cSQL.toString());
        
        List <String> sTables = new ArrayList<String>();
        sTables.add("YXHIS2016..TBDLQK201706");
        sTables.add("YXHIS2016..TBDLQK201707");
        String sTBDLQK = "(";
        for (String name : sTables) {
            sTBDLQK += (" SELECT DKSSJ, DJSSJ, CIP, CGH FROM " + name + " WITH(NOLOCK) UNION ALL");
        }
        sTBDLQK = sTBDLQK.substring(0, sTBDLQK.length() - 10) + " )";
        // System.out.println(sTBDLQK);
        
        cSQL.setLength(0);
        cSQL.append("SELECT BTZ, BTZBJ, CPBBH, DPBRQ, P.CKSSJ, P.CJSSJ, CSJD, P.IKSBM, P.CKSMC, CZSBM, CZSMC,")
            .append(" IYSBM, CYSMC, CGH = (SELECT CGH FROM ").append("YXHIS").append("..TBCZY ")
            .append("C WITH(NOLOCK), ").append("YXHIS").append("..TBSYSCZY S WITH(NOLOCK) ")
            .append("WHERE C.CSRM = S.CUID AND CBH = (SELECT CCZYGH FROM ").append("YXHIS")
            .append("..TBZDMZYS WITH(NOLOCK) WHERE IBM = P.IYSBM) AND CCXBH = '18'), CASE WHEN K.BKH = 1")
            .append(" AND CONVERT(DATE, DSXSJ, 120) <= CONVERT(DATE, DPBRQ, 120) THEN K.CKSSJ ELSE NULL")
            .append(" END CTKSSJ, CASE WHEN K.BKH = 1 AND CONVERT(DATE, DSXSJ, 120) <= CONVERT(DATE, DPBRQ, 120)")
            .append(" THEN K.CJSSJ ELSE NULL END CTJSSJ FROM ").append("YXHIS").append("..TBMZYSPB")
            .append(" P WITH(NOLOCK) LEFT JOIN ").append("YXHIS").append("..TBTSKSKHPZB K WITH(NOLOCK)")
            .append(" ON P.IKSBM = K.IKSBM AND (CASE P.CSJD WHEN '上午' THEN 1 WHEN '下午' THEN 2 END) = K.ISJD")
            .append(" WHERE (ISNULL(K.BKH, '1') <> 0 OR K.BKH = 0 AND K.BSXSJ = 0 OR K.BKH = 0 AND K.BSXSJ = 1")
            .append(" AND CONVERT(DATE, K.DSXSJ, 120) > CONVERT(DATE, P.DPBRQ, 120)) AND DPBRQ BETWEEN '")
            .append("2017-06-12").append("' AND '").append("2017-07-12").append("' ORDER BY DPBRQ, IYSBM");
        // System.out.println(cSQL.toString());
        
        Map<String, String> map = new ConcurrentHashMap<String, String>();
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        
        map.put("A", "A");
        
        Map<String, String> reMap = new ConcurrentHashMap<String, String>();
        reMap.put("B", "B");
        reMap.put("C", "C");
        
        map.putAll(reMap);
        
        System.out.println(map.toString()); // {A=A, B=B, C=C}
        // System.out.println(map.get("NULL")); // null
        
        System.out.println("包含：" + Arrays.asList("Test|T".split("\\|")).contains("T")); // ture
        
        // System.out.println(Time.valueOf("10:00:00").compareTo(Time.valueOf("10:00:00"))); // 0
        /*
        int i;
        for (i = 0; i < 5; i++) {
            if (i == 3) {
                break;
            }
        }
        System.out.println("index:　" + i); // 3
        */
        java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
        System.out.println(ts);
        System.out.println(Timestamp.valueOf("1990-01-01 00:00:00"));
        
        List<HashMap<String, String>> checkTime = new ArrayList<HashMap<String,String>>();
        HashMap<String, String> hMap1 = new HashMap<String, String>();
        hMap1.put("DKSSJ", "1991-01-01 00:00:00");
        checkTime.add(hMap1);
        HashMap<String, String> hMap2 = new HashMap<String, String>();
        hMap2.put("DKSSJ", "1993-01-01 00:00:00");
        checkTime.add(hMap2);
        HashMap<String, String> hMap3 = new HashMap<String, String>();
        hMap3.put("DKSSJ", "1992-01-01 00:00:00");
        checkTime.add(hMap3);
        
        Collections.sort(checkTime, new Comparator<HashMap<String, String>>() {
            @Override
            public int compare(HashMap<String, String> h1, HashMap<String, String> h2) {
                String defStr = "1990-01-01 00:00:00";
                Timestamp t1 = Timestamp.valueOf(isEmptyStr(h1.get("DKSSJ")) ? defStr : h1.get("DKSSJ"));
                Timestamp t2 = Timestamp.valueOf(isEmptyStr(h2.get("DKSSJ")) ? defStr : h2.get("DKSSJ"));
                return t1.compareTo(t2);
            }
        });
        
        checkTime.forEach(item -> {
            System.out.println(item.get("DKSSJ"));
        });
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
			if (type == 1) {
				List<Attribute> attrs = rowEle.attributes();
				for (Attribute attr : attrs) {
					System.out.println("row.addAttribute(\"" + attr.getName() + "\", \"" + attr.getValue() + "\");");
				}
			} else {
				List<Element> eles = rowEle.elements();
				for (Element ele : eles) {
					System.out.println("row.addAttribute(\"" + ele.getName() + "\", \"" + ele.getText() + "\");");
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
			for (Element childEle : childs) {
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
			for (Element childEle : childs) {
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
		if (type == 0) {
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
	
	public static void testAppendSQL() {
        StringBuffer cSQL = new StringBuffer(300);
        cSQL.append("SELECT (SUBSTRING(CXM, CHARINDEX('排班编号:', CXM)+5, CHARINDEX(',上班日期:', CXM)-6)) CPBBH, ")
            .append("CYZ IYSBM, (SELECT CMC FROM ").append("YXHIS").append("..TBZDMZYS WITH(NOLOCK)")
            .append(" WHERE IBM=X.CYZ) CYSMC FROM ").append("YXHIS").append("..TBSYSXGJL X WITH(NOLOCK)")
            .append(" WHERE CTYPE='医生替诊' AND CHZ='").append("4").append("' AND CXM LIKE ('%' + (SELECT ")
            .append("CONVERT(VARCHAR(10), DPBRQ)+'%'+CONVERT(CHAR(1),ISJD) FROM ").append("YXHIS")
            .append("..TBMZYSPB WITH(NOLOCK) WHERE CPBBH='").append("41707093").append("')+'%'); \n");
        
        String dbName = "YXHIS";
        String sITZYSBM = "";
        String sCTZPBBH = "";
        cSQL.append("SELECT (SUBSTRING(CXM, CHARINDEX('排班编号:', CXM)+5, CHARINDEX(',上班日期:', CXM)-6))")
            .append(" CPBBH, CYZ IYSBM, (SELECT CMC FROM ").append(dbName).append("..TBZDMZYS WITH(NOLOCK)")
            .append(" WHERE IBM = X.CYZ) CYSMC FROM ").append(dbName).append("..TBSYSXGJL X WITH(NOLOCK)")
            .append(" WHERE CTYPE = '医生替诊' AND CHZ = '").append(sITZYSBM).append("' AND CXM LIKE ")
            .append("('%'+(SELECT CONVERT(VARCHAR(10), DPBRQ)+'%'+CONVERT(CHAR(1),ISJD) FROM ")
            .append(dbName).append("..TBMZYSPB WITH(NOLOCK) WHERE CPBBH='").append(sCTZPBBH).append("')+'%'); \n");

        String nowDate = nowStr("yyyy-MM-dd");

        cSQL.append("SET DATEFIRST 1; SELECT CASE BTZBJ WHEN 1 THEN (SELECT CYZ + '|' + (SELECT CMC FROM ")
            .append(dbName).append("..TBZDMZYS WITH(NOLOCK) WHERE IBM = X.CYZ) + '|' + ")
            .append("(SUBSTRING(CXM, CHARINDEX('排班编号:', CXM)+5, CHARINDEX(',上班日期:', CXM)-6))+'='+")
            .append("(CONVERT(VARCHAR, P.IYSBM)+'|'+P.CYSMC+'|'+CPBBH) CPBXX FROM ").append(dbName)
            .append("..TBSYSXGJL X WITH(NOLOCK) WHERE CTYPE = '医生替诊' AND CHZ = P.IYSBM AND CXM LIKE ")
            .append("('%' + (SELECT CONVERT(VARCHAR(10), DPBRQ)+'%'+CONVERT(CHAR(1),ISJD) FROM ")
            .append(dbName).append("..TBMZYSPB WITH(NOLOCK) WHERE CPBBH = P.CPBBH)+'%'))")
            .append("ELSE (CONVERT(VARCHAR, P.IYSBM) + '|' + P.CYSMC + '|' + CPBBH) END CPBXX, DPBRQ, CSJD, CWEEK=")
            .append("(CASE DATEPART(DW, DPBRQ) WHEN 1 THEN '周一' WHEN 2 THEN '周二' WHEN 3 THEN '周三' WHEN 4 ")
            .append("THEN '周四' WHEN 5 THEN '周五' WHEN 6 THEN '周六' WHEN 7 THEN '周日' END), P.CKSMC, CZSMC, ")
            .append("CKQZT = (CASE WHEN BMZTH=1 OR BTZ=1 THEN '4' WHEN BTZBJ=1 THEN '5' ELSE (CASE WHEN ")
            .append("ISNULL(Y.CJSSJ, '')<>'' AND CONVERT(TIME, GETDATE()) >= Y.CJSSJ OR ISNULL(K.CJSSJ, '')<>'' ")
            .append("AND CONVERT(TIME, GETDATE()) >= K.CJSSJ OR CONVERT(TIME, GETDATE()) >= CONVERT(TIME, P.CJSSJ)")
            .append(" THEN '0' ELSE '6' END) END) FROM ").append(dbName).append("..TBMZYSPB P")
            .append(" WITH(NOLOCK) LEFT JOIN ").append(dbName).append("..TBYSKHSJPZB Y WITH(NOLOCK)")
            .append(" ON P.IYSBM=Y.IYSBM AND (CASE P.CSJD WHEN '上午' THEN 1 WHEN '下午' THEN 2 ELSE 0 END)=Y.ISJD ")
            .append("LEFT JOIN ").append(dbName).append("..TBTSKSKHPZB K WITH(NOLOCK)")
            .append(" ON P.IKSBM=K.IKSBM AND (CASE P.CSJD WHEN '上午' THEN 1 WHEN '下午' THEN 2 ELSE 0 END)=K.ISJD ")
            .append(" WHERE DPBRQ='").append(nowDate).append("' AND (CPBBH NOT IN (SELECT CPBBH FROM ")
            .append("YXHIS2017..TBMZYSKQSJJL201707").append(" WITH(NOLOCK) WHERE DPBRQ='").append(nowDate)
            .append("') OR BTZBJ=1) ORDER BY DPBRQ DESC, P.IYSBM ASC ");
        System.out.println(cSQL);
	}
	
	/**
	 * 转创建表 SQL 
	 * @param tableName 库名..表名..注释
	 * @param primary   字段名1, 字段名2
	 * @param type 表类型{0：单表, 1：单库年表, 4：年库月表, 5：年库年表}
	 * @param tableStr  字段名1, 字段名2:类型1, 类型2:注释1, 注释2
	 */
	public static void strToTable(String tableName, String primary, String type, String tableStr) {
	    String[] names = tableName.split("\\.\\.");
	    String dbName = names[0];
	    String tbName = names[1];
	    String tbMark = names[2];
	    String[] types = new String[]{"", ""}; // [0]:库类型后缀, [1]:表类型后缀
	    switch (type) {
        case "1": // 单库年表
            types[0] = "";
            types[1] = nowStr("yyyy");
            break;
        case "4": // 年库月表
            types[0] = nowStr("yyyy");
            types[1] = nowStr("yyyyMM");
            break;
        case "5": // 年库年表
            types[0] = nowStr("yyyy");
            types[1] = nowStr("yyyy");
            break;
        case "0": // 默认单表
        default:
            types[0] = "";
            types[1] = "";
            break;
        }
	    StringBuffer cSQL = new StringBuffer(200);
	    cSQL.append("USE [").append(dbName).append(types[0]).append("]\nGO\n\n-- ").append(tbMark).append("\n")
	        .append("IF NOT EXISTS (SELECT * FROM SYSOBJECTS WHERE [name]='").append(tbName).append(types[1]).append("' AND xtype='U')\n")
	        .append("BEGIN\n\tSET ANSI_NULLS ON;\n\tSET QUOTED_IDENTIFIER ON;\n\tSET ANSI_PADDING ON;\n\n\t")
	        .append("CREATE TABLE [dbo].[").append(tbName).append(types[1]).append("](").append("\n\t\t");
	    
        String[] strs = tableStr.split(":");
        String[] fileds = strs[0].split(", "); // 字段
        String[] dtypes = strs[1].split(", "); // 类型
        String[] remark = strs[2].split(", "); // 备注
        String[] primarys = primary.split(", "); // 主键
	    for (int i = 0; i < fileds.length; i++) {
	        String isNull = Arrays.asList(primarys).contains(fileds[i]) ? " NOT NULL" : " NULL";
	        cSQL.append(fileds[i]).append(" ").append(dtypes[i]).append(isNull)
	            .append(", \t -- ").append(remark[i]).append("\n\t\t");
        }
	    cSQL.append("PRIMARY KEY CLUSTERED (").append(primary).append(" ASC)").append("\n\t")
	        .append("WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)")
	        .append(" ON [PRIMARY]").append("\n\t").append(") ON [PRIMARY];\n\t").append("SET ANSI_PADDING OFF;").append("\n").append("END;\n")
	        .append("SELECT * FROM ").append(dbName).append(types[0]).append(".[dbo].").append(tbName).append(types[1]).append(" WITH(NOLOCK); ");
	    System.out.println(cSQL.toString());
	}
	
    public static String getMinDiff(String timeBegin, String timeEND) {
        long minDiff = Math.abs(StrToTime(timeBegin).getTime() - StrToTime(timeEND).getTime()) / 60000;
        if (minDiff > 59) {
            return (minDiff / 60) + " 小时 " + String.valueOf(minDiff % 60) + " 分钟";
        }
        return String.valueOf(minDiff) + " 分钟";
    }

    public static void testWebService() {
        String actionName = "Execute";
        String code = "QryWPXX"; // 物品信息
        String inVal = "<MSG><ASK><PAR>" + "<IKWBM>2</IKWBM><CBM>10002</CBM><CPYM></CPYM><CWPTXM></CWPTXM>"
                + "<CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM>" + "</PAR></ASK></MSG>";

        code = "QryWPZSMXJL"; // 物资追溯查询
        inVal = "<MSG><ASK><PAR>" + "<BEGINDATE>2017-01-01 01:00:00</BEGINDATE><ENDDATE>2017-05-13 16:48:59</ENDDATE>"
                + "<IKWBM>2</IKWBM><CNBTXM/><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM>" + "</PAR></ASK></MSG>";

        code = "WPLWCK"; // 物资出库
        inVal = "<MSG><ASK><PAR>"
                + "<Rows><ROW><IKWBM>2</IKWBM><IDFKWBM>1</IDFKWBM><CDFKWMC>内一科</CDFKWMC><IXH>1</IXH><IKCBM>52</IKCBM>"
                + "<NSL>1</NSL><MPFJ>90</MPFJ><MGRJ>95</MGRJ><MLSJ>99.9</MLSJ><DCRSJ>2017-05-13 15:05:13</DCRSJ>"
                + "<DCZSJ>2017-05-13 15:05:01</DCZSJ><CJSR>刘天琪</CJSR><CBGR>施良锋</CBGR><CCZYGH>M63</CCZYGH>"
                + "<CCZYXM>管理员</CCZYXM><CPCTXM/><CNBTXM/></ROW></Rows>" + "</PAR></ASK></MSG>";

        code = "WPTHCK"; // 物资退货
        inVal = "<MSG><ASK><PAR>"
                + "<Rows><ROW><CDFKWMC>上海圣荷西医疗用品有限公司</CDFKWMC><IKWBM>2</IKWBM><IXH>1</IXH><IKCBM>52</IKCBM>"
                + "<NSL>1</NSL><MPFJ>90</MPFJ><MGRJ>95</MGRJ><MLSJ>99</MLSJ><MTYJ>90</MTYJ>"
                + "<DCRSJ>2017-05-13 15:05:13</DCRSJ><DCZSJ>2017-05-13 15:05:01</DCZSJ><CJSR>刘天琪</CJSR><CBGR>施良锋</CBGR>"
                + "<CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM><CPCTXM/><CNBTXM/></ROW></Rows>" + "</PAR></ASK></MSG>";

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
        // inVal =
        // "<MSG><ASK><PAR><CKSMC>内三病区</CKSMC><CKWMC>内一科</CKWMC><CCZYXM>管理员</CCZYXM><CJZBH>16000042</CJZBH><CYSBM/><CYSMC>马国威</CYSMC><CSSBH/><DCZSJ>2017-05-26
        // 16:46</DCZSJ><IBRLX>2</IBRLX><CCZYGH>M63</CCZYGH><CKSBM>3</CKSBM><IKWBM>7</IKWBM><Rows><Row
        // IXH=\"1\" CPCTXM=\"201701240003\"/><Row IXH=\"2\"
        // CPCTXM=\"201701240001\" /></Rows></PAR></ASK></MSG>";
        inVal = "<MSG><ASK><PAR><CKSMC>内三病区</CKSMC><CKWMC>内一科</CKWMC><CCZYXM>管理员</CCZYXM><CJZBH>16000042</CJZBH><CYSBM/><CSSBH/><CYSMC>马国威</CYSMC><DCZSJ>2017-05-27 10:41</DCZSJ><IBRLX>0</IBRLX><CCZYGH>M63</CCZYGH><IKWBM>7</IKWBM><CKSBM>3</CKSBM><Rows><Row><IXH>1</IXH><CPCTXM>201701240003</CPCTXM></Row><Row><IXH>2</IXH><CPCTXM>201701240001</CPCTXM></Row></Rows></PAR></ASK></MSG>";
        /**
         * 设备管理
         *
         * code = "QrySBXX"; // 设备信息 inVal =
         * "<MSG><ASK><PAR><IKWBM>4</IKWBM><CSBBH></CSBBH><CSYKS></CSYKS></PAR></ASK></MSG>"
         * ;
         * 
         * code = "QrySBWX"; // 设备维修查询 inVal = "<MSG><ASK><PAR>" +
         * "<DBEGIN>2016-01-01 13:14:00</DBEGIN>" +
         * "<DEND>2017-05-14 13:14:48</DEND>" + "<IKWBM>4</IKWBM>" +
         * "<CSBBH></CSBBH>" + "<CWXDH></CWXDH>" + "<ICXLX>1</ICXLX>" +
         * "</PAR></ASK></MSG>"; /* // 供应室 code = "QryGYSBWPXX"; // 获取包内物品信息
         * inVal =
         * "<MSG><MSH><MSH.1>HT10003</MSH.1><MSH.2>packInfoQuery</MSH.2></MSH><ASK><PAR><CBTM></CBTM><CBBM></CBBM><CBMC></CBMC><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM></PAR></ASK></MSG>"
         * ;
         * 
         * code = "GYSBWPXD"; // 医用包消毒 inVal =
         * "<MSG><ASK><PAR><NXDWD>0.0</NXDWD><DXDRQ>2017-05-24 16:16</DXDRQ><NZGGC>1</NZGGC><NMJSC>0.0</NMJSC><CBTM>1705095845</CBTM><CCZYGH>M63</CCZYGH><CXDRQ/><CXDYBM/><CCZYXM>管理员</CCZYXM><NXDYL>0.0</NXDYL><CQXFS/><NXDGC>1</NXDGC><CXDY>管理员</CXDY><CXDFS/><CZGGH/><CXDGH/><DGXRQ>2017-05-31 16:16</DGXRQ></PAR></ASK></MSG>"
         * ;
         * 
         * code = "QryZYBRJBXX"; // 获取在院病人信息 inVal =
         * "<MSG><ASK><PAR><CZYH>15000003123</CZYH><CCZYGH>M6</CCZYGH><CCZYXM>管理员</CCZYXM></PAR></ASK></MSG>"
         * ;
         * 
         * code = "QryMZBRJBXX"; // 获取门诊病人信息 inVal =
         * "<MSG><ASK><PAR><CMZH>1612000003</CMZH><DGHKSRQ>2015-12-27</DGHKSRQ><DGHJSRQ>2016-12-31</DGHJSRQ><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM></PAR></ASK></MSG>"
         * ;
         */

        /**
         * 药品管理
         */
        /*
         * code = "QryYPCGJHSQMX"; // 采购申请单信息查询 
         * inVal = "<MSG><ASK><PAR><BEGINDATE>2016-05-27 10:09:59</BEGINDATE><ENDDATE>2017-05-27 10:09:59</ENDDATE><IKWBM>1</IKWBM><CCGD></CCGD><CJYDW></CJYDW><CCZYGH>M63</CCZYGH><CCZYXM>操作员姓名</CCZYXM></PAR></ASK></MSG>";
         * 
         * code = "QryYPXX"; // 药品信息 inVal =
         * "<MSG><ASK><PAR><CBM></CBM><CWPTXM></CWPTXM><CCZYGH>M63</CCZYGH><CCZYXM>操作员姓名</CCZYXM></PAR></ASK></MSG>";
         * 
         * code = "QryYPCRKMX"; // 查询药品出入库明细 inVal =
         * "<MSG><ASK><PAR><BEGINDATE>2016-05-27 17:58:20</BEGINDATE><ENDDATE>2017-05-27 17:58:20</ENDDATE><IKWBM>1</IKWBM><CCRD></CCRD><IDFKWBM></IDFKWBM><CCZYGH>M63</CCZYGH><CCZYXM>操作员姓名</CCZYXM></PAR></ASK></MSG>"
         * ;
         * 
         * code = "QryCGPSD"; // 采购配送单查询 inVal =
         * "<MSG><ASK><PAR><CTM>123</CTM><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM></PAR></ASK></MSG>";
         */
        code = "QryCGPSDBZ"; // 采购配送大包装查询
        inVal = "<MSG><ASK><PAR><CTM>123</CTM><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM></PAR></ASK></MSG>";

        code = "QryYPPSMX"; // 药品配送明细查询
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

        code = "ReagInConfirm"; // 试剂入库 通过
        inVal = "<MSG><ASK><PAR><CCZYGH>M63</CCZYGH><CCZYXM>操作员</CCZYXM><Rows><Row><CCRD>TEST11111111</CCRD> <IXH>1</IXH> <IKWBM>1</IKWBM> <CKWMC>库位名称</CKWMC> <IPCBH>1</IPCBH> <IKCBM>1</IKCBM><CWPBM>物品编码</CWPBM><CWPMC>物品名称</CWPMC> <CWPBZ>物品包装</CWPBZ> <CXH>型号</CXH><DYXQ>2017-05-28 15:35:24</DYXQ> <CZXDW>最小单位</CZXDW> <CKCDW>库存单位</CKCDW><IHSXS>1</IHSXS> <CPH>批号</CPH> <NSL>1.0</NSL><CCRFS>出入方式</CCRFS><DCRSJ>2017-05-28 15:35:24</DCRSJ> <IDFKWBM>2</IDFKWBM><CDFKWMC>对方库位名称</CDFKWMC><MPFJ>99.0</MPFJ> <MGRJ>99.0</MGRJ> <MLSJ>99.0</MLSJ> <MTYJ>99.0</MTYJ> <CDJH>单据号</CDJH> <CSCCJ>生产厂家</CSCCJ><CCD>产地</CCD> <NKCL>9</NKCL><CJSR>经手人</CJSR><CBGR>保管人</CBGR> <CCZY>操作员</CCZY> <CSYDD>使用地址</CSYDD> <CBZ>备注</CBZ><ITJFS>1</ITJFS><IDFKCBM>1</IDFKCBM><BFK>1</BFK><CHW>货位</CHW> <CSHR>审核人</CSHR><DSHRQ>2017-05-28 15:35:24</DSHRQ><CFPH>发票号</CFPH> <CJSH>结算号</CJSH><CJSCZY>结算员</CJSCZY><DJSRQ>2017-05-28 15:35:24</DJSRQ><CPCTXM>1111111|2222222</CPCTXM><CNBTXM>1111111|2222222</CNBTXM></Row></Rows></PAR></ASK></MSG>";
        inVal = "<MSG><MSH><MSH.1>HT10005</MSH.1><MSH.2>reagInConfirm</MSH.2></MSH><ASK><PAR><CCZYXM>管理员</CCZYXM><CCZYGH>M63</CCZYGH><RowCount>2</RowCount><Rows><Row><CYSCZY/><ITK/><CHGZBH/><CCZYGH>1319</CCZYGH><CCWH/><DCRSJ>2017-05-31 09:47:07</DCRSJ><DYXQ>1899-12-30 00:00:00</DYXQ><IPCBH>1</IPCBH><CKL>1</CKL><CBRXM/><LWSQSHR/><IDFKWBM>1</IDFKWBM><NSL>1</NSL><CXH/><CJSR>管理员</CJSR><CZCMC/><CWPBZ>1套/套</CWPBZ><CGG/><IKCBM>1248</IKCBM><MLSJ>598.5</MLSJ><BFK>0</BFK><LWSQSHRGH/><CCZYXM>管理员</CCZYXM><CCRD>2017R000005</CCRD><CCRFS>联网入库</CCRFS><CSHR/><CBGR>付菊芳</CBGR><NKCL>0</NKCL><IDFKCBM>988</IDFKCBM><CSYDD/><DJSRQ>1899-12-30 00:00:00</DJSRQ><CJSCZY/><IHSXS>1</IHSXS><IXH>1</IXH><CJSH/><CDFKWMC>医用物资库</CDFKWMC><CPH/><NTKL/><KSFHC/><CWPBM>100518</CWPBM><CFPH/><MPFJ>0</MPFJ><IDYCS/><DLWSQSHRQ>1899-12-30 00:00:00</DLWSQSHRQ><CPZWH/><IKWBM>27</IKWBM><ITJFS>3</ITJFS><CBZ4/><CKCDW>套</CKCDW><CWXR/><CCD>国药集团河北医疗器械有限公司</CCD><CSCCJ>廊坊市爱尔血液</CSCCJ><CYCRDMX/><CZXDW>套</CZXDW><ISMBJ>1</ISMBJ><CDJH>2017C000203</CDJH><CZYH/><CWPCZ/><CBZ3/><CWZFL/><CMXBZ/><IYS/><CKWMC>检验科</CKWMC><DYSSJ>1899-12-30 00:00:00</DYSSJ><DSHRQ>1899-12-30 00:00:00</DSHRQ><CZCLB/><CWPMC>一次性使用血液灌流器</CWPMC><CHW/><CBZ/><MTYJ>0</MTYJ><MGRJ>570</MGRJ></Row><Row><CYSCZY/><ITK/><CHGZBH/><CCZYGH>1319</CCZYGH><CCWH/><DCRSJ>2017-05-31 09:47:07</DCRSJ><DYXQ>1899-12-30 00:00:00</DYXQ><IPCBH>1</IPCBH><CKL>1</CKL><CBRXM/><LWSQSHR/><IDFKWBM>1</IDFKWBM><NSL>1</NSL><CXH/><CJSR>管理员</CJSR><CZCMC/><CWPBZ>1套/套</CWPBZ><CGG/><IKCBM>56</IKCBM><MLSJ>1932</MLSJ><BFK>0</BFK><LWSQSHRGH/><CCZYXM>管理员</CCZYXM><CCRD>2017R000005</CCRD><CCRFS>联网入库</CCRFS><CSHR/><CBGR>付菊芳</CBGR><NKCL>0</NKCL><IDFKCBM>56</IDFKCBM><CSYDD/><DJSRQ>1899-12-30 00:00:00</DJSRQ><CJSCZY/><IHSXS>1</IHSXS><IXH>2</IXH><CJSH/><CDFKWMC>医用物资库</CDFKWMC><CPH/><NTKL/><KSFHC/><CWPBM>100213</CWPBM><CFPH/><MPFJ>0</MPFJ><IDYCS/><DLWSQSHRQ>1899-12-30 00:00:00</DLWSQSHRQ><CPZWH/><IKWBM>27</IKWBM><ITJFS>3</ITJFS><CBZ4/><CKCDW>套</CKCDW><CWXR/><CCD/><CSCCJ>张家港</CSCCJ><CYCRDMX/><CZXDW>套</CZXDW><ISMBJ>1</ISMBJ><CDJH>2017C000203</CDJH><CZYH/><CWPCZ/><CBZ3/><CWZFL/><CMXBZ/><IYS/><CKWMC>检验科</CKWMC><DYSSJ>1899-12-30 00:00:00</DYSSJ><DSHRQ>1899-12-30 00:00:00</DSHRQ><CZCLB/><CWPMC>高尔夫钢板</CWPMC><CHW/><CBZ/><MTYJ>0</MTYJ><MGRJ>1840</MGRJ></Row></Rows></PAR></ASK></MSG>";
        inVal = "<MSG><ASK><PAR><CCZYXM>管理员</CCZYXM><CCZYGH>M63</CCZYGH><Rows><Row><CNBTXM>123</CNBTXM><CXSDH></CXSDH><CYSCZY></CYSCZY><ITK></ITK><CHGZBH></CHGZBH><CSYKS></CSYKS><CCZYGH>1319</CCZYGH><CKY></CKY><CCWH></CCWH><DCRSJ>2017-06-01 09:11:47</DCRSJ><DYXQ>1899-12-30 00:00:00</DYXQ><IPCBH>1</IPCBH><CKL>1</CKL><CBRXM></CBRXM><LWSQSHR></LWSQSHR><IDFKWBM>1</IDFKWBM><NSL>1</NSL><CXH></CXH><CJSR>管理员</CJSR><CZCMC></CZCMC><CWPBZ>1套/套</CWPBZ><CGG></CGG><IKCBM>1248</IKCBM><MLSJ>598.5</MLSJ><BFK>0</BFK><LWSQSHRGH></LWSQSHRGH><CCZYXM>管理员</CCZYXM><CCRD>2017R000006</CCRD><CCRFS>联网入库</CCRFS><CSHR></CSHR><CBGR>付菊芳</CBGR><NKCL>0</NKCL><IDFKCBM>988</IDFKCBM><CSYDD></CSYDD><DJSRQ>1899-12-30 00:00:00</DJSRQ><CJSCZY></CJSCZY><IHSXS>1</IHSXS><IXH>1</IXH><CJSH></CJSH><CDFKWMC>医用物资库</CDFKWMC><CPH></CPH><NTKL></NTKL><KSFHC></KSFHC><CWPBM>100518</CWPBM><CFPH></CFPH><MPFJ>0</MPFJ><IDYCS></IDYCS><DLWSQSHRQ>1899-12-30 00:00:00</DLWSQSHRQ><DKPRQ>1899-12-30 00:00:00</DKPRQ><CPZWH></CPZWH><IKWBM>27</IKWBM><ITJFS>3</ITJFS><CBZ4></CBZ4><CKCDW>套</CKCDW><DXSRQ>1899-12-30 00:00:00</DXSRQ><CWXR></CWXR><CCD>国药集团河北医疗器械有限公司</CCD><CSCCJ>廊坊市爱尔血液</CSCCJ><CYCRDMX></CYCRDMX><CZXDW>套</CZXDW><ISMBJ>1</ISMBJ><CDJH>2017C000204</CDJH><CZYH></CZYH><CWPCZ></CWPCZ><CBZ3></CBZ3><CWZFL></CWZFL><CMXBZ></CMXBZ><IYS></IYS><CKWMC>检验科</CKWMC><DYSSJ>1899-12-30 00:00:00</DYSSJ><DSHRQ>1899-12-30 00:00:00</DSHRQ><CZCLB></CZCLB><CWPMC>一次性使用血液灌流器</CWPMC><CHW></CHW><CBZ></CBZ><MTYJ>0</MTYJ><MGRJ>570</MGRJ></Row></Rows></PAR></ASK></MSG>";
        inVal = "<MSG><MSH><MSH.1>HT10005</MSH.1><MSH.2>reagInConfirm</MSH.2></MSH><ASK><PAR><CCRD>2017R000006</CCRD><CCZYGH>M63</CCZYGH><CCZYXM>管理员</CCZYXM></PAR></ASK></MSG>";
        /*
         * code = "GetReagInfo"; // 获取试剂信息 通过 inVal =
         * "<MSG><MSH><MSH.1>HT10005</MSH.1><MSH.2>getReagInfo</MSH.2></MSH><ASK><PAR><CCZYXM>管理员</CCZYXM><CCZYGH>M63</CCZYGH><CSJTM>123456</CSJTM></PAR></ASK></MSG>";
         * 
         * code = "ReagOutConfirm"; // 试剂出库分组 通过 inVal =
         * "<MSG><ASK><PAR><CCZYXM>管理员</CCZYXM><CCZYGH>M63</CCZYGH><Rows><Row><CLQBM>生化组</CLQBM><index>1</index><CLQBMBM>1</CLQBMBM><CSJMC>总胆汁酸</CSJMC><NSL>2</NSL><CSJBM>0008</CSJBM><CSJTM>123456</CSJTM></Row></Rows></PAR></ASK></MSG>";
         */
        // HttpClientManager.setPostUrl("http://192.168.200.24:4567/webservice1.asmx");
        // // 供应室
        // HttpClientManager.setPostUrl("http://192.168.200.24:4568/webservice.asmx");
        // // 设备
        // HttpClientManager.setPostUrl("http://192.168.200.24:4569/webservice1.asmx");
        // // 物资
        // HttpClientManager.setPostUrl("http://192.168.200.24:7890/webservice1.asmx");
        // // 药品
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
     * 
     * 字符型转换成时间型
     */
    public static Time StrToTime(String str){
        return Time.valueOf(str);
    }
	
    /**
     * 返回格式化当前系统时间
     * 
     * @param inFmt
     * @return
     */
    public static String nowStr(String inFmt) {
        inFmt = isEmptyStr(inFmt) ? "yyyy-MM-dd HH:mm:ss.SSS" : inFmt;
        return new SimpleDateFormat(inFmt).format(System.currentTimeMillis());
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
}
