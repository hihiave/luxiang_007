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

		//
		// HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
		// ArrayList<String> strings = new ArrayList<>();
		// Category category;
		// for (int i = 0; i < categories.size(); i++) {
		// category = categories.get(i);
		// if
		// (category.getCategoryBelongTo().equals(MacroConstant.level0_category))
		// {
		// strings.add(category.getCategoryName());
		// }
		// }
		// // 1482422400 ----> A B C
		// hashMap.put(MacroConstant.level0_category, strings);
		//
		// ArrayList<String> list;
		// for (int i = 0; i < strings.size(); i++) {
		// list = new ArrayList<>();
		// for (int j = 0; j < categories.size(); j++) {
		// category = categories.get(j);
		// if (category.getCategoryBelongTo().equals(strings.get(i))) {
		// list.add(category.getCategoryName());
		// }
		// }
		// // A ----> A1 A2
		// // B ----> B1
		// // C ----> []
		// hashMap.put(strings.get(i), list);
		// }

	}

	@Override
	public boolean alterCategory(Integer categoryUserId, String UserName, String oldCategoryName,
			String newCategoryName) {
		if (categoryMapper.updateCategoryName(categoryUserId, oldCategoryName, newCategoryName) != 1) {
			return false;
		}
		if (categoryMapper.updateCategoryBelongTo(categoryUserId, oldCategoryName, newCategoryName) < 0) {
			return false;
		}
		if (fileInfoService.alterFileCategroy(UserName, oldCategoryName, newCategoryName)) {
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
