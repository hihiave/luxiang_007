package com.qmd.dao;

import com.qmd.model.FileInfo;

public interface FileInfoMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(Integer fileId);
    
    FileInfo selectByFileName(String fileName);
    
    int selectCountByFileCategory(String fileCategory);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);
    
    int updateFileStatusByCategory(String fileCategory);
    
    
}