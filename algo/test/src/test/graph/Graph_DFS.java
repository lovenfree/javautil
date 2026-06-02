package test.graph;

import java.util.Arrays;
import java.util.Stack;

public class Graph_DFS {
	
	static int[][] a;
	static boolean[] c;
	

	////a: 순회해야 하는 그래프  c: 돌았는지 체크 (노드의 갯수 만큼 체크 한지 안한지 표시)  v: 시작하는 위치 
	public static void dfs(int[][] a, boolean[] c, int v) {
		int n = a.length - 1;
		c[v] = true;
		System.out.print(v + " ");
		for (int i = 1; i <= n; i++) { 
			//a[v][i] == 1 : 연결이 되어 있다. 
			//!c[i] : 아직 방문을 안했다. 
			if (a[v][i] == 1 && !c[i]) {
				dfs(a, c, i); 
			}
		}	
	}
	
	public static void dfs(int v) {
		int n = a.length - 1;
		c[v] = true;
		System.out.print(v + " ");
		for (int i = 1; i <= n; i++) { 
			//a[v][i] == 1 : 연결이 되어 있다. 
			//!c[i] : 아직 방문을 안했다. 
			if (a[v][i] == 1 && !c[i]) {
				dfs(a, c, i); 
			}
		}	
	}
	
	
	public static void dfs(int[][] a, boolean[] c, int v, boolean flag) { 
		Stack<Integer> stack = new Stack<>();
		int n = a.length - 1;
		stack.push(v);
		c[v] = true; System.out.print(v + " "); 
		while (!stack.isEmpty()) {
			int t = stack.peek();
			flag = false;
			for (int i = 1; i <= n; i++) {
				if (a[t][i] == 1 && !c[i]) { 
					stack.push(i);
					System.out.print(i + " "); c[i] = true;
					flag = true;
					break;
				}
			}
			if (!flag) {
				stack.pop(); 
				}
		} 
	}
	
	
	public static void solution(int[][] input, int node) {
		c = new boolean[node];
		a = new int[node][node];
		
		for(int i=0;i<input.length;i++) {
			//양방향 
			a[input[i][0]-1][input[i][1]-1] =1;
			a[input[i][1]-1][input[i][0]-1] =1;
			
		}
		for(int i=0;i<a.length;i++) {
			System.out.println(Arrays.toString(a[i]));
			System.out.println();
			System.out.println();
			
			dfs(0);
		}
	}
	
	public static void main(String[] args) {
		//다양한 입력 값 
		int node =6;
		int[][] input = {
				{1,2},
				{1,5},
				{2,3},
				{2,4},
				{2,5},
				{3,4},
				{5,4},
				{4,6}};
		
		solution(input, node);
//		//가로 내꺼에서 나가는 방향
//		int[][] data = {{0,1,0,0,1,0},
//						{1,0,1,1,1,0},
//						{0,1,0,1,0,0},
//						{0,1,1,0,1,1},
//						{1,1,0,1,0,0},
//						{0,0,0,1,0,0}};
//		
//		//행에대한 갯
//		boolean[] c1 = new boolean[data.length];
//		
//		dfs(data, c1, 0);
	}
}
