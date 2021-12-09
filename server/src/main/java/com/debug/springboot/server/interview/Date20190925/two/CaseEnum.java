package com.debug.springboot.server.interview.Date20190925.two;

/**
 * Created by Administrator on 2019/9/25.
 */
public enum CaseEnum {
    A("A"),
    B("B"),
    C("C"),

    F("F"),

    ;

    private String type;

    CaseEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
