package com.mp.common;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;

import com.mp.common.Path;
import com.mp.common.PathUtil;

public class PathUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PathUtil.class);

	private static Path path = null;
	private static PathUtil pathUtil = new PathUtil();

	private PathUtil() {
	}

	/**
	 * 初始化Path类的各个属性值
	 * 
	 * @param params  参数数组
	 * @return Path对象
	 */
	public static Path setPathParams(String[] params) {
		path = new Path();
		for (String param : params) {
			try {
				String[] strs = StringUtil.splitAConStr(param);
				Method method = path.getClass().getDeclaredMethod(strs[0],
						java.lang.String.class);
				method.invoke(path, strs[1]);
			} catch (NoSuchMethodException e) {
				logger.info(e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}

		}

		return path;
	}
    /**
     *  根据传入的Path类，返回tiles视图的链接
     * @param path path对象实例
     * @param modelMap ModelMap对象实例
     * @return tiles视图的链接
     */
	public static String returnStr(Path path, ModelMap modelMap) {
		// 设置第一级菜单名称（用于前台的当前位置需要）
		modelMap.addAttribute("sMenu", path.getSmenu());
		// 设置第二级菜单名称（用于前台的当前位置需要）
		modelMap.addAttribute("mMenu", path.getMmenu());
		// 设置第三级菜单名称（用于前台的当前位置需要）
		modelMap.addAttribute("mmMenu", path.getMmmenu());
		
		return path.getPackageName() + "." + path.getViewName() + "."
				+ path.getViewTitle();
	}
	  public static Path getPath(String package_name, String view_name, String view_title) {
	       Path path = PathUtil.setPathParams(new String[]{
	                "PackageName:" + package_name,
	                "ViewName:" + view_name,
	                "ViewTitle:" + view_title,
	                "smenu:AcademyGK",
	                "mmenu:AcademyIntro"
	        });
	        return path;
	    }
}
