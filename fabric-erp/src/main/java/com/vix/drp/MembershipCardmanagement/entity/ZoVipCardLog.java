/**
 * 
 */
package com.vix.drp.MembershipCardmanagement.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseBOEntity;

/**
 * 会员卡日志信息(消费记录)
 * 
 * @author zhanghaibing
 * 
 * @date 2014-4-14
 */
public class ZoVipCardLog extends BaseBOEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8137842068987751156L;
	/**
	 * 卡结记账编号
	 */
	private String logid;
	/**
	 * 会员卡号
	 */
	private String vipcardid;
	/**
	 * 应用编码
	 */
	private String appid;
	/**
	 * 等级日期
	 */
	private Date regdate;
	/**
	 * 等级时间
	 */
	private Date regtime;
	/**
	 * 创建用户账号
	 */
	private String reguserid;
	/**
	 * 创建用户姓名
	 */
	private String regusername;
	/**
	 * 日志标题编码
	 */
	private String logtitleid;
	/**
	 * 日志主题
	 */
	private String logtitle;
	/**
	 * 日志内容
	 */
	private String logtext;
	/**
	 * 日志备注
	 */
	private String logremark;
	/**
	 * 金额
	 */
	private Double amount;
	/**
	 * 账单号
	 */
	private String billid;
	/**
	 * 用户账号
	 */
	private String respuserid;
	/**
	 * 用户姓名
	 */
	private String respusername;
	/**
	 * 菜单项编码
	 */
	private String menuid;
	/**
	 * 菜单项名称
	 */
	private String menuname;
	/**
	 * 应收金额
	 */
	private Double sumtopay;
	/**
	 * 实收金额
	 */
	private Double sumtorealpay;
	/**
	 * 积分类型:1,增加.2,减少
	 */
	private String pointType;
	/**
	 * 积分值
	 */
	private Double pointValue;
	/**
	 * 会员卡信息
	 */
	private MemberShipCard memberShipCard;

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getVipcardid() {
		return vipcardid;
	}

	public void setVipcardid(String vipcardid) {
		this.vipcardid = vipcardid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	public String getReguserid() {
		return reguserid;
	}

	public void setReguserid(String reguserid) {
		this.reguserid = reguserid;
	}

	public String getRegusername() {
		return regusername;
	}

	public void setRegusername(String regusername) {
		this.regusername = regusername;
	}

	public String getLogtitleid() {
		return logtitleid;
	}

	public void setLogtitleid(String logtitleid) {
		this.logtitleid = logtitleid;
	}

	public String getLogtitle() {
		return logtitle;
	}

	public void setLogtitle(String logtitle) {
		this.logtitle = logtitle;
	}

	public String getLogtext() {
		return logtext;
	}

	public void setLogtext(String logtext) {
		this.logtext = logtext;
	}

	public String getLogremark() {
		return logremark;
	}

	public void setLogremark(String logremark) {
		this.logremark = logremark;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getBillid() {
		return billid;
	}

	public void setBillid(String billid) {
		this.billid = billid;
	}

	public String getRespuserid() {
		return respuserid;
	}

	public void setRespuserid(String respuserid) {
		this.respuserid = respuserid;
	}

	public String getRespusername() {
		return respusername;
	}

	public void setRespusername(String respusername) {
		this.respusername = respusername;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public Double getSumtopay() {
		return sumtopay;
	}

	public void setSumtopay(Double sumtopay) {
		this.sumtopay = sumtopay;
	}

	public Double getSumtorealpay() {
		return sumtorealpay;
	}

	public void setSumtorealpay(Double sumtorealpay) {
		this.sumtorealpay = sumtorealpay;
	}

	public String getPointType() {
		return pointType;
	}

	public void setPointType(String pointType) {
		this.pointType = pointType;
	}

	public Double getPointValue() {
		return pointValue;
	}

	public void setPointValue(Double pointValue) {
		this.pointValue = pointValue;
	}

	public MemberShipCard getMemberShipCard() {
		return memberShipCard;
	}

	public void setMemberShipCard(MemberShipCard memberShipCard) {
		this.memberShipCard = memberShipCard;
	}

}
