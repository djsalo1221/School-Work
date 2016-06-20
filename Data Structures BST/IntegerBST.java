//     @author Daniel Salo
//		@version 8.14.14

/**
 *  This file defines a reference-based implementation of the ADT
 *  binary search tree of integer elements.
 *
 *  The class contains the following methods:
 *
 *
 *   public boolean isEmpty();
 *
 *
 *   public int size();
 *        Returns the number of elements of the binary search tree.
 *
 *
 *   public void removeAll();
 *        Deletes all the elements from the binary search tree.
 *
 *
 *   public void traverse();
 *        Inorder traverse the tree and print the value of each node.
 *        Inorder traversal is used so nodes are visited in the order of
 *        their key values.
 *
 *
 *   public boolean find(int key);
 *        Determines whether an element is in the binary search tree, and
 *        returns true or false accordingly.
 *        In real applications, typically this function would return an
 *        entire record (an object) if the key is found.
 *
 *
 *   public void insert(int newKey);
 *        Inserts an element to the binary search tree.
 *
 *
 *   public boolean delete(int key);
 *        Deletes an element from the binary search tree.  If the element
 *        is found and deleted, returns true; otherwise, returns false.
 *
 */

import java.util.*;

public class IntegerBST
{

    private class IntegerTreeNode   // private inner class
    {
        int             item;
        IntegerTreeNode left;   // left child
        IntegerTreeNode right;  // right child


        /**
         *  constructor
         *  Note that once we define a constructor, the compiler 
         *  will not generate a parameterless default constructor.
         */

        IntegerTreeNode(int newItem)
        {
            item = newItem;
            left = null;
            right = null;
        }


        /**
         *  constructor
         */

        IntegerTreeNode(int newItem, IntegerTreeNode leftChild,
                                 IntegerTreeNode rightChild)
        {
            item = newItem;
            left = leftChild;
            right = rightChild;
        }

     }


     private IntegerTreeNode root;      // root of the binary search tree
     private int             numItems;  // number of elements in the tree


     /**
      *   Constructor - It initializes the binary search tree object.
      *   Since a default constructor would do exactly the same, it
      *   is not really necessary.  However, it is a good practice
      *   to provide your own constructor.
      */

     public IntegerBST()
     {
         root = null;
          numItems = 0;
     }



     /**
      *   This method determines whether the tree is empty.
      */

     public boolean isEmpty()
     {
         return (root == null);
     }



     /**
      *  This method returns the number of elements of the tree.
      */

     public int size()
     {
         return numItems;
     }



     /**
      *  This method deletes all the elements from the tree.
      */

     public void removeAll()
     {
          root = null;
     }




     /**
      *   Inorder traverse the tree and print the value of each node.
      */

     public void traverse()
     {
          inorder(root);
                    // call recursive function inorder to traverse
                    // the tree
          System.out.println('\n');
     }





     /**
      *  This recursive method visits and prints each tree node
      *  by inorder traversal.
      */

     private void inorder(IntegerTreeNode tNode)
     {
          if (tNode != null)
          {
               inorder(tNode.left);
               System.out.print(tNode.item + "   ");
               inorder(tNode.right);
          }
     }




     /**
      *  This method determines whether an element is in the tree, and
      *  returns true or false accordingly.
      *  In real applications, typically this function would return an
      *  entire record (an object) if the key is found.
      */

     public boolean find(int key)
     {
          return findItem(root, key);
                    // call recursive function findItem to find
                    // the key
     }





     /**
      *  This recursive method searches for a given key in the tree.
      */
     
     private boolean findItem(IntegerTreeNode tNode, int k)
     {
          if (tNode == null)
               return false;
          else if (k == tNode.item)
               return true;
          else if (k < tNode.item)
               return findItem(tNode.left, k);
          else
               return findItem(tNode.right, k);
     }




     /**
      *  This method inserts an element to the tree.
      */

     public void insert(int newKey)
     {
          root = insertItem(root, newKey);
                    // call recursive function insertItem to insert
                    // the new key

          ++numItems;
     }




     /**
      *  This recursive method inserts a new key to the tree with
      *  the given root.
      */
     
     private IntegerTreeNode insertItem(IntegerTreeNode tNode, int k)
     {
          IntegerTreeNode newSubtree;

          if (tNode == null)
               return new IntegerTreeNode(k);
          else if (k < tNode.item)
          {
               newSubtree = insertItem(tNode.left, k);
               tNode.left = newSubtree;
               return tNode;
          }
          else
          {
               newSubtree = insertItem(tNode.right, k);
               tNode.right = newSubtree;
               return tNode;
          }

     }



     /**
      *  This method deletes an element from the tree.
      */

     public boolean delete(int key)
     {
          boolean found;

          found = find(key);

          if (found)
          {
               root = deleteItem(root, key);
                    // call recursive function delete to delete
                    // the key

               --numItems;
               return true;
          }
          else
               return false;
     }



     /**
      *  This recursive method deletes a key from the tree with
      *  the given root.
      */
     
     private IntegerTreeNode deleteItem(IntegerTreeNode tNode, int k)
     {
          IntegerTreeNode newSubtree;

          if (k == tNode.item)
               tNode = deleteNode(tNode);
          else if (k < tNode.item)
          {
               newSubtree = deleteItem(tNode.left, k);
               tNode.left = newSubtree;
          }
          else
          {
               newSubtree = deleteItem(tNode.right, k);
               tNode.right = newSubtree;
          }
          
          return tNode;
     }






     /**
      *  This recursive method deletes a node from the tree.
      */

     private IntegerTreeNode deleteNode( IntegerTreeNode tNode )
     {
          if ( ( tNode.left == null ) && ( tNode.right == null ) )
          // leaf node

               return null;

          else if ( tNode.left == null )
          // has only one child - the right child

               return tNode.right;

          else if( tNode.right == null )
          // has only one child - the left child

               return tNode.left;

          else    // has two childre, replace it by the leftmost
                  // node of the right subtree
          {
               tNode.item = findLeftmost( tNode.right );
               tNode.right = deleteLeftmost( tNode.right );
               return tNode;
          }
     }




     /**
      *  This method finds and returns the data stored on the
      *  leftmost node of the tree nNode.
      */

     private int findLeftmost( IntegerTreeNode tNode )
     {
          IntegerTreeNode p = tNode;
          
          while( p.left != null )
               p = p.left;
          
          return p.item;
     }



     /**
      *  This method deletes the leftmost node of the tree nNode.
      */

     IntegerTreeNode deleteLeftmost( IntegerTreeNode tNode )
     {
          if ( tNode.left == null )
               return tNode.right;
          else
          {
               tNode.left = deleteLeftmost( tNode.left );
               return tNode;
          }
     }


	 
	 /**
      * This method initiates the process of creating  a deep copy of an IntegerBST. 
		*Calls next private method, copyTree, if it is not an empty tree.
      */
     
	public Object clone()
	{
		IntegerBST newTree = new IntegerBST();
		
		newTree.numItems = numItems;
		
		if( numItems > 0 )
			newTree.root = copyTree( root );
		
		return newTree;
	}

	/**
      *  Creates a deep copy of an IntegerBST.  uses recursive definition to traverse through tree and copy contents.  
	  *@param tNode from clone()
	  *@return newNode the new node to be added to the tree
      */
	private IntegerTreeNode copyTree( IntegerTreeNode tNode )
	{
		if( tNode != null )
		{
			IntegerTreeNode newNode = new IntegerTreeNode( tNode.item );
			
			newNode.left = copyTree( tNode.left );
			newNode.right = copyTree( tNode.right );
			
			return newNode;
		}
		
		return null;
	}

	/**
      *  Tests for equality of an IntegerBST.  if tree has same number of items, call sameContents()
	  *@param anotherTree the other tree to test equality
	  *@return boolean depending on wether or not numItems are equal, method will return either false or call the sameContents() method.  
      */
     
	public boolean equals( IntegerBST anotherTree )
	{
		if( anotherTree.numItems != numItems )
			return false;
		
		return sameContents( root, anotherTree.root );
	}
	
	/**
      *  Uses recursive definition to determine whether the IntegerBSTs have same contents, after numItems has been compared and verified
	  * @param t1
	  *@param t2 
	  *return boolean whether or not specific IntegerTreeNode in IntegerBST have same contents. 
      */
     
	private boolean sameContents( IntegerTreeNode t1, IntegerTreeNode t2 )
	{
		if( t1 == null && t2 == null )
			return true;
		else if( t1 == null || t2 == null )
			return false;
		else // both are not empty
		{
			if( t1.item != t2.item )
				return false;
			else
				return sameContents( t1.left, t2.left )
					&& sameContents( t1.right, t2.right );
		}
	}

	/**
      *If root != null and height is 0  . calls findDepth and returns int value representing height. 
      */
     
	public int height()
	{
		if( root == null )
			return 0;

		return findDepth( root );
	}
	
	/**
      * Called from height().  
		*@param n depth of the left or right node that has been called. 
		@return (d+1) the depth of the tree.
      */
     
	private int findDepth( IntegerTreeNode n )
	{
		if( n == null )
			return -1;
			
		int d = 0;
		
		d = findDepth( n.left );
		d = Math.max( d, findDepth( n.right ) );
		
		return d + 1;
	}
/*
	* Finds the length of the shortest path from the root to any
	* leaf. Return -1 if the tree is empty.
	*@return d integer representation of shortest path.
*/
	public int minPath()
	{
		int minPathLeft = -1;
		int minPathRight = -1;
		
		if(root.left==null && root.right == null)
			return minPathLeft;
			
		minPathLeft = findDepth(root.left);
		minPathRight = findDepth(root.right);
		
		return (Math.max((minPathLeft + 1),(minPathRight +1)));
		
		
	}

	/**
      * Checks to see if tree is balanced, calls balancedAtNode for return value, recursivly for each node  
	  * @rerun boolean 
      */
     
	public boolean balanced()
	{
		return balancedAtNode( root );
	}
	
	/**
      * Checks to see if tree is balanced, calls recursivly at each node.  Checks to see if tree is parfectly balanced(difference of left and right subtree
	  *no more than 1
	  
	  * @rerun boolean 
      */
	private boolean balancedAtNode( IntegerTreeNode tNode )
	{
		if( tNode == null )
			return true;
		else
		{
			int numLeft = numNodes( tNode.left );
			int numRight = numNodes( tNode.right );
			
			if( Math.abs( numLeft - numRight ) <= 1 )
				//return true;
				return (balancedAtNode( tNode.left ) && balancedAtNode( tNode.right ));
		}
		
		return false;
	}
	/**
      * Checks to see if tree is balanced, calls balancedAtNode for return value, recursivly for each node  
	  * @param n counts number of nodes for the subtree of IntegerTreeNode n.  
		*@return int number of nodes in subtree for specific node. 
      */
	private int numNodes( IntegerTreeNode n )
	{
		if( n == null )
			return 0;
			
		return 1 + numNodes( n.left ) + numNodes( n.right );
	}

	/**
      * Checks to see if tree is balanced, calls heightNodeBalanced for return value, recursivly for each node  
	  * @rerun boolean 
      */
	public boolean heightBalanced()
	{
		if( root == null )
			return true;
			
		return heightNodeBalanced( root );
	}
	
	
	/**
      * Checks to see if tree is balanced,recursively for each node (whether or not the difference between the number
		*of nodes of the subtrees is <=> 1.  
	  * @rerun boolean 
      */
	private boolean heightNodeBalanced( IntegerTreeNode n )
	{
		if( n == null )
			return true;
			
		if( Math.abs( findDepth( n.left ) - findDepth( n.right ) ) > 1 )
			return false;
		
		return (heightNodeBalanced( n.left )&& heightNodeBalanced( n.right ));
	}
}
