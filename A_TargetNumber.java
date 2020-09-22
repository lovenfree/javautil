

class TargetNumber{
	static int[] arr;
	static int N;
	static int targetValue;
	static int cnt;
	
	public int solution(int []numbers, int target){ 
		int result = 0;
		
		targetValue =  target;
		arr = numbers;
		N = numbers.length;
		
		dfs(0,0);
		
		result = cnt;
		
		return result;
	}
	
	
	
	public static void dfs(int idx, int sum) {
		if(idx ==N && targetValue == sum) {
			cnt++;
			return;
		}
		if(idx == N) {
			return;
		}
		
		dfs(idx+1, sum+arr[idx]);
		dfs(idx+1, sum-arr[idx]);
	

	}
}

public class A_TargetNumber {
	

	
	
	public static void main(String[] args) {
		int []numbers = {1,1,1,1,1};
		int	target = 3;     // 5

//		int []numbers = {1,6,2,3,-2,-1,-9};
//		int target = 0;     // 10

		TargetNumber tn = new TargetNumber();
		int res = tn.solution(numbers, target);
		System.out.println(res);
	}

}
