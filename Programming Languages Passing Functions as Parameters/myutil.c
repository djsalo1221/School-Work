
#define _CRT_SECURE_NO_WARNINGS
#include "myutil.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef void* vptr;
typedef int (*compare)(vptr, vptr);  /*Compare is a function type that take parameters two void pointers,
									 compatible to any pointer type, and returns an int which is blah blah*/
typedef char string[21];

void swap(void *p1,void *p2,unsigned int uElemSize)
{
	char * pBuffer = (char*)malloc(uElemSize);
 
	memcpy(pBuffer,p1,uElemSize);
	memcpy(p1,p2,uElemSize);
	memcpy(p2,pBuffer,uElemSize);

    free(pBuffer);
}

/*void * array to be sorted,  number of elements in array, number of bytes in element,
and pointer to the comparison function, which returns an int <, =, or >0 
whether value pointed to by first element is < = > that of the second element...
quickSort shoudl compare generic swap function when it needs to swap to array 
elements.  */

static void sort(char *array, size_t size, int (*cmp)(void*,void*), int begin, int end) {  
   if (end > begin) {  
      void *pivot = array + begin;  
      int l = begin + size;  
      int r = end;  
      while(l < r) {  
         if (cmp(array+l,pivot) <= 0) {  
            l += size;  
         } else {  
            r -= size;  
            swap(array+l, array+r, size);  
         }  
      }  
      l -= size;  
      swap(array+begin, array+l, size);  
      sort(array, size, cmp, begin, l);  
      sort(array, size, cmp, r, end);  
   }  
}  
 
void quickSort(void *array, size_t nitems, size_t size, int (*cmp)(void*,void*)) {  
   sort(array, size, cmp, 0, (nitems-1)*size);  
}  