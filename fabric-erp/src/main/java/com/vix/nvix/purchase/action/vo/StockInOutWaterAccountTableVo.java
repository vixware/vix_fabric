/** 为导出excel表建立响应的类,携带各列数据 **/
package com.vix.nvix.purchase.action.vo;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.properties.util.MyTool;

/** PurchaseStrorageDetailsVo 采购智能分析>入库明细:入库明细表 **/
public class StockInOutWaterAccountTableVo {
	private String createtimetimestr;// 时间
	private String code;//订单号 
	private String flag;//标志  出库,入库
	private String itemname;//物料  
	private String itemcode;//物料编号
	private String specification;//规格	
	private String unit;//单位
	private String unitcost;//单价	
	private String quantity;//数量	
	private String price;//金额
	private String ckname;//仓库名称
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		/** 如果不为空就保留2位小数,科学计数时,返回数字 ***/
		if (StringUtils.isNotEmpty(price)  ){
			price = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(   price  ));
		}
		this.price = price;
	}
	public void setQuantity(String quantity) {
		/** 如果不为空就保留2位小数,科学计数时,返回数字 ***/
		if (StringUtils.isNotEmpty(quantity)  ){
			quantity = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(   quantity  ));
		}
		this.quantity = quantity;
	}
	public void setUnitcost(String unitcost) {
		/** 如果不为空就保留2位小数,科学计数时,返回数字 ***/
		if (StringUtils.isNotEmpty(unitcost)  ){
			unitcost = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(   unitcost  ));
		}
		this.unitcost = unitcost;
	}
	public String getCreatetimetimestr() {
		return createtimetimestr;
	}
	public void setCreatetimetimestr(String createtimetimestr) {
		this.createtimetimestr = createtimetimestr;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		if (StringUtils.isNotEmpty(flag)  && "1".equals(flag)  ){
			flag = "入库";
		}else if (StringUtils.isNotEmpty(flag)  && "2".equals(flag)  ){
			flag = "出库";
		}
		this.flag = flag;
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
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnitcost() {
		return unitcost;
	}
	public String getQuantity() {
		return quantity;
	}
	public String getCkname() {
		return ckname;
	}
	public void setCkname(String ckname) {
		this.ckname = ckname;
	}
	@Override
	public String toString() {
		return "StockInOutWaterAccountTableVo [createtimetimestr=" + createtimetimestr + ", code=" + code + ", flag="
				+ flag + ", itemname=" + itemname + ", itemcode=" + itemcode + ", specification=" + specification
				+ ", unit=" + unit + ", unitcost=" + unitcost + ", quantity=" + quantity + ", price=" + price
				+ ", ckname=" + ckname + "]";
	}
}