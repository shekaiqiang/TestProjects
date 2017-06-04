package com.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;/*
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;*/
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * POI导出Excel
 * @author Tanken·L
 * @Start  2016-9-4
 * @Done   2016-9-4
 * @Revise
 */
public class ExportExcelPoi {
	
	/**
	 * 导出Excel
	 * @param title：sheet页名称
	 * @param headers：标题栏数组，支持双行标题
	 * @param dataset：具体要在Excel中显示的数据链表
	 * @param out：输出路径
	 * @throws IOException 
	 */
	public void exportExcel(String title, String[] headers, ArrayList<ArrayList<String>> dataset, OutputStream out) throws IOException {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为20个字节
		sheet.setDefaultColumnWidth(20);
		/**
		 * 标题单元格样式
		 */
		HSSFCellStyle style_tit = workbook.createCellStyle();
		style_tit.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index); // 设置单元格背景色
		style_tit.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		// 边框 Begin
		style_tit.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style_tit.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style_tit.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style_tit.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 边框 End
		style_tit.setWrapText(true); // 标题自动换行
		style_tit.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平布局：居中
		style_tit.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直布局：居中
		// 标题字体
		HSSFFont font_tit = workbook.createFont();
		font_tit.setColor(HSSFColor.BLACK.index); // 设置字体颜色
		font_tit.setFontHeightInPoints((short)12); // 设置字体大小 
		font_tit.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 设置标题字体粗细：粗体
		// 把字体应用到当前的样式
		style_tit.setFont(font_tit);
		/**
		 * 内容单元格样式
		 */
		HSSFCellStyle style_con = workbook.createCellStyle();
		style_con.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		style_con.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style_con.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style_con.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style_con.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style_con.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style_con.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
		style_con.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// style2.setWrapText(true); // 内容自动换行
		// 内容字体
		HSSFFont font_con = workbook.createFont();
		font_con.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL); // 设置标题字体粗细：正常
		// 把字体应用到当前的样式
		style_con.setFont(font_con);
		/*
		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置单元格注释内容
		comment.setString(new HSSFRichTextString("注释内容"));
		// 设置单元格注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		comment.setAuthor("注释作者");*/

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0); // 一级标题
		HSSFRow row2 = sheet.createRow(1); // 二级标题
		for (int i = 0, n = 0; i < headers.length; i++) {// i是headers的索引，n是Excel的索引
			HSSFCell cell1 = row.createCell(n);
			cell1.setCellStyle(style_tit);
			HSSFRichTextString text = null;
			if (headers[i].contains(":")) {// 双标题
				String[] temp = headers[i].split(":");
				text = new HSSFRichTextString(temp[0]); // 一级标题内容
				String[] tempSec = temp[1].split(","); // 二级标题内容
				// 合并主标题单元格：列
				sheet.addMergedRegion(new CellRangeAddress(0, 0, n, (n + tempSec.length - 1)));
				int tempI = n;
				for (int j = 0; j < tempSec.length - 1; j++) {
					HSSFCell cellT = row.createCell(++tempI); // 一级标题单元格
					cellT.setCellStyle(style_tit);
				}
				for (int j = 0; j < tempSec.length; j++) {
					HSSFCell cell2 = row2.createCell(n++); // 二级标题单元格
					cell2.setCellStyle(style_tit);
					cell2.setCellValue(new HSSFRichTextString(tempSec[j]));
				}
				cell1.setCellValue(text); // 设置单元格内容
				sheet.getRow(cell1.getRowIndex()).setHeightInPoints((float)(text.length() * 2)); // 设置一级标题单元格高度
			} else {// 单标题
				HSSFCell cell2 = row2.createCell(n);
				cell2.setCellStyle(style_tit); // 设置单标题第二行单元格的样式
				sheet.addMergedRegion(new CellRangeAddress(0, 1, n, n)); // 合并单标题两行单元格
				cell1.setCellValue(new HSSFRichTextString(headers[i])); // 设置单元格内容
				n++;
			}
			sheet.createFreezePane(1, 2); // 冻结第一列和前两行
		}
		// 遍历集合数据，产生数据行
		for (int i = 0, index = 2; i < dataset.size(); i++, index++) {
			row = sheet.createRow(index);
			for (int j = 0; j < ((ArrayList<String>) dataset.get(i)).size(); j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellStyle(style_con);
				HSSFRichTextString richString = new HSSFRichTextString(dataset.get(i).get(j));
				cell.setCellValue(richString);
			}
		}
		workbook.write(out);
		workbook.close();
	}

	public static void main(String[] args) {
		ExportExcelPoi eep = new ExportExcelPoi();
		String[] titles = { "标题一", "标题二", "标题三:三-1,三-222222222222222222222", "标题四444444444444:4.1,4.2", "标题五",
				"标题六", "标题七", "标题八" };
		String[] titles2 = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0000000000000000000000000000000000000000000000" };
		ArrayList<ArrayList<String>> dataset = new ArrayList<ArrayList<String>>(); // 实体：excel要显示的数据，从数据库中查出
		ArrayList<String> content = new ArrayList<String>(); // 字段
		for (int i = 0; i < 10; i++) { // 10列
			content.add("单元格内容-Content" + i);
		}
		for (int i = 0; i < 9; i++) { // 9 行数据
			dataset.add(content);
		}
		try {
			File file = new File("D://Users/Administrator/Desktop/outExcel.xls");
			if(file.exists() && !file.renameTo(file)) { // 如果过文件存在并且被占用
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch(Exception e) {}
				Object[] options = { "确定", "取消" }; 
				int i = JOptionPane.showOptionDialog(null, ("文件 " + file.getName() + " 正在使用中，是否强行删除？"), "提示", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]); 
				if(i == 1) {
					return;
				}
				if(!forceDeleteOutLocked(file, "EXCEL.EXE")) {
					throw new Exception("另一个程序正在使用此文件，进程无法访问：" + file.getPath());
				} else {
					System.out.println("已删除！");
				} 
			}
			OutputStream out = new FileOutputStream(file);
			eep.exportExcel("两级标题示例", titles, dataset, out);
			OutputStream out2 = new FileOutputStream("D://Users/Administrator/Desktop/outExcel2.xls");
			eep.exportExcel("单标题示例", titles2, dataset, out2);
			System.out.println("导出成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 强制删除锁定文件
	 * @param fileForDel
	 * @param LockAppName
	 * @return
	 */
	public static boolean forceDeleteOutLocked(File fileForDel, String LockAppName) {
	    boolean result = false;
	    String cmd = "taskkill /f /im " + LockAppName; // 关闭此进程
	    try {
	        Runtime.getRuntime().exec(cmd);
	        Thread.sleep(1000);
	        result = fileForDel.delete();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
}