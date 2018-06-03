package com.rest.core.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rest.core.exception.RestException;
import com.rest.core.utils.MediaTypes;
import com.vix.common.common.bo.MessageObject;
import com.vix.core.exception.BizException;

/**
 * 自定义ExceptionHandler，专门处理Restful异常.
 * 
 * @author calvin
 */
// 会被Spring-MVC自动扫描，但又不属于Controller的annotation。
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	//private JsonMapper jsonMapper = new JsonMapper();
	
	private static Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	@ExceptionHandler(value={BizException.class,RestException.class})
	public final ResponseEntity<?> handleException(RuntimeException ex, WebRequest request) {
		logger.error("BIZ ERROR:",ex);
		ex.printStackTrace();
		HttpHeaders headers = new HttpHeaders();
		/*
		headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
		ResponseEntity<?> re = handleExceptionInternal(ex, ex.getMessage(), headers, HttpStatus.CONFLICT, request);
		return re;
		*/
		//ResponseEntity<?> re = handleExceptionInternal(ex, ex.getMessage(), headers, ex.status, request);
		headers.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));
		MessageObject mo = new MessageObject();
		mo.setSuccess(false);
		mo.setMessage(ex.getMessage());
		ResponseEntity<?> re = handleExceptionInternal(ex, mo, headers, HttpStatus.OK, request);
		return re;
	}
	
	
	@ExceptionHandler(value={
			Exception.class
		})
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		logger.error("ERROR:",ex);
		ex.printStackTrace();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));

		MessageObject mo = new MessageObject();
		mo.setSuccess(false);
		mo.setMessage(ex.getMessage());
		//HttpStatus.INTERNAL_SERVER_ERROR
		ResponseEntity<Object> re = handleExceptionInternal(ex, mo, headers, HttpStatus.OK, request);
		return re;
	}
	
	/*
	@ExceptionHandler(value={Exception.class})
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		System.out.println("IN ALL EXcep");
		ex.printStackTrace();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));
		MessageObject mo = new MessageObject();
		mo.setSuccess(false);
		mo.setMessage(ex.getMessage());
		ResponseEntity<Object> re = handleExceptionInternal(ex, mo, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
		return re;
	}*/
	
	/**
	 * 处理RestException.
	@ExceptionHandler(value={
			BizException.class,
			RestException.class
		})
	public final ResponseEntity<?> handleException(RuntimeException ex, WebRequest request) {
		ex.printStackTrace();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
		//ResponseEntity<?> re = handleExceptionInternal(ex, ex.getMessage(), headers, ex.status, request);
		//headers.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));
		System.out.println(ex.getMessage());
		ResponseEntity<?> re = handleExceptionInternal(ex, ex.getMessage(), headers, HttpStatus.CONFLICT, request);
		return re;
	}
	 */
	/**
	 * 处理JSR311 Validation异常.
	
	@ExceptionHandler(value = { ConstraintViolationException.class })
	public final ResponseEntity<?> handleException(ConstraintViolationException ex, WebRequest request) {
		Map<String, String> errors = BeanValidators.extractPropertyAndMessage(ex.getConstraintViolations());
		String body = jsonMapper.toJson(errors);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
		return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
	} */
}
