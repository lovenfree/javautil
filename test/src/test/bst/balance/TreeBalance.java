package test.bst.balance;

//Tree의 Balance 확인하기
class Tree{
	class Node{
		int data;
		Node left;
		Node right;
		
		Node(int data){
			this.data = data;
		}
	}
		Node root;
		
		Tree(int size){
			root = makeBST(0,size-1);
		}
		

		public Node makeBST(int start, int end) {
			if(start > end) return null;
			 
			int mid  = (start+end) /2;
			Node node = new Node(mid);
			node.left = makeBST(start,mid-1);
			node.right = makeBST( mid+1,end);
			
			return node;
			
		}
		
		
		boolean isBalanced(Node root) {
			if(root == null) return true;
			int heightDiff = getHeight(root.left) -getHeight(root.right); 
			if(Math.abs(heightDiff) >1) {
				return false;
			}else {
				return isBalanced(root.left) && isBalanced(root.right);
			}
		}
		
		//O(N logN)
		int getHeight(Node root) {
			if(root == null) return -1;
		//왼 오른 쪾을 돌다가 더 큰 놈을 선
			return Math.max(getHeight(root.right), getHeight(root.left))+1;
		}
		
		int checkHeight(Node root) {
			if(root == null) return -1;
			int leftHeight = checkHeight(root.left);
			if(leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
			int rightHeight = checkHeight(root.right);
			if(rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
			
			int heightDiff = leftHeight -rightHeight; 
			if(Math.abs(heightDiff) >1) {
				return Integer.MIN_VALUE;
			}else {
				return Math.max(getHeight(root.right), getHeight(root.left))+1;
			}
		}
		
		
		boolean isBalanced2(Node root) {
				return checkHeight(root) != Integer.MIN_VALUE;
		}
		
		class Level{
			int min = Integer.MIN_VALUE;
			int max = Integer.MAX_VALUE;
		}
		
		boolean isBalanced3(Node root) {
			Level obj= new Level();
			checkBalanced(root, obj,0);
			if(Math.abs(obj.min-obj.max) >1) return false;
			else return true;
		}
		
		void checkBalanced(Node node, Level obj, int level) {
			if(node == null) {
				if(level <obj.min) obj.min = level;
				else if(level > obj.max) obj.max = level;
				return;
			}
			
			checkBalanced(node.left, obj,level+1);
			checkBalanced(node.right, obj,level+1);
			
		}

	}

public class TreeBalance {
	
	public static void main(String[] args) {
		
		Tree t = new Tree(10);
		t.isBalanced(t.root);
		
	}

}
