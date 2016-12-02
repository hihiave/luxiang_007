package com.lx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lx.macrofiles.MacroEnum;
import com.lx.macrofiles.MacroEnum.KCheckType;
import com.lx.model.UserInfo;
import com.lx.service.UserInfoService;
import com.lx.serviceimpl.Page;

@Controller
@RequestMapping("/UserInfoController")
public class UserInfoController {

	@Autowired
	UserInfoService userInfoService;

	/**
	 * 登录
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(String username, String password, HttpSession httpSession) {
		MacroEnum.KMessageType result = userInfoService.checkLogin(username, password);
		Map<String, Object> map = new HashMap<String, Object>();
		switch (result) {
		case loginSuccess:
			UserInfo userInfo = userInfoService.selectUserByUserName(username);
			// httpSession.setAttribute("userinfo",userInfo);
			httpSession.setAttribute("username", username);
			httpSession.setAttribute("time", userInfo.getUserRegisterTime());
			httpSession.setAttribute("userrole", userInfo.getUserRole());
			// httpSession.setAttribute("userid", userInfo.getUserId());
			// httpSession.setAttribute("password",password);
			// httpSession.setAttribute("flag", true);
			map.put("data", "LoginSuccess");
			return map;
		case checkNotPass:
			map.put("data", "CheckNotPass");
			return map;
		case loginFail:
			map.put("data", "LoginFail");
			return map;
		default:
			map.put("data", "异常");
			return map;
		}

	}

	/**
	 * 登出
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> logout(HttpSession httpSession) {
		Map<String, Object> map = new HashMap<String, Object>();
		String str = (String) httpSession.getAttribute("username");
		map.put("username", str);
		System.out.println("登出用户" + str);
		httpSession.removeAttribute("username");

		map.put("result", "success");
		return map;
	}

	/**
	 * 注册
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> regist(String username1, String password1, HttpSession httpSession) {
		System.out.println("======username=====" + username1);
		System.out.println("======password=====" + password1);
		Map<String, Object> map = new HashMap<String, Object>();
		boolean check_result = userInfoService.checkUserIsExist(username1);
		map.put("check", check_result);
		System.out.println("检查结果" + check_result);
		if (!check_result) {

			boolean regist_result = userInfoService.registerUserInfo(username1, password1);

			map.put("data", regist_result);
		}
		return map;
	}

	/**
	 * 添加一个用户
	 */
	@RequestMapping(value = "/adm-adduser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> admadduser(String username, HttpServletRequest request) {
		System.out.println(username + "-------___________");
		boolean check_result = userInfoService.checkUserIsExist(username);
		Map<String, Object> map = new HashMap<String, Object>();
		if (!check_result) {
			if (userInfoService.addUserInfo(username)) {
				map.put("flag", "chenggong");
				return map;
			}
			map.put("flag", "shibai");
			return map;
		}
		map.put("flag", "cunzai");
		return map;
	}

//	@RequestMapping(value = "/inquire", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> inquire(String username_search, HttpSession httpSession) {
//
//		System.out.println(username_search + "!!!!!!!!!!!!!!!!!!___________");
//		List<UserInfo> userInfos = userInfoService.selectAllUserInfoByLikeUserName(username_search);
//		System.out.println(userInfos.size() + "个匹配选项");
//		for (int i = 0; i < userInfos.size(); i++) {
//			System.out.println("第" + (i + 1) + "个匹配" + userInfos.get(i).getUserName());
//		}
//		// ModelAndView modelAndView = new ModelAndView();
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("UserInfo_search", userInfos);
//		// System.out.println(map.get(userInfos) + "wowowowo");
//		// modelAndView.addAllObjects(map);
//		// httpSession.setAttribute("UserInfo", userInfos);
//		return map;
//
//	}
	/**
	 * 用户搜索智能提示
	 */
	@RequestMapping(value = "/get_user_points", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get_user_points(HttpSession httpSession, HttpServletRequest request) {
       
		List<String> userInfos = userInfoService.getUserNames("");
		Map<String, Object> map = new HashMap<String, Object>();
       
        map.put("UserInfo_check", userInfos);
		//System.out.println("check+++++++++"+userInfos.size());
		return map;
	}
	
	/**
	 * 用户待审核
	 */
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> check(Integer page_Now,String username,HttpSession httpSession, HttpServletRequest request) {
        int pageNow = 1;
        // 动态接收pageNow
        if (page_Now != null) {
            pageNow = page_Now;
        }
        Page page = new Page(pageNow);
		List<UserInfo> userInfos = userInfoService.selectUserByIsPass(KCheckType.waitForCheck, page,username);
		Map<String, Object> map = new HashMap<String, Object>();
        int totalCount = page.getTotalCount();
        int pageCount = page.getTotalPageCount();
        map.put("totalCount",totalCount);
        map.put("pageNow",pageNow);
        map.put("pageCount",pageCount);
        map.put("UserInfo_check", userInfos);
		System.out.println("check+++++++++");
		return map;
	}

	/**
	 * 用户通过
	 */
	@RequestMapping(value = "/Is_pass", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> is_pass(Integer page_Now,String username,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
		int pageNow = 1;
		// 动态接收pageNow
		if (page_Now != null) {
			pageNow = page_Now;
		}
		Page page = new Page(pageNow);
		
		List<UserInfo> userInfos = userInfoService.selectUserByIsPass(KCheckType.pass, page ,username);
		int pageCount = page.getTotalPageCount();
        int totalCount = page.getTotalCount();
		map.put("totalCount",totalCount);
        map.put("pageNow",pageNow);
        map.put("pageCount",pageCount);
		map.put("UserInfo_check", userInfos);
		return map;
	}

	@RequestMapping(value = "/alterpsw", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> alterpassword(String username, String oldpassword, String newpassword,
			HttpServletRequest httpServletRequest) {
		System.out.println("修改的用户" + username);
		System.out.println("修改的用户" + oldpassword);
		System.out.println("修改的用户" + newpassword);
		boolean result = userInfoService.alterPassword(username, oldpassword, newpassword);
		System.out.println("修改的用户" + result);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", result);
		return map;

	}

	@RequestMapping(value = "/resetpsw", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> resetpassword(String select_username, HttpServletRequest httpServletRequest) {
		boolean result = userInfoService.resetPassword(select_username);
		System.out.println("选择的用户:" + select_username + result);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", result);
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
	public void test() {
		System.out.println("=========wqewq==============");
	}

	// 批量删除用户
	@RequestMapping(value = "/del_user", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> user_delete(String select_username, HttpServletRequest httpServletRequest) {

		boolean result = userInfoService.delUsersByUserName(select_username);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", result);
		return map;
	}

	/**
	 * 批量用户名审核通过
	 */
	@RequestMapping(value = "/check_pass_user", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> checkpassuser(String[] check_user_array, HttpServletRequest httpServletRequest) {
		System.out.println("传入数据=========" + check_user_array.toString());
		boolean result = userInfoService.batchUsersPass(check_user_array);
		System.out.println("审核结果========" + result);
		// boolean result = userInfoService.delByUserName(select_username);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", result);
		return map;
	}
	
	/**
	 * 批量用户名审核拒绝
	 */
	@RequestMapping(value = "/check_refuse_user", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> checkrefuseuser(String[] check_user_array, HttpServletRequest httpServletRequest) {
		System.out.println("传入数据=========" + check_user_array.toString());
		boolean result = userInfoService.delUsersByUserName(check_user_array);
		System.out.println("审核结果========" + result);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", result);
		return map;
	}

	/**
	 * 获取当前用户密码，用于修改密码
	 */
	@RequestMapping(value = "/get_psw", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> get_psw(String username, String oldpassword, HttpServletRequest httpServletRequest) {
		System.out.println("++++++++" + username);
		System.out.println("!!!!!!!!" + oldpassword);
		MacroEnum.KMessageType result = userInfoService.checkLogin(username, oldpassword);
		Map<String, Object> map = new HashMap<String, Object>();
		if (result == MacroEnum.KMessageType.loginSuccess) {
			map.put("flag", true);
		} else {
			map.put("flag", false);
		}

		return map;
	}

}