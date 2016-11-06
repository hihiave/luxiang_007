package com.lx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DocConverterController {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		String fileName = request.getParameter("filename");
		String filePath = "G:/Users";
		File file = new File(filePath + "/" + fileName);
		if (!file.exists()) {
			request.setAttribute("message", "文件不存在");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		response.setHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		FileInputStream in = new FileInputStream(filePath + "/" + fileName);
		System.out.println(filePath + "/" + fileName);
		OutputStream out = response.getOutputStream();
		byte buffer[] = new byte[1024];
		int len = 0;
		while ((len = in.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}
		in.close();
		out.close();
		// System.out.println;

	}

}
