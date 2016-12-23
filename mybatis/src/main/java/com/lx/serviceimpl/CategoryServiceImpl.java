package com.lx.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.dao.CategoryMapper;
import com.lx.macrofiles.MacroConstant;
import com.lx.model.Category;
import com.lx.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;

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

		HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
		ArrayList<String> strings = new ArrayList<>();
		Category category;
		for (int i = 0; i < categories.size(); i++) {
			category = categories.get(i);
			if (category.getCategoryBelongTo().equals(MacroConstant.level0_category)) {
				strings.add(category.getCategoryName());
			}
		}

		hashMap.put(MacroConstant.level0_category, strings);

		// strings A B
		
		
		ArrayList<String> strings1 = new ArrayList<>();
		for (int i = 0; i < categories.size(); i++) {
			category = categories.get(i);
			for (int j = 0; j < strings.size(); j++) {
				if (category.getCategoryBelongTo().equals(strings.get(j))) {
					strings1.add(category.getCategoryName());
					continue;
				}
			}
		}

		return null;

	}

	@Override
	public boolean checkCategoryIsExist(Integer categoryUserId, String categoryName) {
		if (categoryMapper.selectByUserIdAndCategoryName(categoryUserId, categoryName) != null) {
			return true;
		}
		return false;
	}
}
