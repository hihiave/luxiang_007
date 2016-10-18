package com.lx.controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lx.macrofiles.MacroEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.model.UserInfo;
import com.lx.service.UserInfoService;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/UserInfoController")
public class UserInfoController {

	@Autowired
	UserInfoService userInfoService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
	public Map<String ,Object> login(String username, String password, HttpSession httpSession) {
		System.out.println("======username=====" + username);
		System.out.println("======password=====" + password);
        MacroEnum.KMessageType result = userInfoService.checkLogin(username, password);
        UserInfo userInfo = userInfoService.selectUserByUserName(username);
        String str_result = "";
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+result);
        Map<String ,Object> map = new HashMap<String, Object>();
		if (result == MacroEnum.KMessageType.loginSuccess) {
            str_result = "LoginSuccess";
            httpSession.setAttribute("userinfo",userInfo);
            httpSession.setAttribute("username",username);
            httpSession.setAttribute("time",userInfo.getUserRegisterTime());
            httpSession.setAttribute("userrole",userInfo.getUserRole());
//            httpSession.setAttribute("password",password);
//			httpSession.setAttribute("flag", true);
            map.put("data",str_result);
			return map;
		}else if(result == MacroEnum.KMessageType.checkNotPass){
            str_result = "CheckNotPass";
            map.put("data",str_result);
            return map;
        }else {
            str_result = "LoginFail";
            map.put("data",str_result);
            return map;
        }


	}
    @RequestMapping(value = "/regist" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String ,Object> regist(String username1, String password1, HttpSession httpSession){
        System.out.println("======username=====" + username1);
        System.out.println("======password=====" + password1);
        Map<String ,Object> map = new HashMap<String, Object>();
        boolean check_result = userInfoService.checkUserIsExist(username1);
        map.put("check",check_result);
        System.out.println("检查结果"+check_result);
        if (!check_result){

            boolean regist_result = userInfoService.registerUserInfo(username1,password1);

            map.put("data",regist_result);
        }
        return map;
    }

    @RequestMapping(value = "/adm-adduser" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> admadduser(String username, HttpServletRequest request){
        System.out.println(username+"-------___________");
        boolean check_result = userInfoService.checkUserIsExist(username);
        Map<String, Object> map = new HashMap<String, Object>();
        if (!check_result){
            if (userInfoService.addUserInfo(username)){
                map.put("flag","chenggong");
                return map;
            }
            map.put("flag","shibai");
            return map;
        }
        map.put("flag","cunzai");
        return map;
    }

//    @RequestMapping(value="get_export_select_info", method= RequestMethod.POST)
	@RequestMapping(value = "/inquire", method = RequestMethod.POST)
    @ResponseBody
	public Map<String, Object> inquire(String username_search,HttpSession httpSession) {

        System.out.println(username_search+"!!!!!!!!!!!!!!!!!!___________");
		List<UserInfo> userInfos = userInfoService.selectAllUserInfoByLikeUserName(username_search);
        System.out.println(userInfos.size()+"个匹配选项");
        for (int i = 0;i < userInfos.size();i++){
            System.out.println("第"+(i+1)+"个匹配"+userInfos.get(i).getUserName());
        }
        //ModelAndView modelAndView = new ModelAndView();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("UserInfo_search",userInfos);
        //System.out.println(map.get(userInfos) + "wowowowo");
        //modelAndView.addAllObjects(map);
        //httpSession.setAttribute("UserInfo", userInfos);
        return map;

	}

    @RequestMapping(value = "/Is_pass", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> is_pass(HttpSession httpSession,HttpServletRequest httpServletRequest) {

        List<UserInfo> userInfos = userInfoService.selectUserByIsPass(1);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("UserInfo_check", userInfos);
        return map;

    }


    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> check(HttpSession httpSession,HttpServletRequest httpServletRequest) {
        List<UserInfo> userInfos = userInfoService.selectUserByIsPass(0);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("UserInfo_check",userInfos);
        return map;
    }

    @RequestMapping(value = "/alterpsw" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> alterpassword(String username,String oldpassword,String newpassword,HttpServletRequest httpServletRequest){
        System.out.println("修改的用户"+username);
        System.out.println("修改的用户"+oldpassword);
        System.out.println("修改的用户"+newpassword);
        boolean result = false;
        result = userInfoService.alterPassword(username,oldpassword,newpassword);
        System.out.println("修改的用户"+result);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag",result);
        return map;

    }

    @RequestMapping(value = "/resetpsw",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> resetpassword(String select_username,HttpServletRequest httpServletRequest){
        boolean result = userInfoService.resetPassword(select_username);
        System.out.println("选择的用户:"+select_username + result);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag",result);
        return map;

    }

	// @RequestMapping("/login")
	// public String login(String userName, HttpServletRequest request) {
	// List<UserInfo> userInfos =
	// userInfoServiceImpl.selectAllUserInfoByLikeUserName(userName);
	// UserInfo userInfo = userInfos.get(0);
	// request.setAttribute("userInfo", userInfo);
	//
	// return "showUser";
	// }
	public void test(){
		System.out.println("=========wqewq==============");
	}
    @RequestMapping(value = "/del_user",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> user_delete(String select_username,HttpServletRequest httpServletRequest) {
        boolean result = userInfoService.delByUserName(select_username);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag",result);
        return map;
    }


    @RequestMapping(value = "/check_pass_user",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> checkpassuser(String[] check_user_array, HttpServletRequest httpServletRequest) {
        System.out.println("传入数据========="+check_user_array.toString());
        boolean result = userInfoService.updateUsersCheck(check_user_array);
        System.out.println("审核结果========" + result);
//        boolean result = userInfoService.delByUserName(select_username);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag",result);
        return map;
    }
    /**
     * 获取当前用户密码，用于修改密码
     */
    @RequestMapping(value = "/get_psw",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Map<String ,Object> get_psw(String username,String oldpassword,HttpServletRequest httpServletRequest){
        System.out.println("++++++++"+username);
        System.out.println("!!!!!!!!"+oldpassword);
        MacroEnum.KMessageType result = userInfoService.checkLogin(username, oldpassword);
        Map<String,Object> map = new HashMap<String, Object>();
        if (result == MacroEnum.KMessageType.loginSuccess){
            map.put("flag",true);
        }else {
            map.put("flag",false);
        }

        return map;
    }

}