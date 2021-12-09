package com.debug.springboot.server.interview.Date20190925.one;/**
 * Created by Administrator on 2019/9/25.
 */

import com.debug.springboot.server.interview.Date20190925.CaseRequest;
import com.debug.springboot.server.interview.Date20190925.CaseResponse;

/**
 * spring boot替换复杂的switch case
 * @Author:debug (SteadyJack)
 * @Date: 2019/9/25 19:35
 **/
public class SwitchCaseExecute {

    public static CaseResponse execute(CaseRequest request){
        CaseResponse response;
        switch (request.getType()){
            case "A":
                response= methodA(request);
                break;
            case "B":
                response= methodB(request);
                break;
            default:
                response= methodC(request);
                break;
        }
        return response;
    }

    public static CaseResponse methodA(CaseRequest request){
        CaseResponse response=new CaseResponse();
        response.setResult("A"+request.getName());
        return response;
    }

    public static CaseResponse methodB(CaseRequest request){
        CaseResponse response=new CaseResponse();
        response.setResult("B"+request.getName());
        return response;
    }

    public static CaseResponse methodC(CaseRequest request){
        CaseResponse response=new CaseResponse();
        response.setResult("C"+request.getName());
        return response;
    }

    public static void main(String[] args) {
        CaseRequest request=new CaseRequest("D","这是名字");
        System.out.println(execute(request));
    }

}





















