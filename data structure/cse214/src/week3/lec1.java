package week3;

public class lec1 {//complexity measures

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

//analyze algorithms
// "predicting the resources that the algorithm requires"

/*
 * Standard model of environment
 *  Single CPU
 * 	Non-parallel instruction execution(run multiple instruction at the same time)
 * 	Arithmetic(+-/*), data movement (loading, storing, copy)
 * 	and control instructions(if-else, branching)
 *  - i.e., no unrealistic single-step instructions like sorting(not going to assume these exists)
 * 
 * 
 * algorithm performance
 * Three most important features of algorithm implementation
 * 	correctness - most important
 * 	speed, memory usage
 * 
 * Speed 
 * how fast the algorithm run
 * how much does the running time increase with data
 * 
 * memory usage
 * how much memory does it use
 * how does it scale with data 
 * 
 * algorithm execution time
 * wall-clock time
 * 	literally measure the execution time with a clock
 * 
 * two problems
 * clock inaccuracy 
 * no analytical result w.r.t. input 
 *  how will the run-time change as a function of input size?
 *   what part of the algorithm consumes most time?
 * 
 * ex) insertion sort
 * int[] = {4,6,2,1,0,-3,5,8}
 * for(int j = 1; j< arr.length; j++){			N times (arr.length) 8 
 * 		int key = arr[j];						N-1
 * 		int i = j-1;							N-1
 * 		while(i >= 0) && arr[j] > key){			X number of times it meets the condition
 * 			arr[i+1) = arr[i];					Y
 * 			i--;								Y
 * 		}
 * arr[i+1] = key;								N-1
 * }
 * 
 */
