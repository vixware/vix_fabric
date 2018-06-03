package com.vix.nvix.wx.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpTemplateMsgIndustry
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */

public class WxpTemplateMsgIndustry extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private Date updateIndustryDate;
	private Long count;
	private Date nextUpdateIndustryDate;
	private WxpTemplateMessageIndustry hostTemplateMessageIndustry;
	private WxpTemplateMessageIndustry depustryTemplateMessageIndustry;
	private Long templateCount = 0l;

	public Integer getIsEdit() {
		int isEdit = 0;
		if (count == 1) {
			isEdit = 1;
		} else if (nextUpdateIndustryDate != null) {
			Date currentDate = new Date();
			if (currentDate.getTime() > currentDate.getTime() || currentDate.getTime() == currentDate.getTime()) {
				isEdit = 1;
			}
		}
		return isEdit;
	}

	public Date getUpdateIndustryDate() {
		return updateIndustryDate;
	}

	public void setUpdateIndustryDate(Date updateIndustryDate) {
		this.updateIndustryDate = updateIndustryDate;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Date getNextUpdateIndustryDate() {
		return nextUpdateIndustryDate;
	}

	public void setNextUpdateIndustryDate(Date nextUpdateIndustryDate) {
		this.nextUpdateIndustryDate = nextUpdateIndustryDate;
	}

	public WxpTemplateMessageIndustry getHostTemplateMessageIndustry() {
		return hostTemplateMessageIndustry;
	}

	public void setHostTemplateMessageIndustry(WxpTemplateMessageIndustry hostTemplateMessageIndustry) {
		this.hostTemplateMessageIndustry = hostTemplateMessageIndustry;
	}

	public WxpTemplateMessageIndustry getDepustryTemplateMessageIndustry() {
		return depustryTemplateMessageIndustry;
	}

	public void setDepustryTemplateMessageIndustry(WxpTemplateMessageIndustry depustryTemplateMessageIndustry) {
		this.depustryTemplateMessageIndustry = depustryTemplateMessageIndustry;
	}

	public Long getTemplateCount() {
		return templateCount;
	}

	public void setTemplateCount(Long templateCount) {
		this.templateCount = templateCount;
	}
}
