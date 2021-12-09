package com.debug.springboot.server.thread;/**
 * Created by Administrator on 2019/7/11.
 */


import com.debug.springboot.model.entity.primary.Codes;
import com.debug.springboot.model.mapper.primary.CodesMapper;
import com.debug.springboot.server.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author:zyh
 * @Date: 2021/5/11 10:30
 **/
public class CodeGenerateThread implements Runnable{

    public static final SnowFlake SNOW_FLAKE=new SnowFlake(2,3);


    private RedisTemplate redisTemplate;

    private static final String REDIS_KEY = "RADISHES:ISSTRING";


    private CodesMapper codesMapper;

    public CodeGenerateThread(CodesMapper codesMapper ,RedisTemplate redisTemplate) {
        this.codesMapper = codesMapper;
        this.redisTemplate = redisTemplate;
    }
    @Override
    public void run() {
        ListOperations<String, Codes> listOperations = redisTemplate.opsForList();
        Codes entity=new Codes();
        entity.setItemId(SNOW_FLAKE.nextId());
//        codesMapper.insertSelective(entity);
        listOperations.leftPush(REDIS_KEY,entity);
//        redisTemplate.expire(REDIS_KEY,60L, TimeUnit.SECONDS);
    }
}