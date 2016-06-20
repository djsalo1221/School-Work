/**
 *  @author DANIEL SALO
 *
 *  This class defines a linked list (Reference-based) implementation
 *  of the ADT (Abstract Data Type) sorted list of StudentRec objects.
 *  Elements of the list are stored in ascending order by the
 *  student's name (i.e. name is the key).
 *
 *  The insert function inserts a student record to its proper location,
 *  and the delete function removes a student record with the given name.
 *
 *  The class also contains an iterator function, called nextStudent,
 *  that allows the user to step through a list and get each student
 *  in the list.
 *
 *  The class contains the following methods:
 *
 *   public boolean isEmpty();
 *        Determines whether the list is empty.
 *
 *   public int size();
 *        Returns the length of the list.
 *
 *   public void removeAll();
 *        Deletes all student records from the list.
 *
 *   public void insert(StudentRec newStudent);
 *        Inserts a new student to the list ordered by the name.
 *
 *   public boolean delete(String person);
 *        Deletes a student with the given name from the list.
 *        If the student is found and   deleted, returns true;
 *        otherwise, returns false.
 *
 *   public StudentRec find(String person);
 *        Determines whether a student is in the list, and returns the
 *        entire StudentRec if the student is found;
 *        otherwise returns null.
 *
 *   public boolean equals(SortedStudentList anotherList);
 *        Determines whether another SortedStudentList has the same
 *        contents as this list.
 *
 *   public Object clone();
 *        Creates a deep copy (with the same contents, but in different
 *        memory location) of the current object.
 */

public class SortedStudentList
{

     /**
      *  This is a private inner class that defines a node
      *  to be used in linked list.
      */

     private class Node  //  private inner class
     {
          StudentRec  data;
          Node        next;


          /**
           *  constructor
           */

          Node(StudentRec pupil)
          {
               data = new StudentRec(pupil);
               next = null;
          }


          /**
           *  constructor
           */

          Node(StudentRec pupil, Node nextNode)
          {
               data = new StudentRec(pupil);
               next = nextNode;
          }

     }


     private Node  head;           // head of the linked list
     private int   numItems;       // number of items in list


     /**
      *  Constructor
      */

     public SortedStudentList()
     {
          head = null;
          numItems = 0;
     }



     /**
      *  This method determines whether the student list is empty.
      */

     public boolean isEmpty()
     {
          return (numItems == 0);
     }



     /**
      *  This method returns the length of the student list.
      */

     public int size()
     {
          return numItems;
     }



     /**
      *  This method deletes all the students records from the list.
      */

     public void removeAll()
     {
          numItems = 0;
          head = null;
     }



     /**
      *  This method inserts a new student record to the list.
      */

     public void insert(StudentRec newStudent)
     {
          Node    previous, current, newNode;
          String  newKid = newStudent.getName();

          numItems++;

          //  First, find the proper position for the new item.

          previous = null;
          current = head;

          while ((current != null)
               && ((newKid).compareTo(current.data.getName()) > 0))
          {
               previous = current;
               current = current.next;
          }

          if (previous == null)
               head = new Node(newStudent, head);
          else
          {
               newNode = new Node(newStudent, current);
               previous.next = newNode;
          }
     }




     /**
      *  This method deletes a student with the given name
      *  from the list.
      */

     public boolean delete(String person)
     {
          Node previous, current;

          // find the location of the node to be deleted

          previous = null;
          current = head;
          while ((current != null)
                  && (person.compareTo(current.data.getName()) > 0))
          {
               previous = current;
               current = current.next;
          }

          if ((current != null) &&
                         (person.equals((current.data).getName())))
          {
               numItems--;

               if (previous == null)   // delete the head of the list
                    head = current.next;
               else
                    previous.next = current.next;

               return true;
          }
          else
               return false;
     }


     /**
      *  This method determines whether a student is in the list,
      *  and returns the entire StudentRec if the student is found;
      *  otherwise returns null.
      */

     public StudentRec find(String person)
     {
          String name = new String(person.trim());
          Node current = head;

          while ((current != null)
                  && (name.compareTo(((current.data).getName()).trim()) > 0))
               current = current.next;

          if ((current != null)
               && (name.equals(((current.data).getName()).trim())))
			return current.data;
          else
          return null;
     }


     // print()
     // This method display contents of the linked list
     public void print()
     {
          Node p = head;
          while (p != null)
          {
               System.out.println(p.data);
               p = p.next;
          }
     }


    


    /** (1)
     *  This method iterates through the LinkedList and returns the 
     *  number of students who have CS as theirs majors. 
     * @return
     */
	public int csMajors()
	{
		// Starts from the head of the list
		Node p = head;
		// Initializes the number of CS Majors to zero
		String csString = "CS";
		
		int csMajors = 0;
		
		// Iterate through the dnd of the list. 
		 while (p != null)
         {
			 // increments csMajors if case is >
			 
			 if (csString.equalsIgnoreCase(p.data.getMajor()))
			 {
				 csMajors++;
			 }
              p = p.next;
         }
		  
		// return the number of CS Majors 
		return csMajors;
	}


	/**(2)
	 * This method iterates through the linked list
	 * and returns the highest GPA received by any student 
	 * in the list.
	 */
	public float highestGPA() throws Exception 
	{
		// initializes a variable with highestGPA = 0
		float highestGPA = 0;
		
		// Throw an exception if  list is empty
		if ( numItems == 0)
		{
			throw new Exception("The list is empty");
		}
		else
		{
			Node p = head;
			
			// Iterates through the list
			while (p != null)
	         {
				 // if the GPA at the current node has higher value than highestGPA, set this GPA as the highest GPA
				 if (p.data.getGPA() > highestGPA)
				 {
					highestGPA = p.data.getGPA();
				 }
	              p = p.next;
	         }
		}
		// return highestGPA
		return highestGPA;
	}


	
	
	
	
	
	
	
	/**(3)
	 * This method return the data part for first node of the linked list
	 */
	public StudentRec findFirst() throws Exception 
	{
		// If the list is empty throw an exception
		if ( numItems == 0)
		{
			throw new Exception("The List is empty");
		}
		
		// returns the head of the linked list
		return head.data;
	}


	
	/**(4)
	 * This method returns the data part for the second element of the linked list
	 */
	public StudentRec findSecond() throws Exception 
	{
		
		// Throws and exception if the list is empty
		
		if ( numItems == 0){
			throw new Exception("List size zero");
		}
		else
		{	
			Node p = head;
			p = p.next;
			
			// Or if second node is null throw exception
			
			if ( p == null)
			{
				throw new Exception("The Second element is null");
			}
			
			else
			{
				return p.data;	
			}
			
		}
		
	}


	/**(5)
	 * This method returns the data part for last node of the linked list
	 */
	 
	 
	public StudentRec findLast() throws Exception 
	{
		// If the list is empty throw an exception
		if ( numItems == 0){
			throw new Exception("List size zero");
		}
		else{
			Node p = head;
			 
			// Iterates tthrough the  end of the list and returns the last element 
			
			while (p.next != null)
	         {
	              p = p.next;
	         }
			return p.data ;
		}
	}

	/**(6)
	 * This method returns the data part for second last node of the linked list
	 */
	 
	public StudentRec findSecondLast() throws Exception 
	{
		
		// Throws exeption iv numItems <2 or second element does not exist
		if ( numItems < 2)
		{
			throw new Exception("The List does not have 2 elements.");
		}
		else
		{
			
			Node p = head;
			Node next = head.next ; 
			
			// Iteratethrough to the second to last node and returns the contents
			while (next.next != null)
	         {
	              p = next;
	              next = next.next;
	         }
			 
			return p.data ;
		}
	}



	/**(7)
	 * This method deletes the first element of the linkedlist
	 */
	 
	public void deleteFirst() throws Exception 
	{
	
		// If the list is empty throws an exeption
		if ( numItems == 0)
		{
			throw new Exception("The List is empty");
		}
		
		// Sets head to the next element
		head = head.next;
		
		//Decrements number of elements in the list
		numItems-- ;
	}


	
	
	
	/**(8)
	 * This method deletes the second element of the linked list
	 */
	 
	public void deleteSecond() throws Exception
	{
		
		// If the list has less than two elements or second node does not exist, throws an exception
		if ( numItems < 2)
		{
			throw new Exception("Length of the list is < 2");
		}
		else
		{
			// Points the  first elements next to the third element
			head.next = head.next.next ;
			// decrements the number of items in the list
			numItems-- ;
			
		}
		
	}


	/**(9)
	 * This method delete the last node of the linked list
	 */
	 
	public void deleteLast() throws Exception 
	{
		// Throws exception if the list is empty
		if ( numItems == 0){
			throw new Exception("The list is empty");
		}
		// If the list has only one element,the list becomes empty
		else if ( numItems == 1){
			head = null ;
			numItems -- ;
		}
		
		//If list has two or more elements, a while loop will iterate until the last element is reached. 
		else 
		{
		
			Node previous = head ;
			Node current = head.next ;
			
			{
				// Iterates through the  second last element
				while (current.next != null)
		         {
		           	previous = current ;
		           	current = current.next ;
		         }	
				
				
				// sets the  end of the second last element to null
				previous.next = null ;
				numItems -- ;
			}
		}
		
	}
	



	/**(10)
	 * This method deletes the second last element of the linked list
	 */
	 
	public void deleteSecondLast() throws Exception 
	{
		// Throws eception ifthere is no second node.  
		if ( numItems < 2)
		{
			throw new Exception("There is not two elements in the list");
		}
		
		else if ( numItems == 2)
		{
			head = head.next ;
			numItems -- ;
		}
		
		else 
		{
			
			
			
			// Iterate through the  second last element of the list set the next of third last element
			// to the last element
			Node thirdLast = head ;
			Node previous = thirdLast.next ;
			Node current = previous.next ;
			
			{
				while (current.next != null)
		         {
					thirdLast = previous ;
		           	previous = current ;
		           	current = current.next ;
		         }	
				 
				thirdLast.next = current ;
				numItems -- ;
				
			}
		}	
		
	}



	/**(11)
	 * This method removes all the elements from the linked list whose GPA is less than a 2.0
	 */ 
	public void removeDismissed() throws Exception
	{
		
		Node current		= head;
		Node previous = null;
		
		
		
		if ( numItems == 0){
			throw new Exception("The list is empty");
		}
		
		// If the list has only one element,the list becomes empty
		else if(( numItems == 1)&&((head.data).getGPA()) < 2.0)
		{
			head = null ;
			numItems-- ;
		}
		else{
		
		while(current != null)
		{
			if(head.data.getGPA() < 2.0)
			{
				head = head.next;
				current = current.next;
				numItems--;
			}
			else
			{
				if (current.data.getGPA() < 2.0)
				{
					previous.next = current.next;	
					current = current.next;
					numItems--;
				}
				else
				{
					previous = current;
					current = current.next; 
				}
			}
		}
		}
	}
	
	
/**   3.) equals method
      *  This method determines whether another SortedStudentList
      *  has the same contents as this list.
      */

     public boolean equals(Object anotherList)
     {
          SortedStudentList list = (SortedStudentList) anotherList;

          boolean isEqual = true ;
          
		  if (numItems != list.numItems)
               return false;
          
		  else
          {
		  
        	  // Begins with the head of each list and compares the data of the corresponding nodes of each list.
        	  Node p = head;
        	  Node q = list.head ;
              
			  while (p != null)
              {
                   if ( q.data.equals(p.data))
                   {
                	   p = p.next;
                	   q = q.next ;
                   }
                   else
                   {
                	   return false;
                	   
                   }
              }
              
              return true;
          }
     }


     /**  3.) clone method
      *  This method creates a deep copy (with the same contents,
      *  but in different memory location) of the current list.
      *  Note that it returns an Object, because that is how it is
      *  defined in the Object class.
      */

     public Object clone()
     {
          SortedStudentList newList = new SortedStudentList();
          
          
		  // If there are no elements in the original list, we are done and can return the newList
          if ( this.numItems == 0)
          {
        	  return newList ;
          }
          else
          {
        	  Node p = head;
        	  Node next = null;
        	  Node current = null; 
        	
			
              // Creates a new node with data as head of original nodeand sets it a head of cloned list
        	  float gpa = p.data.getGPA();
        	  String major = p.data.getMajor();
        	  String name = p.data.getName();
        	  
			  //Creats new node with above created parameters and the newList with the 
			  //newly created studentRec object, along with the next pointer for the reference parameter.  
        	  StudentRec newStudentRec = new StudentRec(name, major, gpa);
			  newList.head = new Node(newStudentRec, next);
        	  
			  current = newList.head;
			  newList.numItems ++ ;
        	  
			  p = p.next;
      		  
        	  // Iterate through the original list, creates new nodes with data same as corresponding 
			  //original node, and adds the node to the cloned list.
        	  while ( p != null)
        	  {
        		  gpa = p.data.getGPA();
            	  major = p.data.getMajor();
            	  name = p.data.getName();
            	  
            	  newStudentRec = new StudentRec(name, major, gpa);
            	  current.next = newList.new Node(newStudentRec, next);
            	  current = current.next;
            	  newList.numItems ++ ;
            	  p = p.next;	  
        	  } 
          }
          return newList;
     }
}  