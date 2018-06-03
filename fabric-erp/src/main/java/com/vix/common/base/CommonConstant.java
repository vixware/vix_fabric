package com.vix.common.base;

import java.io.Serializable;

public class CommonConstant implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 操作数据不存在 */
	public static final String OPERATE_FAIL_DATA_NOT_EXIST = "用户要修改的数据不存在";
	
	/** 修改用户账号数据时输入的原始密码不正确 */
	public  static final String OLD_PASSWORD_ERROR = "原始密码不正确";
	
}
