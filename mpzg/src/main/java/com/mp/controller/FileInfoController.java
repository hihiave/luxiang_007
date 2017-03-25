package com.mp.controller;

import com.mp.common.StringUtil;
import com.mp.model.FileInfo;
import com.mp.service.IFileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 95 on 2016/12/19.
 */
@Controller
public class FileInfoController {
    private static Logger logger = Logger.getLogger(FileInfoController.class);

    @Autowired
    private IFileInfoService fileInfoService;


    @RequestMapping(value = "/zgmp/get_fileinfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String , Object> getFileInfo(HttpServletRequest request){
        Integer fileid = Integer.parseInt(request.getParameter("fileid"));
        System.out.println(fileid+"+++++++++++++++++++++++");
        String fileurl = fileInfoService.selectByPrimaryKey(fileid).getFileUrl();
        System.out.println(fileurl+"+++++++++++++++++++++++");
        fileurl = "http://localhost:8080/zg/video"+fileurl;
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("file_url",fileurl);
        return map;
    }

    @RequestMapping(value = "/zgmp/getVideoFileDir",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getVideoFileInfo(HttpServletRequest request){
        Map<String,Object> map = new HashMap<String, Object>();
        String dir_str = StringUtil.getDefaultVideoFilePath();
        //道路参数
        String video_filePathLevel1 = request.getParameter("level_1");
        //摄像头参数
        String video_filePathLevel2 = request.getParameter("level_2");
        //日期参数
        String video_filePathLevel3 = request.getParameter("level_3");
        System.out.println(video_filePathLevel1);
        System.out.println(video_filePathLevel2);
        System.out.println(video_filePathLevel3);
        File video_file_dir_lvl_one = new File(dir_str);
        File[] video_file_dir_lvl_one_list = video_file_dir_lvl_one.listFiles();
        if (video_filePathLevel1 == null){
            //返回道路列表
            System.out.println("返回道路列表");
            List<String> lvl_1 = new ArrayList<String>();
            for(int i = 0;i < video_file_dir_lvl_one_list.length;i++){
                lvl_1.add(video_file_dir_lvl_one_list[i].getName());
            }
            map.put("level_1",lvl_1);
        }else if (video_filePathLevel1 != null && video_filePathLevel2 == null){
            //返回指定道路摄像头列表
            System.out.println("返回指定道路摄像头列表");
            List<String> lvl_2 = new ArrayList<String>();
            File video_file_dir_lvl_two = new File(dir_str+video_filePathLevel1);
            File[] video_file_dir_lvl_two_list = video_file_dir_lvl_two.listFiles();
            for(int i = 0;i < video_file_dir_lvl_two_list.length;i++){
                lvl_2.add(video_file_dir_lvl_two_list[i].getName());
            }
            map.put("level_2",lvl_2);
        }else if (video_filePathLevel1 !=null && video_filePathLevel2 !=null && video_filePathLevel3 == null){
            //返回指定摄像头日期列表
            System.out.println("返回指定摄像头日期列表");
            List<String> lvl_3 = new ArrayList<String>();
            File video_file_dir_lvl_three = new File(dir_str+video_filePathLevel1+"/"+video_filePathLevel2);
            System.out.println(dir_str+video_filePathLevel1+"/"+video_filePathLevel2);
            File[] video_file_dir_lvl_three_list = video_file_dir_lvl_three.listFiles();
            System.out.println(video_file_dir_lvl_three_list.length);
            for(int i = 0;i < video_file_dir_lvl_three_list.length;i++){
                lvl_3.add(video_file_dir_lvl_three_list[i].getName());
            }
            map.put("level_3",lvl_3);
        }else if (video_filePathLevel1 !=null && video_filePathLevel2 !=null && video_filePathLevel3 != null){
            //返回指定日期视频文件列表
            System.out.println("返回指定日期视频文件列表");
            List<String> lvl_video = new ArrayList<String>();
            File video_file_dir_lvl_image = new File(dir_str+video_filePathLevel1+"/"+video_filePathLevel2+"/"+video_filePathLevel3);
            System.out.println(dir_str+video_filePathLevel1+"/"+video_filePathLevel2+"/"+video_filePathLevel3);
            File[] video_file_dir_lvl_image_list = video_file_dir_lvl_image.listFiles();
            System.out.println(video_file_dir_lvl_image_list.length);
            for(int i = 0;i < video_file_dir_lvl_image_list.length;i++){
                lvl_video.add(video_file_dir_lvl_image_list[i].getName());
            }
            map.put("level_video",lvl_video);
        }
        return map;
    }


}

