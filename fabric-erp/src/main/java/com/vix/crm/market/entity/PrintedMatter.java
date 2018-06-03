package com.vix.crm.market.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/** 印刷品 */
public class PrintedMatter extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 名称 */
	private String name;
	/** 印刷量 */
	private Long printedCount;
	/** 总价格 */
	private Double totalPrice;
	/** 库存数量 */
	private Long stockNumber;
	/** 单位 */
	private PrintedMatterUnit printedMatterUnit;
	/** 备注 */
	private String memo;
	/** 印刷商 */
	private String printer;
	/** 接洽人 */
	private String contactPerson;
	/** 联系方式 */
	private String contactStyle;
	/** 印刷品领用 */
	private Set<PrintedRequisition> printedRequisitions = new HashSet<PrintedRequisition>();
	
	public PrintedMatter(){}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public Long getPrintedCount() {
		return printedCount;
	}

	public void setPrintedCount(Long printedCount) {
		this.printedCount = printedCount;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(Long stockNumber) {
		this.stockNumber = stockNumber;
	}

	public PrintedMatterUnit getPrintedMatterUnit() {
		return printedMatterUnit;
	}

	public void setPrintedMatterUnit(PrintedMatterUnit printedMatterUnit) {
		this.printedMatterUnit = printedMatterUnit;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getPrinter() {
		return printer;
	}

	public void setPrinter(String printer) {
		this.printer = printer;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactStyle() {
		return contactStyle;
	}

	public void setContactStyle(String contactStyle) {
		this.contactStyle = contactStyle;
	}

	public Set<PrintedRequisition> getPrintedRequisitions() {
		return printedRequisitions;
	}

	public void setPrintedRequisitions(Set<PrintedRequisition> printedRequisitions) {
		this.printedRequisitions = printedRequisitions;
	}
}
