public class ExpTreeTester {
	public static void main(String[] args) {
		String[] expression = new String[] {"5", "3", "+", "5", "*"}; //= (5+3) * 5 = 40
	
		ExpressionTree x = new ExpressionTree(expression);
		System.out.println("The value of this expression tree is: " + x.evalTree());
	}
}