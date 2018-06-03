package com.vix.oa.personaloffice.wab.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassName: PhoneNumber
 * @Description: 手机号
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-5-23 下午4:33:47
 */
public class PhoneNumber extends BaseEntity {

	private static final long serialVersionUID = 87031862813442337L;
	
	
	/** 电话薄 */
	private Wab wab;

	public PhoneNumber() {
		super();
	}

	public PhoneNumber(String id) {
		super();
		setId(id);
	}

	public Wab getWab() {
		return wab;
	}

	public void setWab(Wab wab) {
		this.wab = wab;
	}
}
