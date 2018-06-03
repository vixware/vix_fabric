package com.vix.oa.adminMg.entity;


import com.vix.common.share.entity.BaseEntity;
public class WorkPlanType extends BaseEntity {
	private static final long serialVersionUID = -3943610172581798396L;
	/** 序号 */
	private String orderNumber;
	
	public WorkPlanType() {
        super();
    }
    
    public WorkPlanType(String id) {
        super();
        setId(id);
    }

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

}