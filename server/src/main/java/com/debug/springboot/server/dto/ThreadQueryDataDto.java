package com.debug.springboot.server.dto;

import com.debug.springboot.server.service.DataService;

import java.util.Set;
import java.util.concurrent.Callable;

/**
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/11/8 12:08
 **/
public class ThreadQueryDataDto implements Callable<Set<String>>{

    private DataService dataService;

    private Long pageNo;
    private Long size;

    public ThreadQueryDataDto(DataService dataService, Long pageNo, Long size) {
        this.dataService = dataService;
        this.pageNo = pageNo;
        this.size = size;
    }

    //TODO:多线程拉取数据
    @Override
    public Set<String> call() throws Exception {
        return dataService.pageLimitData(pageNo,size);
    }
}