package com.fqh.utilities.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ReFolderFileEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 文件Id
     */
    private String fileId;

    /**
     * 文件夹Id
     */
    private String folderId;

    /**
     * 排序
     */
    private Integer ranking;

}
