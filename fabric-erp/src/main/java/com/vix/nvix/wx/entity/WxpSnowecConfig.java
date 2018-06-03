package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpSnowecConfig
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */
public class WxpSnowecConfig extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 校检码 wx */
	private String validateCode;
	/** 校检时间戳 yyyyMMddHHmm */
	private String dateValidateStr;
	/** 接口协议 */
	private String syncScheme;
	/** 接口域名 */
	private String syncHost;
	/** 项目名 */
	private String projectName;
	/** 2B项目名 */
	private String projectBName;
	/**
	 * 创建分享树接口
	 * /restService/mspShareTree/createMspShareTreeToSdp.rs?mspShareTreeString={
	 * mspShareTreeString}&validateCode={validateCode}
	 */
	private String uriCreateMspShareTreeToSdp;
	/**
	 * 微店首页url接口
	 * /restService/microShop/findMicroShopDtoByAppId.rs?appId={appId}&
	 * validateCode={validateCode}
	 */
	private String uriFindMicroShopDtoByAppId;
	/**
	 * 跳转登陆电商接口 /common/wxUrlLoginAction!loginByUrl.action
	 */
	private String uriWxToSnowecLogin;
	/**
	 * 获取销客专属二维码接口
	 * /restService/mspShareQrCode/findMspShareQrCodeByTicketKey.rs?ticketKey={
	 * ticketKey}&isFirst={isFirst}&nickName={nickName}&openId={openId}&
	 * companyCode={companyCode}&validateCode={validateCode}
	 */
	private String uriFindMspShareQrCodeByTicketKey;
	/**
	 * 微店注册接口
	 * /restService/ecCustomer/ecCustomerRegister.rs?ecCustomerDtoString={
	 * ecCustomerDtoString}&validateCode={validateCode}
	 */
	private String uriEcCustomerRegister;
	/**
	 * 微店消费记录接口
	 * /restService/ecOrderItemGroup/findEcOrderItemGroupPager.rs?orderField={
	 * orderField}&orderBy={orderBy}&pageNo={pageNo}&pageSize={pageSize}&
	 * ecCustomerId={ecCustomerId}&validateCode={validateCode}
	 */
	private String uriFindEcOrderItemGroupPager;
	/**
	 * 店铺微营销链接接口
	 * /restService/microShopMarket/findMicroShopMarketUrlByAppId.rs?appId={
	 * appId}&marketType={marketType}&validateCode={validateCode}&openId={
	 * openId}
	 */
	private String uriFindMicroShopMarketUrlByAppId;
	/**
	 * 微店优惠卷接口 /restService/microShopCoupon/getShopCouponPager.rs?microShopId={
	 * microShopId}&ecCustomerId={ecCustomerId}&type={type}&orderField={
	 * orderField}&orderBy={orderBy}&pageNo={pageNo}&pageSize={pageSize}&
	 * validateCode={validateCode}
	 */
	private String uriFindEcCouponPager;
	/**
	 * 店铺链接接口
	 * restService/microShop/findMicroShopUrlByAppId.rs?appId={appId}&urlType={
	 * urlType}&validateCode={validateCode}&openId={openId}
	 */
	private String findMicroShopUrlByAppId;

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getDateValidateStr() {
		return dateValidateStr;
	}

	public void setDateValidateStr(String dateValidateStr) {
		this.dateValidateStr = dateValidateStr;
	}

	public String getUriCreateMspShareTreeToSdp() {
		return uriCreateMspShareTreeToSdp;
	}

	public void setUriCreateMspShareTreeToSdp(String uriCreateMspShareTreeToSdp) {
		this.uriCreateMspShareTreeToSdp = uriCreateMspShareTreeToSdp;
	}

	public String getUriFindMicroShopDtoByAppId() {
		return uriFindMicroShopDtoByAppId;
	}

	public void setUriFindMicroShopDtoByAppId(String uriFindMicroShopDtoByAppId) {
		this.uriFindMicroShopDtoByAppId = uriFindMicroShopDtoByAppId;
	}

	public String getUriWxToSnowecLogin() {
		return uriWxToSnowecLogin;
	}

	public void setUriWxToSnowecLogin(String uriWxToSnowecLogin) {
		this.uriWxToSnowecLogin = uriWxToSnowecLogin;
	}

	public String getUriFindMspShareQrCodeByTicketKey() {
		return uriFindMspShareQrCodeByTicketKey;
	}

	public void setUriFindMspShareQrCodeByTicketKey(String uriFindMspShareQrCodeByTicketKey) {
		this.uriFindMspShareQrCodeByTicketKey = uriFindMspShareQrCodeByTicketKey;
	}

	public String getUriEcCustomerRegister() {
		return uriEcCustomerRegister;
	}

	public void setUriEcCustomerRegister(String uriEcCustomerRegister) {
		this.uriEcCustomerRegister = uriEcCustomerRegister;
	}

	public String getSyncScheme() {
		return syncScheme;
	}

	public void setSyncScheme(String syncScheme) {
		this.syncScheme = syncScheme;
	}

	public String getSyncHost() {
		return syncHost;
	}

	public void setSyncHost(String syncHost) {
		this.syncHost = syncHost;
	}

	public String getUriFindEcOrderItemGroupPager() {
		return uriFindEcOrderItemGroupPager;
	}

	public void setUriFindEcOrderItemGroupPager(String uriFindEcOrderItemGroupPager) {
		this.uriFindEcOrderItemGroupPager = uriFindEcOrderItemGroupPager;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getUriFindMicroShopMarketUrlByAppId() {
		return uriFindMicroShopMarketUrlByAppId;
	}

	public void setUriFindMicroShopMarketUrlByAppId(String uriFindMicroShopMarketUrlByAppId) {
		this.uriFindMicroShopMarketUrlByAppId = uriFindMicroShopMarketUrlByAppId;
	}

	public String getProjectBName() {
		return projectBName;
	}

	public void setProjectBName(String projectBName) {
		this.projectBName = projectBName;
	}

	public String getUriFindEcCouponPager() {
		return uriFindEcCouponPager;
	}

	public void setUriFindEcCouponPager(String uriFindEcCouponPager) {
		this.uriFindEcCouponPager = uriFindEcCouponPager;
	}

	public String getFindMicroShopUrlByAppId() {
		return findMicroShopUrlByAppId;
	}

	public void setFindMicroShopUrlByAppId(String findMicroShopUrlByAppId) {
		this.findMicroShopUrlByAppId = findMicroShopUrlByAppId;
	}
}
