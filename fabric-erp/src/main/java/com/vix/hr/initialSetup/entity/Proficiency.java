package com.vix.hr.initialSetup.entity;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.job.entity.HrTitleSys;

/**
 * 
 * @ClassName: Proficiency
 * @Description: 熟练程度
 * @author bobchen
 * @date 2015年11月18日 下午2:33:34
 *
 */
public class Proficiency extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 是否启用 */
	public Integer disabled;

	/** 职称体系 */
	private Set<HrTitleSys> hrTitleSys = new HashSet<HrTitleSys>();

	public Proficiency() {
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	public Set<HrTitleSys> getHrTitleSys() {
		return hrTitleSys;
	}

	public void setHrTitleSys(Set<HrTitleSys> hrTitleSys) {
		this.hrTitleSys = hrTitleSys;
	}

}
