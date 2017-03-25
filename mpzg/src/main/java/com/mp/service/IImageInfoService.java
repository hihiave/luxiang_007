package com.mp.service;

import com.mp.model.ImageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 95 on 2016/12/29.
 */
@Service
public interface IImageInfoService {
    /**
     * 通过图片ID找到对应文件夹
     * @param image_id
     * @return
     */
    ImageInfo selectByPrimaryKey(Integer image_id);
    /**
     * 查询图片文件夹信息
     * @param begin
     * @param int_row_num
     * @return
     */
    List<ImageInfo> findAllImageInfoPerPage(int begin,int int_row_num);
    /**
     * 查询文件夹个数
     * @param
     */
    int findAllImageInfoNum();
    /**
     * 查询状态为可用的文件夹
     */
    List<ImageInfo> findAvailableRecord(int begin,int int_row_num);
    int findAvailableRecordNum();
}
