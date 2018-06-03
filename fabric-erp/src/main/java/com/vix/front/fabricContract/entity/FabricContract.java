package com.vix.front.fabricContract.entity;
import com.vix.common.share.entity.BaseEntity;

/**
 * 区块链 -合约
 *  
 * @author zhanghaibing
 *
 * @date 2017年4月2日
 */
public class FabricContract  extends BaseEntity  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**编码*/
	private String contractCode;
	/**标题*/
	private String title;
	/**经营人*/
	private String operator;
	/**种植面积*/
	private String plantingArea;
	/**预计产量*/
	private String  estimatedOutput;
	/**预订价格*/
	private Double predeterminedPrice;
	/**现货价格*/
	private Double spotPrice;
	/**预付定金*/
	private Double prepaidDeposit;
	/**交易模式*/
	private String tradingMode;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getPlantingArea() {
		return plantingArea;
	}
	public void setPlantingArea(String plantingArea) {
		this.plantingArea = plantingArea;
	}
	public String getEstimatedOutput() {
		return estimatedOutput;
	}
	public void setEstimatedOutput(String estimatedOutput) {
		this.estimatedOutput = estimatedOutput;
	}
	public Double getPredeterminedPrice() {
		return predeterminedPrice;
	}
	public void setPredeterminedPrice(Double predeterminedPrice) {
		this.predeterminedPrice = predeterminedPrice;
	}
	public Double getSpotPrice() {
		return spotPrice;
	}
	public void setSpotPrice(Double spotPrice) {
		this.spotPrice = spotPrice;
	}
	public Double getPrepaidDeposit() {
		return prepaidDeposit;
	}
	public void setPrepaidDeposit(Double prepaidDeposit) {
		this.prepaidDeposit = prepaidDeposit;
	}
	public String getTradingMode() {
		return tradingMode;
	}
	public void setTradingMode(String tradingMode) {
		this.tradingMode = tradingMode;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	
}
