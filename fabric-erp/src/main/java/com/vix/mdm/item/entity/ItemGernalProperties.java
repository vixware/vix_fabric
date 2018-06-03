package com.vix.mdm.item.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/** 物料(产品)基本属性 */
public class ItemGernalProperties extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 物料编码 */
	private String itemCode;
	/** 物料类型 */
	private String itemType;
	/** 重量计量组 */
	private String weightMensurationGroup;
	/** 重量单位 */
	private String weightUnit;
	/** 净重 */
	private Double netWeight;
	/** 毛重 */
	private Double grossWeight;
	/** 体积计量组 */
	private String volumeMensurationGroup;
	/** 体积单位 */
	private String volumeUnit;
	/** 长(cm) */
	private Double lenth;
	/** 宽(cm) */
	private Double width;
	/** 高(cm) */
	private Double height;
	/** 单位体积 */
	private Double unitVolume;
	/** 批注文号 */
	private String referenceNumber;
	/** 包装规格 */
	private String packageSpecs;
	/** 合格证号 */
	private String certificateOfApprovalNumber;
	/** 注册商标 */
	private String registerBrand;
	/** 入关证号 */
	private String enterCustomsNumber;
	/** 许可证号 */
	private String licenseNumber;
	/** 专利名称 */
	private String patentName;
	/** 国际非专利名 */
	private String nonPatentName;
	/** 质量要求 */
	private String qualityRequirement;
	/** 所属权限组 */
	private String rightGroup;
	/** 推荐人 */
	private String referrer;
	/** 变更人 */
	private String fchanger;
	/** 变更时间 */
	private Date changeTime;
	/** 物料 */
	private Item item;
	
	public ItemGernalProperties(){}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getWeightMensurationGroup() {
		return weightMensurationGroup;
	}

	public void setWeightMensurationGroup(String weightMensurationGroup) {
		this.weightMensurationGroup = weightMensurationGroup;
	}

	public String getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public String getVolumeMensurationGroup() {
		return volumeMensurationGroup;
	}

	public void setVolumeMensurationGroup(String volumeMensurationGroup) {
		this.volumeMensurationGroup = volumeMensurationGroup;
	}

	public String getVolumeUnit() {
		return volumeUnit;
	}

	public void setVolumeUnit(String volumeUnit) {
		this.volumeUnit = volumeUnit;
	}

	public Double getLenth() {
		return lenth;
	}

	public void setLenth(Double lenth) {
		this.lenth = lenth;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getUnitVolume() {
		return unitVolume;
	}

	public void setUnitVolume(Double unitVolume) {
		this.unitVolume = unitVolume;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getPackageSpecs() {
		return packageSpecs;
	}

	public void setPackageSpecs(String packageSpecs) {
		this.packageSpecs = packageSpecs;
	}

	public String getCertificateOfApprovalNumber() {
		return certificateOfApprovalNumber;
	}

	public void setCertificateOfApprovalNumber(String certificateOfApprovalNumber) {
		this.certificateOfApprovalNumber = certificateOfApprovalNumber;
	}

	public String getRegisterBrand() {
		return registerBrand;
	}

	public void setRegisterBrand(String registerBrand) {
		this.registerBrand = registerBrand;
	}

	public String getEnterCustomsNumber() {
		return enterCustomsNumber;
	}

	public void setEnterCustomsNumber(String enterCustomsNumber) {
		this.enterCustomsNumber = enterCustomsNumber;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getPatentName() {
		return patentName;
	}

	public void setPatentName(String patentName) {
		this.patentName = patentName;
	}

	public String getNonPatentName() {
		return nonPatentName;
	}

	public void setNonPatentName(String nonPatentName) {
		this.nonPatentName = nonPatentName;
	}

	public String getQualityRequirement() {
		return qualityRequirement;
	}

	public void setQualityRequirement(String qualityRequirement) {
		this.qualityRequirement = qualityRequirement;
	}

	public String getRightGroup() {
		return rightGroup;
	}

	public void setRightGroup(String rightGroup) {
		this.rightGroup = rightGroup;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public String getFchanger() {
		return fchanger;
	}

	public void setFchanger(String fchanger) {
		this.fchanger = fchanger;
	}

	public Date getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
