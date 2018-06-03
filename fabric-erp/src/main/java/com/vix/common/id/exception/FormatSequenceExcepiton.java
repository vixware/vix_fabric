package com.vix.common.id.exception;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-21
 */
public class FormatSequenceExcepiton extends IDException {

	private static final long serialVersionUID = 2764882170630522600L;

	public FormatSequenceExcepiton() {
		super("格式化序号异常!");
	}

	public FormatSequenceExcepiton(String message, Throwable cause) {
		super(message, cause);
	}

	public FormatSequenceExcepiton(String message) {
		super(message);
	}

	public FormatSequenceExcepiton(Throwable cause) {
		super(cause);
	}

}
