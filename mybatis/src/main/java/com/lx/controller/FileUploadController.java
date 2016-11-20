package com.lx.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lx.tools.ToolDate;

@Controller
@RequestMapping("/FileUploadController")
public class FileUploadController {
	// String fileName = "西游记"
	// String fileNameExtension = ".png"
	// String fileNameFull = fileName + fileNameExtension;

	// String dirPath = "c:/a/b";
	// String filePath = dirPath + "/" + fileNameFull;

	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> fileUpload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		// System.out.println(request.getParameter("file"));
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		String dirPath = "C:/Users"; // 上传的地址

		File file = new File(dirPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		System.out.println("=====================================");
		String tempPath = "C:/temp";
		file = new File(tempPath);
		if (!file.exists()) {
			System.out.println(tempPath + "临时目录不存在，需要创建");
			file.mkdirs(); // 创建临时目录
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
			upload.setFileSizeMax(1024 * 1024 * 10);// 单个文件的最大大小 这里是10M
			upload.setSizeMax(1024 * 1024 * 30);// 文件总大小 这里设为30M

			// 用ServletFileUpload解析上传数据,返回一个List<FileItem>集合
			// 每一个FileItem对应一个Form表单的输入项
			List<FileItem> fileItems = upload.parseRequest(request);

			System.out.println("fileItems ==================== " + fileItems.size());

			for (FileItem fileItem : fileItems) {
				// 如果fileitem中封装的是普通输入项的数据
				if (fileItem.isFormField()) {
					System.out.println("");

					String name = fileItem.getFieldName();
					String value = fileItem.getString("UTF-8");
					System.out.println(name + "=" + value);
				}
				// 如果fileitem中封装的是上传文件
				else {
					String filePath = fileItem.getName();

					System.out.println("String filePath =========" + filePath);
					if (filePath == null || filePath.trim().equals("")) {
						continue;
					}
					// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的
					// 如：
					// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt处理获取到的上传文件的文件名的路径部分，只保留文件名部分

					String fileNameFull = filePath.substring(filePath.lastIndexOf("\\") + 1);

					System.out.println("=======fileNameFull=====" + fileNameFull);

					String fileNameExtension = fileNameFull.substring(fileNameFull.lastIndexOf(".") + 1);
					System.out.println("上传文件的扩展名是====" + fileNameExtension);
					if (fileNameExtension.equals("docx")) {
						fileNameExtension = "doc";
						System.out.println("上传文件的扩展名是" + fileNameExtension);
					}
					if (fileNameExtension.equals("xlsx")) {
						fileNameExtension = "xls";
						System.out.println("上传文件的扩展名是" + fileNameExtension);
					}
					if (fileNameExtension.equals("pptx")) {
						fileNameExtension = "ppt";
						System.out.println("上传文件的扩展名是" + fileNameExtension);
					}

					filePath = dirPath + "/" + ToolDate.getCurrentTimestamp() + "." + fileNameExtension;
					System.out.println(filePath);
					// 获取item中上传文件的输入流
					InputStream in = fileItem.getInputStream();
					// 创建一个文件输出流
					FileOutputStream out = new FileOutputStream(filePath);
					// 创建一个缓存区
					byte buffer[] = new byte[1024];
					// 建立一个标志判断输入流中的数据是否已经读完
					int len = 0;
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
			}
		} catch (FileUploadBase.FileSizeLimitExceededException e) {
			e.printStackTrace();
			// request.setAttribute("message", "单个文件超出最大值");
			// request.getRequestDispatcher("/message.jsp").forward(request,
			// response);
			map.put("message", "单个文件超出最大值");
			return map;
		} catch (FileUploadBase.SizeLimitExceededException e) {
			e.printStackTrace();
			// request.setAttribute("message", "上传文件的总大小超出最大值");
			// request.getRequestDispatcher("/message.jsp").forward(request,
			// response);
			map.put("message", "上传文件的总大小超出最大值");
			return map;
		} catch (Exception e) {
			message = "文件上传失败";
			e.printStackTrace();
		}
		// request.setAttribute("message", message);
		// request.getRequestDispatcher("/message.jsp").forward(request,
		// response);
		map.put("message", message);
		return map;
		// System.out.println;
	}

}
