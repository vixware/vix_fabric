package com.vix.ebusiness.statisticalAnalysis.service;

import java.util.List;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.drp.playgroundmanagementstatistics.vo.AgeStatistic;
import com.vix.drp.playgroundmanagementstatistics.vo.ClassifiedRevenue;
import com.vix.drp.playgroundmanagementstatistics.vo.ConsumerPeopleReport;
import com.vix.drp.playgroundmanagementstatistics.vo.CustomerCount;
import com.vix.drp.playgroundmanagementstatistics.vo.DayIncomeSummary;
import com.vix.drp.playgroundmanagementstatistics.vo.EquipmentCoinNumber;
import com.vix.drp.playgroundmanagementstatistics.vo.GoodsStatistics;
import com.vix.drp.playgroundmanagementstatistics.vo.GradeRatio;
import com.vix.drp.playgroundmanagementstatistics.vo.GroupIncomeStatement;
import com.vix.drp.playgroundmanagementstatistics.vo.MonthAccountBalance;
import com.vix.drp.playgroundmanagementstatistics.vo.SexProportion;
import com.vix.drp.rides.entity.TranLog;

public interface IStatisticalAnalysisService extends IBaseHibernateService {

	public List<TranLog> findTranLogByHql(String hql) throws Exception;

	public List<ConsumerPeopleReport> findConsumerPeopleReport(String hql) throws Exception;

	public List<ClassifiedRevenue> findClassifiedRevenue(String hql) throws Exception;

	public List<GoodsStatistics> findGoodsStatistics(String hql) throws Exception;

	public List<GradeRatio> findGradeRatio(String hql) throws Exception;

	public List<SexProportion> findSexProportionList(String hql) throws Exception;

	public List<EquipmentCoinNumber> findEquipmentCoinNumber(String hql) throws Exception;

	public List<CustomerCount> findCustomerCount(String hql) throws Exception;

	public List<AgeStatistic> findAgeStatistic(String hql) throws Exception;

	public List<MonthAccountBalance> findMonthAccountBalance(String hql) throws Exception;

	public List<GroupIncomeStatement> findGroupIncomeStatement(String hql) throws Exception;

	public List<DayIncomeSummary> findDayIncomeSummaryByHql(String hql) throws Exception;

	public Double findSumAmountByHql(String hql) throws Exception;
}
