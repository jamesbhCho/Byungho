package week11;

public class RadixSort {  

	int getMax(int a[], int n) {  
		int max = a[0];  
		for(int i = 1; i<n; i++) {  
			if(a[i] > max)  
				max = a[i];  
		}  
		return max; //maximum element from the array  
	}  

	void countingSort(int a[], int n, int place) // function to implement counting   
	/*
	 * what is (a[i] / place) % 10?
	 * 
	 */
	{  
		int[] output = new int[n+1];  
		int[] count = new int[10];  //1 for rach digit

		// counting the current place for every single digit pointed by the place value 
		for (int i = 0; i < n; i++)  //for each element 
			count[(a[i] / place) % 10]++;   //352/10 * 10  = 5 //extracts the second digit 

		// Calculate cumulative frequency  
		for (int i = 1; i < 10; i++)  
			count[i] += count[i - 1];  

		// Place the elements in sorted order  
		for (int i = n - 1; i >= 0; i--) {   
			output[count[(a[i] / place) % 10] - 1] = a[i];  
			count[(a[i] / place) % 10]--;  
		}  

		for (int i = 0; i < n; i++)  
			a[i] = output[i];  
	}  

	// function to implement radix sort  
	void radixsort(int a[], int n) {  

		// get maximum element from array  
		int max = getMax(a, n);  

		// Apply counting sort to sort elements based on place value  
		for (int place = 1; max / place > 0; place *= 10)  //place goes through each digit O(k) k = maximum number of digits
			countingSort(a, n, place);  //O(n)
	}  

	// function to print array elements  
	void printArray(int a[], int n) {  
		for (int i = 0; i < n; ++i)   
			System.out.print(a[i] + " ");  
	}  

	public static void main(String args[]) {  
		int a[] = {151, 259, 360, 91, 115, 706, 34, 858, 2};  
		int n = a.length;  
		RadixSort r1 = new RadixSort();  
		System.out.print("Before sorting array elements are - \n");  
		r1.printArray(a,n);  
		r1.radixsort(a, n);  
		System.out.print("\n\nAfter applying Radix sort, the array elements are -\n");  
		r1.printArray(a, n);  
	}  
}  