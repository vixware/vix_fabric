
package com.vix.oa.infoCenter.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

public class MessageManagement extends BaseEntity{

	private static final long serialVersionUID = 1396865628678655168L;
	
	/**收信人*/
	private String senderPeople;
	
	/**抄送人*/
	private String ccPeople;
	
	/**抄送人*/
	private String ccPeople1;
	
	/** 发布人id */
	private String uploadPersonId;	
	
	/** 发布对象的id集合 */
    private String pubIds;
    
    /** 发布对象的id集合 */
    private String pubIds1;
    
    /** 发布对象的id集合 */
    private String pubIds2;
	
	/**
	 * 是否为正式数据
	 */
	private String isTemp;
	
	/** id发布人 */
	private String uploadPerson;
	
	private String uploadPersonName;
	
	/** 发送日期*/
	public Date sendDate;
	
	/** 是否发送 0 发送 1 保存草稿 */
	public Integer isTop;
	
	/**内容*/
	private String newscontent;
	
	/** 上传附件 */
	private Set<ManagementUploader> managementUploader = new HashSet<ManagementUploader>();
	
	/** 消息评论 */
	private Set<NewsComment> newsComment = new HashSet<NewsComment>();
    

	public MessageManagement() {
        super();
    }
    
    public MessageManagement(String id) {
        super();
        setId(id);
    }

	public String getSenderPeople() {
		return senderPeople;
	}

	public void setSenderPeople(String senderPeople) {
		this.senderPeople = senderPeople;
	}

	public String getCcPeople() {
		return ccPeople;
	}

	public void setCcPeople(String ccPeople) {
		this.ccPeople = ccPeople;
	}

	public String getNewscontent() {
		return newscontent;
	}

	public void setNewscontent(String newscontent) {
		this.newscontent = newscontent;
	}

	public String getCcPeople1() {
		return ccPeople1;
	}

	public void setCcPeople1(String ccPeople1) {
		this.ccPeople1 = ccPeople1;
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

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public Set<ManagementUploader> getManagementUploader() {
		return managementUploader;
	}

	public void setManagementUploader(Set<ManagementUploader> managementUploader) {
		this.managementUploader = managementUploader;
	}

	@Override
	public String getIsTemp() {
		return isTemp;
	}

	@Override
	public void setIsTemp(String isTemp) {
		this.isTemp = isTemp;
	}

	public String getPubIds() {
		return pubIds;
	}

	public void setPubIds(String pubIds) {
		this.pubIds = pubIds;
	}

	public String getPubIds1() {
		return pubIds1;
	}

	public void setPubIds1(String pubIds1) {
		this.pubIds1 = pubIds1;
	}

	public String getPubIds2() {
		return pubIds2;
	}

	public void setPubIds2(String pubIds2) {
		this.pubIds2 = pubIds2;
	}

	public Set<NewsComment> getNewsComment() {
		return newsComment;
	}

	public void setNewsComment(Set<NewsComment> newsComment) {
		this.newsComment = newsComment;
	}

}
