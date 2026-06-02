package sp.utils.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerUtil {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        //2 second
        long end = start + 2*1000;
        while (System.currentTimeMillis() < end) {
            // Some expensive operation on the item.
        }

        Timer m_timer = new Timer();

        TimerTask m_task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Hello 2S");
            }
        };

        m_timer.schedule(m_task, 2000);
        //tiemr cancel
        m_timer.cancel();
    }
}
