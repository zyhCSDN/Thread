package com.debug.springboot.model.mapper.primary;


import com.debug.springboot.model.entity.primary.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface DataMapper {

    Set<String> getAllItemIds(@Param("start") Long start,@Param("size") Long size);

    Long getTotal();
}