class TreeNode implements Comparable<TreeNode> {
	// variable to hold frequency of the element
	private int frequency;

	// Boolean to hold if element is left or right child
	private boolean isLeftChild;

	// Variable to hold parent node
	private TreeNode parent;

	// Variable to hold next node
	private TreeNode next;
	

	/**
	 * No-arg constructor to initialize the TreeNode
	 * 
	 * @param freq
	 */
	public TreeNode(int freq) {
		frequency = freq;
		isLeftChild = true;
		parent = null;
		next = null;
	}

	/**
	 * @return the frequency
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * @param frequency
	 *            the frequency to set
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * @return the isLeftChild
	 */
	public boolean isLeftChild() {
		return isLeftChild;
	}

	/**
	 * @param isLeftChild
	 *            the isLeftChild to set
	 */
	public void setLeftChild(boolean isLeftChild) {
		this.isLeftChild = isLeftChild;
	}

	/**
	 * @return the parent
	 */
	public TreeNode getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	/**
	 * @return the next
	 */
	public TreeNode getNext() {
		return next;
	}

	/**
	 * @param next
	 *            the next to set
	 */
	public void setNext(TreeNode next) {
		this.next = next;
	}

	

	/**
	 * compareTo overridden to sort the List such that element with largest
	 * frequency is at the end.
	 */
	@Override
	public int compareTo(TreeNode treeNode) {

		int frequency = treeNode.frequency;

		if (this.frequency > frequency) {
			return 1;
		} else if (this.frequency == frequency) {
			return 0;
		} else {
			return -1;
		}

	}

}