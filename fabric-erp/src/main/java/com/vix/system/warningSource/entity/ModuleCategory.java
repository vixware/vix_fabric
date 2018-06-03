/**
 * 
 */
package com.vix.system.warningSource.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 模块分类
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-20
 */
public class ModuleCategory extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 分类编码 */
	private String categoryCode;
	/** 分类名称 */
	private String categoryName;
	/** 分类描述 */
	private String categoryDescription;
	/** 预警分类 */
	private Set<WarningType> warningTypes = new HashSet<WarningType>();

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public Set<WarningType> getWarningTypes() {
		return warningTypes;
	}

	public void setWarningTypes(Set<WarningType> warningTypes) {
		this.warningTypes = warningTypes;
	}

}
