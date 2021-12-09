package com.debug.springboot.model.entity.primary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: Zhaoyongheng
 *
 * @date: 2021/5/18 
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Codes {
    private Integer id;

    private Long itemId;
}