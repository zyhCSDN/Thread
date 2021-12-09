package com.debug.springboot.server.interview.Date20190925.two;/**
 * Created by Administrator on 2019/9/25.
 */

import com.debug.springboot.server.interview.Date20190925.CaseRequest;
import org.springframework.stereotype.Component;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/9/25 20:35
 **/
@Component
@CaseAnnotation(value = CaseEnum.F)
public class CaseFImpl implements CaseInterface{

    @Override
    public String execute(CaseRequest request) throws Exception {
        return "结果：F -- "+request.getName();
    }
}