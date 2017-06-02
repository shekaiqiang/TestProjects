package com.time;

import java.util.Scanner;

/**
 * 万年历 Perpetual Calendar
 * @author Tanken·L
 * @Start  2016-2-23
 * @Done   2016-2-23
 * @Revision 
 */
public class PerpetualCalendar {

	public static void main(String[] args) {
		int daysOfMonth = 0; // 记录输入日期的那个月有多少天
		int daysOfMonths = 0; // 记录输入日期距离输入年份第一天多少天
		int daysOfYears = 0;// 记录输入日期的那个月的一号距离1900年一月一日多少天
		int month = 0; // 记录距离输入的月份
		int year = 0; // 记录距离输入的年份

		System.out.println("*********************** 欢迎使用万年历 ***********************");
		Scanner input = new Scanner(System.in);
		System.out.print("输入年份:");
		year = input.nextInt();
		boolean bMonth = year % 400 == 0 || year % 4 == 0 && year % 100 != 0;// 输入年份是否为闰年
		do {
			if (month < 12) {
				System.out.print("输入月份:");

			} else {
				System.out.print("月份出入错误!\n重新输入月份:");
			}
			month = input.nextInt();
		} while (month > 12);

		switch (month) {// 计算输入月份有多少天;
		case 2:
			if (bMonth) {
				daysOfMonth = 29;
			} else {
				daysOfMonth = 28;
			}
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			daysOfMonth = 30;
			break;
		default:
			daysOfMonth = 31;
		}
		for (int i = 1; i < month; i++) {// 计算输入日期距离输入年份第一天多少天
			switch (i) {
			case 2:
				if (bMonth) {
					daysOfMonths += 29;
				} else {
					daysOfMonths += 28;
				}
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				daysOfMonths += 30;
				break;
			default:
				daysOfMonths += 31;
			}
		}
		for (int i = 1900; i < year; i++) {// 输入年份距离1900年一月一日多少天
			if (i % 400 == 0 || i % 4 == 0 && i % 100 != 0) {
				daysOfYears += 366;// 366
			} else {
				daysOfYears += 365;// 365
			}
		}
		daysOfYears += daysOfMonths;// daysOfYears现在的值为输入日期的那个月的一号距离1900年一月一日多少天

		int xqj = 1 + daysOfYears % 7; // 计算输入月份的第一天是星期几
		// 开始按照格式输出该月日历

		System.out.println("\n星期日\t" + "星期一\t" + "星期二\t" + "星期三\t" + "星期四\t"
				+ "星期五\t" + "星期六");
		for (int i = 0; i < xqj; i++) {
			System.out.print("\t");
		}
		int temp = xqj;// 用于计算什么时候是换行，什么时候是输出制表符号
		for (int i = 1; i <= daysOfMonth; i++) { // 循环输出日期
			temp++;
			if (temp % 7 != 0) {
				System.out.print(i + "\t");
			} else {
				System.out.print(i + "\n");
			}

		}
		System.out.println("\n");
		input.close();
		main(args);
	}
}
