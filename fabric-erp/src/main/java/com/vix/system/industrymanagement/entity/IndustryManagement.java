package com.vix.system.industrymanagement.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * com.vix.system.industrymanagement.entity.Industry
 * 
 * @author zhanghaibing
 * 
 * @date 2014-6-12
 */
public class IndustryManagement extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 英文名称
	 */
	private String englishName;
	/**
	 * 所属行业
	 */
	private String belongsIndustry;
	/**
	 * VIX代码
	 */
	private String vixCode;
	/**
	 * 行业内码
	 */
	private String innerCode;
	
	/**
	 * 行业模块
	 */
	private Set<IndustryManagementModule> industryManagementModules;
	
	
	
	
	
	 /** 权限菜单集合 */
	//@Deprecated
	//private Set<Authority> authoritys = new HashSet<Authority>(0);

	public IndustryManagement() {
	}

	public IndustryManagement(String id) {
		super();
		setId(id);
	}
	
	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getBelongsIndustry() {
		return belongsIndustry;
	}

	public void setBelongsIndustry(String belongsIndustry) {
		this.belongsIndustry = belongsIndustry;
	}

	public String getVixCode() {
		return vixCode;
	}

	public void setVixCode(String vixCode) {
		this.vixCode = vixCode;
	}

	public String getInnerCode() {
		return innerCode;
	}

	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}

	public Set<IndustryManagementModule> getIndustryManagementModules() {
		return industryManagementModules;
	}

	public void setIndustryManagementModules(
			Set<IndustryManagementModule> industryManagementModules) {
		this.industryManagementModules = industryManagementModules;
	}

}