package ReviewStudy.集合;

import java.util.ArrayList;

public class Collection {
	
	public static void main(String[] args) {
		Collection coll = new Collection();
		coll.testList();
		coll.testArray();
	}

	public void testList() {
		ArrayList<String> alist = new ArrayList<String>();
		alist.add("String0");
		alist.add("String1");
		alist.add("String2");
		alist.add(3, "String2");
		
		boolean isEmpty = alist.isEmpty();
		boolean isIn = alist.contains("String0");
		int index = alist.indexOf("String1");
		int alSize = alist.size();
		
		System.out.println("集合是否为空：" + isEmpty + " \t 集合大小：" + alSize 
			+ " \t 是否存在字符串String0：" + isIn 
			+ " \t String1在集合中的位置下标：" + index);
	}
	
	public void testArray() {
		String[] strArr = new String[6];
		strArr[0] = "str0";
		System.out.println(strArr.length);
	}
}
