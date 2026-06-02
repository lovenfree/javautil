package proto;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 * Jetty 임베디드 서버 샘플.
 * GET / POST 요청을 구분해서 처리하고 JSON 으로 응답한다.
 *
 * [필요 라이브러리] Jetty 11.x (Java 17+ / Java 21 호환), jakarta.servlet-api 5.x
 *   - jetty-server-11.x.jar
 *   - jetty-http-11.x.jar
 *   - jetty-io-11.x.jar
 *   - jetty-util-11.x.jar
 *   - jakarta.servlet-api-5.0.x.jar
 *
 * [컴파일]  javac -cp "lib/*" -d out proto/HttpServerSample.java
 * [실행]    java  -cp "out:lib/*" proto.HttpServerSample      (Windows 는 ; 구분자)
 *
 * 서버를 띄운 뒤 HttpClientSample 을 실행하면 GET/POST 교환을 확인할 수 있다.
 */
public class HttpServerSample extends AbstractHandler {

    private static final int PORT = 8080;

    @Override
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {

        String method = request.getMethod();
        System.out.println("[server] " + method + " " + target);

        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        String body;
        if ("GET".equalsIgnoreCase(method)) {
            // GET : 쿼리 파라미터(?name=apple) 를 읽어서 그대로 응답
            String name = request.getParameter("name");
            body = "{\"method\":\"GET\",\"path\":\"" + target + "\",\"name\":\"" + name + "\"}";

        } else if ("POST".equalsIgnoreCase(method)) {
            // POST : 요청 본문(body)을 읽어서 echo
            String payload = readBody(request);
            body = "{\"method\":\"POST\",\"received\":" + payload + "}";

        } else {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            body = "{\"error\":\"only GET/POST supported\"}";
        }

        response.getWriter().print(body);
        baseRequest.setHandled(true);   // Jetty 에 "이 요청 처리 끝" 을 알린다
    }

    /** POST 본문 전체를 문자열로 읽는다. */
    private String readBody(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.length() == 0 ? "null" : sb.toString();
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(PORT);
        server.setHandler(new HttpServerSample());

        server.start();
        System.out.println("[server] started on http://localhost:" + PORT);
        server.join();   // 서버가 종료될 때까지 대기
    }
}
