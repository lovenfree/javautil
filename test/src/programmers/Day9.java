package programmers;

import java.util.ArrayList;
import java.util.HashMap;

public class Day9 {
	
	
    public static boolean solution(String[] phone_book) {
        HashMap<Character, ArrayList<String>> map = new  HashMap<Character, ArrayList<String>>();
        
    	boolean answer = true;
        
        for(String s: phone_book) {
        	char key = s.charAt(0);
        	if(map.containsKey(key)) {
        		ArrayList list = map.get(key);
        		list.add(s);
        		map.put(key, list);
        	}else {
        		ArrayList list = new ArrayList();
        		list.add(s);
        		map.put(key, list);
        	}
        }
        
        for(String s: phone_book) {
        	char key = s.charAt(0);
        	if(map.containsKey(key)) {
        		ArrayList<String> list = map.get(key);
        		for(String s1 : list) {
        			if(!s1.equals(s) &&  s1.startsWith(s) ){
						return false;
        			}
        		}
        	}
        }
        
        
        return answer;
    }
    
	public static void main(String[] args){ 

//		String[] data= {"119","97674223","1195524421"};
		String[] data= {"123","456","789"};
//		String[] data= {"12","123","1235","567","88"};
		
		boolean answer = solution(data);
		System.out.println(answer);
	}


}
