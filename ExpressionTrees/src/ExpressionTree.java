/**
 * 	Class Description: This class extends the TreeNode object and implements the supplied Expressions interface 
 * 						which requires methods enabling the for evaluation/output of the values of expression 
 * 						in prefix, infix, and postfix notation.
 *  @author MurphyP1
 *  @date 3/1/18
 */

import java.util.Stack;

public class ExpressionTree extends TreeNode implements Expressions {
	/** 
	 * The default constructor which accepts an array of Strings containing the terms the Expression in postfix notation  and builds a Tree out of the values
	 * @author MurphyP1
	 * @date 2/28/18
	 * @method ExpressionTree
	 * @param expression an array of Strings containing the terms the Expression
	 */
	public ExpressionTree(String[] expression) {
		super("");	//Create empty TreeNode
		TreeNode temp = buildTree(expression);	//Get the full doohicky from buildTree
		this.fix(temp.getValue(), temp.getLeft(), temp.getRight());	//Circumvent casting issues
	}

	/** 
	 * A helper method used by the constructor to process the elements of the supplied array and add the data to a Tree structure 
	 * @author MurphyP1
	 * @date 2/28/18
	 * @method buildTree
	 * @param expression an array of Strings containing the terms the Expression
	 * @return the root TreeNode which is converted (in the constructor) to the full ExpressionTree
	 */
	@Override
	public TreeNode buildTree(String[] expression) {
		Stack<Object> unprocessed = new Stack<Object>();
		
		for(int i = 0; i < expression.length; i++) {
			String current = expression[i].trim();	//cut down on O(n) iterations
			
			if( isOperand(current) ) //Leave as String here for convenient evaluation 
				unprocessed.push(new TreeNode(current) );	//Create it as a TreeNode when you push 
			
			else if( isOperator(current) ) {
				TreeNode right = (TreeNode) unprocessed.pop();	 
				TreeNode left = (TreeNode) unprocessed.pop();	//everything in the stack is a TreeNode by default 
				//creates a sub-tree with operator as root and last 2 operands as the left and right sub-nodes
				TreeNode root = new TreeNode(current, left, right); 
				
				unprocessed.push(root); //replace last 2 operands with a reference to the tree node that has them as leaves
			}	
		}
		return (TreeNode) unprocessed.pop();	//push that mini expression back onto the stack
	}
	
	//EVALUATION METHODS 
	
	/** 
	 * An evaluation method which returns the integer value of an ExpressionTree using a recursive helper method 
	 * @author MurphyP1
	 * @date 2/28/18
	 * @method evalTree
	 * @return the value of the ExpressionTree
	 */
	@Override 
	public int evalTree() {
		return (int) evalTreeHelper(this);	//pass in self as the root for the first pass
	}

	/** 
	 * The helper method called by evalTree which 
	 * @author MurphyP1
	 * @date 2/28/18
	 * @method evalTreeHelper
	 * @param root a TreeNode whose value is returned if it is an operand, or passed to another recursive call if it is an operator
	 * @return the value of the ExpressionTree
	 */
	private int evalTreeHelper(TreeNode root) {
		if (isOperator( (String) root.getValue()) ) {
			int op1 = evalTreeHelper(root.getLeft());
			int op2 = evalTreeHelper(root.getRight());
			
			if(root.getValue().equals("+"))
				return op1 + op2;
			
			if(root.getValue().equals("*"))
				return op1 * op2;
			
			//Bonus operators
			if(root.getValue().equals("-"))
				return op1 - op2;
			
			if(root.getValue().equals("/")) { 
				if(op2 != 0)
					return op1 / op2;	
				else 
					return op1 / 1; //don't divide by zero
			}
			
		} else //operands have no children in ExpressionTrees, so just return the value of the node
			return (int) Integer.parseInt((String) root.getValue());
		
		return 0; //dumb
	}

	/** 
	 * An evaluation method which uses a stack to evaluate a postfix expression
	 * @author MurphyP1
	 * @date 2/28/18
	 * @method postfixEval
	 * @param exp an array of Strings whose elements are individual terms of a postfix expression
	 * @return the value of the String expression
	 */
	@Override
	public int postfixEval(String[] exp) {
		Stack<Integer> unprocessed = new Stack<Integer>();
		
		for(int i = 0; i < exp.length; i++) {
			String current = exp[i].trim();	//cut down on O(n) iterations
			
			if( isOperand(current) ) //Leave as String here for convenient evaluation 
				unprocessed.push(Integer.parseInt(current));
			
			else if( isOperator(current) ) {
				int right = unprocessed.pop();	 
				int left =  unprocessed.pop();
				int result = 0;
				
				if(current.equals("+") )
					result = left + right;
				
				else if(current.equals("*") )
					result = left * right;
				
				else if(current.equals("-") )	//bonus operators
					result = left - right;
				
				else if(current.equals("/") )
					if ( right != 0 )
						result = left / right;
					else 
						result = left / 1;	//don't divide by zero here either 
				
				unprocessed.push(result); //replace last 2 operands with their sum or product depending on the operator
			}	
		}
		return unprocessed.pop();	
	}
	
	//toStrings//
	
	/** 
	 * A method which returns the expression in prefix notation by calling a recursive method which traverses the tree in appropriate order
	 * @author MurphyP1
	 * @date 2/28/18
	 * @method toPrefixNotation
	 * @return the expression as a String in prefix notation
	 */
	@Override
	public String toPrefixNotation() {
		return preOrder(this, "");
	}

	/** 
	 * A method which returns the expression in infix notation by calling a recursive method which traverses the tree in appropriate order
	 * @author MurphyP1
	 * @date 2/28/18
	 * @method toInfixNotation
	 * @return the expression as a String in infix notation with parentheses 
	 */
	@Override
	public String toInfixNotation() {
		return inOrder(this, "");
	}

	/** 
	 * A method which returns the expression in postfix notation by calling a recursive method which traverses the tree in appropriate order
	 * @author MurphyP1
	 * @date 2/28/18
	 * @method toPostfixNotation
	 * @return the expression as a String in postfix notation
	 */
	@Override
	public String toPostfixNotation() {
		return postOrder(this, "");
	}
	
	//Helper Traversals modified from p. 581//
	
	/** 
	 * A recursive method which extracts the value of the current TreeNode before moving to the left, then right
	 * @author MurphyP1
	 * @date 2/28/18
	 * @method preOrder
	 * @param root the root of the current node tree
	 * @param soFar a String which is added to with each recursive call 
	 * @return the value of the current node, as well as the values of the left and right TreeNodes (or node trees)
	 */
	private String preOrder(TreeNode root, String soFar) {	//V L R 
		String result = soFar;
		if(root != null) {
			result += root.getValue() + " ";
			result += preOrder(root.getLeft(), soFar );
			result += preOrder(root.getRight(), soFar );
		} 
		return result;
	}
	
	/** 
	 * A recursive method which  traverses to the left and right sub-trees before extracting the value of the current TreeNode
	 * @author MurphyP1
	 * @date 2/28/18
	 * @method postOrder
	 * @param root the root of the current node tree
	 * @param soFar a String which is added to with each recursive call 
	 * @return the values of the left and right TreeNodes (or node trees) as well as the value of the current TreeNode
	 */
	private String postOrder(TreeNode root, String soFar) {	//L R V
		String result = soFar;
		if(root != null) {
			result += postOrder(root.getLeft(), soFar );
			result += postOrder(root.getRight(), soFar );
			result += root.getValue() + " ";
		} 
		return result;
	}

	/** 
	 * A recursive method which traverses to the left, extracts the value of the current TreeNode, and then traverses to the right sub-tree, adding parentheses to the final String as necessary to retain Order of Operations
	 * @author MurphyP1
	 * @date 2/28/18
	 * @method inOrder
	 * @param root the root of the current node tree
	 * @param soFar a String which is added to with each recursive call 
	 * @return the value of the left TreeNode (or node trees), then current node, then the value of the right TreeNode (or node trees) with appropriate parentheses in between
	 */
	private String inOrder(TreeNode root, String soFar) {	 //L V R
		String result = soFar;
		if(root != null) {
			TreeNode left = new TreeNode(inOrder(root.getLeft(), soFar ) );
			String v = (String) root.getValue(); 
			TreeNode right = new TreeNode(inOrder(root.getRight(), soFar ) );
			
			if(isOperator(v))	
				result += "(" + left.getValue() + " " + v + " " + right.getValue() + ")";	//Add spaces for readability
			else 
				result += left.getValue() + v + right.getValue();
		} 
		return result;
	}

	//HELPER BOOLEANS//
	
	/** 
	 * A boolean method which returns true if the given String is an integer 
	 * @author MurphyP1
	 * @date 2/26/18
	 * @method isOperand
	 * @param symbol the String to be evaluated 
	 * @return true if the symbol is a valid operand, false if it is not
	 */
	private boolean isOperand(String symbol) {	
		try {
			Integer.parseInt(symbol.trim());	//if this throws an error then it's not a num
			return true;
		} catch (NumberFormatException e) {
			//System.out.print(e + "\nSymbol is not a number");
			return false;
		}
	}
	
	/** 
	 * A boolean method which returns true if the given String is an accepted operator: + - * /
	 * @author MurphyP1
	 * @date 2/26/18
	 * @method isOperator
	 * @param symbol the String to be evaluated 
	 * @return true if the symbol is a valid operator, false if it is not
	 */
	private boolean isOperator(String symbol) {
		return ( symbol.trim().equals("+") || symbol.trim().equals("*") || symbol.trim().equals("-") || symbol.trim().equals("/"));
	}
}
