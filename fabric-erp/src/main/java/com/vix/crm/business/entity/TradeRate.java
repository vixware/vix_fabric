package com.vix.crm.business.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 订单评价 com.vix.crm.business.entity.TradeRate
 *
 * @author zhanghaibing
 *
 * @date 2014年12月27日
 */
public class TradeRate extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long orderId;//交易ID
	private Long orderDetailId;//子订单ID
	private String role;//评价者角色.可选值:seller(卖家),buyer(买家)
	private String nick;//评价者昵称
	private String result;//评价结果,可选值:good(好评),neutral(中评),bad(差评)
	private String ratedNick;//被评价者昵称
	private String itemTitle;//商品标题
	private Double itemPrice;//商品价格,精确到2位小数;单位:元.如:200.07，表示:200元7分
	private String content;//评价内容,最大长度:500个汉字
	private String reply;//评价解释,最大长度:500个汉字
	private Long numId;//商品的数字ID
	private String validScore;//评价信息是否用于记分， 可取值：true(参与记分)和false(不参与记分)

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRatedNick() {
		return ratedNick;
	}

	public void setRatedNick(String ratedNick) {
		this.ratedNick = ratedNick;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Long getNumId() {
		return numId;
	}

	public void setNumId(Long numId) {
		this.numId = numId;
	}

	public String getValidScore() {
		return validScore;
	}

	public void setValidScore(String validScore) {
		this.validScore = validScore;
	}

}
