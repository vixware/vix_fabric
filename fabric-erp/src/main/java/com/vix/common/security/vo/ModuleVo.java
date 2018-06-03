package com.vix.common.security.vo;

import java.io.Serializable;

import com.vix.common.security.entity.Module;

public class ModuleVo extends Module implements Serializable {

	private Boolean isCheck;

	public Boolean getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Boolean isCheck) {
		this.isCheck = isCheck;
	}
	
	
}
