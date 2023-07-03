package hw5;

import java.util.ArrayList;



/**
 * Name: Byungho Cho
 * SBUID: 115236235
 */
enum OPER{PLUS, MINUS, TIMES, DIV, POW}; // Just for your reference. Don't need to use this.

/*
 * Your documentation here.
 */
public class Expression {

	Stackable<String> stack; // Don't change this
	private ArrayList<String> arrStr;

	public Expression() {	
		arrStr = new ArrayList<>();
		stack = new Stackable<String>() {

			@Override
			public String peek() {

				if(arrStr.isEmpty()) {
					return null;
				}
				return arrStr.get(arrStr.size()-1);
			}

			@Override
			public void push(String str) {
				arrStr.add(str);
			}

			@Override
			public String pop() {
				if(arrStr.isEmpty()) {
					return null;
				}
				return arrStr.remove(arrStr.size()-1);
			}

			@Override
			public int size() {
				return arrStr.size();
			}
		};

	}
	/*
	 * Documentation:
	 * The constructor for Expression overrides Stackable which contains four methods:peek,push,pop and size.
	 * Size method I added for my convenience. As for these methods, they use arrayList data structure.
	 * As peek method is designated to 'peek' at the top of the stack which is the last item in terms of array.
	 * Therefore, simply return the last item of the array. As for push, it is suppose to append which in terms of
	 * array we simply add the parameter that is passed in. As for pop, it just removes the topmost stack which 
	 * is the last item in terms of array. So using arrayList we remove the item and return the removed item.
	 * As for size use the size() in arrayList. 
	 * Time complexity: amrotized constant 
	 * because of the random access feature in ArrayList and because it is a side by side loop and all of them are
	 * O(1). Except for push method as it is using add method in ArrayList. 
	 */

	/*
	 * These methods should return the converted infix/postfix. 
	 * If there is a syntax error, return an empty string.
	 * State the big-O (w.r.t. the length of input string) and explain why.
	 */

	/*infix2Postfix
	 * for each symbol 's' in the expression
	 * if 's' is an operand: append 's' to the postfix string
	 * Else(if s is an operator)
	 * 		pop all operators from stack as long as they have same or higher precedence than 's'
	 * 		append those popped operators to postfix string in order
	 * 		push 's'
	 * pop everything from stack and append to postfix string in order 
	 */

	public String infix2Postfix(String infix) {
		
		//for handling parenthesis
		Stackable<String> parenStack = stack;
		
		if(infix == null || infix.isEmpty()) {
			throw new NullPointerException();
		}

		String postfix = "";
		int operandCnt = 0;
		int operatorCnt = 0;
		//for each symbol 's' in the expression
		for(int i =0; i<infix.length(); i++) {
			char s = infix.charAt(i);
			//if 's' is an operand: append 's' to the postfix string
			if(Character.isLetterOrDigit(s)) {//operand
				postfix+=s;
				if(i == infix.length()-2 &&!(Character.isLetterOrDigit(infix.charAt(i+1)))){//dangling operator
					return "";
				}
				operandCnt++;
			//if open parens found store it in stack until close paren is found
			}else if(s == '(') { 
				parenStack.push("(");
			
			//if close parens pop until only open parens in stack
			}else if(s==')') {
				while(parenStack.peek()!="(" &&!(parenStack.peek()==null)){
					postfix+=stack.pop();
				}
				//syntax error-unmatched parenthesis
				if(parenStack.peek()==null) {
					return "";
				}else {
					//remove open parens 
					stack.pop();
				}
				
			}else {//operator
				if(i < infix.length()-2 &&!(Character.isLetterOrDigit(infix.charAt(i+1)))){//wrong operator
					return "";
				} 
				//pop all operators from stack as long as they have same or higher precedence than 's'
				while (!(stack.peek()==null) && precedence(stack.peek().charAt(0)) >= precedence(s)) {
					//append those popped operators to postfix string in order
					postfix += stack.pop();
				}
				//push 's' operators
				stack.push(Character.toString(s));
				operatorCnt++;
			}
			if(operandCnt-1 == operatorCnt+1) {//dangling operand
				return "";
			}
		}//pop everything from stack and append to postfix string in order 
		while (!(stack.peek()==null)) {
			postfix += stack.pop();
		}
		return postfix;
	}
	/*
	 * Documentation:
	 * for this implementation of infix2postfix, I have referenced Professor Byung's pseudocode for infix2postfix. 
	 * Mainly the algorithm behind the transition of infix to postfix is divided into two cases: if it is an operand or if it is an operator.
	 * We iterate the loop based on the number of characters in the string. Then,
	 * If it is an operand we simply append that character in the result string.
	 * If it is an operator we push it in the stack and it has to check the precedence of the current operator to the ones that are in stack. 
	 * If the operator in stack has higher precedence than the current operator, pop all operators and add it onto the result string. 
	 * As for the possible parenthesis,I have implemented a seprate stack for handling parentheis. We store it seperately and when the for loop
	 * encounters open parenthesis first then it stores it until close parentheis is found. Once its found pop all the operators from the orignal
	 * stack where it stored the operators up until open parenthesis is found in the parenStack. For the possible syntax error such as mismatched
	 * parens can be resolved via checking if the parenStack is null because when a perfect execution of this parenthesis part is processed there will
	 * only be an open paren in the paranthesis stack before it pops at the end.
	 * As for the error handling we check for three cases: wrong operator postion, dangling operator, and dangling operand.
	 * As for wrong operator position in infix to postfix, all the consecutive operators in the infix string are wrong operator position.
	 * So we check that in the operator condition part and only up to second to last character as up to last character will result
	 * in index out of bounds.
	 * As for dangling operator, it simply needs to check if the second to last character is an operand and the next charcter is an operator. 
	 * Finally, for dangling operand, there will always be one less operator than operand. Therefore, if total operand number - 1 equals 
	 * the total operator count it will be a dangling operand.
	 * Time complexity: O(n^2)
	 * In the worst case, the outer for loop will run n times with n refering to length of the input string.
	 * There are two inner loops in the for loop and each of them are O(n) as it may have to execute o(n) times
	 * to find specific operator or close parenthesis. Thus, as is it a nested loop, it is O(n^2).
	 * 
	 */

	//method for precedence 
	private int precedence(char c) {
		switch (c) {
		case '+':
			return 1;
		case '-':
			return 1;
		case '*':
			return 2;
		case '/':
			return 2;
		case '^':
			return 3;
		}
		return -1;
	}
	/*
	 * Documentation:
	 * This is just a helper function I made to calculate the precedence of operators. 
	 * It returns specific numbers for each desginated characters. 
	 * Time complexity: o(1)
	 * It simply returns the number that matches with the character so it runs constant time.
	 * 
	 * 
	 */
	//ab+
	/*
	 * for each symbol c in the expression
	 * 		if 's' is an operand: push it in the stack
	 * else(if it's an operator)
	 * 		take out the stack and add it to the infix string in reverse order(first peek is the last operand)
	 */
	public String postfix2Infix(String postfix) {

		if(postfix == null || postfix.isEmpty()) {
			throw new IllegalArgumentException();
		}
		for(int i = 0; i<postfix.length(); i++) {
			char s = postfix.charAt(i);
			//if 's' is an operand: push it in the stack
			if(Character.isLetterOrDigit(s)) {//if operand
				stack.push(Character.toString(s));
			}else {//operator
				if(stack.size()<2) {//dangling operator && wrong operator position
					return "";
				}
				//take out the stack(at least 2) and add it to the infix string in reverse order(first peek is the last operand)
				String top = stack.pop();
				String bottom = stack.pop();
				String res = bottom + s + top;
				stack.push(res);
			}
		}
		//only one element left(the concatenation of new infix)
		if(stack.size()!= 1 || stack.size() == 0) {//dangling operand or just checking for null as it should not be empty
			return "";
		}//remove last
		return stack.pop();
	}
	/*
	 * Documentation:
	 * As for postfix to infix algorithm, it is the oppposite of infix to postfix.
	 * Infix to postfix was storing the operator in stack while appending the operands
	 * in the result string and depending on the precedency of upcoming operators 
	 * in the input string it appended to the result string. In the case for postfix to infix,
	 * it is stacking the operands and for each operator found it pops the operand back
	 * and manipulate it in a way it transitions to infix notation. That is, due to the behavior of
	 * postifix notation to have consecutive operands in its notation, we store it in stack and when
	 * its operator comes after we pop the stack back one by one and concatenate it with the operator
	 * and push that concatenated String back in the stack. That way, as it loops and store other operands and
	 * finds another operator it does its procedure again. The error handling is different in this case,
	 * as aforementioned behavior of postifix notation to have at least two operand before encountering an operator.
	 * Therefore, for dangling operator and wrong operator position we use stack.size()>2. 
	 * As for dangling operand as we are storing operands in the stack if execute perfectly there should be only one 
	 * element left which is the final result string. Anything other than that is a dangling operator or stack is empty which
	 * should not be until the end of the function. 
	 * Time complexity: O(n)
	 * with n refering to the length of the input String.
	 * Since it iterates over each character in the input String and
	 * all the stack methods are O(1) except for push but for most case is O(1) for this as well,
	 * it is O(n).
	 * 
	 */

	public static void main(String[] args) {
		// The following are just test codes, which you can change all you want.
		// Feel free to share test cases with your classmates, but not the solution.
		Expression hw = new Expression();
		String infix = "a+b"; //"x-y+z/1-7*y" //"a-a-a-a-a*b*b*b*b" 
		String infix2 = "x-y+z/1-7*y";
		String infix3 = "a-a-a-a-a*b*b*b*b";
		String infix4 = "(a+b)*c";
		String postfix = "ab+";
		String postfix2 = "xy-z1/+7y*-";
		String postfix3 = "aa-a-a-ab*b*b*b*-";
		String postfix4 = "ab+c*";
		String badInfix = "a+-b"; //wrong operator positions 
		String badInfix2 ="a-a-a-a-a*b*b*b*b*";//dangling operator 
		String badInfix3 ="ab+c";//dangling operand		
		String badPostfix = "a+b"; //wrong operator positions 
		String badPostfix2 = "ab+-"; //dangling operator 
		String badPostfix3 = "aab+"; //dangling operand	


		
		  if(hw.infix2Postfix(infix4).equals(postfix4))System.out.println("i4Success");
		  if(hw.infix2Postfix(infix3).equals(postfix3))System.out.println("i3Success");
		  if(hw.infix2Postfix(infix2).equals(postfix2))System.out.println("i2Success");
		  if(hw.infix2Postfix(infix).equals(postfix))System.out.println("i1Success");
		  //if(hw.infix2Postfix(badInfix).equals(""))System.out.println("badInfix1");
		  //if(hw.infix2Postfix(badInfix2).equals(""))System.out.println("badInfix2");
		  //if(hw.infix2Postfix(badInfix3).equals(""))System.out.println("badInfix3");
		 


		/*
		 * if(hw.postfix2Infix(postfix).equals(infix)) System.out.println("p1Success");
		 * if(hw.postfix2Infix(postfix2).equals(infix2))System.out.println("p2Success");
		 * if(hw.postfix2Infix(postfix3).equals(infix3))System.out.println("p3Success");
		 */

		//if(hw.postfix2Infix(badPostfix).equals(""))System.out.println("badPostfix1");
		//if(hw.postfix2Infix(badPostfix2).equals(""))System.out.println("badPostfix2");
		//if(hw.postfix2Infix(badPostfix3).equals(""))System.out.println("badPostfix3");






	}
}

/**
 * Your stack should use the following interface. You may use any of the data structures
 * that we looked at in class so far, but the time complexity for each of the required
 * methods should be minimal.
 * Note: Having more than one classes/interfaces in the same file is generally undesirable.
 * But we'll just keep it this way for the sake of simplicity.
 */
interface Stackable<E> {
	public E peek();
	public void push(E obj);
	public E pop();
	public int size();


}
