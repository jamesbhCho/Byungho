package week3;

public class Rec2Lec {//Generic

	public static void main(String[] args) {
		//instantiating geneerics

		Pair<String, Integer> p = new Pair<String,Integer>();
		p.put("One", 1);
		System.out.println(p.get("One"));

	}

}

//type unknown(generic), however it is specified when the class is instantiated 
//no string methods allowed nor constructor 
// object methods are allowed(ex- equal)
class Pair<K,V>{
	K first;
	V second;

	public Pair() {

	}

	public void put(K first, V second) {
		this.first = first;
		this.second = second;
	}

	public V get(K first) {
		if(this.first.equals(first)) {
			return second;
		}
		return null;
	}

	//generic methods 
	public static<T> void print(T[] arr) {
		for(T elem : arr) {
			System.out.println(elem);
		}
	}

}


//bounded type 
//provides restrictions on generic types for when you only want to receive numeric types
//ex) class Pair<K,V extends Number>{...} 
//type V must be something that is a subclass of Number(numeric type)
//can also restrict the subclass by using 'super' not used often tho
//with this V can use numeric methods 





