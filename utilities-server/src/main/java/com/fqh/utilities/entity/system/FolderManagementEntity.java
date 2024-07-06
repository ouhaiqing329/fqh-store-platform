package com.fqh.utilities.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "folder_management")
public class FolderManagementEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 上级文件夹Id
     */
    private String parentFolderId;


    /**
     * 文件夹名称
     */
    private String folderName;


    /**
     * 排序
     */
    private Integer ranking;


    /**
     * 是否可读
     */
    private String canRead;


    /**
     * 创建人
     */
    private String creator;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    /**
     * 更新人
     */
    private String modifier;


    /**
     * 更新时间
     */
    private LocalDateTime modifyTime;


    /**
     * 删除标识
     */
    private Integer deleteMark;
}
