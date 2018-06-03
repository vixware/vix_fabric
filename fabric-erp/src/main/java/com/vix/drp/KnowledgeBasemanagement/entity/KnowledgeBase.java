/**
 * 
 */
package com.vix.drp.KnowledgeBasemanagement.entity;

import com.vix.common.share.entity.BaseBOEntity;

/**
 * 知识点
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-20
 */
public class KnowledgeBase extends BaseBOEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 关键字
	 */
	private String keyword;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 知识点分类
	 */
	private KnowledgeBaseCategory knowledgeBaseCategory;

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

	public KnowledgeBaseCategory getKnowledgeBaseCategory() {
		return knowledgeBaseCategory;
	}

	public void setKnowledgeBaseCategory(KnowledgeBaseCategory knowledgeBaseCategory) {
		this.knowledgeBaseCategory = knowledgeBaseCategory;
	}

}
