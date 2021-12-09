package com.debug.springboot.server.controller;

import com.debug.springboot.api.enums.StatusCode;
import com.debug.springboot.api.response.BaseResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/8/24 17:25
 **/
@RestController
@RequestMapping("mongo")
public class MongoController extends AbstractController{

    @RequestMapping("/info")
    public BaseResponse info(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        response.setData(1);
        return response;
    }

}



























