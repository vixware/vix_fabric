package com.vix.common.security.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 承租户默认的跳转页面配置
 * @ClassName: ConfigUrlAdd
 * @Description: 
 * @author wangmingchen
 * @date 2014年8月31日 上午11:18:09
 */
public class ConfigUrlAdd extends BaseEntity {

	/**
	 * Y
	 * N
	 */
	private String isRedirect;
	
	/**
	 * 链接地址
	 */
	private String url;

	public String getIsRedirect() {
		return isRedirect;
	}

	public void setIsRedirect(String isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
