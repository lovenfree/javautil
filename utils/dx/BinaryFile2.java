package sp.utils.dx;

public class BinaryFile2 {

  public static void main(String[] args) throws Exception {

    HttpClient httpClient = new HttpClient();

    httpClient.start();

    ContentResponse contentRes =

        httpClient.newRequest("http://127.0.0.1:8080/requestFile2").method(HttpMethod.GET).send();

    byte [] myByteArray = contentRes.getContent();



    ByteArrayInputStream byteIn = new ByteArrayInputStream(myByteArray);

    ObjectInputStream in = new ObjectInputStream(byteIn);

    Map<String, byte[]> data2 = (Map<String, byte[]>) in.readObject();



    try (FileOutputStream fos = new FileOutputStream("C:\\temp\\test.jpg")) {

      fos.write(data2.get("file"));

    }

    httpClient.stop();

  }

}