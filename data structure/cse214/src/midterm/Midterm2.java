package midterm;

public class Midterm2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/*
	 * write a recursive method that returns true if the given tree satisfies 
	 * the black count rule of a red-black tree, and false otherwise.  
	 * you may assume that the other rules are satisfied. 
	 * clearly identify the base case and recursive case and do not import anything
	 */
	class RBNode {
		RBNode left, right, parent;
		int data;
		boolean color; // black is false
	}

	public static boolean blackCountSatisfied(RBNode root) {
		// Base case: An empty subtree is considered to satisfy the black count rule
		if (root == null)
			return true;

		// Recursive case: Check the black count rule for left and right subtrees
		boolean leftSatisfied = blackCountSatisfied(root.left);
		boolean rightSatisfied = blackCountSatisfied(root.right);

		// Count the number of black nodes on the left and right subtrees
		int leftBlackCount = countBlackNodes(root.left);
		int rightBlackCount = countBlackNodes(root.right);

		// Check if the black count rule is satisfied for the current node
		boolean currentSatisfied = (root.color == false && leftBlackCount == rightBlackCount);

		// Return true if the black count rule is satisfied for the current node
		// as well as its left and right subtrees
		return currentSatisfied && leftSatisfied && rightSatisfied;
	}

	private static int countBlackNodes(RBNode node) {
		// Base case: An empty subtree contributes 0 black nodes
		if (node == null)
			return 0;

		int count = 0;

		// Count the black nodes based on the current node's color
		if (node.color == false)
			count = 1;

		// Recursively count the black nodes on the left and right subtrees
		count += countBlackNodes(node.left);
		count += countBlackNodes(node.right);

		return count;
	}


	//2.
	/*write a method that prints out all elements of the given BST 
	 * in descending order and provide a time complexity analysis. 
	 * Every complexity must be accounted for to receive credit. 
	 * Your algorithm should be as efficient as possible
	 */
	class Node {
		Node left, right, parent;
		int data;
		// assume a constructor exists
	}

	public static void printDescending(Node root) {
		if (root == null) {
			return;
		}
		//in order traversal
		printDescending(root.right);
		System.out.println(root.data);
		printDescending(root.left);

	}


}



