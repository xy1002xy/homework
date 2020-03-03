package commontest;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by huyibo on 2020/2/29.
 */
public class ReentryLockCondition {

    private AtomicInteger ticket = new AtomicInteger(0);

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
    }

    class producer extends Thread {

        private Condition coco;

        public producer(Condition condition) {
            coco = condition;
        }

        @Override
        public void run() {
            while (ticket.get() <= 50) {
                ticket.addAndGet(1);
                System.out.println("生产消息：..." + ticket);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("队列已满：..." + ticket);
            try {
                coco.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    class consumer extends Thread {
        private Condition coco;

        public consumer(Condition condition) {
            coco = condition;
        }

        @Override
        public void run() {
            while (ticket.get() <= 50) {
                if (ticket.get() <=0){
                    try {
                        System.out.println("队列已清空：..." + ticket);
                        coco.await();
                        ticket.addAndGet(-1);
                        System.out.println("消费：..." + ticket);
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

}
