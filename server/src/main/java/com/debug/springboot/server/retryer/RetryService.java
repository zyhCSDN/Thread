package com.debug.springboot.server.retryer;

import com.debug.springboot.model.entity.second.SysConfig;
import com.debug.springboot.model.mapper.second.SysConfigMapper;
import com.debug.springboot.server.service.EmailSendService;
import com.github.rholder.retry.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Guava_Retrying重试机制的 小型真实案例
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/12/1 17:51
 **/
@Service
public class RetryService {

    private static final Logger log= LoggerFactory.getLogger(RetryService.class);

    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Autowired
    private EmailSendService emailSendService;


    //TODO:获取某个字典配置详情
    public SysConfig getConfigInfo(final Integer id){
        SysConfig config=sysConfigMapper.selectByPrimaryKey(id);

        if (config==null){
            doRetry(id);

            config=sysConfigMapper.selectByPrimaryKey(id);
        }
        return config;
    }

    //TODO:执行重试逻辑
    private void doRetry(final Integer id){

        //TODO:定义任务实例
        /*Callable<SysConfig> callable= () -> {
            return sysConfigMapper.selectByPrimaryKey(id);
        };*/

        Callable<SysConfig> callable=new Callable<SysConfig>() {
            @Override
            public SysConfig call() throws Exception {
                return sysConfigMapper.selectByPrimaryKey(id);
            }
        };




        //TODO:每次重试时 监听器执行的逻辑
        RetryListener retryListener=new RetryListener() {
            @Override
            public <V> void onRetry(Attempt<V> attempt) {
                Long curr=attempt.getAttemptNumber();
                log.info("----每次重试时 监听器执行的逻辑,当前已经是第 {} 次重试了----",curr);

                if (curr == 3){
                    log.error("--重试次数已到，是不是得该执行一些补偿逻辑，如发送短信、发送邮件...");

                    emailSendService.sendSimpleEmail("重试次数已到","请各位大佬上去检查一下sysConfig是否存在","1948831260@qq.com");
                }
            }
        };

        //TODO：定义重试器
        Retryer<SysConfig> retryer= RetryerBuilder.<SysConfig>newBuilder()
                //TODO:当返回结果为 false 时 - 执行重试(即sysCofig为null)
                .retryIfResult(Objects::isNull)
                //TODO:当执行核心业务逻辑抛出RuntimeException - 执行重试
                .retryIfRuntimeException()
                //TODO:还可以自定义抛出何种异常时 - 执行重试
                .retryIfExceptionOfType(IOException.class)

                //TODO:每次重试时的时间间隔为10s (当然啦，实际项目中一般是不超过1s的，如500ms，这里是为了方便模拟演示)
                .withWaitStrategy(WaitStrategies.fixedWait(10L, TimeUnit.SECONDS))
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























