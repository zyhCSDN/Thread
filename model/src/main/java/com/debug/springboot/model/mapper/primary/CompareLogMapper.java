package com.debug.springboot.model.mapper.primary;

import com.debug.springboot.model.entity.primary.CompareLog;

public interface CompareLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompareLog record);

    int insertSelective(CompareLog record);

    CompareLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompareLog record);

    int updateByPrimaryKey(CompareLog record);
}