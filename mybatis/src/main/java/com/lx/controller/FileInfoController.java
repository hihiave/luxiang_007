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

import com.alibaba.fastjson.JSON;
import com.lx.macrofiles.MacroEnum.KCheckType;
import com.lx.macrofiles.MacroEnum.KFilePropertyType;
import com.lx.model.FileInfo;
import com.lx.service.FileInfoService;
import com.lx.tools.Page;
import com.lx.tools.ToolDate;

@Controller
@RequestMapping("/FileInfoController")
public class FileInfoController {

	@Autowired
	FileInfoService fileInfoService;

	@RequestMapping("/fy")
	public String showFileInfo(HttpServletRequest request) {
		System.out.println("=====================");
		// FileInfo fileInfo = fileInfoServiceImpl.getFileInfoById(fileId);

		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileCategory("专利类");
		fileInfo.setFileName("哈利波特");

		request.setAttribute("fileInfo", fileInfo);
		return "showFileInfo";
	}

	/**
	 * 修改文件描述
	 */
	@RequestMapping(value = "/alter_file_msg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> alter_file_msg(Integer fileid, String keyword, String filedesc, String filecate) {
		Map<String, Object> map = new HashMap<String, Object>();
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileCategory(filecate);
		if (keyword != null || filedesc != null) {
			fileInfo.setFileDesc(filedesc);
			fileInfo.setFileKeywords(keyword);
		}
		boolean result = fileInfoService.updateFileByFileId(fileid, fileInfo);
		map.put("flag", result);
		return map;
	}

	/**
	 * 获取文件描述
	 */
	@RequestMapping(value = "/get_file_msg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get_file_msg(Integer fileid) {
		Map<String, Object> map = new HashMap<String, Object>();
		FileInfo fileInfo = fileInfoService.getFileByFileId(fileid);
		String keyWord = fileInfo.getFileKeywords();
		String fileDes = fileInfo.getFileDesc();
		String fileCate = fileInfo.getFileCategory();
		map.put("keyWord", keyWord);
		map.put("fileDes", fileDes);
		map.put("filecate", fileCate);
		return map;
	}

	@RequestMapping(value = "/get_file_points", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get_file_points(KFilePropertyType filePropertyType, String value) {
		System.out.println("=================get_file_points=============");
		System.out.println("++++++++" + filePropertyType);
		System.out.println("1" + value);
		List<String> fileInfos = fileInfoService.getIntelligentPrompt(filePropertyType, value);
		Map<String, Object> map = new HashMap<String, Object>();
		/*
		 * Iterator<String> x=fileInfos.iterator(); while(x.hasNext()) {
		 * System.out.println(x.next()); }
		 */
		map.put("FileInfo_check", fileInfos);
		// System.out.println("check+++++++++"+userInfos.size());
		return map;
	}

	// ********************普通用户操作权限********************
	/**
	 * 公有文件,包括查询
	 */
	@RequestMapping(value = "/publicfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> publicfile(String fileCategory, String fileProperty, String fileIn, Integer page_Now) {
		System.out.println("=================publicfile=============");
		System.out.println("==q===" + "类别" + fileCategory + "属性" + fileProperty + "搜索值" + fileIn);

		int pageNow = 1;
		if (page_Now != null) {
			pageNow = page_Now;
		}
		Page page = new Page(pageNow);

		List<FileInfo> fileInfos = fileInfoService.getFileByFilePropertyWithPass(fileCategory, KFilePropertyType.valueOf(fileProperty), fileIn,
				page);
		
		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", page.getTotalCount());
		map.put("pageNow", pageNow);
		map.put("pageCount", page.getTotalPageCount());
		map.put("pub_file", fileInfos);
		return map;
	}

	/**
	 * 我的上传
	 */
	@RequestMapping(value = "/myuploadfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> myuploadfile(Integer page_Now, HttpSession httpSession) {
		Map<String, Object> map = new HashMap<String, Object>();
		String username = (String) httpSession.getAttribute("username");
		System.out.println(username + "hahahaha");

		Page page = new Page(page_Now);

		List<FileInfo> pri_file = fileInfoService.selectMyFileInfo(username, page, KCheckType.pass);
		int pageCount = page.getTotalPageCount();
		int totalCount = page.getTotalCount();
		map.put("totalCount", totalCount);
		map.put("pageNow", page_Now);
		map.put("pageCount", pageCount);
		map.put("pri_file", pri_file);
		return map;
	}

	/**
	 * 我的下载
	 */
	public String myDownloadFile() {

		return null;
	}

	/**
	 * 我的待审
	 */
	@RequestMapping(value = "/waitforcheckfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> waitforcheckfile(Integer page_Now, HttpSession httpSession,
			HttpServletRequest httpServletRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		String username = (String) httpSession.getAttribute("username");
		System.out.println(username + "hahahaha");

		Page page = new Page(page_Now);

		List<FileInfo> wait_file = fileInfoService.selectMyFileInfo(username, page, KCheckType.waitForCheck,
				KCheckType.notPass);
		int pageCount = page.getTotalPageCount();
		int totalCount = page.getTotalCount();
		map.put("totalCount", totalCount);
		map.put("pageNow", page_Now);
		map.put("pageCount", pageCount);
		map.put("wait_file", wait_file);
		return map;
	}

	/**
	 * 我的草稿
	 */
	@RequestMapping(value = "/draftfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> draftfile(Integer page_Now, HttpSession httpSession,
			HttpServletRequest httpServletRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		String username = (String) httpSession.getAttribute("username");

		Page page = new Page(page_Now);

		List<FileInfo> draft_file = fileInfoService.selectMyFileInfo(username, page, KCheckType.invalid);

		int pageCount = page.getTotalPageCount();
		int totalCount = page.getTotalCount();
		map.put("totalCount", totalCount);
		map.put("pageNow", page_Now);
		map.put("pageCount", pageCount);

		map.put("all_file", draft_file);
		return map;
	}

	/**
	 * 用户删除文件,仅仅放入垃圾箱
	 */
	@RequestMapping(value = "/delete_file_to_draft", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> pushWithRecycleBin(Integer[] delete_array) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = fileInfoService.batchFilesIsPass(KCheckType.invalid, delete_array);
		map.put("flag", flag);
		return map;
	}

	/**
	 * 用户还原垃圾箱的文件
	 */
	@RequestMapping(value = "/recovery_file", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> popWithRecycleBin(Integer[] recovery_array) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean d = fileInfoService.batchFilesIsPass(KCheckType.pass, recovery_array);
		map.put("flag", d);
		return map;
	}

	/**
	 * 用户删除文件,从垃圾箱里再次删除
	 */
	@RequestMapping(value = "/delete_file", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete_file(Integer[] delete_array) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("传入数据=========");

		boolean result = fileInfoService.delFilesById(delete_array);
		map.put("flag", result);
		return map;
	}

	/**
	 * 上传文件
	 */
	@RequestMapping(value = "/add_file_info", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> uploadFile(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		// System.out.println(request.getParameter("filename1"));
		// System.out.println(request.getParameter("filename2"));
		// System.out.println(request.getParameter("filename3"));
		boolean result1;
		boolean result2;
		boolean result3;
		if (request.getParameter("filename1") != null) {
			String filename1 = request.getParameter("filename1");
			String fileauthor1 = request.getParameter("author1");
			String filekeys1 = request.getParameter("word1");
			String filecate1 = request.getParameter("cate1");
			String filedesc1 = request.getParameter("area1");
			String filechildcate1 = request.getParameter("child_cate1");
			String filevisible1 = request.getParameter("pro1");
			FileInfo fileInfo1 = new FileInfo();
			fileInfo1.setFileName(filename1);
			fileInfo1.setFileAuthor(fileauthor1);
			fileInfo1.setFileDesc(filedesc1);
			if (filechildcate1 != "") {
				fileInfo1.setFileCategory(filechildcate1);
			} else {
				fileInfo1.setFileCategory(filecate1);
			}
			if (filevisible1.equals("私有")) {
				fileInfo1.setFileCheck(1);
			} else if (filevisible1.equals("公有")) {
				fileInfo1.setFileCheck(0);
			}

			fileInfo1.setFileDownloadCount(0);
			fileInfo1.setFileUploadTime(ToolDate.getCurrentTimestamp());
			fileInfo1.setFileKeywords(filekeys1);
			fileInfo1.setFileIsVisible(filevisible1);
			fileInfo1.setFileUrl(request.getParameter("filepath1"));
			fileInfo1.setFileStatus(1);

			result1 = fileInfoService.addFileInfo(fileInfo1);
			map.put("message1", "hahaha");
			map.put("result1", result1);
		}
		if (request.getParameter("filename2") != null) {
			String filename2 = request.getParameter("filename2");
			String fileauthor2 = request.getParameter("author2");
			String filekeys2 = request.getParameter("word2");
			String filecate2 = request.getParameter("cate2");
			String filedesc2 = request.getParameter("area2");
			String filechildcate2 = request.getParameter("child_cate2");
			String filevisible2 = request.getParameter("pro2");
			FileInfo fileInfo2 = new FileInfo();
			fileInfo2.setFileName(filename2);
			fileInfo2.setFileAuthor(fileauthor2);
			if (filechildcate2 != "") {
				fileInfo2.setFileCategory(filechildcate2);
			} else {
				fileInfo2.setFileCategory(filecate2);
			}
			if (filevisible2.equals("私有")) {
				fileInfo2.setFileCheck(1);
			} else if (filevisible2.equals("公有")) {
				fileInfo2.setFileCheck(0);
			}
			fileInfo2.setFileDownloadCount(0);
			fileInfo2.setFileUploadTime(ToolDate.getCurrentTimestamp());
			fileInfo2.setFileKeywords(filekeys2);
			fileInfo2.setFileIsVisible(filevisible2);
			fileInfo2.setFileUrl(request.getParameter("filepath2"));
			fileInfo2.setFileStatus(1);
			fileInfo2.setFileDesc(filedesc2);

			result2 = fileInfoService.addFileInfo(fileInfo2);
			map.put("message2", "hahaha");
			map.put("result2", result2);
		}
		if (request.getParameter("filename3") != null) {
			String filename3 = request.getParameter("filename3");
			String fileauthor3 = request.getParameter("author3");
			String filekeys3 = request.getParameter("word3");
			String filecate3 = request.getParameter("cate3");
			String filedesc3 = request.getParameter("area3");
			String filechildcate3 = request.getParameter("child_cate3");
			String filevisible3 = request.getParameter("pro3");
			FileInfo fileInfo3 = new FileInfo();
			fileInfo3.setFileName(filename3);
			fileInfo3.setFileAuthor(fileauthor3);
			if (filechildcate3 != "") {
				fileInfo3.setFileCategory(filechildcate3);
			} else {
				fileInfo3.setFileCategory(filecate3);
			}
			if (filevisible3.equals("私有")) {
				fileInfo3.setFileCheck(1);
			} else if (filevisible3.equals("公有")) {
				fileInfo3.setFileCheck(0);
			}
			fileInfo3.setFileDownloadCount(0);
			fileInfo3.setFileUploadTime(ToolDate.getCurrentTimestamp());
			fileInfo3.setFileKeywords(filekeys3);
			fileInfo3.setFileIsVisible(filevisible3);
			fileInfo3.setFileUrl(request.getParameter("filepath3"));
			fileInfo3.setFileStatus(1);
			fileInfo3.setFileDesc(filedesc3);

			result3 = fileInfoService.addFileInfo(fileInfo3);
			map.put("message3", "hahaha");
			map.put("result3", result3);
		}

		return map;
	}

	/**
	 * 下载文件
	 */
	@RequestMapping(value = "/download_count_add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> down_check_file(Integer fileid) {

		Map<String, Object> map = new HashMap<String, Object>();
		FileInfo fileInfo = fileInfoService.getFileByFileId(fileid);

		int downloadCount = fileInfo.getFileDownloadCount() + 1; // 获取url
		fileInfo.setFileDownloadCount(downloadCount);
		fileInfoService.updateFileByFileId(fileInfo.getFileId(), fileInfo);
		map.put("count", downloadCount);
		return map;
	}

	// ********************管理员操作权限********************
	/**
	 * 资源审核
	 */
	@RequestMapping(value = "/get_all_checkfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get_all_checkfile(Integer page_Now) {
		// 你看一下，完善这个方法
		Map<String, Object> map = new HashMap<String, Object>();
		int pageNow = 1;
		if (page_Now != null) {
			pageNow = page_Now;
		}
		Page page = new Page(pageNow);
		List<FileInfo> all_checkfile = fileInfoService.getFileWithWaitForCheck(page);
		int pageCount = page.getTotalPageCount();
		int totalCount = page.getTotalCount();
		map.put("totalCount", totalCount);
		map.put("pageNow", page_Now);
		map.put("pageCount", pageCount);
		map.put("checkfile", all_checkfile);
		return map;
	}

	/**
	 * 审核通过
	 */
	@RequestMapping(value = "/pass_file", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> pass_file(Integer[] pass_array) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("传入数据=========");
		boolean result = fileInfoService.batchFilesIsPass(KCheckType.pass, pass_array);
		map.put("flag", result);
		return map;
	}

	/**
	 * 审核不通过
	 */
	@RequestMapping(value = "/notpass_file", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> notpass_file(Integer[] notpass_array) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = fileInfoService.batchFilesIsPass(KCheckType.notPass, notpass_array);
		map.put("flag", flag);
		return map;
	}
}
