package com.debug.springboot.server.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/11/14 17:06
 **/
@Service
public class InitRunnerService {

    private static final Logger log= LoggerFactory.getLogger(InitRunnerService.class);

    @PostConstruct
    public void init(){
        log.info("--@PostConstruct方式-SpringBoot容器启动之后执行一些初始化的内容....--");

    }
}