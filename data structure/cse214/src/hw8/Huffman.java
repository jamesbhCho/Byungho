package hw8;

/*
 * Name: Byungho Cho
 * SBUID: 115236235
 * Do not import anything else.
 * You may add other methods and classes as needed, but do not alter what's given.
 */
import java.util.*; // You can use other classes in this package, but only the ones we learned in class (except PriorityQueue).


public class Huffman {

	private static class Node implements Comparable<Node> {
		Node left, right;
		int ch, freq;

		public Node(int ch, int freq) { 
			this.left = null;
			this.right = null;
			this.ch = ch; //ascii code
			this.freq = freq; //frequency of the chracter in the string  
		}

		public boolean isLeaf() {
			return left == null && right == null;
		}

		@Override
		public int compareTo(Node o) {
			return freq - o.freq;
		}
		
		//contains method for if the left or right node has the character
		public boolean contains(int ch2) {
			if (isLeaf()) {
	            return ch == ch2;
	        }
	        return (left != null && left.contains(ch2)) || (right != null && right.contains(ch2));
		}


	}
	
	//linkedlist implementation of priority queue
	private class LinkedListQueue<E extends Comparable<E>> implements Queueable<Node>{

		private LinkedList<Node> list;

		public LinkedListQueue(){
			list = new LinkedList<>();
		}
		
		//priority enqueue
		//enqueue based on freq of character lowest -> highest
		@Override
		public void enqueue(Node node) {
			int index = 0;					//current freq > list.get(index) freq
			while (index < list.size() && node.compareTo(list.get(index)) > 0) {
				index++;
			}
			list.add(index,node);
		}
		
		//dequeue
		@Override
		public Node dequeue() {
			if (list.isEmpty()) {
				throw new NoSuchElementException();
			}
			return list.removeFirst();
		}
		
		//helper functions
		
		@Override
		public int size() {
			if(list.isEmpty()) throw new NullPointerException();
			return list.size();
		}

		@Override
		public boolean isEmpty() {
			return list.isEmpty();
		}

	}

	private Queueable<Node> pq;
	private Node root;

	public Huffman() {
		pq = new LinkedListQueue<>();
		root = null;
	}

	/*
	 * Perform a Huffman encoding of 'msg', and return a String containing 0s and 1s that
	 * encodes 'msg'. You MUST use a priority queue-based algorithm for this assignment.
	 * 'msg' is guaranteed to consist only of ASCII values (0 - 255). See https://www.asciitable.com/
	 */
	
	/* Documentation:
	 * for this implementation of encode, the logic is to count the frequency of each character in the String
	 * msg and create nodes based on that. Then the nodes are enqued in priority queue manner. That is, it
	 * compares each node's(character) frequency and enqueues from lowest to highest. This procedure would 
	 * make the first two dequeues as the lowest two frequency nodes. With these nodes we can start to 
	 * build the huffman tree by combining the two nodes to make the parent node and keep building up 
	 * while enqueueing the combined form of the data until there is one left in the priority queue which
	 * is the root node of the huffman tree. Once the tree is built, we just have to encode into a binary form.
	 * To do so, for each character in the msg String traverse from the root node to the leaf node and 
	 * append the respective bits to a String builder. By the time this procedure ends, the appended form
	 * will have the correct bits in order.
	 * To surmise, encode() was implemented by creating an array for each ascii code and going through 
	 * the input String and counting the frequency of each character and enqueues the ones that have
	 * frequency of at least one. Then the priority queue will hold from the least frequency to highest.
	 * Therefore, when dequeueing occurs, it will start from the least frequency nodes. Make parent nodes
	 * with the two nodes and enqueue the combined node. Keep doing that until a single node is left.
	 * Then what is left is the actual encoding which can be done by simply traversing from root to the
	 * leaf node for each path.
	 * Time complexity: O(n log n)
	 * There are four loops in total with one being a nested loop.
	 * Because its a side by side loop, the one with the highest time complexity is accounted for.
	 * Therefore, with nested loop running for each character in the message(n), and for each run 
	 * of traversing from root to single leaf node for a given path takes log n, The total time complexity
	 * is O(n log n) with n refering the length of the message.
	 * Space complexity: O(1)
	 * It will be constant as space complexity in this implementation is determined by the priority queue 
	 * and huffman tree which is 256 regardless of the length of the input String.
	 */
	public String encode(String msg) {
	    if (msg == null) {
	        return null;
	    }

	    //calculate frequency for individual ascii values O(n)
	    int[] freq = new int[256];
	    for (int i = 0; i < msg.length(); i++) {
	        freq[msg.charAt(i)]++; //converts charcater into ascii code and increment in accordance
	    }

	    // create leaf nodes for all chracter with freq > 0
	    for (int i = 0; i < 256; i++) {
	        if (freq[i] > 0) {
	            Node node = new Node(i, freq[i]);	//character's position and its frequency 
	            pq.enqueue(node); //enqueues based on freq
	        }
	    }

	    // building huffman tree O(log n)
	    while (pq.size() > 1) {//til the last node
	    	//two lowest freq nodes
	        Node left = pq.dequeue();
	        Node right = pq.dequeue();	
	        Node parent = new Node(-1, left.freq + right.freq);	//combine left and right freq
	        //move up and do the same procedure for all nodes 
	        parent.left = left; 
	        parent.right = right;
	        pq.enqueue(parent);
	    }

	    // set the root of the Huffman tree (last node = root)
	    root = pq.dequeue();

	    //actual encode of the tree
	    String encoded = "";
	    //for each character in the msg, 
	    //append the encoded by traversing the tree from root to its leaf
	    //O(n log n)
	    for (int i = 0; i < msg.length(); i++) {//o(n)
	        int ch = msg.charAt(i);
	        Node node = root;
	        while (!node.isLeaf()) {//left = 0 right = 1 				//O(log n)
	            if (node.left != null && node.left.contains(ch)) {
	            	encoded += "0";
	                node = node.left;
	            } else if (node.right != null && node.right.contains(ch)) {
	            	encoded += "1";
	                node = node.right;
	            }
	        }
	    }
	    return encoded;
	}
	
	/*
	 * Perform decoding of the binary string 'code' using the Huffman tree represented by 'this.root'.
	 * This method should return a null in case the given code cannot be decoded.
	 * (e.g., error in code, or Huffman tree doesn't exist)
	 */
	/* Documentation:
	 *For this implementation of decode, for each bit in the input code, traverse the tree to
	 *its left or right depending on the bit value while updating the pointer. If its a leaf node, 
	 *it means that character has been found. So convert it and append it to the String builder. 
	 * Then update the current pointer to the root. Keep doing this procedure to the length 
	 * of the input code and by the time for loop ends string builder should have all the appended characters. 
	 * To surmise, traverse the huffman tree based on the bit provided. If its 0 go to the left and vice versa. 
	 * When it reaches the leaf node, it append the current character and set the current pointer back to the root.
	 * Repeat til end of the bit is reached for the whole input String.
	 * Time complexity: O(n)
	 * with n refering to the number of bits in the input code 
	 * as decode method aims to go through each bit and append its 
	 * leaf node.
	 * Space complexity: O(n)
	 * it is also O(n) as only space that needs to be allocated is the Stringbuilder and
	 * this fills up until the length of the input.
	 */
	public String decode(String code) {
	    if (code == null) {
	        return null;
	    }

	    if (root == null) {
	        return "";
	    }

	    String decoded = "";
	    Node current = root; 
	    
	    for (int i = 0; i < code.length(); i++) {
	        char bit = code.charAt(i);
	        if (bit == '0') {
	            current = current.left;
	            
	        } else if (bit == '1') {
	            current = current.right;
	        }
	        
	        //concatenate the leaf node's chracter 
	        if (current.isLeaf()) {
	            decoded += (char) current.ch; 
	            current = root;
	        }
	    }
	    return decoded;
	}

	/*
	 * This is just for testing purposes, and you do not have to use it in your implementation.
	 * You can use this method to see what the binary representation of the original string looks like.
	 * It's probably useless in this assignment, but just in case you're curious....
	 */
	public String toBinary(String s) {
		String ret = "";
		for(int i = 0; i < s.length(); i++) 
			ret = toBinary(s.charAt(i)) + ret;
		return ret;
	}

	private String toBinary(int ch) {
		ch = 0xFFFF & ch; // Just want to deal with char's
		String ret = "";
		for(int i = 0; i < 16; i++) {
			ret = (ch & 1) + ret;
			ch = ch >> 1;
		}
		return ret;
	}

	public static void main(String[] args) {
		Huffman h = new Huffman();
		String msg = "There is a pleasure in philosophy, and a lure even in the mirages of metaphysics, which every student feels until the coarse necessities of physical existence drag him from the heights of thought into the mart of economic strife and gain.";
		//String msg = "She sells sea shells by the sea shore.\nThe shells she sells are seashells, I\'m sure.\nAnd if she sells seashells on the seashore\nThen I\'m sure she sells seashells.";
		//String msg = "And I shall have some peace there, for peace comes dropping slow, Dropping from the veils of the morning to where the cricket sings; There midnight\'s all a glimmer, and noon a purple glow, And evening full of the linnet\'s wings.";
		//String msg = "Paying anything to roll the dice, just one more time. Some will win, some will lose, some are born to sing the blues. Oh the movie never ends it goes on and on and on and on.";
		//String msg = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
		//String msg = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaazzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
		//String msg = "(A U B) n C = (A n C) U (A n B)";
		//String msg = "azazazazazazazazazazazazazazazazazazazazazazazazazazazazazazazazaz";
		//System.out.println(h.toBinary(msg).length());
		String code = h.encode(msg);
		System.out.println(code);
		System.out.println(h.decode(code));
		assert(h.decode(code).equals(msg)); // Original message should be reconstructed.
		assert(code.length() < msg.length() * 16); // Code should be compressed.
	}
}

/*
 * Priority queue implementation.
 */
interface Queueable<E extends Comparable<E> > {
	public void enqueue(E obj);
	public E dequeue();

	public int size();
	public boolean isEmpty();
}
