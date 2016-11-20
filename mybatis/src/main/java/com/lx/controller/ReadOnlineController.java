package com.lx.controller;

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

		// "C:/Users/" 文件所在的地址 filename文件名
		String from = "C:/Users/" + request.getParameter("filename");
		String savePath = "D:/SWFTools";

		System.out.println("+++++======" + from);

		ToolFileTransfer fileTransfer = new ToolFileTransfer(savePath, from);
		fileTransfer.transfer();// 将文件移动到swftools文件夹  savePath

		System.out.println("//////////" + fileTransfer.getSavePath());

		// fileTransfer.getSavePath() 当前文件所在的地址xxxxxx.doc
		ToolDocConverter docConverter = new ToolDocConverter(fileTransfer.getSavePath());
		docConverter.convert();// 生成swf文件

		System.out.println("nice++++" + docConverter.getSwfFilePath());

		String savePath2 = "E:/swf文件";
		
		
		ToolFileTransfer fileTransfer2 = new ToolFileTransfer(savePath2, docConverter.getSwfFilePath());
		fileTransfer2.transfer();
		
		
		request.getSession().setAttribute("swfPath", fileTransfer2.getSavePath());
		response.sendRedirect("../Flexpaper2.10/documentView.jsp");
		// return "../Flexpaper2.10/documentView";
		// request.getRequestDispatcher("../Flexpaper2.10/documentView.jsp").forward(request,response);
		// TODO Auto-generated method stub

	}

}
