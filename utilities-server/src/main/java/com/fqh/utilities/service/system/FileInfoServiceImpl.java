package com.fqh.utilities.service.system;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fqh.utilities.entity.system.FileInfoEntity;
import com.fqh.utilities.enums.FileFolderEnum;
import com.fqh.utilities.handle.ServiceException;
import com.fqh.utilities.mapper.system.FileInfoMapper;
import com.fqh.utilities.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.file.PathUtils;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
public class FileInfoServiceImpl implements FileInfoService {

    @Value("${upload.path}")
    private String basePath;

    @Autowired
    private FileInfoMapper fileInfoMapper;

    private String getBasePath() {
        return basePath;
    }

    @Override
    public FileInfoEntity save(MultipartFile file, FileFolderEnum fileFolderEnum) {

        Path targetPath = Paths.get(getBasePath(), fileFolderEnum.getCode() + "/" + LocalDate.now().toString().replaceAll("-", ""), UUID.randomUUID().toString());
        Path filePath = Paths.get(targetPath.toString(), file.getOriginalFilename());
        //创建多级目录
        try {
            PathUtils.createParentDirectories(filePath);
        } catch (IOException e) {
            log.error("创建多级目录失败，exception：", e);
            throw new ServiceException("创建目录失败");
        }
        //写入文件
        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            log.error("写入文件失败，exception：", e);
            throw new ServiceException("写入文件失败");
        }
        FileInfoEntity fileInfoEntity = new FileInfoEntity();
        fileInfoEntity.setFileName(file.getOriginalFilename());
        fileInfoEntity.setFilePath(filePath.toString());
        fileInfoEntity.setFileType(file.getContentType());
        fileInfoEntity.setContent(FileUtil.getContent(filePath.toFile(), file.getContentType()));

        int row = fileInfoMapper.insert(fileInfoEntity);
        if (row < 1) {
            throw new ServiceException("保存文件失败");
        }
        return fileInfoEntity;
    }

    @Override
    public File getFile(String fileId, FileFolderEnum fileFolderEnum) {

        //获取文件信息
        FileInfoEntity fileInfoEntity = fileInfoMapper.selectOne(Wrappers.<FileInfoEntity>lambdaQuery().eq(FileInfoEntity::getId, fileId));

        if (fileInfoEntity == null) {
            throw new ServiceException("文件不存在！");
        }
        //读取文件
        return Paths.get(getBasePath(), fileInfoEntity.getFilePath()).toFile();
    }

    @Override
    public void removeFile(String fileId, FileFolderEnum fileFolderEnum, boolean isDeleteOriginFile) {
        //获取文件信息
        FileInfoEntity fileInfoEntity = fileInfoMapper.selectOne(Wrappers.<FileInfoEntity>lambdaQuery().eq(FileInfoEntity::getId, fileId));

        if (fileInfoEntity == null) {
            throw new ServiceException("文件不存在！");
        }
        //删除磁盘文件
        if (isDeleteOriginFile) {
            File file = getFile(fileId, fileFolderEnum);
            //判断文件是否存在
            if (file.exists()) {
                File parentFile = file.getParentFile();
                boolean delete = file.delete();
                //删除父文件夹
                boolean deleteParent = parentFile.delete();
                if (!delete || !deleteParent) {
                    throw new ServiceException("文件删除失败");
                }
            }
        }
        int row = fileInfoMapper.deleteById(fileInfoEntity);
        if (row < 1) {
            throw new ServiceException("文件删除失败");
        }
    }
}
