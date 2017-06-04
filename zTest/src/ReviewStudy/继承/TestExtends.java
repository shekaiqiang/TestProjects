package ReviewStudy.继承;

/**
 * 继承测试
 * @author Tanken·L 
 * @Start 2016年3月24日
 * @Done  2016年3月24日
 */
public class TestExtends {

	public static int count = 0;
	
	/**
	 * Test Main Method 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		int i = 1;
		int sum = 0;
		while(i < 101) {
			sum += i;
			i++;
		}
		System.err.println(sum);
		TestChild testC = new TestChild();
		try {
			testC.testChild();
		} catch (Exception e) {
			System.out.println((++count) + ": Oh my God! What did you do?! " + e);
		} finally {
			System.out.println((++count) + ": Done.");
		}
	}
}

/**
 * Test Extends
 */
class TestChild extends TestParent {
	
	public void testChild() throws Exception {
		System.out.println("[c]");
		System.out.println((++TestExtends.count) + ": I am the Test Extends method!");
		super.str = "TestChild extends TestParent!";
		super.testParent();
		System.out.println("\n --- " + super.protStr + " --- ");
		System.out.println("[/c]");
		throw new Exception("Wuwuwu~");
	}
}

/**
 * TestParent Method 
 */
class TestParent {
	public String str = "";
	protected String protStr = "protected";
	private String privStr = "private";
	
	public void testParent() throws Exception {
		System.out.println(" --- " + this.privStr + " --- ");
		System.out.println("[p]");
		System.out.println((++TestExtends.count) + ": I am the TestParent method!");
		System.out.println((++TestExtends.count) + ": str is " + str);
		System.out.print("[/p]");
	}
}