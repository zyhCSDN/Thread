package com.debug.springboot.server.thread;/**
 * Created by Administrator on 2020/4/21.
 */

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

/**
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2020/4/21 10:01
 **/
public class ThreadTwo {

    public static void main(String[] args) {
        Long startTime=System.currentTimeMillis();
        List<Integer> list= Lists.newLinkedList();
        final Random random=new Random();
        final Integer total=1000;

        for (int i=0;i<total;i++){
            Thread thread= new Thread(() -> list.add(random.nextInt(10)));

            thread.start();

            try {
                thread.join();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        System.out.println("列表总数："+list.size());
        System.out.println("列表数据："+list);
        System.out.println("总耗时："+(System.currentTimeMillis() - startTime));
    }

}




















