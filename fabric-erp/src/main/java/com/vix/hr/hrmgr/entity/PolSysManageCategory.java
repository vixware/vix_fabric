package com.vix.hr.hrmgr.entity;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassName: PolSysManageCategory
 * @Description: 政策制度分类
 * @author bobchen
 * @date 2015年12月4日 下午5:05:13
 *
 */
public class PolSysManageCategory extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 子分类集合 */
	private Set<PolSysManageCategory> polSysManageCategorys = new HashSet<PolSysManageCategory>();
	/**
	 * 政策制度
	 */
	private Set<PolSysManage> polSysManage = new HashSet<PolSysManage>();
	/** 父分类 */
	private PolSysManageCategory polSysManageCategoryss;

	public Set<PolSysManage> getPolSysManage() {
		return polSysManage;
	}

	public void setPolSysManage(Set<PolSysManage> polSysManage) {
		this.polSysManage = polSysManage;
	}

	public Set<PolSysManageCategory> getPolSysManageCategorys() {
		return polSysManageCategorys;
	}

	public void setPolSysManageCategorys(Set<PolSysManageCategory> polSysManageCategorys) {
		this.polSysManageCategorys = polSysManageCategorys;
	}

	public PolSysManageCategory getPolSysManageCategoryss() {
		return polSysManageCategoryss;
	}

	public void setPolSysManageCategoryss(PolSysManageCategory polSysManageCategoryss) {
		this.polSysManageCategoryss = polSysManageCategoryss;
	}

}
