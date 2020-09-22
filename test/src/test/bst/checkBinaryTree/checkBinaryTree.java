package test.bst.checkBinaryTree;



//주어진 트리가 이진검색트리인지 확인하기
//이진검색트리이면 inorder로 출력시 오름차순 된 데이터가 출력된다. 
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
		int size;
		
		Tree(int size){
			this.size = size;
			root = makeBST(0,size-1);
//			root.right.right.right.left = new Node(10);
//			this.size++;
		}

		public Node makeBST(int start, int end) {
			if(start > end) return null;
			 
			int mid  = (start+end) /2;
			Node node = new Node(mid);
			node.left = makeBST(start,mid-1);
			node.right = makeBST( mid+1,end);
			
			return node;
			
		}
		
		
		boolean isValidateBST1() {
			int[] array = new int[size];
			inorder(root, array);
			for(int i=1;i<array.length;i++) {
				if(array[i] < array[i-1]) {
					return false;
				}
			}
			return true;
		}
		
		int index = 0;
		void inorder(Node root, int[] array) {
			if(root!=null) {
				inorder(root.left, array);
				array[index] = root.data;
				index++;
				inorder(root.right, array);
		
				
			}
		}
		
		Integer last_printed = null;
		boolean isValidateBST2() {
			return isValidateBST2(root);
		}
		
		boolean isValidateBST2(Node node) {
			if (node == null) return true;
			if(!isValidateBST2(node.left)) return false;
			if(last_printed!=null && node.data < last_printed) {
			
				return false;
			} 
			
			last_printed = node.data;
			if(!isValidateBST2(node.right)) return false;
			return true;
		}
		
		
		boolean isValidateBST3() {
			return isValidateBST3(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
		}
		
		
		boolean isValidateBST3(Node node, int min, int max) {
			if (node == null) return true;
			
			if(node.data < min || node.data > max) {
				return false;
			}
			
			if(!isValidateBST3(node.left, min, node.data) 
					||!isValidateBST3(node.right, node.data, max)) {
				return false;
			}
			
			return true;
		}
		
}
		
public class checkBinaryTree {
	
	public static void main(String[] args) {
		Tree t = new Tree(10);
		//t.printList(t.BSTtoList());
		System.out.println(t.isValidateBST1());
		System.out.println(t.isValidateBST2());
		System.out.println(t.isValidateBST3());
		
	}

}
