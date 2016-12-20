package com.lx.tools;

public class Page {

	private int pageNow = 1; // 当前页数

	private int pageSize; // 每页显示记录的条数

	private int totalCount; // 总记录条数

	private int totalPageCount; // 总页数

	private int startPos; // 分页的开始位置

	private int endPos; // 分页的结束位置

	public Page(int pageNow) {
		this.pageSize = 10;
		this.pageNow = pageNow;
	}

	public Page(int pageNow, int pageSize) {
		this.pageSize = pageSize;
		this.pageNow = pageNow;
	}

	public void init() {
		// 计算总页数
		totalPageCount = totalCount / pageSize;
		totalPageCount = (totalCount % pageSize == 0) ? totalPageCount : totalPageCount + 1;

		startPos = (pageNow - 1) * pageSize; // 计算分页的起始位置
		endPos = Math.min(pageNow * pageSize, totalCount); // 计算分页的结束位置
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
	public int getStartPos() {
		return startPos;
	}

	public int getEndPos() {
		return endPos;
	}

	public int getPageNow() {
		return pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}
}
