package com.vix.mdm.item.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.persistence.jdbc.dbstruct.Specification;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 物料分类
 * 
 * @类全名 com.vix.mdm.item.entity.ItemCatalog
 *
 * @author zhanghaibing
 *
 * @date 2016年9月22日
 */
public class ItemCatalog extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 父类编码 */
	private String parentCode;
	/** 编码规则 */
	private String codeRule;
	/** 预设检验标准 */
	private String preExamineStandard;
	/** 预设采购天数 */
	private Float prePurchaseDays;
	/** 预设制造天数 */
	private Float preProduceDays;
	/** 预设检验天数 */
	private Float preExamineDays;
	/** 预设备料天数 */
	private Float preBackupDays;
	/** 预设库存计量单位 */
	private String preInventoryUnit;
	/** 预设批号控制标识 */
	private String preBatchCode;
	/** 预设虚项标识 */
	private String preVirtualItem;
	/** 预设是否单项发料 */
	private String isSingalItemDelivery;
	/** 预设整包发料标识 */
	private String preWholePackaged;
	/** 预设承认标识 */
	private String preSingal;
	/** 预设循环盘点周期 */
	private Float preCycleCheck;
	/** 预设损耗率 */
	private Float preAttritionRate;
	/** 预设损耗发料标识 */
	private String preAttritionDelivery;
	/** 预设海关商品编码 */
	private String preCustomhouseProductCode;
	/** 物料 */
	private Set<Item> items = new HashSet<Item>();
	/** 子分类集合 */
	private Set<ItemCatalog> subItemCatalogs = new HashSet<ItemCatalog>();
	/** 父分类 */
	private ItemCatalog parentItemCatalog;
	/** 业务模块标识，用于标识不同模块人员创建的分类 */
	private String businessModelTag;

	/** 规格表是否已生成 0：未生成 1： 已生成 */
	private String specTableIsGenerate;
	/** 扩展表 */
	private String expandTableName;
	private String flag;// 是否已经同步到门店0,未同步; 1,已同步
	private Set<ChannelDistributor> subChannelDistributors = new HashSet<ChannelDistributor>();
	private Set<Specification> specifications = new HashSet<Specification>();
	public ItemCatalog() {
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getCodeRule() {
		return codeRule;
	}

	public void setCodeRule(String codeRule) {
		this.codeRule = codeRule;
	}

	public String getPreExamineStandard() {
		return preExamineStandard;
	}

	public void setPreExamineStandard(String preExamineStandard) {
		this.preExamineStandard = preExamineStandard;
	}

	public Float getPrePurchaseDays() {
		return prePurchaseDays;
	}

	public void setPrePurchaseDays(Float prePurchaseDays) {
		this.prePurchaseDays = prePurchaseDays;
	}

	public Float getPreProduceDays() {
		return preProduceDays;
	}

	public void setPreProduceDays(Float preProduceDays) {
		this.preProduceDays = preProduceDays;
	}

	public Float getPreExamineDays() {
		return preExamineDays;
	}

	public void setPreExamineDays(Float preExamineDays) {
		this.preExamineDays = preExamineDays;
	}

	public Float getPreBackupDays() {
		return preBackupDays;
	}

	public void setPreBackupDays(Float preBackupDays) {
		this.preBackupDays = preBackupDays;
	}

	public String getPreInventoryUnit() {
		return preInventoryUnit;
	}

	public void setPreInventoryUnit(String preInventoryUnit) {
		this.preInventoryUnit = preInventoryUnit;
	}

	public String getPreBatchCode() {
		return preBatchCode;
	}

	public void setPreBatchCode(String preBatchCode) {
		this.preBatchCode = preBatchCode;
	}

	public String getPreVirtualItem() {
		return preVirtualItem;
	}

	public void setPreVirtualItem(String preVirtualItem) {
		this.preVirtualItem = preVirtualItem;
	}

	public String getIsSingalItemDelivery() {
		return isSingalItemDelivery;
	}

	public void setIsSingalItemDelivery(String isSingalItemDelivery) {
		this.isSingalItemDelivery = isSingalItemDelivery;
	}

	public String getPreWholePackaged() {
		return preWholePackaged;
	}

	public void setPreWholePackaged(String preWholePackaged) {
		this.preWholePackaged = preWholePackaged;
	}

	public String getPreSingal() {
		return preSingal;
	}

	public void setPreSingal(String preSingal) {
		this.preSingal = preSingal;
	}

	public Float getPreCycleCheck() {
		return preCycleCheck;
	}

	public void setPreCycleCheck(Float preCycleCheck) {
		this.preCycleCheck = preCycleCheck;
	}

	public Float getPreAttritionRate() {
		return preAttritionRate;
	}

	public void setPreAttritionRate(Float preAttritionRate) {
		this.preAttritionRate = preAttritionRate;
	}

	public String getPreAttritionDelivery() {
		return preAttritionDelivery;
	}

	public void setPreAttritionDelivery(String preAttritionDelivery) {
		this.preAttritionDelivery = preAttritionDelivery;
	}

	public String getPreCustomhouseProductCode() {
		return preCustomhouseProductCode;
	}

	public void setPreCustomhouseProductCode(String preCustomhouseProductCode) {
		this.preCustomhouseProductCode = preCustomhouseProductCode;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public Set<ItemCatalog> getSubItemCatalogs() {
		return subItemCatalogs;
	}

	public void setSubItemCatalogs(Set<ItemCatalog> subItemCatalogs) {
		this.subItemCatalogs = subItemCatalogs;
	}

	public ItemCatalog getParentItemCatalog() {
		return parentItemCatalog;
	}

	public void setParentItemCatalog(ItemCatalog parentItemCatalog) {
		this.parentItemCatalog = parentItemCatalog;
	}

	public String getBusinessModelTag() {
		return businessModelTag;
	}

	public void setBusinessModelTag(String businessModelTag) {
		this.businessModelTag = businessModelTag;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Set<ChannelDistributor> getSubChannelDistributors() {
		return subChannelDistributors;
	}

	public void setSubChannelDistributors(Set<ChannelDistributor> subChannelDistributors) {
		this.subChannelDistributors = subChannelDistributors;
	}

	public Set<Specification> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(Set<Specification> specifications) {
		this.specifications = specifications;
	}

	public String getSpecTableIsGenerate() {
		return specTableIsGenerate;
	}

	public void setSpecTableIsGenerate(String specTableIsGenerate) {
		this.specTableIsGenerate = specTableIsGenerate;
	}

	public String getExpandTableName() {
		return expandTableName;
	}

	public void setExpandTableName(String expandTableName) {
		this.expandTableName = expandTableName;
	}
}
