package com.vix.common.mail.entity;

import com.vix.common.share.entity.BaseEntity;

public class MailServer extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** server 名称 */
	private String serverName;

	/** smtp 设定 */
	private String smtpServerName;
	private Boolean smtpRequireSSL;
	/** smtp端口 */
	private String smtpPort;

	/**
	 * 接收使用协议 pop3 imap
	 */
	private String receiveProtocol;
	/** pop3设定 */
	private String pop3ServerName;
	private Boolean pop3RequireSSL;
	/** pop3端口 */
	private String pop3Port;

	/** imap设定 */
	private String imapServerName;
	private Boolean imapRequireSSL;
	/** imap端口 */
	private String imapPort;

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getSmtpServerName() {
		return smtpServerName;
	}

	public void setSmtpServerName(String smtpServerName) {
		this.smtpServerName = smtpServerName;
	}

	public Boolean getSmtpRequireSSL() {
		return smtpRequireSSL;
	}

	public void setSmtpRequireSSL(Boolean smtpRequireSSL) {
		this.smtpRequireSSL = smtpRequireSSL;
	}

	public String getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getPop3ServerName() {
		return pop3ServerName;
	}

	public void setPop3ServerName(String pop3ServerName) {
		this.pop3ServerName = pop3ServerName;
	}

	public Boolean getPop3RequireSSL() {
		return pop3RequireSSL;
	}

	public void setPop3RequireSSL(Boolean pop3RequireSSL) {
		this.pop3RequireSSL = pop3RequireSSL;
	}

	public String getPop3Port() {
		return pop3Port;
	}

	public void setPop3Port(String pop3Port) {
		this.pop3Port = pop3Port;
	}

	public String getImapServerName() {
		return imapServerName;
	}

	public void setImapServerName(String imapServerName) {
		this.imapServerName = imapServerName;
	}

	public Boolean getImapRequireSSL() {
		return imapRequireSSL;
	}

	public void setImapRequireSSL(Boolean imapRequireSSL) {
		this.imapRequireSSL = imapRequireSSL;
	}

	public String getImapPort() {
		return imapPort;
	}

	public void setImapPort(String imapPort) {
		this.imapPort = imapPort;
	}

	public String getReceiveProtocol() {
		return receiveProtocol;
	}

	public void setReceiveProtocol(String receiveProtocol) {
		this.receiveProtocol = receiveProtocol;
	}

}
