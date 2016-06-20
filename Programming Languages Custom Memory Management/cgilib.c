#define _CRT_SECURE_NO_WARNINGS
#include "cgilib.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int stringLength;


/*This function is used to parse an input string, and returns 
a linked list of tokens (oneFields). The linked list is 
maintained by the alphabetical order of the field name, and 
the data stored in each node is a record representing one 
field.This function will extract a word from the input string,
starting at the given position specified by pos. If such a
word exists, the function will dynamically create an array
and put that word in the array, and return the pointer to
the word.*/
link tokenize(string input_string)
{
	
	int pos = 0;
	
	
	/*call next_word function with input string parameter and address*/
	string next_word(string input_string, int *pos);
	
	/*Initialize the newnode for new words, and current head and a temp node for sorting*/
	node *newnode, *head, *temp;
	head = NULL;


	while (input_string[pos] != '\0')/*iterates until end of file*/
	{
		/*creates a single new node with malloc in heap the size of element oneField type*/
		newnode = (struct element*)malloc(sizeof(struct element));

		/*determine next word for field at the current pointer*/
		string field = next_word(input_string, &pos);
		
		
		if (input_string[pos] == '=')/*in order to determine the space between 
									 the field name and data */
		{
			newnode->data.fieldName = (char *)calloc(stringLength + 1, sizeof(char));
			/*allocate current position for field name 
			*/
			strcpy(newnode->data.fieldName, field);/*allocate current position for data 
			*/
			if (input_string[pos] != '\0')/*keep incrementing pos unless \0*/
				pos++;
		}

		/*determine next word for value at the current pointer*/
		string val = next_word(input_string, &pos);


		if (input_string[pos] == '&' || input_string[pos] == '\0')
		{
			/*create new node for value field and make the next pointer NULL*/
			newnode->data.value = (char *)calloc(stringLength + 1, sizeof(char));
			strcpy(newnode->data.value, val);
			newnode->next = NULL;
			
			
			if (head != NULL && strcmp(newnode->data.fieldName, head->data.fieldName) < 0)
			{
				temp = head;
				while (temp->next != NULL && strcmp(newnode->data.fieldName, temp->data.fieldName)<0)
					temp = temp->next;
				newnode->next = temp->next;
				temp->next = newnode;
			}
			else
			{
				newnode->next = head;
				head = newnode;
			}

		if (input_string[pos] != '\0') pos++;
		}
	}
	return head; /*return the linked list/head*/
}

string next_word(string input_string, int *pos)
{

	int bytesSoFar;
	bytesSoFar = *pos;
	char *word;
	
	stringLength = 0;

	/*=,&, and /0 represent control characters in the CGI script.  */
	while (input_string[bytesSoFar] != '=' && 
		input_string[bytesSoFar] != '&' && input_string[bytesSoFar] != '\0')
	{
		/*iterations will be the number of regular characters in the string, 
		and j ill be the number of symbols represented in the string, since
		+,/, and & are represented by three characters, (%, 2 and B,5 or F.)*/
		if (input_string[bytesSoFar] == '%'&& 
			input_string[bytesSoFar + 1] == '2')
		{
			bytesSoFar = bytesSoFar + 2; /*compensates for the three characters, 1+2=3.*/
		}
		
		stringLength++; /*End of loop, total string length and char bytes so far are incremented by one block*/
		bytesSoFar++;
	}
	
	/*storage space allocated for this particular word in the fashion of 1 extra character for /0*/
	word = (char *)calloc(stringLength + 1, sizeof(char));
	/*bytesSoFar reset to origional pointer number*/
	bytesSoFar = *pos;
	
	/*i represents the placeholder for word[]*/
	int i = 0;

	/*This is a mouthfull, 
	checks to see if the char is a 'control character', such as &, =, or null terminatior.  */
	while (input_string[bytesSoFar] != '&' && input_string[bytesSoFar] != '=' && input_string[bytesSoFar] != '\0')
	{
		/*then checks to see if the 'control character', &, =, or + is actually a data value.  */
		if (input_string[bytesSoFar] == '%' && input_string[bytesSoFar + 1] == '2')
		{
			/*To handle the two +s in a row*/
			if (input_string[bytesSoFar + 2] == 'B' && input_string[bytesSoFar + 3] == '%' &&input_string[bytesSoFar + 4] == '2' && input_string[bytesSoFar + 5] == '2')
			{
				word[i++] = '+';
				bytesSoFar += 3;
				i++;
				word[i++] = '+';
				bytesSoFar += 3;
			}
			else if (input_string[bytesSoFar + 2] == 'B')/*Else if for if it is not ++, but +*/
			{
				word[i++] = '+';
				bytesSoFar += 3;
			}
			if (input_string[bytesSoFar + 2] == '5')/*for %*/
			{
				word[i++] = '%';
				bytesSoFar += 3;
			}
			
			if (input_string[bytesSoFar + 2] == 'F')/*For /*/
			{
				word[i++] = '/';
				bytesSoFar += 3;
			}
			continue;
		}
		if (input_string[bytesSoFar] == '+')/*If the + is actually a controcharacter for representing a space*/
		{
			word[i++] = ' ';
			bytesSoFar++;
			continue;
		}

		word[i++] = input_string[bytesSoFar++];/*enter the particular character for the return string
											   from what character was determined, from input string
											   with bytesSoFar counter.  */
	}
	word[i++] = '\0';/*Null terminate the end*/
	*pos = bytesSoFar;/*reset the pos number again*/
	return word;/*finally, return word[]*/

}


/*This function will step through the linked list pointed to 
by head, find the node containing the given field, and 
return its value. The function returns a dynamic 
string. */
string cgi_val(link head, string field)
{
	char *str;

	node *temp; 
	temp = head;
	

	while (temp != NULL)
	{
		// if (strcmp(field, temp->data.fieldName) > 0)
			//return NULL;
		if (strcmp(field, temp->data.fieldName) == 0) /* uses strlen to determine 
the length of its value,*/
		{
			int len = strlen(temp->data.value);
			/*use calloc to dynamically
				create a string of size length + 1*/
			str = (char *)calloc(len + 1, sizeof(char));
			/*returns the dynamic string, str, pointed to by *str.*/
			strcpy(str, temp->data.value);
			return str;
		}
		
		temp = temp->next;
	}
	return NULL; /*If field name is not found, returns the NULL pointer.*/
}

/*iterates through the linked list printeing out its fields and values.  */
void print_table(link head)
{
	int spaces;
	
	node *temp;
	temp = head;
	
	printf("\n\n\n\tField Name\t\tValue\n");
	printf("------------------------------------------------------------");
	 

	while (temp != NULL)
	{
		printf("\n\t%s", temp->data.fieldName);/*Accesses the fields of the oneField data,
											   strn will return length of data.fieldname*/
		spaces = strlen(temp->data.fieldName);
		
		while (spaces < 24)/*formated with whitespace counter loop*/
		{
			spaces++;
			printf(" ");
		}
		spaces = 0;/*counter loop value reset*/
		
		printf("%s", temp->data.value);
		temp = temp->next;/*prints field value after spaces aare printed.  */
	}
}
