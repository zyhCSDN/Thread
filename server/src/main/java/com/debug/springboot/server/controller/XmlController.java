package com.debug.springboot.server.controller;

import com.debug.springboot.api.enums.StatusCode;
import com.debug.springboot.api.response.BaseResponse;
import com.debug.springboot.server.dto.XmlDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * xml控制器
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/12/13 9:46
 **/
@RequestMapping("xml")
@RestController
public class XmlController extends AbstractController{

    private static final Logger log= LoggerFactory.getLogger(XmlController.class);

    @RequestMapping(value = "info",method = RequestMethod.POST,consumes = MediaType.APPLICATION_XML_VALUE)
    public BaseResponse info(@RequestBody XmlDto dto){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            log.info("----接收到消息：{}",dto);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }




}






































