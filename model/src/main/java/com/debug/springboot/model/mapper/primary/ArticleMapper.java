package com.debug.springboot.model.mapper.primary;

import com.debug.springboot.model.entity.primary.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    void deleteBatch(@Param("ids") String ids);

    List<Article> pageSelect(@Param("search") String search);

    List<Article> list();

    Article selectById(@Param("id") Integer id);
}