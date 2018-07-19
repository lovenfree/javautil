package sp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class HashUtil {
	//키는 중복될 수 없지만 값은 중복 가
	public static void main(String[] args) throws IOException {
		HashMap<String, Grade> m = new HashMap();
		
		BufferedReader in = new BufferedReader(new FileReader("Grade.txt"));
		String s;
		
		while((s=in.readLine())!=null) {
			String[] words = s.split("\t");
			String key = words[0];
			
			if(!m.containsKey(key)) {
				Grade g = new Grade(words[1], Integer.parseInt(words[3]));
				m.put(key, g);
				
				
			}else{
				Grade g = m.get(key);
				g.addPoint(Integer.parseInt(words[3]));
				m.put(key, g);
			}
		}
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		 String strInput;
         while (true)
         {
                strInput = br.readLine();
                if (strInput.equals("print"))
                {
                       for (String key : m.keySet()) {
                             double avg = m.get(key).getPoint() / 2.;
                             String str = key + "\t" + m.get(key).getName() + "\t" + m.get(key).getPoint() + "\t-->\t" + avg;
                             System.out.println(str);
                       }
                }
                else if(strInput.equals("num"))
                {
                       TreeMap<String, Grade> tm = new TreeMap<String, Grade>(m);
                       Iterator<String> iteratorKey = tm.keySet().iterator();
                       while (iteratorKey.hasNext()) {
                             String key = iteratorKey.next();
                             double avg = m.get(key).getPoint() / 2.;
                             String str = key + "\t" + m.get(key).getName() + "\t" + m.get(key).getPoint() + "\t-->\t" + avg;
                             System.out.println(str);
                       }
                }
                else if(strInput.equals("grade"))
                {
                       Iterator it = sortByValue(m).iterator();
                       while (it.hasNext())
                       {
                             String key = (String)it.next();
                             double avg = m.get(key).getPoint() / 2.;
                             String str = key + "\t" + m.get(key).getName() + "\t" + m.get(key).getPoint() + "\t-->\t" + avg;
                             System.out.println(str);                             
                       }
                }
                else if(strInput.equals("quit"))
                {
                       break;
                }                   


         }
		
		
	}
         
         
         public static ArrayList sortByValue(final HashMap map) {
             ArrayList<Integer> list = new ArrayList();
             list.addAll(map.keySet());
             
             Collections.sort(list, new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                           Object v1 = ((Grade) map.get(o1)).getPoint();
                           Object v2 = ((Grade) map.get(o2)).getPoint();
                           
                           return ((Comparable)v2).compareTo(v1);
                    }
             });
             //Collections.reverse(list);
             return list;
       }




}

class Grade
{
    private String strName;
    private int nPoint;
 
    public Grade(String str, int n)
    {
        this.strName = str;
        nPoint = n;
    }
 
    public String getName()
    {
        return strName;
    }
 
    public void setName(String str)
    {
        this.strName = str;
    }
 
    public int getPoint()
    {
        return nPoint;
    }
 
    public void setPoint(int n)
    {
        this.nPoint = n;
    }
 
    public void addPoint(int n)
    {
        this.nPoint += n;
    }
}


