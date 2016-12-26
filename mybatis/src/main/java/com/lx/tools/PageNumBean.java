package com.lx.tools;

import java.util.ArrayList;
import java.util.List;

public class PageNumBean {

	private Integer pageNow; // 当前页数
	private Integer pageSize; // 每页显示记录的条数
	private Integer totalCount; // 总记录条数
	private Integer totalPageCount; // 总页数

	private Integer beginRecordIndex; // 当前页开始显示的记录数 从0开始

	private Integer perPageNumCount; // 每页显示的页码数

	private Integer upPageNum; // 上一页页码

	private Integer downPageNum; // 下一页页码

	private Integer firstPageNum; // 首页

	private Integer endPageNum; // 尾页

	private List pages; // 需要显示连续的页码

	public PageNumBean(int pageNow, int totalCount, int pageSize, int perPageNumCount) {
		this.pageNow = new Integer(pageNow);// 设置当前页码
		this.pageSize = new Integer(pageSize);// 每页显示记录数
		this.totalCount = new Integer(totalCount); // 设置总的记录数
		this.perPageNumCount = new Integer(perPageNumCount);// 每页显示的页码数
		setValue();
	}

	public final void setValue() {
		int _page = -1;
		int _perPageRecordCount = -1;
		int _recordCount = -1;
		int _pageCount = -1;
		int _pageNumBegin = -1;
		int _beginIndex = -1;
		int _upPageNum = -1;
		int _downPageNum = -1;
		int _perPageNumCount = -1;
		int _firstPageNum = -1;
		int _endPageNum = -1;

		List _pages = new ArrayList();
		_page = this.pageNow.intValue();
		_perPageRecordCount = this.pageSize.intValue();
		_recordCount = this.totalCount.intValue();
		_perPageNumCount = this.perPageNumCount.intValue();
		
		 // 计算出总的页数
		_pageCount = _recordCount / _perPageRecordCount + (_recordCount % _perPageRecordCount == 0 ? 0 : 1);
		
		
		_pageNumBegin = ((_page - 1) / _perPageNumCount) * _perPageNumCount + 1;

		for (int i = 0; i < _perPageNumCount && _pageNumBegin <= _pageCount; i++) {
			String pageNum = String.valueOf(_pageNumBegin);
			_pages.add(pageNum);
			_pageNumBegin++;
		}

		_beginIndex = (_page - 1) * _perPageRecordCount;

		if (_pageCount != -1 && _page != 1) {
			_firstPageNum = 1;
		}

		if (_page > 1) {
			_upPageNum = _page - 1;
		}

		if (_page + 1 <= _pageCount) {
			_downPageNum = _page + 1;
		}

		if (_pageCount != -1) {
			this.totalPageCount = new Integer(_pageCount);
		}

		if (_beginIndex != -1) {
			this.beginRecordIndex = new Integer(_beginIndex);
		}

		if (_upPageNum != -1) {
			this.upPageNum = new Integer(_upPageNum);
		}

		if (_downPageNum != -1) {
			this.downPageNum = new Integer(_downPageNum);
		}

		if (_firstPageNum != -1) {
			this.firstPageNum = new Integer(_firstPageNum);
		}

		if (_pageCount != -1 && _page != _pageCount) {
			this.endPageNum = new Integer(_pageCount);
		}

		this.pages = _pages;
	}

	
	//get & set
	public Integer getPageNow() {
		return pageNow;
	}

	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(Integer totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public Integer getBeginRecordIndex() {
		return beginRecordIndex;
	}

	public void setBeginRecordIndex(Integer beginRecordIndex) {
		this.beginRecordIndex = beginRecordIndex;
	}

	public Integer getPerPageNumCount() {
		return perPageNumCount;
	}

	public void setPerPageNumCount(Integer perPageNumCount) {
		this.perPageNumCount = perPageNumCount;
	}

	public Integer getUpPageNum() {
		return upPageNum;
	}

	public void setUpPageNum(Integer upPageNum) {
		this.upPageNum = upPageNum;
	}

	public Integer getDownPageNum() {
		return downPageNum;
	}

	public void setDownPageNum(Integer downPageNum) {
		this.downPageNum = downPageNum;
	}

	public Integer getFirstPageNum() {
		return firstPageNum;
	}

	public void setFirstPageNum(Integer firstPageNum) {
		this.firstPageNum = firstPageNum;
	}

	public Integer getEndPageNum() {
		return endPageNum;
	}

	public void setEndPageNum(Integer endPageNum) {
		this.endPageNum = endPageNum;
	}

	public List getPages() {
		return pages;
	}

	public void setPages(List pages) {
		this.pages = pages;
	}

}