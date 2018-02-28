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
	
	@Override //Place holder traversal sum --need to account for order of tree (branches = operators I think?)
	public int evalTree() {
		return (int) evalTreeHelper(this);	//pass in self as the root for the first pass
	}
	
	//TODO Should not return an int tbh
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
			
			if(root.getValue().equals("/"))
				return op1 / op2;
		} else //number have no children in expression trees, so just return val
			return (int) Integer.parseInt((String) root.getValue());
		
		return 0; //TODO Shouldn't make it here????
	}

	@Override
	public String toPrefixNotation() {
		return preOrder(this, "");
	}

	@Override
	public String toInfixNotation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toPostfixNotation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int postfixEval(String[] exp) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	//Helper booleans//
	private boolean isOperand(String symbol) {	//is number
		try {
			Integer.parseInt(symbol);	//if this throws an error then it's not a num
			return true;
		} catch (NumberFormatException e) {
			//System.out.print(e + "\nSymbol is not a number");
			return false;
		}
	}
	
	private boolean isOperator(String symbol) {	//is + or *
		return ( symbol.equals("+") || symbol.equals("*") || symbol.equals("-") || symbol.equals("/"));
	}
	
	//Helper Traversals modified from p. 581
	private String preOrder(TreeNode root, String r) {
		String result = r;
		if(root != null) {
			preOrder(root.getLeft(), result);
			preOrder(root.getRight(), result);
			return result += (String) root.getValue();
		} else 
			return ""; //if null node (DNE) return blank string 
	}
	
	private String postOrder(TreeNode root, String r) {
		String result = r;
		if(root != null) {
			postOrder(root.getLeft(), result);
			postOrder(root.getRight(), result);
			return result += (String) root.getValue();
		} else 
			return ""; //if null node (DNE) return blank string 
	}

	private String inOrder(TreeNode root, String r) {
		String result = r;
		if(root != null) {
			inOrder(root.getLeft(), result);
			result += (String) root.getValue();
			inOrder(root.getRight(), result);
			return result;
		} else 
			return "";
	}
}
