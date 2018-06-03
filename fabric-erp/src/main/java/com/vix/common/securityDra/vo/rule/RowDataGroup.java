package com.vix.common.securityDra.vo.rule;

import java.io.Serializable;
import java.util.List;

/**
 * 数据权限规则设定的 组
 * @author Administrator
 *
 */
public class RowDataGroup implements Serializable {

	private List<RowDataRule> rules;

	/**  用于对 rowDataRule 的集合中每个规则对象之间的关系  值为 and 或者 or */
	private String op;

	public List<RowDataRule> getRules() {
		return rules;
	}

	public void setRules(List<RowDataRule> rules) {
		this.rules = rules;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}
	
}
