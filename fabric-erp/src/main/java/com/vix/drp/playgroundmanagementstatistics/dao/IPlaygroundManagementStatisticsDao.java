/**
 * 
 */
package com.vix.drp.playgroundmanagementstatistics.dao;

import java.util.List;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.drp.playgroundmanagementstatistics.vo.AgeStatistic;
import com.vix.drp.playgroundmanagementstatistics.vo.ClassifiedRevenue;
import com.vix.drp.playgroundmanagementstatistics.vo.ConsumerPeopleReport;
import com.vix.drp.playgroundmanagementstatistics.vo.CustomerCount;
import com.vix.drp.playgroundmanagementstatistics.vo.DayIncomeSummary;
import com.vix.drp.playgroundmanagementstatistics.vo.GoodsStatistics;
import com.vix.drp.playgroundmanagementstatistics.vo.GradeRatio;
import com.vix.drp.playgroundmanagementstatistics.vo.GroupIncomeStatement;
import com.vix.drp.playgroundmanagementstatistics.vo.MonthAccountBalance;
import com.vix.drp.playgroundmanagementstatistics.vo.SexProportion;
import com.vix.drp.rides.entity.TranLog;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
public interface IPlaygroundManagementStatisticsDao extends IBaseHibernateDao {

	/**
	 * 获取机台交易信息
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public List<TranLog> findAllTranLog(String hql) throws Exception;

	/**
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public List<DayIncomeSummary> findDayIncomeSummary(String hql) throws Exception;

	/**
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public List<ConsumerPeopleReport> findConsumerPeopleReport(String hql) throws Exception;

	/**
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public List<MonthAccountBalance> findMonthAccountBalance(String hql) throws Exception;

	/**
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public List<GroupIncomeStatement> findGroupIncomeStatement(String hql) throws Exception;

	/**
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public List<GoodsStatistics> findGoodsStatistics(String hql) throws Exception;

	public List<GradeRatio> findGradeRatio(String hql) throws Exception;

	public List<SexProportion> findSexProportionList(String hql) throws Exception;

	public List<AgeStatistic> findAgeStatisticList(String hql) throws Exception;

	/**
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> findEquipmentCoinNumber(String hql) throws Exception;

	/**
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public List<ClassifiedRevenue> findClassifiedRevenue(String hql) throws Exception;

	/**
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public Double findSumAmount(String hql) throws Exception;

	/**
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public List<CustomerCount> findCustomerCount(String hql) throws Exception;
}
