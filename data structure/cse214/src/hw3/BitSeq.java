package hw3;

/**
 * Name: Byungho Cho
 * SBU ID:115236235
 * Do not add any imports other than the ones we supplied, even if you don't use them.
 * Do not modify any of the given code, although you are free to add your own fields, methods, and classes.
 */
import java.util.ArrayList;


public class BitSeq {

	/*
	 * This is a sequence of bits, where 'true' is 1 and 'false' 0.
	 * You MUST use this ArrayList to represent your bit string.
	 */
	private ArrayList<Boolean> seq; 
	/*
	 * An empty 's' means it's just a single '0'
	 */
	public BitSeq(String s) {

		seq = new ArrayList<Boolean>();

		//check if the list is empty and if not 
		//store true 
		//return a boolean to inform if add effectively changed the collection.
		if(s.isEmpty()) {
			seq.add(false); //if s its empty just a single 0
		}else {
			for(int i = 0; i<s.length();i++) {
				seq.add(s.charAt(i)=='1'); //true if 1 else false
			}
		}
	}

	/*
	 * Documentation:
	 * for this implementation of the constructor, the string paramter is check if it was empty and if it is not, it iterates over the length of the String parameter
	 * and adds the boolean value to depending on if it is 1 or not. The logic is quite simple, check if the param exists, if it does not add a single 0 otherwise,
	 * add the boolean values in accordance to the characters.
	 * Time complexity: O(n)
	 * the time complexity is O(n) with n refering to the length of the String. In the worst case, it will loop as many as the length of the String. Thus, it is O(n).
	 */

	@Override
	public boolean equals(Object obj) {
		if( obj == this) {
			return true;
		}

		if(obj == null) {//nullpointerexception
			return false;
		}
		//make it equal size 
		BitSeq ob = (BitSeq) obj;
		int maxLen = Math.max(this.seq.size(), ob.seq.size());
		if(this.seq.size()<maxLen) {
			while(this.seq.size()<maxLen) {
				this.seq.add(0, false); //add 0 to the however many 0s that need to be padded
			}
			//pad 0s for b.seq if condition falls
		} else if(ob.seq.size()<maxLen) {
			while(ob.seq.size()<maxLen) {
				ob.seq.add(0, false); //add 0 to the however many 0s that need to be padded
			}
		}
		//after comparing if it doesn't equal return false.
		for(int i = 0; i < this.seq.size();i++) {
			if(!this.seq.get(i).equals(ob.seq.get(i))) {
				return false;
			}
		}
		return true;

	}
	/*
	 * Documentation:
	 * this overriding was necessary as the default java method for equals compare the reference. Thus, when comparing two sequence it will
	 * not compare for their bit sequence rather their reference which will induce the first print line to be false when executed.Ex) System.out.println(b2.equals(b3));
	 * To correct this, I first checked for equality and nullpointer then I typecasted the parameter so that 
	 * I can make both list the equal size. After making both of them in equal size, the for loop is implemented to 
	 * compare if each of them is different. Then it return accordingly. Thus, the execution of the aforementioned example will be true
	 * even if their size is different.
	 * Time complexity: o(n) 
	 * The first two loops are padding 0s as many as the difference of the size of each lists. As mentioned, it would be depended upon the size of the lists.
	 * Thus, it will be O(n) with n begin the size of the list. 
	 * as for the final loop it iterates over the whole list to check on each element. Thus, it is o(n) with n being the seq.size(). 
	 * The side by side loops take the highest term which deduces to o(n).
	 */

	/*
	 * Return the number of bits in this string, except the padded 0s in the front.
	 */
	public int length() {
		int len = seq.size();
		for(int i = 0; i<seq.size(); i++) {
			if(seq.get(i)) { //if true = 1
				len = len - i;
				break;
			}
		}
		return len;
	}
	/*documentation:
	 * for this implementation of length() we are to return the length of the string except for the 0s in front. 
	 * The logic behind the code is to go through the list and since 1 = true and 0 is false and the list is already 
	 * organized in the structure of boolean when the condition is met for true it just returns the total length minus that index
	 * to get the number of bits in this string minus the padded 0s. To surmise, the number of bits in this string is 
	 * defined by the total size of the array(actual nums) minus the indesx of the first true in the array.
	 * Time complexity: O(n) with n being the seq.size(). The worst case is if the whole array is filled with false. 
	 * In that case, the loop would have to iterate trough the entire array. Thus, the time complexity becomes O(n).
	 */


	/*
	 * Perform a logical AND between the current and the given bit sequence. The 
	AND operation between two bits results in a 1 only if both bits are 1, and 0 otherwise. 	
	The AND operation between two bit sequences is another sequence whose individual 
	bits are the results of AND operations between bits from the two input sequences at 
	the same position. If one sequence is shorter than the other, then assume 0s are 
	padded to the left (front) to make the length equal.(1100 011 = 1100 0011)
	 */
	public void and(BitSeq b) {
		//pad 0s for seq if condition falls
		int maxLen = Math.max(b.seq.size(), seq.size());
		if(seq.size()<maxLen) {
			while(seq.size()<maxLen) {
				seq.add(0, false); //add 0 to the however many 0s that need to be padded
			}
			//pad 0s for b.seq if condition falls
		} else if(b.seq.size()<maxLen) {
			while(b.seq.size()<maxLen) {
				b.seq.add(0, false); //add 0 to the however many 0s that need to be padded
			}
		}
		//after padding, perform and operation
		//now they are the same size, go through each values in the list and perform bitwise and set
		for(int i = 0; i <maxLen; i++) {
			boolean a = seq.get(i);
			boolean bSeq = b.seq.get(i);
			seq.set(i, a && bSeq); //bitwise AND operation 
		}
	}

	/*Documentation
	 * for this method for and the logic is to pad the shorter list with 0 first. To do that, there are conditions 
	 * for each list comparing one another's size. If the list is shorter, the 0s are prepended via add method provided by ArrayList.
	 * After the shorter one is rightfully padded with 0s code for AND operation starts.
	 * The logic for AND operation is to get the value for each list and compare them with bitwise operator && and then override the value using set method.
	 * Time complexity: O(n)
	 * the reason for this is that for the first two loops checking on the length of the each lists and filling the empty spots with 0 to 
	 * make both lists equal in 'length' is bound to run as much as the 'length'. Thus, the time complexity is the maximum lenth of the two lists as n which
	 * deduce to O(n). As for the last loop, it iterates in terms of the maximum length of the two lists. Thus, it is O(n) as well.
	 * Since side by side loops are just addition, the final time complexity of this add method is O(n).
	 */

	/*
	 *  OR: The logical OR operation on two bits is 1 if at least one of the two bits is 1, and 
		0 otherwise. 
	 */
	public void or(BitSeq b) {
		//pad 0s for seq if condition falls
		int maxLen = Math.max(b.seq.size(), seq.size());
		if(seq.size()<maxLen) {
			while(seq.size()<maxLen) {
				seq.add(0, false); //add 0 to the however many 0s that need to be padded
			}
			//pad 0s for b.seq if condition falls
		} else if(b.seq.size()<maxLen) {
			while(b.seq.size()<maxLen) {
				b.seq.add(0, false); //add 0 to the however many 0s that need to be padded
			}
		}
		//after padding, perform and operation
		//now they are the same size, go through each values in the list and perform bitwise and set
		for(int i = 0; i <maxLen; i++) {
			boolean a = seq.get(i);
			boolean bSeq = b.seq.get(i);
			seq.set(i, a || bSeq); //bitwise OR operation 
		}
	}
	/*Documentation
	 * for this implementation of or, I just copied my own version of add except for the actual bitwise operation.
	 * Simply put, it is padding a particular list with 0 if it has less length than the one that is getting compared to and vice versa.
	 * As for the bitwise OR operation, it iterates through the whole sequence and sets it in accordance to the 
	 * '||" operator. 
	 * Time complexity: O(n)
	 * The time complexity for this code is o(n) as the first two loops' worst case is to go through the loop N times(the total size)
	 * for a case where other one is empty which it will fill the empty one with 0 N times. 
	 * As for the last loop where it does the OR operation it runs as many as size of the list(Whichiever one has the maximum size).
	 * Thus, the side by side loops of O(n) result to O(n)
	 * 
	 * 
	 */
	/*
	 * NOT: This is a unit operation, meaning there is no second operand. A logical NOT 
	simply inverts the bit from 1 to 0, and 0 to 1
	 */
	public void not() {
		for(int i = 0; i <seq.size(); i++) {
			boolean a = seq.get(i);
			seq.set(i, !a); //bitwise not operation 
		}
	}
	/*
	 * Documentation:
	 * for this implementation of not(), since there is no second operand, there is no need for padding 0s.
	 * Therefore, the only necessary step is to perform bitwise not operation. That is, iterate through each item
	 * in the list and simply perform negations on each item. Thus, bitwise not operation is performed via the 
	 * ! operator and will be set via set method. 
	 * Time complexity: O(n)
	 * The time complexity still depends on the size of the array as it has to iterate over each item in the list and 
	 * perform negations on each and every one of them. Thus, big-O(n).
	 * 
	 */


	/*
	 * Shift left/right: Move the entire bit string to the left/right by the given number. The 
	right/left side of the bit string should be filled with 0s, respectively
	 */
	public void shiftRight(int n) {
		if(n > seq.size()) {
			throw new IndexOutOfBoundsException(); //for the case when the user input shift nubmer that is greater than the actual size of the array
		}
		//starting backwards
		for(int i = seq.size()-1; i>=n; i--) {
			seq.set(i, seq.get(i-n)); //assign current i with n place back 
		}

		//the left side of the bit string 
		for(int i = 0; i<n; i++) {
			seq.set(i, false); //padding 0s
		}
	}

	/*
	 * Documentation:
	 * for this implementation of shiftRight, it would be easier to start from the back as the right side will be padded with 0s.
	 * That is, in terms of i being the last element of the list, shifting to the right n times would equal to 
	 * new i having a value of element at i - n. 
	 * for the padding of 0s, it is basically filling 0s up til n and it is done by iterating til n and setting it 0 with the set method.
	 * Time complexity: o(n)
	 * The time complexity of this side by side loop is o(n) with n being the seq.size()
	 * although the loop runs til i <= n which is the number of times to perform each bit shift if started from the back,
	 * if we look at the worst case one can deduce that it will run seq.size() times for the second loop and none for the first loop.
	 * Thus, the big-O for this code is O(n) with n refering to seq.size()
	 * 
	 */

	public void shiftLeft(int n) {
		if(n > seq.size()) {
			throw new IndexOutOfBoundsException(); //for the case when the user input shift nubmer that is greater than the actual size of the array
		}
		//the number of item that need to be changed = every element in the list except n number of list
		for(int i = 0; i < seq.size()-n; i++) {
			seq.set(i, seq.get(i+n)); //assign current i with n place front 
		}
		//the right side of the bit string 
		for (int i =  seq.size()-n; i<seq.size(); i++) {
			seq.set(i, false); //padding 0s with the items that are left from above loop
		}
	}
	/*
	 * Documentation:
	 * The logic of this shiftLeft method is to override the values that are within total list - n range as for that the 
	 * values are overriden via set method where the index is the iteration of the list and we want to replace the value 
	 * of that index with n times in front. As for the padding of 0s, we want to iterate over the values that aren't overriden.
	 * That is, from the total size - n is where it should start til the last item in the list. They are set to 0 via 
	 * the set method.
	 * Time complexity: O(n)
	 * similar to the shiftRight the time complexity is O(n) with n refering to seq.size. when looking at the worst case where 
	 * n is equal to size of the array, the loop would have iterate seq.size() while the first loop would run 0 times. Thus, with 
	 * the side by side loop, it accounts for highest term which would be O(n) with n refering to seq.size().
	 * 	 
	 * */

	/*
	 * Rotate left/right: Similar to shifts, but the bits that are moved over the edge are not 
	destroyed but appear on the other end (wrap-around)
	 */
	public void rotateRight(int n) {
		if(n > seq.size()) {
			throw new IndexOutOfBoundsException(); //for the case when the user input shift nubmer that is greater than the actual size of the array
		}
		int cnt = 0;
		boolean[] bolArr = new boolean[seq.size()];
		//starting backwards
		for(int i = seq.size()-1; i>=n; i--) {
			bolArr[cnt] = seq.set(i, seq.get(i-n)); //assign current i with n place back
			cnt++;
		}
		//the left side of the bit string 
		int cnt2 = n;
		for(int i = 0; i<n; i++) {
			seq.set(i, bolArr[cnt2-1]); //filling with removed bits
			cnt2--;
		}
	}
	/*
	 * Documentation:
	 * for this implementation of rotateRight, it is analagous to shiftRight except for the fact that removed variables are 
	 * stored in front of the list. Logic behind this is to use the return value for set. as the loop starts from the back
	 * and works it way to n, it will set each value to n time previous. Here, by utilizing the return value for set,
	 * the removed values are stored in a different array for future use.
	 * After the elements are shifted to the right, the array should be rearranged again for the removed values.
	 * To do so, the loops would have to run n times, which is the number of bit shifted over. Then as it iterates from 0, its values
	 * are set with the values stored from the boolean array. The logic behind this is to get a cnt variable and 
	 * decrement it by one on each iteration as in the first loop it appended the removed values.
	 * so it makes sense to set the values in reverse order. Thus, rotateRight is implemented via the return values from set method.
	 * Time complexity: O(n)
	 * The worst case for the first loop iterates over the whole array if given 0 as the parameter. Although, the output of the array will
	 * be same as before the execution, it still runs as many as seq.size(). As for the second loop, even though it depends on the parameter n
	 * rather than the size of the array, because of the addition rule for side by side loop, the total time complexity is O(n).
	 */

	public void rotateLeft(int n) {
		if(n > seq.size()) {
			throw new IndexOutOfBoundsException(); //for the case when the user input shift nubmer that is greater than the actual size of the array
		}
		int cnt = 0;
		boolean[] bolArr = new boolean[seq.size()];
		//the number of item that need to be changed = every element in the list except n number of list
		for(int i = 0; i < seq.size()-n; i++) {
			bolArr[cnt] = seq.set(i, seq.get(i+n)); //assign current i with n place front 
			cnt++;
		}
		int cnt2= 0;
		//the right side of the bit string 
		for (int i = seq.size()-n; i<seq.size(); i++) {
			seq.set(i, bolArr[cnt2]); //padding 0s with the items that are left from above loop
			cnt2++;
		}
	}

	/*
	 * Documentation:
	 * the logic behind rotateLeft is tantamount to shiftLeft except for several steps implemented to set remove values to the right of the list.
	 * That is, create a new array of boolean and iterate as many as bit to shift over. Then, store the overriden values to new array while 
	 * setting the current values with the n times in front. Then from the index in which is not changed, set the variables at that position til the end of the list
	 * with the aforementioned boolean array. Thus, rotateLeft is implemented via utilizing set method's return value.
	 * Time comlexity: O(n)
	 * Time complexity for this would be big-O of n with n refering to the number of positions to shift the bits to the left. In the first loop it starts from 0 to 
	 * however many times the array needs to be shifted(total - n). For the second loop it starts from the position where the bits 
	 * is not shifted and goes to the end of the list. Thus, it is big-O of n with n refering to the number of bits needed to shift over.
	 */

	/*
	 * The returned Comparable should be able to compute the actual decimal difference between
	 * the two bit sequences, not just return any positive/negative/zero numbers.
	 * See the test code for an example.
	 */
	public Comparable<BitSeq> getComp() {
		return new Comparable<BitSeq>() {
			@Override
			public int compareTo(BitSeq o) {
				int dec1 = 0;
				int base = 1; //2^1

				int len = seq.size();
				for (int i = len - 1; i >= 0; i--) {
					if (seq.get(i)) {//if 1
						dec1 += base;
					}
					base = base * 2;

				}
				int dec2 = 0;
				int base2 = 1; //2^1

				int len2 = o.seq.size();
				for (int i = len2 - 1; i >= 0; i--) {
					if (o.seq.get(i)) {//if 1
						dec2 += base2;
					}
					base2 = base2 * 2;

				}
				return dec1 - dec2;
			}
		};

	}
	/*
	 * Documentation:
	 * for this implementation of getComp, the logic is to compare the current object by the object being compared to and then find the decimal for each.
	 * That is, the decimal for each bit sequence can be found by iterating from the back, and find the 1 which is finding for true in that case as the arrays are boolean.
	 * If it is 1 we append it with base set to 1 as the first base is always going to equal 1. then the base is multiplied by 2 as binary sequence is power of two for each 
	 * position. ex) 2^3 2^2 2^1 2^0. So for each iteration dec is appended with doubling base if it is true. For the one being compared to it is similar except for that it is 
	 * using the Bitseq that is inputted in the compareTo method. The Comparable method is then return by first object minus the second object giving the actual decimal differnece
	 * between the two. Thus, getComp is implemented via for loops iterating over and appending the values if it is true.
	 * Time complexity: O(n)
	 * It is O(n) with n being the seq.size(), the size of the array. That is, it is iterating backwards. so from n-1 to 0 which would mean that it is 
	 * iterating over the whole array. Thus, O(n) with n refering to the size of the array.
	 * 
	 */



	public String toString() {

		String result="";
		for (boolean bit : seq) {
			if (bit) {
				result+='1';
			} else {
				result+="0";
			}
		}
		return result;
	}
	/*
	 * Documentation:
	 * this implementation of toString converts bigseq object into string form of binary. 
	 * The logic is to go through the seq array and for each boolean value if true it appends 1 and if false it appends 0 correspondingly.
	 * Thus, when the whole array of seq is ran through it returns the final string in binary form. 
	 * Time complexity: O(n)
	 * with n refering to the size of the seq. 
	 * 
	 */
}
