package com.debug.springboot.server.interview.Date20190925.two;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2019/9/25.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CaseAnnotation {
    CaseEnum value();
}
