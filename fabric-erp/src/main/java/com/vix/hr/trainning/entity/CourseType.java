package com.vix.hr.trainning.entity;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassName: CourseType
 * @Description: 课程类别
 * @author bobchen
 * @date 2015年10月22日 上午10:17:00
 *
 */
public class CourseType extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 是否启用 */
	public Integer disabled;

	/** 培训课程 */
	private Set<TrainingCost> trainingCost = new HashSet<TrainingCost>();

	public CourseType() {
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

	public Set<TrainingCost> getTrainingCost() {
		return trainingCost;
	}

	public void setTrainingCost(Set<TrainingCost> trainingCost) {
		this.trainingCost = trainingCost;
	}

}
