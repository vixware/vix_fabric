package com.vix.mdm.srm.share.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

public class SupplierEvaluation extends BaseEntity {

	private static final long serialVersionUID = -2479889427162099635L;
	/** 供应商编码 */
	private String supplierCode;
	/** 生产能力 */
	private String capacity;
	/** 工艺水平 */
	private String technologicalLevel;
	/** 质量水平 */
	private String qualityLevel;
	/** 价格水平 */
	private String priceLevel;
	/** 历史价格 */
	private String historyPrice;
	/** 质量评价 */
	private String qualityEvaluation;
	/** 退货情况 */
	private String returnGoods;
	/** 拒收情况 */
	private String rejectInfo;
	/** 按时交货率 */
	private String onTimeDelivery;
	/** 数量可靠性 */
	private String reliability;
	/** 是否按规定装运 */
	private String isDeliveredSpecification;
	/** 服务评价 */
	private String serviceEvaluation;
	/** 资质 */
	private Set<SupplierEvaluationItem> supplierEvaluationItems = new HashSet<SupplierEvaluationItem>();

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getTechnologicalLevel() {
		return technologicalLevel;
	}

	public void setTechnologicalLevel(String technologicalLevel) {
		this.technologicalLevel = technologicalLevel;
	}

	public String getQualityLevel() {
		return qualityLevel;
	}

	public void setQualityLevel(String qualityLevel) {
		this.qualityLevel = qualityLevel;
	}

	public String getPriceLevel() {
		return priceLevel;
	}

	public void setPriceLevel(String priceLevel) {
		this.priceLevel = priceLevel;
	}

	public String getHistoryPrice() {
		return historyPrice;
	}

	public void setHistoryPrice(String historyPrice) {
		this.historyPrice = historyPrice;
	}

	public String getQualityEvaluation() {
		return qualityEvaluation;
	}

	public void setQualityEvaluation(String qualityEvaluation) {
		this.qualityEvaluation = qualityEvaluation;
	}

	public String getReturnGoods() {
		return returnGoods;
	}

	public void setReturnGoods(String returnGoods) {
		this.returnGoods = returnGoods;
	}

	public String getRejectInfo() {
		return rejectInfo;
	}

	public void setRejectInfo(String rejectInfo) {
		this.rejectInfo = rejectInfo;
	}

	public String getOnTimeDelivery() {
		return onTimeDelivery;
	}

	public void setOnTimeDelivery(String onTimeDelivery) {
		this.onTimeDelivery = onTimeDelivery;
	}

	public String getReliability() {
		return reliability;
	}

	public void setReliability(String reliability) {
		this.reliability = reliability;
	}

	public String getIsDeliveredSpecification() {
		return isDeliveredSpecification;
	}

	public void setIsDeliveredSpecification(String isDeliveredSpecification) {
		this.isDeliveredSpecification = isDeliveredSpecification;
	}

	public String getServiceEvaluation() {
		return serviceEvaluation;
	}

	public void setServiceEvaluation(String serviceEvaluation) {
		this.serviceEvaluation = serviceEvaluation;
	}

	public Set<SupplierEvaluationItem> getSupplierEvaluationItems() {
		return supplierEvaluationItems;
	}

	public void setSupplierEvaluationItems(
			Set<SupplierEvaluationItem> supplierEvaluationItems) {
		this.supplierEvaluationItems = supplierEvaluationItems;
	}

}
