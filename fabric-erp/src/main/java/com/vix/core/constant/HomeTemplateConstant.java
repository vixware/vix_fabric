package com.vix.core.constant;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 工作台模板常量
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.core.constant.HomeTemplateConstant
 *
 * @date 2018年1月8日
 */
public class HomeTemplateConstant {

	/** 协同 */
	public static final String NVIXNT_OA = "NVIXNT_OA";
	/** 会员 */
	public static final String NVIXNT_CRM = "NVIXNT_CRM";
	/** 供应链 */
	public static final String NVIXNT_CHAIN = "NVIXNT_CHAIN";

	private static Map<String, String> homeTemplateMap = new LinkedHashMap<String, String>();

	public static Map<String, String> getHomeTemplateMap() {
		if (homeTemplateMap.size() <= 0) {
			homeTemplateMap.put(HomeTemplateConstant.NVIXNT_OA, "协同办公");
			homeTemplateMap.put(HomeTemplateConstant.NVIXNT_CRM, "会员管理");
			homeTemplateMap.put(HomeTemplateConstant.NVIXNT_CHAIN, "供应链");
		}
		return homeTemplateMap;
	}
}
