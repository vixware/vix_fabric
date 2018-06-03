/**   
* @Title: ContractCatalog.java 
* @Package com.vix.contract.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenzhengwen
* @author w_a_533@163.com   
* @date 2013-6-17 下午2:26:38  
*/
package com.vix.contract.config.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: ContractCatalog
 * @Description:合同分类 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-6-17 下午2:26:38
 */
public class ContractCatalog extends BaseEntity {

	private static final long serialVersionUID = 2399610969602841141L;
	
	/**
	 * 分类编码
	 *//*
	private String code;
	*//**
	 * 名称
	 *//*
	private String name;
	*//**
	 * 描述
	 *//*
	private String description;*/
	/**
	 * 父类编码
	 */
	private String parentCode;
	/**
	 * 编码规则
	 */
	private String codeRule;
	
	/** 子分类集合 */
	private Set<ContractCatalog> subCategorys = new HashSet<ContractCatalog>();
	/** 父分类 */
	private ContractCatalog parentCategory;
	
	
	
	public ContractCatalog() {
		super();
	}
	
	public ContractCatalog(String id) {
		super();
		setId(id);
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getCodeRule() {
		return codeRule;
	}

	public void setCodeRule(String codeRule) {
		this.codeRule = codeRule;
	}

	public Set<ContractCatalog> getSubCategorys() {
		return subCategorys;
	}

	public void setSubCategorys(Set<ContractCatalog> subCategorys) {
		this.subCategorys = subCategorys;
	}

	public ContractCatalog getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(ContractCatalog parentCategory) {
		this.parentCategory = parentCategory;
	}

	
}
