package com.vix.nvix.customer.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.nvix.common.base.dao.IVixntBaseDao;
import com.vix.nvix.common.base.hql.SqlReturnDataHandle;
import com.vix.nvix.customer.hql.CustomerHqlProvider;
import com.vix.nvix.customer.service.ICustomerService;
import com.vix.nvix.customer.vo.CustomerAccountStatisticsVo;

@Service("customerService")
public class CustomerServiceImpl extends BaseHibernateServiceImpl implements ICustomerService {

	private static final long serialVersionUID = 1L;
	
	@Resource(name = "customerHqlProvider")
	private CustomerHqlProvider customerHqlProvider;
	@Autowired
	private IVixntBaseDao vixntBaseDao;
	@Resource(name = "sqlReturnDataHandle")
	private SqlReturnDataHandle sqlReturnDataHandle;//List<Object[]>结果处理者

	@Override
	public List<CustomerAccountStatisticsVo> getCustomerTypeList(Map<String, Object> params) throws Exception {
		List<CustomerAccountStatisticsVo> customerAccountStatisticsVoList = new ArrayList<CustomerAccountStatisticsVo>();
		StringBuilder hql = customerHqlProvider.findCustomerTypeList(params);
		List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
		if (null != objectList && objectList.size() > 0) {
			for (Object[] obj : objectList) {
				if (obj.length >= 2) {//其他地方要用 groupByID 所以 obj.length >= 2 取前两列
					CustomerAccountStatisticsVo customerAccountStatisticsVo = new CustomerAccountStatisticsVo();
					if (obj[0] != null) {
						customerAccountStatisticsVo.setCustomerTypeName(String.valueOf(obj[0]));
					}
					if (obj[1] != null) {
						customerAccountStatisticsVo.setRegnum(Long.parseLong(String.valueOf(obj[1])));
					}
					customerAccountStatisticsVoList.add(customerAccountStatisticsVo);
				}
			}
		}
		return customerAccountStatisticsVoList;
	}

	@Override
	public List<CustomerAccountStatisticsVo> getCustomerStageList(Map<String, Object> params) throws Exception {
		List<CustomerAccountStatisticsVo> customerAccountStatisticsVoList = new ArrayList<CustomerAccountStatisticsVo>();
		StringBuilder hql = customerHqlProvider.findCustomerStageList(params);
		List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
		if (null != objectList && objectList.size() > 0) {
			for (Object[] obj : objectList) {
				if (obj.length >= 2) {//其他地方要用 groupByID 所以 obj.length >= 2 取前两列
					CustomerAccountStatisticsVo customerAccountStatisticsVo = new CustomerAccountStatisticsVo();
					if (obj[0] != null) {
						customerAccountStatisticsVo.setCustomerStageName(String.valueOf(obj[0]));
					}
					if (obj[1] != null) {
						customerAccountStatisticsVo.setRegnum(Long.parseLong(String.valueOf(obj[1])));
					}
					customerAccountStatisticsVoList.add(customerAccountStatisticsVo);
				}
			}
		}
		return customerAccountStatisticsVoList;
	}
	
	/** 查询 客户关系管理 > 客户管理 > 客户统计 饼图  (本方法查询目标的type必须以customer开头如'customerRelationshipClass','关系等级') **/
	@Override
	public List<CustomerAccountStatisticsVo> getCustomerListByControlSQL(Map<String, Object> params) throws Exception {
		List<CustomerAccountStatisticsVo> customerAccountStatisticsVoList = new ArrayList<CustomerAccountStatisticsVo>();
		StringBuilder hql = customerHqlProvider.findCustomerListByControlSQL(params);
		List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
		if (null != objectList && objectList.size() > 0) {
			for (Object[] obj : objectList) {
				if (obj.length >= 2) {//其他地方要用 groupByID 所以 obj.length >= 2 取前两列
					CustomerAccountStatisticsVo customerAccountStatisticsVo = new CustomerAccountStatisticsVo();
					if (obj[0] != null) {
						customerAccountStatisticsVo.setCustomerStageName(String.valueOf(obj[0]));
					}
					if (obj[1] != null) {
						customerAccountStatisticsVo.setRegnum(Long.parseLong(String.valueOf(obj[1])));
					}
					customerAccountStatisticsVoList.add(customerAccountStatisticsVo);
				}
			}
		}
		return customerAccountStatisticsVoList;
	}
	/** 查询 客户关系管理 > 客户管理 > 客户统计 饼图2 **/
	@Override
	public List<CustomerAccountStatisticsVo> getCustomerListByControlSQLStr(Map<String, Object> params) throws Exception {
		List<CustomerAccountStatisticsVo> customerAccountStatisticsVoList = new ArrayList<CustomerAccountStatisticsVo>();
		StringBuilder hql = customerHqlProvider.findCustomerListByControlSQLStr(params);
		List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
		if (null != objectList && objectList.size() > 0) {
			for (Object[] obj : objectList) {
				if (obj.length >= 2) {//其他地方要用 groupByID 所以 obj.length >= 2 取前两列
					CustomerAccountStatisticsVo customerAccountStatisticsVo = new CustomerAccountStatisticsVo();
					if (obj[0] != null) {
						customerAccountStatisticsVo.setCustomerStageName(String.valueOf(obj[0]));
					}
					if (obj[1] != null) {
						customerAccountStatisticsVo.setRegnum(Long.parseLong(String.valueOf(obj[1])));
					}
					customerAccountStatisticsVoList.add(customerAccountStatisticsVo);
				}
			}
		}
		return customerAccountStatisticsVoList;
	}
	/** 客户关系管理 > 客户分析 > 客户种类分布的pie图数据查询  ***/
	@Override
	public Map<String, Object> customerDatePieQuery(Map<String, Object> params)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder();
		String type = (String)params.get("type");
		/** 和NvixCustomerStatisticsAction的goCustomerAccountDistribute()方法一样判断 **/
		if (StringUtils.isNotEmpty(type)) {
			if ("customerType".equals(type)) {
				hql = customerHqlProvider.findCustomerTypeList(params);
			}else if ("customerStage".equals(type)) {
				hql = customerHqlProvider.findCustomerStageList(params);
			}else if (type.startsWith("customer") && (!"customerType".equals(type)) && (!"customerStage".equals(type))) {
				hql = customerHqlProvider.findCustomerListByControlSQL(params);
			}else if (type.startsWith("cstomer")) {
				hql = customerHqlProvider.findCustomerListByControlSQLStr(params);
			}
		}
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		StringBuffer sBufferJson = sqlReturnDataHandle.topPieBynameAdnumAdgroupbyHasOtherTopNumAdTotalNum(dataList,"10");//top10 11 12 合并为其他
		hsMapReturn.put("sBufferJsonToString",sBufferJson.toString());
		return hsMapReturn;
	}
	/** 查询 客户关系管理 > 客户管理 > 客户统计 >合同排行 barView 根据不同的controlSQL topNum queryTime参数,查询不同的数据  ***/
	@Override
	public Map<String, Object> queryContractTopBar(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = customerHqlProvider.queryContractTopBar(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		StringBuffer sBufferJson = sqlReturnDataHandle.topBarXaxisBysqlStrnameAdsqlDounum(dataList,(String)hsMap.get("topNum"));
		hsMapReturn.put("sBufferJsonToString",sBufferJson.toString());
		return hsMapReturn;
	}
}
