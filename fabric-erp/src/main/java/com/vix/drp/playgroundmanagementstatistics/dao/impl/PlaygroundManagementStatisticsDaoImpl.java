/**
 * 
 */
package com.vix.drp.playgroundmanagementstatistics.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.drp.playgroundmanagementstatistics.dao.IPlaygroundManagementStatisticsDao;
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
 * 
 * com.vix.drp.playgroundmanagementstatistics.dao.impl.
 * PlaygroundManagementStatisticsDaoImpl
 *
 * @author bjitzhang
 *
 * @date 2014年8月6日
 */
@Service("playgroundManagementStatisticsDao")
public class PlaygroundManagementStatisticsDaoImpl extends BaseHibernateDaoImpl implements IPlaygroundManagementStatisticsDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<TranLog> findAllTranLog(String hql) throws Exception {
		List<TranLog> tranLogList = new ArrayList<TranLog>();
		Session session = getSession();
		Iterator it = session.createQuery(hql).iterate();
		while (it.hasNext()) {
			Object[] oc = (Object[]) it.next();
			if (oc != null) {
				TranLog tranLog = new TranLog();
				String macDepartName = (String) oc[0];
				String macName = (String) oc[1];
				Double amount = (Double) oc[2];
				tranLog.setMacDepartName(macDepartName);
				tranLog.setMacTypeName(macName);
				tranLog.setAmount(amount);
				tranLogList.add(tranLog);
			}
		}
		return tranLogList;
	}

	@Override
	public List<DayIncomeSummary> findDayIncomeSummary(String hql) throws Exception {
		List<DayIncomeSummary> dayIncomeSummaryList = new ArrayList<DayIncomeSummary>();
		Session session = getSession();
		Iterator<?> it = session.createQuery(hql).iterate();
		while (it.hasNext()) {
			Object[] oc = (Object[]) it.next();
			if (oc != null) {
				DayIncomeSummary dayIncomeSummary = new DayIncomeSummary();
				dayIncomeSummary.setName((String) oc[0]);
				dayIncomeSummary.setCash((Double) oc[1]);
				dayIncomeSummary.setPos((Double) oc[2]);
				dayIncomeSummary.setGroupBuying((Double) oc[3]);
				dayIncomeSummary.setRealIncomesCash((Double) oc[4]);
				dayIncomeSummary.setRealIncomesPos((Double) oc[5]);
				dayIncomeSummary.setRealIncomesGroupBuying((Double) oc[6]);
				dayIncomeSummaryList.add(dayIncomeSummary);
			}
		}
		return dayIncomeSummaryList;
	}

	@Override
	public List<ConsumerPeopleReport> findConsumerPeopleReport(String hql) throws Exception {
		List<ConsumerPeopleReport> consumerPeopleReportList = new ArrayList<ConsumerPeopleReport>();
		Session session = getSession();
		Iterator it = session.createQuery(hql).iterate();
		while (it.hasNext()) {
			Object[] oc = (Object[]) it.next();
			if (oc != null) {
				ConsumerPeopleReport consumerPeopleReport = new ConsumerPeopleReport();
				String macName = (String) oc[0];
				Double amount = (Double) oc[1];
				Double perCapitaNumberCoins = (Double) oc[2];
				consumerPeopleReport.setMacName(macName);
				consumerPeopleReport.setTatal(amount);
				consumerPeopleReport.setPerCapitaNumberCoins(perCapitaNumberCoins);
				consumerPeopleReportList.add(consumerPeopleReport);
			}
		}
		return consumerPeopleReportList;
	}

	@Override
	public List<MonthAccountBalance> findMonthAccountBalance(String hql) throws Exception {
		List<MonthAccountBalance> monthAccountBalanceList = new ArrayList<MonthAccountBalance>();
		Session session = getSession();
		Iterator it = session.createQuery(hql).iterate();
		while (it.hasNext()) {
			Object[] oc = (Object[]) it.next();
			if (oc != null) {
				MonthAccountBalance consumerPeopleReport = new MonthAccountBalance();
				consumerPeopleReport.setSalesDate((Date) oc[0]);
				consumerPeopleReport.setSaleCoinAmount((Double) oc[1]);
				consumerPeopleReport.setCateringSalesAmount((Double) oc[2]);
				consumerPeopleReport.setGoodsSalesAmount((Double) oc[3]);
				consumerPeopleReport.setTotal((Double) oc[4]);
				consumerPeopleReport.setCash((Double) oc[5]);
				consumerPeopleReport.setPos((Double) oc[6]);
				consumerPeopleReport.setGroupBuying((Double) oc[7]);
				monthAccountBalanceList.add(consumerPeopleReport);
			}
		}
		return monthAccountBalanceList;
	}

	@Override
	public List<GroupIncomeStatement> findGroupIncomeStatement(String hql) throws Exception {
		List<GroupIncomeStatement> groupIncomeStatementList = new ArrayList<GroupIncomeStatement>();
		Session session = getSession();
		Iterator it = session.createQuery(hql).iterate();
		while (it.hasNext()) {
			Object[] oc = (Object[]) it.next();
			if (oc != null) {
				GroupIncomeStatement groupIncomeStatement = new GroupIncomeStatement();
				groupIncomeStatement.setStorename((String) oc[0]);
				groupIncomeStatement.setSalesDate((Date) oc[1]);
				groupIncomeStatement.setSaleCoinAmount((Double) oc[2]);
				groupIncomeStatement.setCateringSalesAmount((Double) oc[3]);
				groupIncomeStatement.setGoodsSalesAmount((Double) oc[4]);
				groupIncomeStatement.setTotal((Double) oc[5]);
				groupIncomeStatement.setCash((Double) oc[6]);
				groupIncomeStatement.setPos((Double) oc[7]);
				groupIncomeStatement.setGroupBuying((Double) oc[8]);
				groupIncomeStatementList.add(groupIncomeStatement);
			}
		}
		return groupIncomeStatementList;
	}

	@Override
	public List<GoodsStatistics> findGoodsStatistics(String hql) throws Exception {
		List<GoodsStatistics> goodsStatisticsList = new ArrayList<GoodsStatistics>();
		Session session = getSession();
		Iterator it = session.createQuery(hql).iterate();
		while (it.hasNext()) {
			Object[] oc = (Object[]) it.next();
			if (oc != null) {
				GoodsStatistics goodsStatistics = new GoodsStatistics();
				String goodsname = (String) oc[0];
				Double amount = (Double) oc[1];
				goodsStatistics.setName(goodsname);
				goodsStatistics.setSalesAmount(amount);
				goodsStatisticsList.add(goodsStatistics);
			}
		}
		return goodsStatisticsList;
	}

	@Override
	public List<CustomerCount> findCustomerCount(String hql) throws Exception {
		List<CustomerCount> customerCountList = new ArrayList<CustomerCount>();
		Session session = getSession();
		Iterator it = session.createQuery(hql).iterate();
		while (it.hasNext()) {
			Object[] oc = (Object[]) it.next();
			if (oc != null) {
				CustomerCount customerCount = new CustomerCount();
				if (oc[0] != null) {
					customerCount.setStoreName((String) oc[0]);
				}
				if (oc[1] != null) {
					customerCount.setOpencard1((Long) oc[1]);
				}
				if (oc[2] != null) {
					customerCount.setOpencard2((Long) oc[2]);
				}
				if (oc[3] != null) {
					customerCount.setOpencard3((Long) oc[3]);
				}
				if (oc[4] != null) {
					customerCount.setOpencard4((Long) oc[4]);
				}
				if (oc[5] != null) {
					customerCount.setOpencard5((Long) oc[5]);
				}
				customerCountList.add(customerCount);
			}
		}
		return customerCountList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findEquipmentCoinNumber(String hql) throws Exception {
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(1);
		query.setMaxResults(10);
		query.list();
		return query.list();
	}

	@Override
	public List<ClassifiedRevenue> findClassifiedRevenue(String hql) throws Exception {
		List<ClassifiedRevenue> classifiedRevenueList = new ArrayList<ClassifiedRevenue>();
		Session session = getSession();
		Iterator it = session.createQuery(hql).iterate();
		while (it.hasNext()) {
			Object[] oc = (Object[]) it.next();
			if (oc != null) {
				ClassifiedRevenue classifiedRevenue = new ClassifiedRevenue();
				classifiedRevenue.setMacName((String) oc[0]);
				classifiedRevenue.setCoinNumber((Double) oc[1]);
				classifiedRevenueList.add(classifiedRevenue);
			}
		}
		return classifiedRevenueList;
	}

	@Override
	public Double findSumAmount(String hql) throws Exception {
		Session session = getSession();
		Iterator it = session.createQuery(hql).iterate();
		Double sumAmount = 0D;
		while (it.hasNext()) {
			Object[] oc = (Object[]) it.next();
			if (oc != null) {
				if (oc[1] != null && !"null".equals(oc[1])) {
					sumAmount = (Double) oc[1];
				}
			}
		}
		return sumAmount;
	}

	@Override
	public List<GradeRatio> findGradeRatio(String hql) throws Exception {
		List<GradeRatio> gradeRatioList = new ArrayList<GradeRatio>();
		Session session = getSession();
		Iterator it = session.createQuery(hql).iterate();
		while (it.hasNext()) {
			Object[] oc = (Object[]) it.next();
			if (oc != null) {
				GradeRatio gradeRatio = new GradeRatio();
				gradeRatio.setGrade((String) oc[0]);
				gradeRatio.setProportion(Double.parseDouble(oc[1].toString()));
				gradeRatioList.add(gradeRatio);
			}
		}
		return gradeRatioList;
	}

	@Override
	public List<SexProportion> findSexProportionList(String hql) throws Exception {
		List<SexProportion> sexProportionList = new ArrayList<SexProportion>();
		Session session = getSession();
		Iterator it = session.createQuery(hql).iterate();
		while (it.hasNext()) {
			Object[] oc = (Object[]) it.next();
			if (oc != null) {
				SexProportion sexProportion = new SexProportion();
				sexProportion.setSex((String) oc[0]);
				sexProportion.setProportion(Long.parseLong(oc[1].toString()));
				sexProportionList.add(sexProportion);
			}
		}
		return sexProportionList;
	}

	@Override
	public List<AgeStatistic> findAgeStatisticList(String hql) throws Exception {
		List<AgeStatistic> ageStatisticList = new ArrayList<AgeStatistic>();
		Session session = getSession();
		Iterator it = session.createQuery(hql).iterate();
		while (it.hasNext()) {
			Object[] oc = (Object[]) it.next();
			if (oc != null) {
				AgeStatistic ageStatistic = new AgeStatistic();
				ageStatistic.setAge((Integer) oc[0]);
				ageStatistic.setAmount((Double) oc[1]);
				ageStatisticList.add(ageStatistic);
			}
		}
		return ageStatisticList;
	}
}
