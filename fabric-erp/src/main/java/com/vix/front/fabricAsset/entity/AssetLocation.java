package com.vix.front.fabricAsset.entity;

import com.vix.common.share.entity.BaseEntity;

public class AssetLocation  extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private FabricAsset fabricAsset;//资产
	private Double lng; //经度
	private Double lat; //纬度
	private String points;//{经度:"",纬度:""}
	
	public FabricAsset getFabricAsset() {
		return fabricAsset;
	}
	public void setFabricAsset(FabricAsset fabricAsset) {
		this.fabricAsset = fabricAsset;
	}
	
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	
}
