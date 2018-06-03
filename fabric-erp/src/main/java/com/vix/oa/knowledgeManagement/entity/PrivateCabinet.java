/**
 * 
 */
package com.vix.oa.knowledgeManagement.entity;

import com.vix.common.share.entity.BaseBOEntity;

/**
 * 
 * @ClassName: PrivateCabinet
 * @Description: 个人文件柜 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-6-25 下午1:37:39
 */
public class PrivateCabinet extends BaseBOEntity {
	private static final long serialVersionUID = 1L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 文件名称*/
	private String fileName;
	/**关键字*/
	private String keyword;
	/**类型*/
	private String type;
	private String parentId;
	/** 上传人id */
	private String uploadPersonId;	
	/** id上传人 */
	private String uploadPerson;
	private String uploadPersonName;
	/**个人文件柜分类*/
	private PrivateCabinetCategory privateCabinetCategory;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PrivateCabinetCategory getPrivateCabinetCategory() {
		return privateCabinetCategory;
	}

	public void setPrivateCabinetCategory(
			PrivateCabinetCategory privateCabinetCategory) {
		this.privateCabinetCategory = privateCabinetCategory;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
