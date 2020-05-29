package com.biz.lesson.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * @program:
 * @description:
 * @author: lyy
 * @generate: 2020-04-21 14:58
 **/
public class Common {
    static String FOLDER = "1919/";
    static String BACKET_NAME = "icanlyy";
    static String ENDPOINT = "oss-cn-beijing.aliyuncs.com/";
    static String ACCESSKEYID= "LTAI7Ix89ALe4rhV";
    static String SECRETACCESSKEY= "1VIaYHuqL23wuDb2vh9MEh99N3fj3V";



//    public static Student toStudent(String json){
//        return StringUtils.isNotBlank(json) ?
//                JSONObject.parseObject(json,Student.class) :
//                new Student();
//    }


    public static OSSClient getOSSClient(){
        return new OSSClient(ENDPOINT, ACCESSKEYID, SECRETACCESSKEY);
    }
    public static void main(String[] args) {
        String f="F:\\360downloads\\316068.jpg";
        File file=new File(f);
        try{
            InputStream fileInputStream = new FileInputStream(file);
            String md5key = uploadObject2OSS(fileInputStream,file.getName());
            System.out.println("上传后的文件MD5数字唯一签名:" + md5key);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 上传图片至OSS
     * @param is 上传文件（文件全路径如：D:\\image\\cake.jpg）
     * @return String 返回访问路径
     * */
    public static  String uploadObject2OSS(InputStream is,String fileName) {
        OSSClient ossClient = getOSSClient();
        String resultStr = null;
        try {
            //以输入流的形式上传文件
//            InputStream is = new FileInputStream(file);
            //文件名  如果出现重复，则重新生成名字，再上传
            fileName = getfileName(fileName);
//            if(ossClient.doesObjectExist(BACKET_NAME, FOLDER + fileName)){
//                fileName = getfileName(file.getName());
//            }
            //文件大小
            Long fileSize = is.available()/1000L;
            System.out.println(fileSize);
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(is.available());
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
//            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            metadata.setContentDisposition("attachment;filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(BACKET_NAME, FOLDER + fileName, is, metadata);
            resultStr = putResult.getETag();
            System.out.println(resultStr);
            //解析结果
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "http://"+BACKET_NAME+"."+ENDPOINT+"/"+FOLDER + fileName;
    }
    public static  String getfileName(String fileName){

        String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String name = UUID.randomUUID().toString()+fileType;
        System.out.println(fileType+"----"+name);
        return name.replaceAll("-", "");
    }
    public static  String getContentType(String fileName){
        //文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if(".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if(".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if(".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension)  || ".png".equalsIgnoreCase(fileExtension) ) {
            return "image/jpeg";
        }
        if(".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if(".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if(".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if(".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if(".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if(".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        //默认返回类型
        return "image/jpeg";
    }
}
