package week10;

public class Lec1 {

	public static void main(String[] args) {//post midterm 2

		//1a. 8 queue(reversing the queue)
		// using enqueue,dequeue, and peek

		//1b. list two traveral seqeuences
		// level order, in order

		//2. 
		///number of black, stop at the leaf node and the single child 

		//3b. rb tree no matter how skewed it will always be logarithmic
		//3e. not bst, o(n)

		//4. 
		//in order - ascending order
		// reverse of inorder traversal -recursion

		//lecture
		/*
		 * priority queue
		 * dequeue is based on priority 
		 * if they are all the same priority no need for adjustment for size comparison
		 * -access or removal is on the highest priority element in the queue
		 * regular queue 
		 * - every element has the same level of priority 
		 * 
		 * Heap
		 *	-complete binary tree
		 *		-every level is full except for the last level(leaves) and it is crammed to the left  
		 * 	 		- base case: empty or the root element is the smalllest element(min-heap)
		 * 	 		- recursive case: left and right subtrees are also heap
		 * 
		 * Heap representation with array
		 * accessing element: O(1), random access
		 * indexing is straight forward:
		 * 	children of the node: 
		 * 		left: 2i+1
		 * 		right: 2i+2
		 * 	parent of the index
		 * 		(int)((i-1)/2)
		 * 
		 * insertion into min heap
		 * 	works just like the regular add()
		 * 		-append the new element to the end of the array
		 * 		-but the resulting tree is not always go to be in heap after insertion
		 * 		So, percolateUp()
		 * 			-initially add the new element to the end of the array
		 * 			 while(new element is in wrong position)
		 * 				if(new element is smaller than its parent)
		 * 					swich the position of the two
		 * 			 when the while loop terminates, depending on the size of the new element it will be the root or the parent
		 * 
		 * deletion in min heap
		 * 	in the case of deleting the root node
		 * 		switch the root node with the last element
		 * 		percolateDown() the new root
		 * 		that is, compare the new root with its right child 
		 * 			if it is smaller maintain position
		 * 			else switch with the child
		 */

	}

	public static void heapify(int[] arr, int n, int i) {

		int largest = i;
		int left = 2*i+1;
		int right = 2*i+2;

		//if left child is larger than root
		if(left < n && arr[left] > arr[largest]) largest = left;

		//if right child is larger than root
		if(right < n && arr[right] > arr[largest]) largest = right;

		// If largest is not root
		if (largest != i) {
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;

			// Recursively heapify the affected sub-tree
			heapify(arr, n, largest);

		}

	}
	
	static int deleteRoot(int arr[], int n)
    {
        // Get the last element
        int lastElement = arr[n - 1];
 
        // Replace root with first element
        arr[0] = lastElement;
 
        // Decrease size of heap by 1
        n = n - 1;
 
        // heapify the root node
        heapify(arr, n, 0);
 
        // return new size of Heap
        return n;
    }
	
	static void heapifyInsert(int[] arr, int n, int i)
    {
        // Find parent
        int parent = (i - 1) / 2;
     
        if (arr[parent] > 0) {
            // For Max-Heap
            // If current node is greater than its parent
            // Swap both of them and call heapify again
            // for the parent
            if (arr[i] > arr[parent]) {
                 
                  // swap arr[i] and arr[parent]
                int temp = arr[i];
                arr[i] = arr[parent];
                arr[parent] = temp;
               
                // Recursively heapify the parent node
                heapifyInsert(arr, n, parent);
            }
        }
    }
 
    // Function to insert a new node to the heap.
    static int insertNode(int[] arr, int n, int Key)
    {
        // Increase the size of Heap by 1
        n = n + 1;
     
        // Insert the element at end of Heap
        arr[n - 1] = Key;
     
        // Heapify the new node following a
        // Bottom-up approach
        heapifyInsert(arr, n, n - 1);
         
        // return new size of Heap
        return n;
    }

}
