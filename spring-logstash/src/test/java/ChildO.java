/**
 * Created by huyibo on 2020/2/24.
 */
public class ChildO extends ParentO{

    static {
        System.out.println(" child static cinit ");
    }

    public ChildO(){
        System.out.println(" child instance init");
    }

}
