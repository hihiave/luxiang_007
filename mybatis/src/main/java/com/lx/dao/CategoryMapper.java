package com.lx.dao;

import java.util.List;

import com.lx.model.Category;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

	//**********用于处理一些业务逻辑的方法**********
	int deleteByCategoryName(String categoryName);
	Category selectByCategoryName(String categoryName);
	public List<Category> getAllCategory();

}