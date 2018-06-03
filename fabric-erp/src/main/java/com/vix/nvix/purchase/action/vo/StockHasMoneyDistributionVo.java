/** 为导出excel表建立响应的类,携带各列数据 **/
package com.vix.nvix.purchase.action.vo;

/** StockHasMoneyDistributionVo 库存管理>库存报表>现存物料价值分布表 列表查询 **/
public class StockHasMoneyDistributionVo {
	private String topStr;//排行
	private String itemname;//物料名称
	private String itemcode;//物料编号
	private String price;//单价
	private String avaquantity;//可用数量
	private String value;//价值
	private String proportion;//占比
	private String spareOne;//备用字符串1
	public String getTopStr() {
		return topStr;
	}
	public void setTopStr(String topStr) {
		this.topStr = topStr;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAvaquantity() {
		return avaquantity;
	}
	public void setAvaquantity(String avaquantity) {
		this.avaquantity = avaquantity;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getProportion() {
		return proportion;
	}
	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	public String getSpareOne() {
		return spareOne;
	}
	public void setSpareOne(String spareOne) {
		this.spareOne = spareOne;
	}
	public StockHasMoneyDistributionVo(String topStr, String itemname, String itemcode, String price,
			String avaquantity, String value, String proportion, String spareOne) {
		super();
		this.topStr = topStr;
		this.itemname = itemname;
		this.itemcode = itemcode;
		this.price = price;
		this.avaquantity = avaquantity;
		this.value = value;
		this.proportion = proportion;
		this.spareOne = spareOne;
	}
	public StockHasMoneyDistributionVo() {
		super();
	}
	@Override
	public String toString() {
		return "StockHasMoneyDistributionVo [topStr=" + topStr + ", itemname=" + itemname + ", itemcode=" + itemcode
				+ ", price=" + price + ", avaquantity=" + avaquantity + ", value=" + value + ", proportion="
				+ proportion + ", spareOne=" + spareOne + "]";
	}
}