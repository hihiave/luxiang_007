package com.mp.service;

import com.mp.model.FileInfo;
import org.springframework.stereotype.Service;

/**
 * Created by 吴松泽 on 2016/12/19.
 */
@Service
public interface IFileInfoService {
    /**
     * 通过文件ID查找文件
     * @param fileid
     * @return
     */
    FileInfo selectByPrimaryKey(Integer fileid);
    /**
     *
     */
}
