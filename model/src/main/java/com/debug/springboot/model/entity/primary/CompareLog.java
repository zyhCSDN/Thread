package com.debug.springboot.model.entity.primary;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CompareLog implements Serializable{
    private Integer id;

    private String code;

    private String name;

    private String oldVal;

    private String newVal;

    private Date createTime;

}