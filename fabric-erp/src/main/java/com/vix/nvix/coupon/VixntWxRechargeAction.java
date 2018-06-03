package com.vix.nvix.coupon;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.security.util.SecurityUtil;
import com.vix.core.utils.StrUtils;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.crm.entity.CustomerAccountClip;
import com.vix.mdm.crm.entity.RechargeRecord;
import com.vix.nvix.common.base.service.IVixntBaseService;
import com.vix.nvix.wx.action.WxBaseAction;
import com.vix.nvix.wx.entity.WxPaySource;
import com.vix.nvix.wx.entity.WxpWeixinSite;
import com.vix.nvix.wx.util.HttpUtil;
import com.vix.nvix.wx.util.PayCommonUtil;
import com.vix.nvix.wx.util.ScanPayReqData;
import com.vix.nvix.wx.util.XMLUtil;
@Controller
@Scope("request")
public class VixntWxRechargeAction extends WxBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IVixntBaseService vixntBaseService;
	private CustomerAccount customerAccount;
	private RechargeRecord rechargeRecord;
	private CustomerAccountClip customerAccountClip;
	private String rechargeRecordId;
	private String customerAccountClipId;
	private ChannelDistributor channelDistributor;
	private String payAmount;
	private String payType;
	@SuppressWarnings("rawtypes")
	public String weixinRecharge() {
		String urlCode = "";
		try {
			if (StrUtils.isNotEmpty(rechargeRecordId) && StrUtils.isNotEmpty(customerAccountClipId) && StrUtils.isNotEmpty(payType)) {
				String tenantId = SecurityUtil.getCurrentUserTenantId();
				WxpWeixinSite wxpWeixinSite = vixntBaseService.findEntityByAttribute(WxpWeixinSite.class, "tenantId", tenantId);
				if (null == wxpWeixinSite || StrUtils.isEmpty(wxpWeixinSite.getAppId()) || StrUtils.isEmpty(wxpWeixinSite.getPrivateKey()) || StrUtils.isEmpty(wxpWeixinSite.getMchID())) {
					System.out.println("error:微信支付配置数据不全！");
				} else {
					rechargeRecordId = rechargeRecordId.replaceAll("\r|\n", "");
					customerAccountClipId = customerAccountClipId.replaceAll("\r|\n", "");
					rechargeRecord = vixntBaseService.findEntityById(RechargeRecord.class, rechargeRecordId);
					customerAccountClip = vixntBaseService.findEntityById(CustomerAccountClip.class, customerAccountClipId);
					Employee employee = getEmployee();
					if (employee != null) {
						channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, employee.getChannelDistributor().getId());
					}
					if (customerAccountClip != null) {
						customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, customerAccountClip.getCustomerAccount().getId());
					}
					if (null != rechargeRecord) {
						if (StrUtils.isNotEmpty(payAmount) && Double.parseDouble(payAmount) > 0) {
							int totalFee = (int) (Double.parseDouble(payAmount) * 100);
							// 测试写死
							// int totalFee = 1;
							ScanPayReqData scanPayReqData = new ScanPayReqData("", "会员卡充值", "", String.valueOf(rechargeRecord.getCode()), totalFee, "", getIpAddr(getRequest()), "", "", "");
							// 账号信息
							String appid = wxpWeixinSite.getAppId(); // appid
							// String appsecret = PayConfigUtil.APP_SECRET; //
							// appsecret
							String mch_id = wxpWeixinSite.getMchID(); // 商业号
							String key = wxpWeixinSite.getPrivateKey(); // key

							String nonce_str = scanPayReqData.getNonce_str();

							String order_price = String.valueOf(scanPayReqData.getTotal_fee()); // 价格
																								// 注意：价格的单位是分
							String body = scanPayReqData.getBody(); // 商品名称
							String out_trade_no = scanPayReqData.getOut_trade_no(); // 订单号

							// 获取发起电脑 ip
							String spbill_create_ip = scanPayReqData.getSpbill_create_ip();
							// String spbill_create_ip = "127.0.0.1";
							// 签名
							// String sign = scanPayReqData.getSign();
							// 回调接口 TODO XXX
							String notify_url = "http://www.vixware.cn:8888/ws/nvixnt/vixntWxPayAction!getPayBack.action ";
							String trade_type = "NATIVE";
							// System.out.println(scanPayReqData.toString());
							// WXPay.doScanPayBusiness(scanPayReqData, null);

							SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
							packageParams.put("appid", appid);
							packageParams.put("mch_id", mch_id);
							packageParams.put("nonce_str", nonce_str);
							packageParams.put("body", body);
							packageParams.put("out_trade_no", out_trade_no);
							packageParams.put("total_fee", order_price);
							packageParams.put("spbill_create_ip", spbill_create_ip);
							packageParams.put("notify_url", notify_url);
							packageParams.put("trade_type", trade_type);
							String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
							packageParams.put("sign", sign);

							String requestXML = PayCommonUtil.getRequestXml(packageParams);
							System.out.println(requestXML);

							String resXml = HttpUtil.postData("https://api.mch.weixin.qq.com/pay/unifiedorder", requestXML);
							System.out.println(resXml);

							Map map = XMLUtil.doXMLParse(resXml);
							// String return_code = (String)
							// map.get("return_code");
							// String prepay_id = (String) map.get("prepay_id");
							urlCode = (String) map.get("code_url");
							// urlCode = "weixin://wxpay/bizpayurl?pr=2lXYzK6";
							System.out.println(urlCode);
							if (StrUtils.isNotEmpty(urlCode)) {
								WxPaySource wxPaySource = vixntBaseService.findEntityByAttribute(WxPaySource.class, "out_trade_no", out_trade_no);
								if (wxPaySource == null) {
									wxPaySource = new WxPaySource();
									wxPaySource.setOut_trade_no(out_trade_no);
									wxPaySource.setSource("1");
									wxPaySource = vixntBaseService.merge(wxPaySource);
								}
								if (channelDistributor != null) {
									rechargeRecord.setChannelDistributor(channelDistributor);
								}
								rechargeRecord.setPayType(payType);
								rechargeRecord.setPayMoney(Double.valueOf(payAmount));
								rechargeRecord.setCustomerAccountClip(customerAccountClip);
								rechargeRecord.setQrCodePath(urlCode);
								rechargeRecord.setUpdateTime(new Date());
								rechargeRecord = vixntBaseService.merge(rechargeRecord);
							}
						} else {
							System.out.println("error:支付金额不存在！");
						}
					} else {
						System.out.println("error:订单不存在!");
					}
				}
			} else {
				System.out.println("error:订单不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "weixinRecharge";
	}
	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}
	public RechargeRecord getRechargeRecord() {
		return rechargeRecord;
	}
	public void setRechargeRecord(RechargeRecord rechargeRecord) {
		this.rechargeRecord = rechargeRecord;
	}
	public CustomerAccountClip getCustomerAccountClip() {
		return customerAccountClip;
	}
	public void setCustomerAccountClip(CustomerAccountClip customerAccountClip) {
		this.customerAccountClip = customerAccountClip;
	}
	public String getRechargeRecordId() {
		return rechargeRecordId;
	}
	public void setRechargeRecordId(String rechargeRecordId) {
		this.rechargeRecordId = rechargeRecordId;
	}
	public String getCustomerAccountClipId() {
		return customerAccountClipId;
	}
	public void setCustomerAccountClipId(String customerAccountClipId) {
		this.customerAccountClipId = customerAccountClipId;
	}
	public String getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}
	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}
}
