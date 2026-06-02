import java.util.Arrays;

class Solution {
	public int solution(int[] budgets, int M) {
        int answer = 0;
       
//        TODO
        
        Arrays.sort(budgets);
        int left =0;
        int mid;
        int right = budgets[budgets.length-1];
        
        while(left<=right) {
        	long sum = 0;
        	mid = (left+right)/2;
        	
        	for(int b: budgets) {
        		if(b>=mid) {
        			sum+=mid;
        		}else {
        			sum+= b;
        		}
        	}
        	
        	if(sum > M) {
        		right = mid-1;
        	}else {
        		answer = mid;
        		left = mid+1;
        	}
        }
        return answer;
    }
}

public class Budget {

	public static void main(String[] args) {
		int []budgets = {120, 110, 140, 150};
		int M = 485;     //  # 127

//		int []budgets = {14935, 79863, 33424, 62780, 97395, 52901, 22944, 77340, 10023, 87933, 90534, 44412, 12556, 81150};
//		int M = 387690;   //  # 32723
		
		Solution s = new Solution();
		int res = s.solution(budgets, M);
		System.out.println(res);

	}

}
