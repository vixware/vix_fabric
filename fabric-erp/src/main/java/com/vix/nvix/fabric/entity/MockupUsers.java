package com.vix.nvix.fabric.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.nvix.fabric.entity.MockupUsers
 *
 * @date 2018年3月11日
 */
public class MockupUsers extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;// 账号
	private String name;// 名称
	private String passWord;// 密码
	private String cmId;// 编码
	private String acct;// 名称

	public MockupUsers() {
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the passWord
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * @param passWord
	 *            the passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * @return the cmId
	 */
	public String getCmId() {
		return cmId;
	}

	/**
	 * @param cmId
	 *            the cmId to set
	 */
	public void setCmId(String cmId) {
		this.cmId = cmId;
	}

	/**
	 * @return the acct
	 */
	public String getAcct() {
		return acct;
	}

	/**
	 * @param acct
	 *            the acct to set
	 */
	public void setAcct(String acct) {
		this.acct = acct;
	}

}
