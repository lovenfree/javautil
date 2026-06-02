class Solution2 {	
	public int solution(int N, int []num, int value) {
		int answer = 0;
		
//		TODO
		
		return answer;
    }
}


public class SumOfSubSet_loop {

	public static void main(String[] args) {
		int N = 7;
		int []num = new int[]{ 4, 2, 5, 1, 3, 9, 6 };
		int value = 6;                     // 4
		
//		int N = 10;
//		int []num = new int[]{-1, 3, -9, 6, 7, -6, 1, 5, 4, -2};
//		int value = 0;                      // 42 
		
//		int N = 13;
//		int []num = new int[]{-3,2,-6,-1,9,1, 4, 2, -5, 1, -3, 9, -6 };
//		int value = 6;                     // 330
		
		Solution2 s = new Solution2();
		int res = s.solution(N, num, value);
		System.out.println(res);
		
		
	}
}
