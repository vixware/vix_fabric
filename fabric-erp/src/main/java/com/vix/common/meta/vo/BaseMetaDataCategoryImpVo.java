package com.vix.common.meta.vo;

import com.vix.core.utils.excel.ExcelVOAttribute;

/**
 * 元数据分类导入
 * @author Administrator
 *
 */
public class BaseMetaDataCategoryImpVo {
	
	private static final long serialVersionUID = 1L;
	
	/** 元数据分类编码 */
	@ExcelVOAttribute(name = "元数据分类编码", column = "A", isExport = true, prompt="元数据分类编码必须填写！")
	private String code;
	
	/** 元数据分类名称  */
	@ExcelVOAttribute(name = "元数据分类名称", column = "B", isExport = true, prompt="元数据分类名称必须填写！")
	private String categoryName;

	public BaseMetaDataCategoryImpVo() {
		super();
	}

	public BaseMetaDataCategoryImpVo(String code, String categoryName) {
		super();
		this.code = code;
		this.categoryName = categoryName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
}
