package com.ppw.graduation.project.server.service;

import com.ppw.graduation.project.api.response.StatusCode;
import com.ppw.graduation.project.server.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/3/4 16:54
 */

//文件上传工具
@Service
public class FileService {

    //TODO: 上传照片
    public void photoUpload(MultipartFile file, String uname){
        //String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());   //需要覆盖~
        String name = uname;

        //获取文件后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        if (!suffix.equals(".jpg") && !suffix.equals(".png")){
            throw new RuntimeException("无效的图片格式！");
        }
        String filename = name+suffix;
        File fileDir = FileUtil.getImgDirFile();
        // 构建真实的文件路径
        File newFile = new File(fileDir.getAbsolutePath() + File.separator + filename);

        try {
            // 上传图片到 -》 “绝对路径”
            int res = FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
            //System.out.println("resresresres:"+res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
