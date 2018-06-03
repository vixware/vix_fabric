package com.vix.mdm.item.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.Regional;
import com.vix.crm.customer.entity.CustomerAccountCategory;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.crm.entity.CustomerAccountGroup;
import com.vix.mdm.srm.share.entity.Supplier;

/** 
 * 数量区间
 * @author Administrator
 *
 */
public class PriceConditionCountArea extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 定价条件 */
	private PriceCondition priceCondition;
	/** 条件类型 */
	private String conditionType;
	/** 客户分类 */
	private CustomerAccountCategory customerAccountCategory;
	/** 物料分类 */
	private ItemCatalog itemCatalog;
	/** 客户组 */
	private CustomerAccountGroup customerAccountGroup;
	/** 物料组 */
	private ItemGroup itemGroup;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 物料 */
	private Item item;
	private StoreItem storeItem;
	/** 地域 */
	private Regional regional;
	/** 分销商 */
	private ChannelDistributor channelDistributor;
	/** 供应商 */
	private Supplier supplier;
	
	/** 开始数量 */
	private Double startCount;
	/** 结束数量 */
	private Double endCount;
	/** 单位 */
	private String unit;
	/** 区间价格类型 all:全部，price:价格，discount:折扣 */
	private String areaPriceType;
	/** 价格  */
	private Double price;
	/** 折扣 */
	private Double discount;
	/** 备注 */
	private String memo;
	/** 赠品 */
	private Set<PriceConditionGift> priceConditionGifts = new HashSet<PriceConditionGift>();
	
	public PriceConditionCountArea(){}

	public PriceCondition getPriceCondition() {
		return priceCondition;
	}

	public void setPriceCondition(PriceCondition priceCondition) {
		this.priceCondition = priceCondition;
	}

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	public CustomerAccountCategory getCustomerAccountCategory() {
		return customerAccountCategory;
	}

	public void setCustomerAccountCategory(
			CustomerAccountCategory customerAccountCategory) {
		this.customerAccountCategory = customerAccountCategory;
	}

	public ItemCatalog getItemCatalog() {
		return itemCatalog;
	}

	public void setItemCatalog(ItemCatalog itemCatalog) {
		this.itemCatalog = itemCatalog;
	}

	public CustomerAccountGroup getCustomerAccountGroup() {
		return customerAccountGroup;
	}

	public void setCustomerAccountGroup(CustomerAccountGroup customerAccountGroup) {
		this.customerAccountGroup = customerAccountGroup;
	}

	public ItemGroup getItemGroup() {
		return itemGroup;
	}

	public void setItemGroup(ItemGroup itemGroup) {
		this.itemGroup = itemGroup;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}
	
	public String getCustomerAccountName() {
		if(customerAccount != null){
			return customerAccount.getName();
		}
		return "";
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public Item getItem() {
		return item;
	}
	
	public String getItemName() {
		if(item != null){
			return item.getName();
		}
		return "";
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Regional getRegional() {
		return regional;
	}
	
	public String getRegionalName() {
		if(regional != null){
			return regional.getName();
		}
		return "全";
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}
	
	public String getChannelDistributorName() {
		if(channelDistributor != null){
			return channelDistributor.getName();
		}
		return "";
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public Supplier getSupplier() {
		return supplier;
	}
	
	public String getSupplierName() {
		if(supplier != null){
			return supplier.getName();
		}
		return "";
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Double getStartCount() {
		return startCount;
	}

	public void setStartCount(Double startCount) {
		this.startCount = startCount;
	}

	public Double getEndCount() {
		return endCount;
	}

	public void setEndCount(Double endCount) {
		this.endCount = endCount;
	}

	public String getUnit() {
		return unit;
	}

	public String getAreaPriceType() {
		if(null == areaPriceType || "".equals(areaPriceType)){
			return "all";
		}
		return areaPriceType;
	}

	public void setAreaPriceType(String areaPriceType) {
		this.areaPriceType = areaPriceType;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Set<PriceConditionGift> getPriceConditionGifts() {
		return priceConditionGifts;
	}

	public void setPriceConditionGifts(Set<PriceConditionGift> priceConditionGifts) {
		this.priceConditionGifts = priceConditionGifts;
	}

	public StoreItem getStoreItem() {
		return storeItem;
	}
	
	public String getStoreItemName() {
		if(storeItem != null){
			return storeItem.getName();
		}
		return "";
	}

	public void setStoreItem(StoreItem storeItem) {
		this.storeItem = storeItem;
	}
}
