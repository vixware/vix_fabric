package com.vix.nvix.common.message.constant;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 短信接口公司
 * 
 * @author Administrator
 *
 */
public class MessageCompanyConstant {

	/** 奥易互通 */
	public final static String AO_YI_HU_TONG = "AO_YI_HU_TONG";
	/** 创蓝文化 */
	public final static String CHUANG_LAN_WEN_HUA = "CHUANG_LAN_WEN_HUA";
	/** 华信科技 */
	public final static String HUA_XIN_KE_JI = "HUA_XIN_KE_JI";
	/** 美联软通 */
	public final static String MEI_LIAN_RUAN_TONG = "MEI_LIAN_RUAN_TONG";

	private static Map<String, String> messageCompany = new LinkedHashMap<String, String>();

	public static Map<String, String> getMessageCompany() {
		if (messageCompany.size() <= 0) {
			/** 奥易互通 */
			messageCompany.put(MessageCompanyConstant.AO_YI_HU_TONG, "奥易互通");
			/** 创蓝文化 */
			messageCompany.put(MessageCompanyConstant.CHUANG_LAN_WEN_HUA, "创蓝文化");
			/** 华信科技 */
			messageCompany.put(MessageCompanyConstant.HUA_XIN_KE_JI, "华信科技");
			/** 美联软通*/
			messageCompany.put(MessageCompanyConstant.MEI_LIAN_RUAN_TONG, "美联软通");
		}
		return messageCompany;
	}
}
