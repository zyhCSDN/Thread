package com.debug.springboot.server.interview.Date20190925;/**
 * Created by Administrator on 2019/9/25.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 请求体
 * @Author:debug (SteadyJack)
 * @Date: 2019/9/25 19:46
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaseRequest implements Serializable{
    private String type;

    private String name;
}