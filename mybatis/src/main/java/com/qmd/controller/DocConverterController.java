package com.qmd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qmd.tools.DocConverter;
import com.qmd.tools.FileTransfer;

/**
 * Servlet implementation class DocConverterController
 */
public class DocConverterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DocConverterController() {
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

		String temp = "G:/Users/qmd/";
		String fileName = request.getParameter("filename");
		String from = temp + fileName;
		String savePath = "F:/SWFTools";

		System.out.println("+++++======" + from);

		FileTransfer fileTransfer = new FileTransfer(savePath, from);
		fileTransfer.transfer();// 将文件移动到swftools文件夹

		System.out.println("//////////" + fileTransfer.getSavePath());

		DocConverter docConverter = new DocConverter(fileTransfer.getSavePath());
		docConverter.convert();// 生成swf文件

		System.out.println("nice++++" + docConverter.getSwfFilePath());

		String savePath2 = "F:/ProgramFiles/Apache-Software-Foundation/Tomcat8.0/webapps/swf文件";
		FileTransfer fileTransfer2 = new FileTransfer(savePath2, docConverter.getSwfFilePath());
		fileTransfer2.transfer();
		request.getSession().setAttribute("swfPath", fileTransfer2.getSavePath());
		response.sendRedirect("Flexpaper2.10/documentView.jsp");
		// request.getRequestDispatcher("/Flexpaper2.10/documentView.jsp").forward(request,response);
		// TODO Auto-generated method stub
	}

}
