package com.lx.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.tool.DocConverter;
import com.lx.tool.FileTransfer;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ReadOnlineController")
public class ReadOnlineController {

	@RequestMapping(value = "/readOnline",method = RequestMethod.POST)
	protected void readOnline(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String from = request.getParameter("filename");
//        String from = "C:/Users/1479264893.doc";
		String savePath = "C:/temp";

		System.out.println("+++++======" + from);

		FileTransfer fileTransfer = new FileTransfer(savePath, from);
		fileTransfer.transfer();// 将文件移动到swftools文件夹

		System.out.println("//////////" + fileTransfer.getSavePath());

		DocConverter docConverter = new DocConverter(fileTransfer.getSavePath());
		docConverter.convert();// 生成swf文件

		System.out.println("nice++++" + docConverter.getSwfFilePath());

		String savePath2 = "C:/程序/Knowledge/luxiang_007/mybatis/target/mybatis-0.0.1-SNAPSHOT/swf";
		FileTransfer fileTransfer2 = new FileTransfer(savePath2, docConverter.getSwfFilePath());
		fileTransfer2.transfer();
		request.getSession().setAttribute("swfPath", fileTransfer2.getSavePath());
		response.sendRedirect("../Flexpaper2.10/documentView.jsp");
//        return "../Flexpaper2.10/documentView";
//        request.getRequestDispatcher("../Flexpaper2.10/documentView.jsp").forward(request,response);
		// TODO Auto-generated method stub

	}



}
