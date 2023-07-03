package week4;

import java.util.Objects;

public class Lec2 {//arrayList

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	}
	/*
	 * //constant time o(1) public E set(int index, E element) {
	 * Objects.checkIndex(index, size); E oldValue = elementData(index);
	 * elementData[index] = element; return oldValue; }
	 * 
	 * public int lastIndexOf(Object o) { return lastIndexOfRange(o, 0, size); }
	 * 
	 * int lastIndexOfRange(Object o, int start, int end) { Object[] es =
	 * elementData; //equal testing are different //first occurance of null if (o ==
	 * null) {//if each position is null for (int i = end - 1; i >= start; i--) { if
	 * (es[i] == null) {//equal method cannot be called on null return i; } } } else
	 * {//not searching for null for (int i = end - 1; i >= start; i--) { if
	 * (o.equals(es[i])) { return i; } } }//not found(search failure) return -1; }
	 * //time complexity O(n) O(n) + O(n) = O(n) where n is the size of the array
	 * (es[]
	 * 
	 * 
	 * //size: the actual num of the contents inside the array //capacity: the
	 * number the array can hold
	 * 
	 * 
	 * //appending method public boolean add(E e) {
	 * 
	 * modCount++; add(e, elementData, size); return true; } //O(2n) = O(n) //best
	 * case: dont have to grow the array and simply store the element within the
	 * same capacity so O(1) private void add(E e, Object[] elementData, int s) { if
	 * (s == elementData.length)//checking the capacity of the array elementData =
	 * grow(); elementData[s] = e;//appending for that last array size = s + 1; }
	 * //add() runs in amortized constant time(Adding N elements takes O(n) time)
	 * //grow the array proportional to the current capacity of the array that way
	 * //amortized time complexity = perform series of operation (o(1), o(n)
	 * //linear time complexity = single operation of o(1)
	 * 
	 * //insertion operation //insert the object at the given index //the index is
	 * likely occupied, but shouldn't be overwritten
	 * 
	 * public void add(int index, E element) { rangeCheckForAdd(index); //indexCheck
	 * modCount++; final int s; //just for an efficiency Object[] elementData;
	 * //assigning size to s //capacity == size if ((s = size) == (elementData =
	 * this.elementData).length) elementData = grow(); // grow the array when there
	 * is not enough space System.arraycopy(elementData, index, // elementData,
	 * index + 1,//(shift subarray to the right) s - index); elementData[index] =
	 * element; //insert the index size = s + 1; } //time complexity: worst case:
	 * o(n) add(0,1) //best case: o(1) add(n-1, 2) //Thus, linear time //n refers to
	 * the capacity as it grows the space(length of the array) //source
	 * //start,target arr,index of the target array, length of how mnay you want to
	 * copy public static native void arraycopy(Object src, int srcPos,Object dest,
	 * int destPos,int length); //time complexity o(n): number of element that the
	 * method has to copy is directly related to the length of the array
	 * //instantiating array is o(n)
	 * 
	 * 
	 * public E remove(int index) { Objects.checkIndex(index, size); //index check
	 * final Object[] es = elementData;
	 * 
	 * @SuppressWarnings("unchecked") E oldValue = (E) es[index]; fastRemove(es,
	 * index); //shift the entire subarray to the left(opposite of add())
	 * 
	 * return oldValue; //return the removed item(insurance) }
	 * 
	 * 
	 * private void fastRemove(Object[] es, int i) { modCount++; final int newSize;
	 * if ((newSize = size - 1) > i) //checking if i is not the last array
	 * System.arraycopy(es, i + 1, es, i, newSize - i); es[size = newSize] = null; }
	 * //time complexity: o(n) where n is the number of element stored in the
	 * array(size of the array) //length of an array vs size of the array, capacity
	 * vs size
	 * 
	 * public void clear() { modCount++; final Object[] es = elementData; for (int
	 * to = size, i = size = 0; i < to; i++) //size of the array to 0 es[i] = null;
	 * }
	 * 
	 * //time complexity: o(n) with n refers to size of the array
	 */}
