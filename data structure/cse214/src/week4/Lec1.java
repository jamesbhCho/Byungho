package week4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

//array class 
//ArrayList constructor

/*
 * public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA; //empty array
    }
 */
//private OBject[] grow(int minCapacity)
/*
 * Increases the capacity to ensure that it can hold at least the
   number of elements specified by the minimum capacity argument.
 
   private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = ArraysSupport.newLength(oldCapacity, //static method: 
                    minCapacity - oldCapacity,	//minimum growth 								//the actual division takes longer 
         oldCapacity >> 1);	//bit shift operator(shift all the bits to the right once== dividing that number by 2) //preferred growth as the main algorithm allocates more space
            return elementData = Arrays.copyOf(elementData, newCapacity);  //copies the current data and assign new capacity 
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }
    
    //increase the current capacity by half 

    private Object[] grow() {
        return grow(size + 1);
    }
    
    //calculate new capacity 
    public static int newLength(int oldLength, int minGrowth, int prefGrowth) {
        // assert oldLength >= 0
        // assert minGrowth > 0

        int newLength = Math.max(minGrowth, prefGrowth) + oldLength;
        if (newLength - MAX_ARRAY_LENGTH <= 0) {
            return newLength;
        }
        return hugeLength(oldLength, minGrowth); //newly calculated capacity if it exceeds the current arr length //checks memory restriction
    }
 
 
 contents will be preserved within the new capacity 
*/


/*
 *  get method ffrom ArrayList class
 *  public E get(int index) {O(1)
        Objects.checkIndex(index, size); //check if the given index is valid if not, throw an exception
        return elementData(index); //return the element at that position 
        
    }
    
    what it means
    public E get(int index){
    if(index<0 || index <= size){
    	throw new IndexOutOfBoundsException();
    return (E) element[index];
    
    why throw an exception rather than null?
    null can be a valid value you can store in an ArrayList. Can't be used to signal an error.
    
    
    
     public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldValue = elementData(index);
        elementData[index] = element; //override
        return oldValue;
    }
    
    modify the element at the given index
    pre execution steps of checking index is valid 
                                                                                           assigns the valid index to 'oldValue' and overrides it
    however returns the oldValue to ensure the value that has been overriden.
 */


public class Lec1 {//arrayList

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
	}

}


