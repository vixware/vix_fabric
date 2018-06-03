/**
 * 
 */
package com.vix.dtbcenter.deliveryroute.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 预先定义的车辆配送 路线
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-27
 */
public class DispatchRoute extends BaseEntity {

	private static final long serialVersionUID = 5987316928717578866L;
	/** 路线序号 */
	private int pathSerial;
	/** 起始地 */
	private String source;
	/** 目的地 */
	private String target;
	/** 预计时间 */
	private Date forcastTime;
	/** 里程 */
	private Double mileage;

	public int getPathSerial() {
		return pathSerial;
	}

	public void setPathSerial(int pathSerial) {
		this.pathSerial = pathSerial;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Date getForcastTime() {
		return forcastTime;
	}

	public void setForcastTime(Date forcastTime) {
		this.forcastTime = forcastTime;
	}

	public Double getMileage() {
		return mileage;
	}

	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}
}
