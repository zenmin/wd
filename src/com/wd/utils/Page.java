package com.wd.utils;

public class Page {
	
	private long everPage;//ÿҳ��ʾ����
	private long totalSize; //�ܹ�����
	private long pageSize;//�ܹ�ҳ��
	private long currentPage;//��ǰҳ��
	
	
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
