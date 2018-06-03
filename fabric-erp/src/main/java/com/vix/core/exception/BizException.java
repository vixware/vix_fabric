package com.vix.core.exception;


/**
 * 业务构件的异常封装
 * @author wangmingchen
 *
 */
public class BizException extends RuntimeException {

	public BizException() {
		super();
	}

	public BizException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BizException(String msg) {
		super(msg);
	}

	public BizException(Throwable cause) {
		super(cause);
	}



	
}
