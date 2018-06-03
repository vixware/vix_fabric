package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 微信模板消息行业
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpTemplateMessageIndustry
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */
public class WxpTemplateMessageIndustry extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 代码 */
	private String code;
	/** 主行业 */
	private String hostIndustry;
	/** 副行业 */
	private String deputyIndustry;

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	public String getHostIndustry() {
		return hostIndustry;
	}

	public void setHostIndustry(String hostIndustry) {
		this.hostIndustry = hostIndustry;
	}

	public String getDeputyIndustry() {
		return deputyIndustry;
	}

	public void setDeputyIndustry(String deputyIndustry) {
		this.deputyIndustry = deputyIndustry;
	}
}
