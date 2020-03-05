package com.huyibo.springgateway.server;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by huyibo on 2020/3/3.
 */
public class FutureDemo {
    //1、Future 源码
    //2、catdownlatch
    //3、symaphore
    public static void main(String[] args){
        FutureTask<String> task = new FutureTask<String>(new Thread(new Runnable() {
            public void run() {
                System.out.println("--------==========");
            }
        }),null);
//        task.get();
    }

}
