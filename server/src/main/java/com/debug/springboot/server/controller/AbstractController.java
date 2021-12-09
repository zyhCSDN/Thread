package com.debug.springboot.server.controller;/**
 * Created by Administrator on 2019/8/24.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/8/24 16:07
 **/
@Controller
public abstract class AbstractController {

    protected final Logger log=LoggerFactory.getLogger(getClass());

}