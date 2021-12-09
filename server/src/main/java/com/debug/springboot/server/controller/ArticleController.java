package com.debug.springboot.server.controller;/**
 * Created by Administrator on 2019/8/27.
 */

import com.debug.springboot.api.enums.StatusCode;
import com.debug.springboot.api.response.BaseResponse;
import com.debug.springboot.model.entity.primary.Article;
import com.debug.springboot.server.dto.ArticleQueryDto;
import com.debug.springboot.server.dto.DeleteDto;
import com.debug.springboot.server.service.IArticleService;
import com.debug.springboot.server.utils.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/8/27 9:39
 **/
@RestController
@RequestMapping("article")
public class ArticleController extends AbstractController{

    @Autowired
    public IArticleService articleService;

    //新增
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public BaseResponse save(@RequestBody @Validated Article article, BindingResult result){
        String error=ValidatorUtil.checkResult(result);
        if (StringUtils.isNotBlank(error)){
            return new BaseResponse(StatusCode.Fail.getCode(),error);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            Integer id=articleService.saveEntity(article);
            response.setData(id);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    //更新
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public BaseResponse update(@RequestBody @Validated Article article, BindingResult result){
        String error=ValidatorUtil.checkResult(result);
        if (StringUtils.isNotBlank(error)){
            return new BaseResponse(StatusCode.Fail.getCode(),error);
        }
        if (article.getId()==null || article.getId()<=0){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            articleService.updateEntity(article);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    //查询
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public BaseResponse query(@Validated ArticleQueryDto dto, BindingResult result){
        String error=ValidatorUtil.checkResult(result);
        if (StringUtils.isNotBlank(error)){
            return new BaseResponse(StatusCode.Fail.getCode(),error);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            response.setData(articleService.pageList(dto));
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    //删除
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResponse delete(@RequestBody DeleteDto dto){
        if (dto.getIds()==null || dto.getIds().isEmpty()){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            articleService.deleteEntity(dto.getIds());
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


    //多表关联-平级
    @RequestMapping(value = "info/{id}")
    public BaseResponse info(@PathVariable Integer id){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            response.setData(articleService.info(id));
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    //文章列表（带子查询）
    @RequestMapping(value = "list")
    public BaseResponse list(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            response.setData(articleService.list());
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
}

























