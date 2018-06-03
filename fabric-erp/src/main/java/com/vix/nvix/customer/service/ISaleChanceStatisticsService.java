package com.vix.nvix.customer.service;

import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

public interface ISaleChanceStatisticsService extends IBaseHibernateService {
	/** 查询 客户关系管理 > 售前管理 > 销售机会统计 饼图分布  ***/
	public Map<String, Object> queryPieView(Map<String, Object> hsMap)throws Exception;
	/** 客户关系管理 > 销售跟踪 > 机会发现时间月份统计 视图查询 **/
	public Map<String, Object> discoveryTimeMonthViewQuery(Map<String, Object> hsMap)throws Exception;
	/** 客户关系管理 > 销售跟踪 > 机会发现时间月份统计 视图查询 **/
	public Map<String, Object> saleChanceDatePieQuery(Map<String, Object> params)throws Exception;
	/** 客户关系管理 > 销售跟踪 > 负责人/机会状态统计 视图查询 **/
	public Map<String, Object> chargerDivisionStatusViewQuery(Map<String, Object> hsMap)throws Exception;
	/** 客户关系管理 > 销售跟踪 > 各阶段机会数量漏斗 **/
	public Map<String, Object> saleChanceStageFunnelQuery(Map<String, Object> hsMap)throws Exception;
	/** 客户关系管理 > 销售跟踪 > 销售活动类型/月份分布相关数组查询 **/
	public Map<String, Object> activityDivideMonthDrawQuery(Map<String, Object> hsMap)throws Exception;
	/** 客户关系管理 > 销售跟踪 > 应收款对应客户排行TOP20... 视图查询 **/
	public Map<String, Object> backPlanAmountCustomerTopQuery(Map<String, Object> hsMap)throws Exception;
}
