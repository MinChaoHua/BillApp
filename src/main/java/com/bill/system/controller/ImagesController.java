package com.bill.system.controller;

import com.bill.system.entity.Photos;
import com.bill.system.entity.UploadUtils;
import com.bill.system.service.ImagesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class ImagesController {

    @Resource
    ImagesService imagesService;


    @GetMapping(value = "/toPhotoWall")
    public String getAllPhotos(Map<String,List> map){
        System.out.println(imagesService.getPhotoList());
        map.put("photoList",imagesService.getPhotoList());
        return "personImages/photoWall";
    }


    @ResponseBody
    @PostMapping(value = "/uploadImages")
    public Map<String,Object> uploadFiles(@RequestParam(value = "file") MultipartFile file){
        System.out.println(file+"file");
        Map<String,Object> map = new HashMap<>();
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        // 拿到文件名
        String filename = file.getOriginalFilename();

        // 存放上传图片的文件夹
        File fileDir = UploadUtils.getImgDirFile();
        // 输出文件夹绝对路径  -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
        System.out.println(fileDir.getAbsolutePath());

        try {
            // 构建真实的文件路径
            File newFile = new File(fileDir.getAbsolutePath() + File.separator + filename);
            System.out.println(newFile.getAbsolutePath());

            // 上传图片到 -》 “绝对路径”
            file.transferTo(newFile);
            Photos photos = new Photos();
            int upLoadFile = imagesService.upLoadFile(photos, newFile.getAbsolutePath());
            if(upLoadFile>0){
                map.put("result",true);
                map.put("msg","上传成功");
            }else{
                map.put("result",false);
                map.put("msg","上传失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

}
