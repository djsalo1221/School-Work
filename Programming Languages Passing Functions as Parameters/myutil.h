
typedef struct
{
	char studentName[21];
	char studentMajor[11];
	char studentGrade[4];
	int grade1;
}studentObject;

/*void * data to be swapped, unsigned  is space of each data to be swapped*/
void swap(void *, void *, unsigned); 

/*void * array to be sorted,  number of elements in array, number of bytes in element,
and pointer to the comparison function, which returns an int <, =, or >0 
whether value pointed to by first element is < = > that of the second element...
quickSort shoudl compare generic swap function when it needs to swap to array 
elements.  */
void quickSort(void *, unsigned, unsigned, int(*) (void *, void *));

