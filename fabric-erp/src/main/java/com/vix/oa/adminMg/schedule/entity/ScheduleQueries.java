
package com.vix.oa.adminMg.schedule.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

public class ScheduleQueries extends BaseEntity{

	private static final long serialVersionUID = 1396865628678655168L;
	
	/**
	 * 姓名
	 */
	private String rname;
	/**
	 * 开始日期
	 */
	private Date startDate;
	/**
	 * 结束日期
	 */
	private Date endDate;
	/**
	 * 事务内容
	 */
	private String scheduleContent;
	/**
	 * 人员范围
	 */
	private String rangeStaff;
	/**
	 * 人员角色
	 */
	private String staffRoles;
	/**
	 * 说明
	 */
	private String explanation;
	 /** 置顶 */
    /** 是否置顶 0 否  1 是*/
    private Boolean onTop ;
	/**
	 * 附件文档
	 */
	private String attachedDocument;
	/**
	 * 附件选择
	 */
	private String selectAccessories;
	/**
	 * 备注
	 */
	private String remark;

	public ScheduleQueries() {
        super();
    }
    
    public ScheduleQueries(String id) {
        super();
        setId(id);
    }

	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getScheduleContent() {
		return scheduleContent;
	}
	public void setScheduleContent(String scheduleContent) {
		this.scheduleContent = scheduleContent;
	}
	public String getRangeStaff() {
		return rangeStaff;
	}
	public void setRangeStaff(String rangeStaff) {
		this.rangeStaff = rangeStaff;
	}
	public String getStaffRoles() {
		return staffRoles;
	}
	public void setStaffRoles(String staffRoles) {
		this.staffRoles = staffRoles;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public Boolean getOnTop() {
		return onTop;
	}
	public void setOnTop(Boolean onTop) {
		this.onTop = onTop;
	}
	public String getAttachedDocument() {
		return attachedDocument;
	}
	public void setAttachedDocument(String attachedDocument) {
		this.attachedDocument = attachedDocument;
	}
	public String getSelectAccessories() {
		return selectAccessories;
	}
	public void setSelectAccessories(String selectAccessories) {
		this.selectAccessories = selectAccessories;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
