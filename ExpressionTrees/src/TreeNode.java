//From p. 579 of Textbook
public class TreeNode {
	private Object value;
	private TreeNode left;
	private TreeNode right;
	
	//Default Constructor
	public TreeNode(Object v) 
		{ value = v; left = null; right = null;}
	
	//more args Constructor
	public TreeNode(Object v, TreeNode l, TreeNode r) 
		{ value = v; left = l; right = r; }
	
	//getters
	public Object getValue() { return value; }
	public TreeNode getLeft() { return left; }
	public TreeNode getRight() { return right; }
	
	//setters
	public void setValue(Object v) { value = v; }
	public void setLeft(TreeNode l) { left = l; }
	public void setRight(TreeNode r) { right = r; }
	
	//My added helpers
	public boolean hasChildren() {
		return (getLeft() != null && getRight() != null );
	}
	
	public boolean hasLeft() { return getLeft() != null; }
	public boolean hasRight() { return getRight() != null; }

}
