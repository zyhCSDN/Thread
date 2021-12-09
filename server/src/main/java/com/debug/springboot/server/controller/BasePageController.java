package com.debug.springboot.server.controller;/**
 * Created by Administrator on 2019/8/24.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/8/24 17:25
 **/
@Controller
@RequestMapping("base/page")
public class BasePageController extends AbstractController{

    @RequestMapping("/info")
    public String info(ModelMap modelMap){
        modelMap.put("code","天王盖第五");
        return "page";
    }

}



























