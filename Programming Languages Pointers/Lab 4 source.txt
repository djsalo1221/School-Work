/*Daniel Salo
CS351 ASSIGNMENT 4

This program takes input from user for a desired matrix size as well as individual elements,
and is able to perform addition, subraction, and multiplication operations on matrixes of correct size.  */

#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h> /*Allows to creat contiguious block of memory in dynamic storage area*/

/*Type definition for the matrix struct.  ocntains a pointer to the pointers of the individual matrixes,
along with the number of rows and columbs for the matrixes*/
typedef struct
{
	int **elements;
	int rows;
	int columns;
} matrix;


/*read function.  pointer to matrix struct and char for matrix number as parameters.  */
void read_matrix(matrix *a, char matrixLetter)
{
	int rows, columbs;
	int i, j;  /*loop counters*/


	printf("Enter rows in Matrix %c: ", matrixLetter);
	scanf("%d", &rows);

	printf("Enter columbs in Matrix %c: ", matrixLetter);
	scanf("%d", &columbs);
	printf("\n");

	/*set rows and columbs*/
	(*a).rows = rows;
	(*a).columns = columbs;

	/*dynamically allocate storage space for the rows of the matrix struct.  */
	(*a).elements = (int **)calloc(rows, sizeof(int *));

	/*dynamically allocate storage space for the columbs of the matrix struct.  */
	for (i = 0; i < rows; i++)
		(*a).elements[i] = (int *)calloc(columbs, sizeof(int));

	/*embeded for loop for entering individual elements.*/
	for (i = 0; i < rows; i++)
	{
		for (j = 0; j < columbs; j++)
		{
			printf("\tEnter Row %d, Columb %d => ", i + 1, j + 1);
			scanf("%d", &(*a).elements[i][j]);
		}
		printf("\n");
		fflush(stdin);
	}
}

/*print function.  pointer to matrix struct and char for matrix number as parameters.  */
void print(matrix a, char matrixLetter)
{
	int i, j, last;

	printf("\tMatrix %c\n", matrixLetter);
	for (i = 0; i < a.rows; i++)
	{
		printf("\t[");
		for (j = 0; j < a.columns - 1; j++)
		{
			printf("%d ", a.elements[i][j]);
			last = j;  /*used for setting last element to print the ending bracket in row.   */
		}

		last++;
		printf("%d]", a.elements[i][last]);
		printf("\n");
	}
	printf("\n\n");
}

/*add function.  Two matrixes of equal size needed for operation  */
matrix add(matrix a, matrix b)
{
	matrix c;
	int i, j;
	/*ensure matrix dimentions are equal  */
	if (a.rows == b.rows && a.columns == b.columns)
	{
		/*set new matrix struct, C to dimensions of matrix A.  Either struct can be used since they are confirmed equal.   */
		c.rows = a.rows;
		c.columns = a.columns;

		/* dynamically allocate storage apsce for matrix C*/
		c.elements = (int **)calloc(c.rows, sizeof(int *));
		/* dynamically allocate columbs for c*/
		for (i = 0; i < c.rows; i++)
			c.elements[i] = (int *)calloc(c.columns, sizeof(int));

		/* matrix addition operation*/
		for (i = 0; i < c.rows; i++)
			for (j = 0; j < c.columns; j++)
				c.elements[i][j] = a.elements[i][j] + b.elements[i][j];

		return c;
	}
	else /* if dimensions are unequal, will return NULL and main method will output an error.  */
	{
		c.elements = NULL;
		return c;
	}
}


/* subrract matrix B from matrix A*/
matrix subtract(matrix a, matrix b)
{
	matrix c;
	int i, j;

	if (a.rows == b.rows && a.columns == b.columns)
	{
		c.rows = a.rows;
		c.columns = a.columns;

		/* dynamically allocate storage space.  */
		c.elements = (int **)calloc(c.rows, sizeof(int *));
		for (i = 0; i < c.rows; i++)
			c.elements[i] = (int *)calloc(c.columns, sizeof(int));

		/*matrix subtraction operation.  Subtracts matrix b from matrix a.   */
		for (i = 0; i < c.rows; i++)
			for (j = 0; j < c.columns; j++)
				c.elements[i][j] = a.elements[i][j] - b.elements[i][j];
	}


	else
	{
		c.elements = NULL; /* retirns NULL to main method.  an error will be displayed if dimensions are unequal.  */
		return c;
	}

	return c;
}

/* matrix multiplication operation*/
matrix multiply(matrix a, matrix b)
{
	matrix c;
	int i, j, k;
	int total;

	if (a.columns == b.rows)/*dimension of columb A must equal the dimension of columb B.   */
	{
		c.rows = a.rows;
		c.columns = b.columns;

		/* allocate storage space in dynamic storage.  */
		c.elements = (int **)calloc(c.rows, sizeof(int *));
		for (i = 0; i < c.rows; i++)
			c.elements[i] = (int *)calloc(c.columns, sizeof(int));

		/*nasty for loop matrix multiplication operation.   */
		for (i = 0; i < a.rows; i++)
		{
			for (j = 0; j < b.columns; j++)
			{
				total = 0; /* initialize total.  */
				for (k = 0; k < a.columns; k++)
				{
					/* add multiplication of */
					total += (a.elements[i][k] * b.elements[k][j]);
				}
				c.elements[i][j] = total;
			}
		}

		return c;
	}


	else /* if matrix A columb is not equal to matrix B row will return to main methos NULL where an error will be displayed   */
	{
		c.elements = NULL;
		return c;
	}

}

/* Main Method                             */
int main()
{
	char operation;
	matrix matrixA, matrixB, matrixC;


	int status = 1; /* status counter set for loop control */
	while (status)
	{
		read_matrix(&matrixA, 'A'); /* READ AND PRINT MATRIX A*/
		print(matrixA, 'A');

		read_matrix(&matrixB, 'B');/* READ AND PRINT MATRIX B*/
		print(matrixB, 'B');



		int notValid = 1;/* not valid loop.  Will continue as long as invalid input is entered.  */
		while (notValid)
		{
			printf(" Choose Operation:\n\t- for subtraction\n\t+ for addition\n\t* for multiplication\n\tx for termination\n\n");
			printf("=====> ");          /* OPERAITONS MENU*/
			scanf("%c", &operation);  /* SCAN IN OPERATION*/

			switch (operation)  /* OPERATION SWITCH STATEMENT FOR DETERMINING OPERATION*/
			{
			case 'x':
				return 0;
				break;

			case '+':
				matrixC = add(matrixA, matrixB);
				notValid = 0;
				break;

			case '*':
				matrixC = multiply(matrixA, matrixB);
				notValid = 0;
				break;

			case '-':
				matrixC = subtract(matrixA, matrixB);
				notValid = 0;
				break;

			default:
				printf("Invalid Operation.");
				break;
			}
		}

		if (matrixC.elements != NULL) /* IF input is valid, print the output for matrix c.  */
			print(matrixC, 'C');
		else/* IF NULL (from incorrect matrix dimesi*/
		{
			printf("Invalid matrix sizes.  For addition and subtraction, dimensions must be equal.\n");
			printf("For multiplication, columbs in matrix A must equal rows in matrix B.\n\n");
		}
	}
}