package com.debug.springboot.server.enums;/**
 * Created by Administrator on 2019/10/14.
 */

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/10/14 22:50
 **/
public class ItemCompareEnum {

    private static Map<String,Object> fieldMap;

    static{
        fieldMap=Maps.newConcurrentMap();
        fieldMap.put("name","商品名称");
        fieldMap.put("code","商品编码");
        fieldMap.put("stock","商品库存");
        fieldMap.put("purchaseTime","采购时间");
        fieldMap.put("isActive","是否有效");
    }

    public static Map<String,Object> getFieldMap(){
        return fieldMap;
    }
}






















