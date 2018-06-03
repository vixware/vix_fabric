package com.vix.mdm.bom.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.item.entity.Item;

/** Bom配置节点 */
public class BomNode extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 主题 */
	private String subject;
	/** 子物料编码 */
	private String itemCode;
	/** 子物料名称 */
	private String itemName;
	/** 配置节点类型 */
	private String nodeType;
	/** 父节点编码 */
	private String parentNodeCode;
	/** 数量 */
	private Double amount;
	/** 子件计量单位 */
	private String unit;
	/** 基本用量 */
	private Double commonAmount;
	/** 基础数量 */
	private Double baseAmount;
	/** 使用数量 */
	private Double usedAmount;
	/** 子件阶别 */
	private Integer level;
	/** 废品率 */
	private Double rejectRate;
	/** 材料单价 */
	private Double unitPrice;
	/** 单位材料成本 */
	private Double costOfUnit;
	/** 准备人工 */
	private Double prepareHuman;
	/** 加工人工 */
	private Double processHuman;
	/** 变动制造费用 */
	private Double varProductionFee;
	/** 固定制造费用 */
	private Double fixedProductionFee;
	/** 外协费用 */
	private Double ourSourcingFee;
	/** 子节点集合 */
	private Set<BomNode> subBomNodes = new HashSet<BomNode>();
	/** 产品可选配置项 */
	private Set<ProductOptionConfigItem> productOptionConfigItems = new HashSet<ProductOptionConfigItem>();
	/** 父节点 */
	private BomNode parentBomNode;
	/** Bom结构 */
	private BomStruct bomStruct;
	/** 物料 */
	private Item item;
	
	public BomNode(){}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getParentNodeCode() {
		return parentNodeCode;
	}

	public void setParentNodeCode(String parentNodeCode) {
		this.parentNodeCode = parentNodeCode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getCommonAmount() {
		return commonAmount;
	}

	public void setCommonAmount(Double commonAmount) {
		this.commonAmount = commonAmount;
	}

	public Double getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(Double baseAmount) {
		this.baseAmount = baseAmount;
	}

	public Double getUsedAmount() {
		return usedAmount;
	}

	public void setUsedAmount(Double usedAmount) {
		this.usedAmount = usedAmount;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Double getRejectRate() {
		return rejectRate;
	}

	public void setRejectRate(Double rejectRate) {
		this.rejectRate = rejectRate;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getCostOfUnit() {
		return costOfUnit;
	}

	public void setCostOfUnit(Double costOfUnit) {
		this.costOfUnit = costOfUnit;
	}

	public Double getPrepareHuman() {
		return prepareHuman;
	}

	public void setPrepareHuman(Double prepareHuman) {
		this.prepareHuman = prepareHuman;
	}

	public Double getProcessHuman() {
		return processHuman;
	}

	public void setProcessHuman(Double processHuman) {
		this.processHuman = processHuman;
	}

	public Double getVarProductionFee() {
		return varProductionFee;
	}

	public void setVarProductionFee(Double varProductionFee) {
		this.varProductionFee = varProductionFee;
	}

	public Double getFixedProductionFee() {
		return fixedProductionFee;
	}

	public void setFixedProductionFee(Double fixedProductionFee) {
		this.fixedProductionFee = fixedProductionFee;
	}

	public Double getOurSourcingFee() {
		return ourSourcingFee;
	}

	public void setOurSourcingFee(Double ourSourcingFee) {
		this.ourSourcingFee = ourSourcingFee;
	}

	public Set<BomNode> getSubBomNodes() {
		return subBomNodes;
	}

	public void setSubBomNodes(Set<BomNode> subBomNodes) {
		this.subBomNodes = subBomNodes;
	}

	public Set<ProductOptionConfigItem> getProductOptionConfigItems() {
		return productOptionConfigItems;
	}

	public void setProductOptionConfigItems(
			Set<ProductOptionConfigItem> productOptionConfigItems) {
		this.productOptionConfigItems = productOptionConfigItems;
	}

	public BomNode getParentBomNode() {
		return parentBomNode;
	}
	
	public String getParentBomNodeName() {
		if(parentBomNode != null){
			return parentBomNode.getSubject();
		}
		return "";
	}

	public void setParentBomNode(BomNode parentBomNode) {
		this.parentBomNode = parentBomNode;
	}

	public BomStruct getBomStruct() {
		return bomStruct;
	}
	
	public String getBomStructName() {
		if(bomStruct != null){
			return bomStruct.getConfigItemBomName();
		}
		return "";
	}

	public void setBomStruct(BomStruct bomStruct) {
		this.bomStruct = bomStruct;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
