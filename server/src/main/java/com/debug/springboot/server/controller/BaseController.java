package com.debug.springboot.server.controller;/**
 * Created by Administrator on 2019/8/24.
 */

import com.alibaba.dubbo.common.utils.StringUtils;
import com.debug.springboot.api.enums.StatusCode;
import com.debug.springboot.api.response.BaseResponse;
import com.debug.springboot.server.dto.MenuDto;
import com.debug.springboot.server.interview.Date20190925.CaseRequest;
import com.debug.springboot.server.interview.Date20190925.two.*;
import com.debug.springboot.server.service.IBaseService;
import com.debug.springboot.server.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/8/24 16:07
 **/
@RestController
@RequestMapping("base")
public class BaseController {

    private static final Logger log= LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private IBaseService baseService;

    /**
     * json交互获取基本的信息一
     * @param name
     * @return
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public BaseResponse info(String name){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            if (StringUtils.isBlank(name)){
                name="这里是程序员实战基地：http://www.fightjava.com";
            }
            response.setData(name);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * json交互获取基本的信息二
     * @param id
     * @return
     */
    @RequestMapping(value = "/item",method = RequestMethod.GET)
    public BaseResponse item(@RequestParam(required = false,defaultValue = "1") Integer id){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            response.setData(baseService.getItem(id));

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    @Autowired
    private InitCaseBeanMapComponent mapComponent;

    @RequestMapping(value = "/switch/case",method = RequestMethod.GET)
    public BaseResponse caseInfo(@RequestParam String type,@RequestParam String name){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            CaseRequest request=new CaseRequest(type,name);

            CaseEnum caseEnum=CaseEnum.valueOf(request.getType());
            CaseInterface caseInterface= mapComponent.getProcessMap().get(caseEnum);
            response.setData(caseInterface.execute(request));
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/menu",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse menu(@RequestBody MenuDto menuDto){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            menuService.manageMenu(menuDto);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
}



































