/**
 * 
 */
package com.vix.nvix.common.base.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 同步电商开关
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.nvix.common.base.entity.VixEcSet
 *
 * @date 2017年12月14日
 */
public class VixEcSet extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 是否需要同步到电商
	 */
	private String isUploadToEc;
	private String ec_useraccount_upload;
	private String ec_useraccount;
	private String ec_password;

	public VixEcSet() {
		super();
	}

	public VixEcSet(String id) {
		super();
		setId(id);
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

}
