/**
 * 	Class Description: This class creates a TreeNode object which contains a value and a left and right 
 * 						reference to other TreeNodes. 
 * 						//Most of this code is from p. 579 of the textbook
 *  @author MurphyP1
 *  @date 2/25/18
 */
public class TreeNode {
	private Object value;	
	private TreeNode left;
	private TreeNode right;
	
	/** 
	 * The default constructor which accepts an argument for the value to be stored.
	 * @author MurphyP1
	 * @date 2/25/18
	 * @method TreeNode
	 * @param v the Object whose value is stored within the TreeNode
	 */
	public TreeNode(Object v) 
		{ value = v; left = null; right = null;}
	
	/** 
	 * The expanded constructor which accepts arguments for the value as well as references to left and right leaves of the TreeNode
	 * @author MurphyP1
	 * @date 2/25/18
	 * @method TreeNode
	 * @param v the Object whose value is stored within the TreeNode
	 * @param l a reference to the root of the left sub-TreeNode
	 * @param r a reference to the root of the right sub-TreeNode
	 */
	public TreeNode(Object v, TreeNode l, TreeNode r) 
		{ value = v; left = l; right = r; }
	
	//GETTERS// 
	
	/** 
	 * A getter method which returns the value stored within the TreeNode
	 * @author MurphyP1
	 * @date 2/25/18
	 * @method getValue
	 * @return the value of the TreeNode
	 */
	public Object getValue() { return value; }
	
	/** 
	 * A getter method which returns a reference to the root of the left sub-TreeNode
	 * @author MurphyP1
	 * @date 2/25/18
	 * @method getLeft
	 * @return a reference to the root of the left sub-TreeNode
	 */
	public TreeNode getLeft() { return left; }
	
	/** 
	 * A getter method which returns a reference to the root of the right sub-TreeNode
	 * @author MurphyP1
	 * @date 2/25/18
	 * @method getRight
	 * @return a reference to the root of the right sub-TreeNode
	 */
	public TreeNode getRight() { return right; }
	
	//SETTERS//
	
	/** 
	 * A setter method which defines the value of a TreeNode
	 * @author MurphyP1
	 * @date 2/25/18
	 * @method setValue
	 * @param v an Object which becomes the value of the TreeNode
	 */
	public void setValue(Object v) { value = v; }
	
	/** 
	 * A setter method which defines reference to the left sub-TreeNode
	 * @author MurphyP1
	 * @date 2/25/18
	 * @method setLeft
	 * @param l the TreeNode which becomes the left sub-TreeNode
	 */
	public void setLeft(TreeNode l) { left = l; }
	
	/** 
	 * A setter method which defines reference to the right sub-TreeNode
	 * @author MurphyP1
	 * @date 2/25/18
	 * @method setRight
	 * @param r the TreeNode which becomes the right sub-TreeNode
	 */
	public void setRight(TreeNode r) { right = r; }
	
	//EXTRA HELPER METHODS//
	
	/** 
	 * A setter method which calls both the setLeft and setRight methods for concision
	 * @author MurphyP1
	 * @date 2/27/18
	 * @method setLeaves
	 * @param l the TreeNode which becomes the left sub-TreeNode
	 * @param r the TreeNode which becomes the right sub-TreeNode
	 */
	public void setLeaves(TreeNode l, TreeNode r) { setLeft(l); setRight(r); }
	
	/** 
	 * A setter method which calls both setValue and setLeaves so a TreeNode can be fully configured after creation with one method call
	 * @author MurphyP1
	 * @date 2/27/18
	 * @method fix
	 * @param l the TreeNode which becomes the left sub-TreeNode
	 * @param r the TreeNode which becomes the right sub-TreeNode
	 */
	public void fix(Object v, TreeNode l, TreeNode r) { setValue(v); setLeaves(l, r); }
}
