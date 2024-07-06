package com.fqh.utilities.service.system;

import com.fqh.utilities.entity.system.FileInfoEntity;
import com.fqh.utilities.enums.FileFolderEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileInfoService {

    /**
     * 保存文件
     *
     * @param file           输入流
     * @param fileFolderEnum 文件类型
     * @return {@link FileInfoEntity}
     */
    FileInfoEntity save(MultipartFile file, FileFolderEnum fileFolderEnum);

    /**
     * 获取文件
     *
     * @param fileId         文件Id
     * @param fileFolderEnum 文件类型
     * @return {@link FileInfoEntity}
     */
    File getFile(String fileId, FileFolderEnum fileFolderEnum);


    /**
     * 删除文件
     *
     * @param fileId             文件Id
     * @param fileFolderEnum     上级文件夹
     * @param isDeleteOriginFile 是否删除源文件
     */
    void removeFile(String fileId, FileFolderEnum fileFolderEnum, boolean isDeleteOriginFile);
}
