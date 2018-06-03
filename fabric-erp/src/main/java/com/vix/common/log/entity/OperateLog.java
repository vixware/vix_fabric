package com.vix.common.log.entity;

import com.vix.common.share.entity.BaseEntity;

/** 
 * 操作日志
 * @author arron
 */
public class OperateLog extends BaseEntity {

	private static final long serialVersionUID = 1L;
	 
	/** 操作人id */
	private String operaterId;
	/** 操作人姓名 */
	private String operaterName;
	/** 方法名称 */
	private String methodName;
	/** 操作内容 */
	private String content;
	/** 对象id */
	private String objectId;
	/** 对象全名 */
	private String objectClass;
	/** 对象类型 */
	private String objectType;
	/** 查询关键字 */
	private String keyWord;
	/** ip地址 */
	private String ipAddress;
	/** ip地区 */
	private String ipCountry;
	/** 请求的url */
	private String requestUrl;
	
	public OperateLog() {}

	public String getOperaterId() {
		return operaterId;
	}

	public void setOperaterId(String operaterId) {
		this.operaterId = operaterId;
	}

	public String getOperaterName() {
		return operaterName;
	}

	public void setOperaterName(String operaterName) {
		this.operaterName = operaterName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getObjectClass() {
		return objectClass;
	}

	public void setObjectClass(String objectClass) {
		this.objectClass = objectClass;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getIpCountry() {
		return ipCountry;
	}

	public void setIpCountry(String ipCountry) {
		this.ipCountry = ipCountry;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
}