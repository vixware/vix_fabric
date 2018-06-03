package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

public class WxPaySource extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String out_trade_no;// 交易编码(唯一)
	private String source;// 交易来源(0:POS消费;1:充值消费;2:办卡消费)

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
