package com.vix.nvix.wx.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.mdm.crm.entity.RechargeRecord;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrder;

/**
 * 支付完成返回的数据
 * 
 * @类全名 com.vix.diandoc.base.entity.ScanPaySuccessReturnDataEntity
 *
 * @author zhanghaibing
 *
 * @date 2017年5月2日
 */
public class ScanPaySuccessReturnDataEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 协议层
	private String return_code;
	private String return_msg;
	private String appid;
	private String mch_id;
	private String nonce_str;
	private String sign;
	private String result_code;
	private String openid;
	private String is_subscribe;
	private String trade_type;
	private String bank_type;
	private String total_fee;
	private String fee_type;
	private String transaction_id;
	private String attach;
	private String out_trade_no;
	private String time_end;
	private String trade_state;
	private String cash_fee;
	private String coupon_fee;
	private String coupon_count;
	private String coupon_fee_0;
	private String coupon_fee_1;
	private String coupon_fee_2;
	private String coupon_fee_3;
	private String coupon_fee_4;
	private String coupon_type_0;
	private String coupon_type_1;
	private String coupon_type_2;
	private String coupon_type_3;
	private String coupon_type_4;
	private String coupon_id_0;
	private String coupon_id_1;
	private String coupon_id_2;
	private String coupon_id_3;
	private String coupon_id_4;

	private RequireGoodsOrder requireGoodsOrder;
	private RechargeRecord rechargeRecord;
	private String source;

	private String refundStatus;// 退款状态0,未申请 1,待退款 2,退款中 3,退款完成
	/** 申请时间 */
	private Date applyTime;

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getCoupon_fee() {
		return coupon_fee;
	}

	public void setCoupon_fee(String coupon_fee) {
		this.coupon_fee = coupon_fee;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public String getTrade_state() {
		return trade_state;
	}

	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}

	public String getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}

	public RequireGoodsOrder getRequireGoodsOrder() {
		return requireGoodsOrder;
	}

	public void setRequireGoodsOrder(RequireGoodsOrder requireGoodsOrder) {
		this.requireGoodsOrder = requireGoodsOrder;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public String getCoupon_fee_0() {
		return coupon_fee_0;
	}

	public void setCoupon_fee_0(String coupon_fee_0) {
		this.coupon_fee_0 = coupon_fee_0;
	}

	public String getCoupon_fee_1() {
		return coupon_fee_1;
	}

	public void setCoupon_fee_1(String coupon_fee_1) {
		this.coupon_fee_1 = coupon_fee_1;
	}

	public String getCoupon_fee_2() {
		return coupon_fee_2;
	}

	public void setCoupon_fee_2(String coupon_fee_2) {
		this.coupon_fee_2 = coupon_fee_2;
	}

	public String getCoupon_fee_3() {
		return coupon_fee_3;
	}

	public void setCoupon_fee_3(String coupon_fee_3) {
		this.coupon_fee_3 = coupon_fee_3;
	}

	public String getCoupon_fee_4() {
		return coupon_fee_4;
	}

	public void setCoupon_fee_4(String coupon_fee_4) {
		this.coupon_fee_4 = coupon_fee_4;
	}

	public String getCoupon_type_0() {
		return coupon_type_0;
	}

	public void setCoupon_type_0(String coupon_type_0) {
		this.coupon_type_0 = coupon_type_0;
	}

	public String getCoupon_type_1() {
		return coupon_type_1;
	}

	public void setCoupon_type_1(String coupon_type_1) {
		this.coupon_type_1 = coupon_type_1;
	}

	public String getCoupon_type_2() {
		return coupon_type_2;
	}

	public void setCoupon_type_2(String coupon_type_2) {
		this.coupon_type_2 = coupon_type_2;
	}

	public String getCoupon_type_3() {
		return coupon_type_3;
	}

	public void setCoupon_type_3(String coupon_type_3) {
		this.coupon_type_3 = coupon_type_3;
	}

	public String getCoupon_type_4() {
		return coupon_type_4;
	}

	public void setCoupon_type_4(String coupon_type_4) {
		this.coupon_type_4 = coupon_type_4;
	}

	public String getCoupon_id_0() {
		return coupon_id_0;
	}

	public void setCoupon_id_0(String coupon_id_0) {
		this.coupon_id_0 = coupon_id_0;
	}

	public String getCoupon_id_1() {
		return coupon_id_1;
	}

	public void setCoupon_id_1(String coupon_id_1) {
		this.coupon_id_1 = coupon_id_1;
	}

	public String getCoupon_id_2() {
		return coupon_id_2;
	}

	public void setCoupon_id_2(String coupon_id_2) {
		this.coupon_id_2 = coupon_id_2;
	}

	public String getCoupon_id_3() {
		return coupon_id_3;
	}

	public void setCoupon_id_3(String coupon_id_3) {
		this.coupon_id_3 = coupon_id_3;
	}

	public String getCoupon_id_4() {
		return coupon_id_4;
	}

	public void setCoupon_id_4(String coupon_id_4) {
		this.coupon_id_4 = coupon_id_4;
	}

	public String getCoupon_count() {
		return coupon_count;
	}

	public void setCoupon_count(String coupon_count) {
		this.coupon_count = coupon_count;
	}
	
	public Date getApplyTime() {
		return applyTime;
	}
	
	public String getApplyTimeStr() {
		if(null != applyTime){
			return DateUtil.format(applyTime);
		}
		return "";
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public RechargeRecord getRechargeRecord() {
		return rechargeRecord;
	}

	public void setRechargeRecord(RechargeRecord rechargeRecord) {
		this.rechargeRecord = rechargeRecord;
	}
}