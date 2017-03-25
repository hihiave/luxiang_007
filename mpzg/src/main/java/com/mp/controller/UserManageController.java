package com.mp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mp.common.PaginationUtil;
import com.mp.common.StringUtil;
import com.mp.common.Utility;
import com.mp.model.UserInfo;
import com.mp.service.IUserInfoService;

@Controller
public class UserManageController {
	private static Logger logger = Logger.getLogger(UserManageController.class);
	 @Autowired
	 private IUserInfoService user_info_service ;
	 /**
	     * 跳转界面
	     * @param request
	     * @param model
	     * @return
	     */
		@RequestMapping("/zgmp/check_user_manage")
	    public String checkUserManagement(HttpServletRequest request, ModelMap model) {
	        System.out.println("check check_user_manage");
	        return "user_manage";
	    }
		
		
		/**
	     * 查看信息
	     * @param request
	     * @param response
	     * @param session
	     * @return
	     */
	    @RequestMapping(value="/zgmp/get_user_manage_info" , method= RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object>
	    getMyDraftPageData(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
	    	System.out.println("get user info");
	        List<Integer> nums = getPageNowAndShowNum(request);
	        Map<String,Object> datas = new HashMap<String, Object>();
	        try{
	            int int_page_now = nums.get(0);
	            int int_row_num =  nums.get(1);
	            //int user_id = SessionUtil.getUserIdFromSession(session);
	            List<UserInfo> userinfo;
	            int total = 0;
	            int begin = PaginationUtil.getRowBegin(int_row_num, int_page_now);
	            userinfo = user_info_service.findAllUserManageInfoPerPage(begin, int_row_num);
	            total = user_info_service.findAllUserManageInfoNum();
	            System.out.println("userinfo + " + userinfo);
	            System.out.println("total + " + total);
	            setUserInfoValues(datas, userinfo, total, int_page_now, int_row_num);
	            logger.info(datas.get("userinfo").toString());
	            System.out.println("userinfo + ");
	        }catch (Exception e){
	            e.printStackTrace();
	        }

	        return datas;

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
	     * @param page_assets
	     * @param total
	     * @param page_now
	     * @param row_num
	     */
	    private void setUserInfoValues(Map<String, Object> datas, List<UserInfo> page_users, int total, int page_now, int row_num) {
	        int total_num = total;
	        int page_num = Utility.getPageCount(total_num, row_num);
	        int  from =  PaginationUtil.getFrom(row_num, page_now);
	        // 解决没有数据的时候，从0开始
	        if(total_num == 0 ) {
	            from = 0;
	        }
	        int  end =   PaginationUtil.getTo(from, page_users.size());
	        datas.put("page_now", page_now);
	        datas.put("userinfo", page_users);
	        datas.put("from",  from);
	        datas.put("end",  end);
	        datas.put("total", total_num);
	        datas.put("page_num", page_num);

	    }
}
