package com.debug.springboot.server.controller;

import com.debug.springboot.api.enums.StatusCode;
import com.debug.springboot.api.response.BaseResponse;
import com.debug.springboot.server.dto.UserDto;
import com.debug.springboot.server.service.JdbcTemplateService;
import com.debug.springboot.server.utils.ValidatorUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * spring jdbcTemplate
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/11/11 21:33
 **/
@RestController
@RequestMapping("jdbc/template/user")
public class JbdcTemplateController extends AbstractController{

    @Autowired
    private JdbcTemplateService jdbcTemplateService;

    //TODO:新增
    @RequestMapping(value = "add",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse info(@RequestBody @Validated UserDto userDto, BindingResult result){
        String checkRes=ValidatorUtil.checkResult(result);
        if (StringUtils.isNotBlank(checkRes)){
            return new BaseResponse(StatusCode.InvalidParams.getCode(),checkRes);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            int res=jdbcTemplateService.addUser(userDto);
            response.setData(res);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    //TODO:查询一个
    @RequestMapping(value = "query/one",method = RequestMethod.GET)
    public BaseResponse queryOne(@RequestParam Integer id){
        if (id<=0){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            response.setData(jdbcTemplateService.queryUserById(id));

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    //TODO:查询列表
    @RequestMapping(value = "query/list",method = RequestMethod.GET)
    public BaseResponse queryList(@RequestParam String search){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap= Maps.newHashMap();
        try {
            resMap.put("数据列表-v1",jdbcTemplateService.queryListV1(search));
            resMap.put("数据列表-v2",jdbcTemplateService.queryListV2(search));

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        response.setData(resMap);
        return response;
    }

    //TODO:删除
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResponse delete(@RequestParam Integer id){
        if (id<=0){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            response.setData(jdbcTemplateService.delete(id));

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
}






























