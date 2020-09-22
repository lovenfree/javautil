import java.util.Stack;

class LandDFS {
	static int[][] arr;
	static int[][] check;
	static int[] dx = {-1,1,0,0,-1,1,-1,1};
	static int[] dy = {0,0,-1,1,-1,1,1,-1};
	static int w1;
	static int h1;
	
	private static void dfs(int x, int y, int count) {
		
		Stack<Pairs> stack = new Stack<>();
		Pairs p = new Pairs(x,y);
		boolean flag = false;
		stack.push(p);
		check[x][y] = count;
		
		while(!stack.isEmpty()) {
			Pairs peek = stack.peek();
			flag = false;
			x = peek.x;
			y = peek.y;
			
			for(int i=0; i<dx.length;i++) {
				int nx = x+ dx[i];
				int ny = y+ dy[i];
				if(0 <= nx && nx < h1 && 0 <= ny && ny < w1) {
					if(arr[nx][ny] == 1 && check[nx][ny] == 0) {
						stack.push(new Pairs(nx, ny));
						check[nx][ny] = count;
						flag = true;
						break;
					}
				}
			}
			if(!flag) {
				stack.pop();
			}
			
			
			
		}
	}
	
    public int solution(int [][]data, int h, int w){
    	int count = 0;
    	
        
        return count;
    }
    
}

public class D_LandCount_DFS {

	public static void main(String[] args) {
//		sample 1
//		int [][]data = {{0,0}};
//		int h = 1;
//		int w = 2;       // result : 0
		
//		sample 2
//		int [][]data = {{1,1}};
//		int h = 1;
//		int w = 2;       // result : 1
		
//		sample 3
//		int [][]data = {{1,0,1,0,1},
//						{0,0,0,0,0},
//						{1,0,1,0,1},
//						{0,0,0,0,0},
//						{1,0,1,1,1}};
//		int h = 5;
//		int w = 5;        // result : 8
//		
	
//		sample 4
		int [][]data = {{0,0,1,0,1,1},
						{1,0,1,1,0,1},
						{0,0,1,0,1,1},
						{1,1,1,1,1,1},
						{0,0,1,0,0,0},
						{0,1,1,0,1,1},
						{1,0,1,0,0,1},
						{0,1,1,0,1,1}};
		int h = 8;
		int w = 6;        // result : 3
		
		
//		sample 5
//		int [][]data = {{0,1,0,0,1,0},
//						{1,0,0,0,0,1},
//						{0,0,1,0,1,1},
//						{1,1,1,0,1,1},
//						{0,0,1,0,0,0},
//						{0,0,1,0,1,1},
//						{1,0,1,0,0,1},
//						{0,1,1,0,1,1}};
//		int h = 8;
//		int w = 6;        // result : 4
		
		LandDFS s = new LandDFS();
        int count = s.solution(data, h, w);
        System.out.println(count);
                
    }

}
