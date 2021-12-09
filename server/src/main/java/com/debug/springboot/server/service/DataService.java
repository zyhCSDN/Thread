package com.debug.springboot.server.service;

import com.debug.springboot.model.entity.primary.ItemData;
import com.debug.springboot.model.entity.primary.User;
import com.debug.springboot.model.mapper.primary.DataMapper;
import com.debug.springboot.model.mapper.primary.ItemDataMapper;
import com.debug.springboot.model.mapper.primary.UserMapper;
import com.debug.springboot.server.mongo.MongoUser;
import com.debug.springboot.server.mongo.MongoUserRepository;
import com.debug.springboot.server.utils.SnowFlake;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/11/8 11:38
 **/
@Service
public class DataService {

    private static final Logger log= LoggerFactory.getLogger(DataService.class);

    @Autowired
    private DataMapper dataMapper;

    //TODO:分页获取数据
    public Set<String> pageLimitData(final Long pageNo,final Long size){
        log.info("---开始分页拉取数据：pageNo={} size={} ",pageNo,size);

        Set<String> set= Sets.newHashSet();
        if (pageNo>0 && size>0){
            set=dataMapper.getAllItemIds((pageNo-1)*size,size);
        }
        return set;
    }



    @Autowired
    private ItemDataMapper itemDataMapper;


    //TODO:批量插入数据
//    @Async("taskDataExecutor")  注解式线程
    public void insertBatchData(Set<String> set){
        log.info("----开始批量插入数据----");
        List<ItemData> list= Lists.newLinkedList();
        //TODO:真正的插入数据的业务逻辑
        set.forEach(s -> {
            ItemData data=new ItemData(null,s,1);
            list.add(data);
        });

        itemDataMapper.insertBatch(list);
    }


    @Autowired
    private MongoUserRepository mongoUserRepository;

    private static final SnowFlake SNOW_FLAKE=new SnowFlake(3,2);

    @Autowired
    private UserMapper userMapper;


    //TODO:批量插入数据2
    @Async("taskDataExecutor")
    public void insertBatchData2(Set<String> set){
        log.info("----开始批量插入数据-用于mongodb批量查询----");

        //TODO:真正的插入数据的业务逻辑
        set.forEach(s -> {

            //TODO:添加进mongodb
            MongoUser data=new MongoUser(null,String.valueOf(SNOW_FLAKE.nextId()),s,s+"@qq.com",1);
            try {
                mongoUserRepository.save(data);
            }catch (Exception e){
                e.printStackTrace();
            }
        });


    }
}

































