package com.mp.controller;

import com.mp.common.StringUtil;
import com.mp.model.UserInfo;
import com.mp.service.IUserInfoService;
import com.mp.serviceimpl.UserInfoServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by 95 on 2017/1/9.
 */
@Controller
@RequestMapping("/zg")
public class WebSocketController {
    private static final Logger logger = Logger.getLogger(WebSocketController.class);

    private SimpMessagingTemplate template;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    public WebSocketController(SimpMessagingTemplate simpMessagingTemplate){this.template = simpMessagingTemplate;}


    @MessageMapping("/web-socket-test")
    @ResponseBody
    public void pushMessageTest(Map<String,String> str){
        System.out.println("++++++++");
        String user_id = str.get("userId");
        Integer int_user_id = Integer.parseInt(user_id);
        System.out.println("userId" + int_user_id + "!!!!!!!!!!!!!!!");
        UserInfo userInfo = userInfoService.selectPswById(int_user_id);
        template.convertAndSendToUser(""+1,"/get_info",userInfo);
        System.out.println("连接成功!!!!!!!!!!!!!!!!");
    }

    @MessageMapping("/image-handle-test")
    @ResponseBody
    public void imageHandleTest(Map<String,String> str,Map<String,ArrayList> arrayListMap){
        String user_id = str.get("userId");
        Integer int_user_id = Integer.parseInt(user_id);
        ArrayList<String> path = arrayListMap.get("imagePath");
        String dir_str = StringUtil.getDefaultImageFilePath();
        System.out.println(arrayListMap.get("imagePath")+"图片地址数组");
//        System.out.println(path.get(0)+"类型");
        for (int i = 0;i < path.size();i++){
            String temp = dir_str + path.get(i);
            path.set(i, temp);
        }
        for (int i = 0;i < path.size();i++){
            System.out.println("!!!!!!"+path.get(i));
        }
        template.convertAndSendToUser(""+user_id,"/get_result",path);

    }
}
