/**
 * 
 */
package com.vix.oa.knowledgeManagement.entity;

import com.vix.common.share.entity.BaseBOEntity;

/**
 * 
 * @ClassName: PublicCabinet
 * @Description:  公共文件柜  
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-6-27 上午10:42:02
 */
public class PublicCabinet extends BaseBOEntity {
	private static final long serialVersionUID = 1L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 关键字*/
	private String keyword;
	/** 文件名称*/
	private String fileName;
	/**类型*/
	private String type;
	/**公共文件柜分类*/
	private PublicCabinetCategory publicCabinetCategory;

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

	public PublicCabinetCategory getPublicCabinetCategory() {
		return publicCabinetCategory;
	}

	public void setPublicCabinetCategory(PublicCabinetCategory publicCabinetCategory) {
		this.publicCabinetCategory = publicCabinetCategory;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}
}
