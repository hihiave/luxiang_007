package com.mp.controller;

import com.mp.common.PaginationUtil;
import com.mp.common.StringUtil;
import com.mp.common.Utility;
import com.mp.model.ImageInfo;
import com.mp.service.IImageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 95 on 2016/12/29.
 */
@Controller
public class ImageInfoController {
    private static Logger logger = Logger.getLogger(ImageInfoController.class);
    @Autowired
    private IImageInfoService iImageInfoService;

    @RequestMapping(value = "/zgmp/getAllImageInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getAllImageInfo(HttpServletRequest request){
        List<Integer> nums = getPageNowAndShowNum(request);
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int int_page_now = nums.get(0);
            int int_row_num = nums.get(1);
            List<ImageInfo> imageInfos;
            int total = 0;
            int begin = PaginationUtil.getRowBegin(int_row_num,int_page_now);
            imageInfos = iImageInfoService.findAllImageInfoPerPage(begin,int_row_num);
            List<String> imageStatus = new ArrayList<String>();
            for(int i = 0;i<imageInfos.size();i++){
                if (imageInfos.get(i).getImageStatus() == 1){
                    imageStatus.add("启用");
                }
                if (imageInfos.get(i).getImageStatus() == 2){
                    imageStatus.add("禁用");
                }
            }
            total = iImageInfoService.findAllImageInfoNum();
            setImageInfoValues(map,imageInfos,total,int_page_now,int_row_num);
            map.put("image_status",imageStatus);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/zgmp/getAvailableImageInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getAvailableImageInfo(HttpServletRequest request){
        List<Integer> nums = getPageNowAndShowNum(request);
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int int_page_now = nums.get(0);
            int int_row_num = nums.get(1);
            List<ImageInfo> imageInfos;
            int total = 0;
            int begin = PaginationUtil.getRowBegin(int_row_num,int_page_now);
            total = iImageInfoService.findAvailableRecordNum();
            imageInfos = iImageInfoService.findAvailableRecord(begin, int_row_num);

            System.out.println("可用的文件夹个数" + total);
            setImageInfoValues(map,imageInfos,total,int_page_now,int_row_num);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping(value = "/zgmp/getImageFileDir",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getImageFileInfo(HttpServletRequest request){
        Map<String,Object> map = new HashMap<String, Object>();
        String dir_str = StringUtil.getDefaultImageFilePath();
        //道路参数
        String filePathLevel1 = request.getParameter("level_1");
        //卡口参数
        String filePathLevel2 = request.getParameter("level_2");
        //日期参数
        String filePathLevel3 = request.getParameter("level_3");
        System.out.println(filePathLevel1);
        System.out.println(filePathLevel2);
        System.out.println(filePathLevel3);
        File file_dir_lvl_one = new File(dir_str);
        File[] file_dir_lvl_one_list = file_dir_lvl_one.listFiles();
        if (filePathLevel1 == null){
            //返回道路列表
            System.out.println("返回道路列表");
            List<String> lvl_1 = new ArrayList<String>();
            for(int i = 0;i < file_dir_lvl_one_list.length;i++){
                lvl_1.add(file_dir_lvl_one_list[i].getName());
            }
            map.put("level_1",lvl_1);
        }else if (filePathLevel1 != null && filePathLevel2 == null){
            //返回指定道路卡口列表
            System.out.println("返回指定道路卡口列表");
            List<String> lvl_2 = new ArrayList<String>();
            File file_dir_lvl_two = new File(dir_str+filePathLevel1);
            File[] file_dir_lvl_two_list = file_dir_lvl_two.listFiles();
            for(int i = 0;i < file_dir_lvl_two_list.length;i++){
                lvl_2.add(file_dir_lvl_two_list[i].getName());
            }
            map.put("level_2",lvl_2);
        }else if (filePathLevel1 !=null && filePathLevel2 !=null && filePathLevel3 == null){
            //返回指定卡口日期列表
            System.out.println("返回指定卡口日期列表");
            List<String> lvl_3 = new ArrayList<String>();
            File file_dir_lvl_three = new File(dir_str+filePathLevel1+"/"+filePathLevel2);
            System.out.println(dir_str+filePathLevel1+"/"+filePathLevel2);
            File[] file_dir_lvl_three_list = file_dir_lvl_three.listFiles();
            System.out.println(file_dir_lvl_three_list.length);
            for(int i = 0;i < file_dir_lvl_three_list.length;i++){
                lvl_3.add(file_dir_lvl_three_list[i].getName());
            }
            map.put("level_3",lvl_3);
        }else if (filePathLevel1 !=null && filePathLevel2 !=null && filePathLevel3 != null){
            //返回指定日期图片文件列表
            System.out.println("返回指定日期图片文件列表");
            List<String> lvl_image = new ArrayList<String>();
            File file_dir_lvl_image = new File(dir_str+filePathLevel1+"/"+filePathLevel2+"/"+filePathLevel3);
            System.out.println(dir_str+filePathLevel1+"/"+filePathLevel2+"/"+filePathLevel3);
            File[] file_dir_lvl_image_list = file_dir_lvl_image.listFiles();
            System.out.println(file_dir_lvl_image_list.length);
            for(int i = 0;i < file_dir_lvl_image_list.length;i++){
                lvl_image.add(file_dir_lvl_image_list[i].getName());
            }
            map.put("level_image",lvl_image);
        }
        return map;
    }

    @RequestMapping(value = "/zgmp/get_images",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getImages(HttpServletRequest request){
        System.out.println("获取图片++++++++++++++++++++++++++++");
        int imageid = Integer.parseInt(request.getParameter("imageid"));
        ImageInfo imageInfo = iImageInfoService.selectByPrimaryKey(imageid);
        String image_Dir = imageInfo.getImageKakou() + "/" + imageInfo.getImageDate();
        Map<String,Object> map = new HashMap<String, Object>();
        List<String> image_file_name_list = new ArrayList<String>();
        String file_dir = StringUtil.getDefaultImageFilePath();
        String image_dir = file_dir + image_Dir;
        System.out.println("图片地址"+image_dir);
        File image_file = new File(image_dir);
        File[] image_list = image_file.listFiles();
        for (int i = 0; i < image_list.length; i++) {
            if (image_list[i].isFile()) {
                System.out.println("图片文件："+image_list[i].getName());
                image_file_name_list.add(image_Dir+"/"+image_list[i].getName());
            }

        }
        map.put("image_file",image_file_name_list);



        return map;
    }


    /**
     * 计算和显示页数
     * @param request
     * @return
     */
    private List<Integer> getPageNowAndShowNum(HttpServletRequest request) {
        String page_now = request.getParameter("page_now");
        int int_page_now = StringUtil.convertStringToInt(page_now);

        String row_num = StringUtil.getDefaultShowRowNum();
        int int_row_num = StringUtil.convertStringToInt(row_num);

        List<Integer> nums = new ArrayList<Integer>();
        nums.add(int_page_now);
        nums.add(int_row_num);
        return  nums;
    }

    /**
     * 分页的数据
     * @param datas
     * @param total
     * @param page_now
     * @param row_num
     */
    private void setImageInfoValues(Map<String, Object> datas, List<ImageInfo> page_images, int total, int page_now, int row_num) {
        int total_num = total;
        int page_num = Utility.getPageCount(total_num, row_num);
        int  from =  PaginationUtil.getFrom(row_num, page_now);
        // 解决没有数据的时候，从0开始
        if(total_num == 0 ) {
            from = 0;
        }
        int  end =   PaginationUtil.getTo(from, page_images.size());
        datas.put("page_now", page_now);
        datas.put("image_info", page_images);
        datas.put("from",  from);
        datas.put("end",  end);
        datas.put("total", total_num);
        datas.put("page_num", page_num);

    }

}
