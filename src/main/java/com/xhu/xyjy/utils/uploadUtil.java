package com.xhu.xyjy.utils;

import com.xhu.xyjy.dto.ResultData;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;

public class uploadUtil {

    public static ResultData upload(MultipartFile[] files, String fileExt,String path,int type) {


        for (int i=0;i<files.length;i++)
        {
            System.out.println(files[i].getOriginalFilename());
        }
        ResultData result = new ResultData();
        if (files == null) {
            result.setCode(9004);
            result.setMsg("文件数据异常");
        }
        //最后的图片保存路径
        String endpath = "";
        //文件保存的位置
        String basePath = path;
        String upLoadPath = StringUtil.GetNowData();
        for (int i = 0; i < files.length; i++) {
            // 1. 得到文件的名字
            String filename = files[i].getOriginalFilename();
            //得到后缀
            String ext = StringUtil.getExt(filename).toLowerCase();
            //验证后缀
            if (fileExt.indexOf(ext) == -1) {
                result.setCode(9004);
                result.setMsg("上传的文件后缀不满足规则");
                break;
            }
            if (filename != null && filename != "") {

                File uploadDir = new File(basePath, upLoadPath);
                //手动创建以时间区分的文件夹
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                //创建一个随机不同名文件
                String UniqeName = StringUtil.GetUniueFileName();
                File uploadFile = new File(uploadDir, UniqeName + ext);

                System.out.println("wenjianming" + uploadFile.getName());
                System.out.println("mulumings" + upLoadPath);
                //将图片存入文件夹

                try {
                    //将上传的文件写到服务器上指定的文件。
                    files[i].transferTo(uploadFile);
                    result.setCode(200);
                    result.setMsg("文件上传成功");
                    if(type==1){
                        endpath += "image\\moment" + "\\" + upLoadPath + "\\" + uploadFile.getName() + ",";
                    }
                    else {
                        endpath += "video\\moment" + "\\" + upLoadPath + "\\" + uploadFile.getName() + ",";
                    }

                    System.out.println("文件路径" + "image\\moment" + "\\" + upLoadPath + "\\" + uploadFile.getName());
                    System.out.println("执行try语句");
                } catch (Exception e) {
                    if (e.getMessage().indexOf("The field file exceeds its maximum permitted size") != -1) {
                        System.out.println("文件大小超出限制");
                        result.setCode(9004);
                        break;
                    }
//                            result.setMsg("系统异常，图片上传失败");
                }
            }
        }
        System.out.println(endpath+"图片路径");
        result.setData(endpath);
        return result;
    }
}

//    public  static  ResultData uploadOne(MultipartFile[] files, String fileExt){
//
//        ResultData result = new ResultData();
//        if (files==null) {
//            result.setCode(9004);
//            result.setMsg("文件数据异常");
//        }
//
//        for (MultipartFile file:files) {
//
//        }
//        // 1. 得到文件的名字
//        String filename = file.getOriginalFilename();
//        //得到后缀
//        String ext = StringUtil.getExt(filename);
//        //验证后缀
//        if(fileExt.indexOf(ext) == -1){
//            result.setCode(9004);
//            result.setMsg("上传的文件后缀不满足规则");
//        }
//        if(filename!=null&&filename!=""){
//            //文件保存的位置
//            String basePath = "F:\\xyjy\\src\\main\\resources\\static\\image\\moment";
//            String upLoadPath = StringUtil.GetNowData();
//            File uploadDir = new File(basePath, upLoadPath);
//            //手动创建以时间区分的文件夹
//            if (!uploadDir.exists()) {
//                uploadDir.mkdirs();
//            }
//            //创建一个随机不同名文件
//            String UniqeName = StringUtil.GetUniueFileName();
//            File uploadFile = new File(uploadDir, UniqeName + ext);
//
//            System.out.println("wenjianming"+uploadFile.getName());
//            System.out.println("mulumings"+upLoadPath);
//            //将图片存入文件夹
//            try {
//                //将上传的文件写到服务器上指定的文件。
//                file.transferTo(uploadFile);
//                result.setCode(200);
//                result.setMsg("文件上传成功");
//                result.setData("image\\moment" + "\\" +upLoadPath+"\\"+ uploadFile.getName());
//                System.out.println("文件路径" + "image\\moment" + "\\" +upLoadPath+"\\"+ uploadFile.getName());
//                System.out.println("执行try语句");
//            } catch (Exception e) {
//                if (e.getMessage().indexOf("The field file exceeds its maximum permitted size") != -1) {
//                    System.out.println("执行catch语句");
//                    result.setCode(9004);
//                    result.setMsg("系统异常，图片上传失败");
//                }
//            }
//        }
//        return result;
//    }
//    }


