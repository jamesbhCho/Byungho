package midterm;

import java.util.NoSuchElementException;
import java.util.Stack;

public class Midterm1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/*
	 consider a linkedArrayList where the nodes of a linked list are stored in an array list 
	 in order while still maintaing the prev and next pointers. That is, index 0 of the array 
	 list contains the first node, index 1 of the array list contains the second node and so on. 
	 implemen the following methods
	 */

	public class LinkedArrayList {
		private class Node{
			int data;
			Node prev, next;
			public Node(Node p, int d, Node n) {
				this.prev = p;
				this.data = d;
				this.next = n;
			}
		}
		private Node head,tail;
		Node[] arr; //assume this is initialized elsewhere
		public void insert(int index, int data) {
			// Check if the index is valid
		    if (index < 0 || index > arr.length)
		        throw new IndexOutOfBoundsException();

		    // Create a new node with the given data
		    Node newNode = new Node(null, data, null);

		    // If the list is empty
		    if (head == null) {
		        head = tail = newNode;
		        arr[0] = newNode;
		    } else {
		        // If the index is at the beginning
		        if (index == 0) {
		            newNode.next = head;
		            head.prev = newNode;
		            head = newNode;
		            arr[0] = newNode;
		        }
		        // If the index is at the end
		        else if (index == arr.length) {
		            newNode.prev = tail;
		            tail.next = newNode;
		            tail = newNode;
		            arr[index] = newNode;
		        }
		        // If the index is in the middle
		        else {
		            Node current = arr[index - 1];
		            Node nextNode = current.next;

		            newNode.prev = current;
		            newNode.next = nextNode;

		            current.next = newNode;
		            nextNode.prev = newNode;

		            arr[index] = newNode;
		        }
		    }
		}
		public void remove(int index) {
			// Check if the index is valid
		    if (index < 0 || index >= arr.length)
		        throw new IndexOutOfBoundsException();

		    // If the list is empty
		    if (head == null)
		        throw new NoSuchElementException();

		    // If the list has only one element
		    if (head == tail) {
		        head = tail = null;
		        arr[0] = null;
		        return;
		    }

		    // If the index is at the beginning
		    if (index == 0) {
		        Node nextNode = head.next;
		        nextNode.prev = null;
		        head.next = null;
		        head = nextNode;
		        arr[0] = null;
		        return;
		    }

		    // If the index is at the end
		    if (index == arr.length - 1) {
		        Node prevNode = tail.prev;
		        prevNode.next = null;
		        tail.prev = null;
		        tail = prevNode;
		        arr[index] = null;
		        return;
		    }

		    // If the index is in the middle
		    Node current = arr[index];
		    Node prevNode = current.prev;
		    Node nextNode = current.next;

		    prevNode.next = nextNode;
		    nextNode.prev = prevNode;
		    current.prev = null;
		    current.next = null;
		    arr[index] = null;
		}
	}
	
	/*long answers
	 * write a method that returns the number of elements in a stack given
	 * an argument without using the size() method.
	 * Do not alter thegiven Stack.
	 */
	
	public static <T> int getStackSize(Stack<T> stack) {
	    Stack<T> tempStack = new Stack<>();
	    int count = 0;
	    while (!stack.isEmpty()) {
	        tempStack.push(stack.pop());
	        count++;
	    }
	    while (!tempStack.isEmpty()) {
	        stack.push(tempStack.pop());
	    }
	    return count;
	}
	
/*
 * java's stack inherits the vector data structure. 
 * As such it provides a number of unintended features. 
 * Decribe in detail how you would prevent this 
 * if you were in charge of designing the stack class(still have to extend vector class)
 * 
 * Override Vector Methods: For the methods that need to be exposed in the Stack class, 
 * I would override them to provide the desired stack behavior. For example, 
 * I would override the add method to throw an UnsupportedOperationException, 
 * as adding elements to a stack using arbitrary indices goes against the stack's Last-In-First-Out (LIFO) behavior.
 */
}
