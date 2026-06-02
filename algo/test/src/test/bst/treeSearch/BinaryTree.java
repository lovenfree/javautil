package test.bst.treeSearch;


/*
 *       (1)
 *       
 *     (2)   (3)
 *     
 *   (4)  (5)
 * 
 * inorder (left, root, right) : 4 2 5 1 3
 * preorder (root, left, right) : 1 2 4 5 3
 * postorder (left, right, root) : 4 5 2 3 1
 * 
?. ?*/

// Binary Tree의 3가지 순회방법 구현하기
class TreeNode{
	int data;
	TreeNode left;
	TreeNode right;

}

class Tree{
	public TreeNode root;
	
	public void setRoot(TreeNode node) {
		this.root = node;
	}
	
	public TreeNode getRoot() {
		return root;
	}

	public static TreeNode makeNode(TreeNode left, int data, TreeNode right) {
		TreeNode node = new TreeNode();
		node.data = data;
		node.left = left;
		node.right = right;
		return node; 
	}
	
	public static void inorder(TreeNode node) {
		if(node!=null) {
			inorder(node.left);
			System.out.println(node.data);
			inorder(node.right);
		}
	}
	
	public static void preorder(TreeNode node) {
		if(node!=null) {

			System.out.println(node.data);
			inorder(node.left);
			inorder(node.right);
		}
	}
	
	public void postorder(TreeNode node) {
		if(node!=null) {
			inorder(node.left);
			inorder(node.right);
			System.out.println(node.data);
		}
	}
	

}
 
public class BinaryTree {
	public static void main(String[] args) {
		Tree t = new Tree();
		TreeNode n4 = t.makeNode(null, 4, null);
		TreeNode n5 = t.makeNode(null, 5, null);
		TreeNode n2 = t.makeNode(n4, 2, n5);
		TreeNode n3 = t.makeNode(null, 3, null);
		TreeNode n1 = t.makeNode(n2, 1, n3);
		
		t.root = n1;
		t.preorder(n1);
		  
	}



}
