package com.ppw.graduation.project.server.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/3/4 15:02
 */

//文件上传工具类
public class FileUtil {

    public final static String IMG_PATH_PREFIX = "static/images";

    public static File getImgDirFile(){

        // 构建上传文件的存放 "文件夹" 路径
        String fileDirPath = new String("server/src/main/resources/" + IMG_PATH_PREFIX);

        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }

/*
    *//*绝对路径*//*
    private static String absolutePath="";

    //静态目录
    private static  String staticDir="static";

    //文件存放的目录
    private static String fileDir="/images";

    *//**
     * 上传单个文件
     * 最后文件存放路径为：static/img/11.jpg
     * 文件访问路径为：http://127.0.0.1:8080/img/11.jpg
     * 该方法返回值为：/img/11.jpg
     * @param inputStream 文件流
     * @param path 文件路径，如：image/
     * @param filename 文件名，如：test.jpg
     * @return 成功：上传后的文件访问路径，失败返回：null
     *//*
    public static String upload(InputStream inputStream, String path, String filename){
        //第一次会创建文件夹
        createDirIfNotExists();

        String resultPath = fileDir + path + filename;

        //存文件
        File uploadFile = new File(absolutePath, staticDir + resultPath);
        try {
            FileUtils.copyInputStreamToFile(inputStream, uploadFile);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return resultPath;
    }

    //创建文件夹路径
    private static void createDirIfNotExists() {
        if (!absolutePath.isEmpty())  return;

        //获取根目录
        File file = null;
        try {
            file = new File(ResourceUtils.getURL("classpath:").getPath());


        }catch (FileNotFoundException e){
            throw new RuntimeException("获取根目录失败，无法创建上传目录！");
        }
        if (!file.exists()){
            file = new File("");
        }
        absolutePath = file.getAbsolutePath();

        File upload = new File(absolutePath, staticDir+fileDir);
        if (!upload.exists()){
            upload.mkdirs();
        }
    }

    *//**
     * 删除文件
     * @param path 文件访问的路径upload开始 如：img/11.jpg
     * @return true 删除成功； false 删除失败
     *//*
    public static boolean delete(String path){
        File file = new File(absolutePath, staticDir + path);
        if (file.exists()){
            return file.delete();
        }
        return false;
    }*/
}
