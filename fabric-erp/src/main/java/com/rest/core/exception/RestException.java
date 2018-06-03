package com.rest.core.exception;

import org.springframework.http.HttpStatus;

import com.vix.core.exception.BizException;

/**
 * 专用于Restful Service的异常.
 * 
 * @author calvin
 */
public class RestException extends BizException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

	public RestException() {
	}

	public RestException(HttpStatus status) {
		this.status = status;
	}

	public RestException(String message) {
		super(message);
	}

	public RestException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}
}
