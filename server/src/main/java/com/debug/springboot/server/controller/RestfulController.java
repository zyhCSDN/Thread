package com.debug.springboot.server.controller;

import com.debug.springboot.api.enums.StatusCode;
import com.debug.springboot.api.response.BaseResponse;
import com.debug.springboot.model.entity.primary.User;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/11/17 12:27
 **/
@RestController
@RequestMapping("rest")
public class RestfulController extends AbstractController{

    @RequestMapping(value = "info",method = RequestMethod.GET)
    private BaseResponse info(@RequestParam Integer id, HttpServletResponse servletResponse){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            RestTemplate restTemplate=new RestTemplate();
            final String url="http://localhost:8081/technology/jdbc/template/user/query/one?id={id}";

            Map<String,Object> uriMap= Maps.newHashMap();
            uriMap.put("id",id);
            ResponseEntity<Map> responseEntity=restTemplate.getForEntity(url, Map.class,uriMap);

            response.setData(responseEntity.getBody());
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;

    }


}




































