class Solution3 {
	
	static int []numArray;
	static int cnt =0;
	static int n1 =0;
	static int v1 = 0;
	
	
	public static void subset(int k, int sum) {
		if(k==n1) {
			if(sum==v1) {
				cnt++;
				return;
			}
			return;
		}
		
		int sum1 = sum+numArray[k];
		subset(k+1, sum1);
		subset(k+1, sum);
		System.out.println();
	}
	
	
	public int solution(int n, int []num, int v) {
		int answer = 0;
		numArray = num;
		
		n1 = n;
		v1 = v;
		
		subset(0,0);
		
		if(v==0) {
			answer = cnt-1;
		}else {
			answer =cnt;
		}
//		TODO
		
		return answer;
    }
}



public class SumOfSubSet_recursive {

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
		
		Solution3 s = new Solution3();
		int res = s.solution(N, num, value);
		System.out.println(res);
		
	}

}

