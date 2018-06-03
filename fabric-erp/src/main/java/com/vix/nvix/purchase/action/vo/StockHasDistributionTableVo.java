/** 为导出excel表建立响应的类,携带各列数据 **/
package com.vix.nvix.purchase.action.vo;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.properties.util.MyTool;

/**   导出:库存管理>库存报表>存货分布表 **/
public class StockHasDistributionTableVo {
	private String ckname;//仓库
	private String code;//仓库编号
	private String termStartNum;//期初数量
	private String inNum;//入库数量
	private String outNum;//出库数量
	private String thisResultNum;//本期结存数量
	private String tinvwarehouseid;//仓库id
	public void setTermStartNum(String termStartNum) {
		/** 如果不为空就保留2位小数,科学计数时,返回数字 ***/
		if (StringUtils.isNotEmpty(termStartNum)  ){
			termStartNum = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(   termStartNum  ));
		}
		this.termStartNum = termStartNum;
	}
	public void setInNum(String inNum) {
		/** 如果不为空就保留2位小数,科学计数时,返回数字 ***/
		if (StringUtils.isNotEmpty(inNum)  ){
			inNum = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(   inNum  ));
		}
		this.inNum = inNum;
	}
	public void setOutNum(String outNum) {
		/** 如果不为空就保留2位小数,科学计数时,返回数字 ***/
		if (StringUtils.isNotEmpty(outNum)  ){
			outNum = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(   outNum  ));
		}
		this.outNum = outNum;
	}
	public String getCkname() {
		return ckname;
	}
	public void setCkname(String ckname) {
		this.ckname = ckname;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getThisResultNum() {
		return thisResultNum;
	}
	public void setThisResultNum(String thisResultNum) {
		this.thisResultNum = thisResultNum;
	}
	public String getTinvwarehouseid() {
		return tinvwarehouseid;
	}
	public void setTinvwarehouseid(String tinvwarehouseid) {
		this.tinvwarehouseid = tinvwarehouseid;
	}
	public String getTermStartNum() {
		return termStartNum;
	}
	public String getInNum() {
		return inNum;
	}
	public String getOutNum() {
		return outNum;
	}
	public StockHasDistributionTableVo(String ckname, String code, String termStartNum, String inNum, String outNum,
			String thisResultNum, String tinvwarehouseid) {
		super();
		this.ckname = ckname;
		this.code = code;
		this.termStartNum = termStartNum;
		this.inNum = inNum;
		this.outNum = outNum;
		this.thisResultNum = thisResultNum;
		this.tinvwarehouseid = tinvwarehouseid;
	}
	public StockHasDistributionTableVo() {
		super();
	}
	@Override
	public String toString() {
		return "StockHasDistributionTableVo [ckname=" + ckname + ", code=" + code + ", termStartNum=" + termStartNum
				+ ", inNum=" + inNum + ", outNum=" + outNum + ", thisResultNum=" + thisResultNum + ", tinvwarehouseid="
				+ tinvwarehouseid + "]";
	}
	
}