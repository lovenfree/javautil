package programmers;

import java.util.HashSet;
import java.util.Set;

//방문길
public class Day12 {
	
//	mine
//    public static int solution(String dirs) {
//        int answer = 0;
//        
//        int[][] orgmap = new int[10][10];
//        int[][] pathmap =new int[10][10];
//        
//        //U,D,R,L
//        int[][] path = {{-1,0},{1,0},{0,-1},{0,1}};
//
//        char[] paths = dirs.toCharArray();
//        int[] currentPath = {5,5};
//    	int pathIdx = -1;
//        for(char c: paths) {
//        	if(c == 'U') {
//        		pathIdx =0;
//        	}else if(c == 'D') {
//         		pathIdx =1;
//        	}else if(c == 'R') {
//         		pathIdx =2;
//        	}else if(c == 'L') {
//         		pathIdx =3;
//        	}
//        	
//    		
//    		int[] dir  = path[pathIdx];
//    		int[] nextPath = {currentPath[0]+dir[0],currentPath[1]+dir[1]};
//    		int x  = nextPath[0];
//    		int y  = nextPath[1];
//    		
//    		if(pathmap[x][y] == 0) {
//    			System.out.println(""+	pathmap[x][y]);
//    			pathmap[x][y]  = -1;
//    			answer++;
//    		}else {
//    			System.out.println(""+	pathmap[x][y]);
//    		}
//    		currentPath = nextPath;
//        }
//        
//        return answer;
//    }
	
	
	public static int solution(String dirs) {
	  int answer = 0;

	  
	  int[] dx = {-1,1,0,0}; //UDLR
	  int[] dy = {0,0,-1,1};
	  
	  int x = 5;
	  int y =5;
	  
	  Set<String> road = new HashSet<>();
	  
	  
	  for(int i=0;i<dirs.length();i++) {
		  int tmpx = 0;
		  int tmpy =0;
		  
		  switch(dirs.charAt(i)) {
		  case 'U':
			  tmpx = x+ dx[0];
			  tmpy = y+ dy[0];
			  break;
		  case 'D':
			  tmpx = x+ dx[1];
			  tmpy = y+ dy[1];			  
		  break;
		  case 'L':
			  tmpx = x+ dx[2];
			  tmpy = y+ dy[2];
		  break;
		  case 'R':
			  tmpx = x+ dx[3];
			  tmpy = y+ dy[3];
		  break;
		  }
		  if(tmpx < 0 || tmpy <0 ||tmpx > 10 || tmpy >10 )
			  continue;
		  road.add(x+","+y+","+tmpx+","+tmpy);
		  road.add(tmpx+","+tmpy+","+x+","+y);
		  
		  x = tmpx;
		  y = tmpy;
		  
	  }
	  
	  answer = road.size()/2;
	  return answer;
	}
    
    public static void main(String[] args) {
//    	String dirs ="ULURRDLLU";
    	String dirs = "LULLLLLLU";
    	int answer = solution(dirs);
        System.out.println(answer);
    }
	
	

}
