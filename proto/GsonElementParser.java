package proto;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * [방법 2] 트리(Element) 기반 파싱.
 * 구조체를 만들지 않고 JsonObject / JsonArray 트리를 직접 탐색해
 * "필요한 데이터만" 골라서 뽑는다.
 * 구조가 자주 바뀌거나, 전체가 아니라 특정 값 하나만 필요할 때 유리하다.
 *
 * 대상 JSON (배열 형태):
 *   {"fruite":[
 *      {"name":"apple","color":"red","sweet":1},
 *      {"name":"banana","color":"yellow","sweet":2}
 *   ]}
 *
 * [필요 라이브러리] Gson (gson-2.11.0.jar 등)
 * [컴파일] javac -cp "lib/*" -d out proto/GsonElementParser.java
 * [실행]   java  -cp "out:lib/*" proto.GsonElementParser
 */
public class GsonElementParser {

    public static void main(String[] args) {
        String json = "{\"fruite\":["
                + "{\"name\":\"apple\",\"color\":\"red\",\"sweet\":1},"
                + "{\"name\":\"banana\",\"color\":\"yellow\",\"sweet\":2}"
                + "]}";

        // 루트를 JsonObject 로 파싱
        JsonObject root = JsonParser.parseString(json).getAsJsonObject();

        // "fruite" 키의 값을 배열로 꺼낸다
        JsonArray fruite = root.getAsJsonArray("fruite");

        // 1) 배열을 순회하며 필요한 필드만 각각 추출
        System.out.println("== 전체 순회하며 개별 추출 ==");
        for (int i = 0; i < fruite.size(); i++) {
            JsonObject fruit = fruite.get(i).getAsJsonObject();

            String name  = fruit.get("name").getAsString();
            String color = fruit.get("color").getAsString();
            int    sweet = fruit.get("sweet").getAsInt();   // 타입 지정해서 꺼냄

            System.out.printf(" - %s : color=%s, sweet=%d%n", name, color, sweet);
        }

        // 2) 특정 데이터 하나만 콕 집어서 추출 (예: banana 의 sweet 값)
        System.out.println("\n== 특정 값만 추출 (banana 의 sweet) ==");
        Integer bananaSweet = null;
        for (int i = 0; i < fruite.size(); i++) {
            JsonObject fruit = fruite.get(i).getAsJsonObject();
            if ("banana".equals(fruit.get("name").getAsString())) {
                bananaSweet = fruit.get("sweet").getAsInt();
                break;
            }
        }
        System.out.println("banana.sweet = " + bananaSweet);

        // 3) 인덱스로 바로 접근 (첫 번째 과일의 color)
        System.out.println("\n== 인덱스로 직접 접근 (0번째 color) ==");
        String firstColor = fruite.get(0).getAsJsonObject().get("color").getAsString();
        System.out.println("fruite[0].color = " + firstColor);
    }
}
