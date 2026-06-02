package src.com.time.t3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class main {

    static Map<String, String> dict;
    static Set<String> excluded;
    static JsonArray models;
    static HttpClient client;

    public static void main(String[] args) throws Exception {
        dict = loadDictionary("DICTIONARY.TXT");
        excluded = loadExcluded("STOPWORD.TXT");
        models = loadModels("MODELS.JSON");

        client = new HttpClient();
        client.start();

        Server server = new Server();
        ServerConnector http = new ServerConnector(server);
        http.setHost("127.0.0.1");
        http.setPort(8080);
        server.addConnector(http);

        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(InferenceServlet.class, "/");
        server.setHandler(handler);

        server.start();
        server.join();
    }

    public static class InferenceServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

        protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
            JsonObject body = JsonParser.parseString(readBody(req)).getAsJsonObject();
            String modelName = body.get("modelname").getAsString();
            JsonArray queries = body.getAsJsonArray("queries");

            JsonObject model = findModel(modelName);
            String url = model.get("url").getAsString();
            Map<String, String> codeToValue = new HashMap<>();
            for (JsonElement c : model.getAsJsonArray("classes")) {
                JsonObject cls = c.getAsJsonObject();
                codeToValue.put(cls.get("code").getAsString(), cls.get("value").getAsString());
            }

            JsonArray results = new JsonArray();
            for (JsonElement q : queries) {
                String preprocessed = transform(q.getAsString());
                String code = classify(url, preprocessed);
                results.add(codeToValue.get(code));
            }

            JsonObject out = new JsonObject();
            out.add("results", results);
            res.setStatus(200);
            res.setContentType("application/json");
            res.getWriter().write(out.toString());
        }
    }

    static String classify(String url, String preprocessed) {
        JsonObject reqBody = new JsonObject();
        reqBody.addProperty("query", preprocessed);
        try {
            ContentResponse cr = client.POST(url)
                    .content(new StringContentProvider(reqBody.toString()), "application/json")
                    .send();
            JsonObject resp = JsonParser.parseString(cr.getContentAsString()).getAsJsonObject();
            return resp.get("result").getAsString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static JsonObject findModel(String name) {
        for (JsonElement m : models) {
            JsonObject model = m.getAsJsonObject();
            if (model.get("modelname").getAsString().equals(name)) return model;
        }
        throw new IllegalArgumentException("unknown model: " + name);
    }

    static String transform(String sentence) {
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

    static String readBody(HttpServletRequest req) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = req.getReader()) {
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
        }
        return sb.toString();
    }

    static JsonArray loadModels(String path) throws Exception {
        try (FileReader fr = new FileReader(path)) {
            JsonObject root = JsonParser.parseReader(fr).getAsJsonObject();
            return root.getAsJsonArray("models");
        }
    }

    static Map<String, String> loadDictionary(String path) throws Exception {
        Map<String, String> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                int i = line.indexOf('#');
                if (i < 0) continue;
                map.put(line.substring(0, i), line.substring(i + 1).trim());
            }
        }
        return map;
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

// tech: t2 변환로직 위에 임베디드 Jetty 서블릿(127.0.0.1:8080, POST /) 수신 + Jetty HttpClient
//       중계호출 + Gson 파싱/직렬화. 파일목록(JSON) 룩업으로 대상 URL/코드맵 결정,
//       응답코드 -> 값 치환 후 배열 직렬화 응답. server.join()으로 상주(무종료).
