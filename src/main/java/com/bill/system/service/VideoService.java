package com.bill.system.service;

import com.bill.system.entity.Videos;

import java.util.List;

public interface VideoService {
    int uploadVideo(Videos videos, String path);

    List<Videos> getListVideos();
}
