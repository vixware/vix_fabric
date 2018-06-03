package com.vix.common.security.vo;

import java.io.Serializable;

public class ResourceVO implements Serializable {

	private String url;
	
	private String authorityCode;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}
	
}
