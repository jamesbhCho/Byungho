package hw10;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Test {

	public static void main(String[] args) {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		Iterator it = ll.iterator();
		System.out.println(it.next());
	}

}
