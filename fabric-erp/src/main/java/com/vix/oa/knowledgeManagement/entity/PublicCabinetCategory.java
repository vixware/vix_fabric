package com.vix.oa.knowledgeManagement.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassName: PublicCabinetCategory
 * @Description: 公共文件柜分类  
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-6-27 上午10:43:26
 */
public class PublicCabinetCategory extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 子分类集合 */
	private Set<PublicCabinetCategory> publicCabinetCategorys = new HashSet<PublicCabinetCategory>();
	/**
	 * 公共文件柜
	 */
	private Set<PublicCabinet> publicCabinet = new HashSet<PublicCabinet>();
	/** 父分类 */
	private PublicCabinetCategory publicCabinetCategoryss;

	public PublicCabinetCategory() {
	}

	public Set<PublicCabinetCategory> getPublicCabinetCategorys() {
		return publicCabinetCategorys;
	}

	public void setPublicCabinetCategorys(
			Set<PublicCabinetCategory> publicCabinetCategorys) {
		this.publicCabinetCategorys = publicCabinetCategorys;
	}

	public Set<PublicCabinet> getPublicCabinet() {
		return publicCabinet;
	}

	public void setPublicCabinet(Set<PublicCabinet> publicCabinet) {
		this.publicCabinet = publicCabinet;
	}

	public PublicCabinetCategory getPublicCabinetCategoryss() {
		return publicCabinetCategoryss;
	}

	public void setPublicCabinetCategoryss(
			PublicCabinetCategory publicCabinetCategoryss) {
		this.publicCabinetCategoryss = publicCabinetCategoryss;
	}

}
