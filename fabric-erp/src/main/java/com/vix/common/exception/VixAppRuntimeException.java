package com.vix.common.exception;


/**
 * VixAppException 为Vixware ERP系统的运行时异常父类.
 * @author Jackie
 *
 */
public class VixAppRuntimeException extends RuntimeException {

	public VixAppRuntimeException() {
		// TODO Auto-generated constructor stub
	}

	public VixAppRuntimeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public VixAppRuntimeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public VixAppRuntimeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
