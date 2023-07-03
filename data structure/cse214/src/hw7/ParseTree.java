package hw7;

/**
 * Name: Byungho Cho
 * SBUID: 115236235
 * Do not use any unauthorized packages.
 * For all recursive methods, feel free to use helper methods.
 */

/*
The task is to convert the given infix expression into a parse tree. A parse tree 
is a binary tree whose internal nodes are the operators and whose leaves correspond to operands. 
The expressions in this assignment will consist of the five basic operators (+,-,*,/,^) and positive 
integers as operands. In addition to these symbols, you will also have to deal with parentheses
 */
public class ParseTree {

	Node root;
	enum OP {PLUS, TIMES, MINUS, DIVIDE}; // This is just for your reference. You're not required to use this.

	public ParseTree() {
	}

	private class Node {
		Node parent, left, right;
		// Add more necessary fields and methods
		String data;
		
		public Node(String exp) { // Modify the constructor accordingly
			this.parent = null;
			this.left = null;
			this.right = null;
			this.data = exp;
		}
		
	}
	/*
	 * Build a parse tree, to be pointed by root, that represents 'expression'.
	 * Implement recursively.
	 *  Build the parse tree that represents the given expression string. The input 
		string is an infix notation consisting of the four operators, parentheses, and numerical 
		operands. Provide a recursive solution, possibly by using helper methods.
		infix-> parse tree, build left and right and return root
	 */

	/*Documentation: buildTree()
	 *For this implementation of buildTree, its objective is to parse tree in a way that parent
	 *nodes are operators and the leaves are operands. Also, the level of each parent nodes are 
	 *determined by their precedence. That is, lowest precedence operator sits at the top while the
	 *highest precedence will be beneath that level. To start, I made a helper function so that I can
	 *update the root. For the base case, when expression becomes a single String(a character), then
	 *it should return itself as a node. The recursive part, the logic here is to traverse the expression
	 *in a way that it locates the lowest precedence operator outside of parentheses. there are three variables 
	 *that indicate the index of the lowest precedence operator,count of parens, and the lowest precedence. 
	 *Traverses through the String and when it passes the parentheses it checks for each operator and based
	 *on previous lowest precedence operator it overrides the index that keep track of index of lowest 
	 *precedence operator. This for loop does not check for operators within the parentheses. For that 
	 *we iterate it via recursive part with parentheses cut out at the end. After the for loop iterations,
	 *the index position for low precedence should be valid so we make that into a node. its left and right
	 *should be a recursive call that contains the left String(0 to up to the node index) and vice versa. 
	 *After that, link the node as the parent for the above recursive calls and return the node itself.
	 */
	public void buildTree(String expression) {
		//to be pointed by root, that represents 'expression'
		root = buildTreeHelper(expression);
	}

	private Node buildTreeHelper(String expression) {
		// Base case: single string == node 
		if (expression.length() == 1) {
			return new Node(expression); //node
		}
		//1+2*3-2
		//(2-4)*(6+9)
		// Find the index of the operator with the lowest precedence = parent node 
		int operatorPos = -1;
		int parenCnt = 0;
		//just to indicate the lowest value(x>3)
		int lowestPrecedence = 4;
		for (int i = 0; i<expression.length();i++) {
			char c = expression.charAt(i);
			if (c == '(') {
				parenCnt++;
			} else if (c == ')') {
				parenCnt--;
			} else if (parenCnt == 0) {//no parens 
				if (c == '+' || c == '-') {
					if (lowestPrecedence >= 1) {
						lowestPrecedence = 1;
						operatorPos = i;
					}
				} else if (c == '*' || c == '/') {
					if (lowestPrecedence >= 2) {
						lowestPrecedence = 2;
						operatorPos = i;
					}
				} else if (c == '^') {
					if (lowestPrecedence >= 3) {
						lowestPrecedence = 3;
						operatorPos = i;
					}
				}
			}
		}

		// single value, no operator
		if (operatorPos == -1) {//for operators within parens
			if (expression.charAt(0) == '(' && expression.charAt(expression.length() - 1) == ')') {
	            return buildTreeHelper(expression.substring(1, expression.length() - 1));//cut out
	        }
	        return new Node(expression);
		}

		//root node
		Node node = new Node(expression.substring(operatorPos, operatorPos + 1));

		//recursive: left and right subtrees
		node.left = buildTreeHelper(expression.substring(0, operatorPos));
		node.right = buildTreeHelper(expression.substring(operatorPos + 1));

		//parent node 
		node.left.parent = node;
		node.right.parent = node;

		return node;
	}

	/*
	 * Evaluate the expression represented by 'root'.
	 * do the computation described by the expression
	 * Implement recursively. 
	 */

	/*Documentation: eval()
	 * For this implementation of eval, the logic is to do the computation descrived
	 * by the expression. That is, for each built node from the buildTree method, it should
	 * calculate whatever that node is to its left and right value. Given that the base case
	 * is when the recursion reached the end of the branch which is null it just returns 0.
	 * Otherwise, we check for parentheses first and if there exists parentheses we 'skip' it 
	 * by doing a recursive call to the left. Then we keep evaluating left and right w
	 * hile doing the computation for each operators.
	 * If the node is not one of the five operators, then it is either going to be 
	 * a number or just wrong input. Therefore, if it is a number just return it,
	 * and if it is not a number throw an exception.
	 */
	public double eval() {
		return evaluate(root);
	}

	private double evaluate(Node node) {
		//base case
	    if(node == null){
	    	return 0;
	    }
	    
	    if (node.data.charAt(0) == '(' && node.data.charAt(node.data.length()-1) == ')') {
	        // recursively evaluate child node without removing parentheses
	        return evaluate(node.left);
	    }
	    double leftVal = evaluate(node.left);
	    double rightVal = evaluate(node.right);

	    if (node.data.equals("+")) {
	        return leftVal + rightVal;
	    } else if (node.data.equals("-")) {
	        return leftVal - rightVal;
	    } else if (node.data.equals("*")) {
	        return leftVal * rightVal;
	    } else if (node.data.equals("/")) {
	        return leftVal / rightVal;
	    } else if (node.data.equals("^")) {
	        return Math.pow(leftVal, rightVal);
	    } else if( Character.isLetterOrDigit(node.data.charAt(0))){
	    	return Double.parseDouble(node.data);
	    }else {
	    	throw new IllegalArgumentException("invalid operator: " + node.data);
	    }
	}

	/*
	 * Evaluate the expression represented by 'root'.
	 * Implement iteratively. 
	 */
	
	/*Documentation: iterativeEval()
	 * For this implementation of iterativeEval, if it is a leaf node its a number. However for the 
	 * internal nodes depending on the branches it can have both the number and the operator. First 
	 * check if the nodes are internal nodes by if their left and right nodes exists. Otherwise, we 
	 * move the pointer to the left or right depending on if it has left or right. Then if the data
	 * is an operand(including negative sign) we store the value of the left first. Otherwise, 
	 * it is an operator so we just let it lterate to next by replacing current pointer with the 
	 * next left. The right side for this symmetrical to the left one. While the datas are stored
	 * in the desginated values(left and right) we then go on to check the operator for each 
	 * data and do the computation necessary. Then update the parent node with the new result node
	 * and move up the tree by replacing the curr with its parent. With these checks in the while 
	 * loop it should do the computation.
	 * 
	 */
	public double iterativeEval() {
		return iterativeEvaluate(root);
		
	}
	private double iterativeEvaluate(Node node) {
		
	    if (node == null) {
	        return 0;
	    }
	    double result = 0;
	    double leftVal = 0;
	    double rightVal = 0;
	    Node curr = node;
	    while (curr != null) {
	        if (curr.left == null && curr.right == null) { // leaf node
	            result = Double.parseDouble(curr.data);
	        } else if (curr.left != null && curr.right != null) { // internal node
	        	//if operands (including negatives)
	            if (Character.isDigit(curr.left.data.charAt(0)) || (curr.left.data.charAt(0) == '-' && curr.left.data.length() > 1 )) {
	                leftVal = Double.parseDouble(curr.left.data);
	            } else { // left child is operator
	                curr = curr.left;
	                continue;
	            }//if operands
	            if (Character.isDigit(curr.right.data.charAt(0)) || (curr.right.data.charAt(0) == '-' && curr.right.data.length() > 1 )) {
	                rightVal = Double.parseDouble(curr.right.data);
	            } else { // right child is operator
	                curr = curr.right;
	                continue;
	            }
	            //computation
	            if (curr.data.equals("+")) {
	                result = leftVal + rightVal;
	            } else if (curr.data.equals("-")) {
	                result = leftVal - rightVal;
	            } else if (curr.data.equals("*")) {
	                result = leftVal * rightVal;
	            } else if (curr.data.equals("/")) {
	                result = leftVal / rightVal;
	            } else if (curr.data.equals("^")) {
	                result = Math.pow(leftVal, rightVal);
	            } else if (curr.data.equals("(") || curr.data.equals(")")) {
	                // do nothing
	            } else {
	                throw new IllegalArgumentException("Invalid operator: " + curr.data);
	            }
	            //updating the parent node with the new result node
	            if (curr.parent != null) {
	                if (curr.parent.left == curr) {
	                    curr.parent.left = new Node(Double.toString(result));
	                } else {
	                    curr.parent.right = new Node(Double.toString(result));
	                }
	            }//moving up the tree
	            curr = curr.parent;
	            
	        } else { // missing a child( x internal node)
	            if (curr.left != null) {
	                curr = curr.left;
	            } else {
	                curr = curr.right;
	            }
	        }
	    }
	    return result;
	}


	/*
	 * Return the original infix notation. You shouldn't just return the stored input string.
	 * Implement recursively.
	 */
	
	/*Documentation: toString()
	 * for this implementation of toString(), the objective isto turn the parse tree back into 
	 * its infix notation. To do that, I have thought of inorder traversal where it starts from 
	 * the left most. To append each operator or operands on each recursion, I used stringbuilder
	 * to concatenate each data onto the next. The base case is when the node is null which means
	 * we have traversed the entire tree. I have failed to think of an algorithm where it puts 
	 * parentheses where it is. Instead, I have put parentheses to the beginning and the end of 
	 * every equation. That is, since we traverse the tree via in-order traversal, if the node 
	 * doesn't either the left or right it is an operand. Therefore, we can append paratheses in
	 * the front and the back using this logic. So before the recursion append the parenthese and
	 * after append the closing parenthese. This would put parentheses after every equation. However,
	 * it is not an ideal solution as it doesn't put the parentheses where it is originally located.
	 */

	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    buildString(sb, root);
	    return sb.toString();
	}

	private void buildString(StringBuilder sb, Node node) {
		//base case
	    if (node == null) {
	        return;
	    }//building parens 
	    if (node.left != null || node.right != null) {
	        sb.append("(");
	    }
	    //in order traversal
	    buildString(sb, node.left);
	    sb.append(node.data);
	    buildString(sb, node.right);
	    if (node.left != null || node.right != null) {
	        sb.append(")");
	    }
	}


	/*
	 * Return the postfix version of the expression.
	 * Implement recursively.
	 */
	
	/*Documentation: toPostfixString
	 * For this implementation of toPostfixString(), we simply traverse the tree in 
	 * post-order traversal. Since the leaves are operands with the post order 
	 * traversal it would hit the operands before an operator. With the logic
	 * entrenched, the actual implementation is executed with the help of Stringbuilder
	 * to append all the nodes when traversing. After the traversal comes to an end, which
	 * in the case it would be null, we simply return and this serves as the base case. 
	 * As for the recursive case, like stated, will recrusively call node left and right
	 * before appending the current node.
	 */
	public String toPostfixString() {
	    StringBuilder sb = new StringBuilder();
	    buildPostfixString(sb, root);
	    return sb.toString();
	}

	private void buildPostfixString(StringBuilder sb, Node node) {
	    if (node == null) {
	        return;
	    }
	    buildPostfixString(sb, node.left);
	    buildPostfixString(sb, node.right);
	    sb.append(node.data);
	}



	public static void main(String[] args) {

		ParseTree pt1 = new ParseTree();
		String[] expressions = {
				"1+2*3-2", // expected output: "-",
				"(2-4)*(6+9)", // expected output: "*",
				"1+2", // expected output: "+",
				"3", // expected output: "3"
		};

		for (String exp : expressions) {
			
			pt1.buildTree(exp);
			//System.out.println("Expression: " + exp);
			//System.out.println("Result: " + pt1.root.data);
			//System.out.println("====================================");
		}
		
		ParseTree pt2 = new ParseTree();
		//pt2.buildTree("(2-4)*(6+9)");
		//double result = pt2.eval();
		//System.out.println(result);

		ParseTree pt3 = new ParseTree();
		pt3.buildTree("(2-4)*(6+9)");
		//pt3.buildTree("1+2");
		//double result2 = pt3.iterativeEval();
		//System.out.println(result2);
		System.out.println(pt3.toString());
		System.out.println(pt3.toPostfixString());
	}
}


