package com.vix.oa.adminMg.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

public class ProgressLog extends BaseEntity {
	private static final long serialVersionUID = -3943610172581798396L;
	/** 当前时间 */
	private Date currentTime;
	
	/** 进度详情*/
	private String schedule;
	
	/** 完成百分比*/
	private String percent;
	
	/** 附件 */
	private String accessory;
	
	/** 状态 *//*
	private String status;*/
	
	public ProgressLog() {
        super();
    }
    
    public ProgressLog(String id) {
        super();
        setId(id);
    }

	public String getAccessory() {
		return accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public Date getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}
    }
	

	

