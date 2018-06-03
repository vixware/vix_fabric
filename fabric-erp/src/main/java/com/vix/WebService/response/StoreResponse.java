package com.vix.WebService.response;

import java.io.Serializable;

/**
 * 
 * com.vix.WebService.response.StoreResponse
 *
 * @author zhanghaibing
 *
 * @date 2014年10月11日
 */
public abstract class StoreResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;

	private String msg;

	private boolean isSuccess;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		if (this.code == "0") {
			isSuccess = true;
		} else {
			isSuccess = false;
		}
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

}
