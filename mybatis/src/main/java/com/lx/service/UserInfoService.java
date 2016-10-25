package com.lx.service;

import java.util.List;

import com.lx.macrofiles.MacroEnum.KMessageType;
import com.lx.model.UserInfo;

public interface UserInfoService {
	
	/** 管理员添加一个用户，指定其用户名即可
	 * @author luxiang
	 * @param userName
	 * @return boolean，true表示添加成功，false表示添加失败
	 */
	public boolean addUserInfo(String userName);
	
	/** 用户自己注册一个账号
	 * @author luxiang
	 * @param userName
	 * @param userPassword
	 * @return boolean，true表示注册成功，等待审核。false表示注册失败
	 */
	public boolean registerUserInfo(String userName, String userPassword);
	
	/** 通过用户名查找一个用户
	 * @author luxiang
	 * @param userName
	 * @return UserInfo
	 */
	public UserInfo selectUserByUserName(String userName);
	
	/** 通过用户名批量审核通过
	 * @author luxiang
	 * @param userNames
	 * @return boolean，true表示审核通过成功，false表示审核通过失败
	 */
	public boolean updateUsersCheck(String... userNames);
	
	/** 通过用户名和密码验证用户登录
	 * @author luxiang
	 * @param userName
	 * @param userPassword
	 * @return KMessageType 消息类型，请查看枚举
	 */
	public KMessageType checkLogin(String userName, String userPassword);
	
	/** 通过审核是否通过查询用户
	 * @author luxiang
	 * @param checkType
	 * @return 一个用户对象列表 UserInfo
	 */
	public List<UserInfo> selectUserByIsPass(int checkType);
	
	/** 获取未通过审核的用户的数量
	 * @author luxiang
	 * @return int
	 */
	public int getCountWithNotPass();
	
	/** 通过用户名模糊查询所有用户，比如，查“三”，找到“李三”，“张三”
	 * @author luxiang
	 * @param userName
	 * @return 一个用户对象列表 UserInfo
	 */
	public List<UserInfo> selectAllUserInfoByLikeUserName(String userName);

	/** 通过用户名删除一个用户
	 * @author luxiang
	 * @param userName
	 * @return boolean，true表示删除成功，false表示删除失败
	 */
	public boolean delByUserName(String userName);

	/** 重置用户密码为123456
	 * @author luxiang
	 * @param userName
	 * @return boolean，true表示重置成功，false表示重置失败
	 */
	public boolean resetPassword(String userName);

	/** 用户修改密码
	 * @author luxiang
	 * @param userName
	 * @param oldPwd
	 * @param newPwd
	 * @return boolean，true表示修改密码成功，false表示旧密码不对，修改密码失败
	 */
	public boolean alterPassword(String userName, String oldPwd, String newPwd);
	
	/** 检查用户名是否存在
	 * @author luxiang
	 * @param userName
	 * @return boolean，true表示用户名已经存在，false表示用户名不存在
	 */
	public boolean checkUserIsExist(String userName);
	
	/** 通过用户名获取一些智能下拉提示
	 * @author luxiang
	 * @param userName
	 * @return 用户名列表List<String>
	 */
	List<String> getUserNames(String userName);

}
