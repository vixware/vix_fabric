/**
 * 
 */
package com.vix.inventory.batch.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.system.billTypeManagement.entity.BillsType;

/**
 * 批次号规则表
 * 
 * 
 * @author zhanghaibing
 * 
 * @date 2013-7-19
 */
public class BaseCoder extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号编码/前缀
	 */
	private String sequenceID;
	/**
	 * 序号
	 */
	private Long initValue;
	/**
	 * 编码长度
	 */
	private Integer codeLength;
	/**
	 * 启用级数
	 */
	private Integer enableSeries;
	/**
	 * 一级值
	 */
	private String level1value;
	/**
	 * 二级值
	 */
	private String level2value;
	/**
	 * 三级值
	 */
	private String level3value;
	/**
	 * 四级值
	 */
	private String level4value;
	/**
	 * 五级值
	 */
	private String level5value;
	/**
	 * 六级值
	 */
	private String level6value;
	/**
	 * 七级值
	 */
	private String level7value;
	/**
	 * 八级值
	 */
	private String level8value;
	/**
	 * 九级值
	 */
	private String level9value;
	/**
	 * 十级值
	 */
	private String level10value;
	/**
	 * 是否开启时间
	 */
	private Integer isOpenTime;
	/**
	 * 是否开启流水号
	 */
	private Integer isOpenNumber;
	/**
	 * 时间格式
	 */
	private String timeFormat;
	/**
	 * 序列号起始
	 */
	private Long serialNumberBegin;
	/**
	 * 序列号结束
	 */
	private Long serialNumberEnd;
	/**
	 * 序列号步长
	 */
	private Integer serialNumberStep;
	/**
	 * 序列号长度
	 */
	private Integer serialNumberLength;
	/**
	 * 级数是否被选中
	 */
	private Integer islevel1;
	private Integer islevel2;
	private Integer islevel3;
	private Integer islevel4;
	private Integer islevel5;
	private Integer islevel6;
	private Integer islevel7;
	private Integer islevel8;
	private Integer islevel9;
	private Integer islevel10;

	/* 单据类型 */
	private BillsType billsType;

	public BaseCoder() {
	}

	public String getSequenceID() {
		return sequenceID;
	}

	public void setSequenceID(String sequenceID) {
		this.sequenceID = sequenceID;
	}

	public Long getInitValue() {
		return initValue;
	}

	public void setInitValue(Long initValue) {
		this.initValue = initValue;
	}

	public Integer getCodeLength() {
		return codeLength;
	}

	public void setCodeLength(Integer codeLength) {
		this.codeLength = codeLength;
	}

	public Integer getEnableSeries() {
		return enableSeries;
	}

	public void setEnableSeries(Integer enableSeries) {
		this.enableSeries = enableSeries;
	}

	public String getLevel1value() {
		return level1value;
	}

	public void setLevel1value(String level1value) {
		this.level1value = level1value;
	}

	public String getLevel2value() {
		return level2value;
	}

	public void setLevel2value(String level2value) {
		this.level2value = level2value;
	}

	public String getLevel3value() {
		return level3value;
	}

	public void setLevel3value(String level3value) {
		this.level3value = level3value;
	}

	public String getLevel4value() {
		return level4value;
	}

	public void setLevel4value(String level4value) {
		this.level4value = level4value;
	}

	public String getLevel5value() {
		return level5value;
	}

	public void setLevel5value(String level5value) {
		this.level5value = level5value;
	}

	public String getLevel6value() {
		return level6value;
	}

	public void setLevel6value(String level6value) {
		this.level6value = level6value;
	}

	public String getLevel7value() {
		return level7value;
	}

	public void setLevel7value(String level7value) {
		this.level7value = level7value;
	}

	public String getLevel8value() {
		return level8value;
	}

	public void setLevel8value(String level8value) {
		this.level8value = level8value;
	}

	public String getLevel9value() {
		return level9value;
	}

	public void setLevel9value(String level9value) {
		this.level9value = level9value;
	}

	public String getLevel10value() {
		return level10value;
	}

	public void setLevel10value(String level10value) {
		this.level10value = level10value;
	}

	public Integer getIsOpenTime() {
		return isOpenTime;
	}

	public void setIsOpenTime(Integer isOpenTime) {
		this.isOpenTime = isOpenTime;
	}

	public Integer getIsOpenNumber() {
		return isOpenNumber;
	}

	public void setIsOpenNumber(Integer isOpenNumber) {
		this.isOpenNumber = isOpenNumber;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public Long getSerialNumberBegin() {
		return serialNumberBegin;
	}

	public void setSerialNumberBegin(Long serialNumberBegin) {
		this.serialNumberBegin = serialNumberBegin;
	}

	public Long getSerialNumberEnd() {
		return serialNumberEnd;
	}

	public void setSerialNumberEnd(Long serialNumberEnd) {
		this.serialNumberEnd = serialNumberEnd;
	}

	public Integer getSerialNumberStep() {
		return serialNumberStep;
	}

	public void setSerialNumberStep(Integer serialNumberStep) {
		this.serialNumberStep = serialNumberStep;
	}

	public Integer getSerialNumberLength() {
		return serialNumberLength;
	}

	public void setSerialNumberLength(Integer serialNumberLength) {
		this.serialNumberLength = serialNumberLength;
	}

	public Integer getIslevel1() {
		return islevel1;
	}

	public void setIslevel1(Integer islevel1) {
		this.islevel1 = islevel1;
	}

	public Integer getIslevel2() {
		return islevel2;
	}

	public void setIslevel2(Integer islevel2) {
		this.islevel2 = islevel2;
	}

	public Integer getIslevel3() {
		return islevel3;
	}

	public void setIslevel3(Integer islevel3) {
		this.islevel3 = islevel3;
	}

	public Integer getIslevel4() {
		return islevel4;
	}

	public void setIslevel4(Integer islevel4) {
		this.islevel4 = islevel4;
	}

	public Integer getIslevel5() {
		return islevel5;
	}

	public void setIslevel5(Integer islevel5) {
		this.islevel5 = islevel5;
	}

	public Integer getIslevel6() {
		return islevel6;
	}

	public void setIslevel6(Integer islevel6) {
		this.islevel6 = islevel6;
	}

	public Integer getIslevel7() {
		return islevel7;
	}

	public void setIslevel7(Integer islevel7) {
		this.islevel7 = islevel7;
	}

	public Integer getIslevel8() {
		return islevel8;
	}

	public void setIslevel8(Integer islevel8) {
		this.islevel8 = islevel8;
	}

	public Integer getIslevel9() {
		return islevel9;
	}

	public void setIslevel9(Integer islevel9) {
		this.islevel9 = islevel9;
	}

	public Integer getIslevel10() {
		return islevel10;
	}

	public void setIslevel10(Integer islevel10) {
		this.islevel10 = islevel10;
	}

	public BillsType getBillsType() {
		return billsType;
	}

	public void setBillsType(BillsType billsType) {
		this.billsType = billsType;
	}

}