package commontest;

/**
 * Created by huyibo on 2020/2/29.
 */

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;


public class CulDemo {

    public static void main(String[] args){
        System.out.println("请输入一行字符串：");
        int errorCount = 0;
        Scanner scanner = new Scanner(System.in);
        String source = scanner.nextLine();
        while (source == null || "".equals(source)){
            errorCount++;
            if(errorCount>3){
                System.out.println("输入错误过多,结束");
                return;
            }

            System.out.println("输入错误,请重试");
            source = scanner.nextLine();

        }

        errorCount = 0;
        System.out.println("请输入需要计数的子串符串 ：");
        String target = scanner.nextLine();

        while (target == null || "".equals(target)){
            errorCount++;
            if(errorCount>3){
                System.out.println("输入错误过多,结束");
                return;
            }

            System.out.println("输入错误,请重试");
            target = scanner.nextLine();
        }


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
