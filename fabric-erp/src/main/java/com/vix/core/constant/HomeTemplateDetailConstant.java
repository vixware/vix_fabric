package com.vix.core.constant;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 工作台模板模块常量
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.core.constant.HomeTemplateDetailConstant
 *
 * @date 2018年1月26日
 */
public class HomeTemplateDetailConstant {

	/** 任务 */
	public static final String NVIXNT_TASK = "NVIXNT_TASK";
	/** 提醒 */
	public static final String NVIXNT_MESSAGE = "NVIXNT_MESSAGE";
	/** 日程 */
	public static final String NVIXNT_CALENDAR = "NVIXNT_CALENDAR";
	/** 项目 */
	public static final String NVIXNT_PROJECT = "NVIXNT_PROJECT";
	/** 文档 */
	public static final String NVIXNT_DOCUMENT = "NVIXNT_DOCUMENT";

	private static Map<String, String> homeTemplateDetailMap = new LinkedHashMap<String, String>();

	public static Map<String, String> getHomeTemplateDetailMap() {
		if (homeTemplateDetailMap.size() <= 0) {
			homeTemplateDetailMap.put(HomeTemplateDetailConstant.NVIXNT_TASK, "任务");
			homeTemplateDetailMap.put(HomeTemplateDetailConstant.NVIXNT_MESSAGE, "提醒");
			homeTemplateDetailMap.put(HomeTemplateDetailConstant.NVIXNT_CALENDAR, "日程");
			homeTemplateDetailMap.put(HomeTemplateDetailConstant.NVIXNT_PROJECT, "项目");
			homeTemplateDetailMap.put(HomeTemplateDetailConstant.NVIXNT_DOCUMENT, "文档");
		}
		return homeTemplateDetailMap;
	}
}
