public class TreeNode {
	private Object value;	
	private TreeNode left;
	private TreeNode right;
	
	public TreeNode(Object v) 
		{ value = v; left = null; right = null;}
	
	public TreeNode(Object v, TreeNode l, TreeNode r) 
		{ value = v; left = l; right = r; }
	
	public Object getValue() { return value; }
	public TreeNode getLeft() { return left; }
	public TreeNode getRight() { return right; }
	
	public void setValue(Object v) { value = v; }
	public void setLeft(TreeNode l) { left = l; }
	public void setRight(TreeNode r) { right = r; }
}