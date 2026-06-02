package proto;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * HTTP 클라이언트 샘플.
 * HttpServerSample 서버(localhost:8080)에 GET / POST 요청을 보낸다.
 *
 * [필요 라이브러리] 없음 — JDK 내장 java.net.http.HttpClient(Java 11+) 사용.
 *
 * [컴파일]  javac -d out proto/HttpClientSample.java
 * [실행]    java  -cp out proto.HttpClientSample
 *
 * 먼저 HttpServerSample 을 실행해 두어야 한다.
 */
public class HttpClientSample {

    private static final String BASE = "http://localhost:8080";

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        // 1) GET : 쿼리 파라미터로 정보 전달
        HttpRequest getReq = HttpRequest.newBuilder()
                .uri(URI.create(BASE + "/fruit?name=apple"))
                .GET()
                .build();
        HttpResponse<String> getRes = client.send(getReq, HttpResponse.BodyHandlers.ofString());
        System.out.println("[GET ] status=" + getRes.statusCode() + " body=" + getRes.body());

        // 2) POST : 본문(JSON)으로 정보 전달
        String json = "{\"name\":\"banana\",\"color\":\"yellow\",\"sweet\":2}";
        HttpRequest postReq = HttpRequest.newBuilder()
                .uri(URI.create(BASE + "/fruit"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> postRes = client.send(postReq, HttpResponse.BodyHandlers.ofString());
        System.out.println("[POST] status=" + postRes.statusCode() + " body=" + postRes.body());
    }
}
