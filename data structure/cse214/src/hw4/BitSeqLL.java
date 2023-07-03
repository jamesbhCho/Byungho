package hw4;

/**
 * Name: Byungho Cho
 * SBU ID:115236235
 * Do not add any imports other than the ones we supplied, even if you don't use them.
 * Do not modify any of the given code, although you are free to add your own fields, methods, and classes.
 * Do not copy code that is not yours, even the ones given in class.
 */

public class BitSeqLL {
	/*
	 * This is a sequence of bits, where 'true' is 1 and 'false' 0.
	 * You MUST use the given Node to represent your bit string as a linked list.
	 */
	private Node<Boolean> head; 

	/*
	 * An empty 's' means it's just a single '0'
	 */
	public BitSeqLL(String s) {
		if(s.isEmpty()) {//single 0
			head = new Node<Boolean>(null, false);
		}else {
			head = new Node<Boolean>(null, s.charAt(0)=='1'); //first val 
			Node<Boolean> current  = head;
			for(int i = 1; i<s.length(); i++) {
				current.next = new Node<Boolean>(null, s.charAt(i)=='1');//link next items to first val 
				//new current
				current = current.next;
			}
		}
	}

	/*Documentation:
	 * The implementation behind the constructor is to create a first link via the first chracter of the string
	 * and link the next node in accordance to the next string character until the the loop reaches the end 
	 * of the string charcter. That is, it first checks if the string is empty and if not entrench the first
	 * character of the String as the head and then links the rest of the character via for loop which is iterated 
	 * over rest of the String character while creating new node to link to.
	 * Time complexity: O(n)
	 * The conditional statement is constant while the for loop traverse through the entire length of the String.
	 * Thus, with n refering to input String s, the total time complexity for this code is O(n).
	 */

	/*
	  Return the number of bits in this string, except the padded 0s in the front.
	 */ 
	public int length() {
		int cnt = 0;
		Node<Boolean> current = head;
		Boolean firstTrue = false;
		while(current != null) {
			if(current.data && !firstTrue){//if first true is found(true&& false)
				firstTrue = true;
				cnt++;
			}else if(current.data){//if bit true and after the set of first true
				cnt++;

			}else if(!current.data && firstTrue) {
				cnt++;
			}
			current = current.next;
		}
		return cnt;
	}

	/*
	 * Documentation:
	 * The code for length returns the number of bits minus the padded 0s in the front. To implement this
	 * it needs to check for the first occurance of the true. That is, if false exists before the first 
	 * occurance of the true that means those are padded 0s which should be ignored and after the 
	 * first occurance of true is found, any false values should not be ignored. Thus, there are two 
	 * occurances where the number of bits should be counted: if it is true, if false but first true has 
	 * been found.
	 * Time complexity: O(n)
	 * With n refering to the total sequence of nodes. That is, the loop will iterate as many as 
	 * total nodes as it will have to traverse the entire sequence of nodes to detect node that matches
	 * certain criteria.
	 */

	@Override
	public boolean equals(Object obj) {
		if( obj == this) {
			return true;
		}

		if(obj == null) {//nullpointerexception
			return false;
		}

		Node<Boolean> current = head;
		BitSeqLL ob = (BitSeqLL) obj;
		Node<Boolean> other = ob.head;


		//setting node at first true 
		while(current != null && !current.data) {
			current = current.next;
		}

		while(other != null && !other.data) {
			other = other.next;
		}
		//starting from their first true
		while(current != null && other != null) {
			if(current.data != other.data) {
				return false;
			}
			current = current.next;
			other = other.next;
		}
		return true;
	}

	/*Documentation:
	 * for this implementation of overriding equals method, it first checks for reference and nulls.
	 * From then on, the objective is to locate the first true within the sequence of nodes and iterate
	 * from the first true and compare. That is what I implemented for the first two while loops. They 
	 * set the current node to the first true. The last while loop compares each object starting 
	 * from the first true and compare. This implementation ignores the padded 0s and compares
	 * the sequence of nodes.
	 * Time complexity: O(n)
	 * The equals() is composed of side by side loop which adheres for the one with the highest term
	 * in regards to the Big-O. First while loop runs til the first true so its O(n). with n refering
	 * to total number of bit sequence minus the first true. The second one is the same as above.
	 * As for the last while loop it is tantamount to the number of bit sequence in worst case.
	 * Therefore, with n refering to the total bit sequence, it is O(n).
	 */

	public void and(BitSeqLL b) {

		Node<Boolean> current = this.head;
		Node<Boolean> other = b.head;
		int len1 = 0;
		int len2 = 0;

		//for counting length 
		while(current!= null) {
			len1++;
			current = current.next;
		}
		while(other!= null) {
			len2++;
			other = other.next;
		}
		// Pad 0s for whichever one is the shortest 
		if (len1 > len2) {
			for (int i = 0; i < len1 - len2; i++) {//o(n)
				b.head = new Node<Boolean>(b.head, false);
			}
		} else if (len2 > len1) {
			for (int i = 0; i < len2 - len1; i++) {
				head = new Node<Boolean>(head, false);
			}
		}

		//no connection between above current, thus it has not been moved
		// Perform the AND operation on each corresponding bit
		Node<Boolean> curr = head;
		Node<Boolean> oth = b.head;
		while (curr != null && oth != null) {
			curr.data = curr.data & oth.data; //AND operation
			curr = curr.next;
			oth = oth.next;
		}
	}

	/*Documentation:
	 * AND operation requires both bits to be same in length. One with the lesser length
	 * should be padded with 0 in accordance. After it is padded with 0s, both bits should
	 * be iterated simultaneously and perform bit AND operation on each sequence. Thus, the 
	 * code first checks for the length of each bit sequences then it padds whichever one is 
	 * shorter. After that, both bits are looped and the former object is reset by the and operation.
	 * Simply put, the current object overriden with the new bit sequence between the former and the latter objects.
	 * Thus, the code first finds the lenfth and it pads the 0s then it performs AND operation
	 * which is overriden to the current object.
	 * Time complexity: O(n)
	 * For any of the loop in this code, the worst case is O(n) with n refering to the total number of nodes.
	 */

	public void or(BitSeqLL b) {
		Node<Boolean> current = this.head;
		Node<Boolean> other = b.head;
		int len1 = 0;
		int len2 = 0;

		//for counting length 
		while(current!= null) {
			len1++;
			current = current.next;
		}
		while(other!= null) {
			len2++;
			other = other.next;
		}

		// Pad 0s for whichever one is the shortest 
		if (len1 > len2) {
			for (int i = 0; i < len1 - len2; i++) {
				b.head = new Node<Boolean>(b.head, false);
			}
		} else if (len2 > len1) {
			for (int i = 0; i < len2 - len1; i++) {
				this.head = new Node<Boolean>(this.head, false);
			}
		}

		//no connection between above current, thus it has not been moved
		// Perform the OR operation on each corresponding bit
		Node<Boolean> curr = this.head;
		Node<Boolean> oth = b.head;
		while (curr != null && oth != null) {
			curr.data = curr.data || oth.data; //OR operation
			curr = curr.next;
			oth = oth.next;
		}
	}

	/*Documentation:
	 * The implementation of OR is exactly tantamount to the above AND method. Except for 
	 * the actual operation of OR where the operator is '||' in this case. Similiar to the
	 * explanation for the AND method, the logic is to find the length of each bit sequences,
	 * pad whichever one is shorter with 0, then iterate through them at the same time while
	 * comparing each bit sequence with or operator.
	 * Time complexity: O(n)
	 * For any of the loop in this code, the worst case is O(n) with n refering to the total number of nodes.
	 */

	public void not() {
		Node<Boolean> current = head;
		while(current != null) {
			current.data = !current.data;
			current = current.next;
		}
	}

	/*Documentation:
	 * this implementation of not is simply turning the opposite of the bit sequence.
	 * Therefore, the logic is to traverse through the sequence of bits and set each
	 * element the oppsite of its value. 
	 * Time complexity: O(n)
	 * It is O(n) with n refering to the total number of bit sequences. That is, n is
	 * sequence of nodes. Therefore, it is O(n) with n being the number of bit sequences.
	 */

	/*
	 * 'n' is guaranteed to be non-negative for all shifting/rotating operations.
	 */
	public void shiftRight(int n) {

		int shift = n;
		Node<Boolean> forLen = head;

		int len = 0;
		while(forLen != null) {
			len++;
			forLen = forLen.next;
		}

		if(n == 0) {//if no rotate 
			return;
		}else if(n == len) {//if it rotate n times which is itself
			return;
		}else  if (n > len) {//if shifting is over the length
			n = n - len;
			shift = n;//number for bits that got carried over the length
		}
		
		//the number of actual elements to be shifted and its the new tail
		n = len - n;
		//index 0
		int cnt = 1;
		Node<Boolean> current = head;
		//find the new tail 
		while(current != null && cnt < n) {
			cnt++;
			current = current.next;
		}
		//new tail node
		Node<Boolean> nthNode = current;		
		Node<Boolean> newNode = head;
		nthNode.next = null;
		//pad 0s
		for(int i = 0; i<shift; i++) {
			newNode.next = new Node<Boolean>(newNode.next, false);
			newNode = newNode.next;
		}
	}
	/*Documentation:
	 * for the implementation of shiftRight(n), we first need to identify where the new tail is which can be found 
	 * by total number of nodes minus n. However, on occasion where n is greater than len, we want the n to equal 
	 * to a situation where n is not greater n. For example shiftRight(8) should work similarly to shiftRight(2).
	 * After finding where the tail is set it to new tail and start padding 0s from the head as many as the number
	 * of bits that got carried over. 
	 * Time complexity: o(n)
	 * In the worst case for any of the loop, the iterations will traverse through the entire sequence of nodes. Therefore,
	 * it is O(n) with n refering to total number of nodes.
	 */


	public void shiftLeft(int n) {//011011 -> 101100
		
		Node<Boolean> forLen = head;

		int len = 0;
		while(forLen!= null) {
			len++;
			forLen = forLen.next;
		}
		//index 0
		int cnt = 1;

		if(n == 0) {//if no rotate 
			return;
		}else if(n == len) {//if it rotate n times which is itself
			return;
		}else  if (n > len) {//if over len, len should rotate n - len times
			n = n - len;
		}
		
		//from current head to nth
		Node<Boolean> current = head;
		while(current != null && cnt < n) {
			cnt++;
			current = current.next;
		}
		//current nth
		Node<Boolean> nthNode = current;				

		//new head = n+1
		head = nthNode.next;
		//current tail
		while (current.next != null) {
			current = current.next;
		}
		
		//add 0s at tail
		for(int i = 0; i<n; i++) {
			current.next = new Node<Boolean>(null, false);
			current = current.next;
		}
		current.next = null;
	}
	
	/*Documentation:
	 * The implementation of ShiftLeft(n) should work similarly to shiftRight(n) except for the fact that it needs
	 * to define where the new head is. The logic itself is analagous to the shiftRight(n) implementation. The new head
	 * is found via n + 1; Thus, iterate the loop to the nth position and nth.next will be the new head. Then find the current tail position.
	 * From the current tail position padd 0s as many as number of bits that got carried over which is n in this case. After the padding
	 * set the next item for current tail as null and this should complete the new node sequences.
	 * Time complexity:
	 * In the worst case for any of the loop, the iterations will traverse through the entire sequence of nodes. Therefore,
	 * it is O(n) with n refering to total number of nodes. 
	 */

	public void rotateRight(int n) {

		Node<Boolean> current = head;
		Node<Boolean> forLen = head;

		int len = 1;
		while(forLen.next!= null) {//
			len++;
			forLen = forLen.next;
		}
		//index 0
		int cnt = 1;

		if(n == 0) {//if no rotate 
			return;
		}else if(n == len) {//if it rotate n times which is itself
			return;
		}else  if (n > len) {//if over len, len should rotate n - len times
			n = n - len;
		}
		//shift num
		n = len - n;
		//find the new tail 
		while(current != null && cnt < n) {
			cnt++;
			current = current.next;

		}
		//new tail position
		Node<Boolean> nthNode = current;				

		//go to current tail
		while (current.next != null) {
			current = current.next;
		}
		//prev tail link to prev head
		current.next = head;

		//new head = n+1
		head = nthNode.next;

		//cut link to establish new tail 
		nthNode.next = null;
	}
	/*
	 *Documentation:
	 *The implementation of rotateRight should be similar to the above implementation shifting except for the 
	 *fact that instead of padding 0s from new tail or from new head, it is simply linking the new tail to previous head 
	 *and vice versa. To implement this for the rotateRight, first find out where the new tail is. Then go to the current
	 *tail and link that tail to the current head. After the links are made, set the new head to new tail's next. 
	 *This makes sense as it is a rotation. Even though, it a single linkedlist, current tail's next is always current 
	 *tail's head in respect to rotation. We end the rotating by making new tail's next to null.
	 *Time complexity" O(n)
	 *Even though, setting the head or the tail takes constant time, locating where the new tail is and the current tail
	 *takes linear time. That is, traversing the sequence of nodes is necessary to perform the rotation.
	 */
	public void rotateLeft(int n) { 

		Node<Boolean> current = head;
		Node<Boolean> forLen = head;

		int len = 0;
		while(forLen!= null) {
			len++;
			forLen = forLen.next;
		}
		//index 0
		int cnt = 1;

		if(n == 0) {//if no rotate 
			return;
		}else if(n == len) {//if it rotate n times which is itself
			return;
		}else  if (n > len) {//if over len, len should rotate n - len times
			n = n - len;
		}

		//find the nth
		while(current != null && cnt < n) {
			cnt++;
			current = current.next;

		}
		//store the new head -1(nth position)
		Node<Boolean> nthNode = current;				

		//go to current tail
		while (current.next != null) {
			current = current.next;
		}
		//linking current tail to prev head
		current.next = head;

		//new head = n+1
		head = nthNode.next;

		//cut link to establish new tail 
		nthNode.next = null;

	}
	
	/*Documentation:
	 * for the implementation of roteteLeft it is yet again similar to shifting methods and above rotateRight.
	 * Except the logic is to find where the new head is. Which can be calculated via n + 1. If n + 1 is the 
	 * new head then new tail is always going to be n as well in rotation. After we find the n, store it for a second and 
	 * go to the current tail to link the tail to current head. when that is done set the new head to n + 1 and
	 * set the new tail by making n's next to null.
	 * Time complexity: O(n)
	 * This will be O(n) as well due to locating the nth position in the sequence of node and locating where the current
	 * tail is. At most it will have to traverse an entire sequnce of nodes. Thus, O(n) with n refering to the total sequence
	 * of nodes. 
	 */

	/*
	 * The returned Comparable should be able to compute the actual decimal difference between
	 * the two bit sequences, not just return any positive/negative/zero numbers.
	 * See the test code for an example.
	 */
	public Comparable<BitSeqLL> getComp() {
		return new Comparable<BitSeqLL>() {
			@Override
			public int compareTo(BitSeqLL o) {
				
				//this 111
				int dec1 = 0;
				int base = 1; // 2^0
				Node<Boolean> curr1 = head;
				int len1 = 0;
				
				//find the length
				while (curr1 != null) {
					len1++;
					curr1 = curr1.next;
				}
				//turn the node back
				curr1 = head;
				while (curr1 != null) {
					if (curr1.data) {
						dec1 += Math.pow(2, len1 - base); //2^3...2^0
					}
					base++;
					curr1 = curr1.next;
				}
				
				//obj 000111
				int dec2 = 0;
				int base2 = 1; // 2^0
				Node<Boolean> curr2 = o.head;
				int len2 = 0;
				
				//find the length
				while (curr2 != null) {
					len2++;
					curr2 = curr2.next;
				}
				//turn the node back 
				curr2 = o.head;
				while (curr2 != null) {
					if (curr2.data) {
						dec2 += Math.pow(2, len2 - base2); //2^3...2^0
					}
					base2++;
					curr2 = curr2.next;
				}
				return dec1 - dec2;
			}
		};
	}
/*
 * Documentation:
 * for this implementation of getComp() it returns compareTo method which compares two
 * objects in their decimal values. Since the data structure is singly linkedlist, the plan
 * is to traverse through the nodes and calculate their decimal value by their position. 
 * That is, calculate the total length of the nodes and decrement the exponents accordingly.
 * That way, through each iteration, the exponents will be subtracted by 1 and append the 
 * data based on its validity in boolean. Thus, the binary to decimal operation is done via
 * decrementing the exponent value by their length and starting base. 
 * Time complexity: O(n)
 * It needs to traverse through an entire node sequence to convert each bit 
 * to decimal. Thus, O(n) with n refering to the total
 * number of nodes. 
 */

	public String toString() {

		String result="";
		Node<Boolean> current  = head;
		while (current!= null) {
			if (current.data) {
				result+='1';
			} else {
				result+='0';
			}//override so it can go through the next node
			current = current.next;
		}
		return result;
	}
}
/*
 * Documentation:
 * this implementation of toString converts bigseqLL object into string form of binary. 
 * The logic is traverse through the nodes and depict whether or not they are true or false
 * and append the string character in accordance. 
 * Time complexity: O(n)
 * with n refering to the total number of nodes. 
 * 
 */

final class Node<E> {
	Node<E> next;
	E data;

	public Node(Node<E> n, E d) {
		this.next = n;
		this.data = d;
	}
}