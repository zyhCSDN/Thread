package com.debug.springboot.server.utils;/**
 * Created by Administrator on 2019/8/1.
 */

import org.springframework.validation.BindingResult;

/**
 * 请求参数统一校验工具
 * @Author:debug (SteadyJack)
 * @Date: 2019/8/27 11:52
 **/
public class ValidatorUtil {

    //统一处理校验的结果
    public static String checkResult(BindingResult result){
        StringBuilder sb=new StringBuilder("");

        if (result!=null && result.hasErrors()){
            //java8 stream写法
            result.getAllErrors().stream().forEach(error -> sb.append(error.getDefaultMessage()).append("\n"));
        }

        return sb.toString();
    }

}