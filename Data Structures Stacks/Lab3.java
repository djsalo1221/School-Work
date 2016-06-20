/*
*@author Daniel Salo
*@version 2.4.2015
*
*
*
*
*/

import java.util.Stack;
import java.io.*;
import java.util.*;


/*
	*
	*This class evaluates a fully parenthesized expression if it is correct. 
	*/
public class Lab3
{/*
	*
	*Main method
	*/

	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		Scanner inputFile;
		
		double result;
		String fileName;
		String line;
		char nextChar;
		int error;
		
		System.out.println("Enter input file name: ");
		fileName = sc.nextLine();
		
		try
		{
			inputFile = new Scanner(new File(fileName));
			
			while(inputFile.hasNext())
			{
				line = inputFile.nextLine();
				
	/*
	*Evaluate for corectedness
	*
	*/
				error = isBalanced(line);
				if(error == -1){
					System.out.print(line + " = " );
					process(line);
					line = "";
					
				}
				
				
	/*
	*if statement is not correct
	*/
				else  
				{
					System.out.println(line + " = Syntax Error");
					line = "";
				}
				line = "";
				
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found, try again");
		}
		
	}
	
	/*
	*Checks to see if string expression is valid.  
	*
	*/
	public static int isBalanced(String equation)
	{
		//left brackets initialized. 
		final char leftNormal  = '(';
		final char leftCurley   = '{';
		final char leftSquare  = '[';
	    
		//right brakets initialized.  
		final char rightNormal = ')';
		final char rightCurly  = '}';
		final char rightSquare = ']';
      
		//Stack of chars inititailized.  
		Stack<Character> store = new Stack<Character>();

		// return value, errorPlace, set to zero to begin invrements,  also the index to the string.  
		int errorPlace = 0;                              
		boolean matches = true;        
      
		for (int i = 0; matches && (i < equation.length( )); i++)
		{
			switch (equation.charAt(i))
			{
				case leftNormal:
				case leftCurley:
				case leftSquare: 
					store.push(equation.charAt(i));
					break;
				case rightNormal:
					if (store.isEmpty( ) || (store.pop( ) != leftNormal))
						matches = false;
					break;
				case rightCurly:
					if (store.isEmpty( ) || (store.pop( ) != leftCurley))
						matches = false;
					break;
				case rightSquare:
					if (store.isEmpty( ) || (store.pop( ) != leftSquare))
						matches = false;
					break;
			}
			//When loop stops, errorPlace takes on the value of i, the farthest increment.  
			errorPlace = i;
		}
	  
		///If the brackets match, return -1 for no error
		if(store.isEmpty( ) && matches)
			return -1;
		else //Else return the errorPlace.  
			return errorPlace;
	}
	
	static void process(String s) 
	{
		Stack <Integer> operands = new Stack<Integer>(); 
		Stack <Character> operators = new Stack<Character>();
		Stack <Character> openBrackets = new Stack<Character>(); 
	 
		
	
		String num =""; 
		
		int top=0;  int second=0; int result=0; 
		char operator = 0; 
		boolean isValid = true; 
		 
		for (int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i); 
			if (c==' ') continue;
			
			else if (isLeftBracket(c))
			{
				openBrackets.push(c);
			}
			else if (Character.isDigit(c))
			{
				
				/*
	*
	*Gets the next int so long as the next character in s is a digit
	*/
				for (int j = i; i<s.length(); j++)
				{
					c = s.charAt(j);
					if (Character.isDigit(c))
					{
						num +=c ;					
						i++;	
					}
					else 
					{
						i--;
						break;
					}
				}
					/*
	*
	*if there were digits encounterd, parse the string into an int and reset the string
	*/
				if (num != "")
				{
					int number = Integer.parseInt(num); 
					operands.push(number);
					num = "";  
				}
			}
			/*
	*
	*is the current char an operator. ?
	*/
			else if (isOperator(c))
			{
				operators.push(c);
			}
			/*
	*
	*
	*/
			else
			{
				if (!openBrackets.isEmpty())
				{
					char left = openBrackets.pop(); 
				    
					if ((c == ')' && left == '(') ||(c == ']' && left == '[')|| (c =='}' && left=='{') )
				    {
				    	if (operands.size()>=2)
						{
							top = operands.pop();
							second = operands.pop();	 
				    	}
				    	else
						{
							isValid = false;
				    		break;
				    	}
				    	if (!operators.isEmpty())
						{
				    		operator = operators.pop();
				    	}
				    	else
						{
							isValid = false;
				    		break;
				    	}
				    /*
	*
	*perform the operation on the top and second Integers and push the result to the top of the stack
	*/	
						if (operator == '+') 
							result = second+top;
						if  (operator == '-') 
							result = second - top;
						if (operator == '*') 
							result = second * top;
						if  (operator == '/') 
							result = second /  top;
				    	 
				    	operands.push(result);
						
				    }
				     
				    else
					{
						isValid = false;
				    	break;
				    }
				}
				else
				{ 
					isValid = false;
			    	break;
				}
			}
		}
		
		if (operands.size()==1 && isValid && openBrackets.isEmpty() && operators.isEmpty())
		{
			int res = operands.pop();
			
			String res1 = String.valueOf(res);
			
			System.out.print(res1 + "\n");
		}
	}

	
	

	public static boolean isLeftBracket(char c)
	{
		return (c == '(' || c == '[' || c == '{');
	}
	
	
	public static boolean isOperator(char c)
	{
		return (c == '*' || c == '/' || c == '-' || c == '+');
	}
	
}
	
