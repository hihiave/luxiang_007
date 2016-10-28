package com.lx.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.dao.CategoryMapper;
import com.lx.model.Category;
import com.lx.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public boolean addCategory(String categoryName) {
		Category category = new Category();
		category.setCategoryName(categoryName);
		if (categoryMapper.insertSelective(category) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delCategory(String categoryName) {
		if (categoryMapper.deleteByCategoryName(categoryName) == 1) {
			return true;
		}
		return false;
	}

}
