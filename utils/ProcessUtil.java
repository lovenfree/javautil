package sp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ProcessUtil {
    public static void main(String[] args) throws IOException {
//        new ProcessBuilder("파일 경로", "파라미터 1", "파라미터 2").start();

        Process process = new ProcessBuilder("C:\\PathToExe\\MyExe.exe","param1","param2").start();
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;

        System.out.printf("Output of running %s is:", Arrays.toString(args));

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }


    }


//    import java.io.*;
//    class OneMore {
//        public static void main(String[] args)throws IOException
//        {
//            Runtime runtime = Runtime.getRuntime();
//            String cmds[] = {"ls"};
//            Process proc = runtime.exec(cmds);
//            InputStream inputstream = proc.getInputStream();
//            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
//            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
//            String line;
//            while ((line = bufferedreader.readLine()) != null) {
//                System.out.println(line);
//            }
//        }
//    }





//    public class Test {



//        public static String getProcessOutput(List<String> cmdList) throws IOException, InterruptedException {
//
//            ProcessBuilder builder = new ProcessBuilder(cmdList);
//
//            Process process = builder.start();
//
//            process.waitFor();
//
//            InputStream psout = process.getInputStream();
//
//            return (new String(psout.readAllBytes()));
//
//        }
//
//
//
//        public static void main(String[] args) throws IOException, InterruptedException {
//
//            String output = getProcessOutput(Arrays.asList("multiply.exe","2","3"));
//
//            System.out.println(output);
//
//        }

//    }

//    https://byul91oh.tistory.com/249

}
