package com.debug.springboot.server.controller;/**
 * Created by Administrator on 2019/10/13.
 */

import com.debug.springboot.api.enums.StatusCode;
import com.debug.springboot.api.response.BaseResponse;
import com.debug.springboot.server.dto.ItemDto;
import com.debug.springboot.server.service.FieldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/10/13 22:44
 **/
@RestController
@RequestMapping("field")
public class FieldController {

    private static final Logger log= LoggerFactory.getLogger(FieldController.class);

    @Autowired
    private FieldService fieldService;

    @RequestMapping(value = "compare",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse compareFields(@RequestBody @Validated ItemDto dto, BindingResult result){
        if (result.hasErrors()){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            response.setData(fieldService.compare(dto));

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
}


































