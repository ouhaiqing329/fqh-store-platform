package com.fqh.utilities.controller;

import com.fqh.utilities.entity.ImageEntity;
import com.fqh.utilities.handle.BaseResponseResult;
import com.fqh.utilities.handle.ServiceException;
import com.fqh.utilities.service.ImageService;
import com.fqh.utilities.utils.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;


/**
 * 图像上传控制器
 *
 * @author fqh
 * @date 2023/01/06
 */
@RestController
@RequestMapping("/utilities/img")
@Slf4j
public class ImageUploadController {

    @Autowired
    private ImageService imageService;

    @Value("${upload.path}")
    private String realPath;

    /**
     * 单个图片压缩
     */
    @GetMapping("/compress/{id}")
    public void compress(@PathVariable("id") Long id,HttpServletResponse response) throws UnsupportedEncodingException {
        OutputStream out = null;
        response.setContentType("application/x-download");
        ImageEntity imageEntity = imageService.selectOne(id);
        response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode
                (imageEntity.getImgName() + "." + imageEntity.getType(), "UTF-8") + "\"");
        try {
            FileInputStream is = new FileInputStream(realPath + imageEntity.getStorePath());
            out = response.getOutputStream();
            Thumbnails.of(is).scale(1D).outputQuality(0.5).toOutputStream(out);
        } catch (IOException e) {
            log.error("图片压缩失败！Exception：",e);
        }

    }

    /**
     * 单个图片上传
     */
    @PostMapping("/upload")
    public BaseResponseResult<Void> upload(MultipartFile img){
        String contentType = img.getContentType();
        ImageEntity imageEntity = new ImageEntity();
        if (contentType == null){
            throw new ServiceException("图片类型无法解析！");
        }
        String type = contentType.split("/")[1];
        File rootFile = new File(realPath + "compressed/");
        if (!rootFile.exists()){
            boolean flag = rootFile.mkdir();
            log.error("图片缓存文目录创建{}", flag ? "成功！" : "失败！");

        }
        String originalFilename = StringUtils.hasText(img.getOriginalFilename()) ? img.getOriginalFilename().split("\\.")[0] : UUID.randomUUID().toString().replaceAll("-", "");
        String filePath = "compressed/" + originalFilename + "." + type;
        File file = new File(realPath + filePath);
        //创建字节输出流
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            InputStream is = img.getInputStream();
            byte[] bytes = new byte[1024];
            while (is.read(bytes) != -1){
                out.write(bytes);
            }
            imageEntity.setStorePath(filePath);
        } catch (IOException e) {
            log.error("exception:",e);
            throw new ServiceException("图片上传失败");
        }finally {
            if (out != null){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //存储
        imageEntity.setImgName(originalFilename);
        imageEntity.setType(type);
        imageEntity.setSize(file.length() / 1024000D);
        return imageService.save(imageEntity) ? BaseResponseResult.success(ImageUtil.getServicePath() + imageEntity.getStorePath()) : BaseResponseResult.error();

    }
}
