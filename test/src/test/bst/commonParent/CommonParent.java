package test.bst.commonParent;

import java.util.HashMap;

//이진트리에서 주어진 두개 노드의 첫번째 공통된 부모 찾기
//#1 두 노드의 길이 맞추기 (두 노드의 길이가 다를 경우 한 레벨씩 올라가다 보면 어긋 날 수 있다)
//방법 : 노드에서 루트까지의 길이를 잰다  -> 둘 사이의 길이 차 계산  -> 긴 아이가 차이만큼 먼저 올라간다 

class Tree{
	static class Node{
		int data;
		Node left;
		Node right;
		Node parent;
		Node(int data){
			this.data = data;
		}
	}
	
	Node root;
	//노드 검색을 쉽게 한다. (값으로 노드를 찾아 올수 있다.)
	HashMap<Integer, Node> rootMap;
	Tree(int size){
		rootMap = new HashMap<Integer, Node>();
		root = makeBST(0,size-1,null);
	}
	
	Node makeBST(int start, int end, Node parent) {
		if(start > end) return null;
		int mid = (start+end)/2;
		Node node = new Node(mid);
		node.left = makeBST(start,mid-1, node);
		node.right = makeBST(mid+1,end, node);
		node.parent = parent;
		rootMap.put(mid, node);
		return node;
	}
	
	Node getNode(int data) {
		return rootMap.get(data);
	}
	
	
	//Solution #1
	Node commonAncestor(int d1, int d2) {
		Node p = getNode(d1);
		Node q = getNode(d2);
		
		int diff = depth(p)- depth(q);
		
		Node first = diff >0?q:p; // 짧은거 
		Node second = diff > 0? p:q; //긴거
		
		second = goUpBy(second, Math.abs(diff)); //긴쪽을 차이만큼 올라가서 양쪽의 길이를 같게  
		
		while(first!=second && first!=null &second !=null) {
			first = first.parent;
			second = second.parent;
		}
		
		return first==null || second==null? null: first;
	}
	
	
	Node goUpBy(Node node, int diff) {
		while(diff >0 && node!=null) {
			node = node.parent;
			diff --;
		}
		
		return node;
	}
	
	int depth(Node node) {
		int depth = 0;
		while(node!=null) {
			node = node.parent;
			depth++;
		}
		return depth;
		
	}
	
	
	//Solution #2
	//인자로 받은 node 가 root의 자손인지 확인 
	boolean covers(Node root, Node node) {
		if(root == null) return false;
		if(root == node) return true;
		return covers(root.left,node) || covers(root.right,node);
		
	}
	
	Node commonAncestor2(int d1, int d2) {
		Node p = getNode(d1);
		Node q = getNode(d2);
		if(!covers(root,p) || !covers(root,q)) { //시작 노 밑에 잇는 값이 아니면  
			return null;
		}else if(covers(p,q)) {
			return p;
		}else if(covers(q,p)) {
			return q;
		}
		Node sibling = getSibling(p); //형제 노드를 가져옴 
		Node parent = p.parent; //부모노드를 가져
		
		while(!covers(sibling,q)) {
			sibling = getSibling(parent);
			parent = parent.parent;
		}
		
		return parent;
	}
	
	Node getSibling(Node node) {
		if(node ==null || node.parent == null) {
			return null;
		}
		Node parent = node.parent;
		return parent.left == node? parent.right:parent.left;
	}
	
	//Solution#3 부모노드가 없는 경우
	// root에서 부터 내려가면서 	
	Node commonAncestor3(int d1, int d2) {
		Node p = getNode(d1);
		Node q = getNode(d2);
		if(!covers(root,p)||!covers(root,q)) {
			return null;
		}
		//재귀 함수 호
		return ancestorHelper(root, p,q);
		
	}
	
	
	Node ancestorHelper(Node root, Node p ,Node q) {
		if(root == null || root == q || root == p) {
			return root;
		}
		boolean pIsOnLeft = covers(root.left, p);
		boolean qIsOnLeft = covers(root.left, q);
		if(pIsOnLeft != qIsOnLeft) { //둘이 갈라진다 첫번째 조상 
			return root;
		}
		//둘이 같은 방향에 있지 않으면 왼쪽인지 오른 쪽인지 판단
		Node childSide = pIsOnLeft ? root.left:root.right;
		return ancestorHelper(childSide, p,q);
	}
	
	//Solution #4 최적화 시키기
	//postOrder (L,R,root) 방식으로 딱 한번씩만 검사 (반환받은 값으
	
	Node commonAncestor4(int d1, int d2) {
		Node p = getNode(d1);
		Node q = getNode(d2);
	
		return commonAncestor4(root, p,q);
		
	}
	
	Node commonAncestor4(Node root, Node p, Node q) {
		if(root == null) return null;
		if( root ==p && root ==q) return root;
		Node x = commonAncestor4(root.left, p,q);
		if(x!= null && x!=p && x!=q) {
			return x; // 공통 부모를 찾음 
		}
		Node y = commonAncestor4(root.right, p,q);
		if(y!= null && y!=p && y!=q) {
			return y; // 공통 부모를 찾음 
		}
		 
		if(x!=null && y!= null) {
			return root;
		}else if(root == p || root == q) { //찾는 노드 일 경우 
			return root;
		}else {
			return x == null ? y:x; //null 일 수도 아닐 수도 있는것 위에 전달 
		}
	}
	
	//Solution #5 최적화 시키기
	// 정확한 결과 가져오기 
	class Result{
		Node node;
		boolean isAncestor;
		Result(Node n, boolean isAnc){
			this.node = n;
			this.isAncestor = isAnc;
		}
	}
	
	Node commonAncestor5(int d1, int d2) {
		Node p = getNode(d1);
		Node q = getNode(d2);
	
		Result r =  commonAncestor5(root, p,q);
		if(r.isAncestor) {
			return r.node;
		}
		return null;
		
	}
	
	Result commonAncestor5(Node root, Node p, Node q) {
		if(root == null) return new Result(null, false);
		if( root ==p && root ==q) return  new Result(root, true);
		Result x = commonAncestor5(root.left, p,q);
		if(x.isAncestor) {
			return x; // 공통 부모를 찾음 
		}
		Result y = commonAncestor5(root.right, p,q);
		if(y.isAncestor) {
			return y; // 공통 부모를 찾음 
		}
		 
		if(x!=null && y!= null) { //공통 부모 찾음 
			return new Result(root, true);
		}else if(root == p || root == q) { //찾는 노드 일 경우 
			boolean isAncestor = x.node!=null || y.node!=null;
			return new Result(root, isAncestor);
		}else {
			return new Result(x.node!=null ? x.node:y.node, false);//null 일 수도 아닐 수도 있는것 위에 전달 
		}
	}
	
	
}
public class CommonParent {
	public static void main(String[] args) {
		Tree t = new Tree(10);
		//Solution #1
		Tree.Node fa = t.commonAncestor5(6, 8);
		System.out.println("The First commin ancestor is " + fa.data);
	}
}
