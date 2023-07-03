package week9;

public class TreeContinued {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
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
}
