import java.util.LinkedList;
import java.util.Queue;

class Pairs{
	int x;
	int y;
	
	Pairs(int x,int y){
		this.x = x;
		this.y = y;
	}
}

class LandBFS {
	static int[][] arr;
	static int[][] check;
	static int[] dx= {0,0,1,-1,1,-1,1,-1};
	static int[] dy= {-1,1,0,0,-1,1,1,-1};
	static int w1;
	static int h1;
	
	public void bfs(int x, int y, int count) {
		Queue<Pairs> queue = new LinkedList<Pairs>();
		queue.add(new Pairs(x,y));
		check[x][y] = count;
		while(!queue.isEmpty()){
			Pairs p = queue.remove();
			x = p.x;
			y = p.y;
			
			for(int i=0;i<dx.length;i++) {
				int nx = x+ dx[i];
				int ny = y+ dy[i];
				
				if(0 <= nx && nx <h1 && 0<=ny &&ny<w1) {
					if(arr[nx][ny] == 1 && check[nx][ny] == 0)
						bfs(nx,ny,count);
				}
			}
		}	
	}
	
    public int solution(int [][]data, int h, int w){
    	int count = 0;
    	
    	arr = data;
    	check = new int[h][w];
    	h1= h;
    	w1 = w;
    	
    	for(int i=0;i<h;i++) { 
    		for(int j=0;j<w;j++) {
    			if(arr[i][j] ==1 && check[i][j]==0)
    				bfs(i,j,++count);
    		}
    	}
    		
    	
        
        return count;
    }
    
}

public class C_LandCount_BFS {

	public static void main(String[] args) {
//		sample 1
//		int [][]data = {{0,0}};
//		int h = 1;
//		int w = 2;       // result : 0
		
//		sample 2
		int [][]data = {{1,1}};
		int h = 1;
		int w = 2;       // result : 1
		
//		sample 3
//		int [][]data = {{1,0,1,0,1},
//						{0,0,0,0,0},
//						{1,0,1,0,1},
//						{0,0,0,0,0}, 
//						{1,0,1,1,1}};
//		int h = 5;
//		int w = 5;        // result : 8
		
	
//		sample 4
//		int [][]data = {{0,0,1,0,1,1},
//						{1,0,1,1,0,1},
//						{0,0,1,0,1,1},
//						{1,1,1,1,1,1},
//						{0,0,1,0,0,0},
//						{0,1,1,0,1,1},
//						{1,0,1,0,0,1},
//						{0,1,1,0,1,1}};
//		int h = 8;
//		int w = 6;        // result : 3
		
		
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
		
		LandBFS s = new LandBFS();
        int count = s.solution(data, h, w);
        System.out.println(count);
                
    }

}
