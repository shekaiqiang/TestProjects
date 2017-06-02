package net.algorithm;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 面试中常见的算法
 * @author Tianqi Liu
 * @version 2016年11月20日
 */
public class Interview {

	/**
	 * 查找算法<br>
	 * 对于二分查找算法要求, 查找前的数据必须是已经排好序的, 然后得到数组的开始位置start和结束位置end, 取中间位置mid的数据a[mid]跟待查找数据key进行比较
	 * @param nums 已经排好序的数组
	 * @param key 要查找的值
	 * @return key所在位置的数组下标
	 */
	public static int binarySearch(int[] nums, int key) {
		int iStart = 0; // 开始位置
		int iEnd = nums.length - 1; // 结束位置
		int iMid = -1; // 中间位置
		while(iStart <= iEnd) { // 依次遍历直到找到数据或者最终没有该条数据
			iMid = (iStart + iEnd) / 2;
			// System.out.println("iMid: " + iMid);
			if(nums[iMid] == key) {
				return iMid; // 已经找到，返回当前位置。若 a[mid] = key 则直接返回当前mid为查找到的位置
			} else if(nums[iMid] > key) { // 若 a[mid] > key, 则取end = mid - 1
				iEnd = iMid - 1;
			} else { // if(nums[iMid] < key) { // 若 a[mid] < key, 则取start = mid + 1
				iStart = iMid + 1;
			}
		}
		return -1; // 未找到
	}
	
	/**
	 * 交换算法<br>
	 * ^是按位异或，两者真值相同则假，真值相反则真。例如a^=b是a=a^b，若a,b值均为真或者均为假，则a为假，反之，则为真。
	 */
	private static void swap(int[] a, int i, int j) {
		a[i] ^= a[j];
		a[j] ^= a[i];
		a[i] ^= a[j];
		System.out.println("a["+i+"]:" + a[i] + " - a["+j+"]:" + a[j]);
	}
	
	// 排序算法：以下所有的排序算法都是从1开始, 而不是从0开始, 有的排序算法会把0位置当作监视哨
	
	/**
	 * 插入排序<br>
	 * 插入排序的基本思想是，
	 * 经过i-1遍处理后,L[1..i-1]己排好序。第i遍处理仅将L[i]插入L[1..i-1]的适当位置，使得L[1..i] 又是排好序的序列。
	 * 要达到这个目的，我们可以用顺序比较的方法。
	 * @param a
	 */
	public static int[] insertSort(int[] a) { // a0为监视哨位置,不作为数据存储
		int len = a.length;
		for (int i = 2; i < len; i++) {
			if(a[i - 1] > a [i]) { // 首先比较L[i]和L[i-1],如果L[i-1] > L[i],则交换L[i]与L[i-1]的位置,否则L[1..i]已排好序
				a[0] = a[i];
				a[i] = a[i - 1];
				int j = 0;
				for (j = i - 2; a[j] > a[0]; j--) { // 继续比较L[i-1]和L[i-2]，直到找到某一个位置j(1≤j≤i-1)，使得L[j] ≤L[j+1]时为止
					a[j + 1] = a[j];
				}
				a[j + 1] = a[0];
			}
		}
		return a;
	}
	
	/**
	 * 选择排序<br>
	 * 选择排序的基本思想是对待排序的记录序列进行n-1遍的处理，第i遍处理是将L[i..n]中最小者与L[i]交换位置。这样，经过i遍处理之后，前i个记录的位置已经是正确的了。
	 * @param a
	 */
	public static int[] selectSort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int j = selectMinKey(a, i); //从i开始a.length中找到最小的位置
	         if (i != j) {
	        	 swap(a, i, j);
	         }
		}
		return a;
	}
	/**
	 * 查找从i开始到a.length中最小的位置
	 * @param a
	 * @param i
	 * @return
	 */
	private static int selectMinKey(int[] a, int i) {
		int key = i;
		for (int j = i + 1; j < a.length; j++) {
			if (a[j] < a[key]) {
				key = j;
			}
		}
		return key;
	}
	
	
	/**
	 * 冒泡排序<br>
	 * 冒泡排序方法是最简单的排序方法。这种方法的基本思想是，将待排序的元素看作是竖着排列的“气泡”，较小的元素比较轻，从而要往上浮。在冒泡排序算法中我们要对这个“气泡”序列处理若干遍。所谓一遍处理，就是自底向上检查一遍这个序列，并时刻注意两个相邻的元素的顺序是否正确。如果发现两个相邻元素的顺序不对，即“轻”的元素在下面，就交换它们的位置。显然，处理一遍之后，“最轻”的元素就浮到了最高位置；处理二遍之后，“次轻”的元素就浮到了次高位置。在作第二遍处理时，由于最高位置上的元素已是“最轻”元素，所以不必检查。一般地，第i遍处理时，不必检查第i高位置以上的元素，因为经过前面i-1遍的处理，它们已正确地排好序。
	 * @param a
	 */
	public static int[] bubbleSort(int[] a) {
		int len = a.length;
		for (int i = 0; i < len - 1; i++) {
			for (int j = 0; j < len - i - 1; j++) {
				if (a[j + 1] < a[j]) {
					swap(a, j + 1, j);
				}
			}
		}
		return a;
	}
	
	/**
	 * 快速排序<br>
	 * 快速排序是对冒泡排序的一种本质改进。它的基本思想是通过一趟扫描后，使得排序序列的长度能大幅度地减少。在冒泡排序中，一次扫描只能确保最大数值的数移到正确位置，而待排序序列的长度可能只减少1。快速排序通过一趟扫描，就能确保某个数（以它为基准点吧）的左边各数都比它小，右边各数都比它大。然后又用同样的方法处理它左右两边的数，直到基准点的左右只有一个元素为止。
	 * @param a
	 * @param low
	 * @param high
	 */
	public static int[] quickSort(int[] a, int low, int high) {
		// 递归快速排序
		int pivotLoc = 0; //中心点
		if (low < high) {
			pivotLoc = partitionLoc(a, low, high);
			quickSort(a, low, pivotLoc-1);
			quickSort(a, pivotLoc+1, high);
		}
		return a;
	}
	/**
	 * 获取到a的下标 low ~ high 中, a[low]的应该放的位置, 即左边的数 < a[low] 右边的数 > a[low]
	 * @param a
	 * @param low
	 * @param high
	 * @return
	 */
	private static int partitionLoc(int[] a, int low, int high) {
		a[0] = a[low];
		while (low < high) {
			while (low < high && a[high] >= a[0]) {
				high--;
			}
			a[low] = a[high];
			while (low < high && a[low] <= a[0]) {
				low++;
			}
			a[high] = a[low];
		}
		a[low] = a[0];
		return low;
	}
	
	/**
	 * TEST METHOD
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {0, 1, 2, 3, 4, 5, 6, 7};
		System.out.println(binarySearch(arr, 7));
		swap(arr, 0, 2);
		int [] arrSort = {0, 3, 5, 1, 2, 4, 9, 8, 6, 7};

		long longStart = System.currentTimeMillis();
		System.out.println("插入排序：" + Arrays.toString(insertSort(arrSort)));
		long longInsert = System.currentTimeMillis();
		System.out.println(bigOperation(longInsert, longStart) + " ms");
		System.out.println("选择排序：" + Arrays.toString(selectSort(arrSort)));
		long longSelect = System.currentTimeMillis();
		System.out.println(bigOperation(longSelect, longInsert) + " ms");
		System.out.println("冒泡跑恤：" + Arrays.toString(bubbleSort(arrSort)));
		long longBubble = System.currentTimeMillis();
		System.out.println(bigOperation(longBubble, longSelect) + " ms");
		System.out.println("快速排序：" + Arrays.toString(quickSort(arrSort, 1, 9)));
		long longQuick = System.currentTimeMillis();
		System.out.println(bigOperation(longQuick, longBubble) + " ms");
	}
	
	private static BigDecimal bigOperation(long a, long b) {
		BigDecimal a1 = new BigDecimal(String.valueOf(a));
		BigDecimal b1 = new BigDecimal(String.valueOf(b));
		return a1.subtract(b1);
	}
}
