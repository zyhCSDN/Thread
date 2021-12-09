package com.debug.springboot.server.service;

import com.debug.springboot.model.entity.primary.Item;

/**
 * Created by Administrator on 2019/8/24.
 */
public interface IBaseService {

    Item getItem(Integer id) throws Exception;

}
