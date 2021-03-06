package com.bill.system.service.impl;

import com.bill.system.dao.PhotosMapper;
import com.bill.system.entity.Photos;
import com.bill.system.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class ImagesServiceImpl implements ImagesService {

    @Resource
    private PhotosMapper photoMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public int upLoadFile(Photos photos,String path) {
        photos.setAccountnumber(request.getSession().getAttribute("userinfo").toString());
        photos.setPath(path);
        photos.setDate(new Date());
        return photoMapper.insert(photos);
    }

    @Override
    public List<Photos> getPhotoList() {
       List<Photos> list =  photoMapper.selectByAccountnumber(request.getSession().getAttribute("userinfo").toString());
        return list;
    }

    @Override
    public int deletePhoto(int id) {
        return  photoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int restoreByPrimaryKey(int id) {
        return  photoMapper.restoreByPrimaryKey(id);
    }

    @Override
    public List<Photos> selectByAccountnumberDelete() {
        List<Photos> photosList = photoMapper.selectByAccountnumberDelete(request.getSession().getAttribute("userinfo").toString());
        return photosList;
    }
}
