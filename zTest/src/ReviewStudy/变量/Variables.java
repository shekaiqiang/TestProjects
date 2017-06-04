package ReviewStudy.变量;

/**
 * 变量测试
 * @author Tanken·L
 * @Start 2016年3月25日
 * @Done  2016年3月25日
 */
public class Variables {
	// Global 
	private static int i;
	private static byte by;
	private static float f;
	private static double d;
	private static boolean b;
	private static String s;
	private static int[] iArr;

	public static void main(String[] args) {
		// Global 
		System.out.println("noInitInt: " + i);     // 0
		System.out.println("noInitByte: " + by);   // 0
		System.out.println("noInitFloat: " + f);   // 0.0
		System.out.println("noInitDouble: " + d);  // 0.0
		System.out.println("noInitBoolean: " + b); // false
		System.out.println("noInitString: " + s);  // null
		System.out.println("noInitArray: " + iArr);// null
		// Part 
		int i = 37; // Must be initialized! 
		System.out.println(i);
	}
}
