import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;

/**
 * Created by huyibo on 2020/3/5.
 */
public class HttpClinetTest {

    public static void main(String[] args) throws InterruptedException {


        final CountDownLatch countDownLatch = new CountDownLatch(100);
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 3000; i++) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    String url = "http://localhost:10002/test1";
                    String result = HttpUtil.doGet(url);
                    System.out.println(result);
                    countDownLatch.countDown();
                }
            });
            t.start();
        }
        countDownLatch.await();
        Long end = System.currentTimeMillis();
        System.out.println("done " + String.valueOf(end - start));
    }

}
