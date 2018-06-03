package com.vix.oa.knowledgeManagement.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassName: PrivateCabinetCategory
 * @Description: 个人文件柜分类 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-6-25 下午1:43:09
 */
public class PrivateCabinetCategory extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 子分类集合 */
	private Set<PrivateCabinetCategory> privateCabinetCategorys = new HashSet<PrivateCabinetCategory>();
	/**
	 * 个人文件柜
	 */
	private Set<PrivateCabinet> privateCabinet = new HashSet<PrivateCabinet>();
	/** 父分类 */
	private PrivateCabinetCategory privateCabinetCategoryss;

	public PrivateCabinetCategory() {
	}

	public Set<PrivateCabinetCategory> getPrivateCabinetCategorys() {
		return privateCabinetCategorys;
	}

	public void setPrivateCabinetCategorys(
			Set<PrivateCabinetCategory> privateCabinetCategorys) {
		this.privateCabinetCategorys = privateCabinetCategorys;
	}

	public Set<PrivateCabinet> getPrivateCabinet() {
		return privateCabinet;
	}

	public void setPrivateCabinet(Set<PrivateCabinet> privateCabinet) {
		this.privateCabinet = privateCabinet;
	}

	public PrivateCabinetCategory getPrivateCabinetCategoryss() {
		return privateCabinetCategoryss;
	}

	public void setPrivateCabinetCategoryss(
			PrivateCabinetCategory privateCabinetCategoryss) {
		this.privateCabinetCategoryss = privateCabinetCategoryss;
	}



}
