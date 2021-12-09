package com.debug.springboot.model.mapper.primary;

import com.debug.springboot.model.entity.primary.Codes;

/**
 * @author: Zhaoyongheng
 *
 * @date: 2021/5/18 
 */
public interface CodesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Codes record);

    int insertSelective(Codes record);

    Codes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Codes record);

    int updateByPrimaryKey(Codes record);
}