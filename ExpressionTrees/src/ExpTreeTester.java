public class ExpTreeTester {
	public static void main(String[] args) {
		//Declarations
		String[] exp1 = new String[] {"5", "3", "+", "5", "*"}; //= (5+3) * 5 = 40
		String[] exp2 = new String[] { "5", "5", "3", "-", "*"}; //= (5-3) * 5 = 10
		String[] exp3 = new String[] { "5", "5", "/", "5", "*"}; //= (5/5) * 5 = 5	//TODO THIS IS BROKEN

		ExpressionTree xt1 = new ExpressionTree(exp1);
		ExpressionTree xt2 = new ExpressionTree(exp2);
		ExpressionTree xt3 = new ExpressionTree(exp3);

		//Eval Tests
		System.out.println("The value of xt1 is: " + xt1.evalTree());
		System.out.println("The value of xt2 is: " + xt2.evalTree());
		System.out.println("The value of xt3 is: " + xt3.evalTree());
		
		System.out.println("\n---BREAK---\n");

		//prefix Conversion tests
		System.out.println("xt1 in prefix notation is: " + xt1.toPrefixNotation() + " = " + xt1.evalTree() );
		System.out.println("xt2 in prefix notation is: " + xt2.toPrefixNotation() + " = " + xt2.evalTree() );
		System.out.println("xt3 in prefix notation is: " + xt3.toPrefixNotation() + " = " + xt3.evalTree() );
		
		System.out.println("\n---BREAK---\n");
		
		//inFix Conversion tests
		System.out.println("xt1 in infix notation is: " + xt1.toInfixNotation() + " = " + xt1.evalTree() );
		System.out.println("xt2 in infix notation is: " + xt2.toInfixNotation() + " = " + xt2.evalTree() );
		System.out.println("xt3 in infix notation is: " + xt3.toInfixNotation() + " = " + xt3.evalTree() );
		
		System.out.println("\n---BREAK---\n");

		//postFix Conversion tests
		System.out.println("xt1 in postfix notation is: " + xt1.toPostfixNotation() + " = " + xt1.evalTree() );
		System.out.println("xt2 in postfix notation is: " + xt2.toPostfixNotation() + " = " + xt2.evalTree() );
		System.out.println("xt3 in postfix notation is: " + xt3.toPostfixNotation() + " = " + xt3.evalTree() );


	}
}