package com.bill.system.controller;

import com.bill.system.entity.UploadUtils;
import com.bill.system.entity.Videos;
import com.bill.system.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VideoController {

    @Resource
    private VideoService videoService;

    @ResponseBody
    @PostMapping(value = "/uploadVideos")
    public Map<String,Object> uploadFiles(@RequestParam(value = "file") MultipartFile file){

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

            // 上传图片到 -》 “绝对路径”
            file.transferTo(newFile);
            Videos videos = new Videos();
            int upLoadFile = videoService.uploadVideo(videos,newFile.getAbsolutePath().substring(40,newFile.getAbsolutePath().length()));
            if(upLoadFile>0){
                map.put("resultVideo",true);
                map.put("msg","上传成功");
            }else{
                map.put("resultVideo",false);
                map.put("msg","上传失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    @GetMapping(value = "/toVideoList")
    public String getAllPhotos(Map<String,List> map){
        System.out.println(videoService.getListVideos());
        map.put("videoList",videoService.getListVideos());
        return "video/video";
    }
}
