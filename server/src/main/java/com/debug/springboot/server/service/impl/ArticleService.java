package com.debug.springboot.server.service.impl;/**
 * Created by Administrator on 2019/8/27.
 */

import com.debug.springboot.model.entity.primary.Article;
import com.debug.springboot.model.mapper.primary.ArticleMapper;
import com.debug.springboot.server.dto.ArticleQueryDto;
import com.debug.springboot.server.service.IArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/8/27 9:40
 **/
@Service
public class ArticleService implements IArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Integer saveEntity(Article article) throws Exception {
        article.setId(null);
        articleMapper.insertSelective(article);
        //可以返回成功插入到数据时那条记录对应的主键id
        return article.getId();
    }

    @Override
    public void updateEntity(Article article) throws Exception {
        if (article.getId()!=null && article.getId()>=0){
            articleMapper.updateByPrimaryKeySelective(article);
        }
    }

    @Override
    public void deleteEntity(Set<Integer> ids) throws Exception {
        //第一种方式
        /*if (ids!=null && !ids.isEmpty()){
            ids.stream().forEach(id -> articleMapper.deleteByPrimaryKey(id));
        }*/

        //第二种方式
        if (ids!=null && !ids.isEmpty()){
            articleMapper.deleteBatch(Joiner.on(",").join(ids));
        }
    }

    @Override
    public PageInfo<Article> pageList(ArticleQueryDto dto) throws Exception {
        PageHelper.startPage(dto.getPageNo(),dto.getPageSize());
        return new PageInfo<>(articleMapper.pageSelect(dto.getSearch()));
    }


    //列表查询
    @Override
    public List<Article> list() throws Exception {
        return articleMapper.list();
    }

    //详情
    @Override
    public Article info(Integer id) throws Exception {
        return articleMapper.selectById(id);
    }
}