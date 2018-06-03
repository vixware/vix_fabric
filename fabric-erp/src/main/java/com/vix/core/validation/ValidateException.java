package com.vix.core.validation;

import com.vix.core.exception.BizException;

public class ValidateException extends BizException {
	private static final long serialVersionUID = 20920496215941871L;
	
	public ValidateException() {
		super();
	}

	public ValidateException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ValidateException(String msg) {
		super(msg);
	}

	public ValidateException(Throwable cause) {
		super(cause);
	}
}