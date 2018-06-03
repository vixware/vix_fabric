package com.vix.sales.delivery.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.item.entity.Item;

/** 销售退货单明细 */
public class SaleReturnFormItem extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 行编码(ID) */
	private String rowID;
	/** 行类型 */
	private String rowTyoe;
	/** 销售订单编号 */
	private String poCode;
	/** 成组编码 */
	private String groupCode;
	/** 销售或者服务内容 */
	private String content;
	/** 物料编码 */
	private String itemCode;
	/** 物料名称 */
	private String itemName;
	/** 物料 */
	private Item item;
	/** 规格型号 */
	private String specification;
	/** 物料类型 */
	private String itemType;
	/** 销售数量 */
	private Double count;
	/** 辅计算量 */
	private Double assistCount;
	/** 计量单位 */
	private String unit;	 
	/** 辅助计量单位 */
	private String assistUnit;
	/** 主辅计量单位换算率 */
	private Double unitExchange;
	/** 税率 */
	private Double tax;
	/** 税额 */
	private Double taxAmount;
	/** 单价 */
	private Double price;
	/** 折扣 */
	private Double discount;
	/** 净单价 */
	private Double netPrice;
	/** 含税单价 */
	private Double taxPrice;
	/** 金额(净单价*数量) */
	private Double netTotal;
	/** 价税合计金额 */
	private Double taxTotal;
	/** 项目 */
	private String project;
	/** 仓库 */
//	private WimWarehouse wareHouse;
	/** 收货仓库 */
//	private WimWarehouse recieveWareHouse;
	/** 仓库组织 */
	private String wareHouseOrg;
	/** 销售退货 */
	private SaleReturnForm saleReturnForm;

	public SaleReturnFormItem(){}

	public String getRowID() {
		return rowID;
	}

	public void setRowID(String rowID) {
		this.rowID = rowID;
	}

	public String getRowTyoe() {
		return rowTyoe;
	}

	public void setRowTyoe(String rowTyoe) {
		this.rowTyoe = rowTyoe;
	}

	public String getPoCode() {
		return poCode;
	}

	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public Double getAssistCount() {
		return assistCount;
	}

	public void setAssistCount(Double assistCount) {
		this.assistCount = assistCount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getAssistUnit() {
		return assistUnit;
	}

	public void setAssistUnit(String assistUnit) {
		this.assistUnit = assistUnit;
	}

	public Double getUnitExchange() {
		return unitExchange;
	}

	public void setUnitExchange(Double unitExchange) {
		this.unitExchange = unitExchange;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(Double netPrice) {
		this.netPrice = netPrice;
	}

	public Double getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(Double taxPrice) {
		this.taxPrice = taxPrice;
	}

	public Double getNetTotal() {
		return netTotal;
	}

	public void setNetTotal(Double netTotal) {
		this.netTotal = netTotal;
	}

	public Double getTaxTotal() {
		return taxTotal;
	}

	public void setTaxTotal(Double taxTotal) {
		this.taxTotal = taxTotal;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getWareHouseOrg() {
		return wareHouseOrg;
	}

	public void setWareHouseOrg(String wareHouseOrg) {
		this.wareHouseOrg = wareHouseOrg;
	}

	public SaleReturnForm getSaleReturnForm() {
		return saleReturnForm;
	}

	public void setSaleReturnForm(SaleReturnForm saleReturnForm) {
		this.saleReturnForm = saleReturnForm;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
