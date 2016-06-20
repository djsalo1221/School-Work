/*Daniel Salo
Programming Assignment 5

This program is a CGI script that takes a formatted string of characters that is meant 
to origionate from an HTML form.  The program manually parses the formatted data contents of
the string (that is stored in a .txt file), displays the contents in human-readable form,
and gives the option to view the specific data contents from a specific field.  
*/


#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include "cgilib.h" /*This is the header file that contains the defininitions for the 
linked list struct to be used and its function prototypes.  It is in double quotes because
it is contained in the user directory */

int count = 0;

void main()
{
	/*calls function to ask for input filename, and to return data as string that 
	is enclosed in the input file */
	string get_input(void);
	
	/*field[] is for storing the file name entered*/
	char field[30];
	
	char *input_string;

	/*we dynamically allocate storage in heap of the number of characters
	in the input string consisting plus one for null terminator in blocks
	of 8 bits each.  */
	input_string = (char *)calloc(count + 1, sizeof(char));
	input_string = get_input();
	
	/*reference to the real head is returned form the tokenize function
	which contains references to a linked list of the various field/data
	combinations*/
	node *head = tokenize(input_string);
	/*prints the contents of the file organized into field/data columb*/
	print_table(head);
	
	
	/*user input loop*/
	int status = 1;
	while (status)
	{
		printf("\n\nEnter field name, or 0 to abort =>");
		status = scanf("%s", &field);
		
		/*terminate if input is single 0 char*/
		if (field[0]=='0')
			return 0;
		
		/*method to search for specific field value in
		the linked list created by tokenize()*/
		string val = cgi_val(head, field);
		
		/*to handle invlid entries.  */
		if (val == NULL)
			printf("\nInvalid file name");
		else
			printf("\n%s: %s", field, val);
	}

}



string get_input(void)
{
	int  c, i;
	char   file_name[21];
	char *input;
	FILE   *text;

	printf("\n\nEnter file name =>  ");
	
	scanf("%s", file_name);
	
	text = fopen(file_name, "r");  

	if (!text)
	{
		printf("\n\nError: The file \"%s\" does not exist.\n",
			file_name);
		exit(1);       
	}


	while ((c = getc(text)) != EOF)   
	{
		count++;
	}
	fclose(text);

	input = (char *)calloc(count + 1, sizeof(char));
	text = fopen(file_name, "r"); 

	if (!text)
	{
		printf("\n\nError: The file \"%s\" does not exist.\n",
			file_name);
		exit(1);     
	}

	i = 0;
	while ((c = getc(text)) != EOF)
	{
		
		input[i] = c;  
		i++;
	}
	input[i] = '\0';

	fclose(text);
	return input;
}
