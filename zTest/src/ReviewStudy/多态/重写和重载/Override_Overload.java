package ReviewStudy.多态.重写和重载;

/**
 * @author Tanken·L
 * @Revis  2016年11月15日
 * 重载：在同一个类中方法名相同，参数不同，返回值可相同可不同。
 * 重写：在子类中有和父类相同的方法（方法名、参数和返回值都相同），实现方式或功能可以有所不同
 */
public class Override_Overload extends Animal {

	public static void main(String[] args) {
		Override_Overload oRoL = new Override_Overload();
		oRoL.enjoy();
	}
	
	@Override
	public void enjoy() {
		System.out.println(" Override");
	}
	
	public String enjoy(String str) {
		return " ---" + str + " --- ";
	}
}

class Animal {
    private String name;
    public Animal(String name){
        this.name = name;
    }
    public void enjoy(){
        System.out.println(this.name + " 叫声~~~");
    }
    public Animal() {}
}

