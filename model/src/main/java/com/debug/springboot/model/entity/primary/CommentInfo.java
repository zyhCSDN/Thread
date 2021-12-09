package com.debug.springboot.model.entity.primary;

import lombok.Data;

@Data
public class CommentInfo {
    private Integer cId;

    private String comment;

    private Integer cUserId;
}