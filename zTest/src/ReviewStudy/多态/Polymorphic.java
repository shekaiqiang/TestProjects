package ReviewStudy.多态;

public class Polymorphic {
    public static void main(String args[]){
        Animal animal = new Animal("animal");
        Cat cat = new Cat("cat", "blue");
        Dog dog = new Dog("dog", "black");
        // 多态 
        Lily l1 = new Lily("l1", animal);
        Lily l2 = new Lily("l2", cat);
        Lily l3 = new Lily("l3", dog);
        
        l1.myAnimalEnjoy();
        l2.myAnimalEnjoy();
        l3.myAnimalEnjoy();
    }
}

@SuppressWarnings("unused")
class Lily {
	private String name;
    private Animal animal;
    public Lily(String name, Animal animal){
        this.name = name;
        this.animal = animal;
    }
    public void myAnimalEnjoy(){
        animal.enjoy();
    }
}

class Animal{
    private String name;
    public Animal(String name){
        this.name = name;
    }
    public void enjoy(){
        System.out.println(this.name + " 叫声~~~");
    }
}

@SuppressWarnings("unused")
class Dog extends Animal{
	private String forlorColor;
    public Dog(String name,String forlorColor){
        super(name);
        this.forlorColor = forlorColor;
    }
    public void enjoy(){
        System.out.println("dog 叫声~~~");
    }
}

@SuppressWarnings("unused")
class Cat extends Animal{
	private String eyesColor;
    public Cat(String name,String eyesColor){
        super(name);
        this.eyesColor = eyesColor;
    }
    public void enjoy(){
        System.out.println("cat 叫声~~~");
    }
}