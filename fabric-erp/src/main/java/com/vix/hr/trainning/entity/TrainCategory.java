package com.vix.hr.trainning.entity;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @Description: 课程性质
 * @author chenzhengwen
 * @date 2015-8-24 下午4:44:19
 */
public class TrainCategory extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 是否启用 */
	public Integer disabled;

	/** 培训课程 */
	private Set<TrainingCM> trainingCM = new HashSet<TrainingCM>();

	public TrainCategory() {
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	public Set<TrainingCM> getTrainingCM() {
		return trainingCM;
	}

	public void setTrainingCM(Set<TrainingCM> trainingCM) {
		this.trainingCM = trainingCM;
	}

}
