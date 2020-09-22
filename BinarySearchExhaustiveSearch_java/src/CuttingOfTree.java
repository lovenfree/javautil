import java.util.Arrays;

class Solution1 {
	
	static long sumofTrees(int height, int N, int []trees) {
		long sum = 0;
		for(int i=0;i<trees.length;i++) {
			if(trees[i] >= height) {
				sum = sum + trees[i]-height;
			}
		}
		return sum;
	}
	
	
	public int solution(int N, int M, int[] trees) {
        int answer = 0;
       
//        TODO
        
        Arrays.sort(trees);
        
        long sum = 0;
        
        int left =1;
        int right = trees[N-1];
        int cutter;
 
        while(left < right) {
        	cutter = (left+right)/2; //(mid)
        	sum = sumofTrees(cutter,N,trees);
        	System.out.println("cutter : " + cutter);
        	
        	if(sum < M) {
        		right = cutter;
        	}else {
        		answer = cutter;
        		left = cutter+1;
        	}
        }
       
        return answer;
    }
}


public class CuttingOfTree {
	
	public static void main(String[] args){ 
//		int N = 4; 
//		int M = 7; 
//		int []trees = new int[]{12,16,22,17};
		
//		int N = 5;
//		int M = 10;
//		int []trees = new int[]{20,17,13,12,15};	
		
		int N = 10; 
		int M = 20; 
		int []trees = new int[]{10,12,14,17,18,11,23,9,13,3};	
		
		Solution1 s = new Solution1();
		int res = s.solution(N, M, trees);
		System.out.println(res);
			
		
	}
}

		
		
