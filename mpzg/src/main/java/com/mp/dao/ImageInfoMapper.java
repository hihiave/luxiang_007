package com.mp.dao;

import com.mp.model.ImageInfo;

import java.util.List;
import java.util.Map;

public interface ImageInfoMapper {
    int deleteByPrimaryKey(Integer imageId);

    int insert(ImageInfo record);

    int insertSelective(ImageInfo record);

    ImageInfo selectByPrimaryKey(Integer imageId);

    int updateByPrimaryKeySelective(ImageInfo record);

    int updateByPrimaryKey(ImageInfo record);

    List<ImageInfo> findAllImageInfoPerPage(Map<String,Object> data);

    int findAllImageInfoNum();

    List<ImageInfo> findAvailableRecord(Map<String,Object> data);
    int findAvailableRecordNum();
}