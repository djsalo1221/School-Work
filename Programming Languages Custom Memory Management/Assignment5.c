/*                         Assignment5.c                         */

/*  This program will read from a text file, one character at a  */
/*  time, display each character, until the end of file is       */
/*  reached.                                                     */



#include <stdio.h>
#include <stdlib.h>    /*  It contains the function prototype of
                           "exit".  */



void main()
{

     int  c;
     char   file_name[21];
     FILE   *text;


     printf("\n\nEnter the file name -->  ");
     scanf("%s",file_name);
     text = fopen(file_name, "r");  /*  open the file for reading  */

     if (!text)
     {
          printf("\n\nError: The file \"%s\" does not exist.\n",
                                       file_name);
          exit(1);        /*  terminate the program with an error  */
     }


     while ((c = getc(text)) != EOF)   /* "getc" is used to read a
                                          character from a file.   */

          /*  Note that c was declared to be an int rather than a
              char.  For details, see C Example 6.  */

          putchar(c);

     fclose(text);
}
