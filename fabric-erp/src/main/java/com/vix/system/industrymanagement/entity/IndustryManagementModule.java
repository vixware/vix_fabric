package com.vix.system.industrymanagement.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.orginialMeta.entity.OrginialAuthority;
import com.vix.common.orginialMeta.entity.OrginialBaseMetaData;
import com.vix.common.security.entity.Module;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.StrUtils;

/**
 * 行业 模块
 * @author shadow
 *
 */
public class IndustryManagementModule extends BaseEntity {

	/** 行业 */
	private IndustryManagement industryManagement;
	
	/** 模块 */
	private Module module;
	
	/** 行业 模块的权限菜单集合 */
	private Set<OrginialAuthority> authoritys = new HashSet<OrginialAuthority>(0);

	/**
	 * 元数据
	 */
	private Set<OrginialBaseMetaData> orginialBaseMetaDatas = new HashSet<OrginialBaseMetaData>(0);
	
	//非持久化属性
	private String immName;

	
	public IndustryManagement getIndustryManagement() {
		return industryManagement;
	}

	public void setIndustryManagement(IndustryManagement industryManagement) {
		this.industryManagement = industryManagement;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Set<OrginialAuthority> getAuthoritys() {
		return authoritys;
	}

	public void setAuthoritys(Set<OrginialAuthority> authoritys) {
		this.authoritys = authoritys;
	}

	public Set<OrginialBaseMetaData> getOrginialBaseMetaDatas() {
		return orginialBaseMetaDatas;
	}

	public void setOrginialBaseMetaDatas(
			Set<OrginialBaseMetaData> orginialBaseMetaDatas) {
		this.orginialBaseMetaDatas = orginialBaseMetaDatas;
	}

	public String getImmName() {
		if(StrUtils.isEmpty(immName)){
			String tmpName = null;
			if(null != industryManagement){
				tmpName = industryManagement.getName();
				
				if(null != module){
					tmpName += "("+module.getName()+")";
				}
			}
			return tmpName;
		}
		return immName;
	}

	public void setImmName(String immName) {
		this.immName = immName;
	}

}
