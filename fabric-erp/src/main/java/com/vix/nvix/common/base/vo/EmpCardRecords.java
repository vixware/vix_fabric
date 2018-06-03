/**
 * 
 */
package com.vix.nvix.common.base.vo;

/**
 * 
 * @类全名 com.vix.nvix.common.base.vo.EmpCardRecords
 *
 * @author zhanghaibing
 *
 * @date 2016年7月13日
 */
public class EmpCardRecords {
	/**
	 * 员工编码
	 */
	private String userCode;
	/**
	 * 当天时间
	 */
	private String datetemp;
	/**
	 * 最早打卡时间
	 */
	private String startcard;
	/**
	 * 最晚打卡时间
	 */
	private String endcard;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getDatetemp() {
		return datetemp;
	}

	public void setDatetemp(String datetemp) {
		this.datetemp = datetemp;
	}

	public String getStartcard() {
		return startcard;
	}

	public void setStartcard(String startcard) {
		this.startcard = startcard;
	}

	public String getEndcard() {
		return endcard;
	}

	public void setEndcard(String endcard) {
		this.endcard = endcard;
	}

}
