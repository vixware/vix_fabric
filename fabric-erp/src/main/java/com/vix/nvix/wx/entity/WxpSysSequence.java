package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpSysSequence
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */
public class WxpSysSequence extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String seqMark;
	Long seqVal;
	String scopeMark;

	public String getSeqMark() {
		return seqMark;
	}

	public void setSeqMark(String seqMark) {
		this.seqMark = seqMark;
	}

	public Long getSeqVal() {
		return seqVal;
	}

	public void setSeqVal(Long seqVal) {
		this.seqVal = seqVal;
	}

	public String getScopeMark() {
		return scopeMark;
	}

	public void setScopeMark(String scopeMark) {
		this.scopeMark = scopeMark;
	}

}
