package com.vix.nvix.customer.service;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.nvix.customer.vo.CustomerAccountStatisticsVo;

public interface ICustomerService extends IBaseHibernateService {

	public List<CustomerAccountStatisticsVo> getCustomerTypeList(Map<String, Object> params) throws Exception;

	public List<CustomerAccountStatisticsVo> getCustomerStageList(Map<String, Object> params) throws Exception;
	/** 查询 客户关系管理 > 客户管理 > 客户统计 饼图 **/
	public List<CustomerAccountStatisticsVo> getCustomerListByControlSQL(Map<String, Object> params) throws Exception;
	/** 查询 客户关系管理 > 客户管理 > 客户统计 饼图2 **/
	public List<CustomerAccountStatisticsVo> getCustomerListByControlSQLStr(Map<String, Object> params) throws Exception;
	/** 客户关系管理 > 客户分析 > 客户种类分布的pie图数据查询  ***/
	public Map<String, Object> customerDatePieQuery(Map<String, Object> hsMap)throws Exception;
	/** 查询 客户关系管理 > 客户管理 > 客户统计 >合同排行 barView 根据不同的controlSQL topNum queryTime参数,查询不同的数据  ***/
	public Map<String, Object> queryContractTopBar(Map<String, Object> hsMap)throws Exception;
}
