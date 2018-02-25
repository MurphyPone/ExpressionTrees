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
		TreeNode result = new TreeNode(5);
		//Fill structures with values of expression based off the helper method 
		Stack[] temp = buildTreeHelper(expression);	
		Stack<Integer> nums = temp[0];
		Stack<String> operators = temp[1];
		Stack<String> parens = temp[2];

		
		return result;
	}
	
	private Stack[] buildTreeHelper(String[] expression) throws NumberFormatException{
		Stack<Integer> nums = new Stack<Integer>();		
		Stack<String> operators = new Stack<String>();
		Stack<String> parens = new Stack<String>();
		Stack[] result = new Stack[3];	//Create an empty array of size 3 to hold our stacks
		
		for(int i = 0; i < expression.length; i++) {
			String current = expression[i]; //cut down on those O(n) iterations 
			
			if(current.equals( "(" ) || current.equals( ")" ) ) //if its a ( or ) then push to parens
				parens.push(current); 
			
			else if(current.equals("*") || current.equals("+") ) //if its a * or + then push to operators
				operators.push(current);
			
			else {
				try { //Try to convert it to an Int and push to the nums stack
					nums.push(Integer.parseInt(current));	
				} catch (NumberFormatException e) {
					System.out.print(e); //Remove after testing?
				}
			}
		}
		
		return result;
	}

	@Override
	public int evalTree() {
		// TODO Auto-generated method stub
		return 0;
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
