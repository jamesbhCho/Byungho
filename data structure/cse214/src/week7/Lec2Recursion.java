package week7;



public class Lec2Recursion {

	public static void main(String[] args) {
		//System.out.println(Lec2Recursion.factorial(10));
		//System.out.println(Lec2Recursion.even(10));
		System.out.println(Lec2Recursion.add(5));
	}

	public static int factorial(int n) {

		if(n<0) {//undefined
			throw new IllegalArgumentException();
		}
		if(n<=1) { //1 factorial that is defined as just 1 if(n==0)
			return 1;
		}
		int simple = n;
		int reduced = factorial(n-1);
		return simple * reduced;
		//return n * (n-1) * factorial(n-2) need two base cases why?
	}


	//return 1+2 + n

	public static int add(int n) {

		if(n==1) {
			return 1;
		}
		return n+add(n-1);
	}

	//if 5 return 2 + 4 
	public static int even(int n) {

		if(n<2) {
			return 0;
		}
		if(n%2==0) {//if even 
			return n+even(n-2);
		}else {//if odd
			return even(n-1);
		}
	}

	public static String Dec2Bin(int n) {
		if(n<0) {//if invalid
			return "";
		}else if(n<=1) {//0 or 1
			return String.valueOf(n);
		}
		int rem = n%2;
		return Dec2Bin(n/2) + rem;

	}

	public int gcd(int a, int b) {
		if(b == 0) return a;
		else return gcd(b,a%b);
	}

	public static boolean isPalindrome(String s) {
		if(s.length()<=1) return true;
		//if first and last is the same and the substring is a palindrome
		if(s.charAt(0) != s.charAt(s.length()-1)) return false;
		return isPalindrome(s.substring(1,s.length()-1));
	}

	//no iterative version of recursion
	//backtracking recursion is not asked 
	//recursion problems related to data structure
	//no probs that directly asks to recurse something
	//given a string reverse a string by words
	//hello world i am blah -> blah am i world hello 
	//try it without using data structure(if time allows both)
	//try to do it without string split
	//base: length of split is 1 return split[0]
	//recursive case: call word_reverse on split[minus the first word] + "" split[0]
	public static String reverseString(String s) {
		String[] arr = s.split(" ");
		if(arr.length == 1) {
			return arr[0];
		}
		int indexAtFirstSpace = s.indexOf(" ");
		System.out.println(s.substring(indexAtFirstSpace+1));
		return reverseString(s.substring(indexAtFirstSpace+1)) +  " " + arr[0];
	}

	static void printLeafNodes(Node root)
	{

		// If node is null, return
		if (root == null)
			return;

		// If node is leaf node, print its data    
		if (root.left == null &&
				root.right == null)
		{
			System.out.print(root.data + " ");
			return;
		}

		// If left child exists, check for leaf
		// recursively
		if (root.left != null)
			printLeafNodes(root.left);

		// If right child exists, check for leaf
		// recursively
		if (root.right != null)
			printLeafNodes(root.right);
	}

	// Utility function to create a new tree node
	
	public class Node {
		Node parent, left, right;
		// Add more necessary fields and methods
		int data;

		public Node() { // Modify the constructor accordingly
			this.parent = null;
			this.left = null;
			this.right = null;
			this.data = 0;

		}
	}
}
