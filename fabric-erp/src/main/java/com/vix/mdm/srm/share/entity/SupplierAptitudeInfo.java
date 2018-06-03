package com.vix.mdm.srm.share.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 供应商资质
 * 
 * @author Ivan 2013-05-22
 */
public class SupplierAptitudeInfo extends BaseEntity {

	private static final long serialVersionUID = -4919454669720305966L;
	/** 供应商编码 */
	private String supplierCode;
	/** 资质文件 */
	private String files;
	/** 资质有效期 */
	private String validPeriod;
	/** 供应商 */
	private Supplier supplier;

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getValidPeriod() {
		return validPeriod;
	}

	public void setValidPeriod(String validPeriod) {
		this.validPeriod = validPeriod;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}
