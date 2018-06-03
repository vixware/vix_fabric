package com.vix.oa.adminMg.typeSettings.entity;


import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassName: VotingType
 * @Description: 投票结果查看类型
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-3-11 下午3:00:11
 */
public class VoteType extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 是否启用 */
	private String disabled;
	
	
	public VoteType(){}

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
	
	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}


}
