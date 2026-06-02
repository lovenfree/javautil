package programmers;


public class Day5_ {
	 public static int solution(int n, int a, int b)
	    {
	        int answer = 0;

	        //make
	        while (a!=b) {
	        	if(a%2==0) {
	        		a=a/2;
	        	}else {
	        		a=a/2+1;
	        	}
	        	
	        	
	        	if(b%2==0) {
	        		b=b/2;
	        	}else {
	        		b=b/2+1;
	        	}
	        	
	        	answer+=1;
	       	// System.out.println("a:" +a +", b:" +b+" an: "+answer  );
	        }
	        
	        return answer;
	    }
	 
	 public static void main(String[] args) {
		 int N = 8;
		 int A = 7;
		 int B =4;
		 
		 int answer = solution(N,A,B);
		 System.out.println("answer: "+ answer);
	 }
}
