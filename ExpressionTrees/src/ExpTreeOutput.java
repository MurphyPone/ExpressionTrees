/**
 * 	Class Description: This class handles the input/output of ExpressionTrees 
 *  @author MurphyP1
 *  @date 3/2/18
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ExpTreeOutput {
	/**
	 * The main method inside of which several ExpressionTrees are created from user input, evaluated, and then output to designated files 
	 *  @author MurphyP1
	 *  @date 3/1/18
	 *  @method main
	 *  @param args an array of Strings which are pulled from the command line
	 *  @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
	    Scanner keyboard = new Scanner(System.in);	//Create Scanner for keyboard IO
	    String fileName = "ExpressionTests.txt";	//"postFixExpressions.txt" 
	    Scanner input = createInput(args, fileName, keyboard );	//Create input from args, fileName or user input 
	    PrintWriter output = new PrintWriter(new FileWriter("myAnswers.txt"));	//Create output

	    //Create expressions from input Scanner
	    String[] expressions = readFile(input);
	    
	    //Create ExpressionTrees from expressions array
	    ExpressionTree[] trees = new ExpressionTree[expressions.length];	//Create an array of trees the same size as the #expressions
	    for(int i = 0; i < expressions.length; i++ ) {
	    		String[] exp = expressions[i].split(" "); //splits each expression into another array of the terms within
	    		trees[i] = new ExpressionTree(exp);
	    	
	    		//	Evaluate the expression using your evalTree* method and print the answer to the output file
	    		output.println("Expression["+i+"]");
	    		output.println("\tvalue: " + trees[i].evalTree());
	    		
	    		//	Print the prefix, infix, and postfix notations in that order, each on its own line
	    		output.println("\tprefix: " + trees[i].toPrefixNotation());
	    		output.println("\tinfix: " + trees[i].toInfixNotation());
	    		output.println("\tpostfix: " + trees[i].toPostfixNotation());
	    		
	    		// 	Print the result of calling your postFixEval method
	    		output.println("\tpostFixEval: " + trees[i].postfixEval(exp));

	    		//	Print two blank lines
	    		output.println("\n");
	    }
	    
	    // Finish:
	    input.close();
	    output.close();
	    keyboard.close();
	}
	
	//HELPER METHODS // 

	/**
	 *	Converts the input Scanner to an array of Strings 
	 *  @author MurphyP1
	 *  @date 3/1/18
	 *  @method readFile
	 *  @param in a Scanner which is converted to an Array of Strings 
	 *  @return an array of Strings which contains all the expressions in the input file 
	 */
	public static String[] readFile(Scanner in) {
		String[] expressions;
		ArrayList<String> raw = new ArrayList<String>();
		
		//input --> raw
		while (in.hasNextLine() ) {
			String line = in.nextLine().trim();
			if( !line.equals("") )
		      raw.add(line);
		}
		//initialize expressions 
		expressions = new String[raw.size()]; 
		
		//raw --> expressions
		int i = 0;
		for(String exp : raw)	//traverse raw & expressions simultaneously
			expressions[i++] = exp;
		
		return expressions;
	}
	
	/**
	 *	A helper method which returns a Scanner from either the command line arguments, the provided fileName, or (if all else fails) the user's unreliable input
	 *  @author MurphyP1
	 *  @date 3/1/18
	 *  @method createInput
	 *  @param args an array of Strings which might contain the fileName from which the Scanner can be created 
	 *  @param fileName a String which can be used to attempt to open a file to be converted to the Scanner 
	 *  @param kb the Scanner created to return user input 
	 *  @return a Scanner with expressions in postfix notation
	 */
	public static Scanner createInput(String[] args, String fileName, Scanner kb ) {
		Scanner input = null;
		
		if (args.length > 0 && openFile(args[0]) != null )	 // Attempt to open file based on cmdln args and valid result
 			input = openFile(args[0]);	//set input to a Scanner from fileName
 
	    	else if(openFile(fileName) != null )	//Attempt to open file based on default fileName and valid result
	    		input = openFile(fileName);
 
	    	else {	//Attempt to open file based on user input (God help us if this doesn't work)
	    		while ( ( openFile(fileName) == null ) ) {
	    			System.out.println("\nEnter input file name: ");
		    		fileName = kb.nextLine().trim(); //Input = user input
	    			input = openFile(fileName);
	    		} 
	    	}
		return input;
	}
	
	/**
	 *	A helper method which returns a Scanner from a supplied fileName
	 *  @author MurphyP1
	 *  @date 3/1/18
	 *  @method openFile
	 *  @param fname a String which can be used to try to open initialize a Scanner
	 *  @return a Scanner from the supplied fileName or null if the file name was invalid 
	 */
    public static Scanner openFile(String fname) {	
		File file = new File(fname);
		Scanner input = null;

		try {							//Check to see if the requested input file exists in the given directory
			input = new Scanner(file);	//If so, create a new Scanner to grab the data
		} catch (FileNotFoundException ex) {	//Else, print to console the reason why and output the reason why & part of program getting stuck
			//System.out.println("Unable to Open File: fname\n"); 
			return null;
		}
		return input;
	}
}
