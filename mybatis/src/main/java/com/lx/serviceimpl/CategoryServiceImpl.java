package com.lx.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.dao.CategoryMapper;
import com.lx.model.Category;
import com.lx.service.CategoryService;
import com.lx.service.FileInfoService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private FileInfoService fileInfoService;

	@Override
	public boolean addCategory(Integer categoryUserId, String categoryName, String categoryBelongTo) {
		Category category = new Category();
		category.setCategoryUserId(categoryUserId);
		category.setCategoryName(categoryName);
		category.setCategoryBelongTo(categoryBelongTo);
		if (categoryMapper.insertSelective(category) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delCategory(Integer categoryUserId, String categoryName) {
		if (categoryMapper.deleteByCategoryName(categoryUserId, categoryName) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<Category> getAllCategory(Integer categoryUserId) {
		List<Category> categories = categoryMapper.getAllCategory(categoryUserId);
		if (!categories.isEmpty()) {
			return categories;
		}
		return null;

	}

	@Override
	public boolean alterCategory(Integer categoryUserId, String UserName, String oldCategoryName,
			String newCategoryName) {
		int i = categoryMapper.updateCategoryName(categoryUserId, oldCategoryName, newCategoryName);
		if (i != 1) {
			return false;
		}
		int j = categoryMapper.updateCategoryBelongTo(categoryUserId, oldCategoryName, newCategoryName);
		if (j < 0) {
			return false;
		}
		boolean flag = fileInfoService.alterFileCategroy(UserName, oldCategoryName, newCategoryName);
		if (!flag) {
			return false;
		}
		return true;
	}

	@Override
	public boolean checkCategoryIsExist(Integer categoryUserId, String categoryName) {
		if (categoryMapper.selectByUserIdAndCategoryName(categoryUserId, categoryName) != null) {
			return true;
		}
		return false;
	}
}
