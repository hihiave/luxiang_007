package com.lx.service;

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

}
