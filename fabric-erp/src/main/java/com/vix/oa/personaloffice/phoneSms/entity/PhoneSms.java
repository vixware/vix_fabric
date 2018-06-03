
package com.vix.oa.personaloffice.phoneSms.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
/**
 * 
 * @ClassName: PhoneSms
 * @Description: 手机短信 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2015-5-27 下午16:53:36
 */
public class PhoneSms extends BaseEntity{

	private static final long serialVersionUID = 1396865628678655168L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	
	/**收信人*/
	private String recipients;
	
	/**手机号码*/
	private String phoneNumber;
	
	/**发送时间*/
	private Date sendTime;
	
	/** 发布对象的id集合 */
    private String pubIds;
	
	/**内容*/
	private String phoneSmsContent;
	
	/** 是否发送 0 发送 1 保存草稿 */
	public Integer isTop;
	
	/** 发布人id */
	private String uploadPersonId;	
	
	/** id发布人 */
	private String uploadPerson;
	
	private String uploadPersonName;	
	
	public PhoneSms() {
        super();
    }
    
    public PhoneSms(String id) {
        super();
        setId(id);
    }

	public String getRecipients() {
		return recipients;
	}

	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getPhoneSmsContent() {
		return phoneSmsContent;
	}

	public void setPhoneSmsContent(String phoneSmsContent) {
		this.phoneSmsContent = phoneSmsContent;
	}

	public String getUploadPersonId() {
		return uploadPersonId;
	}

	public void setUploadPersonId(String uploadPersonId) {
		this.uploadPersonId = uploadPersonId;
	}

	public String getUploadPerson() {
		return uploadPerson;
	}

	public void setUploadPerson(String uploadPerson) {
		this.uploadPerson = uploadPerson;
	}

	public String getUploadPersonName() {
		return uploadPersonName;
	}

	public void setUploadPersonName(String uploadPersonName) {
		this.uploadPersonName = uploadPersonName;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public String getPubIds() {
		return pubIds;
	}

	public void setPubIds(String pubIds) {
		this.pubIds = pubIds;
	}
}
