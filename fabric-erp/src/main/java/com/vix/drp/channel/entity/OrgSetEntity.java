/**
 * 
 */
package com.vix.drp.channel.entity;

import com.vix.common.org.entity.Organization;
import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.drp.channel.entity.OrgSetEntity
 *
 * @author zhanghaibing
 *
 * @date 2016年12月17日
 */
public class OrgSetEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 是否需要同步到电商
	 */
	private String isUploadToEc;
	private String ec_item_upload;
	private String ec_useraccount;
	private String ec_password;

	private Organization organization;

	public OrgSetEntity() {
		super();
	}

	public OrgSetEntity(String id) {
		super();
		setId(id);
	}

	public String getIsUploadToEc() {
		return isUploadToEc;
	}

	public void setIsUploadToEc(String isUploadToEc) {
		this.isUploadToEc = isUploadToEc;
	}

	public String getEc_item_upload() {
		return ec_item_upload;
	}

	public void setEc_item_upload(String ec_item_upload) {
		this.ec_item_upload = ec_item_upload;
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

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
