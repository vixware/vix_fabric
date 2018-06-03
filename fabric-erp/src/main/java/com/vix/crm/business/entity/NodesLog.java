package com.vix.crm.business.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 节点日志 com.vix.crm.business.entity.NodesLog
 *
 * @author zhanghaibing
 *
 * @date 2015年1月19日
 */
public class NodesLog extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 日志内容
	 */
	private String logContent;

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

}
