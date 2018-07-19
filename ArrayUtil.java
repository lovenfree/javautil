package sp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class ArrayUtil {
	//중복 허용, 저장 순서 유
	public static void main(String[] args) throws IOException {
		
		ArrayList<Exam> al = new ArrayList<>();
		BufferedReader in = new BufferedReader(new FileReader("exam_result.txt"));
		String strLine;
		
		while((strLine=in.readLine())!=null) {
			String words[] = strLine.split(" ");
			Exam ex = new Exam(words[0], Integer.parseInt(words[1]),Integer.parseInt(words[2]));
			al.add(ex);
		}
		
		in.close();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String strInput = br.readLine();
			
			if(strInput.equals("print")) {
				Collections.sort(al, (e1, e2) ->e1.getStrStudent().compareTo(e2.getStrStudent()));
			

			}else if(strInput.equals("science")) {
				Collections.sort(al, (e1,e2)->e1.getScience()-e1.getScience());
			}else if (strInput.equals("history")) {
				Collections.sort(al, (e1,e2)->e2.getHistory()-e1.getHistory());
			}else if(strInput.equals("quit")) {
				break;
			}
		}
	}

}

class Exam{
	private String strStudent;
	private int Science;
	private int History;
	
	  Exam(String str, int s, int h) {
	        strStudent = str;
	        Science = s;
	        History = h;
	    }
	       public String getStrStudent() {
	             return strStudent;
	       }
	       public void setStrStudent(String strStudent) {
	             this.strStudent = strStudent;
	       }
	       public int getScience() {
	             return Science;
	       }
	       public void setScience(int science) {
	             Science = science;
	       }
	       public int getHistory() {
	             return History;
	       }
	       public void setHistory(int history) {
	             History = history;
	       }



	
	
}
