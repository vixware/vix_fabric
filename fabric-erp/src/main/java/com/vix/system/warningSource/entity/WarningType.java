/**
 * 
 */
package com.vix.system.warningSource.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * 预警类型
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-20
 */
public class WarningType extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 分类编码 */
	private String typeCode;
	/** 分类名称 */
	private String typeName;
	/** 分类描述 */
	private String typeDescription;
	private Long executionFrequency;
	private String executionFrequencyUtil;
	private Date executeTime;
	/**
	 * 任务执行类基类
	 */
	private String classCode;
	/**
	 * 模块分类
	 */
	private ModuleCategory moduleCategory;

	public WarningType() {
	}

	public WarningType(String id) {
		super();
		setId(id);
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	public ModuleCategory getModuleCategory() {
		return moduleCategory;
	}

	public void setModuleCategory(ModuleCategory moduleCategory) {
		this.moduleCategory = moduleCategory;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public Long getExecutionFrequency() {
		return executionFrequency;
	}

	public void setExecutionFrequency(Long executionFrequency) {
		this.executionFrequency = executionFrequency;
	}

	public String getExecutionFrequencyUtil() {
		return executionFrequencyUtil;
	}

	public void setExecutionFrequencyUtil(String executionFrequencyUtil) {
		this.executionFrequencyUtil = executionFrequencyUtil;
	}

	public Date getExecuteTime() {
		return executeTime;
	}
	public String getExecuteTimeStr() {
		if(executeTime != null){
			return DateUtil.format(executeTime);
		}
		return "";
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

}
