package com.codermi.blog.label.data.po;

import com.codermi.blog.label.enums.LabelType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author qiudm
 * @date 2018/6/29 10:10
 * @desc
 */
@Document(collection = "t_label")
@CompoundIndex(name = "label_name_type", def = "{'name':1,'type':1}", unique = true)
@Data
public class Label {

    @Id
    private String id;

    private String name;

    /**
     * @see LabelType
     */
    private Integer type;


    private Long updateTime;

    private Long createTime;

    /**
     * 标签使用量，计算热度
     */
    private Integer useCount;
}
