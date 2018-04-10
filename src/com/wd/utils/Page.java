package com.wd.utils;

public class Page {
	
	private long everPage;//每页显示多少
	private long totalSize; //总共条数
	private long pageSize;//总共页面
	private long currentPage;//当前页数
	
	
	public long getEverPage() {
		return everPage;
	}
	public void setEverPage(long everPage) {
		this.everPage = everPage;
	}
	public long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}
	public long getPageSize() {
		return pageSize;
	}
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}
	public long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}
	@Override
	public String toString() {
		return "Page [totalSize=" + totalSize + ", pageSize=" + pageSize
				+ ", currentPage=" + currentPage + "]";
	}
	
	
}
