package com.vix.crm.service.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/** 质量保证 */
public class QualityAssurance extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 标题 */
	private String title;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 解答 */
	private String answer;
	/** 内部提示 */
	private String innerTip;
	/** 创建日期 */
	private Date createDate;
	/** 状态  1:正常 0：关闭*/
	private String status;
	/** 创建人 */
	private Employee employee;
	
	public QualityAssurance(){}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getInnerTip() {
		return innerTip;
	}

	public void setInnerTip(String innerTip) {
		this.innerTip = innerTip;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
