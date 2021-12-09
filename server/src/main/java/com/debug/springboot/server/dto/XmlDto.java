package com.debug.springboot.server.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/12/13 10:00
 **/
@XmlRootElement(name = "Request")
@Data
public class XmlDto implements Serializable{

    private String smsType;
    private String fromNum;

}