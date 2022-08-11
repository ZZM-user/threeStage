package com.example.controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.example.common.domain.R;
import com.example.common.enums.AckCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author： 17602
 * @Date： 2022/8/5 11:32
 * @Desc：
 **/
@Api(value = "文件上传")
@Slf4j
@CrossOrigin
@RestController
public class FileUploadController {
    // 雪花文件名
    private Snowflake snowflake = IdUtil.getSnowflake(1, 1);
    private final String img_path = "E:\\Workspace\\JAVA\\threeStage\\waimai\\img_server";
    private final String img_server = "img/";
    
    private final String[] allowed_types = {"image/png", "image/jpg", "image/jpeg", "img/gif"};
    
    @ApiOperation("文件上传")
    @PostMapping("img/upload")
    public R fileUpload(MultipartFile file) throws IOException {
        // 检查图片是否为空
        if (file.isEmpty()) {
            return R.build(AckCode.FILE_NOT_EMPTY);
        }
        // 检查文件类型
        String contentType = file.getContentType();
        if (notAllowedType(contentType)) {
            return R.build(AckCode.UPLOAD_TYPE_ERROR);
        }
        
        // 检查大小
        if (file.getSize() > 1024L * 1204L * 2) {
            return R.build(AckCode.FILE_NOT_SIZE);
        }
        
        // 获取后缀名
        String originalFilename = file.getOriginalFilename();
        
        // 取得临时的文件名
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 重命名 雪花算法 hutools工具类封装
        // 取名copy
        String fileName = snowflake.nextIdStr() + ext;
        File newFile = new File(img_path, fileName);
        file.transferTo(newFile);
        
        Map<String, Object> map = new HashMap<>(2);
        map.put("fileName", fileName);
        map.put("fileUrl", img_server + fileName);
        log.info("上传文件：" + map);
        return R.okHasData(map);
    }
    
    /**
     * 属于不能通过的类型？
     *
     * @param contentType
     *
     * @return
     */
    private boolean notAllowedType(String contentType) {
        for (String type : allowed_types) {
            if (type.equalsIgnoreCase(contentType)) {
                return false;
            }
        }
        return true;
    }
}
