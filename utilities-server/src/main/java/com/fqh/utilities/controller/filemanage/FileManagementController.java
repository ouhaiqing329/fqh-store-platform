package com.fqh.utilities.controller.filemanage;

import com.fqh.utilities.enums.FileFolderEnum;
import com.fqh.utilities.service.system.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/file/admin/management")
public class FileManagementController {

    @Autowired
    private FileInfoService fileInfoService;

    @PostMapping("/add")
    public void add(@RequestParam MultipartFile file) {
        fileInfoService.save(file, FileFolderEnum.COMMON);
    }

}
