import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by huyibo on 2020/2/24.
 */
public class ExetuctorDemo  {

    public static void main(String[] args){

//        ReentrantLock  reentrantLock = new ReentrantLock();

        final ReentrantLock  reentrantLock = new ReentrantLock(true);

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                reentrantLock.lock();
                reentrantLock.lock();
                reentrantLock.unlock();
                reentrantLock.unlock();
            }
        });

        ExecutorService threadPoolExcutor = Executors.newCachedThreadPool();
//        threadPoolExcutor.submit();


    }

}
