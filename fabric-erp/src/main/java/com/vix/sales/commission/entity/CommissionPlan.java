package com.vix.sales.commission.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;

/**
 * 
	1. 首先定义销售类型，销售类型实际为佣金组的概念，即把具有相同佣金方案的人放到一起；
	2. 输入销售定额，销售定额以销售人员为中心，根据不同的物料，不同的时间周期，设置不同的销售数量和销售金额
	3. 设定销售比率, 销售比率类似数据字典表，主要作用为用于根据类型确定使用哪些条件进行计算；
		销售比率表中的类型字段和佣金方案明细中的业绩考评方式对应；
		业务考核方式实际是设定的计算模式，用于区分采用哪些计算条件；
	4. 根据佣金方案明细中的 计算基础 字段 获取销售人员 的销售数据；
		计算基础来自销售订单、销售发票、销售回款等，还是其他单据 ; 
		现阶段可以采用销售订单、销售发票作为计算基础.
	5. 结合计算方式以及是否考虑累计业绩的属性，进行计算。
	6. 如果是采用固定佣金比率，则不需要考虑佣金比率表中的条件，直接按照固定佣金比率计算即可。
 * @author Administrator
 *
 */
public class CommissionPlan extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 销售组织 */
	private OrganizationUnit saleOrg;
	/** 佣金计划编码 */
	private String cpCode;
	/** 佣金计划名称 */
	private String cpName;
	/** 佣金计划明细 */
	private Set<CommissionPlanItem> commissionPlanItems = new HashSet<CommissionPlanItem>();
	
	public CommissionPlan(){}

	public OrganizationUnit getSaleOrg() {
		return saleOrg;
	}

	public void setSaleOrg(OrganizationUnit saleOrg) {
		this.saleOrg = saleOrg;
	}

	public String getCpCode() {
		return cpCode;
	}

	public void setCpCode(String cpCode) {
		this.cpCode = cpCode;
	}

	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

	public Set<CommissionPlanItem> getCommissionPlanItems() {
		return commissionPlanItems;
	}

	public void setCommissionPlanItems(Set<CommissionPlanItem> commissionPlanItems) {
		this.commissionPlanItems = commissionPlanItems;
	}
}
