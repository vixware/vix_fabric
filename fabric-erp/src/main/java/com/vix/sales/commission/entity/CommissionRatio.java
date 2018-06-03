package com.vix.sales.commission.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/** 佣金比率 用于给销售人员设定销售目标金额，类似销售完成的任务金额。 */
public class CommissionRatio extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 佣金比率编码 */
	private String crCode;
	/** 佣金比率名称 */
	private String crName;
	/** 佣金比率明细 */
	private Set<CommissionRatioItem> commissionRatioItems = new HashSet<CommissionRatioItem>();
	/** 佣金方案明细 */
	private CommissionPlanItem commissionPlanItem;
	
	public CommissionRatio(){}

	public String getCrCode() {
		return crCode;
	}

	public void setCrCode(String crCode) {
		this.crCode = crCode;
	}

	public String getCrName() {
		return crName;
	}

	public void setCrName(String crName) {
		this.crName = crName;
	}

	public Set<CommissionRatioItem> getCommissionRatioItems() {
		return commissionRatioItems;
	}

	public void setCommissionRatioItems(
			Set<CommissionRatioItem> commissionRatioItems) {
		this.commissionRatioItems = commissionRatioItems;
	}

	public CommissionPlanItem getCommissionPlanItem() {
		return commissionPlanItem;
	}

	public void setCommissionPlanItem(CommissionPlanItem commissionPlanItem) {
		this.commissionPlanItem = commissionPlanItem;
	}
}
