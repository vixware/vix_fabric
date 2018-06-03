/**
 * 
 */
package com.vix.drp.channel.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.drp.channel.entity.ChannelDistributorSet
 *
 * @author zhanghaibing
 *
 * @date 2016年11月25日
 */
public class ChannelDistributorSet extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 门店内部编码
	 */
	private String innerCode;
	/**
	 * 是否需要同步到门店
	 */
	private String isUpload;

	private String zf_pos_menutype_upload;
	private String zf_pos_menu_upload;
	private String zf_pos_useraccount;
	private String zf_pos_password;

	/**
	 * 是否需要同步到电商
	 */
	private String isUploadToEc;
	private String ec_useraccount_upload;
	private String ec_useraccount;
	private String ec_password;

	private ChannelDistributor channelDistributor;

	public ChannelDistributorSet() {
		super();
	}

	public ChannelDistributorSet(String id) {
		super();
		setId(id);
	}

	public String getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(String isUpload) {
		this.isUpload = isUpload;
	}

	public String getZf_pos_menutype_upload() {
		return zf_pos_menutype_upload;
	}

	public void setZf_pos_menutype_upload(String zf_pos_menutype_upload) {
		this.zf_pos_menutype_upload = zf_pos_menutype_upload;
	}

	public String getZf_pos_menu_upload() {
		return zf_pos_menu_upload;
	}

	public void setZf_pos_menu_upload(String zf_pos_menu_upload) {
		this.zf_pos_menu_upload = zf_pos_menu_upload;
	}

	public String getZf_pos_useraccount() {
		return zf_pos_useraccount;
	}

	public void setZf_pos_useraccount(String zf_pos_useraccount) {
		this.zf_pos_useraccount = zf_pos_useraccount;
	}

	public String getZf_pos_password() {
		return zf_pos_password;
	}

	public void setZf_pos_password(String zf_pos_password) {
		this.zf_pos_password = zf_pos_password;
	}

	public String getIsUploadToEc() {
		return isUploadToEc;
	}

	public void setIsUploadToEc(String isUploadToEc) {
		this.isUploadToEc = isUploadToEc;
	}

	public String getEc_useraccount_upload() {
		return ec_useraccount_upload;
	}

	public void setEc_useraccount_upload(String ec_useraccount_upload) {
		this.ec_useraccount_upload = ec_useraccount_upload;
	}

	public String getEc_useraccount() {
		return ec_useraccount;
	}

	public void setEc_useraccount(String ec_useraccount) {
		this.ec_useraccount = ec_useraccount;
	}

	public String getEc_password() {
		return ec_password;
	}

	public void setEc_password(String ec_password) {
		this.ec_password = ec_password;
	}

	public String getInnerCode() {
		return innerCode;
	}

	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}
