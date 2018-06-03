/**
 * 
 */
package com.vix.system.code.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 编码规则中间表
 * 
 * @author zhanghaibing
 * 
 * @date 2013-8-20
 */
public class EncodingRulesTableInTheMiddle extends BaseEntity {
	private static final long serialVersionUID = -8575803874669990250L;
	/**
	 * 预览显示
	 */
	private String codingPreview;
	/**
	 * 序号编码/前缀
	 */
	private String sequenceID;
	/**
	 * 序号
	 */
	private Long initValue;
	/**
	 * 对象
	 */
	private String objectType;
	/**
	 * 单据类型
	 */
	private String billType;
	/**
	 * 编码长度
	 */
	private Integer codeLength;
	/**
	 * 启用级数
	 */
	private Integer enableSeries;
	/**
	 * 编码类型
	 */
	private String codeType;
	/**
	 * 一级长度
	 */
	private Integer level1Length;
	/**
	 * 二级长度
	 */
	private Integer level2Length;
	/**
	 * 三级长度
	 */
	private Integer level3Length;
	/**
	 * 四级长度
	 */
	private Integer level4Length;
	/**
	 * 五级长度
	 */
	private Integer level5Length;
	/**
	 * 六级长度
	 */
	private Integer level6Length;
	/**
	 * 七级长度
	 */
	private Integer level7Length;
	/**
	 * 八级长度
	 */
	private Integer level8Length;
	/**
	 * 九级长度
	 */
	private Integer level9Length;
	/**
	 * 十级长度
	 */
	private Integer level10Length;
	/**
	 * 十一级长度
	 */
	private Integer level11Length;
	/**
	 * 十二级长度
	 */
	private Integer level12Length;
	/**
	 * 一级类型
	 */
	private String level1type;
	/**
	 * 二级类型
	 */
	private String level2type;
	/**
	 * 三级类型
	 */
	private String level3type;
	/**
	 * 四级类型
	 */
	private String level4type;
	/**
	 * 五级类型
	 */
	private String level5type;
	/**
	 * 六级类型
	 */
	private String level6type;
	/**
	 * 七级类型
	 */
	private String level7type;
	/**
	 * 八级类型
	 */
	private String level8type;
	/**
	 * 九级类型
	 */
	private String level9type;
	/**
	 * 十级类型
	 */
	private String level10type;
	/**
	 * 十一级类型
	 */
	private String level11type;
	/**
	 * 十二级类型
	 */
	private String level12type;
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
	 * 十一级值
	 */
	private String level11value;
	/**
	 * 十二级值
	 */
	private String level12value;
	/**
	 * 是否开启时间
	 */
	private Integer isOpenTime;
	/**
	 * 是否开启流水号
	 */
	private Integer isOpenNumber;
	/**
	 * 时间类型
	 */
	private String timeType;
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
	private Integer islevel11;
	private Integer islevel12;

	public EncodingRulesTableInTheMiddle() {
	}

	public EncodingRulesTableInTheMiddle(String id) {
		setId(id);
	}

	public String getCodingPreview() {
		return codingPreview;
	}

	public void setCodingPreview(String codingPreview) {
		this.codingPreview = codingPreview;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
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

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public Integer getLevel1Length() {
		return level1Length;
	}

	public void setLevel1Length(Integer level1Length) {
		this.level1Length = level1Length;
	}

	public Integer getLevel2Length() {
		return level2Length;
	}

	public void setLevel2Length(Integer level2Length) {
		this.level2Length = level2Length;
	}

	public Integer getLevel3Length() {
		return level3Length;
	}

	public void setLevel3Length(Integer level3Length) {
		this.level3Length = level3Length;
	}

	public Integer getLevel4Length() {
		return level4Length;
	}

	public void setLevel4Length(Integer level4Length) {
		this.level4Length = level4Length;
	}

	public Integer getLevel5Length() {
		return level5Length;
	}

	public void setLevel5Length(Integer level5Length) {
		this.level5Length = level5Length;
	}

	public Integer getLevel6Length() {
		return level6Length;
	}

	public void setLevel6Length(Integer level6Length) {
		this.level6Length = level6Length;
	}

	public Integer getLevel7Length() {
		return level7Length;
	}

	public void setLevel7Length(Integer level7Length) {
		this.level7Length = level7Length;
	}

	public Integer getLevel8Length() {
		return level8Length;
	}

	public void setLevel8Length(Integer level8Length) {
		this.level8Length = level8Length;
	}

	public Integer getLevel9Length() {
		return level9Length;
	}

	public void setLevel9Length(Integer level9Length) {
		this.level9Length = level9Length;
	}

	public Integer getLevel10Length() {
		return level10Length;
	}

	public void setLevel10Length(Integer level10Length) {
		this.level10Length = level10Length;
	}

	public Integer getLevel11Length() {
		return level11Length;
	}

	public void setLevel11Length(Integer level11Length) {
		this.level11Length = level11Length;
	}

	public Integer getLevel12Length() {
		return level12Length;
	}

	public void setLevel12Length(Integer level12Length) {
		this.level12Length = level12Length;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
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

	public String getLevel1type() {
		return level1type;
	}

	public void setLevel1type(String level1type) {
		this.level1type = level1type;
	}

	public String getLevel2type() {
		return level2type;
	}

	public void setLevel2type(String level2type) {
		this.level2type = level2type;
	}

	public String getLevel3type() {
		return level3type;
	}

	public void setLevel3type(String level3type) {
		this.level3type = level3type;
	}

	public String getLevel4type() {
		return level4type;
	}

	public void setLevel4type(String level4type) {
		this.level4type = level4type;
	}

	public String getLevel5type() {
		return level5type;
	}

	public void setLevel5type(String level5type) {
		this.level5type = level5type;
	}

	public String getLevel6type() {
		return level6type;
	}

	public void setLevel6type(String level6type) {
		this.level6type = level6type;
	}

	public String getLevel7type() {
		return level7type;
	}

	public void setLevel7type(String level7type) {
		this.level7type = level7type;
	}

	public String getLevel8type() {
		return level8type;
	}

	public void setLevel8type(String level8type) {
		this.level8type = level8type;
	}

	public String getLevel9type() {
		return level9type;
	}

	public void setLevel9type(String level9type) {
		this.level9type = level9type;
	}

	public String getLevel10type() {
		return level10type;
	}

	public void setLevel10type(String level10type) {
		this.level10type = level10type;
	}

	public String getLevel11type() {
		return level11type;
	}

	public void setLevel11type(String level11type) {
		this.level11type = level11type;
	}

	public String getLevel12type() {
		return level12type;
	}

	public void setLevel12type(String level12type) {
		this.level12type = level12type;
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

	public String getLevel11value() {
		return level11value;
	}

	public void setLevel11value(String level11value) {
		this.level11value = level11value;
	}

	public String getLevel12value() {
		return level12value;
	}

	public void setLevel12value(String level12value) {
		this.level12value = level12value;
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

	public Integer getIslevel11() {
		return islevel11;
	}

	public void setIslevel11(Integer islevel11) {
		this.islevel11 = islevel11;
	}

	public Integer getIslevel12() {
		return islevel12;
	}

	public void setIslevel12(Integer islevel12) {
		this.islevel12 = islevel12;
	}

}
