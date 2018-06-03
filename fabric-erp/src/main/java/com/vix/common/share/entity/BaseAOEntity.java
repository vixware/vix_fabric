/*
 * Copyright (C) 2014 VixSoft Inc.
 *
 * Licensed under the VixSoft License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at license.txt
 * 
 * Purpose:  受托业务中的基础业务对象
 * Author:   Jackie
 * Date:     2014.06.07
 * Version:  1.0
 *
 */

package com.vix.common.share.entity;


/**
 * BaseAOEntity 用于在需要使用受托人或被指定人的业务场景中派生业务对象
 * 
 * 使用场景如协同办公的任务管理, 营销活动, 项目管理中的任务管理
 * 
 * 
 * @author Jackie
 *
 */
public class BaseAOEntity extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	// 被指定人
	private String assignee = null;
	
	// 被指定人姓名
	private String assigneer = null;
	
	public BaseAOEntity() {};
	
	public String getAssignee(){
		return this.assignee;
	}
	
	public void  setAssignee(String assignee){
		this.assignee = assignee;
	}
	
	
	public String getAssigneer(){
		return this.assigneer;
	}
	
	public void setAssigneer(String assigneer){
		this.assigneer = assigneer;
	}
}
