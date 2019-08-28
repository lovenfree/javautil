# javautil


소프트웨어 공학 연습문제
https://blog.naver.com/lwj798/220679963868



//https://blog.naver.com/english_daram_g/221140094150 file util

​

public static void fileAppend(String fn, String str){

BufferdWriter bw;

try{

bw=new BufferedWriter(new FileWriter(fn,true));

PrintWriter pw = new PrintWriter(bw,true);

pw.write(str+"\n");

pw.flush();

pw.close();

}catch(IOException e){

e.printStackTrace();

}

}

​

​

객체 정렬

​

​

https://gmlwjd9405.github.io/2018/09/06/java-comparable-and-comparator.html
[Java] Comparable와 Comparator의 차이와 사용법 - Heee's Development Blog

Step by step goes a long way.

gmlwjd9405.github.io

https://blog.naver.com/isaac7263/221343255063
JAVA 자바이야기*47. 객체 정렬 Comparable, Comparator

객체 정렬 Comparable, Comparator, Collections.sort()※Comparable, Comparator는 객체를 정렬하는...

blog.naver.com

https://mine-it-record.tistory.com/135
[JAVA]자바_sort (배열 정렬- 오름차순 / 내림차순)

sort() - 자바에서 기본적으로 제공해주는 함수이다. - 기본적으로 오름차순 사용시 Arrays.sort() 를 사용해 주고 - 내림차순으로 사용시 Collections.reverseOrder() 를 사용해 줍니다. public class SortTest{..

mine-it-record.tistory.com

​

외부프로그램 실행

https://yangyag.tistory.com/55
자바로 외부 프로그램 실행시키기(ProcessBuilder)

자바로 외부 프로그램 실행 시키는 방법중에 ProcessBuilder 에 대해서 간단하게 소개 하려고 한다. 자바에서는 외부 프로그램 실행 시 출력 내용을 바로 확인 할 수 없고 버퍼에 저장 후 출력하는 형식으로 확인..

yangyag.tistory.com

https://202psj.tistory.com/980
[Java] 다른 프로그램 프로세싱 관리, 프로그램 죽으면 다시 실행 시키기 관련, 자바로 외부파일 실행.

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////..

202psj.tistory.com
