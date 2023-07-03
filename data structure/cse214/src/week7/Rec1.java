package week7;

import java.util.Queue;

public class Rec1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//pop quiz
	public static int get(int index, Queue<Integer> q) {
		
		int cnt = q.size();
		int arr[] = new int[cnt];
		if(index>= q.size() || index<0) {
			throw new IndexOutOfBoundsException();
		}
		
		for(int i = 0; i<q.size(); i++) {
			//arr[i] = q.dequeue();
		}
		
		for(int j = 0; j<arr.length; j++) {
			//q.enqueue(arr[i]);
		}
		return arr[index];
	}
	
	//infix2postfix
	//postfix2infix
	

}
