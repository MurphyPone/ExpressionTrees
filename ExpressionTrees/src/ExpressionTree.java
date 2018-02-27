import java.nio.charset.Charset;
import java.util.Stack;

//	2/25/18
public class ExpressionTree extends TreeNode implements Expressions {
	//Default constructor 
	public ExpressionTree(Object v) {
		super(v);
		// TODO Auto-generated constructor stub
	}
	
	//More args constructor
	public ExpressionTree(Object v, TreeNode l, TreeNode r) {
		super(v, l, r);
	}

	@Override
	public TreeNode buildTree(String[] expression) {
		Stack<Object> unprocessed = new Stack<Object>();
		
		for(int i = 0; i < expression.length; i++) {
			String current = expression[i];	//cut down on O(n) iterations
			
			if( isOperand(current) ) 
				unprocessed.push(current);
			
			else if( isOperator(current) ) {
				TreeNode right = new TreeNode(unprocessed.pop());	 
				TreeNode left = new TreeNode(unprocessed.pop());
				//creates a cub-tree with operator as root and last 2 operands as the left and right sub-nodes
				TreeNode root = new TreeNode(current, left, right); 
				
				unprocessed.push(root); //replace last 2 operands with a reference to the tree node that has them as leaves
			}
			//TODO else if isParen
				
		}
		
		return (TreeNode) unprocessed.pop();	//should be the tree
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
		return ( symbol.equals("+") || symbol.equals("*"));
	}
	
	private boolean isParen(String symbol) {
		return ( symbol.equals("(") || symbol.equals(")")); 
	}
	
	
	@Override //Place holder traversal sum --need to account for order of tree (branches = operators I think?)
	public int evalTree() {
		return evalTreeHelper(this);	//pass in self as the root for the first pass
	}
	
	private int evalTreeHelper(TreeNode root) {
		if(!root.hasChildren())
			return (int) root.getValue();	
		//If a tree only has one subtree, then that subtree has no children, so just get val
		else if(root.hasLeft() ) 
			return (int) root.getLeft().getValue();
		
		else if(root.hasRight()) 
			return (int) getRight().getValue();
	
		else //it has multiple children then
			return evalTreeHelper(root.getLeft()) + evalTreeHelper(root.getRight());	//TODO assuming the operator is +
	}

	@Override
	public String toPrefixNotation() {
		// TODO Auto-generated method stub
		return null;
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

}
