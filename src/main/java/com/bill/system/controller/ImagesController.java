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
        System.out.println(fileDir+"90");

        try {
            // 构建真实的文件路径
            File newFile = new File(fileDir.getAbsolutePath() + File.separator + filename);
            System.out.println(newFile.getPath());
            System.out.println(newFile.getAbsolutePath());

            // 上传图片到 -》 “绝对路径”
            file.transferTo(newFile);
            Photos photos = new Photos();
            int upLoadFile = imagesService.upLoadFile(photos, newFile.getAbsolutePath().substring(40,newFile.getAbsolutePath().length()));
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

    /**
     * 去图片删除
     * */
    @GetMapping("/toDeletePhoto")
    public String toDeletePhoto(Map<String,List> map){
        map.put("photoList",imagesService.getPhotoList());
        return "personImages/photoUpdate";
    }

    /**
     * 图片删除
     * */
    @ResponseBody
    @DeleteMapping(value = "/deletePhoto/{id}",produces = "application/json;charset-utf-8")
    public Map<String,Object>  deletePhoto(@PathVariable("id")int id){

        Map<String,Object> map = new HashMap<>();
        int deletePhoto = imagesService.deletePhoto(id);
        if (deletePhoto > 0) {
            map.put("deletePhoto", true);
            map.put("msg", "删除成功");
        } else {
            map.put("deletePhoto", false);
            map.put("msg", "删除失败");
        }
        return map;
    }

    /**
     *
     * 去删除恢复页面
     * */
    @GetMapping("/toRestorePhoto")
    public String toRestorePhoto(Map<String,List> map){
        map.put("photoList",imagesService.selectByAccountnumberDelete());
        return "personImages/TrashPhotos";
    }

    /**
     * 删除恢复
     * */
    @ResponseBody
    @PostMapping(value = "/restorePhoto/{id}",produces = "application/json;charset-utf-8")
    public Map<String,Object>  restorePhoto(@PathVariable("id")int id){

        Map<String,Object> map = new HashMap<>();
        int restorePhoto = imagesService.restoreByPrimaryKey(id);
        if (restorePhoto > 0) {
            map.put("restorePhoto", true);
            map.put("msg", "恢复成功");
        } else {
            map.put("restorePhoto", false);
            map.put("msg", "恢复失败");
        }
        return map;
    }
}
