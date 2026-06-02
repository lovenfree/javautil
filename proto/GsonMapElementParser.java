package proto;

import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * [객체 맵 형태 - 방법 2] 트리(Element) 기반 파싱.
 * 구조체 없이 JsonObject 트리를 직접 탐색해 필요한 값만 뽑는다.
 * 과일 이름이 key 이므로 entrySet() 으로 순회하거나, 이름으로 바로 집을 수 있다.
 *
 * 대상 JSON (객체 맵 형태):
 *   {"fruite":{
 *      "apple":{"color":"red","sweet":1},
 *      "banana":{"color":"yellow","sweet":2}
 *   }}
 *
 * [필요 라이브러리] Gson (gson-2.11.0.jar 등)
 * [컴파일] javac -cp "lib/*" -d out proto/GsonMapElementParser.java
 * [실행]   java  -cp "out:lib/*" proto.GsonMapElementParser
 */
public class GsonMapElementParser {

    public static void main(String[] args) {
        String json = "{\"fruite\":{"
                + "\"apple\":{\"color\":\"red\",\"sweet\":1},"
                + "\"banana\":{\"color\":\"yellow\",\"sweet\":2}"
                + "}}";

        JsonObject root   = JsonParser.parseString(json).getAsJsonObject();
        JsonObject fruite = root.getAsJsonObject("fruite");   // 배열이 아니라 객체로 꺼낸다

        // 1) entrySet() 으로 전체 순회 — key(과일이름)와 value(속성)를 함께 얻음
        System.out.println("== 전체 순회하며 개별 추출 ==");
        for (Map.Entry<String, JsonElement> e : fruite.entrySet()) {
            String     name  = e.getKey();
            JsonObject attr  = e.getValue().getAsJsonObject();
            String     color = attr.get("color").getAsString();
            int        sweet = attr.get("sweet").getAsInt();

            System.out.printf(" - %s : color=%s, sweet=%d%n", name, color, sweet);
        }

        // 2) 특정 데이터 하나만 콕 집어서 추출 (이름으로 바로 접근)
        System.out.println("\n== 특정 값만 추출 (banana 의 sweet) ==");
        int bananaSweet = fruite.getAsJsonObject("banana").get("sweet").getAsInt();
        System.out.println("banana.sweet = " + bananaSweet);

        // 3) apple 의 color
        System.out.println("\n== apple 의 color ==");
        String appleColor = fruite.getAsJsonObject("apple").get("color").getAsString();
        System.out.println("apple.color = " + appleColor);
    }
}
