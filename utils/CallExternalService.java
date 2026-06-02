package sp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class CallExternalService {
//
//  자바에서 외부 프로그램을 실행시키고 싶다면 ProcessBuilder클래스를 이용하면 된다.
//
//  외부 프로그램의 출력결과는 Stream으로 받아올 수 있다.
//
//    * multiply.exe를 실행시켜서 출력결과를 가져오는 샘플 코드 (multiply.exe는 인자 두 개를 곱해서 출력)


       public static String getProcessOutput(List<String> cmdList) throws IOException, InterruptedException {

             ProcessBuilder builder = new ProcessBuilder(cmdList);

             Process process = builder.start();

             process.waitFor();

             InputStream psout = process.getInputStream();

             return (new String(psout.readAllBytes()));

       }



       public static void main(String[] args) throws IOException, InterruptedException {

             String output = getProcessOutput(Arrays.asList("multiply.exe","2","3"));

             System.out.println(output);

       }

}
