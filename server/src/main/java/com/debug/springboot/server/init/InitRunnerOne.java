package com.debug.springboot.server.init;

import com.debug.springboot.model.entity.second.SysConfig;
import com.debug.springboot.model.mapper.second.SysConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * spring boot项目启动之后执行一些初始化的内容1
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/11/14 16:49
 **/
@Component
@Order(1)
public class InitRunnerOne implements CommandLineRunner{

    private static final Logger log= LoggerFactory.getLogger(InitRunnerOne.class);

    @Autowired
    private SysConfigMapper sysConfigMapper;

    //TODO:SpringBoot容器启动之后执行一些初始化的内容：比如将一些频繁访问的数据字典塞至缓存中....
    @Override
    public void run(String... strings) throws Exception {
        log.info("--1~SpringBoot容器启动之后执行一些初始化的内容....--");

        SysConfig sysConfig=sysConfigMapper.selectByPrimaryKey(5);
        log.info("--项目启动成功后初始化资源：{}",sysConfig);
    }
}




















