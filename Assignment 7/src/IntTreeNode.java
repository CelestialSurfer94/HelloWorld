
public class IntTreeNode {

private IntTreeNode left;
private IntTreeNode right;
private int data;

	public IntTreeNode(int data , IntTreeNode left, IntTreeNode right){
		this.data = data;
		this.left = left;
		this.right = right;
			
	}
	
	public IntTreeNode(int data){
		this.data = data;
		
	}
	
	public boolean isLeaf(){
		return this.left == null && this.right == null;
		}
}

