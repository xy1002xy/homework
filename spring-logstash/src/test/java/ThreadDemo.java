import java.util.concurrent.locks.LockSupport;

/**
 * Created by huyibo on 2020/2/20.
 */
public class ThreadDemo{

    static void parkTest(){
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("准备park t1 线程");
                LockSupport.park();
                System.out.println("t1 被唤起 线程");

            }
        });
        t1.start();

        LockSupport.unpark(t1);
    }


    public static void main(String[] args){
        ThreadDemo threadDemo = new ThreadDemo();
        LockSupport.park();

    }
}
