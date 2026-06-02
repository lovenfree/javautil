package sp.utils.dx.JSONFile;

import java.util.List;
import java.util.Map;

public class Lecture {
    private int id;
    private List<Map<String, Object>> students;
    private Map<String, Object> subject;
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public List<Map<String, Object>> getStudents() {
        return students;
    }
 
    public void setStudents(List<Map<String, Object>> students) {
        this.students = students;
    }
 
    public Map<String, Object> getSubject() {
        return subject;
    }
 
    public void setSubject(Map<String, Object> subject) {
        this.subject = subject;
    }
 
    @Override
    public String toString() {
        return "Lecture [id=" + id + ", students=" + students + ", subject=" + subject + "]";
    }
 
}




// {
//     "id": 1,
//     "students": [
//       {
//         "id": 123,
//         "name": "Tom"
//       },
//       {
//         "id": 124,
//         "name": "Jerry"
//       }
//     ],
//     "subject": {
//       "name": "Java",
//       "professor": "Anna"
//     }
//   }