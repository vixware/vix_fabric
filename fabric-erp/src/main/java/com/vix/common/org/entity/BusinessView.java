package com.vix.common.org.entity;

import java.util.Date;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 业务组织视图
 * @author Administrator
 *
 */
public class BusinessView extends BaseEntity {

	//name 使用父类
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 是否为矩阵管理结构 */
	private String isMatrixManagement;
	
	/** 业务视图类型 */
	private String bizViewType;
	
	/** 项目标识 */
	private String pmCode;
	
	/** 业务对象标识 */
	private String metaDataCode;
	
	/** 创建日期 */
	private Date bizViewCreateDate;
	
	/** 所属公司 */
	private Organization organization;
	
	
	/** 业务组织  */
	private Set<BusinessOrg> businessOrgs;


	public String getIsMatrixManagement() {
		return isMatrixManagement;
	}


	public void setIsMatrixManagement(String isMatrixManagement) {
		this.isMatrixManagement = isMatrixManagement;
	}


	public String getBizViewType() {
		return bizViewType;
	}


	public void setBizViewType(String bizViewType) {
		this.bizViewType = bizViewType;
	}


	public String getPmCode() {
		return pmCode;
	}


	public void setPmCode(String pmCode) {
		this.pmCode = pmCode;
	}


	public String getMetaDataCode() {
		return metaDataCode;
	}


	public void setMetaDataCode(String metaDataCode) {
		this.metaDataCode = metaDataCode;
	}


	public Set<BusinessOrg> getBusinessOrgs() {
		return businessOrgs;
	}


	public void setBusinessOrgs(Set<BusinessOrg> businessOrgs) {
		this.businessOrgs = businessOrgs;
	}


	public Date getBizViewCreateDate() {
		return bizViewCreateDate;
	}


	public void setBizViewCreateDate(Date bizViewCreateDate) {
		this.bizViewCreateDate = bizViewCreateDate;
	}


	public Organization getOrganization() {
		return organization;
	}


	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
}
