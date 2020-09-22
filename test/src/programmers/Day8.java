package programmers;

import java.util.HashSet;


//https://bcp0109.tistory.com/14 이
public class Day8 {
	static HashSet<Integer> num = new HashSet();
	
    public static int solution(String numbers) {
        int answer = 0;
        
        char[] numArray = numbers.toCharArray();
        int[] nArr = new int[numArray.length];
        
        int idx=0;
        for(char c: numArray) {
        	nArr[idx++] = c-'0';
        }
        
        
        for (int i=1;i<=numArray.length;i++) {
    	   perm(nArr, 0,numArray.length, i);
        }
        
        
        
        // 소수를 구한다. 
        for(int i: num) {
        	System.out.println("###"+i);
        	if(isPrime(i))
        		answer++;
        }
        
        
        return answer;
    }
    
    public static boolean isPrime(int n) {
    	boolean result = true;
    	if(n <2) {
    		return false;
    	}
    	
    	for (int i=2; i<n; i++) {
            if (n % i == 0) {
            	result = false;
                break;
            }
        }
    	
    	return result;
    }
    
    
    //depth : 어떤 깊이에서 교환 작
    //n: 총 배열안에 들어있는 숫자 : 고정값 
    //k : 순열에서 몇개를 뽑아 내서 순열을 만들 것인
    public static void perm(int[] arr, int depth, int n, int k) {
    	if(depth ==k) {
    		int n1 = print(arr,k);
    		//System.out.println("###");
    		num.add(n1);
    		return;
    		
    	}
    	
    	
    	for(int i=depth;i<n;i++) {
    		swap(arr,i,depth);
    		perm(arr, depth+1,n,k);
    		swap(arr,i,depth);		
    	}
    	
    }
    
    


	//배열의 특정위치 교환 
    public static void swap(int[] arr, int i, int j) { 
    	int temp = arr[i]; 
    	arr[i] = arr[j]; 
    	arr[j] = temp; 
    }
    
    
    public static int print(int[] arr, int k) {
    	String s = "";
    	for (int i = 0; i < k; i++) {
    		if (i == k - 1) {
    			s+=String.valueOf(arr[i]);
    			System.out.println(arr[i]); 
    		}
    		else {
    			s+=String.valueOf(arr[i]);
    			System.out.print(arr[i] + ","); 
    		}
    	} 
    	return Integer.parseInt(s);
    }

	public static void main(String[] args){ 

	//	String data="17";
		String data="011";
		
		int answer = solution(data);
		System.out.println(answer);
	}


}
