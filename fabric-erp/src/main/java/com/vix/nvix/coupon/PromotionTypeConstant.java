package com.vix.nvix.coupon;

import java.util.LinkedHashMap;
import java.util.Map;

/** 促销策略 */
public class PromotionTypeConstant {
	/** 促销价(第一级别) */
	/*
	 * public final static String PROMOTION_PRICE = "PROMOTION_PRICE";
	 *//** 价格折扣(第一级别) */
	/*
	 * public final static String PROMOTION_DISCOUNT = "PROMOTION_DISCOUNT";
	 *//** 全场/分类/品牌折扣(第一级别) */
	/*
	 * public final static String PROMOTION_DISCOUNTCB = "PROMOTION_DISCOUNTCB";
	 *//** 秒杀(第一级别) */
	/*
	 * public final static String PROMOTION_SECONDSALE = "PROMOTION_SECONDSALE";
	 *//**
		 * 捆绑销售(第一级别) public final static String PROMOTION_BINDSALE =
		 * "PROMOTION_BINDSALE";
		 */
	/*
	*//** 买赠(第一级别) *//*
						 * public final static String PROMOTION_SALEGIFT =
						 * "PROMOTION_SALEGIFT";
						 */

	/** 满减(第二级别) */
	public final static String PROMOTION_FULLREDUCE = "PROMOTION_FULLREDUCE";
	/** 满赠(第二级别) */
	public final static String PROMOTION_FULLGIFT = "PROMOTION_FULLGIFT";
	/** 积分翻倍(第二级别) *//*
						 * public final static String PROMOTION_DOUBLEINTEGRAL =
						 * "PROMOTION_DOUBLEINTEGRAL";
						 */
	/** 免运费(第二级别) */
	/*
	 * public final static String PROMOTION_FREEFREIGHT =
	 * "PROMOTION_FREEFREIGHT";
	 *//** 优惠券(第二级别) *//*
						 * public final static String PROMOTION_COUPON =
						 * "PROMOTION_COUPON";
						 */

	private static Map<String, String> promotionType = new LinkedHashMap<String, String>();

	public static Map<String, String> getPromotionType() {
		if (promotionType.size() <= 0) {
			/** 整单促销 */
			promotionType.put(PromotionTypeConstant.PROMOTION_FULLREDUCE, "ec_promotionType_fullreduce");
			promotionType.put(PromotionTypeConstant.PROMOTION_FULLGIFT, "ec_promotionType_fullgift");
			/*
			 * promotionType.put(PromotionTypeConstant.PROMOTION_DOUBLEINTEGRAL,
			 * "ec_promotionType_doubleintegral");
			 * promotionType.put(PromotionTypeConstant.PROMOTION_FREEFREIGHT,
			 * "ec_promotionType_freefreight");
			 * promotionType.put(PromotionTypeConstant.PROMOTION_COUPON,
			 * "ec_promotionType_coupon");
			 * 
			 *//** 单品促销 */
			/*
			 * promotionType.put(PromotionTypeConstant.PROMOTION_PRICE,
			 * "ec_promotionType_price");
			 * promotionType.put(PromotionTypeConstant.PROMOTION_DISCOUNT,
			 * "ec_promotionType_discount");
			 * promotionType.put(PromotionTypeConstant.PROMOTION_SECONDSALE,
			 * "ec_promotionType_secondsale");
			 * promotionType.put(PromotionType.PROMOTION_BINDSALE,
			 * "ec_promotionType_bindsale");
			 * promotionType.put(PromotionTypeConstant.PROMOTION_SALEGIFT,
			 * "ec_promotionType_salegift");
			 *//** 全场/分类/品牌促销 *//*
								 * promotionType.put(PromotionTypeConstant.
								 * PROMOTION_DISCOUNTCB,
								 * "ec_promotionType_discountcb");
								 */
		}
		return promotionType;
	}
	/** 流通商品 */
	public static String GOODS = "goods";
	/** 固定资产 */
	public static String FIXEDASSETS = "fixedassets";
	/** 原材料 */
	public static String MATERIAL = "material";
	/** 外购零件 */
	public static String PURCHASEPART = "purchasepart";
	/** 外购备件 */
	public static String PURCHASEBACKUPPART = "purchasebackuppart";
	/** 加工部件 */
	public static String MACHININGPART = "machiningpart";
	/** 半成品 */
	public static String SEMIFINISHED = "semifinished";
	/** 组装件 */
	public static String ASSEMBLYPART = "assemblypart";
	/** 产成品 */
	public static String FINISHEDGOODS = "finishedgoods";
	/** 设备 */
	public static String EQUIPMENT = "equipment";
	/** 办公用品 */
	public static String OFFICESUPPLY = "officesupply";
	/** 售后服务 */
	public static String AFTERSERVICE = "afterservice";
	/** 维修服务 */
	public static String MAINTENANCESERVICE = "maintenanceservice";
	/** 外协服务 */
	public static String OUTSIDESERVICE = "outsideservice";
	public static Map<String, String> getItemType() {
		if (promotionType.size() <= 0) {
			promotionType.put(PromotionTypeConstant.MATERIAL, "mdm_material");
			promotionType.put(PromotionTypeConstant.PURCHASEPART, "mdm_purchasepart");
			promotionType.put(PromotionTypeConstant.PURCHASEBACKUPPART, "mdm_purchasebackuppart");
			promotionType.put(PromotionTypeConstant.MACHININGPART, "mdm_machiningpart");
			promotionType.put(PromotionTypeConstant.SEMIFINISHED, "mdm_semifinished");
			promotionType.put(PromotionTypeConstant.ASSEMBLYPART, "mdm_assemblypart");
			promotionType.put(PromotionTypeConstant.FINISHEDGOODS, "mdm_finishedgoods");
			promotionType.put(PromotionTypeConstant.EQUIPMENT, "mdm_equipment");
			promotionType.put(PromotionTypeConstant.OFFICESUPPLY, "mdm_officesupply");
			promotionType.put(PromotionTypeConstant.AFTERSERVICE, "mdm_afterservice");
			promotionType.put(PromotionTypeConstant.MAINTENANCESERVICE, "mdm_maintenanceservice");
			promotionType.put(PromotionTypeConstant.OUTSIDESERVICE, "mdm_outsideservice");
			promotionType.put(PromotionTypeConstant.GOODS, "mdm_goods");
			promotionType.put(PromotionTypeConstant.FIXEDASSETS, "mdm_fixedassets");
		}
		return promotionType;
	}
}
