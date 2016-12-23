package com.lx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lx.model.Category;

public interface CategoryMapper {
	int deleteByPrimaryKey(Integer categoryId);

	int insert(Category record);

	int insertSelective(Category record);

	Category selectByPrimaryKey(Integer categoryId);

	int updateByPrimaryKeySelective(Category record);

	int updateByPrimaryKey(Category record);

	// **********用于处理一些业务逻辑的方法**********
	int deleteByCategoryName(@Param("categoryUserId") Integer categoryUserId,
			@Param("categoryName") String categoryName);
	public List<Category> getAllCategory(Integer categoryUserId);
	int updateCategoryName(@Param("categoryUserId") Integer categoryUserId,
			@Param("oldCategoryName") String oldCategoryName, @Param("newCategoryName") String newCategoryName);
	int updateCategoryBelongTo(@Param("categoryUserId") Integer categoryUserId,
			@Param("oldCategoryName") String oldCategoryName, @Param("newCategoryName") String newCategoryName);

	Category selectByUserIdAndCategoryName(@Param("categoryUserId") Integer categoryUserId,
			@Param("categoryName") String categoryName);
}