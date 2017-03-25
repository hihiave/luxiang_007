package com.mp.serviceimpl;

import com.mp.dao.FileInfoMapper;
import com.mp.model.FileInfo;
import com.mp.service.IFileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wsz on 2016/12/19.
 */
@Service
public class FileInfoServiceImpl implements IFileInfoService {
    @Autowired
    private FileInfoMapper fileInfoMapper;
    /**
     * 查询文件信息
     */
    public  FileInfo selectByPrimaryKey(Integer fileid){
        if (fileid == null){
            return null;
        }else {
            return fileInfoMapper.selectByPrimaryKey(fileid);
        }
    }
}
