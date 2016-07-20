package com.lx.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lx.model.Student;
import com.lx.serviceimpl.StudentServiceImpl;

public class TestStudent {

	ApplicationContext app = null;
	private StudentServiceImpl studentServiceImpl;

	@Before
	public void init() {

		app = new ClassPathXmlApplicationContext("lx/applicationContext.xml");
		studentServiceImpl = app.getBean(StudentServiceImpl.class);
	}

	@Test
	public void TestselectByUserId() {
		System.out.println("=========QQ===========");
		Student student = studentServiceImpl.getStudentById(145);

		System.out.println("==========" + student.getStuName());
	}
}
