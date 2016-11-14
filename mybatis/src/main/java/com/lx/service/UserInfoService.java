package com.lx.service;

import java.util.List;

import com.lx.macrofiles.MacroEnum.KCheckType;
import com.lx.macrofiles.MacroEnum.KMessageType;
import com.lx.model.UserInfo;
import com.lx.serviceimpl.Page;

public interface UserInfoService {
	
	//**********用于处理一些业务逻辑的方法**********
	/** 管理员添加一个用户，指定其用户名即可
	 * @author luxiang
	 * @param userName 用户名
	 * @return boolean，true表示添加成功，false表示添加失败
	 */
	public boolean addUserInfo(String userName);
	
	/** 用户自己注册一个账号
	 * @author luxiang
	 * @param userName 用户名
	 * @param userPassword
	 * @return boolean，true表示注册成功，等待审核。false表示注册失败
	 */
	public boolean registerUserInfo(String userName, String userPassword);
	
	/** 通过用户名批量删除用户
	 * @author luxiang
	 * @param userNames 用户名
	 * @return boolean，true表示删除成功，false表示删除失败
	 */
	public boolean delUsersByUserName(String... userNames);
		
	/** 通过用户名查找一个用户
	 * @author luxiang
	 * @param userName 用户名
	 * @return UserInfo
	 */
	public UserInfo selectUserByUserName(String userName);
	
	/** 检查用户名是否存在
	 * @author luxiang
	 * @param userName 用户名
	 * @return boolean，true表示用户名已经存在，false表示用户名不存在
	 */
	public boolean checkUserIsExist(String userName);
	
	/** 通过用户名批量审核通过
	 * @author luxiang
	 * @param userNames 用户名
	 * @return boolean，true表示审核通过成功，false表示审核通过失败
	 */
	public boolean batchUsersPass(String... userNames);

	/** 通过用户名和密码验证用户登录
	 * @author luxiang
	 * @param userName 用户名
	 * @param userPassword
	 * @return KMessageType 消息类型，请查看枚举
	 */
	public KMessageType checkLogin(String userName, String userPassword);
	
	/** 重置用户密码为123456
	 * @author luxiang
	 * @param userName 用户名
	 * @return boolean，true表示重置成功，false表示重置失败
	 */
	public boolean resetPassword(String userName);

	/** 用户修改密码
	 * @author luxiang
	 * @param userName 用户名
	 * @param oldPwd
	 * @param newPwd
	 * @return boolean，true表示修改密码成功，false表示旧密码不对，修改密码失败
	 */
	public boolean alterPassword(String userName, String oldPwd, String newPwd);

	/** 获取待审核的用户的数量
	 * @author luxiang
	 * @return int
	 */
	public int getCountWithWaitForCheck();
	
	//**********用于一些查询的方法**********
	/** 通过审核是否通过查询用户
	 * @author luxiang
	 * @param checkType 审核类型，请查看枚举
	 * @param page 分页
	 * @param userName 按用户名的查询条件
	 * @return 一个用户对象列表 UserInfo
	 */
	public List<UserInfo> selectUserByIsPass(KCheckType checkType, Page page, String userName);
	
	// **********用于获取一些智能下拉提示**********
	/** 通过用户名获取一些智能下拉提示
	 * @author luxiang
	 * @param userName 用户名
	 * @return 用户名列表List<String>
	 */
	List<String> getUserNames(String userName);

}
