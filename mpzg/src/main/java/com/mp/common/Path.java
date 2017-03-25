package com.mp.common;

import java.io.Serializable;
/**
 *  链接路径对象类
 * @author Administrator
 *
 */
public class Path implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   private String packageName;
   private String viewName;
   private String viewTitle;
   private String result;
   private String smenu;
   private String mmenu;
   private String mmmenu;
   
 
public String getSmenu() {
	return smenu;
}
public void setSmenu(String smenu) {
	this.smenu = smenu;
}
public String getMmenu() {
	return mmenu;
}
public void setMmenu(String mmenu) {
	this.mmenu = mmenu;
}
public String getMmmenu() {
	return mmmenu;
}
public void setMmmenu(String mmmenu) {
	this.mmmenu = mmmenu;
}
public String getResult() {
	return result;
}
public void setResult(String result) {
	this.result = result;
}
//  private String 
public String getPackageName() {
	return packageName;
}
public void setPackageName(String packageName) {
	this.packageName = packageName;
}
public String getViewName() {
	return viewName;
}
public void setViewName(String viewName) {
	this.viewName = viewName;
}
public String getViewTitle() {
	return viewTitle;
}
public void setViewTitle(String viewTitle) {
	this.viewTitle = viewTitle;
}
@Override
public String toString() {
	return "Path [packageName=" + packageName + ", viewName=" + viewName
			+ ", viewTitle=" + viewTitle + ", result=" + result + ", smenu="
			+ smenu + ", mmenu=" + mmenu + ", mmmenu=" + mmmenu + "]";
}

   
}
