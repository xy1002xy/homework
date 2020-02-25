import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by huyibo on 2020/2/24.
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread t = Thread.currentThread();
        System.out.println(t.getName());
        FutureTask<String> task = new FutureTask(new Callable<String>() {
            public String call() throws Exception {
                return Thread.currentThread().getName() + " call Able";
            }
        });
        Thread t2 = new Thread(new FutureTask<String>(new Callable<String>() {
            public String call() throws Exception {
                return "test";
            }
        }));
        task.run();
        String result = task.get();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " thread Able");
            }
        });
        FutureTask<String> task1 = new FutureTask(t1,result);
        System.out.println(result);
    }
}
