package com.vix.crm.workbench.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/** 知识库分类 */
public class KnowledgeCategory extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 子分类集合 */
	private Set<KnowledgeCategory> subKnowledgeCategorys = new HashSet<KnowledgeCategory>();
	/** 父分类 */
	private KnowledgeCategory parentKnowledgeCategory;
	
	public KnowledgeCategory() {}

	public Set<KnowledgeCategory> getSubKnowledgeCategorys() {
		return subKnowledgeCategorys;
	}

	public void setSubKnowledgeCategorys(
			Set<KnowledgeCategory> subKnowledgeCategorys) {
		this.subKnowledgeCategorys = subKnowledgeCategorys;
	}

	public KnowledgeCategory getParentKnowledgeCategory() {
		return parentKnowledgeCategory;
	}

	public void setParentKnowledgeCategory(KnowledgeCategory parentKnowledgeCategory) {
		this.parentKnowledgeCategory = parentKnowledgeCategory;
	}
}
