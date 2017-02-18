package com.lx.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lx.macrofiles.MacroConstant;
import com.lx.tools.ToolDate;
import com.lx.tools.ToolString;

@Controller
@RequestMapping("/FileUploadController")
public class FileUploadController {
	
	private static Logger logger = Logger.getLogger(FileUploadController.class);

	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> fileUpload(HttpServletRequest request, HttpServletResponse response) {
		logger.info("=================fileUpload==================");
		Map<String, Object> map = new HashMap<>();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		String dirPath = MacroConstant.TEMP; // 上传的地址
		File file = new File(dirPath);
		if (!file.exists()) {
			file.mkdirs();
		}

		String message = ""; // 消息提示
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();// 创建DiskFileItemFactory工厂
			factory.setSizeThreshold(1024 * 100); // 设置工厂缓冲区大小，默认是10KB 这里设为100KB
			factory.setRepository(file); // 设置上传时生成的临时文件的保存目录
			ServletFileUpload upload = new ServletFileUpload(factory); // 创建文件上传解析器
			upload.setHeaderEncoding("UTF-8");
			if (!ServletFileUpload.isMultipartContent(request)) {
				map.put("message", message);
				return map;
			}
			upload.setFileSizeMax(1024 * 1024 * 20);// 单个文件的最大大小 这里是20M
			upload.setSizeMax(1024 * 1024 * 50);// 文件总大小 这里设为50M

			// 用ServletFileUpload解析上传数据,返回一个List<FileItem>集合
			// 每一个FileItem对应一个Form表单的输入项
			List<FileItem> fileItems = upload.parseRequest(request);
			// 如果fileitem中封装的是普通输入项的数据
			// if (fileItems.get(0).isFormField()) {}
			for (FileItem fileItem : fileItems) {
				// fileitem中封装的是上传文件
				String filePath = fileItem.getName();
				if (filePath == null || filePath.trim().equals("")) {
					continue;
				}
				// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的,如：c:\a\b\1.txt;
				// 而有些只是单纯的文件名，如：1.txt，下面做兼容
				String filenameExtension = ToolString.getFilenameExtension(filePath);

				// "C:\\temp\\1483337258.doc"
				filePath = dirPath + ToolDate.getCurrentTimestamp() + "." + filenameExtension;
				InputStream in = fileItem.getInputStream(); // 获取item中上传文件的输入流
				FileOutputStream out = new FileOutputStream(filePath); // 创建一个文件输出流
				byte buffer[] = new byte[1024]; // 创建一个缓存区
				int len = 0; // 建立一个标志判断输入流中的数据是否已经读完
				
				// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
				while ((len = in.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
				in.close();
				out.close();
				fileItem.delete();
				map.put("dirPath", filePath);
				message = "添加文件成功，请完善信息上传！";
			}
		} catch (FileUploadBase.FileSizeLimitExceededException e) {
			e.printStackTrace();
			message = "单个文件超出最大值";
		} catch (FileUploadBase.SizeLimitExceededException e) {
			e.printStackTrace();
			message = "上传文件的总大小超出最大值";
		} catch (Exception e) {
			e.printStackTrace();
			message = "文件上传失败";
		}
		map.put("message", message);
		return map;
	}

}
