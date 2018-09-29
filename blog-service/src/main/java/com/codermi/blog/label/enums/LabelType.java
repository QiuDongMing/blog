package com.codermi.blog.label.enums;

/**
 * @author qiudm
 * @date 2018/7/30 10:24
 * @desc
 */
public enum LabelType {

    ARITCLE(1, "文章");

    private Integer type;

    private String desc;

    LabelType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }
}
