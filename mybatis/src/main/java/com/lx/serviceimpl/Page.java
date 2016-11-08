package com.lx.serviceimpl;

public class Page {

	private int pageNow = 1; // 当前页数

	private int pageSize; // 每页显示记录的条数

	private int totalCount; // 总记录条数

	private int totalPageCount; // 总页数

	private int startPos; // 分页的开始位置

	public Page(int pageNow) {
		this.pageSize = 10;
		this.pageNow = pageNow;
	}

	protected void init() {
		// 计算总页数
		totalPageCount = totalCount / pageSize;
		totalPageCount = (totalCount % pageSize == 0) ? totalPageCount : totalPageCount + 1;
		// 计算分页的起始位置
		startPos = (pageNow - 1) * pageSize;
	}

	// 是否有前一页
	public boolean isHasPre() {
		return (pageNow == 1) ? false : true;
	}

	// 是否有下一页
	public boolean isHasNext() {
		return (pageNow == totalPageCount) ? false : true;
	}

	// get & set
	public int getPageNow() {
		return pageNow;
	}

	protected void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	protected void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	protected void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	protected void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

}
