package commontest;

import java.util.Scanner;

/**
 * Created by huyibo on 2020/2/25.
 */
public class Common {

    private static Thread mainThread;


    public static void main(String[] args){
        System.out.println("请输入一行字符串：");
        Scanner scanner = new Scanner(System.in);
        String source = scanner.nextLine();

        System.out.println("请输入需要计数的子串符串：");
        String target = scanner.nextLine();

        matchCount(source,target);
    }

    private static void matchCount(String source,String target){
        int count = 0;
        for(int i = 0;i<source.length()+1-target.length();i++){
            if(source.substring(i, target.length()+i).equals(target)) {
                count++;
            }
        }
        System.out.println(count);
    }
}
