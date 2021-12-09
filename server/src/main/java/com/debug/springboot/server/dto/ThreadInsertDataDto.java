package com.debug.springboot.server.dto;/**
 * Created by Administrator on 2019/11/8.
 */

import com.debug.springboot.server.service.DataService;

import java.util.Set;
import java.util.concurrent.Callable;

/**
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/11/8 16:15
 **/
public class ThreadInsertDataDto implements Callable<Boolean>{

    private DataService dataService;
    private Set<String> set;

    public ThreadInsertDataDto(DataService dataService, Set<String> set) {
        this.dataService = dataService;
        this.set = set;
    }

    //TODO:实际的插入数据到数据库表的真正逻辑
    @Override
    public Boolean call() throws Exception {
        if (dataService!=null){
            dataService.insertBatchData(set);
//            dataService.insertBatchData2(set);
        }
        return true;
    }
}