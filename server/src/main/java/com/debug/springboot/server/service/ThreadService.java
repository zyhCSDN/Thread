package com.debug.springboot.server.service;

import com.debug.springboot.model.mapper.primary.DataMapper;
import com.debug.springboot.model.mapper.primary.UserMapper;
import com.debug.springboot.server.dto.ThreadInsertDataDto;
import com.debug.springboot.server.dto.ThreadQueryDataDto;
import com.debug.springboot.server.dto.ThreadEmailDto;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 邮件服务service
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/11/7 17:53
 **/
@Service
public class ThreadService {

    private static final Logger log= LoggerFactory.getLogger(ThreadService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailSendService emailSendService;

    //TODO:给所有用户群发一封通知邮件 - 多线程（可以拓展的案例：WebSocket场景下给所有在线的用户发送通知消息）
    public void sendAllEmailsV1() throws Exception{
        Set<String> set=userMapper.selectAllUserEmails();
        log.info("----给所有用户发送一封通知邮件,用户列表：{}",set);


        if (set!=null && !set.isEmpty()){
            //TODO:多线程并发-广播发送邮件
            ExecutorService executorService=Executors.newFixedThreadPool(10);

            List<ThreadEmailDto> list= Lists.newLinkedList();
            set.forEach(s -> list.add(new ThreadEmailDto(s,"双11课程优惠！","所有课程在关注公众号后可享受优惠价",emailSendService)));

            executorService.invokeAll(list);
        }
    }




    @Autowired
    private DataMapper dataMapper;

    @Autowired
    private DataService dataService;


    //TODO:批量拉取、插入数据 - 30多w(353592)
    public void insertDatas() throws Exception{
        //TODO:总线程数 10
        final Integer threadSize=20;

        //TODO:总数据量 368604
        final Long total=dataMapper.getTotal();
//        final Long total=200000L;

        if (total>0){
            //TODO:每个线程将执行插入的数据条目
            Long pageSize=(total%threadSize==0)?total/threadSize:total/threadSize+1;

            Set<String> datas;
            ExecutorService executorService=Executors.newFixedThreadPool(threadSize);

            List<ThreadInsertDataDto> list=Lists.newLinkedList();
            for (Long i=1L;i<=threadSize;i++){
                //TODO:将每个线程将执行的具体条目的记录拿出来
                if (Objects.equals(i, threadSize)){
                    pageSize = total - (threadSize-1) * pageSize;
                }
                datas=dataService.pageLimitData(i,pageSize);

                //TODO：构造线程实例
                list.add(new ThreadInsertDataDto(dataService,datas));
            }
            //TODO:多线程批量插入数据-逻辑
            executorService.invokeAll(list);
        }
    }
}



































