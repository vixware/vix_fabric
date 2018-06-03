package com.vix.common.orginialMeta.vo;

import com.vix.core.utils.excel.ExcelVOAttribute;

/**
 * 元数据对象导入
 * @author Administrator
 *
 */
public class OrginialBaseMetaDataImpVo {
	
	private static final long serialVersionUID = 1L;
	
	/** 元数据对象所属分类编码 */
	@ExcelVOAttribute(name = "元数据对象所属分类编码", column = "A", isExport = true, prompt="元数据对象所属分类编码必须填写！")
	private String baseMetaDataCategoryCode;
	
	/** 元数据对象名称  */
	@ExcelVOAttribute(name = "元数据对象显示名称", column = "B", isExport = true, prompt="元数据对象名称必须填写！")
	private String metaDataName;
	
	
	/** 元数据对象显示名称  */
	@ExcelVOAttribute(name = "元数据对象显示名称", column = "C", isExport = true, prompt="元数据对象显示名称必须填写！")
	private String metaDataDisplayName;
	
	/** 元数据对象编码 */
	@ExcelVOAttribute(name = "元数据对象编码", column = "D", isExport = true, prompt="元数据对象编码必须填写！")
	private String code;

	public OrginialBaseMetaDataImpVo() {
		super();
	}

	public OrginialBaseMetaDataImpVo(String baseMetaDataCategoryCode,
			String metaDataName, String metaDataDisplayName, String code) {
		super();
		this.baseMetaDataCategoryCode = baseMetaDataCategoryCode;
		this.metaDataName = metaDataName;
		this.metaDataDisplayName = metaDataDisplayName;
		this.code = code;
	}

	public String getBaseMetaDataCategoryCode() {
		return baseMetaDataCategoryCode;
	}

	public void setBaseMetaDataCategoryCode(String baseMetaDataCategoryCode) {
		this.baseMetaDataCategoryCode = baseMetaDataCategoryCode;
	}

	public String getMetaDataName() {
		return metaDataName;
	}

	public void setMetaDataName(String metaDataName) {
		this.metaDataName = metaDataName;
	}

	public String getMetaDataDisplayName() {
		return metaDataDisplayName;
	}

	public void setMetaDataDisplayName(String metaDataDisplayName) {
		this.metaDataDisplayName = metaDataDisplayName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
