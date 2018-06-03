package com.vix.front.fabricAsset.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 区块链 -资产
 *  
 * @author zhanghaibing
 *
 * @date 2017年4月2日
 */
public class FabricAsset  extends BaseEntity  {
	
	private static final long serialVersionUID = 1L;
	/**资产编码*/
	private String assetCode;
	/**资产名称*/
	private String assetName;
	/**所在地点*/
	private String site;
	/**种植面积*/
	private String plantingArea;
	/**取得使用权日期*/
	private Date dateOfAccess;
	/**账面原值*/
	private Double originalValue;
	/**使用类型*/
	private String useType;
	/**非转经或闲置起始日期*/
	private Date inidleTime;
	/**有无协议合同 1:有   0:无*/
	private String hsContract;
	/**非转经年收益*/
	private String annualEarnings;
	/**经营单位或个人名称*/
	private String operation;
	/**地块位置信息*/
	private Set<AssetLocation> assetLocations;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getPlantingArea() {
		return plantingArea;
	}
	public void setPlantingArea(String plantingArea) {
		this.plantingArea = plantingArea;
	}
	public Date getDateOfAccess() {
		return dateOfAccess;
	}
	
	public String getDateOfAccessStr() {
		if(null != dateOfAccess ) {
			return sdf.format(dateOfAccess);
		}
		return null;
	}
	public void setDateOfAccess(Date dateOfAccess) {
		this.dateOfAccess = dateOfAccess;
	}
	public Double getOriginalValue() {
		return originalValue;
	}
	public void setOriginalValue(Double originalValue) {
		this.originalValue = originalValue;
	}
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	}
	public Date getInidleTime() {
		return inidleTime;
	}
	public void setInidleTime(Date inidleTime) {
		this.inidleTime = inidleTime;
	}
	public String getHsContract() {
		return hsContract;
	}
	public void setHsContract(String hsContract) {
		this.hsContract = hsContract;
	}
	
	public String getAnnualEarnings() {
		return annualEarnings;
	}
	public void setAnnualEarnings(String annualEarnings) {
		this.annualEarnings = annualEarnings;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	public Set<AssetLocation> getAssetLocations() {
		return assetLocations;
	}
	public void setAssetLocations(Set<AssetLocation> assetLocations) {
		this.assetLocations = assetLocations;
	}
	
}
