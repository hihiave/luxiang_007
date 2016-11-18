package com.lx.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 没用
 * Servlet implementation class ListFileController
 */
public class ListFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListFileController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String savePath = "G:/Users/qmd";// 获取文件目录
		Map<String, String> fileNameMap = new HashMap<String, String>();// 存储要下载的文件名
		listFile(new File(savePath), fileNameMap);
		request.setAttribute("fileNameMap", fileNameMap);
		request.getRequestDispatcher("/ListFile.jsp").forward(request, response);
	}

	public void listFile(File file, Map<String, String> map) {
		// 如果file是目录不是文件
		if (!file.isFile()) {
			File files[] = file.listFiles();// 列出目录file下所用的文件或文件夹
			for (File f : files) {
				listFile(f, map);// 递归，将所有的文件都加入map中
			}
		} else {
			String realName = file.getName().substring(0, file.getName().lastIndexOf("."));
			map.put(file.getName(), realName);
		}
	}

}
