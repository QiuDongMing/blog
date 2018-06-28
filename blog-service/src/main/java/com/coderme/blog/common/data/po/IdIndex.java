package com.coderme.blog.common.data.po;

import com.coderme.blog.common.enums.CommonEnums;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author qiudm
 * @date 2018/6/28 11:05
 * @desc
 */
@Document(collection = "t_idIndex")
@Data
public class IdIndex {

    /**
     * @see CommonEnums.IdIndexDataType
     */
    private String dataType;

    private Long dataId;

}
