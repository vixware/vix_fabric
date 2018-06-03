/**
 * 
 */
package com.vix.ebusiness.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 订单操作日志
 * 
 * com.vix.ebusiness.entity.OrderOperationLog
 *
 * @author zhanghaibing
 *
 * @date 2014年10月11日
 */
public class OrderOperationLog extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date operateTime; // 操作时间
	private String operateDesc; // 操作内容描述
	private Order order;// 操作订单
	private Employee employee;// 操作人

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateDesc() {
		return operateDesc;
	}

	public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
