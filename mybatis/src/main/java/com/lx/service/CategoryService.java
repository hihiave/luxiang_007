package com.lx.service;

import java.util.List;

import com.lx.model.Category;

public interface CategoryService {

	/** 添加一个文件类别
	 * @author luxiang
	 * @param categoryName 文件类别名
	 * @return boolean，true表示添加成功，false表示添加失败
	 */
	public boolean addCategory(String categoryName);
	
	/** 删除一个文件类别
	 * @author luxiang
	 * @param categoryName 文件类别名
	 * @return boolean，true表示删除成功，false表示删除失败
	 */
	public boolean delCategory(String categoryName);

	/** 检查文件类别是否存在
	 * @author luxiang
	 * @param categoryName 文件类别名
	 * @return boolean，true表示类别已经存在，false表示类别不存在
	 */
	public boolean checkCategoryIsExist(String categoryName);
	
	/** 获取所有类别
	 * @author luxiang
	 * @return 一个类别列表 Category
	 */
	public List<Category> getAllCategory();
	
}
