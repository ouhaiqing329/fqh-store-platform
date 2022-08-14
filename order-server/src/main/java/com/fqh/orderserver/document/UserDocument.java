package com.fqh.orderserver.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


/**
 * 用户文档
 *
 * @author fqh
 * @date 2022/08/13
 */
@Data
@Document(indexName = "user-doc",createIndex = true)
public class UserDocument {

    @Id
    private Long id;

    private String username;

    private Integer age;

}
