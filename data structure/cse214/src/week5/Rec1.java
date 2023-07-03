package week5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Rec1 {

	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<String>();
		a.add("he");
		a.add("bla");
		
		LinkedList<String> b = new LinkedList<String>();
		b.add("he22");
		b.add("bla22");
		
		//shouldn't be used to modify
		//its just a snapshot of the data
		//used to access the elements 
		Iterator<String> it = a.iterator();
		int cnt = 0;
		while(it.hasNext()) {
			String nextElem = it.next();
			System.out.println(cnt + nextElem);
			cnt++;
		}
		Iterator<String> it2 = b.iterator();
		while(it2.hasNext()) {
			String nextElem2 = it2.next();
			System.out.println(nextElem2);
		}
		
		ListIterator<String> lit = a.listIterator();
		while(lit.hasNext()) {
			String nextElem3 = lit.next();
			System.out.println(nextElem3);
		}
	}

}
