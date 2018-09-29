package com.codermi.blog.common.data.po;

import org.springframework.data.annotation.Id;

/**
 * @author qiudm
 * @date 2018/9/29 17:03
 * @desc
 */
public abstract class BaseInfo {

    @Id
    private String id;

    private Long updateTime;

    private Long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
