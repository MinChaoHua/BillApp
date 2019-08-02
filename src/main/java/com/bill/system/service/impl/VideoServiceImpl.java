package com.bill.system.service.impl;

import com.bill.system.dao.VideosMapper;
import com.bill.system.entity.Videos;
import com.bill.system.service.VideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Resource
    private HttpServletRequest request;

    @Resource
    private VideosMapper videosMapper;

    @Override
    public int uploadVideo(Videos videos, String path) {
        videos.setAccoutnumber(request.getSession().getAttribute("userinfo").toString());
        videos.setDate(new Date());
        videos.setPath(path);
        return  videosMapper.insert(videos);
    }

    @Override
    public List<Videos> getListVideos() {
        return videosMapper.selectAll();
    }
}
