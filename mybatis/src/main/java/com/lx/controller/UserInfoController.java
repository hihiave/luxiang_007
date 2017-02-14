package com.lx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lx.macrofiles.MacroConstant;
import com.lx.macrofiles.MacroEnum.KCheckType;
import com.lx.model.UserInfo;
import com.lx.service.UserInfoService;
import com.lx.tools.Page;

@Controller
@RequestMapping("/UserInfoController")
public class UserInfoController {

	private static Logger logger = Logger.getLogger(UserInfoController.class);

	@Autowired
	UserInfoService userInfoService;

	// 登录
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(String username, String password, HttpSession httpSession) {
		logger.info("=================login==================");
		Map<String, Object> map = new HashMap<>();
		switch (userInfoService.checkLogin(username, password)) {
		case success:
			UserInfo userInfo = userInfoService.selectUserByUserName(username);
			// httpSession.setAttribute("userinfo",userInfo);
			httpSession.setAttribute("username", username);
			String user = userInfo.getUserRole();
			System.out.println("name=========11========" + user);
			httpSession.setAttribute("time", userInfo.getUserRegisterTime());
			httpSession.setAttribute("userrole", userInfo.getUserRole());
			httpSession.setAttribute("usertruename", userInfo.getUserRealName());
			httpSession.setAttribute("email", userInfo.getUserEmail());
			httpSession.setAttribute("userid", userInfo.getUserId());
			httpSession.setAttribute("adminid", MacroConstant.ADMIN);
			// httpSession.setAttribute("password",password);
			// httpSession.setAttribute("flag", true);
			map.put("data", "LoginSuccess");
			return map;
		case checkNotPass:
			map.put("data", "CheckNotPass");
			return map;
		case fail:
			map.put("data", "LoginFail");
			return map;
		default:
			map.put("data", "异常");
			return map;
		}
	}

	// 登出
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> logout(HttpSession httpSession) {
		logger.info("=================logout==================");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", (String) httpSession.getAttribute("username"));
		httpSession.removeAttribute("username");
		map.put("result", "success");
		return map;
	}

	// 提示待审数量
	@RequestMapping(value = "/get_unchecked_num", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get_unchecked_num(HttpSession httpSession) {
		logger.info("=================get_unchecked_num==================");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", userInfoService.getCountWithWaitForCheck());
		return map;
	}

	// 注册
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> regist(String username, String userpassword, String truename, String email) {
		logger.info("=================regist==================");
		Map<String, Object> map = new HashMap<String, Object>();
		boolean check_result = userInfoService.checkUserIsExist(username);
		map.put("check", check_result);

		if (!check_result) {
			boolean regist_result = userInfoService.registerUserInfo(username, userpassword, truename, email);
			map.put("data", regist_result);
		}
		return map;
	}

	// 添加一个用户
	@RequestMapping(value = "/adm-adduser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> admadduser(String username) {
		logger.info("=================adm-adduser==================");
		Map<String, Object> map = new HashMap<String, Object>();
		if (userInfoService.checkUserIsExist(username)) {
			map.put("flag", "cunzai");
			return map;
		}

		if (userInfoService.addUserInfo(username)) {
			map.put("flag", "chenggong");
			return map;
		}

		map.put("flag", "shibai");
		return map;
	}

	// 用户搜索智能提示
	@RequestMapping(value = "/get_user_points", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get_user_points() {
		logger.info("=================get_user_points==================");
		List<String> userInfos = userInfoService.getUserNames("");
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("UserInfo_check", userInfos);
		return map;
	}

	// 用户待审核
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> check(Integer page_Now, String username) {
		logger.info("=================check==================");
		int pageNow = 1;
		// 动态接收pageNow
		if (page_Now != null) {
			pageNow = page_Now;
		}
		Page page = new Page(pageNow);
		List<UserInfo> userInfos = userInfoService.selectUserByIsPass(KCheckType.waitForCheck, page, username);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNow", pageNow);
		map.put("totalCount", page.getTotalCount());
		map.put("pageCount", page.getTotalPageCount());
		map.put("UserInfo_check", userInfos);
		System.out.println("check+++++++++");
		return map;
	}

	// 用户通过
	@RequestMapping(value = "/Is_pass", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> is_pass(Integer page_Now, String username) {
		logger.info("=================Is_pass==================");
		Map<String, Object> map = new HashMap<String, Object>();
		int pageNow = 1;
		// 动态接收pageNow
		if (page_Now != null) {
			pageNow = page_Now;
		}
		Page page = new Page(pageNow);

		List<UserInfo> userInfos = userInfoService.selectUserByIsPass(KCheckType.pass, page, username);
		int pageCount = page.getTotalPageCount();
		int totalCount = page.getTotalCount();
		map.put("totalCount", totalCount);
		map.put("pageNow", pageNow);
		map.put("pageCount", pageCount);
		map.put("UserInfo_check", userInfos);
		return map;
	}

	// 修改密码
	@RequestMapping(value = "/alterpsw", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> alterpassword(String username, String oldpassword, String newpassword) {
		logger.info("=================alterpsw==================");
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = userInfoService.alterPassword(username, oldpassword, newpassword);
		map.put("flag", result);
		return map;
	}

	// 重置密码
	@RequestMapping(value = "/resetpsw", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> resetpassword(String select_username) {
		logger.info("=================resetpsw==================");
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = userInfoService.resetPassword(select_username);
		map.put("flag", result);
		return map;
	}

	// 批量删除用户
	@RequestMapping(value = "/del_user", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> user_delete(String select_username) {
		logger.info("=================del_user==================");
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = userInfoService.delUsersByUserName(select_username);
		map.put("flag", result);
		return map;
	}

	// 批量用户名审核通过
	@RequestMapping(value = "/check_pass_user", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> checkpassuser(String[] check_user_array) {
		logger.info("=================check_pass_user==================");
		boolean result = userInfoService.batchUsersPass(check_user_array);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", result);
		return map;
	}

	// 批量用户名审核拒绝
	@RequestMapping(value = "/check_refuse_user", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> checkrefuseuser(String[] check_user_array) {
		logger.info("=================check_refuse_user==================");
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = userInfoService.delUsersByUserName(check_user_array);
		map.put("flag", result);
		return map;
	}

	// 获取当前用户密码，用于修改密码
	@RequestMapping(value = "/get_psw", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> get_psw(String username, String oldpassword) {
		logger.info("=================check_refuse_user==================");
		Map<String, Object> map = new HashMap<>();
		switch (userInfoService.checkLogin(username, oldpassword)) {
		case success:
			map.put("flag", true);
			break;
		case fail:
			map.put("flag", false);
			break;
		default:
			map.put("flag", false);
			break;
		}
		return map;
	}

	// 修改用户信息
	@RequestMapping(value = "/change_msg", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> changeUserMsg(Integer userid, String truename, String email, HttpSession httpSession) {
		logger.info("=================check_refuse_user==================");

		UserInfo userInfo = new UserInfo();
		userInfo.setUserEmail(email);
		userInfo.setUserRealName(truename);
		Map<String, Object> map = new HashMap<>();
		if (userInfoService.alterUserInfo(userid, userInfo)) {
			httpSession.setAttribute("usertruename", truename);
			httpSession.setAttribute("email", email);
			map.put("flag", true);
		} else {
			map.put("flag", false);
		}
		return map;
	}

	// @RequestMapping(value = "/inquire", method = RequestMethod.POST)
	// @ResponseBody
	// public Map<String, Object> inquire(String username_search, HttpSession
	// httpSession) {
	//
	// System.out.println(username_search + "!!!!!!!!!!!!!!!!!!___________");
	// List<UserInfo> userInfos =
	// userInfoService.selectAllUserInfoByLikeUserName(username_search);
	// System.out.println(userInfos.size() + "个匹配选项");
	// for (int i = 0; i < userInfos.size(); i++) {
	// System.out.println("第" + (i + 1) + "个匹配" +
	// userInfos.get(i).getUserName());
	// }
	// // ModelAndView modelAndView = new ModelAndView();
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("UserInfo_search", userInfos);
	// // System.out.println(map.get(userInfos) + "wowowowo");
	// // modelAndView.addAllObjects(map);
	// // httpSession.setAttribute("UserInfo", userInfos);
	// return map;
	//
	// }
	
}