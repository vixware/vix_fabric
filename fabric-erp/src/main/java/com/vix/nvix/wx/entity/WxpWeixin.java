package com.vix.nvix.wx.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.wx.util.StrUtils;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpWeixin
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */
public class WxpWeixin extends BaseEntity {
	private static final long serialVersionUID = 1L;
	public static String weixin_type_dingyue = "dingyue";
	public static String weixin_type_fuwu = "fuwu";
	public static String weixin_type_test = "test";
	/** 公众号名称 */
	private String title;
	/** 公众号标识 */
	private String appId;
	/** 公众号微信号 */
	private String weixinId;
	/** 公众号token */
	private String token;
	/** 域名 */
	private String domain;
	/** 第三方用户唯一凭证密钥，即appsecret或appkey,与paySignKey不同 */
	private String secret;
	/** 订阅号dingyue；服务号fuwu */
	private String weixinType;
	/** 获取信息时的accesstoken */
	private String accessToken;
	/** accesstoken过期时间 */
	private Date accessTokenExpireTime;
	/** jssdk使用的ticket */
	private String jsapiTicket;
	/** jssdk使用ticket过期时间 */
	private Date jsapiTicketExpireTime;
	/** 微信是否验证 */
	private Integer isAuthed;
	/** 有效期起始日期 */
	private Date startDate;
	/** 有效期结束日期 */
	private Date endDate;
	/** 对外odb编码 */
	private String sapChildClient;
	/** 对外odb名称 */
	private String sapChildClientName;
	/** 对外2C电商编码 */
	private String snowShopId;
	/** 对外2C电商名称 */
	private String snowShopName;
	/** 对外2B电商编码 */
	private String snowBShopId;
	/** 对外2B电商名称 */
	private String snowBShopName;
	/** 公众号管理员 */
	// private User user;
	/** 创建人 */
	private Employee employee;

	public boolean needReloadAccessToken() {
		if (StrUtils.isEmpty(this.accessToken)) {
			return true;
		} else {
			long timeMill = System.currentTimeMillis();
			if (this.accessTokenExpireTime == null || timeMill >= this.accessTokenExpireTime.getTime()) {
				return true;
			}
		}
		return false;
	}

	public boolean needReloadJsapiTicket() {
		if (StrUtils.isEmpty(this.jsapiTicket)) {
			return true;
		} else {
			long timeMill = System.currentTimeMillis();
			if (this.jsapiTicketExpireTime == null || timeMill >= this.jsapiTicketExpireTime.getTime()) {
				return true;
			}
		}
		return false;
	}

	public String getStartDateStr() {
		if (null != startDate) {
			return DateUtil.format(startDate);
		}
		return "";
	}

	public String getEndDateStr() {
		if (null != endDate) {
			return DateUtil.format(endDate);
		}
		return "";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getWeixinType() {
		return weixinType;
	}

	public void setWeixinType(String weixinType) {
		this.weixinType = weixinType;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getAccessTokenExpireTime() {
		return accessTokenExpireTime;
	}

	public void setAccessTokenExpireTime(Date accessTokenExpireTime) {
		this.accessTokenExpireTime = accessTokenExpireTime;
	}

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	@Override
	public String getAppId() {
		return appId;
	}

	@Override
	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Date getJsapiTicketExpireTime() {
		return jsapiTicketExpireTime;
	}

	public void setJsapiTicketExpireTime(Date jsapiTicketExpireTime) {
		this.jsapiTicketExpireTime = jsapiTicketExpireTime;
	}

	public Integer getIsAuthed() {
		return isAuthed;
	}

	public void setIsAuthed(Integer isAuthed) {
		this.isAuthed = isAuthed;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getSapChildClient() {
		return sapChildClient;
	}

	public void setSapChildClient(String sapChildClient) {
		this.sapChildClient = sapChildClient;
	}

	public String getSapChildClientName() {
		return sapChildClientName;
	}

	public void setSapChildClientName(String sapChildClientName) {
		this.sapChildClientName = sapChildClientName;
	}

	public String getSnowShopId() {
		return snowShopId;
	}

	public void setSnowShopId(String snowShopId) {
		this.snowShopId = snowShopId;
	}

	public String getSnowShopName() {
		return snowShopName;
	}

	public void setSnowShopName(String snowShopName) {
		this.snowShopName = snowShopName;
	}

	public String getSnowBShopId() {
		return snowBShopId;
	}

	public void setSnowBShopId(String snowBShopId) {
		this.snowBShopId = snowBShopId;
	}

	public String getSnowBShopName() {
		return snowBShopName;
	}

	public void setSnowBShopName(String snowBShopName) {
		this.snowBShopName = snowBShopName;
	}
}