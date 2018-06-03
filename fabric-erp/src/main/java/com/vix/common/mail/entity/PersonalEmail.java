package com.vix.common.mail.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 个人邮箱信息
 * 
 * @author zhanghaibing
 * 
 * @date 2014-5-14
 */
public class PersonalEmail extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** email 地址 */
	private String emailAddress;
	/** email 账户 */
	private String emailUserName;
	/** email 密码 */
	private String emailPassword;
	/**
	 * 使用协议
	 */
	private String mailServerHost;
	/**
	 * 是否默认
	 */
	private String isDefault;
	/**
	 * 发送S 接收 R
	 */
	private String type;
	/** email 的服务器 配置 */
	private MailServer mailServer;
	/** 邮件 */
	private Set<MailInfo> mailInfos;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmailUserName() {
		return emailUserName;
	}

	public void setEmailUserName(String emailUserName) {
		this.emailUserName = emailUserName;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public MailServer getMailServer() {
		return mailServer;
	}

	public void setMailServer(MailServer mailServer) {
		this.mailServer = mailServer;
	}

	public Set<MailInfo> getMailInfos() {
		return mailInfos;
	}

	public void setMailInfos(Set<MailInfo> mailInfos) {
		this.mailInfos = mailInfos;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
