package com.vix.hr.initialSetup.entity;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.job.entity.HrPostSys;

/**
 * 
 * @ClassName: Rank
 * @Description: 职级
 * @author bobchen
 * @date 2015年11月19日 上午9:56:17
 *
 */
public class Rank extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 是否启用 */
	public Integer disabled;

	/** 职务体系 */
	private Set<HrPostSys> hrPostSys = new HashSet<HrPostSys>();

	public Rank() {
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

	public Set<HrPostSys> getHrPostSys() {
		return hrPostSys;
	}

	public void setHrPostSys(Set<HrPostSys> hrPostSys) {
		this.hrPostSys = hrPostSys;
	}

}
