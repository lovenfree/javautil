package test.bst.inorderTraverse;


//이진검색트리에서 다음노드 찾기 (inorder traverse)
//이진 검색 트리에서 주어진 노드의 다음 노드 찾 
//다음 노드 순서는 InorderTraverse
//Inorder (left -> data ->  right)
class Tree{
	class Node{
		int data;
		Node left;
		Node right;
		Node parent;
		Node (int data){
			this.data = data;
		}
	}
	
	Node root;
	Tree(int size){
		root = makeBST(0, size-1, null);
	}


	public Node makeBST(int start, int end, Node parent) {
		if(start > end) return null;
		 
		int mid  = (start+end) /2;
		Node node = new Node(mid);
		node.left = makeBST(start,mid-1, node);
		node.right = makeBST( mid+1,end, node);
		node.parent = parent;
		
		return node;
		
	}
	
	void findNext(Node node) {
		if(node.right == null) {
			//오른쪽이 없는 경우 부모로 간다 
			System.out.println(findAbove(node.parent, node).data +"is "+
			node.data +"'s next");
		}else {
			//오른쪽이 있으면 아래에서 찾아
			System.out.println(findBelow(node.right).data +"is "+
					node.data +"'s next");
			
		}
		
	}
	
	//부모노드와 나자신을 같이 보내야 내가 부모의어느쪽에 있는지 안다.
	Node findAbove(Node root, Node child) {
		if(root == null) return null;
		if(root.left == child) return root;
		return findAbove(root.parent, root);
		
	}
	
	
	Node findBelow(Node root) {
		if(root.left == null) return root;
		return findBelow(root.left);
		
	}
	
}


public class InorderTraverse {
	
	public static void main(String[] args) {
		Tree t = new Tree(10);
		//3
		t.findNext(t.root.left.right.right);
		t.findNext(t.root.left);
		t.findNext(t.root);
		t.findNext(t.root.left.left);
	}
	
	
}
