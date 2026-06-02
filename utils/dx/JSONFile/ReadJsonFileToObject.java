package sp.utils.dx.JSONFile;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import com.google.gson.Gson;

public class ReadJsonFileToObject {
    public static void main(String[] args) throws FileNotFoundException {
    	 
        // FileReader 생성
        Reader reader = new FileReader("c:\\code\\lecture.json");
 
        // Json 파일 읽어서, Lecture 객체로 변환
        Gson gson = new Gson();
        Lecture lecture = gson.fromJson(reader, Lecture.class);
 
        // Lecture 객체 출력
        // Lecture [id=1, students=[{id=123.0, name=Tom}, {id=124.0, name=Jerry}], subject={name=Java, professor=Anna}]
        System.out.println(lecture);
    }
}
