package com.mp.serviceimpl;

import com.mp.dao.ImageInfoMapper;
import com.mp.model.ImageInfo;
import com.mp.service.IImageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 95 on 2016/12/29.
 */
@Service
public class ImageInfoServiceImpl implements IImageInfoService {
    @Autowired
    private ImageInfoMapper imageInfoMapper;
    /**
     * 查询图片文件夹信息
     */
    public ImageInfo selectByPrimaryKey(Integer image_id){
        if (image_id == null){
            return null;
        }else {
            return imageInfoMapper.selectByPrimaryKey(image_id);
        }
    }
    @Override
    public List<ImageInfo> findAllImageInfoPerPage(int begin,int size){
        Map<String,Object> data = new HashMap<String, Object>();
        data.put("begin",begin);
        data.put("size",size);
        return imageInfoMapper.findAllImageInfoPerPage(data);
    }
    @Override
    public int findAllImageInfoNum(){
        return imageInfoMapper.findAllImageInfoNum();
    }

    @Override
    public List<ImageInfo> findAvailableRecord(int begin,int size){
        Map<String,Object> data = new HashMap<String, Object>();
        data.put("begin",begin);
        data.put("size",size);
        return imageInfoMapper.findAvailableRecord(data);
    }
    @Override
    public int findAvailableRecordNum(){
        return imageInfoMapper.findAvailableRecordNum();
    }
}
