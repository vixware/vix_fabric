package com.vix.common.properties.util;
/**  TableBeanE 为表格样式的统计表封装数据  E代表可以封装5列数据 **/   //column纵队，列
public class TableBeanE {
	/** column纵队，列 colA 第1列数据 **/
	private String colA="";
	/** 第2列数据  **/
	private String colB="";
	/** 第3列数据  **/
	private String colC="";
	/** 第4列数据  **/
	private String colD="";
	/** 第5列数据  **/
	private String colE="";
	public String getColA() {
		return colA;
	}
	public void setColA(String colA) {
		this.colA = colA;
	}
	public String getColB() {
		return colB;
	}
	public void setColB(String colB) {
		this.colB = colB;
	}
	public String getColC() {
		return colC;
	}
	public void setColC(String colC) {
		this.colC = colC;
	}
	public String getColD() {
		return colD;
	}
	public void setColD(String colD) {
		this.colD = colD;
	}
	public String getColE() {
		return colE;
	}
	public void setColE(String colE) {
		this.colE = colE;
	}
	public TableBeanE(String colA, String colB, String colC, String colD,String colE) {
		super();
		this.colA = colA;
		this.colB = colB;
		this.colC = colC;
		this.colD = colD;
		this.colE = colE;
	}
	public TableBeanE() {
		super();
	}
	@Override
	public String toString() {
		return "TableBeanE [colA=" + colA + ", colB=" + colB + ", colC=" + colC
				+ ", colD=" + colD + ", colE=" + colE + "]";
	}
}
