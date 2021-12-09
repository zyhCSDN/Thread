package com.debug.springboot.server.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * mongoDB操作的实体 - 对象文档映射
 * @Author:debug (SteadyJack)
 * @Date: 2019/10/8 22:44
 **/
@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class MongoUser implements Serializable{

    @Id
    private Integer id;

    private String name;

    private String code;

    private String email;

    private Integer isActive=1;
}