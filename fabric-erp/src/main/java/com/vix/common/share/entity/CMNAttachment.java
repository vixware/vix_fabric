package com.vix.common.share.entity;

import java.util.Date;

/**
 * 共用-附件
 * @author Administrator
 *
 */
public class CMNAttachment extends BaseEntity{
 
	private static final long serialVersionUID = 1L;
	/** 业务对象编码 */
	private String boCode;
	/** 业务对象名称 */
	private String boName;
	/** 业务对象类型 */
	private String boType;
	/** 路径 */
	private String path;
	/** 时间 */
	private Date createTime;
	/** 上传人 */
	private String operator;
	
	public CMNAttachment(){}

	public String getBoCode() {
		return boCode;
	}

	public void setBoCode(String boCode) {
		this.boCode = boCode;
	}

	public String getBoName() {
		return boName;
	}

	public void setBoName(String boName) {
		this.boName = boName;
	}

	public String getBoType() {
		return boType;
	}

	public void setBoType(String boType) {
		this.boType = boType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
}
