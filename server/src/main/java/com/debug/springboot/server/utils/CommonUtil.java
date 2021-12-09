package com.debug.springboot.server.utils;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/11/28 22:23
 */
public class CommonUtil {

    public static String randomUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 分隔后拼接成用于sql查询的字符串- 'a','b','c'  "a,b,c"
     * @param param
     * @param separatorChars
     * @return
     */
    public static String concatStrToChar(String param, String separatorChars){
        StringBuilder sb=new StringBuilder();
        String[] arr= StringUtils.split(param,separatorChars);
        int i=0;
        for (;i<arr.length;i++){
            if (arr.length-1 != i){
                sb.append("'").append(arr[i]).append("'").append(",");
            }else{
                sb.append("'").append(arr[i]).append("'");
            }
        }
        return sb.toString();
    }

    /**
     * 分隔后拼接成用于sql查询的字符串- a,b,c
     * @param param
     * @param separatorChars
     * @return
     */
    public static String concatStrToInt(String param, String separatorChars){
        StringBuilder sb=new StringBuilder();
        String[] arr= StringUtils.split(param,separatorChars);
        int i=0;
        for (;i<arr.length;i++){
            if (arr.length-1 != i){
                sb.append(arr[i]).append(",");
            }else{
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        /*String params="1,2,3,4,5";
        System.out.println(concatStrToInt(params,","));

        params="R1010,R1020,R1030";
        System.out.println(concatStrToChar(params,","));*/


        Callable<String> callable=new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("----重试调用---"+new Date());

                //throw new RuntimeException();
                return null;
            }
        };

        /*Retryer<String> retryer=RetryerBuilder.<String>newBuilder()
                .retryIfResult(Predicates.isNull())
                .retryIfExceptionOfType(IOException.class)
                .retryIfRuntimeException()
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();*/

        //每次重试之后-固定等待5s
        /*Retryer<String> retryer=RetryerBuilder.<String>newBuilder()
                .retryIfResult(Predicates.isNull())
                .retryIfExceptionOfType(IOException.class)
                .retryIfRuntimeException()
                //TODO:每次重试之间的等待策略-固定等待、随机等待、斐波那契数列数值等待
                .withWaitStrategy(WaitStrategies.fixedWait(5, TimeUnit.SECONDS))
                //TODO:终止策略
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();*/


        RetryListener retryListener=new RetryListener() {
            @Override
            public <V> void onRetry(Attempt<V> attempt) {
                System.out.println("---当前第"+attempt.getAttemptNumber()+"次重试！");

                if (3==attempt.getAttemptNumber()){
                    System.out.println("到达了指定的重试次数了，是时候让大佬出手了...");
                }
            }
        };

        //重试超过指定的次数之后-加个监听器-记录额外的操作、如发送加急邮件给系统运维或者项目负责人员
        Retryer<String> retryer=RetryerBuilder.<String>newBuilder()
                .retryIfResult(Predicates.isNull())
                .retryIfExceptionOfType(IOException.class)
                .retryIfRuntimeException()
                //TODO:每次重试之间的等待策略-固定等待、随机等待、斐波那契数列数值等待
                .withWaitStrategy(WaitStrategies.fixedWait(5, TimeUnit.SECONDS))
                //TODO:终止策略
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .withRetryListener(retryListener)
                .build();

        try {
            retryer.call(callable);
        } catch (ExecutionException | RetryException e) {
            e.printStackTrace();
        }
    }
}






















