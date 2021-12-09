package com.debug.springboot.server.interview.Date20190925.two;/**
 * Created by Administrator on 2019/9/25.
 */

import com.debug.springboot.server.interview.Date20190925.CaseRequest;
import com.debug.springboot.server.interview.Date20190925.CaseResponse;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/9/25 20:34
 **/
public interface CaseInterface {

    String execute(CaseRequest request) throws Exception;

}