package com.lx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lx.macrofiles.MacroConstant;
import com.lx.macrofiles.MacroEnum;
import com.lx.macrofiles.MacroEnum.KCheckType;
import com.lx.macrofiles.MacroEnum.KFileFormatType;
import com.lx.macrofiles.MacroEnum.KFilePropertyType;
import com.lx.model.FileInfo;
import com.lx.service.FileInfoService;
import com.lx.tools.Page;
import com.lx.tools.ToolFileTransfer;
import com.lx.tools.ToolOffice2PDF;
import com.lx.tools.ToolString;

@Controller
@RequestMapping("/FileInfoController")
public class FileInfoController {

	private static Logger logger = Logger.getLogger(FileInfoController.class);

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

	// ********************公用方法********************
	// 获取待审文件数量
	@RequestMapping(value = "/get_unchecked_num", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get_unchecked_num(HttpSession httpSession) {
		logger.info("=================get_unchecked_num==================");
		Map<String, Object> map = new HashMap<String, Object>();
		int countWithWaitForCheck = fileInfoService.getCountWithWaitForCheck();
		httpSession.setAttribute("countWithWaitForCheck", countWithWaitForCheck);
		map.put("num", countWithWaitForCheck);
		return map;
	}

	// 获取垃圾文件数量
	@RequestMapping(value = "/get_draft_num", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get_draft_num(String username) {
		logger.info("=================get_draft_num==================");
		Map<String, Object> map = new HashMap<>();
		int num = fileInfoService.getCountWithInvalid(username.trim());
		map.put("num", num);
		return map;
	}

	// 获取文件提示
	@RequestMapping(value = "/get_file_points", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get_file_points(KFilePropertyType filePropertyType, String value) {
		logger.info("=================get_file_points==================");
		List<String> fileInfos = fileInfoService.getIntelligentPrompt(filePropertyType, value);
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("FileInfo_check", fileInfos);
		return map;
	}

	// ********************普通用户操作权限********************
	// 公有文件,包括查询
	@RequestMapping(value = "/publicfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> publicfile(HttpServletRequest request, Integer page_Now) {
		logger.info("=================publicfile==================");
		String fileCategory = request.getParameter("fileCategory");
		String subFileCategory = request.getParameter("subFileCategory");
		String fileProperty = request.getParameter("fileProperty");
		String fileIn = request.getParameter("fileIn");

		System.out.println("============类别============" + fileCategory);
		System.out.println("===========子类别=============" + subFileCategory);
		System.out.println("============属性=============" + fileProperty);
		System.out.println("===========搜索值=============" + fileIn);

		int pageNow = 1;
		if (page_Now != null) {
			pageNow = page_Now;
		}
		Page page = new Page(pageNow);

		if (fileCategory == null) {
			fileCategory = "";
		}
		if (subFileCategory == null) {
			subFileCategory = "";
		}
		if (!subFileCategory.equals("")) {
			fileCategory = subFileCategory;
		}

		List<FileInfo> fileInfos = fileInfoService.getFileByFilePropertyWithPass(fileCategory,
				KFilePropertyType.valueOf(fileProperty), fileIn, page);

		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", page.getTotalCount());
		map.put("pageNow", pageNow);
		map.put("pageCount", page.getTotalPageCount());
		map.put("pub_file", fileInfos);
		return map;
	}

	// 我的文件
	@RequestMapping(value = "/myuploadfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> myuploadfile(Integer page_Now, HttpSession httpSession) {
		logger.info("=================myuploadfile==================");
		Map<String, Object> map = new HashMap<>();
		String username = (String) httpSession.getAttribute("username");

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

	// 我的文件，条件查询
	@RequestMapping(value = "/selectByCondition", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectByCondition(Integer page_Now, String cate, String type, HttpSession httpSession) {
		logger.info("=================selectByCondition==================");
		String username = (String) httpSession.getAttribute("username");
		Page page = new Page(page_Now);
		List<FileInfo> pri_file = fileInfoService.selectMyFileInfoByCondition(username, type, cate, page);

		Map<String, Object> map = new HashMap<>();
		map.put("pageNow", page_Now);
		map.put("totalCount", page.getTotalCount());
		map.put("pageCount", page.getTotalPageCount());
		map.put("pri_file", pri_file);
		return map;
	}

	// 我的待审
	@RequestMapping(value = "/waitforcheckfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> waitforcheckfile(Integer page_Now, HttpSession httpSession) {
		logger.info("=================waitforcheckfile==================");

		Map<String, Object> map = new HashMap<String, Object>();
		String username = (String) httpSession.getAttribute("username");

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

	// 我的垃圾箱
	@RequestMapping(value = "/draftfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> draftfile(Integer page_Now, HttpSession httpSession) {
		logger.info("=================draftfile==================");
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

	// 用户删除文件,仅仅放入垃圾箱
	@RequestMapping(value = "/delete_file_to_draft", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> pushWithRecycleBin(Integer[] delete_array) {
		logger.info("=================delete_file_to_draft==================");
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = fileInfoService.batchFilesIsPass(KCheckType.invalid, delete_array);
		map.put("flag", flag);
		return map;
	}

	// 用户还原垃圾箱的文件
	@RequestMapping(value = "/recovery_file", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> popWithRecycleBin(Integer[] recovery_array) {
		logger.info("=================recovery_file==================");
		Map<String, Object> map = new HashMap<String, Object>();
		boolean d = fileInfoService.batchFilesIsPass(KCheckType.pass, recovery_array);
		map.put("flag", d);
		return map;
	}

	// 用户删除文件
	@RequestMapping(value = "/delete_file", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete_file(Integer[] delete_array) {
		logger.info("=================delete_file==================");

		for (Integer integer : delete_array) {
			logger.info("=================integer==================" + integer);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = fileInfoService.delFilesById(delete_array);
		map.put("flag", result);
		return map;
	}

	// 对上传文件信息进行封装
	private boolean encapsulationUploadFile(String filenameFull, FileInfo fileInfo, String filePath, String tempPath) {
		boolean flag = false;
		switch (KFileFormatType.valueOf(ToolString.getFilenameExtension(filenameFull))) {
		case pdf:
			fileInfo.setFileStatus(MacroConstant.PDF);
			if (ToolFileTransfer.transfer(filePath, tempPath)) {
				if (ToolFileTransfer.transfer(filePath, MacroConstant.PDF_DIR)) {
					flag = true;
				}
			}
			break;
		case doc:
			fileInfo.setFileStatus(MacroConstant.DOC);
			flag = copyFile(filePath, tempPath, MacroConstant.WORD_DIR);
			break;
		case docx:
			fileInfo.setFileStatus(MacroConstant.DOCX);
			flag = copyFile(filePath, tempPath, MacroConstant.WORD_DIR);
			break;
		case ppt:
			fileInfo.setFileStatus(MacroConstant.PPT);
			flag = copyFile(filePath, tempPath, MacroConstant.PDF_DIR);
			break;
		case pptx:
			fileInfo.setFileStatus(MacroConstant.PPTX);
			flag = copyFile(filePath, tempPath, MacroConstant.PDF_DIR);
			break;
		case xlsx:
			fileInfo.setFileStatus(MacroConstant.XLSX);
			flag = copyFile(filePath, tempPath, MacroConstant.EXCEL_DIR);
			break;
		default:
			break;
		}

		if (flag) {
			flag = fileInfoService.addFileInfo(fileInfo);
		}
		return flag;
	}

	// C:\\temp\\1479709800.doc --> "D:\\temp\\fefefe.pdf" --> C:\\temp\\
	private boolean copyFile(String filePath, String tempPath, String dirPath) {
		if (new ToolOffice2PDF().openOfficeToPDF(filePath, tempPath)) {
			if (ToolFileTransfer.transfer(filePath, dirPath)) {
				return true;
			}
		}
		return false;
	}

	// 上传文件
	@RequestMapping(value = "/add_file_info", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> uploadFile(HttpServletRequest request) {
		logger.info("=================uploadFile==================");
		String basePath = request.getSession().getServletContext().getRealPath("");

		Map<String, Object> map = new HashMap<>();
		String filename1 = request.getParameter("filename1");
		if (filename1 != null) {
			FileInfo fileInfo1 = new FileInfo();
			fileInfo1.setFileName(filename1.trim());
			fileInfo1.setFileAuthor(request.getParameter("author1").trim());
			fileInfo1.setFileKeywords(request.getParameter("word1").trim());
			fileInfo1.setFileDesc(request.getParameter("area1").trim());
			fileInfo1.setFileDownloadCount(0);

			// 设置类别
			fileInfo1.setFileCategory(request.getParameter("cate1").trim());
			String filechildcate1 = request.getParameter("child_cate1").trim();
			if (!filechildcate1.equals("")) {
				fileInfo1.setFileCategory(filechildcate1);
			}

			// 设置文件上传时间
			String filePath = request.getParameter("filepath1"); // C:\\temp\\1479709800.doc
			String filenameFull = ToolString.getFilenameFull(filePath); // 1479709800.doc
			String Filename = ToolString.getFilename(filenameFull); // 1479709800
			fileInfo1.setFileUploadTime(Integer.valueOf(Filename));

			// 设置文件url
			String temp = MacroConstant.PUBLIC_DIR + Filename + "." + KFileFormatType.pdf; // 预览的相对路径
			String tempPath = basePath + temp; // 预览的绝对路径
			fileInfo1.setFileUrl(temp);

			boolean flag = false;
			// 设置文件可见类型
			String filevisible1 = request.getParameter("pro1");
			fileInfo1.setFileIsVisible(filevisible1);
			if (filevisible1.equals("私有")) {
				fileInfo1.setFileCheck(MacroEnum.KFileVisibleType.privateFile.getValue());
				flag = encapsulationUploadFile(filenameFull, fileInfo1, filePath, tempPath);
			}
			if (filevisible1.equals("公有")) {
				fileInfo1.setFileCheck(MacroEnum.KFileVisibleType.publicFile.getValue());
				flag = encapsulationUploadFile(filenameFull, fileInfo1, filePath, tempPath);
			}

			

			map.put("message1", "hahaha");
			map.put("result1", flag);
		}
		String filename2 = request.getParameter("filename2");
		if (filename2 != null) {
			FileInfo fileInfo2 = new FileInfo();
			fileInfo2.setFileName(filename2.trim());
			fileInfo2.setFileAuthor(request.getParameter("author2").trim());
			fileInfo2.setFileKeywords(request.getParameter("word2").trim());
			fileInfo2.setFileDesc(request.getParameter("area2").trim());
			fileInfo2.setFileDownloadCount(0);

			// 设置类别
			fileInfo2.setFileCategory(request.getParameter("cate2").trim());
			String filechildcate2 = request.getParameter("child_cate2").trim();
			if (!filechildcate2.equals("")) {
				fileInfo2.setFileCategory(filechildcate2);
			}

			// 设置文件可见类型
			String filevisible2 = request.getParameter("pro2");
			fileInfo2.setFileIsVisible(filevisible2);
			if (filevisible2.equals("私有"))
				fileInfo2.setFileCheck(MacroEnum.KFileVisibleType.privateFile.getValue());
			if (filevisible2.equals("公有"))
				fileInfo2.setFileCheck(MacroEnum.KFileVisibleType.publicFile.getValue());

			// 设置文件url
			String filePath = request.getParameter("filepath2"); // C:\\temp\\1479709800.doc
			String filenameFull = ToolString.getFilenameFull(filePath);
			String Filename = ToolString.getFilename(filenameFull); // 1479709800
			fileInfo2.setFileUploadTime(Integer.valueOf(Filename));

			boolean flag = false;

			// TODO Auto-generated method stub
			/*
			 * 
			 * 
			 * */

			if (flag) {
				flag = fileInfoService.addFileInfo(fileInfo2);
			}
			map.put("message2", "hahaha");
			map.put("result2", flag);
		}

		String filename3 = request.getParameter("filename3");
		if (filename3 != null) {
			FileInfo fileInfo3 = new FileInfo();
			fileInfo3.setFileName(filename3.trim());
			fileInfo3.setFileAuthor(request.getParameter("author3").trim());
			fileInfo3.setFileKeywords(request.getParameter("word3").trim());
			fileInfo3.setFileDesc(request.getParameter("area3").trim());
			fileInfo3.setFileDownloadCount(0);

			// 设置类别
			fileInfo3.setFileCategory(request.getParameter("cate3").trim());
			String filechildcate3 = request.getParameter("child_cate3").trim();
			if (!filechildcate3.equals("")) {
				fileInfo3.setFileCategory(filechildcate3);
			}

			// 设置文件可见类型
			String filevisible3 = request.getParameter("pro3");
			fileInfo3.setFileIsVisible(filevisible3);
			if (filevisible3.equals("私有"))
				fileInfo3.setFileCheck(MacroEnum.KFileVisibleType.privateFile.getValue());
			if (filevisible3.equals("公有"))
				fileInfo3.setFileCheck(MacroEnum.KFileVisibleType.publicFile.getValue());

			// 设置文件地址
			String filePath = request.getParameter("filepath3"); // C:\\temp\\1479709800.doc
			String filenameFull = ToolString.getFilenameFull(filePath);
			String Filename = ToolString.getFilename(filenameFull); // 1479709800
			fileInfo3.setFileUploadTime(Integer.valueOf(Filename));

			boolean flag = false;

			// TODO Auto-generated method stub
			/*
			 * 
			 * 
			 * */

			if (flag) {
				flag = fileInfoService.addFileInfo(fileInfo3);
			}

			map.put("message3", "hahaha");
			map.put("result3", flag);
		}
		return map;
	}

	// 下载文件
	@RequestMapping(value = "/download_count_add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> down_check_file(Integer fileid) {
		logger.info("=================down_check_file==================");
		Map<String, Object> map = new HashMap<String, Object>();
		FileInfo fileInfo = fileInfoService.getFileByFileId(fileid);

		int downloadCount = fileInfo.getFileDownloadCount() + 1; // 获取url
		fileInfo.setFileDownloadCount(downloadCount);
		fileInfoService.updateFileByFileId(fileInfo.getFileId(), fileInfo);
		map.put("count", downloadCount);
		return map;
	}

	// ********************管理员操作权限********************
	// 获取资源审核
	@RequestMapping(value = "/get_all_checkfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get_all_checkfile(Integer page_Now) {
		logger.info("=================get_all_checkfile==================");

		Map<String, Object> map = new HashMap<String, Object>();
		int pageNow = 1;
		if (page_Now != null) {
			pageNow = page_Now;
		}
		Page page = new Page(pageNow);
		List<FileInfo> all_checkfile = fileInfoService.getFileWithWaitForCheck(page);

		map.put("pageNow", page_Now);
		map.put("totalCount", page.getTotalCount());
		map.put("pageCount", page.getTotalPageCount());
		map.put("checkfile", all_checkfile);
		return map;
	}

	// 审核通过
	@RequestMapping(value = "/pass_file", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> pass_file(Integer[] pass_array) {
		logger.info("=================pass_file==================");
		Map<String, Object> map = new HashMap<String, Object>();

		boolean result = fileInfoService.batchFilesIsPass(KCheckType.pass, pass_array);
		map.put("flag", result);
		return map;
	}

	// 审核不通过
	@RequestMapping(value = "/notpass_file", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> notpass_file(Integer[] notpass_array) {
		logger.info("=================notpass_file==================");
		Map<String, Object> map = new HashMap<>();
		boolean flag = fileInfoService.batchFilesIsPass(KCheckType.notPass, notpass_array);
		map.put("flag", flag);
		return map;
	}

	// 获取文件描述
	@RequestMapping(value = "/get_file_msg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get_file_msg(Integer fileid) {
		logger.info("=================get_file_msg==================");
		Map<String, Object> map = new HashMap<String, Object>();
		FileInfo fileInfo = fileInfoService.getFileByFileId(fileid);
		map.put("keyWord", fileInfo.getFileKeywords());
		map.put("fileDes", fileInfo.getFileDesc());
		map.put("filecate", fileInfo.getFileCategory());
		return map;
	}

	// 修改文件信息
	@RequestMapping(value = "/alter_file_msg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> alter_file_msg(Integer fileid, String keyword, String filedesc, String filecate) {
		logger.info("=================alter_file_msg==================");
		Map<String, Object> map = new HashMap<>();
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileKeywords(keyword);
		fileInfo.setFileDesc(filedesc);
		fileInfo.setFileCategory(filecate);
		boolean result = fileInfoService.updateFileByFileId(fileid, fileInfo);
		map.put("flag", result);
		return map;
	}

}
