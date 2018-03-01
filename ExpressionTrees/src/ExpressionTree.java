import java.util.Stack;

//	2/25/18
public class ExpressionTree extends TreeNode implements Expressions {
	//constructor 
	public ExpressionTree(String[] expression) {
		super("");	//Create empty TreeNode
		TreeNode temp = buildTree(expression);	//Get the full doohicky from buildTree
		this.fix(temp.getValue(), temp.getLeft(), temp.getRight());	//Circumvent casting issues
	}

	@Override
	public TreeNode buildTree(String[] expression) {
		Stack<Object> unprocessed = new Stack<Object>();
		
		for(int i = 0; i < expression.length; i++) {
			String current = expression[i].trim();	//cut down on O(n) iterations
			
			if( isOperand(current) ) //Leave as String here for convenient evaluation 
				unprocessed.push(new TreeNode(current) );	//Create it as a TreeNode when you push 
			
			else if( isOperator(current) ) {
				TreeNode right = (TreeNode) unprocessed.pop();	 
				TreeNode left = (TreeNode) unprocessed.pop();	//current/everything in the stack is a TreeNode by default 
				//creates a sub-tree with operator as root and last 2 operands as the left and right sub-nodes
				TreeNode root = new TreeNode(current, left, right); 
				
				unprocessed.push(root); //replace last 2 operands with a reference to the tree node that has them as leaves
			}	
		}
		return (TreeNode) unprocessed.pop();	//should be the tree
	}
	
	//Evals
	
	@Override //Place holder traversal sum --need to account for order of tree (branches = operators I think?)
	public int evalTree() {
		return (int) evalTreeHelper(this);	//pass in self as the root for the first pass
	}

	//Should not return an int tbh
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
			
		} else //number have no children in expression trees, so just return val
			return (int) Integer.parseInt((String) root.getValue());
		
		return 0;	//dumb
	}

	@Override	//Uses a Stack  
	public int postfixEval(String[] exp) {
		Stack<Integer> unprocessed = new Stack<Integer>();
		
		for(int i = 0; i < exp.length; i++) {
			String current = exp[i].trim();	//cut down on O(n) iterations
			
			if( isOperand(current) ) //Leave as String here for convenient evaluation 
				unprocessed.push(Integer.parseInt(current));
			
			else if( isOperator(current) ) {
				int right = unprocessed.pop();	 
				int left =  unprocessed.pop();	//current/everything in the stack is a TreeNode by default 
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
				
				unprocessed.push(result); //replace last 2 operands with a reference to the tree node that has them as leaves
			}	
		}
		return unprocessed.pop();	
	}
	
	//toStrings
	@Override
	public String toPrefixNotation() {
		return preOrder(this, "");
	}

	@Override
	public String toInfixNotation() {
		return inOrder(this, "");
	}

	@Override
	public String toPostfixNotation() {
		return postOrder(this, "");
	}

	//Helper booleans//
	private boolean isOperand(String symbol) {	//is number
		try {
			Integer.parseInt(symbol.trim());	//if this throws an error then it's not a num
			return true;
		} catch (NumberFormatException e) {
			//System.out.print(e + "\nSymbol is not a number");
			return false;
		}
	}
	
	private boolean isOperator(String symbol) {	//is + or *
		return ( symbol.trim().equals("+") || symbol.trim().equals("*") || symbol.trim().equals("-") || symbol.trim().equals("/"));
	}
	
	//Helper Traversals modified from p. 581
	private String preOrder(TreeNode root, String soFar) {	//V L R 
		String result = soFar;
		if(root != null) {
			result += root.getValue() + " ";
			result += preOrder(root.getLeft(), soFar );
			result += preOrder(root.getRight(), soFar );
		} 
		return result;
	}
	
	private String postOrder(TreeNode root, String soFar) {	//L R V
		String result = soFar;
		if(root != null) {
			result += postOrder(root.getLeft(), soFar );
			result += postOrder(root.getRight(), soFar );
			result += root.getValue() + " ";
		} 
		return result;
	}

	//requires implied parens
	private String inOrder(TreeNode root, String soFar) {	 //L V R
		String result = soFar;
		if(root != null) {
			//if result[result.length-1] isOp then don't, else do add paren
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
}
