package hw2;

import java.util.Arrays;

public class HW2 {

	public static void main(String[] args) {
		//3.
		int[] arr = {1,2,3,4,5,6,7,8};
		System.out.println(Arrays.toString(getAllEvenNumbers(arr)));
		//4.
		int[] arr2 = {1, 2, 3, 6, 0, -1, 5};
		System.out.println(count(arr2, 5));
		
	}
	
	public static int[] getAllEvenNumbers(int[] arr) {
		// Return a collection of all even numbers from ¡®arr¡¯.
		
		//for counting even numbers
		int cnt = 0;							//constant
		for(int i = 0; i< arr.length; i++) {	//C1 N	
			if(arr[i]%2 ==0) {					//C2 N-1
				cnt++;							//C3 N-1
			}
		}
		
		//for inputting even numbers in a new array
		int[] newArr = new int[cnt];
		int j = 0;								//constant	
		for(int i = 0; i< arr.length; i++) {	//C1 N	
			if(arr[i]%2 ==0) {					//C2 N-1												
				newArr[j] = arr[i];             //C3 N-1
				j++;							//C4 N-1
			}
		}
		return newArr;							//constant
	}
	
	public static int count(int[] arr, int item) {
		// Return the # of elements that are <= item

		int cnt = 0;							//constant
		for(int i =0; i<arr.length;i++) {		//C1 N
			if(arr[i] <= item) {				//C2 N-1
				cnt++;							//C3 N-1
			}
		}
		return cnt;								//constant
	}

}
