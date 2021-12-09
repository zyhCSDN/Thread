package com.debug.springboot.model.entity.primary;

import lombok.Data;

@Data
public class Comment {
    private Integer id;

    private String content;

    private Integer userId;

    private Integer articleId;
}