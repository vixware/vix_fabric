/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 风险评估
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class PMEvaluationRisk extends BaseEntity {
	/** 风险事件 */
	private String riskEvent;
	/** 风险识别方法 */
	private String riskRecognitionMethod;
	/** 风险事件内容 */
	private String riskContent;
	/** 风险量化影响 */
	private String riskQuantizationEffect;
	/** 风险影响水平 */
	private String riskQuantizationLevel;
	/** 风险程度 */
	private String riskDegree;
	/** 分值 */
	private Double value;
	/** 风险准备金 */
	private String riskFund;
	/** 风险对策 */
	private String riskCountermeasures;
	public String getRiskEvent() {
		return riskEvent;
	}
	public void setRiskEvent(String riskEvent) {
		this.riskEvent = riskEvent;
	}
	public String getRiskRecognitionMethod() {
		return riskRecognitionMethod;
	}
	public void setRiskRecognitionMethod(String riskRecognitionMethod) {
		this.riskRecognitionMethod = riskRecognitionMethod;
	}
	public String getRiskContent() {
		return riskContent;
	}
	public void setRiskContent(String riskContent) {
		this.riskContent = riskContent;
	}
	public String getRiskQuantizationEffect() {
		return riskQuantizationEffect;
	}
	public void setRiskQuantizationEffect(String riskQuantizationEffect) {
		this.riskQuantizationEffect = riskQuantizationEffect;
	}
	public String getRiskQuantizationLevel() {
		return riskQuantizationLevel;
	}
	public void setRiskQuantizationLevel(String riskQuantizationLevel) {
		this.riskQuantizationLevel = riskQuantizationLevel;
	}
	public String getRiskDegree() {
		return riskDegree;
	}
	public void setRiskDegree(String riskDegree) {
		this.riskDegree = riskDegree;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getRiskFund() {
		return riskFund;
	}
	public void setRiskFund(String riskFund) {
		this.riskFund = riskFund;
	}
	public String getRiskCountermeasures() {
		return riskCountermeasures;
	}
	public void setRiskCountermeasures(String riskCountermeasures) {
		this.riskCountermeasures = riskCountermeasures;
	}
	
	
}
