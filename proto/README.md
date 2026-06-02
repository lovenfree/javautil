# proto 패키지

Jetty(HTTP 서버/클라이언트) 와 Gson(JSON 파싱) 샘플 모음.
빌드 시스템 없이 **소스 + 외부 jar** 만으로 컴파일/실행하는 구성이다.

## 파일 구성

| 파일 | 설명 | 필요 라이브러리 |
|------|------|-----------------|
| `HttpServerSample.java` | Jetty 임베디드 서버. GET/POST 구분 처리 후 JSON 응답 | Jetty 11.x + jakarta.servlet-api |
| `HttpClientSample.java` | 서버로 GET/POST 요청 전송 | 없음 (JDK 내장 HttpClient) |
| `GsonStructParser.java` | (배열 형태) **방법1** 구조체(POJO) 기반 통째 파싱 | Gson |
| `GsonElementParser.java` | (배열 형태) **방법2** 트리 탐색으로 필요한 값만 추출 | Gson |
| `GsonMapStructParser.java` | (객체 맵 형태) **방법1** 구조체(`Map<String,Fruit>`) 파싱 | Gson |
| `GsonMapElementParser.java` | (객체 맵 형태) **방법2** 트리 탐색으로 필요한 값만 추출 | Gson |

## 의존성(jar) 준비

`lib/` 폴더를 만들고 아래 jar 를 넣는다. (Maven Central 에서 받을 수 있음)

- Jetty 11: `jetty-server`, `jetty-http`, `jetty-io`, `jetty-util`
- `jakarta.servlet-api` 5.0.x
- `gson` 2.11.0

## 컴파일 & 실행 (macOS/Linux 기준, classpath 구분자 `:`)

```bash
# 컴파일
javac -cp "lib/*" -d out proto/*.java

# 1) HTTP 서버/클라이언트 데모  (터미널 2개)
java -cp "out:lib/*" proto.HttpServerSample     # 터미널 A: 서버 기동
java -cp "out:lib/*" proto.HttpClientSample     # 터미널 B: 요청 전송

# 2) Gson 파싱 데모 (배열 형태)
java -cp "out:lib/*" proto.GsonStructParser      # 구조체 파싱
java -cp "out:lib/*" proto.GsonElementParser     # 트리/개별 추출

# 3) Gson 파싱 데모 (객체 맵 형태)
java -cp "out:lib/*" proto.GsonMapStructParser   # 구조체(Map) 파싱
java -cp "out:lib/*" proto.GsonMapElementParser  # 트리/개별 추출
```

> Windows 는 classpath 구분자를 `;` 로 바꾼다. 예: `-cp "out;lib/*"`

## 다루는 JSON — 두 가지 형태

### A) 배열 형태 (`GsonStructParser`, `GsonElementParser`)

```json
{
  "fruite": [
    {"name": "apple",  "color": "red",    "sweet": 1},
    {"name": "banana", "color": "yellow", "sweet": 2}
  ]
}
```

- 과일이 순서 있는 리스트. 같은 형태의 항목이 N개로 늘어나는 데 자연스럽다.
- **구조체 파싱**: `FruitBox { List<Fruit> fruite }` — `Fruit` 에 `name` 필드 존재.
- **개별 추출**: `JsonArray` 를 인덱스로 순회.

### B) 객체 맵 형태 (`GsonMapStructParser`, `GsonMapElementParser`)

```json
{
  "fruite": {
    "apple":  {"color": "red",    "sweet": 1},
    "banana": {"color": "yellow", "sweet": 2}
  }
}
```

- 과일 이름이 곧 key라서 이름으로 바로 접근 가능 (`fruite.apple`).
- **구조체 파싱**: `FruitBox { Map<String, Fruit> fruite }` — `Fruit` 에 `name` 없음(이름이 key).
- **개별 추출**: `JsonObject.entrySet()` 순회 또는 이름으로 직접 `getAsJsonObject("banana")`.

> 공통: **구조체 방식**은 전체를 객체로 통째 다룰 때, **트리 방식**은 특정 값 하나만 필요하거나 구조가 가변적일 때 유리하다.
