package com.debug.springboot.server.rest;

import com.debug.springboot.api.enums.StatusCode;
import com.debug.springboot.api.response.BaseResponse;
import com.debug.springboot.model.entity.primary.Item;
import com.debug.springboot.model.mapper.primary.ItemMapper;
import com.debug.springboot.server.controller.AbstractController;
import com.google.common.collect.Maps;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/11/17 15:10
 **/
@RestController
@RequestMapping("rest/item")
public class ItemController extends AbstractController{

    private static final RestTemplate restTemplate=new RestTemplate();

    @Autowired
    private ItemMapper itemMapper;

    @RequestMapping(value = "info",method = RequestMethod.GET)
    private ResponseEntity<Item> info(@RequestParam Integer id){
        ResponseEntity<Item> response;
        try {
            Item item=itemMapper.selectByPrimaryKey(id);
            if (item!=null){
                response=new ResponseEntity<>(item,HttpStatus.OK);
            }else{
                response=new ResponseEntity<>(item,HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            response=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;

    }

    @RequestMapping(value = "add",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ResponseEntity<Integer> add(@RequestBody Item item){
        ResponseEntity<Integer> response;
        try {
            item.setCreateTime(DateTime.now().toDate());
            item.setPurchaseTime(DateTime.now().toDate());
            item.setId(null);
            int res=itemMapper.insertSelective(item);

            if (res>0){
                response=new ResponseEntity<>(item.getId(),HttpStatus.OK);
            }else{
                response=new ResponseEntity<>(-1,HttpStatus.OK);
            }
        }catch (Exception e){
            response=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }


}



























