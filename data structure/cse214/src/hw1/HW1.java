package hw1;

import java.util.Arrays;

/**
 * Name: Byungho Cho
 * SBU ID: 115236235
 * Do not use any external packages!
 * Provide a comment block for all methods that you write.
 * The comment should clearly describe the approach you've taken to solve the issue.
 */

public class HW1 extends LMS {

	/* 
	 * This is the maximum possible # of books. 
	 * There should never be more than this many books in the library.
	 * TODO: You MUST observe this limit.
	 */
	public static final int MAX_LEN = 2000;  //"observing this limit" via addBook method

	/* 
	 * You may add any variables that you find necessary, but add only what you need. 
	 */


	private int bookNum;			//field to "observe" the limit of the books
	private Book[] book;			//array of book to store each data

	//constructor
	public HW1(int maxLen) {
		super(maxLen);
		book = getRawData();			//using the field "rawData" from abstract class 'LMS'
		this.bookNum = 0;				//initializing bookNum to 0 to keep up with the current number of book objects
	}

	/*
	 * The code in main() is only for testing, and you are free to write whatever code you need
	 * to test your HW1 class code. The code in main() is not subject to grading.
	 */
	public static void main(String[] args) {
		HW1 hw = new HW1(MAX_LEN);
		p(hw.addBook("The name of the rose", "1234", "Umberto Eco") + "");
		p(hw.addBook("The name of the rose", "1234", "Umberto Eco") + "");
		hw.getBooks("Umberto Eco");
	}

	public static void p(String msg) {
		System.out.println(msg);
	}

	//implemented methods via abstract class(LMS)

	/**
	 * Insert a new book to the library. 
	 * If the array is already full or if a book already exists, insertion fails. already full -> observing the limit 
	 * @return true if addition is successful, false otherwise.
	 * TODO: Don't allow duplicates
	 */
	public boolean addBook(String title, String isbn, String author) {

		//if there are books in the array
		if(bookNum > 0) {

			//already full
			if(bookNum == MAX_LEN) {
				return false;
			}
			//if a book already exists(Two books are the same *only* if the ISBNs match.)
			for(int i = 0; i < bookNum; i++) {
				if(book[i].getISBN().equals(isbn)) {
					return false;
				}
			}
		}
		implBook bookToAdd = new implBook(title, isbn, author);
		//if it passes "already full" and the exist check add the book
		book[bookNum] = bookToAdd;
		bookNum = bookNum + 1;
		return true;
	}

	/* Documentation
	 * For the addBook method, I have partitioned the code by checking if the book is greater than 0. That way when executed, if the array is already full it checks out as false,
	 * and if it is the first iteration of adding a book then it skips the condition and adds the book.
	 * The book is added via the implemented method that 'extends' the abstract class the 'Book'
	 * Then the bookNum is incremented by one to keep up with the current number of the books.
	 * The logic here is to distinguish whether the adding of the book makes the capacity of the LMS exceed or if it does not checks if the two books are the same.
	 * If both of them proceeds without returning then it adds the book to the array.
	 */

	/**
	 * Same as above, but with a Book instance.
	 */
	public boolean addBook(Book b) {//just return the overloading method
		return addBook(b.getTitle(),b.getISBN(),b.getAuthor());
	}

	/* Documentation
	 * This is with the book instance. Here I just implemented it by returning the former overloading method and replaced the parameters with the getter from the b instance.
	 */

	/**
	 * Remove a book. Must not remove a non-existing book.
	 * @return true if removal is successful, false otherwise.
	 */
	public boolean removeBook(String title, String isbn, String author) {

		for(int i = 0; i < bookNum; i++) {
			//instantiate all book in the array to find the book to remove
			Book bookToRemove = book[i];
			if(bookToRemove.getTitle().equals(title) && bookToRemove.getISBN().equals(isbn) && bookToRemove.getAuthor().equals(author)) {
				//override the current value with the next item in the array 
				for(int j =  i; j < bookNum-1; j++) {
					book[j] = book[j+1];
				}
				//decrement the bookNum for removal
				bookNum = bookNum - 1;
				return true;
			}
		}
		return false;
	}

	/* Documentation
	 * The logic here is to iterate over the number of books that are entered in the system and find out if the book to remove is in the system.
	 * If the condition matches then it removes the book by overriding the current value with the one after. It skips it per say. Which is same thing as deleting.
	 * as the book is successfully removed, the total bookNum is decremented by one to keep up with the current number of books in the array.
	 */

	/**
	 * Same as above, but with a Book instance.
	 */
	public boolean removeBook(Book b) {//just return the overloading method
		return removeBook(b.getTitle(),b.getISBN(),b.getAuthor());
	}

	/* Documentation
	 * This is with the book instance. Here I just implemented it by returning the former overloading method and replaced the parameters with the getter from the b instance.
	 * Exactly same as what I did with the addBook(Book b) method.
	 */

	/**
	 * Return the total number of books currently stored in the library.
	 * @return The number of books stored.
	 */
	public int size() {
		return bookNum;
	}

	/* Documentation
	 * For this, I just returned the bookNum that was declared in the beginning of the HW1 class.
	 * As the total number of books increment or decrement via addBook or removeBook method, the numbers are checked as there are codes that have been implemented in the appropriate 
	 * areas of the methods. Thus, returning a field is justifiable as it has been implemented appropriately throughout the methods that meddles with it.
	 */

	/**
	 * Return true if 'b' exists in the library, and false otherwise.
	 */
	//if a book already exists(Two books are the same *only* if the ISBNs match.)
	public boolean exists(Book b) {
		for (int i = 0; i < bookNum; i++) {
			if (book[i].getISBN().equals(b.getISBN())) {
				return true;
			}
		}
		return false;
	}

	/* Documentation
	 * As for the exists method, the logic is to go through the book array and find whether the parameter instance matches with the each books in the iteration.
	 * If the condition meets it returns true, if not the default is false so when the iteration is over it just returns false. 
	 */

	/**
	 * Return all books having the given author name.
	 * Condition: Returned array must not contain any 'empty' spaces. (i.e., shouldn't be padded with nulls)
	 */
	public Book[] getBooks(String author) {
		int arrCnt = 0;
		Book[] authorBook = new Book[bookNum];
		for(int i = 0; i<bookNum; i++) {
			if(book[i].getAuthor().equals(author)) {
				authorBook[arrCnt] = book[i];
				arrCnt++;
			}
		}
		return authorBook;
	}
}

/* Documentation
 * The logic here is to make an array of books equal to the current number of books. That way there are no nulls in the array.
 * Then go through the array and find the books that meets the condition and store it accordingly. 
 * If there are multiple books with the same author, it still checks out as the index of the array increments on each time it adds the book in the array.
 */

//class to implement Book class
class implBook extends Book{

	private String genre;

	public implBook(String title, String isbn, String author) {
		super(title, isbn, author);
		this.genre = "";
	}

	@Override
	public String getGenre() {
		return genre;
	}

}

/* Documentation
 * This is the implemented class that 'extends' the abstract class Book.
 * Nothing grand is added other than super()
 * The existence of this class is to instantiate the book class indirectly that way it can be implemented to add books in the system.
 * It's usage is to store each book's information.
 */

/**
 * Abstract class for library management system.
 * You're free to add more fields/methods, but don't remove what's given.
 */
abstract class LMS {
	/*
	 * You MUST use the following array to store the books.
	 */
	private Book[] rawData;
	private int maxLen;

	public LMS(int maxLen) {
		this.maxLen = maxLen;
		this.rawData = new Book[maxLen];
	}

	public Book[] getRawData() {
		return rawData;
	}
	
	/* Documentation
	 * for the LMS class, nothing significant is added besides initializing the declared variables, and the getRawData method.
	 * The getRawData method is made to utilize the rawData field in the child class. That way rawData array is accessible outside
	 * of the abstract class to store the books.
	 * 
	 */
	/**
	 * Insert a new book to the library. 
	 * If the array is already full or if a book already exists, insertion fails.
	 * @return true if addition is successful, false otherwise.
	 * TODO: Don't allow duplicates
	 */
	public abstract boolean addBook(String title, String isbn, String author);

	/**
	 * Same as above, but with a Book instance.
	 */
	public abstract boolean addBook(Book b);

	/**
	 * Remove a book. Must not remove a non-existing book.
	 * @return true if removal is successful, false otherwise.
	 */
	public abstract boolean removeBook(String title, String isbn, String author);

	/**
	 * Same as above, but with a Book instance.
	 */
	public abstract boolean removeBook(Book b);

	/**
	 * Return the total number of books currently stored in the library.
	 * @return The number of books stored.
	 */
	public abstract int size();

	/**
	 * Return true if 'b' exists in the library, and false otherwise.
	 */
	public abstract boolean exists(Book b);

	/**
	 * Return all books having the given author name.
	 * Condition: Returned array must not contain any 'empty' spaces. (i.e., shouldn't be padded with nulls)
	 */
	public abstract Book[] getBooks(String author);
}

/**
 * Abstract class for Book.
 * You're free to add more fields/methods, but don't remove what's given.
 * Two books are the same *only* if the ISBNs match.
 */
abstract class Book {
	private String title;
	private String isbn;
	private String author;

	// TODO: Add whatever method you think is necessary.
	public Book(String title, String isbn, String author) {
		this.title = title;
		this.isbn = isbn;
		this.author = author;
	}

	public String getTitle() { return title; }
	public String getISBN() { return isbn; }
	public String getAuthor() { return author; }

	public abstract String getGenre();
}