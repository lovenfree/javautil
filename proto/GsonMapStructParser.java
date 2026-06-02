package proto;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * [객체 맵 형태 - 방법 1] 구조체(POJO) 기반 파싱.
 * 과일 이름이 곧 key 이므로 "fruite" 를 Map<String, Fruit> 로 받는다.
 * 이름(key)으로 바로 찾을 수 있는 게 배열 형태와의 차이점.
 *
 * 대상 JSON (객체 맵 형태):
 *   {"fruite":{
 *      "apple":{"color":"red","sweet":1},
 *      "banana":{"color":"yellow","sweet":2}
 *   }}
 *
 * [필요 라이브러리] Gson (gson-2.11.0.jar 등)
 * [컴파일] javac -cp "lib/*" -d out proto/GsonMapStructParser.java
 * [실행]   java  -cp "out:lib/*" proto.GsonMapStructParser
 */
public class GsonMapStructParser {

    /** 과일 한 개의 속성. 이름은 Map 의 key 이므로 여기엔 없다. */
    static class Fruit {
        String color;
        int    sweet;

        @Override
        public String toString() {
            return "color=" + color + ", sweet=" + sweet;
        }
    }

    /** 최상위 구조체. key=과일이름, value=Fruit 인 Map 으로 받는다. */
    static class FruitBox {
        Map<String, Fruit> fruite;
    }

    public static void main(String[] args) {
        String json = "{\"fruite\":{"
                + "\"apple\":{\"color\":\"red\",\"sweet\":1},"
                + "\"banana\":{\"color\":\"yellow\",\"sweet\":2}"
                + "}}";

        Gson gson = new Gson();

        // 역직렬화 : JSON -> 구조체(Map)
        FruitBox box = gson.fromJson(json, FruitBox.class);

        System.out.println("== 구조체(Map) 파싱 결과 ==");
        for (Map.Entry<String, Fruit> e : box.fruite.entrySet()) {
            System.out.println(" - " + e.getKey() + " : " + e.getValue());
        }

        // 이름(key)으로 바로 접근
        System.out.println("\n== 이름으로 직접 접근 ==");
        Fruit banana = box.fruite.get("banana");
        System.out.println("banana.sweet = " + banana.sweet);

        // 직렬화 : 구조체 -> JSON
        Gson pretty = new GsonBuilder().setPrettyPrinting().create();
        System.out.println("\n== 다시 JSON 으로 직렬화 ==");
        System.out.println(pretty.toJson(box));
    }
}
