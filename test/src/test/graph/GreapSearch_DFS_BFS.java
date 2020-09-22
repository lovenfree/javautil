package test.graph;

import java.util.LinkedList;
import java.util.Stack;

import test.Queue.Queue;

class Graph{
	class Node{
		int data;
		//인접한 노드들을 저장 
		LinkedList<Node> adjacent;
		boolean marked; // 방문했는지마킹 
		
		Node(int data){
			this.data =data;
			this.marked = false;
			//linked list준비  
			adjacent = new LinkedList<Node>();
		}
	}
	
	//노드들을 저장할 배열 
	Node[] nodes;
	Graph(int size){
		nodes = new Node[size];
		for(int i=0;i<size;i++) {
			nodes[i] = new Node(i);
		}
	}
	
	//두노드의 관계 저
	void addEdge(int i1, int i2) {
		//data 가 index와 같으니까 
		Node n1 = nodes[i1];
		Node n2 = nodes[i2];
		
		if(!n1.adjacent.contains(n2)) {
			n1.adjacent.add(n2);
		}
		
		if(!n2.adjacent.contains(n1)) {
			n2.adjacent.add(n1);
		} 
		
	}
	

	
	void dfs() {
		dfs(0);
	}
	
	void dfs(int index) {
		Node root = nodes[index];
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		root.marked = true;
		while(!stack.isEmpty()) {
			//stack 에서 데이터를 하나 가져옴 
			Node r = stack.pop();
			//가져온 노드의 자식들을 스택에 추가
			for(Node n: r.adjacent) {
				if(n.marked == false) {
					n.marked = true;
					stack.push(n);
				}	
			}
			//출력 
			visit(r);
		}
	}
	
	void bfs() {
		bfs(0);
	}
	
	void bfs(int index) {
		Node root = nodes[index];
		Queue<Node> queue = new Queue<Node>();
		queue.add(root);
		root.marked = true;
		
		while(!queue.isEmpty()) {
			//stack 에서 데이터를 하나 가져옴 
			Node r = queue.remove();
			//가져온 노드의 자식들을 스택에 추가
			for(Node n: r.adjacent) {
				if(n.marked == false) {
					n.marked = true;
					queue.add(n);
				}	
			}
			//출력 
			visit(r);
		}
		
	}
	
	
	//재귀호출을 이용한 dfs
	//Linked list 가 node의 주소를 가지기 때문에 인자가 Node
	void dfsR(Node r) {
		if(r == null) return;
		r.marked = true;
		visit(r);
		for(Node n: r.adjacent) {
			if(n.marked ==false) {
				dfsR(n);
			}
		}
		
		
	}
	
	void dfsR(int idx) {
		Node r = nodes[idx];
		dfsR(r);
	}
	
	void dfsR() {
		dfsR(0);
	}
	
	void visit(Node n) {
		System.out.print(n.data + " ");
	}
}
public class GreapSearch_DFS_BFS {
/*
	  0
	/
	1--3  7
	| /|\ / 
	|/ | 5
	2--4  \
	  *    6-8
	  */
	public static void main(String[] args) {
		
		Graph g = new Graph(9);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 4);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(3, 5);
		g.addEdge(5, 6);
		g.addEdge(5, 7);
		g.addEdge(6, 8);
		
		//g.bfs();		
		//g.dfs();
		g.dfsR();
		
		
	}
}
