package com.vix.mdm.srm.share.entity;

import com.vix.common.share.entity.BaseEntity;

public class SupplierEvaluationItem extends BaseEntity {

	private static final long serialVersionUID = -1523905668017877139L;
	/** 供应商编码 */
	private String supplierCode;
	/** 供应商名称 */
	private String supplier;
	/** 指标名称 */
	private String name;
	/** 权重 */
	private Float weight;
	/** 分数 */
	private Float value;
	/** 内容 */
	private String content;
	/** 预选与评估 */
	private SupplierEvaluation supplierEvaluation;

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SupplierEvaluation getSupplierEvaluation() {
		return supplierEvaluation;
	}

	public void setSupplierEvaluation(SupplierEvaluation supplierEvaluation) {
		this.supplierEvaluation = supplierEvaluation;
	}

}
