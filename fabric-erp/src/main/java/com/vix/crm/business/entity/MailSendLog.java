package com.vix.crm.business.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * com.vix.crm.business.entity.MailSendLog
 *
 * @author bjitzhang
 *
 * @date 2015年6月12日
 */
public class MailSendLog extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProcessLog processLog;

	public ProcessLog getProcessLog() {
		return processLog;
	}

	public void setProcessLog(ProcessLog processLog) {
		this.processLog = processLog;
	}

}
