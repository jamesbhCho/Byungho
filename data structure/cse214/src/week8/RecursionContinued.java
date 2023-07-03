package week8;

public class RecursionContinued {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * System.out.println(gcd(10,5)); 
		 * System.out.println(gcd(24,10));
		 * System.out.println(gcd(12,5));
		 */
		/*
		 * System.out.println(ispalindrome("redivider"));
		 * System.out.println(ispalindrome("otto"));
		 * System.out.println(ispalindrome("1991"));
		 * System.out.println(ispalindrome("Never odd or even"));
		 */
		
		System.out.println(fibonacciSeq(5));
		System.out.println("---------");
		System.out.println(fib(5));
	}
	public static int gcd(int a,int b) {
		
		if(b==0) {//when a becomes 1
			return a;
		}else {
			return gcd(b,a%b);//10%24 = 10
		}
	}
	
	public static Boolean ispalindrome(String s) {
		if(s.length()<=1) {//base case(even or odd)
			return true;
		}
		if(s.charAt(0) == s.charAt(s.length()-1)) {
			return ispalindrome(s.substring(1,s.length()-1));
		}else {
			return false;
		}
	}
	public static Boolean ispalindromeLec(String s) {
		if(s.length()<=1) {//base case(even or odd)
			return true;
		}
		if(s.charAt(0) != s.charAt(s.length()-1))return false;
		return ispalindromeLec(s.substring(1,s.length()-1));
	}
	
	public static int fibonacciSeq(int n) {
		//base case(Seed numbers) 1base index 
		if(n<=2)return 1;
		else return fibonacciSeq(n-1) + fibonacciSeq(n-2);
	}
	
	public static int fib(int n) {
		if(n<=2) {
			System.out.println("1 ");
			return 1;
		}else {
			int f = fib(n-1) + fib(n-2);
			System.out.printf(f+" ");
			return f;
		}
	}
}
