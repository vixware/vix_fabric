package com.vix.nvix.common.base.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.nvix.common.base.entity.HomeTemplateDetail
 *
 * @date 2018年1月19日
 */
public class HomeTemplateDetail extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/** 明细顺序 */
	private Long orderBy;
	/** 所属模板 */
	private HomeTemplate homeTemplate;

	public HomeTemplateDetail() {
	}

	@Override
	public Long getOrderBy() {
		return orderBy;
	}

	@Override
	public void setOrderBy(Long orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @return the homeTemplate
	 */
	public HomeTemplate getHomeTemplate() {
		return homeTemplate;
	}

	/**
	 * @param homeTemplate
	 *            the homeTemplate to set
	 */
	public void setHomeTemplate(HomeTemplate homeTemplate) {
		this.homeTemplate = homeTemplate;
	}
}