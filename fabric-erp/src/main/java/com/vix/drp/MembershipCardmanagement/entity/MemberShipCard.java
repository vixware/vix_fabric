package com.vix.drp.MembershipCardmanagement.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseBOEntity;
import com.vix.mdm.crm.entity.CustomerAccount;

/**
 * 会员卡
 * 
 * @author zhanghaibing
 * 
 * @date 2013-11-6
 */
public class MemberShipCard extends BaseBOEntity {
	private static final long serialVersionUID = -6458184898066500593L;
	/**
	 * 会员卡号
	 */
	private String vipcardid;
	/**
	 * 会员卡类型编码
	 */
	private String viptypeid;
	/**
	 * 会员卡类型名称
	 */
	private String vipTypeName;
	/**
	 * 余额
	 */
	private Double balance_amount;
	/**
	 * 累计消费金额
	 */
	private Double consume_amount;
	/**
	 * 结算次数
	 */
	private Integer settle_count;
	/**
	 * 发卡日期
	 */
	private Date opencarddate;
	/**
	 * 发卡时间
	 */
	private Date opencardtime;
	/**
	 * 是否销售
	 */
	private String issale;
	/**
	 * 销售顾问编码
	 */
	private String repsid;
	/**
	 * 销售顾问姓名
	 */
	private String repsname;
	/**
	 * 销售金额
	 */
	private Double saleprice;
	/**
	 * 销售日期
	 */
	private Date sale_date;
	/**
	 * 销售时间
	 */
	private Date sale_time;
	/**
	 * 是否启用
	 */
	private String isstartuse;
	/**
	 * 开始日期
	 */
	private Date start_date;
	/**
	 * 开始时间
	 */
	private Date start_time;
	/**
	 * 有效日期
	 */
	private Date invalid_date;
	/**
	 * 有效时间
	 */
	private Date invalid_time;
	/**
	 * 上次使用日期
	 */
	private Date lastuse_date;
	/**
	 * 上次使用时间
	 */
	private Date lastuse_time;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 是否停用 1,停用 2,启用
	 */
	private String isstop;
	/**
	 * 是否销卡,1,是,2,否
	 */
	private String isdestruct;
	/**
	 * 请假开始日期
	 */
	private Date leave_begindate;
	/**
	 * 请假截止日期
	 */
	private Date leave_enddate;
	/**
	 * 会员编号
	 */
	private String vipid;
	/**
	 * 原有卡号
	 */
	private String oldcardid;
	/**
	 * 磁道数据
	 */
	private String trackdata;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否作废
	 */
	private String isinvalid;
	/** 积分历史 */
	private Long pointHistory;
	/** 冻结积分 */
	private Long pointFreeze;
	/** 可用积分 */
	private Long point;
	/**
	 * 批量开卡生成的批次号
	 */
	private String batchNo;
	/**
	 * 验证码
	 */
	private String checkNo;
	/**
	 * 开卡类型B 批量开卡 S普通开卡
	 */
	private String opentype;
	/**
	 * 是否已下载
	 */
	private String isDownLoad = "";//代码里hql的判断是<>'2'，null值无法用等号判断
	/**
	 * 是否开卡 1,未开卡 2, 已开卡
	 */
	private String isOpenCard;
	/**
	 * 会员信息
	 */
	private CustomerAccount customerAccount;

	public String getVipcardid() {
		return vipcardid;
	}

	public void setVipcardid(String vipcardid) {
		this.vipcardid = vipcardid;
	}

	public String getViptypeid() {
		return viptypeid;
	}

	public void setViptypeid(String viptypeid) {
		this.viptypeid = viptypeid;
	}

	public Double getBalance_amount() {
		return balance_amount;
	}

	public void setBalance_amount(Double balance_amount) {
		this.balance_amount = balance_amount;
	}

	public Double getConsume_amount() {
		return consume_amount;
	}

	public void setConsume_amount(Double consume_amount) {
		this.consume_amount = consume_amount;
	}

	public Integer getSettle_count() {
		return settle_count;
	}

	public void setSettle_count(Integer settle_count) {
		this.settle_count = settle_count;
	}

	public Date getOpencarddate() {
		return opencarddate;
	}

	public void setOpencarddate(Date opencarddate) {
		this.opencarddate = opencarddate;
	}

	public Date getOpencardtime() {
		return opencardtime;
	}

	public void setOpencardtime(Date opencardtime) {
		this.opencardtime = opencardtime;
	}

	public String getIssale() {
		return issale;
	}

	public void setIssale(String issale) {
		this.issale = issale;
	}

	public String getRepsid() {
		return repsid;
	}

	public void setRepsid(String repsid) {
		this.repsid = repsid;
	}

	public String getRepsname() {
		return repsname;
	}

	public void setRepsname(String repsname) {
		this.repsname = repsname;
	}

	public Double getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(Double saleprice) {
		this.saleprice = saleprice;
	}

	public Date getSale_date() {
		return sale_date;
	}

	public void setSale_date(Date sale_date) {
		this.sale_date = sale_date;
	}

	public Date getSale_time() {
		return sale_time;
	}

	public void setSale_time(Date sale_time) {
		this.sale_time = sale_time;
	}

	public String getIsstartuse() {
		return isstartuse;
	}

	public void setIsstartuse(String isstartuse) {
		this.isstartuse = isstartuse;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getInvalid_date() {
		return invalid_date;
	}

	public void setInvalid_date(Date invalid_date) {
		this.invalid_date = invalid_date;
	}

	public Date getInvalid_time() {
		return invalid_time;
	}

	public void setInvalid_time(Date invalid_time) {
		this.invalid_time = invalid_time;
	}

	public Date getLastuse_date() {
		return lastuse_date;
	}

	public void setLastuse_date(Date lastuse_date) {
		this.lastuse_date = lastuse_date;
	}

	public Date getLastuse_time() {
		return lastuse_time;
	}

	public void setLastuse_time(Date lastuse_time) {
		this.lastuse_time = lastuse_time;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsstop() {
		return isstop;
	}

	public void setIsstop(String isstop) {
		this.isstop = isstop;
	}

	public String getIsdestruct() {
		return isdestruct;
	}

	public void setIsdestruct(String isdestruct) {
		this.isdestruct = isdestruct;
	}

	public Date getLeave_begindate() {
		return leave_begindate;
	}

	public void setLeave_begindate(Date leave_begindate) {
		this.leave_begindate = leave_begindate;
	}

	public Date getLeave_enddate() {
		return leave_enddate;
	}

	public void setLeave_enddate(Date leave_enddate) {
		this.leave_enddate = leave_enddate;
	}

	public String getVipid() {
		return vipid;
	}

	public void setVipid(String vipid) {
		this.vipid = vipid;
	}

	public String getOldcardid() {
		return oldcardid;
	}

	public void setOldcardid(String oldcardid) {
		this.oldcardid = oldcardid;
	}

	public String getTrackdata() {
		return trackdata;
	}

	public void setTrackdata(String trackdata) {
		this.trackdata = trackdata;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsinvalid() {
		return isinvalid;
	}

	public void setIsinvalid(String isinvalid) {
		this.isinvalid = isinvalid;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public Long getPointHistory() {
		return pointHistory;
	}

	public void setPointHistory(Long pointHistory) {
		this.pointHistory = pointHistory;
	}

	public Long getPointFreeze() {
		return pointFreeze;
	}

	public void setPointFreeze(Long pointFreeze) {
		this.pointFreeze = pointFreeze;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public String getOpentype() {
		return opentype;
	}

	public void setOpentype(String opentype) {
		this.opentype = opentype;
	}

	public String getIsDownLoad() {
		return isDownLoad;
	}

	public void setIsDownLoad(String isDownLoad) {
		this.isDownLoad = isDownLoad;
	}

	public String getVipTypeName() {
		return vipTypeName;
	}

	public void setVipTypeName(String vipTypeName) {
		this.vipTypeName = vipTypeName;
	}

	public String getIsOpenCard() {
		return isOpenCard;
	}

	public void setIsOpenCard(String isOpenCard) {
		this.isOpenCard = isOpenCard;
	}

}
