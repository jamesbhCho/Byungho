package week2;

import java.util.Comparator;

public class Lec2 {//interface


	public static void main(String[] args) { 

		//interface can be instantiable using the implemented classes associated with the interface
		Drivable d = new Truck();
		d.getEngineCycle();

		//static variable can be called without instantiating the object
		//however cannot access other methods within the class
		int a = Truck.y;
		System.out.println(a);

		//interface Comparable
		//how to internally use compareto
		Truck t = new Truck();
		Truck t2 = new Truck();
		t.compareTo(new Truck());

		//util Comparator
		Comparator<Truck> ct = new BaseComparator();
		if(ct.compare(t, t2)<0) {
			System.out.println("t2 is greater");
		}

		//instantiating objects
		Students[] cls = new Students[10];
		for(int i = 0; i<cls.length; i++) {
			cls[i] = new Students();//have to instance because when the array is called it is set as null
			System.out.println("created student with id " + cls[i].getID());
		}

		//nested class
		Outer.StaticInner si = new Outer.StaticInner();//instantiating the static inner only(because it is static you can use the .)
		Outer o = new Outer();
		Outer.Inner in = o.new Inner();//instantiating the inner class
		System.out.println(si.getVal());
		System.out.println(in.getVal());

		//type casting
		int x =3;
		float fl = (float)x;
		
		//type casting objects
		/*
		 * Lec2 lec2 = new Lec2(); Derived1 der = new Derived1(); Base b = new Base();
		 * //lec2.foo(b); classCastexception lec2.foo(der); }
		 * 
		 * //type casting public void foo(Base b) { Derived1 d = (Derived1) b;//b is a
		 * reference o derived }
		 */

		//polymorphism
		
		//encapsulation
		
		//generics
	}

}

//in order to force "consistency" in the source code
//interface do't allow private method
//default setting is public 
//convention = add ~able for interface
//can implement multiple interfaces(abstract classes can't)

interface Drivable {//you can also use "extends" between interfaces

	public int getEngineCycle();
	default boolean haveWheels() {//provide implementation. don't have to implement in the child class(regular method that "can" be used when implemented)
		return true;
	}

}

class Truck implements Drivable, Steerable, Comparable, Comparator{//[comparable] original form must be in generic version 

	static int y = 0;
	private int direction = 0;
	private int nWheels = 0;
	public int getEngineCycle() {
		return 4;
	}

	public void steerLeft() {
		direction = 1;
	}

	public void steerRight() {
		direction = -1;
	}

	//a method in interface comparable
	public int compareTo(Object o1) {//usually a type generic 
		Truck t1 = (Truck) o1;
		//compapring the given object with current object
		if(t1.nWheels > this.nWheels) {
			return -1;
		}else if(t1.nWheels < this.nWheels) {
			return 1;
		}
		return 0;
	}

	// a method in interface comparator
	public int compare(Object o1, Object o2) {
		int wheels = 0;
		Truck t1 = (Truck) o1;
		Truck t2 = (Truck) o2;
		wheels = t2.nWheels - t1.nWheels;
		return wheels;

	}
}

class BaseComparator implements Comparator<Truck>{

	public int compare(Truck o1, Truck o2) {
		// TODO Auto-generated method stub
		return 0;
	}

}

class Motorcycle implements Drivable{
	public int getEngineCycle() {
		return 2;
	}
}

interface Steerable{
	public void steerLeft();
	void steerRight();

}

//final 
//fields: value cannot change(constant)
//methods: cannot be overridden
//classes: cannot be extended 
//scope: when the program runs 

final class Student{
	private static int IDgen = 0;			//single copy of the variable exists- in the static memory 
	private int ID;
	public static final String CLS_NAME = "Student";

	public Student() {
		ID = IDgen++;
	}
	public final int getID() {
		return ID;
	}
}

//static modifier
class Students{
	private static int IDgen = 0;			//single copy of the variable exists- in the static memory 
	private int ID;
	public static final String CLS_NAME = "Student";

	public Students() {
		ID = IDgen++;
	}
	public final int getID() {
		return ID;
	}
}

//nested class
//the inner class will have access to everything of the outer class
class Outer{
	private int x;
	private static int z = 7;
	public Outer() {
		x = 5;
	}
	static class StaticInner{
		int y;
		public StaticInner() {
			y = z+1;
		}
		public int getVal() {
			return y;
		}
	}
	class Inner{
		int y;
		public Inner() {
			y = x+1;
		}
		public int getVal() {
			return y;
		}
	}
}


/*
 * class Base{
 * 
 * }
 */

/*
 * class Derived1 extends Base{
 * 
 * }
 */











