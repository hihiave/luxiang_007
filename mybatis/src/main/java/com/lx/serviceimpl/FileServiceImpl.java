package com.lx.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.dao.FileMapper;
import com.lx.model.File;
import com.lx.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	FileMapper FileMapper;
	
	@Override
	public List<File> selectFileByFileAuthor(String fileAuthor) {
		return FileMapper.selectFileByFileAuthor(fileAuthor);
	}

	@Override
	public List<File> selectFileByKeywords(String... keywords) {
		return FileMapper.selectFileByKeywords(keywords);
	}

	@Override
	public List<File> selectFileByFileCategory(String fileCategory) {
		return FileMapper.selectFileByFileCategory(fileCategory);
	}



}
