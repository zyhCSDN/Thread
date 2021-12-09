package com.debug.springboot.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * web-flux reactor编程
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/11/15 16:16
 **/
@RestController
@RequestMapping("web/flux")
public class WebFluxController extends AbstractController{

    @RequestMapping(value = "info",method = RequestMethod.GET)
    public Mono<String> hello(){
        final String data="这是web-flux reactor编程实体";
        return Mono.just(data);

    }

}
































