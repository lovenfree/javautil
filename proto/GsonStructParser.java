package proto;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * [방법 1] 구조체(POJO) 기반 파싱.
 * JSON 구조와 1:1 로 대응하는 클래스를 만들어 gson.fromJson() 한 번으로 통째로 매핑한다.
 * 데이터 구조를 미리 알고 있고, 전체를 객체로 다룰 때 가장 깔끔하다.
 *
 * 대상 JSON (배열 형태):
 *   {"fruite":[
 *      {"name":"apple","color":"red","sweet":1},
 *      {"name":"banana","color":"yellow","sweet":2}
 *   ]}
 *
 * [필요 라이브러리] Gson (gson-2.11.0.jar 등)
 * [컴파일] javac -cp "lib/*" -d out proto/GsonStructParser.java
 * [실행]   java  -cp "out:lib/*" proto.GsonStructParser
 */
public class GsonStructParser {

    /** JSON 한 개 과일 객체에 대응하는 구조체. 필드명이 JSON 의 key 와 같아야 자동 매핑된다. */
    static class Fruit {
        String name;
        String color;
        int    sweet;

        @Override
        public String toString() {
            return name + "(color=" + color + ", sweet=" + sweet + ")";
        }
    }

    /** 최상위 구조체. "fruite" 배열을 List<Fruit> 로 받는다. */
    static class FruitBox {
        List<Fruit> fruite;
    }

    public static void main(String[] args) {
        String json = "{\"fruite\":["
                + "{\"name\":\"apple\",\"color\":\"red\",\"sweet\":1},"
                + "{\"name\":\"banana\",\"color\":\"yellow\",\"sweet\":2}"
                + "]}";

        Gson gson = new Gson();

        // 1) 역직렬화 : JSON 문자열 -> 구조체
        FruitBox box = gson.fromJson(json, FruitBox.class);

        System.out.println("== 구조체 파싱 결과 ==");
        for (Fruit f : box.fruite) {
            System.out.println(" - " + f);              // toString() 사용
            System.out.println("   name = " + f.name);  // 개별 필드 접근
            System.out.println("   sweet= " + f.sweet);
        }

        // 2) 직렬화 : 구조체 -> JSON (보기 좋게 들여쓰기)
        Gson pretty = new GsonBuilder().setPrettyPrinting().create();
        System.out.println("\n== 다시 JSON 으로 직렬화 ==");
        System.out.println(pretty.toJson(box));
    }
}
