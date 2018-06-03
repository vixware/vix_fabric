package com.vix.hr.job.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.initialSetup.entity.Proficiency;
import com.vix.hr.initialSetup.entity.TechnicalLevel;
import com.vix.hr.initialSetup.entity.TitleGrade;
import com.vix.hr.initialSetup.entity.TitleType;
import com.vix.hr.organization.entity.Employee;

/**
 * @Description: 职称体系
 * @author 李大鹏
 */
public class HrTitleSys extends BaseEntity {

	private static final long serialVersionUID = -1051853827864513483L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 职称编码 */
	private String titlesysCode;
	/** 职称名称 */
	private String titlesysName;
	/** 技术名称 */
	private String technicalName;
	/** 工作经验 */
	private String workExperience;
	/** 人员 */
	private Employee employee;
	/** 职称类型 */
	private TitleType titleType;
	/** 职称类型 */
	private TitleGrade titleGrade;
	/** 职称类型 */
	private TechnicalLevel technicalLevel;
	/** 职称类型 */
	private Proficiency proficiency;

	public String getTitlesysCode() {
		return titlesysCode;
	}

	public void setTitlesysCode(String titlesysCode) {
		this.titlesysCode = titlesysCode;
	}

	public String getTitlesysName() {
		return titlesysName;
	}

	public void setTitlesysName(String titlesysName) {
		this.titlesysName = titlesysName;
	}

	public TitleGrade getTitleGrade() {
		return titleGrade;
	}

	public void setTitleGrade(TitleGrade titleGrade) {
		this.titleGrade = titleGrade;
	}

	public TechnicalLevel getTechnicalLevel() {
		return technicalLevel;
	}

	public void setTechnicalLevel(TechnicalLevel technicalLevel) {
		this.technicalLevel = technicalLevel;
	}

	public Proficiency getProficiency() {
		return proficiency;
	}

	public void setProficiency(Proficiency proficiency) {
		this.proficiency = proficiency;
	}

	public String getTechnicalName() {
		return technicalName;
	}

	public void setTechnicalName(String technicalName) {
		this.technicalName = technicalName;
	}

	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public TitleType getTitleType() {
		return titleType;
	}

	public void setTitleType(TitleType titleType) {
		this.titleType = titleType;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

}
