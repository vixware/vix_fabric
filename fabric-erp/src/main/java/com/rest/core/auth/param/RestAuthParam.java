package com.rest.core.auth.param;

import java.io.Serializable;

import com.rest.core.constant.RestAuthConstant;

/**
 * @ClassName: RestAuthParam
 * @Description: 服务认证接收参数
 * @author wangmingchen
 * @date 2015年2月23日 下午1:57:18
 */
public class RestAuthParam implements Serializable {

	private static final long serialVersionUID = -1527192763001574514L;
	/** 加密后的账号 */
	private String userNameEnc;
	/** 加密后的密码 */
	private String pwdEnc;
	/** 加密后的承租户标识 */
	private String tenantIdEnc;
	
	
	/**
	 * 暂时不使用
	 * 认证类型：RestAuthConstant.SERVICE_AUTH_TYPE_
	 * 无状态的认证
	 * 有状态
	 */
	private String authType = RestAuthConstant.SERVICE_AUTH_TYPE_STATELESS;
	
	public RestAuthParam() {
		super();
	}
	
	public RestAuthParam(String userNameEnc, String pwdEnc, String tenantIdEnc) {
		super();
		this.userNameEnc = userNameEnc;
		this.pwdEnc = pwdEnc;
		this.tenantIdEnc = tenantIdEnc;
	}

	public String getUserNameEnc() {
		return userNameEnc;
	}
	public void setUserNameEnc(String userNameEnc) {
		this.userNameEnc = userNameEnc;
	}
	public String getPwdEnc() {
		return pwdEnc;
	}
	public void setPwdEnc(String pwdEnc) {
		this.pwdEnc = pwdEnc;
	}
	public String getTenantIdEnc() {
		return tenantIdEnc;
	}
	public void setTenantIdEnc(String tenantIdEnc) {
		this.tenantIdEnc = tenantIdEnc;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}
	
}
