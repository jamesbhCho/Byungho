package week12;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;


public class RecMap {
	static class Student{
		int studentID;
		String name;
		public Student(int studentID, String name) {
			this.studentID = studentID;
			this.name = name;
		}
		
		public int hashCode() {//design hashcode to represent the object
			//return studentID;
			return name.hashCode() << 5 + studentID;
		}
		
		//detect duplicate keys
		public boolean equals(Student s) {
			return studentID == s.studentID && s.name.equals(this.name);
			
		}
	}

	public static void main(String[] args) {
		Hashtable<String, Student> tab = new Hashtable<String, Student>();
		tab.put("Jo",new Student(1234,"Joseph"));
		Set<String> s = tab.keySet();
		Iterator<String> it = s.iterator();
		while(it.hasNext()) {
			String n = it.next();
			Student std = tab.get(n); //list the values
			System.out.println("Key: " + n + "Value: " + std.toString());
		}
		//declare new table
		Hashtable<Student, String> tab2 = new Hashtable<Student, String>();
		//should override hashcode
		

	}
	
	
}
