/**
 * 
 */
package com.vix.dtbcenter.vehiclemanagement.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 安检登记
 * 
 * @author zhanghaibing
 * 
 * @date 2013-9-2
 */
public class SaftRegister extends BaseEntity {
	private static final long serialVersionUID = -6921750066846412908L;
	/** 安检记录号 */
	private String securityCheckCode;
	/** 安检日期 */
	private Date time;
	/** 安检类型 */
	private String type;
	/** 车牌号 */
	private String vechileNumber;
	/** 调度单编号 */
	private String dispatchFormCode;
	/** 安检结果 */
	private String result;
	/** 项目名称 */
	private String projectName;
	/** 问题描述 */
	private String question;
	/** 处理意见 */
	private String dealOpinion;
	/** 安检人员 */
	private String person;
	/** 创建人 */
	private String creator;
	/** 创建时间 */
	private Date createTime;
	/** 修改人 */
	private String modifier;
	/** 修改时间 */
	private Date modifyTime;
	/** 车辆 */
	private Vehicle vehicle;

	public String getSecurityCheckCode() {
		return securityCheckCode;
	}

	public void setSecurityCheckCode(String securityCheckCode) {
		this.securityCheckCode = securityCheckCode;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVechileNumber() {
		return vechileNumber;
	}

	public void setVechileNumber(String vechileNumber) {
		this.vechileNumber = vechileNumber;
	}

	public String getDispatchFormCode() {
		return dispatchFormCode;
	}

	public void setDispatchFormCode(String dispatchFormCode) {
		this.dispatchFormCode = dispatchFormCode;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getDealOpinion() {
		return dealOpinion;
	}

	public void setDealOpinion(String dealOpinion) {
		this.dealOpinion = dealOpinion;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	@Override
	public String getCreator() {
		return creator;
	}

	@Override
	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
