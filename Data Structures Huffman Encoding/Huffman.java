public class Huffman
{
	private int[] frequencies; // frequency of each character
	private String[] codes; // code of each character
	private TreeNode finalRoot;

	
	/**
	 * The Huffman constructor. Builds the tree and create huffman codes
	 * based on input freq array.
	 * 
	 * @param freq
	 */
	public Huffman(int[] freq) // constructor
	{
		//create the frequencies array;
		frequencies = new int[26];
		codes = new String[26];

		
		//Copy the input freq array to frequencies array
		System.arraycopy(freq, 0, frequencies, 0, freq.length);
		
		//create the huffman codes for input characters
		createCode();
	}


	/**
	 * Return the code for any given character.
	 * @param c
	 * @return
	 */
	public String codeOf(char c)
	{
		// index of Char is c - 'A', since A is stored at index 0.
		int index = c - 'A';
		//return the code at the index;
		return codes[index];
	}


	/**
	 * Method to create Huffman code for each character
	 */
	private void createCode()
	{
		// contains pointers to all leaf nodes
		TreeNode[] leaves; 
		
		List listOfRoots = new List();
		
	
		leaves = new TreeNode[26];

		/*create the leaves array (with indices 0..25, one for each
		  uppercase letter) */
		for (int i=0 ; i <=25; i ++)
		{
			leaves[i] = new TreeNode(0);
		}


		/*step through the frequencies array and for each character
		  with a frequency > 0, insert the frequency into the
		  linked list listOfRoots, store the object (pointer) returned by the insert function in the leaves array;
		  pass listOfRoots to buildTree to build the Huffman tree, and let finalRoot be the root returned by buildTree;*/
		
		int i= 0;
		for ( int frequency : frequencies)
		{
			
			if ( frequency > 0)
			{
				TreeNode treeNode = listOfRoots.insert(frequency);
				leaves[i] = treeNode ;
			}
			i++;

		}

		// now the Huffman tree is built and its root is returned
		finalRoot = buildTree(listOfRoots);

		//step through the leaves array and for each character
		for (int j=0; j < leaves.length ; j++)
		{

			/*use the pointer (if it is not null) stored in the leaves array to climb up the Huffman tree, 
			  and construct the Huffman code;
				(1) when climbing up the tree, a left child would get a 0 bit and a right child would get a 1 bit
				(2) when climbing up the tree we first get the last bit of the code and move backward
			 */

			StringBuffer code = new StringBuffer(25);

			TreeNode leaf = leaves[j];

			TreeNode parent = leaf.getParent() ;
			while ( parent != null)
			{
				if ( leaf.isLeftChild())
				{
					code.insert(0, "0");
				}
				else
				{
					code.insert(0, "1");
				}

				leaf = leaf.getParent() ;
				parent = parent.getParent() ;

			}
			if ( code.length() != 0)
				codes[j] = new String(code);

		}
	}


	/**
	 * Method to build the tree and get root node
	 * @param roots
	 * @return
	 */
	private TreeNode buildTree(List roots)
	{
		while ( roots.size() >= 2 ) 
		{
			// remove two nodes from the front of the linked list
			TreeNode left = roots.remove();
			TreeNode right = roots.remove() ;

			// combine their frequencies and call insert to insert this combined frequency into the linked list of roots;
			int newfreq = left.getFrequency() + right.getFrequency() ;
			
			TreeNode parent = roots.insert(newfreq);
			
			// set isLeftChild and parent object of two child nodes appropriately; 
			//the node with lower frequency is the left child, and the node with higher frequency is the right child
			left.setLeftChild(true);
			right.setLeftChild(false);

			left.setParent(parent);
			right.setParent(parent);


		}
		return roots.getFirst();
	}


	/**
	 * @return the finalRoot
	 */
	public TreeNode getFinalRoot() {
		return finalRoot;
	}
	
	
}