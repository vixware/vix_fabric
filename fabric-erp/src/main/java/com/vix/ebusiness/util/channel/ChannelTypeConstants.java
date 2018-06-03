package com.vix.ebusiness.util.channel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChannelTypeConstants implements Serializable {

	private static final long serialVersionUID = -3628950314984539834L;

	// 自有商店
	public static final int CLOUDSHOPS = 0;
	// 淘宝
	public static final int TAOBAO = 1;
	// 拍拍
	public static final int PAIPAI = 2;
	// 京东
	public static final int JINGDONG = 3;
	// 当当
	public static final int DANGDANG = 4;
	// 亚马逊
	public static final int AMAZON = 5;
	// 1号店
	public static final int YIHAODIAN = 6;
	/**
	 * 淘宝店C2C
	 */
	public static final String TB_C2C_STORE = "0";

	/**
	 * 淘宝店B2C - 旗舰店
	 */
	public static final String TB_FLAGSHIP_STORE = "1";

	/**
	 * 淘宝店 B2C - 专卖店
	 */
	public static final String TB_SPECIALTY_STORE = "2";

	/**
	 * 淘宝店B2C - 专营店
	 */
	public static final String TB_BOUTIQUE = "3";

	/**
	 * 京东 - 仓储和配送完全由京东托管
	 */
	public static final String JD_FBP = "4";

	/**
	 * 京东 - 商家收到订单后发货给京东，由京东配送给买家
	 */
	public static final String JD_LBP = "5";

	/**
	 * 京东 - 仓储和配送完全由商家自己管理
	 */
	public static final String JD_SOP = "6";
	/**
	 * 
	 */
	public static final String JD_SOPL = "7";

	/**
	 * 拍拍 - c2c集市
	 */
	public static final String PAIPAI_C2C = "8";

	/**
	 * 拍拍 - QQ商城
	 */
	public static final String PAIPAI_STORE = "9";
	/**
	 * 当当B2C
	 */
	public static final String DANGDANG_B2C = "10";
	/**
	 * 亚马逊FBA
	 */
	public static final String AMAZON_FBA = "11";
	/**
	 * 亚马逊自配送
	 */
	public static final String AMAZON_B2C = "12";
	/**
	 * 1号店代售
	 */
	public static final String YIHAODIAN_DAISHOU = "13";
	/**
	 * 1号店FBY
	 */
	public static final String YIHAODIAN_FBY = "14";

	/**
	 * QQ网购自配送
	 */
	public static final String QQ_B2C = "15";

	/**
	 * 商家自发货模式
	 */
	public static final String VJIA_B2C = "16";

	/**
	 * V+中转仓模式
	 */
	public static final String VJIA_LBP = "17";

	/**
	 * 新蛋SOP
	 */
	public static final String NEWEGG_SOP = "18";

	/**
	 * 新蛋LBP
	 */
	public static final String NEWEGG_LBP = "19";

	public static String toChannelTypeModelString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("=--请选择--");
		buffer.append(",");
		buffer.append(CLOUDSHOPS).append("=云商店");
		buffer.append(",");
		buffer.append(TAOBAO).append("=淘宝");
		buffer.append(",");
		buffer.append(PAIPAI).append("=拍拍网");
		buffer.append(",");
		buffer.append(JINGDONG).append("=京东商城");
		buffer.append(",");
		buffer.append(DANGDANG).append("=当当网");
		buffer.append(",");
		buffer.append(AMAZON).append("=卓越亚马逊");
		buffer.append(",");
		buffer.append(YIHAODIAN).append("=1号店");
		return buffer.toString();
	}

	public static String toMallTypeSelectModel(Integer channelType) {
		switch (channelType) {
		case (TAOBAO):
			return TB_FLAGSHIP_STORE + "=淘宝旗舰店," + "" + TB_SPECIALTY_STORE + "=淘宝专卖店," + "" + TB_BOUTIQUE + "=淘宝专营店," + TB_C2C_STORE + "=淘宝店集市店";
		case (PAIPAI):
			return PAIPAI_C2C + "=C2C集市," + PAIPAI_STORE + "=QQ商城";
		case (JINGDONG):
			return JD_LBP + "=LBP," + JD_SOP + "=SOP," + JD_SOPL + "=SOPL";
		case (DANGDANG):
			return DANGDANG_B2C + "=B2C";
		case (AMAZON):
			return AMAZON_B2C + "=自配送," + AMAZON_FBA + "=FBA";
		case (YIHAODIAN):
			return YIHAODIAN_DAISHOU + "=代售," + YIHAODIAN_FBY + "=FBY";
		default:
			return "";
		}
	}

	public static String getMallTypeLabel(int mallType) {

		switch (mallType) {
		case 0:
			return "淘宝店集市店";
		case 1:
			return "淘宝旗舰店";
		case 2:
			return "淘宝专卖店";
		case 3:
			return "淘宝专营店";
		case 4:
			return "FBP";
		case 5:
			return "LBP";
		case 6:
			return "SOP";
		case 7:
			return "SOPL";
		case 8:
			return "C2C集市";
		case 9:
			return "QQ商城";
		case 10:
			return "B2C店铺";
		case 11:
			return "FBA";
		case 12:
			return "自配送";
		case 13:
			return "代售";
		case 14:
			return "FBY";
		case 15:
			return "自配送";
		case 16:
			return "自配送";
		case 17:
			return "V+中转仓模式";
		case 18:
			return "新蛋SOP";
		case 19:
			return "新蛋LBP";
		default:
			return "";
		}
	}

	/**
	 * 根据渠道类型判断类目是否从租户本地获取
	 * 
	 * @param channelTypeId
	 * @return
	 */
	public static boolean isLocal(int channelTypeId) {
		boolean local = false;
		if (channelTypeId == ChannelTypeConstants.JINGDONG)
			local = true;
		return local;
	}

	public static List<Integer> getAllChannelType() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(JINGDONG);
		list.add(TAOBAO);
		return list;
	}
}
