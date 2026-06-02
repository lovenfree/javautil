package src.com.time.t1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class main {

    public static void main(String[] args) throws Exception {
        Map<String, String> dict = loadDictionary("DICTIONARY.TXT");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(transform(line, dict));
        }
    }

    static String transform(String sentence, Map<String, String> dict) {
        StringBuilder sb = new StringBuilder();
        for (String token : sentence.trim().split("\\s+")) {
            if (token.isEmpty()) continue;
            String value = dict.get(token.toLowerCase());
            if (value == null) continue;
            if (sb.length() > 0) sb.append(' ');
            sb.append(value);
        }
        return sb.toString();
    }

    static Map<String, String> loadDictionary(String path) throws Exception {
        Map<String, String> dict = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                int i = line.indexOf('#');
                if (i < 0) continue;
                dict.put(line.substring(0, i), line.substring(i + 1).trim());
            }
        }
        return dict;
    }
}

// tech: System.in 라인 스트림 화이트스페이스 토큰화 -> HashMap 키 룩업(소문자 정규화)
//       -> 매핑값 공백조인 후 무한루프 표준출력. 파일(#구분) 1회 로드.
