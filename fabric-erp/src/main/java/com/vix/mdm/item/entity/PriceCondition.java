package com.vix.mdm.item.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseBOEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.TaxRate;
import com.vix.core.utils.DateUtil;

/** 价格条件
 * @author Administrator
 *
 */
public class PriceCondition extends BaseBOEntity {

	private static final long serialVersionUID = 1L;
	
	/** 销售组织 */
	private OrganizationUnit saleOrg;
	/** 币种 */
	private CurrencyType currencyType;
	/** 税率 */
//	private Double defaultTax;
	/** 开始有效时间 */
	private Date startEffectiveTime;
	/** 结束有效时间 */
	private Date endEffectiveTime;
	/** 是否为临时对象 */
	private String isTemp;
	/** 定价条件类型 */
	private String priceConditionType;
	/** 是否启用  */
	private String enable;
	/** 条件规则 */
	private Set<PriceConditionCountArea> priceConditionCountAreas = new HashSet<PriceConditionCountArea>();
	/** 条件规则 */
	private Set<PriceConditionPriceArea> priceConditionPriceAreas = new HashSet<PriceConditionPriceArea>();
	private TaxRate taxRate;
	public PriceCondition(){}

	public OrganizationUnit getSaleOrg() {
		return saleOrg;
	}
	
	public String getSaleOrgFullName() {
		if(saleOrg != null){
			return saleOrg.getFullName();
		}
		return "";
	}

	public void setSaleOrg(OrganizationUnit saleOrg) {
		this.saleOrg = saleOrg;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public TaxRate getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(TaxRate taxRate) {
		this.taxRate = taxRate;
	}

	public Date getStartEffectiveTime() {
		return startEffectiveTime;
	}
	
	public String getStartEffectiveTimeStr(){
		if(null != startEffectiveTime){
			return DateUtil.format(startEffectiveTime);
		}
		return "";
	}

	public void setStartEffectiveTime(Date startEffectiveTime) {
		this.startEffectiveTime = startEffectiveTime;
	}

	public Date getEndEffectiveTime() {
		return endEffectiveTime;
	}
	
	public String  getEndEffectiveTimeStr(){
		if(null != endEffectiveTime){
			return DateUtil.format(endEffectiveTime);
		}
		return "";
	}

	public void setEndEffectiveTime(Date endEffectiveTime) {
		this.endEffectiveTime = endEffectiveTime;
	}

	@Override
	public String getIsTemp() {
		return isTemp;
	}

	@Override
	public void setIsTemp(String isTemp) {
		this.isTemp = isTemp;
	}

	public String getPriceConditionType() {
		return priceConditionType;
	}

	public void setPriceConditionType(String priceConditionType) {
		this.priceConditionType = priceConditionType;
	}

	public Set<PriceConditionCountArea> getPriceConditionCountAreas() {
		return priceConditionCountAreas;
	}

	public void setPriceConditionCountAreas(
			Set<PriceConditionCountArea> priceConditionCountAreas) {
		this.priceConditionCountAreas = priceConditionCountAreas;
	}

	public Set<PriceConditionPriceArea> getPriceConditionPriceAreas() {
		return priceConditionPriceAreas;
	}

	public void setPriceConditionPriceAreas(
			Set<PriceConditionPriceArea> priceConditionPriceAreas) {
		this.priceConditionPriceAreas = priceConditionPriceAreas;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}
}
