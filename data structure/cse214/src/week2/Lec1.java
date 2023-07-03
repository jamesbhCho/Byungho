package week2;

public class Lec1 {

	public static void main(String[] args) {
		Derived d = new Derived();
		System.out.println(d.x);
	}
}

class base {
	int x;
	public base() {
		System.out.println("default");
		x = 1;
	}

	public base(int y) {
		System.out.println("not default");
		x = y;
	}

	//overriding
	public int get() {
		return 0;
	}
}

class Derived extends base{

	public Derived() {
		System.out.println("child");
	}

	//overloading same name diff params
	public void foo(int z) {}
	public void foo(double d) {}

	//overriding same name same params(Should have the same access modifiers)
	public int get() {
		return 1;
	}
	
	//overriding equals method
	public boolean equals(Object o) {
		//change equals method
		return false;
		
	}
	
}

//abstract class
abstract class Car{
	int gear;
	//abstract class/methods cannot be instantiated
	//abstract methods do not have a body
	
	public abstract int getEngineCycle();
}







