/**
 * 
 */
package com.vix.common.id.exception;

/**
 * IDException
 * 
 * @author zhanghaibing
 * 
 * @date 2013-6-20
 */
public class IDException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IDException() {
		super("ID异常!");
	}

	public IDException(String message, Throwable cause) {
		super(message, cause);
	}

	public IDException(String message) {
		super(message);
	}

	public IDException(Throwable cause) {
		super(cause);
	}

}
