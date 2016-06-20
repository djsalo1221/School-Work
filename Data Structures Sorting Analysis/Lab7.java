//@author Daniel Salo
//
//  Lab7.java


/**
 *  This program compares various sorting algorithms.
 */

import java.util.*;

public class Lab6
{

	public static class SortStats
	{
		public SortStats()
		{
			comp = move = time = 0;
		}
		
		public void zero()
		{
			comp = move = time = 0;
		}
	
		public long comp;
		public long move;
		public long time;
	}
	
	/**
	 *   main method
	 */

	public static void main(String[] args)
	{
		final int MAX_SIZE = 50000;
		
		// row 0 is a sorted array
		// row 1 is a reversely sorted array
		// row 2 is a random array
		Integer[][] x = new Integer[3][MAX_SIZE];
		Integer[] a = new Integer[MAX_SIZE];
		int i;
		SortStats s = new SortStats();

		generateArrayElements(x);

		for( i = 0; i < 3; ++i )
		{
			// print heading;
			System.out.println();
			System.out.println( "                        Number of      Number of    Running Time  " );
			System.out.println( "Algorithms             Comparisons       Moves     (Milliseconds) " );
			System.out.println( "------------------------------------------------------------------" );

			for( int j = 0; j < MAX_SIZE; ++j )
				a[j] = x[i][j];
				
			selectionSort(a, s);
			printStats( "Selection Sort", s );
			s.zero();

			for( int j = 0; j < MAX_SIZE; ++j )
				a[j] = x[i][j];
				
			bubbleSort(a, s);
			printStats( "Bubble Sort", s );
			s.zero();
			
			for( int j = 0; j < MAX_SIZE; ++j )
				a[j] = x[i][j];
				
			bubbleSortWithFlag(a, s);
			printStats( "Bubble Sort w/ flag", s );
			s.zero();
			
			for( int j = 0; j < MAX_SIZE; ++j )
				a[j] = x[i][j];
				
			linearInsertionSort(a, s);
			printStats( "Linear Insertion Sort", s );
			s.zero();
			
			for( int j = 0; j < MAX_SIZE; ++j )
				a[j] = x[i][j];
				
			binaryInsertionSort(a, s);
			printStats( "Binary Insertion Sort", s );
			s.zero();
			
			for( int j = 0; j < MAX_SIZE; ++j )
				a[j] = x[i][j];
				
			quickSort(a, s);
			printStats( "Quicksort", s );
			s.zero();
			
			for( int j = 0; j < MAX_SIZE; ++j )
				a[j] = x[i][j];
				
			heapSort(a, s);
			printStats( "Heap Sort", s );
			s.zero();
			
			for( int j = 0; j < MAX_SIZE; ++j )
				a[j] = x[i][j];
				
			mergeSort(a, s);
			printStats( "Merge Sort", s );
			s.zero();
			
			for( int j = 0; j < MAX_SIZE; ++j )
				a[j] = x[i][j];
				
			ShellSort(a, s);
			printStats( "Shell Sort", s );
			s.zero();

		}

	}

	private static void printStats( String m, SortStats s )
	{
		System.out.printf( "%-24s%-16d%-12d%-13d\n", m, s.comp, s.move, s.time );
	}


	private static void generateArrayElements(Integer[][] x)
	{
		int n = x[0].length;
		int i, k;

		// first generate a sorted array that contains Integer
		// objects of values 1, 2, ..., n
		for( i = 0; i < n; ++i )
			x[0][i] = i + 1;

		// generate a reversely sorted array that contains
		// Integer objects of values n, n-1, ..., 2, 1
		for( i = 0; i < n; ++i )
			x[1][i] = n - i;

		// finally generate an array that contains Integer objects
		// of values 1 through n which are randomly distributed in
		// the array
		// But, how????  (See GenericQuickSort.java example.)
		//...
		for( i = 0; i < n; ++i )
			x[2][i] = i + 1;
			
		// Fisher–Yates shuffle 
		Random r = new Random();
		for ( i = x[2].length - 1; i > 0; --i )
		{
			int index = r.nextInt( i + 1 );
			k = x[2][index];
			x[2][index] = x[2][i];
			x[2][i] = k;
		}
	}





	private static <T extends Comparable<? super T>> void selectionSort(T[] a, SortStats s)
	{
		int minIndex;
		T min;
		T temp;
		int n = a.length;
		
		s.time = System.currentTimeMillis();

		for(int i = 0; i < n-1; ++i)
		{
			minIndex = i;
			min = a[i];

			for (int j = i+1; j < n; ++j)
			{
				++s.comp;
				if( a[j].compareTo( min ) < 0 )
				{
					minIndex = j;
					min = a[j];
				}
			}

			//  swap
			s.move += 3;
			temp = a[minIndex];
			a[minIndex] = a[i];
			a[i] = temp;
		}
		
		s.time = System.currentTimeMillis() - s.time;
	}




	private static <T extends Comparable<? super T>> void bubbleSort(T[] a, SortStats s)
	{
		T temp;
		int n = a.length;
		
		s.time = System.currentTimeMillis();

		for(int i = 0; i < n-1; ++i)
		{
			for(int j = 0; j < n-i-1; ++j)
			{
				++s.comp;
				if(a[j].compareTo( a[j+1] ) > 0)
				{
					//  swap a[j] and a[j+1]
					s.move += 3;
					temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
			}
		}
		
		s.time = System.currentTimeMillis() - s.time;
	}





	private static <T extends Comparable<? super T>> void bubbleSortWithFlag(T[] a, SortStats s)
	{
		int k, changeAt;
		boolean hasChanged = true;
		T temp;
		int n = a.length;

		k = n - 1;      // position of the last exchange
		changeAt = n - 1;
		
		s.time = System.currentTimeMillis();

		while ((k > 0) && hasChanged)
		{
			hasChanged = false;

			for (int j = 0; j < k; ++j)
			{
				++s.comp;
				if (a[j].compareTo(a[j+1]) > 0)
				{
					//  swap a[j] and a[j+1]
					s.move += 3;
					temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;

					hasChanged = true;
					changeAt = j;
				}
			}

			k = changeAt;
		}
		
		s.time = System.currentTimeMillis() - s.time;
	}





	private static <T extends Comparable<? super T>> void linearInsertionSort(T[] a, SortStats s)
	{
		T newItem;
		int i, j;
		int n = a.length;
		
		s.time = System.currentTimeMillis();

		for(i = 1; i < n; ++i)
		{
			++s.move;
			newItem = a[i];
			j = i - 1;
			
			++s.comp;
			while ((j >= 0) && (newItem.compareTo( a[j] ) < 0))
			{
				++s.move;
				a[j + 1] = a[j];    // shift a[j] one position to right
				--j;
				
				++s.comp;
			}

			++s.move;
			a[j + 1] = newItem;

		}
		
		s.time = System.currentTimeMillis() - s.time;
	}



	private static <T extends Comparable<? super T>>
		void binaryInsertionSort(T[] a, SortStats s)
	{
		T newItem;
		int i, j, left, right, middle;
		int n = a.length;
		
		s.time = System.currentTimeMillis();

		for (i = 1; i < n; ++i)
		{
			++s.move;
			newItem = a[i];
			left = 0;
			right = i - 1;

			while (left <= right)
			{
				middle = (left + right) / 2;
				
				++s.comp;
				if (newItem.compareTo( a[middle] ) < 0 )
					right = middle - 1;
				else
					left = middle + 1;
			}

			for (j = i-1; j >= left; --j)
			{
				++s.move;
				a[j + 1] = a[j];    // shift a[j] one position to right
			}

			++s.move;
			a[j + 1] = newItem;
		}
		
		s.time = System.currentTimeMillis() - s.time;
	}



	/**
	  *  This is Generic Quick Sort
	  */

	public static <T extends Comparable<? super T>>
		void quickSort(T[] a, SortStats s)
	{
		int n = a.length;

		s.time = System.currentTimeMillis();
		qsort(a, 0, n-1, s);
		s.time = System.currentTimeMillis() - s.time;
	}


	public static <T extends Comparable<? super T>>
		  void qsort(T[] a, int left, int right, SortStats s)
	{
		int i, j;
		T	pivot, temp;

		i = left;
		j = right;
		pivot = a[(i + j) / 2];

		do
		{
			++s.comp;
			while (a[i].compareTo(pivot) < 0)
			{
				++i;
				++s.comp;
			}

			++s.comp;
			while (a[j].compareTo(pivot) > 0)
			{
				--j;
				++s.comp;
			}

			if (i < j)
			{
				s.move += 3;
				temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}

			if (i <= j)
			{
				++i;
				--j;
			}

		} while (i <= j);


		if(left < j)
			qsort(a, left, j, s);

		if(i < right)
			qsort(a, i, right, s);
	}



	/**
	  *  This is Generic Heap Sort
	  */

	private static <T extends Comparable<? super T>>
		  void heapSort(T[] a, SortStats s)
	{
		int left, right;
		T temp;
		int n = a.length;

		left = n/2 + 1;
		right = n - 1;
		
		s.time = System.currentTimeMillis();

		// heap construction phase

		while (left > 0)
		{
			--left;
			shiftDown(a, left, right, s);
		}

		//  swap and reheap phase
		while  (right > 0)
		{
			s.move += 3;
			temp = a[0];
			a[0] = a[right];
			a[right] = temp;
			
			--right;
			shiftDown(a, left, right, s);
		}
		
		s.time = System.currentTimeMillis() - s.time;
	}



	private static <T extends Comparable<? super T>>
		  void shiftDown(T[] a, int left, int right, SortStats s)
	{
		int i, j;
		T x;

		i = left;
		j = 2 * i + 1;
		
		++s.move;
		x = a[i];

		while(j <= right)
		{
			++s.comp;
			if ((j < right) && (a[j].compareTo(a[j+1]) < 0)) // i has a right child and it is bigger
				++j;

			++s.comp;
			if (x.compareTo(a[j]) >= 0) //  already shift down to proper position
				break;

			++s.move;
			a[i] = a[j];
			i = j;
			j = 2 * i + 1;
		}

		++s.move;
		a[i] = x;
	}



	 /**
	  * This is Generic Merge Sort
	  */

	public static <T extends Comparable<? super T>>
		void mergeSort(T[] a, SortStats s)
	{
		  int n = a.length;

		  s.time = System.currentTimeMillis();
		  mSort(a, 0, n-1, s);
		  s.time = System.currentTimeMillis() - s.time;
	}


	 public static <T extends Comparable<? super T>>
		void mSort(T[] a, int left, int right, SortStats s)
	{
		int middle;

		if(left < right) // the array has 2 or elements
		{
			middle = (left + right) / 2;
			mSort(a, left, middle, s);
			mSort(a, middle + 1, right, s);
			merge(a, left, middle, right, s);
		}
	}



	 public static <T extends Comparable<? super T>>
		  void merge(T[] a, int left, int middle, int right, SortStats s)
	 {
		int n = right - left + 1;
		int i, p, q;
		T[] tempArray = (T[]) new Comparable<?>[n];
		
		// Recall that Java does not allow us
		// to creat an arry of generic type
		// directly.  

		i = 0;
		p = left;
		q = middle + 1;

		while ((p <= middle) && (q <= right))
		{
			++s.comp;
			++s.move;
			if (a[p].compareTo(a[q]) < 0)
			{
				tempArray[i] = a[p];
				++p;
			}
			else
			{
				tempArray [i] = a[q];
				++q;
			}

			++i;
		}

		// finish off an non-empty subarray if necessary

		while (p <= middle)
		{
			++s.move;
			tempArray[i] = a[p];
			++p;
			++i;
		}

		while (q <= right)
		{
			++s.move;
			tempArray[i] = a[q];
			++q;
			++i;
		}

		// copy the result in tempArray back to
		// the original array

		s.move += n;
		System.arraycopy(tempArray, 0, a, left, n);
	}




	 /**
	  *  This is Generic Shell Sort
	  */

	private static <T extends Comparable<? super T>>
		  void ShellSort(T[] a, SortStats s)
	{
		T newItem;
		int i, j, k, pass, start;
		int[] increments = new int[4];
		int n = a.length;


		increments[0] = 11;
		increments[1] = 7;
		increments[2] = 3;
		increments[3] = 1;
		
		s.time = System.currentTimeMillis();

		for (pass = 0; pass <= 3; ++pass)
		{
			k = increments[pass];
			for (start = 0; start < k; ++start)
			{
				i = start + k;
				while (i < n)
				{
					++s.move;
					newItem = a[i];
					j = i - k;
					++s.comp;
					while ((j >= 0) && (newItem.compareTo(a[j]) < 0))
					{
						++s.move;
						a[j + k] = a[j];
						j -= k;
						
						++s.comp;
					}

					++s.move;
					a[j + k] = newItem;
					i += k;
				}
			}
		}
		
		s.time = System.currentTimeMillis() - s.time;
	}

}