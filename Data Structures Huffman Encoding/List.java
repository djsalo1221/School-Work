//@author Daniel Salo
//@version 8.14.14

import java.util.Collections;
import java.util.LinkedList;

class List extends LinkedList<TreeNode>
{
	TreeNode head;
	int numItems; // number of nodes in the list
	
	/**
	 * No-arg Constructor initialize the head to null and size to zero
	 */
	public List()
	{
		head = null; 
		numItems = 0;
	}

	public TreeNode insert(int freq)
	{
		//insert the frequency freq into the linked list 
		TreeNode treeNode = new TreeNode(freq);
		
		this.add(treeNode);
		
		numItems++;
		
		//Sort the list so that element with the least frequency is at the beginning of the linked list, 
		//and the element with the largest frequency is at the end of the linked list
		Collections.sort(this);
		
		head = this.getFirst();
		
		return treeNode ;
		
	}
	
	public TreeNode removeFront()
	{
		//when the linked list is empty. In this case, simply return null
		if (numItems == 0)
		{
			return null ;
		}
		
		numItems--;
		
		//remove the head of the list
		TreeNode removedNode = (TreeNode) this.remove();
		
		head = this.getFirst();
		
		
		return removedNode;
		
	}
	
	public int length()
	{
		return numItems;
	}
}