package com.debug.springboot.server.thread;/**
 * Created by Administrator on 2020/4/21.
 */

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2020/4/21 9:47
 **/
public class ThreadOne {

    public static void main(String[] args) {
        Long startTime=System.currentTimeMillis();
        List<Integer> list= Lists.newLinkedList();
        final Random random=new Random();
        final Integer total=1000;

        ThreadPoolExecutor tp=new ThreadPoolExecutor(1,1,60, TimeUnit.SECONDS,new LinkedBlockingQueue<>(total));
        for (int i=0;i<total;i++){
            tp.execute(() -> list.add(random.nextInt(10)));
        }
        tp.shutdown();

        try {
            tp.awaitTermination(1,TimeUnit.DAYS);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("列表总数："+list.size());
        System.out.println("列表数据："+list);
        System.out.println("总耗时："+(System.currentTimeMillis() - startTime));
    }

}
