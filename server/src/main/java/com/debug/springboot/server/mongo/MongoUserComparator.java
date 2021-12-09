package com.debug.springboot.server.mongo;

import java.util.Comparator;

/**
 * 定义排序规则
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/11/13 18:18
 **/
public class MongoUserComparator implements Comparator<MongoUser>{

    @Override
    public int compare(MongoUser o1, MongoUser o2) {
        return o2.getCode().compareTo(o1.getCode());
    }
}