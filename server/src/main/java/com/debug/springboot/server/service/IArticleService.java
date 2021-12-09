package com.debug.springboot.server.service;

import com.debug.springboot.model.entity.primary.Article;
import com.debug.springboot.server.dto.ArticleQueryDto;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2019/8/27.
 */
public interface IArticleService {

    Integer saveEntity(Article article) throws Exception;

    void updateEntity(Article article) throws Exception;

    void deleteEntity(Set<Integer> ids) throws Exception;

    PageInfo<Article> pageList(ArticleQueryDto dto) throws Exception;

    List<Article> list() throws Exception;

    Article info(Integer id) throws Exception;
}
