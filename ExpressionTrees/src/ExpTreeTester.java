public class ExpTreeTester {
	public static void main(String[] args) {
		String[] exp1 = new String[] {"5", "3", "+", "5", "*"}; //= (5+3) * 5 = 40
		String[] exp2 = new String[] { "5", "5", "3", "-", "*"}; //= (5-3) * 5 = 10
		String[] exp3 = new String[] { "5", "5", "/", "5", "*"}; //= (5/5) * 5 = 5	//TODO THIS IS BROKEN


		ExpressionTree xt1 = new ExpressionTree(exp1);
		ExpressionTree xt2 = new ExpressionTree(exp2);
		ExpressionTree xt3 = new ExpressionTree(exp3);

		System.out.println("The value of xt1 is: " + xt1.evalTree());
		System.out.println("The value of xt2 is: " + xt2.evalTree());
		System.out.println("The value of xt3 is: " + xt3.evalTree());


	}
}