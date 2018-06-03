/** 为导出excel表建立响应的类,携带各列数据 **/
package com.vix.nvix.purchase.action.vo;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.properties.util.MyTool;

/**  库存管理>库存报表>收发存汇总表 **/
public class StockInOutDepositSummaryVo {
	private String ckname;//仓库
	private String itemname;//物料名称
	private String itemcode;//物料编号
	private String specification;//规格
	private String unit;//单位
	private String termStartNum;//期初数量
	private String termStartMoney;//期初金额
	private String inNum;//入库数量
	private String inMoney;//入库金额
	private String outNum;//出库数量
	private String outMoney;//出库金额
	private String thisResultNum;//本期结存数量
	private String thisResultMoney;//本期结存金额
	private String tinvwarehouseid;//仓库id
	private String unitcost;//单价
	public String getCkname() {
		return ckname;
	}
	public void setCkname(String ckname) {
		this.ckname = ckname;
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
	public String getTermStartNum() {
		return termStartNum;
	}
	public void setTermStartNum(String termStartNum) {
		/** 如果不为空就保留2位小数,科学计数时,返回数字 ***/
		if (StringUtils.isNotEmpty(termStartNum)  ){
			termStartNum = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(   termStartNum  ));
		}
		this.termStartNum = termStartNum;
	}
	public String getTermStartMoney() {
		return termStartMoney;
	}
	public void setTermStartMoney(String termStartMoney) {
		this.termStartMoney = termStartMoney;
	}
	public String getInNum() {
		return inNum;
	}
	public void setInNum(String inNum) {
		/** 如果不为空就保留2位小数,科学计数时,返回数字 ***/
		if (StringUtils.isNotEmpty(inNum)  ){
			inNum = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(   inNum  ));
		}
		this.inNum = inNum;
	}
	public String getInMoney() {
		return inMoney;
	}
	public void setInMoney(String inMoney) {
		this.inMoney = inMoney;
	}
	public String getOutNum() {
		return outNum;
	}
	public void setOutNum(String outNum) {
		/** 如果不为空就保留2位小数,科学计数时,返回数字 ***/
		if (StringUtils.isNotEmpty(outNum)  ){
			outNum = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(   outNum  ));
		}
		this.outNum = outNum;
	}
	public String getOutMoney() {
		return outMoney;
	}
	public void setOutMoney(String outMoney) {
		this.outMoney = outMoney;
	}
	public String getThisResultNum() {
		return thisResultNum;
	}
	public void setThisResultNum(String thisResultNum) {
		this.thisResultNum = thisResultNum;
	}
	public String getThisResultMoney() {
		return thisResultMoney;
	}
	public void setThisResultMoney(String thisResultMoney) {
		this.thisResultMoney = thisResultMoney;
	}
	public String getTinvwarehouseid() {
		return tinvwarehouseid;
	}
	public void setTinvwarehouseid(String tinvwarehouseid) {
		this.tinvwarehouseid = tinvwarehouseid;
	}
	public String getUnitcost() {
		return unitcost;
	}
	public void setUnitcost(String unitcost) {
		this.unitcost = unitcost;
	}
	public StockInOutDepositSummaryVo(String ckname, String itemname, String itemcode, String specification,
			String unit, String termStartNum, String termStartMoney, String inNum, String inMoney, String outNum,
			String outMoney, String thisResultNum, String thisResultMoney, String tinvwarehouseid, String unitcost) {
		super();
		this.ckname = ckname;
		this.itemname = itemname;
		this.itemcode = itemcode;
		this.specification = specification;
		this.unit = unit;
		this.termStartNum = termStartNum;
		this.termStartMoney = termStartMoney;
		this.inNum = inNum;
		this.inMoney = inMoney;
		this.outNum = outNum;
		this.outMoney = outMoney;
		this.thisResultNum = thisResultNum;
		this.thisResultMoney = thisResultMoney;
		this.tinvwarehouseid = tinvwarehouseid;
		this.unitcost = unitcost;
	}
	public StockInOutDepositSummaryVo() {
		super();
	}
	@Override
	public String toString() {
		return "StockInOutDepositSummaryVo [ckname=" + ckname + ", itemname=" + itemname + ", itemcode=" + itemcode
				+ ", specification=" + specification + ", unit=" + unit + ", termStartNum=" + termStartNum
				+ ", termStartMoney=" + termStartMoney + ", inNum=" + inNum + ", inMoney=" + inMoney + ", outNum="
				+ outNum + ", outMoney=" + outMoney + ", thisResultNum=" + thisResultNum + ", thisResultMoney="
				+ thisResultMoney + ", tinvwarehouseid=" + tinvwarehouseid + ", unitcost=" + unitcost + "]";
	}
}