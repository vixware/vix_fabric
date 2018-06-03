/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 项目会议
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class ProjectMeeting extends BaseEntity {
	/** 会议地点 */
	private String address;
	/** 会议类型 */
	private String type;
	/** 会议内容 */
	private String content;
	/** 会议持续时间(天) */
	private Float time;
	/** 参与人 */
	private String persons;
	/** 次数 */
	private String count;
	/** 目标 */
	private String target;
	/** 议程 */
	private String agenda;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Float getTime() {
		return time;
	}

	public void setTime(Float time) {
		this.time = time;
	}

	public String getPersons() {
		return persons;
	}

	public void setPersons(String persons) {
		this.persons = persons;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getAgenda() {
		return agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}
}
