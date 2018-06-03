/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 资源文件
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class ResFile extends BaseEntity {
	/** 文件存储路径 */
	private String path;
	/** 文件类型 */
	private String type;
	/** 关键字 */
	private String keywords;
	/** 摘要 */
	private String digest;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

}
