package com.qmd.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownloadController
 */
public class FileDownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileDownloadController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		// 得到下载的文件名
		String fileName = request.getParameter("filename");
		// fileName=new String(fileName.getBytes("ISO8859_1"),"UTF-8");
		System.out.println(fileName);
		// 获取文件的路径
		String filePath = "G:/Users";
		File file = new File(filePath);
		if (!file.exists()) {
			request.setAttribute("message", "资源不存在");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		// 设置响应头
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		// 读取要下载的文件 保存到文件输入流
		FileInputStream in = new FileInputStream(filePath + "/" + fileName);
		System.out.println(filePath + "/" + fileName);
		// 创建输出流
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
