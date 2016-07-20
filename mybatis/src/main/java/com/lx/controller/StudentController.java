package com.lx.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.model.Student;
import com.lx.serviceimpl.StudentServiceImpl;

@Controller
@RequestMapping("/qq")
public class StudentController {

	@Autowired
	StudentServiceImpl studentServiceImpl;

	@RequestMapping("/bb")
	public String showStudent(int stuId, HttpServletRequest request) {
		System.out.println("==StudentServiceImpl======");

		Student student = studentServiceImpl.getStudentById(stuId);

		request.setAttribute("student", student);

		return "showStu";

	}

}
