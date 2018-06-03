package com.vix.inventory.standingbook.vo;

import com.vix.core.utils.excel.ExcelVOAttribute;

public class ItemVo {

	/** 商品编码 */
	@ExcelVOAttribute(name = "商品编码", column = "A", isExport = true, prompt = "商品编码必须填写！")
	private String itemCode;
	/** 商品名称 */
	@ExcelVOAttribute(name = "商品名称", column = "B", isExport = true, prompt = "商品名称必须填写！")
	private String itemName;
	/** 价格 */
	@ExcelVOAttribute(name = "价格", column = "C", isExport = true, prompt = "价格必须填写！")
	private String itemPrice;

	public ItemVo() {
	}
}
