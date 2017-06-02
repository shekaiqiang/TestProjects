package ReviewStudy.循环;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 冒泡排序：比较两个相邻的元素，将值大的元素交换至右端
 * @author Tanken·L
 * @version 2016年11月15日
 * @Revis  2016年11月15日
 */
public class BubbleSort {

	private int[] arr = {7, 1, 5, 9, 6, 4, 2, 8, 0, 3};
	
	/**
	 * 升序冒泡
	 */
	public void ascSort() {
		int sumCount = 0;
		// 外层循环控制排序次数
		for (int i = 0; i < arr.length - 1; i++) {
			int iCount = 0;
			// 内层循环控制每一次排序多少次
			for(int j = 0; j < arr.length - 1 - i; j++) { 
				if(arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
				sumCount++;
				iCount ++;
			}
			System.out.println("循环 " + (i + 1) + "：排序" + iCount + "次 \t 顺序：" + Arrays.toString(arr));
		}
		System.out.println(Arrays.toString(arr) + " " + sumCount);
		//Arrays.sort(arr, );
		System.out.println(Arrays.toString(arr));
	}
	
	public static void main(String[] args) {
		BubbleSort bubSort = new BubbleSort();
		bubSort.ascSort();
	}
}

interface SelfComp extends Comparator<Integer> {
}
