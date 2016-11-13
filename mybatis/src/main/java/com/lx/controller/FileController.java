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

import com.lx.macrofiles.MacroEnum.KCheckType;
import com.lx.macrofiles.MacroEnum.KFilePropertyType;
import com.lx.model.FileInfo;
import com.lx.service.FileInfoService;

@Controller
@RequestMapping("/FileInfoController")
public class FileController {

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
		// System.out.println;
	}

	// ********************普通用户操作权限********************
	/**
	 * 公有文件,包括查询
	 */
	@RequestMapping(value = "/publicfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> publicfile() {
		Map<String, Object> map = new HashMap<String, Object>();

		List<FileInfo> pub_file = fileInfoService.selectFileByIsPass(KCheckType.pass);
		map.put("pub_file", pub_file);
		return map;
	}

	/**
	 * 查询
	 */
	@RequestMapping(value = "/search_file", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> search_file(String fileCategory, String fileProperty, String fileIn) {

		System.out.println("类别" + fileCategory + "属性" + fileProperty + "搜索值" + fileIn);
		Map<String, Object> map = new HashMap<String, Object>();
		// 将第二个String转化成枚举类型的 , 注意： param2 只能是枚举里面有的。
		KFilePropertyType filePropertyType = KFilePropertyType.valueOf(fileProperty);
		List<FileInfo> fileList = fileInfoService.getFileByLikeFileProperty(fileCategory, filePropertyType, fileIn);
		map.put("filelist", fileList);
		return map;
	}

	/**
	 * 我的上传
	 */
	@RequestMapping(value = "/myuploadfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> myuploadfile(HttpSession httpSession, HttpServletRequest httpServletRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		String username = (String) httpSession.getAttribute("username");
		System.out.println(username + "hahahaha");
		List<FileInfo> pri_file = fileInfoService.selectMyFileInfo(username, KCheckType.pass);

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
	public Map<String, Object> waitforcheckfile(HttpSession httpSession, HttpServletRequest httpServletRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		String username = (String) httpSession.getAttribute("username");
		System.out.println(username + "hahahaha");
		List<FileInfo> wait_file = fileInfoService.selectMyFileInfo(username, KCheckType.waitForCheck);
		List<FileInfo> pri_file_3 = fileInfoService.selectMyFileInfo(username, KCheckType.notPass);
		wait_file.addAll(pri_file_3);
		map.put("wait_file", wait_file);
		return map;
	}

	/**
	 * 我的草稿
	 */
	@RequestMapping(value = "/draftfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> draftfile(HttpSession httpSession, HttpServletRequest httpServletRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		String username = (String) httpSession.getAttribute("username");
		
		List<FileInfo> draft_file = fileInfoService.selectMyFileInfo(username, KCheckType.invalid);
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
	public String uploadFile() {
		return null;
	}

	/**
	 * 下载文件
	 */
	@RequestMapping(value = "/down_check_file", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public void down_check_file(String select_filename, HttpServletRequest httpServletRequest) {

		// author luxiang
		// 根据文件id查询文件
		FileInfo fileInfo = fileInfoService.getFileByFileId(40);

		fileInfo.getFileUrl(); // 获取url

		// ..... 执行一些下载的代码
		// .....
		// .....

		// 下载成功后, 将 下载次数 +1
		int count = fileInfo.getFileDownloadCount();
		count++;

		fileInfo.setFileDownloadCount(count);

		// 更新文件信息
		fileInfoService.updateFileByFileId(fileInfo.getFileId(), fileInfo);

	}

	// ********************管理员操作权限********************
	/**
	 * 资源审核
	 */
	@RequestMapping(value = "/get_all_checkfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get_all_checkfile(HttpSession httpSession, HttpServletRequest httpServletRequest) {
		Map<String, Object> map = new HashMap<String, Object>();

		List<FileInfo> all_checkfile = fileInfoService.selectFileByIsPass(KCheckType.waitForCheck);

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
