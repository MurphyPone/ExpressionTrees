
public class ExpTreeTester {
	private static boolean isOperand(String symbol) {	//is number
		try {
			Integer.parseInt(symbol);	//if this throws an error then it's not a num
			return true;
		} catch (NumberFormatException e) {
			//System.out.print(e + "\nSymbol is not a number");
			return false;
		}
	}
	
	private static boolean isOperator(String symbol) {	//is + or *
		return ( symbol.equals("+") || symbol.equals("*"));
	}
	
	public static void main(String[] args) {
		System.out.println(isOperand("5"));
		
		System.out.println(isOperator("+"));

	}
}
