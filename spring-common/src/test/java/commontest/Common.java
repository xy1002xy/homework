package commontest;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by huyibo on 2020/2/25.
 */
public class Common {

    private static Thread mainThread;


    public static void main(String[] args){
        ThreadA t = new ThreadA("test");
        mainThread = Thread.currentThread();

        System.out.println(Thread.currentThread().getName()+" start ta");
        t.start();

        System.out.println(Thread.currentThread().getName()+" block");
        LockSupport.park(mainThread);

        System.out.println(Thread.currentThread().getName()+" continue");
    }
    static class ThreadA extends Thread{

        public ThreadA(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" wakeup others");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" wakeup others");
            LockSupport.unpark(mainThread);
        }

    }
}
