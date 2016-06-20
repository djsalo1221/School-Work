/*Daniel Salo
Programming Assignment 6

This function implements a utility library that contains two generic functions in C:
A generic swap function and a generic quick sort function.
The program reads input data from a file, and creates student objects.
The program then creates three identical arrays of these newly created student objects
and perfoms three sorting operations using a generic quick sort function
and a generic swap function, and prints out the results.  
*/

#define _CRT_SECURE_NO_WARNINGS
#define MAXSIZE 25

typedef void* vptr;
typedef int (*compare)(vptr, vptr);  /*Compare is a function type that take parameters two void pointers,
									 compatible to any pointer type, and returns an int which is blah blah*/
typedef char string[21];


#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "myutil.h"/*include the header definition for the studentObject, in local directory*/



void main()
{
	int cmp1(vptr,vptr);/*function prototypes*/
	int cmp2(vptr,vptr);
	int cmp3(vptr,vptr);

	studentObject studentList1[MAXSIZE];
	studentObject studentList2[MAXSIZE];/*instantiate 3 lists of studentObjects of MAXSIZE length*/
	studentObject studentList3[MAXSIZE];
	
	int dataLength;
	dataLength = getData(studentList1);/*dataLength is loop counter for printList function*/
	
	deepCopy(studentList2, studentList1);/*custom deep copy method for the arrays of studentObjects*/
	deepCopy(studentList3, studentList2);
	
	printf("Origional List:\n");
	printList(studentList3, dataLength);/*prints the oritional lists*/

	/*Quick sort and print the list by grade*/
	printf("\nSorted By Grade:\n");
	quickSort(studentList1, MAXSIZE, sizeof(studentObject), cmp1);
	printList(studentList1, dataLength);

	/*Quick sort and print the list by Name*/
	printf("\nSorted by Grade\n");
	quickSort(studentList2, MAXSIZE, sizeof(studentObject), cmp2);
	printList(studentList2, dataLength);

	/*Quick sort and print the list by major, then grade*/
	printf("\nSorted By major, then grade:\n");
	quickSort(studentList3, MAXSIZE, sizeof(studentObject), cmp3);
	printList(studentList3, dataLength);

	getchar();
}


/*Quick sort and print the list by grade*/
int cmp1(void *p, void *q){
	studentObject *x = (studentObject *) p;
	studentObject *y = (studentObject *) q;

	return(y->grade1 - x->grade1);
	
}


/*Quick sort and print the list by name*/
int cmp2(void *p, void *q){
	studentObject *x = (studentObject *) p;
	studentObject *y = (studentObject *) q;

	return strcmp(x->studentName,y->studentName);
}


/*Quick sort and print the list by major, then grade*/
int cmp3(void *p, void *q){
	int i;
	studentObject *x = (studentObject *) p;
	studentObject *y = (studentObject *) q;
	
	i = strcmp(x->studentMajor,y->studentMajor);
	if (i==0)
		i = cmp1(x,y);

	return i;
}



/*This method reads the data from the input file*/
int getData(studentObject element[])
{
	int n = 0;
	char name[21], junk1[6], major[11], junk2[6], grade[4];
	int grade1;
	FILE *data;
	char c, file_name[20];

	printf("\nEnter the file name --> ");
	gets(file_name);
	data = fopen(file_name, "r");

	while (!data)
	{
		printf("\nError: The file \"%s\" does not exist.\n", file_name);
		printf("Try again =>  ");
		scanf("%s", file_name);
		data = fopen(file_name, "r");
	}

	while (fgets(name, 21, data) != NULL)
	{
		strcpy(element[n].studentName, name);
		fgets(junk1, 5, data);
		fgets(element[n].studentMajor, 11, data);
		fgets(junk2, 5, data);
		//fscanf(data, "%s", &element[n].studentGrade);
		fscanf(data, "%d", &element[n].grade1);
		do
			fscanf(data, "%c", &c);
		while (c != '\n');
		++n;
	}

	return n;
}


/*This method deep copys the contents of the studentObject array*/
int deepCopy(studentObject b[], studentObject a[])
{
	int i = 0;
	int n = 0;
	for (i = 0; i < MAXSIZE; ++i)
	{
		for (n = 0; n <= strlen(b[i].studentName); ++n)
			b[i].studentName[n] = a[i].studentName[n];
		for (n = 0; n <= strlen(b[i].studentMajor); ++n)
			b[i].studentName[n] = a[i].studentName[n];
		/*for (n = 0; n <= strlen(b[i].studentGrade); ++n)
			b[i].studentName[n] = a[i].studentName[n];*/
		b[i].grade1 = a[i].grade1;
	}
	return i;
}


/*This method prints the contents of the studentObject array in an orderly fashion*/
int printList(studentObject a[], int n)
{
	int i;
	for (i = 0; i < n; ++i)
	{
		printf("%20s %4s   %d\n", a[i].studentName, a[i].studentMajor, a[i].grade1);
	}
		
	return i;
}