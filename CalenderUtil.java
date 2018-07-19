package sp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalenderUtil {

	public static long hourDiff(String strTime2, String strTime1) throws ParseException {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		
		Date date1 = transFormat.parse(strTime1);
		Date date2 = transFormat.parse(strTime2);
		
		long gap = date2.getTime() - date1.getTime();
		
		return gap/60/60/1000;
	}
	
	
	public static String getCurrentDateTimeString() {
		
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMddHHmmss");
		String strTime = dayTime.format(new Date(time));
		return strTime;
	}
	
	public static String getCurrentDateString() {
		long time = System.currentTimeMillis();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		String strToday = today.format(new Date(time));
		return strToday;
	}
	
	//string to time diff
	
	
	
}
