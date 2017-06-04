package ReviewStudy.封装;

/**
 * 封装测试
 * @author Tanken·L
 * @Start 2016年3月24日
 * @Done  2016年3月24日
 */
public class Packages {

	private int iParam = 0;
	private String sNulVal;
	
	public void setParam(int i) {
		this.iParam = i;
	}
	
	public int getParam() {
		return this.iParam;
	}
	
	public void setNulVal(String s) {
		this.sNulVal = s;
	}
	
	public String getNulVal() {
		return this.sNulVal;
	}
	
	/**
	 * Test Packages Method
	 * @param args
	 */
	public static void main(String[] args) {
		Packages pak1 = new Packages();
		pak1.setParam(100);
		Packages pak2 = new Packages();
		pak2.setParam(200);
		System.out.println(pak1.getParam() + pak2.getParam());
		System.out.println(pak1.sNulVal);
	}
}
