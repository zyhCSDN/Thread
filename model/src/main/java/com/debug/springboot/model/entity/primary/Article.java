package com.debug.springboot.model.entity.primary;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class Article {

    private Integer id;

    @NotBlank(message = "文章标题不能为空")
    private String title;

    @NotNull
    private Integer userId;


    //子查询映射
    private List<Comment> comments;

    //作者名字
    private String userName;
}