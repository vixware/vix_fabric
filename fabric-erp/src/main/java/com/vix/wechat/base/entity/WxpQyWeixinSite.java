package com.vix.wechat.base.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.org.entity.Organization;
import com.vix.common.share.entity.BaseEntity;

/**
 * 微信企业号
 * 
 * @类全名 com.vix.wechat.base.entity.WxpQyWeixinSite
 *
 * @author zhanghaibing
 *
 * @date 2017年10月20日
 */
public class WxpQyWeixinSite extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String qiyeCorpId; // 企业号id
	private String qiyeAgentId; // 企业号应用id,这个不一定有用
	private String qiyeSecret;
	private String qiyeAccessToken;
	private Date qiyeTokenExpireTime;
	private Organization organization;
	private String corpId;// 授权方企业号id
	private String corpName; // 授权方企业号名称;
	private String corpType; // 授权方企业号类型，认证号：verified, 注册号：unverified，体验号：test
	private String corpRoundLogoUrl; // 授权方企业号圆形头像
	private String corpSquareLogoUrl; // 授权方企业号方形头像
	private String corpUserMax; // 授权方企业号用户规模
	private String corpAgentMax; // 授权方企业号应用规模
	private String corpWxqrcode; // 授权方企业号二维码
	private String authInfo; // 授权信息
	private Date verifiedEndTime;// 认证到期时间
	private String permanentCode;// 企业号永久授权码。长度为64至512个字节
	private String suiteId;// 应用套件id

	// agent 授权的应用信息
	// agentid 授权方应用id
	// agent:name 授权方应用名字
	// square_logo_url 授权方应用方形头像
	// round_logo_url 授权方应用圆形头像
	// corp_full_name 所绑定的企业号主体名称
	// subject_type 企业类型，1. 企业; 2. 政府以及事业单位; 3. 其他组织, 4.团队号
	// appid 服务商套件中的对应应用id
	// api_group 授权方应用敏感权限组，目前仅有get_location，表示是否有权限设置应用获取地理位置的开关
	// department 授权的通讯录部门
	// department:id 部门id
	// department:name 部门名称
	// department:parentid 父部门id
	// department:writable 是否具有该部门的写权限
	public boolean hasQiyeAccount() {
		return StringUtils.isNotEmpty(this.qiyeCorpId);
	}

	public boolean hasPermanentCode() {
		return StringUtils.isNotEmpty(this.permanentCode);
	}

	public boolean needReloadQiyeAccessToken() {
		if (StringUtils.isEmpty(this.qiyeAccessToken)) {
			return true;
		} else {
			long timeMill = System.currentTimeMillis();
			if (this.qiyeTokenExpireTime == null || timeMill >= this.qiyeTokenExpireTime.getTime()) {
				return true;
			}
		}
		return false;
	}

	public String getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(String suiteId) {
		this.suiteId = suiteId;
	}

	public String getPermanentCode() {
		return permanentCode;
	}

	public void setPermanentCode(String permanentCode) {
		this.permanentCode = permanentCode;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getCorpType() {
		return corpType;
	}

	public void setCorpType(String corpType) {
		this.corpType = corpType;
	}

	public String getCorpRoundLogoUrl() {
		return corpRoundLogoUrl;
	}

	public void setCorpRoundLogoUrl(String corpRoundLogoUrl) {
		this.corpRoundLogoUrl = corpRoundLogoUrl;
	}

	public String getCorpSquareLogoUrl() {
		return corpSquareLogoUrl;
	}

	public void setCorpSquareLogoUrl(String corpSquareLogoUrl) {
		this.corpSquareLogoUrl = corpSquareLogoUrl;
	}

	public String getCorpUserMax() {
		return corpUserMax;
	}

	public void setCorpUserMax(String corpUserMax) {
		this.corpUserMax = corpUserMax;
	}

	public String getCorpAgentMax() {
		return corpAgentMax;
	}

	public void setCorpAgentMax(String corpAgentMax) {
		this.corpAgentMax = corpAgentMax;
	}

	public String getCorpWxqrcode() {
		return corpWxqrcode;
	}

	public void setCorpWxqrcode(String corpWxqrcode) {
		this.corpWxqrcode = corpWxqrcode;
	}

	public String getAuthInfo() {
		return authInfo;
	}

	public void setAuthInfo(String authInfo) {
		this.authInfo = authInfo;
	}

	public Date getVerifiedEndTime() {
		return verifiedEndTime;
	}

	public void setVerifiedEndTime(Date verifiedEndTime) {
		this.verifiedEndTime = verifiedEndTime;
	}

	public String getQiyeAgentId() {
		return qiyeAgentId;
	}

	public void setQiyeAgentId(String qiyeAgentId) {
		this.qiyeAgentId = qiyeAgentId;
	}

	public String getQiyeSecret() {
		return qiyeSecret;
	}

	public void setQiyeSecret(String qiyeSecret) {
		this.qiyeSecret = qiyeSecret;
	}

	public String getQiyeAccessToken() {
		return qiyeAccessToken;
	}

	public void setQiyeAccessToken(String qiyeAccessToken) {
		this.qiyeAccessToken = qiyeAccessToken;
	}

	public Date getQiyeTokenExpireTime() {
		return qiyeTokenExpireTime;
	}

	public void setQiyeTokenExpireTime(Date qiyeTokenExpireTime) {
		this.qiyeTokenExpireTime = qiyeTokenExpireTime;
	}

	public String getQiyeCorpId() {
		return qiyeCorpId;
	}

	public void setQiyeCorpId(String qiyeCorpId) {
		this.qiyeCorpId = qiyeCorpId;
	}

	/**
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * @param organization
	 *            the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}