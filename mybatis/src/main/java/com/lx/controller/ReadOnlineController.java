package com.lx.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lx.tools.ToolDocConverter;
import com.lx.tools.ToolFileTransfer;

@Controller
@RequestMapping("/ReadOnlineController")
public class ReadOnlineController {

	@RequestMapping(value = "/readOnline", method = RequestMethod.POST)
	protected void readOnline(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取预览文件的url
		String fileUrl = request.getParameter("filename");
		String dirPath = "E:/swf哈哈哈/"; // 转移地址 "E:/swf哈哈哈/"

		File file = new File(dirPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		System.out.println("=========fileUrl=======" + fileUrl);

		if (ToolFileTransfer.transfer(fileUrl, dirPath)) {
			if (ToolDocConverter.converter(ToolFileTransfer.getToFilePath())) {
				System.out.println("==============转换成功===========");
				fileUrl = ToolDocConverter.getSwfFilePath();

				// String errorMessage = ToolDocConverter.getErrorMessage();
				// System.err.println("===============错误==============" +
				// errorMessage);
				System.out.println("========swf文件目的地址=====Q======" + fileUrl);

				// "E:/lu/swf文件/1479781384.swf" fileUrl
				request.getSession().setAttribute("swfPath", fileUrl);
				response.sendRedirect("../Flexpaper2.10/documentView.jsp");
				// return "../Flexpaper2.10/documentView";
				// request.getRequestDispatcher("../Flexpaper2.10/documentView.jsp").forward(request,response);
				// TODO Auto-generated method stub
			}
		} else {

		}
	}
}
