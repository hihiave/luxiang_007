package com.lx.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.dao.StudentMapper;
import com.lx.model.Student;
import com.lx.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;

	@Override
	public Student getStudentById(int stuId) {
		return studentMapper.selectByPrimaryKey(stuId);
	}

}
