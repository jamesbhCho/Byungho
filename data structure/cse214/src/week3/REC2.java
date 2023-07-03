package week3;

/**
 * Name: Byungho Cho
 * SBU ID: 115236235
 */

public class REC2<K> {

	//array of K
	K[] arr;

	//constructor
	public REC2(K[] base) {//accepts one parameter named 'base' which is an array of K's 
		this.arr = base; 	//The content of the 'base' should be copied to 'arr'
	}

	public K getNth(int n) {//return the 'n'-th item in 'arr'
		if(n<0 || n >= arr.length){
			System.out.println("index out of range");
		}

		return arr[n];
	}

	public void setNth(int n, K item) {//store 'item' to the 'n'-th position of 'arr'
		if(n<0 || n >= arr.length){
			System.out.println("index out of range");
		}
		arr[n] = item;
	}

	public static void main(String[] args) {

		String[] a = {"hello","this","is","a","test"};
		REC2<String> rec2 = new REC2<String>(a);
		System.out.println(rec2.getNth(4));
		rec2.setNth(4, "not a test");
		System.out.println(rec2.getNth(4));
		System.out.println();


	}
}
