package hw1;

class HW1Test {
	LMS hw;
	
	public HW1Test(LMS hw) {
		this.hw = hw;
	}
	
	public void p(String msg) {
		System.out.println(msg);
	}
	
	public void test() {
		p(hw.addBook("The name of the rose", "1234", "Umberto Eco") + ""); // "true"
		p(hw.addBook("The name of the rose", "1234", "Umberto Eco") + ""); // "false"
		p(hw.size() + ""); // 1
		p(hw.addBook("Foucault's pendulum", "5678", "Umberto Eco") + ""); // "true"
		Book[] books = hw.getBooks("Umberto Eco");
		p(books.length + ""); // 2
		Book tmp = books[0];
		for(Book b : books)
			p(b.getTitle()); // "The name of the rose", "Foucault's pendulum"
		p(hw.addBook(tmp) + ""); // "false"
		p(hw.removeBook(tmp) + ""); // "true"
		// Etc. Feel free to add more test cases. You are allowed to share test cases, but not any of your codes.
	}
	
	public static void main(String[] args) {
		(new HW1Test(new HW1(HW1.MAX_LEN))).test();
	}
	
}