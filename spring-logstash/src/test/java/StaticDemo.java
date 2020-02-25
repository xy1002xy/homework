/**
 * Created by huyibo on 2020/2/24.
 */
public class StaticDemo {

    private String param1;

    private String param2;

    static {
        System.out.println("静态块执行");
    }

    public StaticDemo(){
        System.out.println("StaticDemo 初始化执行");
    }

    public static void staticMethod(){
        System.out.println(" invoke static method");
    }

}
