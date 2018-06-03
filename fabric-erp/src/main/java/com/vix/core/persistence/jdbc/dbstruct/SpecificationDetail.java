package com.vix.core.persistence.jdbc.dbstruct;

import com.vix.common.share.entity.BaseEntity;

/**
 * 商品规格明细
 * 
 * @类全名 com.vix.core.persistence.jdbc.dbstruct.SpecificationDetail
 * 
 * @author zhanghaibing
 *
 * @date 2017年12月13日
 */
public class SpecificationDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 规格图片 */
	/** 文件名称 */
	private String imageFileName;
	/** 真实名称 */
	private String imageFileRealName;
	/** 路径 */
	private String imageFilePath;
	/** 商品规格 */
	private Specification specification;

	public SpecificationDetail() {
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageFileRealName() {
		return imageFileRealName;
	}

	public void setImageFileRealName(String imageFileRealName) {
		this.imageFileRealName = imageFileRealName;
	}

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	public String getSpecDetailImagePath() {
		String path = "";
		if (null != imageFilePath && !"".equals(imageFilePath) && null != imageFileRealName && !"".equals(imageFileRealName)) {
			path = imageFilePath + System.getProperty("file.separator") + imageFileRealName;
			path = path.replace("\\", "/");
			path = path.replace("\t", "");
			path = path.replace("\n", "");
			path = path.replace(" ", "");
		}
		return path;
	}

	public Specification getSpecification() {
		return specification;
	}

	public void setSpecification(Specification specification) {
		this.specification = specification;
	}
}
