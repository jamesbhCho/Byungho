package week2;

public class RecHw {

	public static void main(String[] args) {
		//test code
		chickenBreast cb = new chickenBreast("Chicken Breast", 250, "protein");
		System.out.println(cb.getName());
		System.out.println(cb.getCalorie());
		System.out.println(cb.getType());
		System.out.println(cb.goodFood(cb.getCalorie(),cb.getType()));

	}
}

abstract class food{
	private String name;
	private int calorie;
	private String type;

	//constructor
	public food(String n, int cal, String ty) {

		this.name = n;
		this.calorie = cal;
		this.type = ty;
	}

	public String getName() {
		return this.name;
	}

	public int getCalorie() {
		return this.calorie;
	}

	public String getType() {
		return this.type;
	}


	//abstract method
	public abstract Boolean goodFood(int x, String n);
}

class chickenBreast extends food{

	public chickenBreast(String n, int cal, String ty) {
		super(n, cal, ty);

	}
	public Boolean goodFood(int x, String n) {
		if(x<300 && n.equals("protein")) {
			return true;
		}else {
			return false;
		}

	}


}
