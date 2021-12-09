package com.debug.springboot.test;

import com.debug.springboot.server.MainApplication;
import com.debug.springboot.server.mongo.MongoUser;
import com.debug.springboot.server.mongo.MongoUserRepository;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/10/8 22:57
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MongoTest2 {

    private static final Logger log= LoggerFactory.getLogger(MongoTest2.class);

    @Autowired
    private MongoUserRepository mongoUserRepository;

    @Test
    public void method1() throws Exception{
        log.info("---单元测试1---");

        List<MongoUser> list=mongoUserRepository.queryAll();
        log.info("---查询所有：{}---",list);

        MongoUser mongoUser=new MongoUser(null,"曹操","20010","caocao@qq.com",1);
        mongoUserRepository.save(mongoUser);
        log.info("-----插入成功-----");

        list=mongoUserRepository.queryAll();
        log.info("---查询所有：{}---",list);
    }

    @Test
    public void method2() throws Exception{
        log.info("---单元测试2---");

        List<MongoUser> list= Lists.newLinkedList();
        list.add(new MongoUser(null,"宋江","40021","40021@126.com",1));
        list.add(new MongoUser(null,"卢俊义","40022","40022@126.com",1));
        list.add(new MongoUser(null,"吴用","40023","40023@126.com",1));
        list.add(new MongoUser(null,"鲁智深","40024","40024@126.com",1));
        list.add(new MongoUser(null,"武松","40025","40025@126.com",1));

        list.add(new MongoUser(null,"曹操","50001","50001@qq.com",1));
        list.add(new MongoUser(null,"刘备","50002","50002@qq.com",1));
        list.add(new MongoUser(null,"孙权","50003","50003@qq.com",1));
        list.add(new MongoUser(null,"关羽","50004","50004@qq.com",1));
        list.add(new MongoUser(null,"赵云","50005","50005@qq.com",1));

        mongoUserRepository.saveAll(list);
        log.info("-----插入成功-----");

        list=mongoUserRepository.queryAll();
        log.info("---查询所有：{}---",list);
    }

    @Test
    public void method3() throws Exception{
        log.info("---单元测试3---");

        final Integer id=343686;
        MongoUser entity=mongoUserRepository.queryById(id);
        log.info("---根据主键id查询={},结果={}---",id,entity);

        final String name="张飞";
        List<MongoUser> list=mongoUserRepository.queryByName(name);
        log.info("---根据姓名查询,name={},结果={}---",name,list);
    }

    @Test
    public void method4() throws Exception{
        log.info("---单元测试4---");

        MongoUser entity=new MongoUser(343686,"李四","30023","lisi@sina.com",1);
        mongoUserRepository.update(entity);

        entity=mongoUserRepository.queryById(343686);
        log.info("---根据主键id查询,结果={}---",entity);
    }

    @Test
    public void method5() throws Exception{
        log.info("---单元测试5-删除---");

        final Integer id=343686;
        mongoUserRepository.delete(id);

        MongoUser entity=mongoUserRepository.queryById(id);
        log.info("---根据主键id查询,结果={}---",entity);
    }


    @Test
    public void method6() throws Exception{
        log.info("---单元测试6-分页查询---");

        final Integer pageSize=10;
        Integer pageNo=1;

        Map<String,Object> resMap=mongoUserRepository.queryPage(pageNo,pageSize);
        log.info("---分页查询pageNo={} pageSize={} 结果={}\n\n",pageNo,pageSize,resMap);

        pageNo=2;
        resMap=mongoUserRepository.queryPage(pageNo,pageSize);
        log.info("---分页查询pageNo={} pageSize={} 结果={}\n\n",pageNo,pageSize,resMap);

        pageNo=3;
        resMap=mongoUserRepository.queryPage(pageNo,pageSize);
        log.info("---分页查询pageNo={} pageSize={} 结果={}\n\n",pageNo,pageSize,resMap);

        pageNo=800;
        resMap=mongoUserRepository.queryPage(pageNo,pageSize);
        log.info("---分页查询pageNo={} pageSize={} 结果={}\n\n",pageNo,pageSize,resMap);

        pageNo=1000;
        resMap=mongoUserRepository.queryPage(pageNo,pageSize);
        log.info("---分页查询pageNo={} pageSize={} 结果={}\n\n",pageNo,pageSize,resMap);

        pageNo=2000;
        resMap=mongoUserRepository.queryPage(pageNo,pageSize);
        log.info("---分页查询pageNo={} pageSize={} 结果={}\n\n",pageNo,pageSize,resMap);
    }
}


























