package com.vix.common.common.bo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class MessageObject implements Serializable {

	private boolean success = true;
	
	//提示消息
	private String message;
	
	private Object bo;
	
	private String jsonObject;

	private String bizType;
	
	//消息验证使用
	private Map<String,String> msgMap = new LinkedHashMap<String, String>();
	
	private Collection boList;
	
	public MessageObject() {
		super();
	}

	public MessageObject(boolean success, String message, Object bo,
			String jsonObject) {
		super();
		this.success = success;
		this.message = message;
		this.bo = bo;
		this.jsonObject = jsonObject;
	}

	public MessageObject(boolean success, String message,
			Object bo, String jsonObject,
			String bizType, Map<String, String> msgMap) {
		super();
		this.success = success;
		this.message = message;
		this.bo = bo;
		this.jsonObject = jsonObject;
		this.bizType = bizType;
		this.msgMap = msgMap;
	}

	public MessageObject(boolean success, String message, Object bo,
			Map<String, String> msgMap) {
		super();
		this.success = success;
		this.message = message;
		this.bo = bo;
		this.msgMap = msgMap;
	}

	public MessageObject(boolean success, String message,
			Map<String, String> msgMap) {
		super();
		this.success = success;
		this.message = message;
		this.msgMap = msgMap;
	}

	public MessageObject(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getBo() {
		return bo;
	}

	public void setBo(Object bo) {
		this.bo = bo;
	}

	public String getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(String jsonObject) {
		this.jsonObject = jsonObject;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public Map<String, String> getMsgMap() {
		return msgMap;
	}

	public void setMsgMap(Map<String, String> msgMap) {
		this.msgMap = msgMap;
	}

	public Collection getBoList() {
		return boList;
	}

	public void setBoList(Collection boList) {
		this.boList = boList;
	}

	@Override
	public String toString() {
		return "MessageObject [success=" + success + ", message=" + message
				+ ", bo=" + bo + ", jsonObject=" + jsonObject + ", bizType="
				+ bizType + ", msgMap=" + msgMap + "]";
	}

}
