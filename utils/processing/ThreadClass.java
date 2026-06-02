package sp.utils.processing;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadClass extends Thread { // 'Thread' Class를 상속받는다

    static ReentrantLock lock = new ReentrantLock();

    String thread_name;
    public ThreadClass(String name) {
        this.thread_name = name;
    }

    public void run() {
        lock.lock();
        try {
            PrintNums(thread_name);
        }
        finally {
            lock.unlock();
        }
    }

    static void PrintNums(String str)
    {
        int i;

        System.out.println(str);

        for (i=1; i<=30; i++)
        {
            System.out.print(i+" ");
        }
        System.out.println();
    }
}


