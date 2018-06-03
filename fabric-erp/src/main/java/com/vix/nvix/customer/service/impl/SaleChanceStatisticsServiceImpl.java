package com.vix.nvix.customer.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.nvix.common.base.dao.IVixntBaseDao;
import com.vix.nvix.common.base.hql.SqlReturnDataHandle;
import com.vix.nvix.customer.hql.SaleChanceHqlProvider;
import com.vix.nvix.customer.service.ISaleChanceStatisticsService;

@Service("saleChanceStatisticsService")
public class SaleChanceStatisticsServiceImpl extends BaseHibernateServiceImpl implements ISaleChanceStatisticsService {

	private static final long serialVersionUID = 1L;
	
	@Resource(name = "saleChanceHqlProvider")
	private SaleChanceHqlProvider saleChanceHqlProvider;
	@Autowired
	private IVixntBaseDao vixntBaseDao;
	@Resource(name = "sqlReturnDataHandle")
	private SqlReturnDataHandle sqlReturnDataHandle;//List<Object[]>结果处理者

	/** 查询 客户关系管理 > 售前管理 > 销售机会统计 饼图分布  ***/
	@Override
	public Map<String, Object> queryPieView(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = saleChanceHqlProvider.queryPieView(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		StringBuffer sBufferJson = sqlReturnDataHandle.topPieBysqlStrnameAdsqlDounumNoOtherTopNum(dataList);
		hsMapReturn.put("sBufferJsonToString",sBufferJson.toString());
		return hsMapReturn;
	}
	/** 客户关系管理 > 销售跟踪 > 机会发现时间月份统计 视图查询 **/
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> discoveryTimeMonthViewQuery(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = saleChanceHqlProvider.discoveryTimeMonthViewQuery(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		ArrayList<String> arrDate = (ArrayList<String>)hsMap.get("timeArr");
		ArrayList<String> allMonthsOfTime = getAllMonthsOfTime(arrDate);
		ArrayList<String> valueArr = sqlReturnDataHandle.queryViewByYmdTimeAndInteger(dataList,allMonthsOfTime );
		Gson gosn = new Gson();
		StringBuffer returnBufferJson = new StringBuffer();
		returnBufferJson.append("{"+"\"nameArr\":" + gosn.toJson(allMonthsOfTime));
		returnBufferJson.append(",\"valueArr\":" + valueArr.toString() + "}" );
		hsMapReturn.put("sBufferJsonToString",returnBufferJson.toString());
		return hsMapReturn;
	}
	//获得一个范围日期里面的每一个月份2018-01格式
	public ArrayList<String> getAllMonthsOfTime(ArrayList<String> timeArr) throws ParseException{
		ArrayList<String> arrDate = new ArrayList<String>();
		Date start = new SimpleDateFormat("yyyy-MM-dd").parse(timeArr.get(0));
		Date end = new SimpleDateFormat("yyyy-MM-dd").parse(timeArr.get(timeArr.size()-1));
		if(start.getTime() < end.getTime()){
			Calendar dd = Calendar.getInstance();
			dd.setTime(start);
			while(dd.getTime().before(end)){//判断是否到结束日期
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				String str = sdf.format(dd.getTime());
				arrDate.add(str);
				dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
			}	
		}else if(start.getTime() == end.getTime() ){
			String substrQ = timeArr.get(0).substring(0, 7);
			arrDate.add(substrQ);
		}
		return arrDate;
	}
	/** 客户关系管理 > 销售跟踪 > 机会发现时间月份统计 视图查询 **/
	@Override
	public Map<String, Object> saleChanceDatePieQuery(Map<String, Object> params)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = saleChanceHqlProvider.queryPieView(params);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		StringBuffer sBufferJson = sqlReturnDataHandle.topPieBynameAdnumAdgroupbyHasOtherTopNumAdTotalNum(dataList,"10");//top10 11 12 合并为其他
		hsMapReturn.put("sBufferJsonToString",sBufferJson.toString());
		return hsMapReturn;
	}
	/** 客户关系管理 > 销售跟踪 > 负责人/机会状态统计 视图查询 **/
	@Override
	public Map<String, Object> chargerDivisionStatusViewQuery(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = saleChanceHqlProvider.chargerDivisionStatusViewQuery(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		if((hsMap.get("type")+"").toString().equals("queryMaxNum") || (hsMap.get("type")+"").toString().equals("findMaxNum") ){//queryMaxNum  负责人/机会状态统计的查询最大值 
			Double maxNum = sqlReturnDataHandle.queryNumToDouble(dataList);//findMaxNum  机会可能性/状态统计 最大值
			hsMapReturn.put("sBufferJsonToString","{\"maxNum\":"+maxNum+"}" );
		}else {
			hsMapReturn.put("sBufferJsonToString",(sqlReturnDataHandle.queryJsonByNamestrAdNumstr(dataList)).toString());
		}
		return hsMapReturn;
	}
	/** 客户关系管理 > 销售跟踪 > 各阶段机会数量漏斗 **/
	@Override
	public Map<String, Object> saleChanceStageFunnelQuery(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = saleChanceHqlProvider.saleChanceStageFunnelQuery(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		hsMapReturn.put("sBufferJsonToString",(sqlReturnDataHandle.queryJsonByNamestrAdNumstr(dataList)).toString());
		return hsMapReturn;
	}
	/** 客户关系管理 > 销售跟踪 > 销售活动类型/月份分布相关数组查询 **/
	@Override
	public Map<String, Object> activityDivideMonthDrawQuery(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = saleChanceHqlProvider.activityDivideMonthDrawQuery(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		hsMapReturn.put("sBufferJsonToString",(sqlReturnDataHandle.queryObjectNameAdIdArrJson(dataList)).toString());
		return hsMapReturn;
	}
	/** 客户关系管理 > 销售跟踪 > 应收款对应客户排行TOP20... 视图查询 **/
	@Override
	public Map<String, Object> backPlanAmountCustomerTopQuery(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = saleChanceHqlProvider.backPlanAmountCustomerTopQuery(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		StringBuffer sBufferJson = sqlReturnDataHandle.queryTopViewBysqlStrnameAndsqlDoublenum(dataList,(String)hsMap.get("topNum"));
		hsMapReturn.put("sBufferJsonToString",sBufferJson.toString());
		return hsMapReturn;
	}
}
