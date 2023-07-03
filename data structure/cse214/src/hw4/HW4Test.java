package hw4;


public class HW4Test {
	
	public HW4Test() {
		BitSeqLL b1 = new BitSeqLL("11110011"), // 243
			   b2 = new BitSeqLL("000111"),   // 7
			   b3 = new BitSeqLL("111");      // 7
		System.out.println(b2.equals(b3)); // 'true'
		Comparable<BitSeqLL> cmp = b3.getComp();
		System.out.println("-----------");
		System.out.println(cmp.compareTo(b2)); // 0
		System.out.println(cmp.compareTo(b1)); // -236
		b3.and(b1);  // b3 is now "00000011"
		System.out.println(b3); // 00000011
		System.out.println(cmp.compareTo(b2)); // Now that b3 changed, this should print -4
		cmp = b1.getComp();
		System.out.println(cmp.compareTo(b2)); // 236
		b3.or(b2);
		System.out.println(b3); // 00000111
		b3.rotateRight(1);
		System.out.println(b3); // 10000011
		b3.rotateLeft(1);
		System.out.println(b3); // 00000111
		b3.shiftRight(2);
		System.out.println(b3); // 00000001
		b3.shiftLeft(2);
		System.out.println(b3); // 00000100
		b3.not();
		System.out.println(b3); // 11111011
	}
	
	public static void main(String[] args) {
		new HW4Test();
	}
	
}
