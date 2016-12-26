package com.lx.service;

import java.util.List;

import com.lx.model.Category;

public interface CategoryService {

	/** 添加一个文件类别
	 * @author luxiang
	 * @param categoryUserId 用户id
	 * @param categoryName 类别名
	 * @param categoryBelongTo 指定类别所属的上一级类别名
	 * @return boolean,true表示添加成功,false表示添加失败
	 */
	public boolean addCategory(Integer categoryUserId, String categoryName, String categoryBelongTo);
	
	/** 删除一个类别,注意:删除当前类别后,其子类别也将被删除(方法有待完善)
	 * @author luxiang
	 * @param categoryUserId 指定用户Id
	 * @param categoryName 指定删除类别的名称
	 * @return boolean,true表示删除成功,false表示删除失败
	 */
	public boolean delCategory(Integer categoryUserId, String categoryName);

	/** 获取所有类别
	 * @author luxiang
	 * @param categoryUserId 指定用户Id
	 * @return 该用户的分类.注意:若返回null表示该用户还没有分类
	 */
	public List<Category> getAllCategory(Integer categoryUserId);
	
	/** 修改类别名(方法有待完善,没有回滚)
	 * @author luxiang
	 * @param categoryUserId 指定用户Id
	 * @param UserName 指定用户名
	 * @param oldCategoryName 旧类别名
	 * @param newCategoryName 新类别名
	 * @return boolean,true表示修改成功,false表示修改失败
	 */
	public boolean alterCategory(Integer categoryUserId, String UserName, String oldCategoryName,
			String newCategoryName);
	
	/** 检查类别是否存在,注意:一个用户的所有类别名必须唯一
	 * @author luxiang
	 * @param categoryUserId 指定用户Id
	 * @param categoryName 指定删除类别的名称
	 * @return boolean,true类别存在,false表示类别不存在
	 */
	public boolean checkCategoryIsExist(Integer categoryUserId, String categoryName);

}
