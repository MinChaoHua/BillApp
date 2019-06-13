package com.bill.system.service;

import com.bill.system.entity.Photos;

import java.util.List;

public interface ImagesService {
    int upLoadFile(Photos photos,String path);
    List<Photos> getPhotoList();
}
