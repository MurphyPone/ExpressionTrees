import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//Use for assignment
public class ExpTreeOutput {
	public static void main(String[] args) throws IOException {
	    Scanner keyboard = new Scanner(System.in);	//import kb
	    String fileName = "postFixExpressions.txt";	//postFixExpressions.txt
	    Scanner input = createInput(args, fileName, keyboard );
	    PrintWriter output = new PrintWriter(new FileWriter("myAnswers.txt"));	//Create output

	    //Create expressions
	    String[] expressions = readFile(input);
	    //System.out.println(expressions[2]);	///REMOVE ME 
	    
	    //create trees from expressions
	    ExpressionTree[] trees = new ExpressionTree[expressions.length];	//Create an array of trees the same size as the #expressions
	    for(int i = 0; i < expressions.length; i++ ) {
	    		String[] exp = expressions[i].split(" "); //splits each expression into another array of the terms
	    		trees[i] = new ExpressionTree(exp);
	    	
	    		//	Evaluate the expression using your evalExp method and print the answer to the output file
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
	
	
	//helper methods// 

	
	//Converts input Scanner to String[]
	public static String[] readFile(Scanner in) {
		String[] expressions;
		ArrayList<String> raw = new ArrayList<String>();
		
		//input --> raw
		while (in.hasNextLine() ) 
		      raw.add(in.nextLine() );
		
		//initialize expressions 
		expressions = new String[raw.size()]; 
		
		//raw --> expressions
		int i = 0;
		for(String exp : raw)	//traverse raw & expressions simultaneously
			expressions[i++] = exp;
		
		return expressions;
	}
	
	
	public static Scanner createInput(String[] args, String fileName, Scanner kb ) {
		Scanner input = null;
		
		if (args.length > 0)	// Attempt to open file based on cmdln args
 			input = openFile(args[0]);	//set input to a Scanner from filename
 
	    	else if(openFile(fileName) != null )
	    		input = openFile(fileName);
 
	    	else {
	    		System.out.println("\nEnter input file name: ");
	    		fileName = kb.nextLine().trim(); //Input = user input
	    		input = openFile(fileName);
	    	}
		return input;
	}
	
    public static Scanner openFile(String fname) {	
		File file = new File(fname);
		Scanner input = null;

		try {							//Check to see if the requested input file exists in the given directory
			input = new Scanner(file);	//If so, create a new Scanner to grab the data
		} catch (FileNotFoundException ex) {	//Else, print to console the reason why and output the reason why & part of program getting stuck
			System.out.println("Unable to Open File: fname\n"); //TODO REMOVE ME 
			return null;
		}
		return input;
	}
}
