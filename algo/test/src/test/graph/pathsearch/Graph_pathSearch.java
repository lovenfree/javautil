package test.graph.pathsearch;

import java.util.LinkedList;


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
	
	void initMarks() {
		for(Node n:nodes) {
			n.marked = false;
		}
	}
	
	boolean search(int n1, int n2) {
		return search(nodes[n1], nodes[n2]);
	}
	
	//bfs
	boolean search(Node start, Node end) {
		initMarks();
		LinkedList<Node> q = new LinkedList<Node>();
		q.add(start);
		
		while(!q.isEmpty()) {
			Node root = q.removeFirst();
			if(root == end) {
				return true;
			}
			
			//node 의 자식 노드들 만큼 돌면서 queue 에 없던 애 들 추
			for(Node n: root.adjacent) {
				if(!n.marked) {
					n.marked = true;
					q.add(n);
				}
			}
		}
		return false;
	}
}
	
	
//graph에서 두 노드 사이의 경로 구하기 
public class Graph_pathSearch {
	public static void main(String[] args){
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
		
		System.out.println(g.search(1,8));
	}

}
