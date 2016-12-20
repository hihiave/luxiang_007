package com.lx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lx.model.FileInfo;
import com.lx.model.FileInfoVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lx.service.MyDownloadService;
import com.lx.tools.Page;

@Controller
@RequestMapping("/MyDownloadController")
public class MyDownloadController {

	@Autowired
	MyDownloadService myDownloadService;
	
	/**
	 * 删除我的下载记录
	 */
    @RequestMapping(value = "/del_my_download", method = RequestMethod.POST)

    @ResponseBody
	public Map<String, Object> delMyDownload(Integer[] myDownloadIds,HttpSession httpSession) {
		
		//  myDownloadIds 我的下载记录的id号, 批量的
		myDownloadService.delMyDownload(myDownloadIds);
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		return map;
	}

    /**
     * 添加我的下载记录
     */
    @RequestMapping(value = "/add_my_download", method = RequestMethod.POST)

    @ResponseBody
    public Map<String,Object> addMydownload(HttpSession httpSession,Integer fileid){
        String username =(String) httpSession.getAttribute("username");
        myDownloadService.addMyDownload(username,fileid);
        return null;
    }
	
	/**
	 * 获取我的下载记录
	 */
    @RequestMapping(value = "/get_my_download", method = RequestMethod.POST)

    @ResponseBody
	public Map<String, Object> getMyDownload(Integer page_Now,HttpSession httpSession,HttpServletRequest httpServletRequest) {
		
		//  userName 用户名
		//   page 分页
        Map<String, Object> map = new HashMap<String, Object>();
        String userName =(String) httpSession.getAttribute("username");
        int pageNow = 1;
        if (page_Now != null) {
            pageNow = page_Now;
        }
        Page page = new Page(pageNow);
		
		List<FileInfoVo> fileInfoVos = myDownloadService.getMyDownload(userName, page);
        int pageCount = page.getTotalPageCount();
        int totalCount = page.getTotalCount();
        map.put("totalCount", totalCount);
        map.put("pageNow", page_Now);
        map.put("pageCount", pageCount);
		map.put("download_file",fileInfoVos);

		
		return map;
	}
}
