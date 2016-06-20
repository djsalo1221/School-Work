/*
	Daniel Salo - Program 3
	
	1.)  Program 2 was used to demonstrate that the Travelling Salesman Problem was in the class NP 
			by stating it as a decision problem.NP completeness is based on decision problems.   Decision problems consists of a set of instances 
		that fall into two disjoint classes, positive (yes) and negative (no) instances. 	
		A decision problem L is in the class NP if you can write an algorithm that:
			* Constructs a guess in polynomial time
			* Checks that guess in polynomial time 
		Since constructing a guess and checks a guess, we asserted that the 
		Travelling Salesman problem wass in the class NP

		Program 3 was used to actually provide the optimal solution to the TSP based on an adjacency matrix, 
		whereas program 2 was used to demonstrate how solving an NP problem is carried out.
	2.)//O(n^n)
	3.)			a.) False
				b.) True
	

*/
import java.io.*;
import java.util.*;

public class TSPBacktracking
{
	static int [][] w;
	static int [] x;
	static int [] min_x;
	static int n;
	static int totalCost;
	static int minCost = 2147483647;
	static boolean used;
	
	static void tsp()  //O(1)
	{
		x[1] = 1;
		rtsp(2);
	}
	
	static void rtsp(int k)
	{
		while(k<n+1)				//O(n)
		{
			path_ok(k);
			if(x[k] == 0)
				return;
			if(k == n)					
			{
				totalCost = 0;
				for(int p=1; p<k;p++)					//O(n)
				{
					totalCost += w[x[p]][x[p+1]];
				}
				totalCost += w[x[n]][x[1]];
				if(totalCost<minCost)
				{
					minCost=totalCost;
					for(int p=1; p<=k;p++)			//O(n)
						min_x[p] = x[p];
				}
				
				used = true;
				return;
			}
			else
				rtsp(k+1);				//O(n)
		}
	}

	static void path_ok(int k)
	{
		while(k<n+1)
		{
			x[k] = (x[k]+1)%(n+1);	
			if(x[k] > 0)
			{
				if(w[x[k-1]][x[k]] >0)
				{
					int l = 1;
					while(l<k)
					{
						if(x[k] == x[l])
							break;
						l++;
					}
					if(k==l)
					{
						if(k<n)
							return;
						if(w[x[n]][x[1]] > 0 )
							return;	
							
					}
				}
			}
			else
				return;
		}
	}
	
	
	public static void main(String[] args) throws IOException
	{
		int[][] input = null;
        
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		if(n<3)
		{
			System.out.println("n must be <=3");
			return;
		}
		
		w = new int[n+1][n+1];
		x = new int[n+1];
		min_x= new int[n +1];
		used = false;
		
		for(int i = 1; i <= n; i++)
		{
			for(int j = 1; j <= n; j++)
			{
				if(i!=j && i<j)
				{
					System.out.print("Enter ("+i+","+j+"): ");
					w[i][j] = scanner.nextInt();
					w[j][i] = w[i][j];
				}
            }
			System.out.println();
		}	
		
		for(int i = 1; i <= n; i++)
		{
			for(int j = 1; j <= n; j++)
			{
				System.out.print(w[i][j]+ " ");
			
            }
			System.out.println();
		}
		
		x[1] = 1;
		tsp();
		
		System.out.println();
		if (used == true)
		{
			for(int i = 1; i <= n; i++)
				System.out.print(min_x[i] + ", ");
			System.out.println(x[1]);
			System.out.println("cost = " + minCost);
		}
		else
			System.out.println("no solution");
	}
} 

