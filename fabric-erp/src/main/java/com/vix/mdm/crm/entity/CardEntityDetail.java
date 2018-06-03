package com.vix.mdm.crm.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.item.entity.Item;

/**
 * 会员卡定义详情
 * 
 * @类全名 com.vix.mdm.crm.entity.CardEntity
 * 
 * @author zhanghaibing
 *
 * @date 2017年10月27日
 */
public class CardEntityDetail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 会员卡定义
	 */
	private CardEntity cardEntity;
	/**
	 * 服务项目
	 */
	private Item item;
	/**
	 * 服务次数
	 */
	private Long num;
	public CardEntity getCardEntity() {
		return cardEntity;
	}
	public void setCardEntity(CardEntity cardEntity) {
		this.cardEntity = cardEntity;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	public String getItemname(){
		if(item != null){
			return item.getName();
		}
		return "";
	}
	public String getServiceContent(){
		if(null != item){
			return item.getServiceContent();
		}
		return "";
	}
}
