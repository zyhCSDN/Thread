package com.debug.springboot.server.controller;

import com.debug.springboot.api.enums.StatusCode;
import com.debug.springboot.api.response.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/11/10 23:10
 **/
@RestController
@RequestMapping("security")
public class SecurityController extends AbstractController{

    @RequestMapping("info/one")
    public BaseResponse infoOne(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            response.setData("security 安全框架");

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

}

































