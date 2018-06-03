package com.vix.oa.adminMg.conference.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 会议纪要
 * 
 * @类全名 com.vix.oa.adminMg.conference.entity.MeetingSummary
 *
 * @author zhanghaibing
 *
 * @date 2016年8月2日
 */
public class MeetingSummary extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 会议纪要正文
	 */
	private String meetingContent;
	/**
	 * 会议
	 */
	private ApplicationMg applicationMg;

	public String getMeetingContent() {
		return meetingContent;
	}

	public void setMeetingContent(String meetingContent) {
		this.meetingContent = meetingContent;
	}

	public ApplicationMg getApplicationMg() {
		return applicationMg;
	}

	public void setApplicationMg(ApplicationMg applicationMg) {
		this.applicationMg = applicationMg;
	}

}
