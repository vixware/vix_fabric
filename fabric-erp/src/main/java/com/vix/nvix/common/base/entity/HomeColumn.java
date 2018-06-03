package com.vix.nvix.common.base.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.nvix.common.base.entity.HomeColumn
 *
 * @date 2018年1月5日
 */
public class HomeColumn extends BaseEntity {

	private static final long serialVersionUID = -1l;

	/** 栏目类型编码 */
	private String typeCode;
	/** 栏目顺序 */
	private Long orderBy;
	/** 启用时间 */
	private Date enableDate;
	/** 栏目明细 */
	private Set<HomeColumnDetail> subHomeColumnDetails = new HashSet<HomeColumnDetail>();

	public HomeColumn() {
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	@Override
	public Long getOrderBy() {
		return orderBy;
	}

	@Override
	public void setOrderBy(Long orderBy) {
		this.orderBy = orderBy;
	}

	public Date getEnableDate() {
		return enableDate;
	}

	public String getEnableDateShow() {
		if (null != enableDate) {
			return DateUtil.format(enableDate, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}

	public String getEnableDateStr() {
		if (null != enableDate) {
			return DateUtil.format(enableDate, "yyyy-MM-dd");
		}
		return "";
	}

	public void setEnableDate(Date enableDate) {
		this.enableDate = enableDate;
	}

	/**
	 * @return the subHomeColumnDetails
	 */
	public Set<HomeColumnDetail> getSubHomeColumnDetails() {
		return subHomeColumnDetails;
	}

	/**
	 * @param subHomeColumnDetails
	 *            the subHomeColumnDetails to set
	 */
	public void setSubHomeColumnDetails(Set<HomeColumnDetail> subHomeColumnDetails) {
		this.subHomeColumnDetails = subHomeColumnDetails;
	}

}