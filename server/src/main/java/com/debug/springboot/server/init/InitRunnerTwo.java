package com.debug.springboot.server.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * spring boot项目启动之后执行一些初始化的内容2
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/11/14 16:49
 **/
@Component
@Order(2)
public class InitRunnerTwo implements CommandLineRunner{

    private static final Logger log= LoggerFactory.getLogger(InitRunnerTwo.class);

    //TODO:SpringBoot容器启动之后执行一些初始化的内容....
    @Override
    public void run(String... strings) throws Exception {
        log.info("--2~SpringBoot容器启动之后执行一些初始化的内容....--");

    }
}




















