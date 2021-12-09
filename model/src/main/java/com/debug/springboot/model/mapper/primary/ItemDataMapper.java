package com.debug.springboot.model.mapper.primary;

import com.debug.springboot.model.entity.primary.ItemData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemData record);

    int insertSelective(ItemData record);

    ItemData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemData record);

    int updateByPrimaryKey(ItemData record);

    int insertBatch(@Param("datas") List<ItemData> datas);
}