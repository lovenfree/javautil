package test.bst.arraytobst;


//] 배열을 이진검색트리로 만들기 in Java
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
		
		//재귀호출에 필요한 데이터 던져주고 root의 주소를 받아 멤버에 넣는다  
		public void makeTree(int[] a) {
			root = makeTreeR(a,0,a.length-1);
			
		}
		
		public Node makeTreeR(int[] a, int start, int end) {
			if(start > end) return null;
			
			int mid  = (start+end) /2;
			Node node = new Node(a[mid]);
			node.left = makeTreeR(a,start,mid-1);
			node.right = makeTreeR(a, mid+1,end);
			
			return node;
			
		}
		
		public void searchBTree(Node n, int find) {
			if(find<n.data) {
				System.out.println("Data is smaller than "+ n.data);
				searchBTree(n.left, find);
			}else if(find>n.data) {
				System.out.println("Data is bigger than "+ n.data);
				searchBTree(n.right, find);
			}else {
				System.out.println("Data found!");
			}
		}
	}

	


public class binarySearchTree {
	
	public static void main(String[] args) {
		int[] arr = new int[10];
		for(int i=0;i<arr.length;i++) {
			arr[i] = i;
		}
		
		Tree t = new Tree();
		t.makeTree(arr);
		t.searchBTree(t.root, 2);
		
	}

}
