package src.com.time.t2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class main {

    public static void main(String[] args) throws Exception {
        Map<String, String> dict = loadDictionary("DICTIONARY.TXT");
        Set<String> excluded = loadExcluded("STOPWORD.TXT");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(transform(line, dict, excluded));
        }
    }

    static String transform(String sentence, Map<String, String> dict, Set<String> excluded) {
        StringBuilder sb = new StringBuilder();
        for (String token : sentence.trim().split("\\s+")) {
            if (token.isEmpty()) continue;
            String value = dict.get(token.toLowerCase());
            if (value == null) continue;
            if (excluded.contains(value)) continue;
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

    static Set<String> loadExcluded(String path) throws Exception {
        Set<String> set = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) set.add(line);
            }
        }
        return set;
    }
}

// tech: t1 위에 HashSet 멤버십 필터 1단 추가. 룩업값이 제외집합에 속하면 드롭.
//       나머지는 동일 (라인 토큰화 -> 맵 룩업 -> 공백조인 무한루프 출력).
