package com.debug.springboot.server.service.impl;/**
 * Created by Administrator on 2019/8/24.
 */

import com.debug.springboot.model.entity.primary.Item;
import com.debug.springboot.model.mapper.primary.ItemMapper;
import com.debug.springboot.server.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/8/24 17:18
 **/
@Service
public class BaseServiceImpl implements IBaseService{

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Item getItem(Integer id) throws Exception {
        return itemMapper.selectByPrimaryKey(id);
    }
}