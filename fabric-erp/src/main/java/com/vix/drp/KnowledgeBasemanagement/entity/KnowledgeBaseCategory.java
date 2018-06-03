package com.vix.drp.KnowledgeBasemanagement.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 知识库分类
 * 
 * com.vix.drp.KnowledgeBasemanagement.entity.KnowledgeBaseCategory
 * 
 * @author zhanghaibing
 * 
 * @date 2014-6-12
 */
public class KnowledgeBaseCategory extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 子分类集合 */
	private Set<KnowledgeBaseCategory> subKnowledgeBaseCategorys = new HashSet<KnowledgeBaseCategory>();
	/**
	 * 知识点
	 */
	private Set<KnowledgeBase> knowledgeBases = new HashSet<KnowledgeBase>();
	/** 父分类 */
	private KnowledgeBaseCategory parentKnowledgeBaseCategory;

	public KnowledgeBaseCategory() {
	}

	public Set<KnowledgeBaseCategory> getSubKnowledgeBaseCategorys() {
		return subKnowledgeBaseCategorys;
	}

	public void setSubKnowledgeBaseCategorys(Set<KnowledgeBaseCategory> subKnowledgeBaseCategorys) {
		this.subKnowledgeBaseCategorys = subKnowledgeBaseCategorys;
	}

	public KnowledgeBaseCategory getParentKnowledgeBaseCategory() {
		return parentKnowledgeBaseCategory;
	}

	public void setParentKnowledgeBaseCategory(KnowledgeBaseCategory parentKnowledgeBaseCategory) {
		this.parentKnowledgeBaseCategory = parentKnowledgeBaseCategory;
	}

	public Set<KnowledgeBase> getKnowledgeBases() {
		return knowledgeBases;
	}

	public void setKnowledgeBases(Set<KnowledgeBase> knowledgeBases) {
		this.knowledgeBases = knowledgeBases;
	}

}
