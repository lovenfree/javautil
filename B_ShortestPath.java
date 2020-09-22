import java.util.LinkedList;
import java.util.Queue;

class Point{
	//dist : 거리 
	int row,col,dist;
	String path;
	public Point(int row, int col, int dist) {
		this.row = row;
		this.col =col;
		this.dist = dist;
	}
	
	@Override
	public String toString() {
		return "("+row+", "+ col +")"+dist;
	}
}

class Solution{
	public static int[][] map = null;
	public static int rows =0;
	public static int cols =0;
	public static int[] dx = {-1,1,0,0};
	public static int[] dy = {0,0,-1,1};
	
	
	public int solution(int[][] data) {
		int count = 0;
		map = data;
		rows = map.length;
		cols = map[0].length;
		
		count = bfs();
		return count;
	}
	
	public int bfs() {
		int ret = 0;
		
		int x=0; //현재 row
		int y =0; //현재 col
		int dist =0; //전체 이동한 거리 
		
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(y,x,dist));
		
		while(!queue.isEmpty()) {
			Point record = (Point) queue.poll();
			
			x = record.row;
			y = record.col;
			dist = record.dist;
			
			map[x][y] =0;
			
			if(x ==rows -1 && y ==cols -1) {
				ret = dist;
				break;
				
			}
			
			for(int i=0;i<4;i++) {
				int nx = x +dx[i]; 
				int ny = y +dy[i];
				if(nx >=0 && nx < rows && ny >=0 && ny < cols && map[nx][ny] == 1) {
					queue.add(new Point(nx, ny,dist+1));
				}
			}
			
		}
		return ret;
		
		
	}
}
class ShortestPath {    
    
    public int solution(int [][]data) {
    	int count = 0;        
    	Solution s = new Solution();
    	count = s.solution(data);
       
        return count;
    }   
}


public class B_ShortestPath {

	public static void main(String[] args) {
		int [][]road = new int[][] {            
                {1,1,1,1,1,1},
                {0,0,1,0,0,1},
                {1,1,1,0,1,1},
                {1,0,0,0,1,0},
                {1,1,1,0,1,0},
                {0,0,1,1,1,1}};
                
//              int [][]road = new int[][] {            
//              {1,1,1,0,0,1,1,1,1},
//              {0,0,1,1,1,1,1,1,1},
//              {0,0,1,1,1,0,0,0,1},
//              {0,0,1,0,1,0,0,0,1},
//              {0,0,1,0,1,1,0,0,1},
//              {0,0,1,0,0,1,1,0,1},
//              {0,0,1,0,0,0,1,1,1},
//              {0,0,1,1,1,1,1,1,1}}; 
                
       ShortestPath s = new ShortestPath();
       int result = s.solution(road);
       System.out.println(result);
      

	}

}