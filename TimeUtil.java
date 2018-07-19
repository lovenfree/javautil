import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class TimeTest {
           public static void main(String[] args) throws ParseException {
                     String strCT;
                     // HH : 24Hour 기준, hh : 12Hour 기준 
                     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
                     
                     // 1. 현재 시간 구하기 (1)
                     System.out.println("1. Current Time (1) ---------------------");
                     long ct = System.currentTimeMillis();
                     System.out.println(ct); // millisecond
                     System.out.println((ct / 1000 / 60 / 60) % 24 + ":" + (ct / 1000 / 60) % 60 + ":" + (ct / 1000) % 60);
                     strCT = sdf.format(ct);
                     System.out.println(strCT);
                     
                     // 1. 현재 시간 구하기 (2)
                     System.out.println("1. Current Time (2) ---------------------");
                     Date d = new Date();
                     System.out.println(d);
                     strCT = sdf.format(d);
                     System.out.println(strCT);
                     long lt = d.getTime();
                     System.out.println((lt / 1000 / 60 / 60) % 24 + ":" + (lt / 1000 / 60) % 60 + ":" + (lt / 1000) % 60);
                     
                     // 2. String -> Date 변환
                     System.out.println("2. String -> Date  ---------------------");
                     String strTime = "2017-11-13 21:40:15";
                     SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                     Date d2 = transFormat.parse(strTime);
                     System.out.println(strTime + " -> " + d2);
                     
                     // 3. Date -> String 변환
                     System.out.println("3. Date -> String  ---------------------");
                     Date d3 = new Date();
                     strTime = transFormat.format(d3);
                     System.out.println(d3 + " -> " + strTime);
                     
                     // 4. 두 시간 차이 구하기 
                     System.out.println("4. Hour Difference  ---------------------");
                     String start = "20171113184910"; 
                     String end = "20171113214910";
                     SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                     Date dd1 = sf.parse(start);
                     Date dd2 = sf.parse(end);
                     
                     System.out.println(end + " - " + start);
                     long diff = dd2.getTime() - dd1.getTime();
                     System.out.println(diff/1000/60/60); // Hour Difference
           }
}


