package com.vix.inventory.takestock.vo;

import com.vix.core.utils.excel.ExcelVOAttribute;

/**
 * 盘点单需导入的数据
 * 
 * com.vix.inventory.takestock.vo.StockTakingItemVo
 *
 * @author zhanghaibing
 *
 * @date 2014年9月19日
 */
public class StockTakingItemVo {

	/** 货位号 */
	@ExcelVOAttribute(name = "货位号", column = "A", isExport = true, prompt = "货位号必须填写！")
	private String cvposition;

	/** 条码 */
	@ExcelVOAttribute(name = "条码", column = "B", isExport = true, prompt = "条码必须填写！")
	private String itemcode;

	/** 商品名称 */
	@ExcelVOAttribute(name = "商品名称", column = "C", isExport = true, prompt = "商品名称必须填写！")
	private String itemname;

	/** 初盘数量 */
	@ExcelVOAttribute(name = "初盘数量", column = "D", isExport = true, prompt = "初盘数量必须填写！")
	private String cvcquantity;

	public StockTakingItemVo() {
		super();
	}

	/**
	 * @param cvposition
	 * @param itemcode
	 * @param itemname
	 * @param cvcquantity
	 */
	public StockTakingItemVo(String cvposition, String itemcode, String itemname, String cvcquantity) {
		super();
		this.cvposition = cvposition;
		this.itemcode = itemcode;
		this.itemname = itemname;
		this.cvcquantity = cvcquantity;
	}

	public String getCvposition() {
		return cvposition;
	}

	public void setCvposition(String cvposition) {
		this.cvposition = cvposition;
	}

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getCvcquantity() {
		return cvcquantity;
	}

	public void setCvcquantity(String cvcquantity) {
		this.cvcquantity = cvcquantity;
	}

}
