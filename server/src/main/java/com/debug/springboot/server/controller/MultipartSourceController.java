package com.debug.springboot.server.controller;

import com.debug.springboot.api.enums.StatusCode;
import com.debug.springboot.api.response.BaseResponse;
import com.debug.springboot.model.entity.primary.User;
import com.debug.springboot.model.entity.second.SysConfig;
import com.debug.springboot.model.mapper.primary.ItemMapper;
import com.debug.springboot.model.mapper.primary.UserMapper;
import com.debug.springboot.model.mapper.second.SysConfigMapper;
import com.debug.springboot.server.annotation.LogAnnotation;
import com.debug.springboot.server.dto.LogUpdateDto;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 多数据源controller
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/11/7 10:37
 **/
@RestController
@RequestMapping("multipart/source")
public class MultipartSourceController {

    private static final Logger log= LoggerFactory.getLogger(MultipartSourceController.class);

    //TODO:从数据源的
    @Autowired
    private SysConfigMapper sysConfigMapper;

    //TODO:主数据源的
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public BaseResponse list(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap= Maps.newHashMap();
        try {
            resMap.put("主数据源",userMapper.selectByPrimaryKey(11));
            resMap.put("从数据源",sysConfigMapper.selectActiveConfigs());

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        response.setData(resMap);
        return response;
    }

    //TODO:多数据源时-跨事务
    @RequestMapping(value = "add",method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse add(@RequestParam String name) throws Exception{
        BaseResponse response=new BaseResponse(StatusCode.Success);

        User user=new User();
        user.setName(name);
        user.setCode("100");
        userMapper.insertSelective(user);

        SysConfig config=new SysConfig();
        config.setName(name);
        //有些字典必填，故而插入时会报错，测试是否会回滚
        sysConfigMapper.insertSelective(config);

        return response;
    }


    @LogAnnotation("多数据源时-跨事务-日志操作")
    @RequestMapping(value = "update",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse update(@RequestBody LogUpdateDto dto) throws Exception{
        BaseResponse response=new BaseResponse(StatusCode.Success);

        User user=new User();
        user.setName(dto.getName());
        user.setCode(dto.getCode());
        userMapper.insertSelective(user);

        return response;
    }
}













