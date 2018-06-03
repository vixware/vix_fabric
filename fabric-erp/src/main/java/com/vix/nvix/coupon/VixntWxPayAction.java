package com.vix.nvix.coupon;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.message.constant.MessageTemplateConstant;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.crm.business.entity.Coupon;
import com.vix.crm.business.entity.CouponDetail;
import com.vix.crm.member.entity.MemberLevel;
import com.vix.drp.accountmanagement.service.IAccountManagementService;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channel.entity.ChannelDistributorMessageSet;
import com.vix.drp.channel.entity.SMSCostRules;
import com.vix.drp.integralRulesSet.entity.IntegralActivity;
import com.vix.drp.integralRulesSet.entity.IntegralRules;
import com.vix.drp.pointRecord.entity.PointRecord;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.crm.entity.CustomerAccountClip;
import com.vix.mdm.crm.entity.CustomerAccountClipDetail;
import com.vix.mdm.crm.entity.RechargeRecord;
import com.vix.mdm.crm.entity.StoredValueRuleSet;
import com.vix.mdm.crm.entity.StoredValueRuleSetDetail;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemGift;
import com.vix.mdm.purchase.order.entity.PaymentRecord;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrder;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrderItem;
import com.vix.nvix.common.base.service.IVixntBaseService;
import com.vix.nvix.wx.action.WxBaseAction;
import com.vix.nvix.wx.entity.ScanPaySuccessReturnDataEntity;
import com.vix.nvix.wx.entity.WxPaySource;
import com.vix.nvix.wx.entity.WxpWeixinSite;
import com.vix.nvix.wx.util.HttpUtil;
import com.vix.nvix.wx.util.MD5;
import com.vix.nvix.wx.util.PayCommonUtil;
import com.vix.nvix.wx.util.ScanPayReqData;
import com.vix.nvix.wx.util.Util;
import com.vix.nvix.wx.util.XMLUtil;

@Controller
@Scope("request")
public class VixntWxPayAction extends WxBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IAccountManagementService accountManagementService;
	@Autowired
	private IVixntBaseService vixntBaseService;

	private String id;
	private String customerAccountId;
	private String orderId;
	private String payAmount;
	private String amount;
	private String payType;
	private String reduceAmount;
	private String couponId;
	private String resultStatus;
	private CustomerAccount customerAccount;
	private RequireGoodsOrder requireGoodsOrder;

	private String storedValueRuleSetId;
	private RechargeRecord rechargeRecord;
	private StoredValueRuleSet storedValueRuleSet;
	private CustomerAccountClip customerAccountClip;
	private String rechargeRecordId;
	private String customerAccountClipId;
	private ChannelDistributor channelDistributor;
	@SuppressWarnings("rawtypes")
	public String weixinPay() {
		String urlCode = "";
		try {
			if (StrUtils.isNotEmpty(orderId) && StrUtils.isNotEmpty(customerAccountId) && StrUtils.isNotEmpty(payType) && StrUtils.isNotEmpty(payAmount) && StrUtils.isNotEmpty(amount) && StrUtils.isNotEmpty(reduceAmount)) {
				String tenantId = SecurityUtil.getCurrentUserTenantId();
				WxpWeixinSite wxpWeixinSite = vixntBaseService.findEntityByAttribute(WxpWeixinSite.class, "tenantId", tenantId);
				if (null == wxpWeixinSite || StrUtils.isEmpty(wxpWeixinSite.getAppId()) || StrUtils.isEmpty(wxpWeixinSite.getPrivateKey()) || StrUtils.isEmpty(wxpWeixinSite.getMchID())) {
					System.out.println("error:微信支付配置数据不全！");
				} else {
					orderId = orderId.replaceAll("\r|\n", "");
					customerAccountId = customerAccountId.replaceAll("\r|\n", "");
					requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, orderId);
					customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, customerAccountId);
					if (null != requireGoodsOrder) {
						if (StrUtils.isNotEmpty(payAmount) && Double.parseDouble(payAmount) > 0) {
							int totalFee = (int) (Double.parseDouble(payAmount) * 100);
							// 测试写死 int totalFee = 1;
							ScanPayReqData scanPayReqData = new ScanPayReqData("", "POS订单支付", "", String.valueOf(requireGoodsOrder.getCode()), totalFee, "", getIpAddr(getRequest()), "", "", "");
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
							// 签名
							// String sign = scanPayReqData.getSign();
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
							System.out.println(urlCode);
							if (StrUtils.isNotEmpty(urlCode)) {
								// 保存微信支付来源
								WxPaySource wxPaySource = vixntBaseService.findEntityByAttribute(WxPaySource.class, "out_trade_no", out_trade_no);
								if (wxPaySource == null) {
									wxPaySource = new WxPaySource();
									wxPaySource.setOut_trade_no(out_trade_no);
									wxPaySource.setSource("0");
									wxPaySource = vixntBaseService.merge(wxPaySource);
								}
								requireGoodsOrder.setQrCodePath(urlCode);
								requireGoodsOrder.setUpdateTime(new Date());
								// 如果服务项目减去次数
								Map<String, Object> orderParam = new HashMap<String, Object>();
								orderParam.put("requireGoodsOrder.id," + SearchCondition.EQUAL, requireGoodsOrder.getId());
								List<RequireGoodsOrderItem> requireGoodsOrderItemList = vixntBaseService.findAllDataByConditions(RequireGoodsOrderItem.class, orderParam);
								for (RequireGoodsOrderItem requireGoodsOrderItem : requireGoodsOrderItemList) {
									Item item = vixntBaseService.findEntityById(Item.class, requireGoodsOrderItem.getItem().getId());
									if ("Y".equals(item.getIsServiceProduct())) {
										Map<String, Object> params = new HashMap<String, Object>();
										params.put("customerAccount.id," + SearchCondition.EQUAL, customerAccount.getId());
										params.put("item.id," + SearchCondition.EQUAL, item.getId());
										List<CustomerAccountClipDetail> customerAccountClipDetails = vixntBaseService.findAllDataByConditions(CustomerAccountClipDetail.class, params);
										if (customerAccountClipDetails != null && customerAccountClipDetails.size() > 0) {
											CustomerAccountClipDetail customerAccountClipDetail = customerAccountClipDetails.get(0);
											if (customerAccountClipDetail != null && customerAccountClipDetail.getNumber() >= requireGoodsOrderItem.getNum()) {
												customerAccountClipDetail.setNumber(customerAccountClipDetail.getNumber() - requireGoodsOrderItem.getNum());
												customerAccountClipDetail = vixntBaseService.merge(customerAccountClipDetail);
												requireGoodsOrderItem.setPrice(0d);
												requireGoodsOrderItem.setNetTotal(requireGoodsOrderItem.getPrice() * requireGoodsOrderItem.getNum());
												requireGoodsOrderItem = vixntBaseService.merge(requireGoodsOrderItem);
											}
										}
									}
									// 赠品减去数量
									ItemGift itemGift = vixntBaseService.findEntityByAttribute(ItemGift.class, "item.id", item.getId());
									if (itemGift != null) {
										itemGift.setGiftCount((int) (itemGift.getGiftCount() - requireGoodsOrderItem.getNum()));
										itemGift = vixntBaseService.merge(itemGift);
									}
								}
								requireGoodsOrder.setAmount(Double.parseDouble(amount));
								requireGoodsOrder.setPayAmount(Double.parseDouble(payAmount));
								requireGoodsOrder.setReduceAmount(Double.parseDouble(reduceAmount));
								requireGoodsOrder.setPayType(payType);
								requireGoodsOrder.setCustomerAccount(customerAccount);
								// 保存优惠券
								if (StrUtils.isNotEmpty(couponId)) {
									CouponDetail couponDetail = vixntBaseService.findEntityById(CouponDetail.class, couponId);
									if (couponDetail != null) {
										requireGoodsOrder.setCouponDetail(couponDetail);
										requireGoodsOrder.setDiscountFee(couponDetail.getAmount().toString());
									}
								}
								requireGoodsOrder = vixntBaseService.merge(requireGoodsOrder);
							}
						} else {
							System.out.println("error:支付金额不存在！");
						}
					} else {
						System.out.println("error:订单不存在！");
					}
				}
			} else {
				System.out.println("error:订单数据不存在,支付失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "weixinPay";
	}
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void getPayBack() throws Exception {

		// 读取参数
		InputStream inputStream;
		StringBuffer sb = new StringBuffer();
		inputStream = getRequest().getInputStream();
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		while ((s = in.readLine()) != null) {
			sb.append(s);
		}
		in.close();
		inputStream.close();

		// 解析xml成map
		Map<String, String> m = new HashMap<String, String>();
		m = XMLUtil.doXMLParse(sb.toString());
		System.out.println(m + "------------------------");
		// 过滤空 设置 TreeMap
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		Iterator it = m.keySet().iterator();
		while (it.hasNext()) {
			String parameter = (String) it.next();
			String parameterValue = m.get(parameter);

			String v = "";
			if (null != parameterValue) {
				v = parameterValue.trim();
			}
			packageParams.put(parameter, v);
		}

		// 账号信息
		WxpWeixinSite wxpWeixinSite = vixntBaseService.findEntityByAttributeNoTenantId(WxpWeixinSite.class, "appId", api_gz_appId);
		String key = wxpWeixinSite.getPrivateKey(); // key

		logger.info(packageParams.toString());
		// 判断签名是否正确
		if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, key)) {
			// ------------------------------
			// 处理业务开始
			// ------------------------------
			String resXml = "";
			resultStatus = (String) packageParams.get("result_code");
			if ("SUCCESS".equals(resultStatus)) {
				// 这里是支付成功
				////////// 执行自己的业务逻辑////////////////
				String mch_id = (String) packageParams.get("mch_id");
				String openid = (String) packageParams.get("openid");
				String is_subscribe = (String) packageParams.get("is_subscribe");
				String out_trade_no = (String) packageParams.get("out_trade_no");

				String total_fee = (String) packageParams.get("total_fee");
				String time_end = (String) packageParams.get("time_end");// 支付结束时间

				logger.info("mch_id:" + mch_id);
				logger.info("openid:" + openid);
				logger.info("is_subscribe:" + is_subscribe);
				logger.info("out_trade_no:" + out_trade_no);
				logger.info("total_fee:" + total_fee);

				////////// 执行自己的业务逻辑////////////////
				WxPaySource wxPaySource = vixntBaseService.findEntityByAttribute(WxPaySource.class, "out_trade_no", out_trade_no);
				if (wxPaySource != null) {
					if ("0".equals(wxPaySource.getSource())) {
						requireGoodsOrder = vixntBaseService.findEntityByAttribute(RequireGoodsOrder.class, "code", out_trade_no);
						if (requireGoodsOrder != null) {
							// 保存微信支付记录
							orderId = requireGoodsOrder.getId();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
							ScanPaySuccessReturnDataEntity entity = vixntBaseService.findEntityByAttributeNoTenantId(ScanPaySuccessReturnDataEntity.class, "requireGoodsOrder.id", requireGoodsOrder.getId());
							if (entity != null) {
								entity.setUpdateTime(sdf.parse(time_end));
							} else {
								entity = (ScanPaySuccessReturnDataEntity) Util.getObjectFromXML(sb.toString(), ScanPaySuccessReturnDataEntity.class);
								entity.setCreateTime(sdf.parse(time_end));
								entity.setUpdateTime(sdf.parse(time_end));
								entity.setCompanyInnerCode(requireGoodsOrder.getCompanyInnerCode());
								entity.setTenantId(requireGoodsOrder.getTenantId());
								entity.setDepartmentCode(requireGoodsOrder.getDepartmentCode());
								entity.setRequireGoodsOrder(requireGoodsOrder);
								entity.setRefundStatus("0");
								entity.setSource(wxPaySource.getSource());
							}
							entity = vixntBaseService.mergeOriginal(entity);
							dealPosOrder(out_trade_no);
						}
						vixntBaseService.deleteByEntity(wxPaySource);
					}
					if ("1".equals(wxPaySource.getSource())) {
						rechargeRecord = vixntBaseService.findEntityByAttribute(RechargeRecord.class, "code", out_trade_no);
						if (rechargeRecord != null) {
							// 保存微信支付记录
							rechargeRecordId = rechargeRecord.getId();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
							ScanPaySuccessReturnDataEntity entity = vixntBaseService.findEntityByAttributeNoTenantId(ScanPaySuccessReturnDataEntity.class, "rechargeRecord.id", rechargeRecord.getId());
							if (entity != null) {
								entity.setUpdateTime(sdf.parse(time_end));
							} else {
								entity = (ScanPaySuccessReturnDataEntity) Util.getObjectFromXML(sb.toString(), ScanPaySuccessReturnDataEntity.class);
								entity.setCreateTime(sdf.parse(time_end));
								entity.setUpdateTime(sdf.parse(time_end));
								entity.setCompanyInnerCode(rechargeRecord.getCompanyInnerCode());
								entity.setTenantId(rechargeRecord.getTenantId());
								entity.setDepartmentCode(rechargeRecord.getDepartmentCode());
								entity.setRechargeRecord(rechargeRecord);
								entity.setRefundStatus("1");
								entity.setSource(wxPaySource.getSource());
							}
							entity = vixntBaseService.mergeOriginal(entity);
							dealRecharge(out_trade_no);
						}
						vixntBaseService.deleteByEntity(wxPaySource);
					}
					if ("2".equals(wxPaySource.getSource())) {
						rechargeRecord = vixntBaseService.findEntityByAttribute(RechargeRecord.class, "code", out_trade_no);
						if (rechargeRecord != null) {
							// 保存微信支付记录
							rechargeRecordId = rechargeRecord.getId();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
							ScanPaySuccessReturnDataEntity entity = vixntBaseService.findEntityByAttributeNoTenantId(ScanPaySuccessReturnDataEntity.class, "rechargeRecord.id", rechargeRecord.getId());
							if (entity != null) {
								entity.setUpdateTime(sdf.parse(time_end));
							} else {
								entity = (ScanPaySuccessReturnDataEntity) Util.getObjectFromXML(sb.toString(), ScanPaySuccessReturnDataEntity.class);
								entity.setCreateTime(sdf.parse(time_end));
								entity.setUpdateTime(sdf.parse(time_end));
								entity.setCompanyInnerCode(rechargeRecord.getCompanyInnerCode());
								entity.setTenantId(rechargeRecord.getTenantId());
								entity.setDepartmentCode(rechargeRecord.getDepartmentCode());
								entity.setRechargeRecord(rechargeRecord);
								entity.setRefundStatus("1");
								entity.setSource(wxPaySource.getSource());
							}
							entity = vixntBaseService.mergeOriginal(entity);
							dealMakeCard(out_trade_no);
						}
						vixntBaseService.deleteByEntity(wxPaySource);
					}
				}

				logger.info("支付成功");
				// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
				resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

			} else {
				logger.info("支付失败,错误信息：" + packageParams.get("err_code"));
				System.out.println(packageParams.get("err_code"));
				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
			}
			// ------------------------------
			// 处理业务完毕
			// ------------------------------
			BufferedOutputStream out = new BufferedOutputStream(getResponse().getOutputStream());
			out.write(resXml.getBytes());
			out.flush();
			out.close();
		} else {
			logger.info("通知签名验证失败");
		}
	}

	/**
	 * 处理POS
	 * 
	 * @param out_trade_no
	 * @throws Exception
	 */
	private void dealPosOrder(String out_trade_no) throws Exception {
		requireGoodsOrder = vixntBaseService.findEntityByAttributeNoTenantId(RequireGoodsOrder.class, "code", out_trade_no);
		requireGoodsOrder.setIsTemp("");
		requireGoodsOrder.setIsSettlement("1");
		requireGoodsOrder.setPayTime(new Date());
		requireGoodsOrder = vixntBaseService.mergeOriginal(requireGoodsOrder);
		// 保存优惠券
		CouponDetail couponDetail = requireGoodsOrder.getCouponDetail();
		if (couponDetail != null) {
			couponDetail = vixntBaseService.findEntityById(CouponDetail.class, couponDetail.getId());
			couponDetail.setIsUsed("1");
			couponDetail = vixntBaseService.mergeOriginal(couponDetail);
			Coupon coupon = vixntBaseService.findEntityById(Coupon.class, couponDetail.getCoupon().getId());
			if (coupon != null) {
				if (coupon.getUseQuantity() != null && coupon.getUseQuantity() > 0) {
					coupon.setUseQuantity(coupon.getUseQuantity() + 1);
				} else {
					coupon.setUseQuantity(1L);
				}
				coupon = vixntBaseService.mergeOriginal(coupon);
			}
		}
		customerAccount = requireGoodsOrder.getCustomerAccount();
		// 保存积分记录
		Map<String, Object> params = new HashMap<String, Object>();
		List<IntegralRules> integralRuleList = vixntBaseService.findAllDataByConditions(IntegralRules.class, params);
		// 会员生日积分赠送比率
		Double memberBirthdayRatio = 1d;
		if (integralRuleList != null && integralRuleList.size() > 0) {
			IntegralRules integralRules = integralRuleList.get(0);
			memberBirthdayRatio = integralRules.getMemberBirthdayRatio();
		}
		params.put("xsStartDate," + SearchCondition.LESSTHANANDEQUAL, new Date());
		params.put("xsEndDate," + SearchCondition.MORETHANANDEQUAL, new Date());
		List<IntegralActivity> integralActivityList = vixntBaseService.findAllDataByConditions(IntegralActivity.class, params);
		Double point = 0d;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (integralActivityList != null && integralActivityList.size() > 0) {
			IntegralActivity integralActivity = integralActivityList.get(0);
			point = requireGoodsOrder.getPayAmount() * integralActivity.getConversiorate();
			point = Math.floor(point);
			if (customerAccount.getBirthday() != null) {
				if (sdf.format(new Date()).equals(sdf.format(customerAccount.getBirthday()))) {
					point = point * memberBirthdayRatio;
				}
			}
		} else if (integralRuleList != null && integralRuleList.size() > 0) {
			IntegralRules integralRules = integralRuleList.get(0);
			point = requireGoodsOrder.getPayAmount() * integralRules.getConversiorate();
			point = Math.floor(point);
			if (customerAccount.getBirthday() != null) {
				if (sdf.format(new Date()).equals(sdf.format(customerAccount.getBirthday()))) {
					point = point * memberBirthdayRatio;
				}
			}
		}
		if (customerAccount.getPoint() != null && customerAccount.getPoint() > 0) {
			customerAccount.setPoint(customerAccount.getPoint() + point);
		} else {
			customerAccount.setPoint(point);
		}
		if (customerAccount.getAmountPoint() != null && customerAccount.getAmountPoint() > 0) {
			customerAccount.setAmountPoint(customerAccount.getAmountPoint() + point);
		} else {
			customerAccount.setAmountPoint(point);
		}
		CustomerAccountClip customerAccountClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerAccount.getId());
		if (customerAccountClip != null) {
			customerAccountClip.setPointValue(customerAccount.getPoint());
			customerAccountClip = vixntBaseService.mergeOriginal(customerAccountClip);
		}
		PointRecord pointRecord = vixntBaseService.findEntityByAttribute(PointRecord.class, "requireGoodsOrder.id", requireGoodsOrder.getId());
		if (pointRecord != null) {
			pointRecord.setUpdateTime(new Date());
		} else {
			pointRecord = new PointRecord();
			pointRecord.setCreateTime(new Date());
			pointRecord.setUpdateTime(new Date());
			pointRecord.setCompanyInnerCode(requireGoodsOrder.getCompanyInnerCode());
			pointRecord.setTenantId(requireGoodsOrder.getTenantId());
			pointRecord.setDepartmentCode(requireGoodsOrder.getDepartmentCode());
			pointRecord.setPointSource("消费赠送积分");
			pointRecord.setOperation("POS订单");
			pointRecord.setPointType("1");
			pointRecord.setPointValue(point);
			pointRecord.setCustomerAccount(customerAccount);
			pointRecord.setRequireGoodsOrder(requireGoodsOrder);
			pointRecord = vixntBaseService.mergeOriginal(pointRecord);
		}
		// 增加最近消费时间
		customerAccount.setUpdateTime(new Date());
		customerAccount = vixntBaseService.mergeOriginal(customerAccount);
		// 保存支付记录
		PaymentRecord paymentRecord = vixntBaseService.findEntityByAttribute(PaymentRecord.class, "requireGoodsOrder.id", requireGoodsOrder.getId());
		if (paymentRecord != null) {
			paymentRecord.setUpdateTime(new Date());
		} else {
			paymentRecord = new PaymentRecord();
			paymentRecord.setCreateTime(new Date());
			paymentRecord.setUpdateTime(new Date());
			paymentRecord.setCompanyInnerCode(requireGoodsOrder.getCompanyInnerCode());
			paymentRecord.setTenantId(requireGoodsOrder.getTenantId());
			paymentRecord.setDepartmentCode(requireGoodsOrder.getDepartmentCode());
			paymentRecord.setPaymentType(payType);
			paymentRecord.setAmount(requireGoodsOrder.getPayAmount());
			paymentRecord.setRequireGoodsOrder(requireGoodsOrder);
			paymentRecord.setCustomerAccount(customerAccount);
			paymentRecord = vixntBaseService.mergeOriginal(paymentRecord);
		}
	}
	private void dealMakeCard(String out_trade_no) throws Exception {
		rechargeRecord = vixntBaseService.findEntityByAttributeNoTenantId(RechargeRecord.class, "code", out_trade_no);
		rechargeRecord.setIsTemp("");
		rechargeRecord.setPayDate(new Date());
		rechargeRecord = vixntBaseService.mergeOriginal(rechargeRecord);
		customerAccount = rechargeRecord.getCustomerAccountClip().getCustomerAccount();
		CustomerAccountClip customerAccountClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerAccount.getId());
		if (customerAccountClip != null) {
			customerAccountClip.setPointValue(customerAccount.getPoint());
			customerAccountClip = vixntBaseService.mergeOriginal(customerAccountClip);
		}
		if (null != rechargeRecord.getChannelDistributor()) {
			channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, rechargeRecord.getChannelDistributor().getId());
		}
		PointRecord pointRecord = vixntBaseService.findEntityByAttribute(PointRecord.class, "rechargeRecord.id", rechargeRecord.getId());
		if (pointRecord != null) {
			pointRecord.setUpdateTime(new Date());
		} else {
			pointRecord = new PointRecord();
			pointRecord.setCreateTime(new Date());
			pointRecord.setUpdateTime(new Date());
			pointRecord.setCompanyInnerCode(rechargeRecord.getCompanyInnerCode());
			pointRecord.setTenantId(rechargeRecord.getTenantId());
			pointRecord.setDepartmentCode(rechargeRecord.getDepartmentCode());
			pointRecord.setPointSource("办卡获取积分");
			pointRecord.setOperation("办卡");
			pointRecord.setPointType("1");
			pointRecord.setPointValue(customerAccount.getPoint());
			pointRecord.setCustomerAccount(customerAccount);
			pointRecord.setRechargeRecord(rechargeRecord);
			pointRecord = vixntBaseService.mergeOriginal(pointRecord);
		}
		// 增加最近消费时间
		customerAccount.setStatus("2");
		customerAccount.setUpdateTime(new Date());
		customerAccount = vixntBaseService.mergeOriginal(customerAccount);
		if ("2".equals(customerAccount.getStatus())) {
			accountManagementService.saveOrUpdateUserAccount(customerAccount.getMobilePhone(), MD5.MD5Encode("123456"), customerAccount.getId(), "1");
			if (channelDistributor != null) {
				ChannelDistributorMessageSet channelDistributorMessageSet = vixntBaseService.findEntityByAttribute(ChannelDistributorMessageSet.class, "channelDistributor.id", channelDistributor.getId());
				if (channelDistributorMessageSet != null && "0".equals(channelDistributorMessageSet.getIsEnable()) && "0".equals(channelDistributorMessageSet.getSalesInform())) {
					Map<String, Object> params = getParams();
					List<SMSCostRules> sMSCostRulesList = vixntBaseService.findAllDataByConditions(SMSCostRules.class, params);
					if (null != sMSCostRulesList && sMSCostRulesList.size() > 0) {
						SMSCostRules smsCostRules = sMSCostRulesList.get(0);
						Double price = smsCostRules.getPrice();
						if (channelDistributorMessageSet.getAmount() != null && channelDistributorMessageSet.getAmount() > 0 && channelDistributorMessageSet.getAmount() > price) {
							Map<String, String> orderStatusMap = new HashMap<String, String>();
							orderStatusMap.put(MessageTemplateConstant.cardNo, customerAccount.getClipNumber());
							orderStatusMap.put(MessageTemplateConstant.username, customerAccount.getName());
							orderStatusMap.put(MessageTemplateConstant.password, "123456");
							String resp = sendMessage("1", customerAccount.getMobilePhone(), orderStatusMap);
							if (resp != null && resp.contains("|")) {
								saveMessageSendRecord(2, "1", "开卡通知", customerAccount.getMobilePhone(), channelDistributor, channelDistributor.getEmployee());
								if (StringUtils.isNotEmpty(String.valueOf(channelDistributorMessageSet.getNum())) && null != channelDistributorMessageSet.getNum()) {
									channelDistributorMessageSet.setNum(channelDistributorMessageSet.getNum() + 1);
								} else {
									channelDistributorMessageSet.setNum(1d);
								}
								channelDistributorMessageSet.setAmount(channelDistributorMessageSet.getAmount() - price);
								initEntityBaseController.initEntityBaseAttribute(channelDistributorMessageSet);
								channelDistributorMessageSet = vixntBaseService.mergeOriginal(channelDistributorMessageSet);
							}
							System.out.println(resp);
						}
					}
				}
			}
		}
	}
	private void dealRecharge(String out_trade_no) throws Exception {
		rechargeRecord = vixntBaseService.findEntityByAttributeNoTenantId(RechargeRecord.class, "code", out_trade_no);
		rechargeRecord.setIsTemp("");
		rechargeRecord.setPayDate(new Date());
		rechargeRecord = vixntBaseService.mergeOriginal(rechargeRecord);
		customerAccountClip = rechargeRecord.getCustomerAccountClip();
		if (null != rechargeRecord.getStoredValueRuleSet()) {
			storedValueRuleSet = rechargeRecord.getStoredValueRuleSet();
		}
		if (null != rechargeRecord.getChannelDistributor()) {
			channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, rechargeRecord.getChannelDistributor().getId());
		}
		if (null != customerAccountClip) {
			customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, customerAccountClip.getCustomerAccount().getId());
			if ("1".equals(customerAccountClip.getCard().getType())) {
				if (null != rechargeRecord && null != customerAccount) {
					if (null != customerAccount) {
						if (null != storedValueRuleSet) {
							customerAccount.setMoney(customerAccount.getMoney() + storedValueRuleSet.getAmount() + storedValueRuleSet.getGiftAmount());
							customerAccountClip.setMoney(customerAccountClip.getMoney() + storedValueRuleSet.getAmount() + storedValueRuleSet.getGiftAmount());
							Map<String, Object> params = new HashMap<String, Object>();
							List<IntegralRules> integralRuleList = vixntBaseService.findAllDataByConditions(IntegralRules.class, params);
							if (integralRuleList != null && integralRuleList.size() > 0) {
								IntegralRules integralRules = integralRuleList.get(0);
								Double point = rechargeRecord.getPayMoney() * integralRules.getConversiorate();
								point = Math.floor(point);
								if (customerAccount.getPoint() != null && customerAccount.getPoint() > 0) {
									customerAccount.setPoint(customerAccount.getPoint() + point + storedValueRuleSet.getGiftPoints());
								} else {
									customerAccount.setPoint(point + storedValueRuleSet.getGiftPoints());
								}
								if (customerAccount.getAmountPoint() != null && customerAccount.getAmountPoint() > 0) {
									customerAccount.setAmountPoint(customerAccount.getAmountPoint() + point + storedValueRuleSet.getGiftPoints());
								} else {
									customerAccount.setAmountPoint(point + storedValueRuleSet.getGiftPoints());
								}
								PointRecord pointRecord = new PointRecord();
								pointRecord.setCreateTime(new Date());
								pointRecord.setUpdateTime(new Date());
								pointRecord.setCompanyInnerCode(rechargeRecord.getCompanyInnerCode());
								pointRecord.setTenantId(rechargeRecord.getTenantId());
								pointRecord.setDepartmentCode(rechargeRecord.getDepartmentCode());
								pointRecord.setPointSource("充值获取积分");
								pointRecord.setOperation("充值");
								pointRecord.setPointType("1");
								if (StringUtils.isNotEmpty(String.valueOf(storedValueRuleSet.getGiftPoints()))) {
									pointRecord.setPointValue(point + storedValueRuleSet.getGiftPoints());
								} else {
									pointRecord.setPointValue(point);
								}
								pointRecord.setCustomerAccount(customerAccount);
								pointRecord.setRechargeRecord(rechargeRecord);
								pointRecord = vixntBaseService.mergeOriginal(pointRecord);
							}
						} else {
							customerAccount.setMoney(customerAccount.getMoney() + rechargeRecord.getPayMoney());
							customerAccountClip.setMoney(customerAccountClip.getMoney() + rechargeRecord.getPayMoney());
							Map<String, Object> params = new HashMap<String, Object>();
							List<IntegralRules> integralRuleList = vixntBaseService.findAllDataByConditions(IntegralRules.class, params);
							if (integralRuleList != null && integralRuleList.size() > 0) {
								IntegralRules integralRules = integralRuleList.get(0);
								Double point = rechargeRecord.getPayMoney() * integralRules.getConversiorate();
								point = Math.floor(point);
								if (customerAccount.getPoint() != null && customerAccount.getPoint() > 0) {
									customerAccount.setPoint(customerAccount.getPoint() + point);
								} else {
									customerAccount.setPoint(point);
								}
								if (customerAccount.getAmountPoint() != null && customerAccount.getAmountPoint() > 0) {
									customerAccount.setAmountPoint(customerAccount.getAmountPoint() + point);
								} else {
									customerAccount.setAmountPoint(point);
								}
								PointRecord pointRecord = new PointRecord();
								pointRecord.setCreateTime(new Date());
								pointRecord.setUpdateTime(new Date());
								pointRecord.setCompanyInnerCode(rechargeRecord.getCompanyInnerCode());
								pointRecord.setTenantId(rechargeRecord.getTenantId());
								pointRecord.setDepartmentCode(rechargeRecord.getDepartmentCode());
								pointRecord.setPointSource("充值获取积分");
								pointRecord.setOperation("充值");
								pointRecord.setPointType("1");
								pointRecord.setPointValue(point);
								pointRecord.setCustomerAccount(customerAccount);
								pointRecord.setRechargeRecord(rechargeRecord);
								pointRecord = vixntBaseService.mergeOriginal(pointRecord);
							}
						}
					}
					customerAccountClip.setUpdateTime(new Date());
					customerAccount.setUpdateTime(new Date());
					initEntityBaseController.initEntityBaseAttribute(customerAccount);
					customerAccount = vixntBaseService.mergeOriginal(customerAccount);
					checkMemberLevel(customerAccount);
					customerAccountClip.setPointValue(customerAccount.getPoint());
					initEntityBaseController.initEntityBaseAttribute(customerAccountClip);
					customerAccountClip = vixntBaseService.mergeOriginal(customerAccountClip);
					if (channelDistributor != null) {
						ChannelDistributorMessageSet channelDistributorMessageSet = vixntBaseService.findEntityByAttribute(ChannelDistributorMessageSet.class, "channelDistributor.id", channelDistributor.getId());
						if (channelDistributorMessageSet != null && "0".equals(channelDistributorMessageSet.getIsEnable()) && "0".equals(channelDistributorMessageSet.getSalesInform())) {
							Map<String, Object> params = getParams();
							List<SMSCostRules> sMSCostRulesList = vixntBaseService.findAllDataByConditions(SMSCostRules.class, params);
							if (null != sMSCostRulesList && sMSCostRulesList.size() > 0) {
								SMSCostRules smsCostRules = sMSCostRulesList.get(0);
								Double price = smsCostRules.getPrice();
								if (channelDistributorMessageSet.getAmount() != null && channelDistributorMessageSet.getAmount() > 0 && channelDistributorMessageSet.getAmount() > price) {
									Map<String, String> orderStatusMap = new HashMap<String, String>();
									orderStatusMap.put(MessageTemplateConstant.username, customerAccount.getName());
									orderStatusMap.put(MessageTemplateConstant.cardNo, customerAccountClip.getName());
									String resp = sendMessage("2", customerAccount.getMobilePhone(), orderStatusMap);
									if (resp != null && resp.contains("|")) {
										saveMessageSendRecord(2, "2", "充值通知", customerAccount.getMobilePhone(), channelDistributor, channelDistributor.getEmployee());
										if (StringUtils.isNotEmpty(String.valueOf(channelDistributorMessageSet.getNum())) && null != channelDistributorMessageSet.getNum()) {
											channelDistributorMessageSet.setNum(channelDistributorMessageSet.getNum() + 1);
										} else {
											channelDistributorMessageSet.setNum(1d);
										}
										channelDistributorMessageSet.setAmount(channelDistributorMessageSet.getAmount() - price);
										initEntityBaseController.initEntityBaseAttribute(channelDistributorMessageSet);
										channelDistributorMessageSet = vixntBaseService.mergeOriginal(channelDistributorMessageSet);
									}
									System.out.println(resp);
								}
							}
						}
					}
				}
			} else if ("2".equals(customerAccountClip.getCard().getType())) {
				if (null != storedValueRuleSet) {
					Map<String, Object> params1 = getParams();
					params1.put("storedValueRuleSet.id," + SearchCondition.EQUAL, storedValueRuleSet.getId());
					List<StoredValueRuleSetDetail> list = vixntBaseService.findAllByConditions(StoredValueRuleSetDetail.class, params1);
					if (null != list && list.size() > 0) {
						for (StoredValueRuleSetDetail storedValueRuleSetDetail : list) {
							if (null != storedValueRuleSetDetail) {
								Map<String, Object> params = getParams();
								params.put("item.id," + SearchCondition.EQUAL, storedValueRuleSetDetail.getItem().getId());
								params.put("customerAccountClip.id," + SearchCondition.EQUAL, customerAccountClip.getId());
								List<CustomerAccountClipDetail> clipDetailList = vixntBaseService.findAllDataByConditions(CustomerAccountClipDetail.class, params);
								if (clipDetailList != null && clipDetailList.size() > 0 && clipDetailList.size() == 1) {
									CustomerAccountClipDetail customerAccountClipDetail = clipDetailList.get(0);
									customerAccountClipDetail.setNumber(customerAccountClipDetail.getNumber() + storedValueRuleSetDetail.getNum());
									customerAccountClipDetail = vixntBaseService.mergeOriginal(customerAccountClipDetail);
								} else {
									CustomerAccountClipDetail customerAccountClipDetail = new CustomerAccountClipDetail();
									customerAccountClipDetail.setCustomerAccount(customerAccount);
									customerAccountClipDetail.setCompanyInnerCode(rechargeRecord.getCompanyInnerCode());
									customerAccountClipDetail.setTenantId(rechargeRecord.getTenantId());
									customerAccountClipDetail.setDepartmentCode(rechargeRecord.getDepartmentCode());
									customerAccountClipDetail.setCustomerAccountClip(customerAccountClip);
									customerAccountClipDetail.setItem(storedValueRuleSetDetail.getItem());
									customerAccountClipDetail.setNumber(storedValueRuleSetDetail.getNum());
									customerAccountClipDetail = vixntBaseService.mergeOriginal(customerAccountClipDetail);
								}
							}
						}
					}
					Map<String, Object> params3 = new HashMap<String, Object>();
					List<IntegralRules> integralRuleList = vixntBaseService.findAllDataByConditions(IntegralRules.class, params3);
					if (integralRuleList != null && integralRuleList.size() > 0) {
						IntegralRules integralRules = integralRuleList.get(0);
						Double point = rechargeRecord.getPayMoney() * integralRules.getConversiorate();
						point = Math.floor(point);
						if (customerAccount.getPoint() != null && customerAccount.getPoint() > 0) {
							customerAccount.setPoint(customerAccount.getPoint() + point + storedValueRuleSet.getGiftPoints());
						} else {
							customerAccount.setPoint(point + storedValueRuleSet.getGiftPoints());
						}
						if (customerAccount.getAmountPoint() != null && customerAccount.getAmountPoint() > 0) {
							customerAccount.setAmountPoint(customerAccount.getAmountPoint() + point + storedValueRuleSet.getGiftPoints());
						} else {
							customerAccount.setAmountPoint(point + storedValueRuleSet.getGiftPoints());
						}
						PointRecord pointRecord = new PointRecord();
						pointRecord.setCreateTime(new Date());
						pointRecord.setUpdateTime(new Date());
						pointRecord.setCompanyInnerCode(rechargeRecord.getCompanyInnerCode());
						pointRecord.setTenantId(rechargeRecord.getTenantId());
						pointRecord.setDepartmentCode(rechargeRecord.getDepartmentCode());
						pointRecord.setPointSource("充值获取积分");
						pointRecord.setOperation("充值");
						pointRecord.setPointType("1");
						if (StringUtils.isNotEmpty(String.valueOf(storedValueRuleSet.getGiftPoints()))) {
							pointRecord.setPointValue(point + storedValueRuleSet.getGiftPoints());
						} else {
							pointRecord.setPointValue(point);
						}
						pointRecord.setCustomerAccount(customerAccount);
						pointRecord.setRechargeRecord(rechargeRecord);
						pointRecord = vixntBaseService.mergeOriginal(pointRecord);
					}
					customerAccountClip.setUpdateTime(new Date());
					customerAccount.setUpdateTime(new Date());
					initEntityBaseController.initEntityBaseAttribute(customerAccount);
					customerAccount = vixntBaseService.mergeOriginal(customerAccount);
					checkMemberLevel(customerAccount);
					customerAccountClip.setPointValue(customerAccount.getPoint());
					initEntityBaseController.initEntityBaseAttribute(customerAccountClip);
					customerAccountClip = vixntBaseService.mergeOriginal(customerAccountClip);
					if (channelDistributor != null) {
						ChannelDistributorMessageSet channelDistributorMessageSet = vixntBaseService.findEntityByAttribute(ChannelDistributorMessageSet.class, "channelDistributor.id", channelDistributor.getId());
						if (channelDistributorMessageSet != null && "0".equals(channelDistributorMessageSet.getIsEnable()) && "0".equals(channelDistributorMessageSet.getSalesInform())) {
							Map<String, Object> params = getParams();
							List<SMSCostRules> sMSCostRulesList = vixntBaseService.findAllDataByConditions(SMSCostRules.class, params);
							if (null != sMSCostRulesList && sMSCostRulesList.size() > 0) {
								SMSCostRules smsCostRules = sMSCostRulesList.get(0);
								Double price = smsCostRules.getPrice();
								if (channelDistributorMessageSet.getAmount() != null && channelDistributorMessageSet.getAmount() > 0 && channelDistributorMessageSet.getAmount() > price) {
									Map<String, String> orderStatusMap = new HashMap<String, String>();
									orderStatusMap.put(MessageTemplateConstant.username, customerAccount.getName());
									orderStatusMap.put(MessageTemplateConstant.cardNo, customerAccountClip.getName());
									String resp = sendMessage("2", customerAccount.getMobilePhone(), orderStatusMap);
									if (resp != null && resp.contains("|")) {
										saveMessageSendRecord(2, "2", "充值通知", customerAccount.getMobilePhone(), channelDistributor, channelDistributor.getEmployee());
										if (StringUtils.isNotEmpty(String.valueOf(channelDistributorMessageSet.getNum())) && null != channelDistributorMessageSet.getNum()) {
											channelDistributorMessageSet.setNum(channelDistributorMessageSet.getNum() + 1);
										} else {
											channelDistributorMessageSet.setNum(1d);
										}
										channelDistributorMessageSet.setAmount(channelDistributorMessageSet.getAmount() - price);
										initEntityBaseController.initEntityBaseAttribute(channelDistributorMessageSet);
										channelDistributorMessageSet = vixntBaseService.mergeOriginal(channelDistributorMessageSet);
									}
									System.out.println(resp);
								}
							}
						}
					}
				}
			}
		}
	}
	public void checkPay() {
		try {
			if (StrUtils.isNotEmpty(orderId)) {
				requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, orderId);
				if (requireGoodsOrder != null) {
					ScanPaySuccessReturnDataEntity entity = null;
					Map<String, Object> p = new HashMap<String, Object>();
					p.put("requireGoodsOrder.id," + SearchCondition.EQUAL, requireGoodsOrder.getId());
					List<ScanPaySuccessReturnDataEntity> entitys = vixntBaseService.findAllDataByConditions(ScanPaySuccessReturnDataEntity.class, p);
					if (entitys != null && entitys.size() > 0) {
						entity = entitys.get(0);
					}
					if (entity != null) {
						renderText("success");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkMemberLevel(CustomerAccount customerAccount) {
		try {
			Map<String, Object> params = getParams();
			List<MemberLevel> list = vixntBaseService.findAllByConditions(MemberLevel.class, params);
			if (null != list && list.size() > 0) {
				for (MemberLevel memberLevel2 : list) {
					if (memberLevel2.getFromPoints() <= customerAccount.getPoint() && memberLevel2.getToPoints() >= customerAccount.getPoint()) {
						customerAccount.setMemberLevel(memberLevel2);
						initEntityBaseController.initEntityBaseAttribute(customerAccount);
						customerAccount = vixntBaseService.mergeOriginal(customerAccount);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goPaySuccess() {
		return "goPaySuccess";
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerAccountId() {
		return customerAccountId;
	}

	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public RequireGoodsOrder getRequireGoodsOrder() {
		return requireGoodsOrder;
	}

	public void setRequireGoodsOrder(RequireGoodsOrder requireGoodsOrder) {
		this.requireGoodsOrder = requireGoodsOrder;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getReduceAmount() {
		return reduceAmount;
	}

	public void setReduceAmount(String reduceAmount) {
		this.reduceAmount = reduceAmount;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}
	public RechargeRecord getRechargeRecord() {
		return rechargeRecord;
	}
	public void setRechargeRecord(RechargeRecord rechargeRecord) {
		this.rechargeRecord = rechargeRecord;
	}
	public StoredValueRuleSet getStoredValueRuleSet() {
		return storedValueRuleSet;
	}
	public void setStoredValueRuleSet(StoredValueRuleSet storedValueRuleSet) {
		this.storedValueRuleSet = storedValueRuleSet;
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
	public String getStoredValueRuleSetId() {
		return storedValueRuleSetId;
	}
	public void setStoredValueRuleSetId(String storedValueRuleSetId) {
		this.storedValueRuleSetId = storedValueRuleSetId;
	}
}