package sp.utils.processing;

public class ThreadSample {
    public static void main(String[] args) throws InterruptedException {
        ThreadClass tc1 = new ThreadClass("[Thread1] ");
        ThreadClass tc2 = new ThreadClass("[Thread2] ");
        tc1.start();
        tc2.start();

        ThreadClass.lock.lock();
        try {
            ThreadClass.PrintNums("[Main]");
        }
        finally
        {
            ThreadClass.lock.unlock();
        }

        tc1.join();
        tc2.join();
    }
}
