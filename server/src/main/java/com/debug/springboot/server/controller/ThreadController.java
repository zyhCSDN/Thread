package com.debug.springboot.server.controller;

import com.debug.springboot.api.enums.StatusCode;
import com.debug.springboot.api.response.BaseResponse;
import com.debug.springboot.model.mapper.primary.CodesMapper;
import com.debug.springboot.server.service.ThreadService;
import com.debug.springboot.server.thread.CodeGenerateThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * 发送邮件controller
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/11/7 17:52
 **/
@RestController
@RequestMapping("thread")
public class ThreadController extends AbstractController{

    @Autowired
    private ThreadService threadService;

    @RequestMapping(value = "all/mail/send",method = RequestMethod.GET)
    public BaseResponse sendAllUerEmail(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            threadService.sendAllEmailsV1();

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "all/insert/data",method = RequestMethod.GET)
    public BaseResponse insertAllData(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            long start = System.currentTimeMillis();
            threadService.insertDatas();
            long end = System.currentTimeMillis();
            log.info("mysql写20万条数据耗时{}毫秒：" , (end - start));
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


    @Autowired
    private CodesMapper codesMapper;


    @Resource
    private RedisTemplate redisTemplate;


    /**
     * 测试在高并发下多线程生成订单编号-传统的随机数生成方法
     * @return
     */
    @RequestMapping(value = "/code/generate/thread",method = RequestMethod.GET)
    public BaseResponse codeThread(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            ExecutorService executorService= Executors.newFixedThreadPool(20);
            long start = System.currentTimeMillis();
            for (int i=0;i<20000;i++){
                executorService.execute(new CodeGenerateThread(codesMapper,redisTemplate));
            }
            long end = System.currentTimeMillis();
            log.info("mysql写20万条数据耗时{}毫秒：" , (end - start));
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


    /**
     * 对应pom.xml jedis依赖
     */

    public static void main(String[] args) {
//         String REDIS_KEY = "REDISKEY";
//        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        Pipeline pipeline = jedis.pipelined();
//        long startTime = System.currentTimeMillis();
//        IntStream.range(0, 200000).forEach(it -> pipeline.hset(REDIS_KEY,REDIS_KEY+it, it + ""));
//        pipeline.syncAndReturnAll();
//        long endTime = System.currentTimeMillis();
//        System.out.println(endTime - startTime);
    }


}






























