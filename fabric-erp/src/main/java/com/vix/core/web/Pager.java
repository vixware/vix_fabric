package com.vix.core.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vix.core.utils.JSonUtils;

/**
 * 分页对象，页码从1开始
 * @author arron
 *
 */
@SuppressWarnings("rawtypes")
public class Pager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final int pager_list_size = 7;//下方pager显示页签列表的数量，最好是单数
	private int pagerLeftStart;
	private int pagerRightEnd;
	
	public static final int DEF_COUNT = 10;
	
	private String orderField;
	
	private String orderBy;
	
	private List resultList;

	//总数据条数
	protected int totalCount = 0;
	//每页数据容量
	protected int pageSize = 12;
	//当前页条数
	protected int currentPageSize;
	//页码
	protected int pageNo = 1;
	//总页数
	protected int pageCount;
	
	public String genPagerInfoJsonStr(){
		Map<String,Integer> infoMap = new HashMap<String,Integer>();
		infoMap.put("isFirstPage", this.isFirstPage()?1:0);
		infoMap.put("isLastPage", this.isLastPage()?1:0);
		infoMap.put("pageNo", this.pageNo);
		infoMap.put("pageCount", this.pageCount);
		infoMap.put("prePage", this.getPrePage());
		infoMap.put("nextPage", this.getNextPage());
		this.genPagerStartAndEnd();
		infoMap.put("pagerStart", this.pagerLeftStart);
		infoMap.put("pagerEnd", this.pagerRightEnd);
		if(this.currentPageSize>0)
		{
			int rowBeginBase = (this.pageNo - 1) * this.pageSize;
			infoMap.put("pageBeginRow", rowBeginBase + 1);
			infoMap.put("pageEndRow", rowBeginBase + this.currentPageSize);
		}
		else
		{
			infoMap.put("pageBeginRow", 0);
			infoMap.put("pageEndRow", 0);
		}
		infoMap.put("totalCount", this.totalCount);
		infoMap.put("currentPageSize", this.currentPageSize);
		infoMap.put("pageSize", this.pageSize);
		
		return JSonUtils.makeMapToJson(infoMap);
	}

	/**
	 * 检查页码 checkPageNo
	 * 
	 * @param pageNo
	 * @return if pageNo==null or pageNo<1 then return 1 else return pageNo
	 */
	public static int checkPageNumber(Integer pageNo) {
		return (pageNo == null || pageNo < 1) ? 1 : pageNo;
	}

	public Pager() {}

	/**
	 * 构造器
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页几条数据
	 * @param totalCount
	 *            总共几条数据
	 */
	public Pager(int pageNo, int pageSize, int totalCount) {
		setTotalCount(totalCount);
		setPageSize(pageSize);
		setPageNo(pageNo);
		adjustPageNo();
	}

	/**
	 * 调整页码，使不超过最大页数
	 */
	public void adjustPageNo() {
		if (pageNo == 1) {
			return;
		}
		int tp = getTotalPage();
		if (pageNo > tp) {
			pageNo = tp;
		}
	}

	/**
	 * 获得页码
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 每页几条数据
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 总共几条数据
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 总共几页
	 */
	public int getTotalPage() {
		int totalPage = totalCount / pageSize;
		if (totalPage == 0 || totalCount % pageSize != 0) {
			totalPage++;
		}
		return totalPage;
	}

	/**
	 * 是否第一页
	 */
	public boolean isFirstPage() {
		return pageNo <= 1;
	}

	/**
	 * 是否最后一页
	 */
	public boolean isLastPage() {
		return pageNo >= getTotalPage();
	}

	/**
	 * 下一页页码
	 */
	public int getNextPage() {
		if (isLastPage()) {
			return pageNo;
		} else {
			return pageNo + 1;
		}
	}

	/**
	 * 上一页页码
	 */
	public int getPrePage() {
		if (isFirstPage()) {
			return pageNo;
		} else {
			return pageNo - 1;
		}
	}

	/**
	 * if totalCount<0 then totalCount=0
	 * 
	 * @param 总数据条数
	 */
	public void setTotalCount(int totalCount) {
		if (totalCount < 0) {
			this.totalCount = 0;
		} else {
			this.totalCount = totalCount;
		}
		
		this.pageCount = this.totalCount / this.pageSize;
		if(this.totalCount % this.pageSize > 0)
			this.pageCount ++;
	}

	/**
	 * if pageSize< 1 then pageSize=DEF_COUNT
	 * 
	 * @param 每页数据容量
	 */
	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			this.pageSize = DEF_COUNT;
		} else {
			this.pageSize = pageSize;
		}
	}

	/**
	 * if pageNo < 1 then pageNo=1
	 * 
	 * @param 页码
	 */
	public void setPageNo(int pageNo) {
		if (pageNo < 1) {
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}
	}
	
	public int getPagerStart()
	{
		this.genPagerStartAndEnd();
		return this.pagerLeftStart;
	}
	
	public int getPagerEnd()
	{
		this.genPagerStartAndEnd();
		return this.pagerRightEnd;
	}
	
	private void genPagerStartAndEnd()
	{
		int side_size = pager_list_size / 2;
		this.pagerLeftStart = this.pageNo - side_size;
		this.pagerRightEnd = this.pageNo + side_size;
		
		if(this.pagerLeftStart<=0)
		{
			this.pagerRightEnd = this.pagerRightEnd - this.pagerLeftStart + 1;
			this.pagerLeftStart = 1;
		}
		if(this.pagerRightEnd>this.pageCount)
		{			
			if(this.pagerLeftStart>1)
			{
				this.pagerLeftStart = this.pagerLeftStart - (this.pagerRightEnd - this.pageCount);
				if(this.pagerLeftStart<=0)
					this.pagerLeftStart = 1;
			}

			this.pagerRightEnd = this.pageCount;
		}
	}

	public List getResultList() {
		if(null == resultList){
			return resultList = new ArrayList<Object>();
		}
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
		if(this.resultList!=null)
			this.currentPageSize = this.resultList.size();
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public int getPageCount()
	{
		return pageCount;
	}

	public void setPageCount(int pageCount)
	{
		this.pageCount = pageCount;
	}

	public int getCurrentPageSize()
	{
		return currentPageSize;
	}

	public void setCurrentPageSize(int currentPageSize)
	{
		this.currentPageSize = currentPageSize;
	}
}
