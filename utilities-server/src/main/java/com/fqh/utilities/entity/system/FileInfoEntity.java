package com.fqh.utilities.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "file_info")
public class FileInfoEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 文件路径，相对路径
     */
    private String filePath;


    /**
     * 文件名称
     */
    private String fileName;


    /**
     * 文件类型
     */
    private String fileType;


    /**
     * 文件内容，快捷检索
     */
    private String content;


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
