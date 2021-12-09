package com.debug.springboot.server.retryer;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Guava_retrying机制实现重试
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/12/1 16:17
 **/
public class RetryUtil {

    private static final Logger log= LoggerFactory.getLogger(RetryUtil.class);

    private static Integer i=1;


    public static Integer execute() throws Exception{
        log.info("----重试时 变量i的叠加逻辑----");

        return i++;
    }

    public static void main(String[] args) {

        //TODO:方式一：

        /*//TODO:定义任务实例
        Callable<String> callable= () -> {
            Integer res=execute();

            if (res>3){
                return res.toString();
            }
            return null;
        };

        //TODO：定义重试器
        Retryer<String> retryer=RetryerBuilder.<String>newBuilder()
                //TODO:当返回结果为Null时 - 执行重试
                .retryIfResult(Predicates.isNull())
                //TODO:当执行核心业务逻辑抛出RuntimeException - 执行重试
                .retryIfRuntimeException()
                //TODO:还可以自定义抛出何种异常时 - 执行重试
                .retryIfExceptionOfType(IOException.class)
                .build();*/



        //TODO:方式二：

        //TODO:定义任务实例
        Callable<String> callable= () -> {

            return null;
        };


        //TODO:每次重试时 监听器执行的逻辑
        RetryListener retryListener=new RetryListener() {
            @Override
            public <V> void onRetry(Attempt<V> attempt) {
                Long curr=attempt.getAttemptNumber();
                log.info("----每次重试时 监听器执行的逻辑,当前已经是第 {} 次重试了----",curr);

                if (curr == 3){
                    log.error("--重试次数已到，是不是得该执行一些补偿逻辑，如发送短信、发送邮件...");

                }
            }
        };


        //TODO：定义重试器
        Retryer<String> retryer=RetryerBuilder.<String>newBuilder()
                //TODO:当返回结果为Null时 - 执行重试
                .retryIfResult(Predicates.isNull())
                //TODO:当执行核心业务逻辑抛出RuntimeException - 执行重试
                .retryIfRuntimeException()
                //TODO:还可以自定义抛出何种异常时 - 执行重试
                .retryIfExceptionOfType(IOException.class)

                //TODO:每次重试时的时间间隔为5s
                .withWaitStrategy(WaitStrategies.fixedWait(5L, TimeUnit.SECONDS))
                //TODO:重试次数为3次，3次之后就不重试了
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                //TODO:每次重试时定义一个监听器listener，监听器的逻辑可以是 "日志记录"、"做一些补偿操作"...
                .withRetryListener(retryListener)

                .build();


        try {
            retryer.call(callable);
        } catch (ExecutionException | RetryException e) {
            e.printStackTrace();
        }
    }

}










































