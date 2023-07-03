package hw6;

/**
 * Homework 5: Due Apr. 28th @23:59 KST 
 * Name: Byungho Cho
 * SBUID: 115236235
 * 
 * You are to solve a collection of individual recursion puzzles. For each puzzle, you must implement
 * both a recursive and an iterative version. You are free to write helper methods that will do the
 * actual recursion. Not using a recursion in a recursive version will result in a zero score.
 * In particular, using trivial recursion (e.g., recursively computing an array length and proceeding with iteration) does not count.
 * You *must* use recursion to solve the main problem.
 * For each puzzle, clearly describe what your base and recursive cases are in the header comment.
 * Also provide a description of your approach in a single paragraph.
 * 
 * GENERAL INSTRUCTIONS (failure to follow these instructions will result in a deduction of points):
 * Your final submission should be your own work.
 * Do not import any unauthorized packages. 
 * Do not use any Java data structures unless told to do so.
 * Do not change the class or method names.
 * Submit a single Recursion.java file with no package structure.
 * Detailed instructions are given in the comment block of each puzzle.
 * 
 * RUBRIC
 * Correctness (70 points): See the individual method head for individual point allocation. 
 * Comments (30 points): 5 points for each puzzle. This should include your base and recursive 
 *                       case descriptions, as well as the description of your overall approach.
 *                       Place this information in the comment block right before the implementation
 *                       of each puzzle.
 */
import java.util.*;

public class Recursion {

	/*
	 * 1. Enumerate all possible combinations letters in the given phone number digits. Use numCodes for the corresponding 
	 * mnemonic.
	 * 'n' is a numeric string containing a sequence of digits, each between 2 and 9, inclusive.
	 * E.g., n = "45" --> your answer should be: {"GJ", "GK", "GL", "HJ", "HK", "HL", "IJ", "IK", "IL"} 
	 * (not necessarily in that order) This is because you have to produce all possible combinations of
	 * characters that belong to the digits 4 (GHI) and 5 (JKL), respectively.
	 * For the iterative version, you are free to use any data structures mentioned in class.
	 */

	/* Documentation:
	 * In this implementation of recursive mnemonics, I have approached it where each characters in the 
	 * first String has to access every character in the second String. The max number of combinations 
	 * is 16 as the max String length within numCodes is 4. Therefore, make an array to the max size where it 
	 * stores the result combinations and make another char array desginated for each individual combinations. 
	 * Then it calls the actual recursive method which it takes the input String, the index point starting from 0,
	 * the char array, and the result array. 
	 * Within the recursive helper method there exists two cases: base case, and recurisve case.
	 * The simplest case is when each combinations are combined. That is, when there is a pair 
	 * stored in the char array that is when the 'loop' should stop as there should be no more 
	 * character to add on after two are matched. Then we add that combination to the result array.
	 * As for the recursive part, we find the index in the numCodes via parsing the characters of the
	 * input String. After we get the code call the recursive method with increase index and the original
	 * parameters that was passed. This should work as it recurses in a way where the ith String chracter 
	 * traverses and combines with the Second String characters. The logic is to iterate this within the 
	 * for loop so that it will run String(Ex:GHI) length times for each character in the first String. 
	 * Each character in the first String calls its own recursive method. So their charArray will be different
	 * for each sequence of calls. Therefore, when there is two element within the charArray the next recursive
	 * call will head to the base case where it will fill the result Array with the charArray.
	 * At last, when all the recursions are done, use System.arraycopy to copy the result array to a new 
	 * String array with perfect size as there will be some cases where the few elements within the result 
	 * array may be null.
	 * time comlexity: (4^n)
	 * in the worst case the recursion will call 4 times with the maximum value of a string within numCodes
	 * is 4. That is, if the 'phone number' increases, the method runs exponentially with repect to the lenfth
	 * of the 'phone number'.
	 * 
	 * base
	 * if index == 3
	 * 		result[i] = charArr;
	 * recursive
	 * for every i in string numCodes
	 * 		charArr[i] = string numCode.charAt[i]
	 * 		recursion(index + 1)
	 * 
	 */
	static final String[] numCodes = {"", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

	public String[] recursiveMnemonics(String n) { // 12 points

		int arrSize = (int) Math.pow(4, n.length());
		String[] result = new String[arrSize];
		char[] charArr = new char[n.length()];
		recursiveMnemonicsHelper(n, 0, charArr, result);
		String[] newArr = new String[cnt];
		System.arraycopy(result, 0, newArr, 0, cnt);
		return newArr;
	}
	int cnt = 0;
	private int recursiveMnemonicsHelper(String n, int index, char[] charArr, String[] result) {
		if (index == n.length()) {
			result[cnt] = new String(charArr);
			cnt++;
			return 1;
		}//String in the nth array
		int codeLoc = n.charAt(index) - '0';
		String code = numCodes[codeLoc];
		int total = 0;
		for (int i = 0; i < code.length(); i++) {
			charArr[index] = code.charAt(i);
			total += recursiveMnemonicsHelper(n, index + 1, charArr, result); //G + J,K,L
		}
		return total;
	}
	/*Documentation:
	 *For this implementation of iterative mnemonics, since the maximum combination equals to the 4^2 as 
	 *the maximum characters for each string in numCodes is 4. So make an array with that size that will serve 
	 *as the result array. charArr stores the each character combination while the indexArr keeps up with the 
	 *position within the numcode. With that said, we want the indexArr to have 0s in the beginning so that
	 *when looking at each string within the numcode, we are looking at the first one for each position. Then
	 *we iterate through numcode to find the String. Next, store the ith character in the String we have obtained
	 *from numCodes. repeat this n times so that two characters are stored within the character array. 
	 *Then we add this onto the result array. Then we go on to the updating the index position so that 
	 *the program is able to find the next possible combinations. To do so, start at the second String(ex: in 45 is 5)
	 *and if the current index is at that position we set the index back to 0 and move to the next digit by decrementing.
	 *If th condition doesnt meet we simply increase the index. We repeat this process until index is incremented enough to
	 *meet the condition and find all the possible combination until it breaks out due to decrement of index.
	 *time complexity: O(4^n)
	 *with n refering to length of the String n. 
	 *In the worst case we would have to make all possible combinations 
	 *of four characters in which in that case will have 4^n times possible
	 *combination.
	 * 
	 * 
	 */

	public String[] iterativeMnemonics(String n) {
		int arrSize = (int) Math.pow(4, n.length());
		String[] result = new String[arrSize];
		char[] charArr = new char[n.length()];
		int[] indexArr = new int[n.length()];
		for(int i = 0; i < n.length(); i++) {
			indexArr[i] = 0;
		}
		int cnt = 0;
		while (true) {
			// generate current mnemonic
			for (int i = 0; i < n.length(); i++) {
				//index of the string
				int codeLoc = n.charAt(i) - '0';
				String code = numCodes[codeLoc];
				charArr[i] = code.charAt(indexArr[i]);
			}
			// add mnemonic to result
			result[cnt] = new String(charArr);
			cnt++;

			// update index array
			// last index
			int index = n.length() - 1;
			//						current index == last character in the digit
			while (index >= 0 && indexArr[index] == numCodes[n.charAt(index) - '0'].length() - 1) {
				indexArr[index] = 0;
				index--;
			}
			//all combinations are generated
			if (index < 0) {
				break;
			}
			//increment index position
			indexArr[index]++;
		}

		// create new array with only non-null elements
		String[] newArr = new String[cnt];
		System.arraycopy(result, 0, newArr, 0, cnt);

		return newArr;
	}

	/*?
	 * 2. Print all subset sums of a given array. A value of 0 is always part of the answer.
	 * (i.e., when NO elements are added)
	 * For example, when the input is {1, 2, 3}, the result should be the array 
	 * {0, 1, 2, 3, 3, 4, 5, 6} (singletons and duplicates should also be present)
	 * Do not use any data structures for both cases.
	 */

	/*
	 * Documentation: 
	 * For this implementation of recursive Subset sum, simillar to the iterative version
	 * we want the result array to have 2^n size times. As the number of possible subset sums
	 * equal to 2^n. To start with the recursive part I designed a helper function which takes 
	 * in the input array, index count variable, summation variable, array for result, and the empty set.
	 * Within the actual recursion, the base case is if the iterations reached the end, it should store the current sum to the result array
	 * and updates the index array.
	 * For the recursive part, there are two recursion calls with different parameters. The parameters are mostly same except for
	 * the current sum part. Assuming that the recursions work for the first one and the second one, it should return values for all
	 * possible sums. That is, the calls heed to the two possiblities for each element in the array: whether they are in the subset or not.
	 * They add or exclude each element from the sum. Therefore, when all elements are examined, all the subset sum should be in result array.
	 * Time complexity: O(2^n)
	 * Similar to the ittereative subset sum, it is O(2^n)
	 * with n refering to length of the input a. The recursion 
	 * has two calls for each call.(examining each element)
	 */
	public int[] recursiveSubsetSum(int[] a) { // 12 points
		int n = a.length;
		//always 2^n
		int[] result = new int[(int) Math.pow(2, n)];

		int[] resultIndex = {0};
		recursiveSubsetSumHelper(a, 0, 0, result, resultIndex);
		return result;
	}

	private void recursiveSubsetSumHelper(int[] a, int currentIndex, int currentSum, int[] result, int[] resultIndex) {
		if (currentIndex == a.length) {
			//end of the array
			result[resultIndex[0]] = currentSum;
			resultIndex[0]++;
			return;
		}
		recursiveSubsetSumHelper(a, currentIndex + 1, currentSum + a[currentIndex], result, resultIndex);
		recursiveSubsetSumHelper(a, currentIndex + 1, currentSum, result, resultIndex);
	}

	/*{1,2,3}
	 * Documentation:
	 * For this implementation of iterative subset attempts to generate all 
	 * possible subsets of the a.The key is to start with an empty set and
	 * increase that empty sey by iterating over each element in the a.
	 * as each element is accessed, double the size of the result array, and 
	 * keep generating two new subset sums. Repeat this process a.length we have
	 * 2^3 which is all the possible combinations within the set of 3. 
	 * Time complexity: O(2^n)
	 * with n is the size of a. The possible number 
	 * of subsets for the give set of n is 2^n. Thus the time complexity is 2^n as well.
	 */

	public int[] iterativeSubsetSum(int[] a) { // 2 points
		//universal
		int[] result = {0};
		for (int i = 0; i < a.length; i++) {
			int[] newResult = new int[result.length * 2];
			int index = 0;
			for (int j = 0; j < result.length; j++) {
				newResult[index] = result[j];
				index++;
				newResult[index] = result[j] + a[i];
				index++;
			}//increase result[]
			result = newResult;
		}
		return result;
	}

	/*
	 * 3. Skip every n-th element in the LinkedList (n > 1).
	 * For example, in a list 1-2-3-4-5-6 with n=3, the output is 1-2-4-5.
	 * Use only a LinkedList, and no other data structures.
	 */

	/*
	 * Documentation:
	 * For this implementation of recursive Skip nth, I have implemented a helper function that
	 * takes in the index as well. We start at 1 for convenience. So there are two base cases:
	 * if the list itself is null or if we reach the nth element to remove. For either case 
	 * we either return an empty list back or remove the first item and recurse itself again to 
	 * continue on with the iteration. As for the recursive part, because of the second base 
	 * checks if the current index equals the nth item and removes the first item, we 
	 * remove first to pass the sub array into the recursive call. Then we add back the first item
	 * to maintain the orignal order of the element s in the list while skipping every nth. 
	 * Time complexity: O(n)
	 * With n refering to the size of linkedList. 
	 * 
	 */

	public LinkedList<Integer> recursiveSkipNth(LinkedList<Integer> l, int n) {// 10 points
		return recursiveSkipNthHelper(l, n, 1);
	}
	private LinkedList<Integer> recursiveSkipNthHelper(LinkedList<Integer> l, int n, int index) {
		//base case: null
		if (l == null || l.isEmpty()) {
			return new LinkedList<Integer>();
		}
		//base case 2: current index == n remove nth
		if (index == n) {
			l.removeFirst();
			return recursiveSkipNthHelper(l, n, 1);

		} else {
			//pass the subLL minus the first character that way the second base can be triggered
			int first = l.removeFirst();					
			//cut out the first element, move index
			LinkedList<Integer> subLL = recursiveSkipNthHelper(l, n, index + 1);

			//maintain og LL
			subLL.addFirst(first);
			return subLL;
		}
	}

	/*
	 * Documentation:
	 * For this implementation of iterative skip nth, traverse the loop 
	 * and set a counter variable that increments until it matches with the
	 * nth item. Once it is match we removed the element at that position and
	 * set count to 0. That way it can count up to nth item again and remove 
	 * the item at that position.
	 * time complexity: O(n)
	 * with n refering to the size of the linkedlist.
	 *  
	 */

	public LinkedList<Integer> iterativeSkipNth(LinkedList<Integer> l, int n) { // 1 points
		int count = 0;
		for (int i = 0; i < l.size(); i++) {
			count++;
			if (count == n) {
				l.remove(i);
				count = 0;
				i--;
			}
		}
		return l;
	}

	/*?
	 * 4. Sort a given integer Stack in descending order.
	 * For example, if the initial stack is: <TOP> 3-4-1-2-9 <BOTTOM>, the result should be
	 * <TOP> 9-4-3-2-1 <BOTTOM>.
	 * Use only a Stack, and no other data structures.
	 */
	/*For this documentation of recursive sort stack, There are two recursive calls.
	 * The first one, if we assume that it will sort it then it will return a sorted list
	 * with one less than the original list. The helper function which is also recursive is 
	 * meant to comapre the current top with the next top in line.  if the current top is 
	 * higher we add it back onto the stack. Otherwise we pop the stack and pass it onto
	 * the recurisve call with the current top and push the orignal top back as it is not the 
	 * highest. We repeat this process and it will be sorted as the base case is triggered 
	 * for each time the current top is bigger than the next top.
	 * time complexity: O(n^2)
	 * with n refering to the size of the linkedlist. There are two 
	 * recursive calls within the function. That is, the first recursive
	 * call will run n times while the second recursive call will also run n times. 
	 * Thus, for the worst case scenartio when the list is sorted in ascending order
	 * it will call nxn times.
	 * 
	 * 
	 */
	public Stack<Integer> recursiveSortStack(Stack<Integer> s) {// 12 points
		//base case: empty
		if (s.isEmpty()) {
			return s;
			//recursive: pop top and recursive call with that subStack.
		} else {
			int temp = s.pop();
			//loop for sort
			recursiveSortStack(s);
			//compare
			recursiveSortStackHelper(s, temp);
			return s;
		}
	}

	private Stack<Integer> recursiveSortStackHelper(Stack<Integer> s, int value) {
		//base case: empty or top >= next top
		if (s.isEmpty() || value >= s.peek()) {
			s.push(value);
			return s;
		} 
		int temp = s.pop();
		s = recursiveSortStackHelper(s, value);
		s.push(temp);
		return s;
	}

	/*
	 *Documentation:
	 *for this implementation of iteratives sort stack, we compare the top 
	 *element of the input stack with the top of the temporary stack which 
	 *act as sorted stack. Simply put, pop() the input stack first and depending
	 *on if the sorted stack has value and if its top is greater than the current
	 *input top, append the sorted top on the input. That is, pop all elements 
	 *from the sorted stack until the stack is empty and sorted stack is greater.
	 *Then we append the top in the sorted stack. repeat this until s is empty.
	 *The logic is to keep sorting incoming values in the result array.
	 *Time complexity: O(n^2)
	 * with n refering to the size of the input stack.
	 * In the worst case, every element needs to be compared(ascending stack). 
	 * Therefore with the nested loop it is n^2.
	 */
	/*
	 * 4. Sort a given integer Stack in descending order.
	 * For example, if the initial stack is: <TOP> 3-4-1-2-9 <BOTTOM>, the result should be
	 * <TOP> 9-4-3-2-1 <BOTTOM>.
	 * Use only a Stack, and no other data structures.
	 */
	public Stack<Integer> iterativeSortStack(Stack<Integer> s) { // 2 points
		Stack<Integer> tempSS = new Stack<>();
		while(!s.isEmpty()) {
			int top = s.pop();
			//comparison
			while(!tempSS.isEmpty() && top<tempSS.peek()) {
				s.push(tempSS.pop());
			}
			//add it on to result stack.
			tempSS.push(top);
		}
		return tempSS;

	}

	/*
	 * 5. Even-odd sum. Return the sums of all even and odd numbers, respectively, in a given array.
	 * For example, when the array is {4, 2, 1, 5, 6}, it should return {12, 6} (in that order).
	 * Do not use any data structures.
	 */

	/*Documentation:
	 * For this implementation of recursive version of even and odd sums, the simplest case would 
	 * be summing an empty array. That is, the base case would be when the array is size 0 which 
	 * would just return a result array with 0 0 for the result of sum even and odd. As for the 
	 * recursive part, because each recursion is a loop in a way it has to check if the first 
	 * element is even or odd. To do that recurively it needs keep cutting out the first element.
	 * To do that, iterate a for loop that will run n-1 times the length of the input array and fill
	 * the temporary array with the values in the input array minus the first element. Then have the 
	 * function recurse itself with that sub array. Here belive that the recursion would work and return 
	 * the sum of even and odds. Assuming that, the returned array will have sum of even and odds. So
	 * store that in a variable where it can be appended depending on the condition if its even or odd.
	 * With that recursion is done. 
	 * Time complexity: O(n)
	 * n being the length of the input array. For each time the functon is called it makes a new sub array 
	 * with the for loop and this takes O(n) times. 
	 */
	public int[] recursiveEvenOddSums(int[] a) { // 10 points

		//base case(simplest case) if null
		if(a.length==0) {
			return new int[] {0,0};
		}

		//copy over current array minus the first item over to subArr
		int[] subArr = new int[a.length-1];
		for(int i = 0; i< subArr.length; i++) {
			subArr[i] = a[i+1];
		}

		//reduced case
		int[] subArrRes = recursiveEvenOddSums(subArr);
		int even = subArrRes[0], odd = subArrRes[1];

		//if even
		if(a[0]%2==0) {
			even+=a[0];
		}else {//if odd
			odd+=a[0];
		}
		return new int[] {even,odd};
	}

	/*Documentation:
	 * For the iterative version for even and odd sums, go through the array of a 
	 * and when a[i]th element is even append it to the even variable and if it is 
	 * odd, append it to the odd variable. Since there are only two elements in the 
	 * result array as nubmers are either sum of the even or sum of the odd, put the
	 * variables in the respective array index. 
	 * Time complexity: O(n)
	 * with n refering to the length of the array.
	 */
	public int[] iterativeEvenOddSums(int[] a) { // 1 point
		int even = 0,odd =0;
		int[] res = new int[2];
		for(int i = 0; i <a.length;i++) {
			if(a[i]%2==0) {
				even+=a[i];
			}else {
				odd+=a[i];
			}
		}
		res[0] = even;
		res[1] = odd;
		return res;
	}

	/*
	 * 6. Reverse the order of elements in a given queue.
	 * For example, if the queue has the following elements [A, B, C, D], the result should
	 * be [D, C, B, A], where the last element is the front of the queue.
	 * The contents of the given queue should not change.
	 * Use only a queue, and no other data structures.
	 */

	/*Documentation:
	 * for this implementation of recursive queue, the simplest case is when there is only 
	 * one item in the queue in which case it should jsut return that one element. 
	 * For the recursive part, if passing sub queue into the recursion part successfully works,
	 * then it will return the last item to the first;making it reversed. Simply put, way 
	 * the recursion behaves, it first calls the sub queue of n-1 items to n-2 items all the
	 * way till it reaches the call for the last item. Which in that case goes to the base case
	 * and returns that last item which is then added to the new queue. As the queue gets 
	 * returned on each calls because the first element were cut for every sub array it will 
	 * be added reversely as it gets returned. Thus, return the new queue and we have the reversed
	 * queue. 
	 * Time complexity: O(n)
	 * with n refering to the size of the original queue a as the recursive
	 * call depends on the actual size of the input queue as it recurse
	 * by cutting it by one on each call and stops when it has 1 element 
	 * left.
	 * 
	 */
	public Queue<Integer> recursiveReverseQ(Queue<Integer> a) { // 5 points
		if (a.size() == 1) {
			return a;
		} else {
			int temp = a.poll();
			Queue<Integer> reverseQQ = recursiveReverseQ(a);
			reverseQQ.offer(temp);
			return reverseQQ;
		}
	}

	/*Documentation:
	 * for this implementation of queue in the form of iteration, make a temporary queue 
	 * and iterate the input queue to n-1 times all the while shuffling in a way 
	 * the last item comes in first. That is, offer the input queue with poll()'ing' the input queue.
	 * Now that the last element is the first element, add it in the temp queue with poll. repeat this 
	 * process until a is empty then we have a reverse list in the temp queue. Then run the temp queue 
	 * til its empty and restore a with polling the temp queue.
	 * Time complexity: O(n^2)
	 * This is a side by side loop. That is, it is nested loop and a while loop. Both loops 
	 * traverse through the size of queue 'a'. so with n refering to the size of a
	 * it is n^2.
	 */
	public Queue<Integer> iterativeReverseQ(Queue<Integer> a) { // 1 point
		Queue<Integer> temp = new LinkedList<>();
		while (!a.isEmpty()) {
			for (int i = 0; i < a.size() - 1; i++) {
				a.offer(a.poll());
			}
			temp.offer(a.poll());
		}
		while (!temp.isEmpty()) {
			a.offer(temp.poll());
		}
		return a;
	}


	public static void main(String[] args) {

		Recursion rc = new Recursion();

		//1.
		//System.out.println(Arrays.toString(rc.recursiveMnemonics("78")));
		//System.out.println(Arrays.toString(rc.iterativeMnemonics("78")));

		//2.
		int[] a = {1,2,3};
		//System.out.println(Arrays.toString(rc.recursiveSubsetSum(a)));
		//System.out.println(Arrays.toString(rc.iterativeSubsetSum(a)));

		//3.
		LinkedList<Integer> ll = new LinkedList<>();
		for(int i= 0; i<6;i++) {
			ll.add(i+1);
		}
		//System.out.println(rc.recursiveSkipNth(ll, 3));
		//System.out.println(rc.iterativeSkipNth(ll, 3));

		//4.
		/*
		 * 4. Sort a given integer Stack in descending order.
		 * For example, if the initial stack is: <TOP> 3-4-1-2-9 <BOTTOM>, the result should be
		 * <TOP> 9-4-3-2-1 <BOTTOM>.
		 * Use only a Stack, and no other data structures.
		 */
		Stack<Integer> ss = new Stack<>();
		ss.push(9);
		ss.push(2);
		ss.push(1);
		ss.push(4);
		ss.push(3);
		//System.out.println(ss.peek());
		//System.out.println("iterative stack top " + (rc.iterativeSortStack(ss)).peek());
		//System.out.println("recursive stack top " + (rc.recursiveSortStack(ss)));


		//5.
		int[] evenOddSums = {4, 2, 1, 5, 6};
		//System.out.println(Arrays.toString(rc.iterativeEvenOddSums(evenOddSums)));
		//System.out.println(Arrays.toString(rc.recursiveEvenOddSums(evenOddSums)));

		//6.
		Queue<Integer> qq = new ArrayDeque<>();
		qq.offer(1);
		qq.offer(2);
		qq.offer(3);
		qq.offer(4);
		//System.out.println(rc.iterativeReverseQ(qq));
		//System.out.println(rc.recursiveReverseQ(qq));

	}
}
