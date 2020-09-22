package test.graph;

import java.util.LinkedList;
import java.util.Queue;

public class Graph_BFS {

	public static void bfs(int[][] a, boolean[] c, int v) { 
		Queue<Integer> q = new LinkedList<>();
		int n = a.length - 1;
		q.add(v);
		c[v] = true;
		while (!q.isEmpty()) {
			v = q.poll(); System.out.print(v + " "); 
			for (int i = 1; i <= n; i++) {
				if (a[v][i] == 1 && !c[i]) {
					q.add(i); c[i] = true; 
				}
			} 
		}
	}
	
	public static void main(String[] args) {
		//가로 내꺼에서 나가는 방향
		int[][] data = {{0,1,0,0,1,0},
						{1,0,1,1,1,0},
						{0,1,0,1,0,0},
						{0,1,1,0,1,1},
						{1,1,0,1,0,0},
						{0,0,0,1,0,0}};
		
		//행에대한 갯
		boolean[] c1 = new boolean[data.length];
		
		bfs(data, c1, 0);
	}
	
}
