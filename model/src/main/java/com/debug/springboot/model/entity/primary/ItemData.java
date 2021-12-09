package com.debug.springboot.model.entity.primary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemData implements Serializable{
    private Integer id;

    private String code;

    private Integer pId;

}