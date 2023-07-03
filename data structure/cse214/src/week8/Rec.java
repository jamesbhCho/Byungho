package week8;

public class Rec {

	public static void main(String[] args) {//recursion continued 
		int[] min = {3,1,6,-2};
		System.out.println(min(min));
		char c = '1';

		System.out.println(c-'0'); 
		System.out.println(parseInt("1234")+1);

	}

	/*min
	 * {3,1,6,-2}
	 * 3 vs {1,6-2}
	 * min{11,6,12} vs 3
	 * min(3,11,6,12) = smaller(3,min(11,6,12)
	 */

	public static int min(int[] arr) {
		if(arr.length ==1) {
			return arr[0];
		}
		int[] newArr = new int[arr.length-1];
		System.arraycopy(arr, 1, newArr, 0, arr.length-1);
		return Math.min(arr[0], min(newArr));
	}

	//integer.parseInt
	/*
	 * base case = length == 1
	 * return a[0]-'0'
	 * int a = 1*10^3(length-1)
	 * a + parseInt("234")
	 */

	public static int parseInt(String str) {

		int num = str.charAt(0)-'0';
		if(str.length()==1) {
			return num;
		}
		int simple = (int) (num * Math.pow(10, str.length()-1));
		return simple + parseInt(str.substring(1));
	}

}
