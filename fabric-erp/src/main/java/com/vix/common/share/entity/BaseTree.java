package com.vix.common.share.entity;

/**
 * 基础树形对象
 * @author Administrator
 *
 */
public class BaseTree extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 是否叶子节点 */
	private String isLeaf;
	
	public BaseTree(){}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
}
