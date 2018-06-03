package com.vix.drp.playgroundmanagementstatistics.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.drp.playgroundmanagementstatistics.dao.IPlaygroundManagementStatisticsDao;
import com.vix.drp.playgroundmanagementstatistics.service.IPlaygroundManagementStatisticsService;
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

@Service("playgroundManagementStatisticsService")
public class PlaygroundManagementStatisticsServiceImpl extends BaseHibernateServiceImpl implements IPlaygroundManagementStatisticsService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IPlaygroundManagementStatisticsDao playgroundManagementStatisticsDao;

	@Override
	public List<TranLog> findTranLogByHql(String hql) throws Exception {
		return playgroundManagementStatisticsDao.findAllTranLog(hql);
	}

	@Override
	public List<DayIncomeSummary> findDayIncomeSummaryByHql(String hql) throws Exception {
		return playgroundManagementStatisticsDao.findDayIncomeSummary(hql);
	}

	@Override
	public List<ConsumerPeopleReport> findConsumerPeopleReport(String hql) throws Exception {
		return playgroundManagementStatisticsDao.findConsumerPeopleReport(hql);
	}

	@Override
	public List<MonthAccountBalance> findMonthAccountBalance(String hql) throws Exception {
		return playgroundManagementStatisticsDao.findMonthAccountBalance(hql);
	}

	@Override
	public List<GroupIncomeStatement> findGroupIncomeStatement(String hql) throws Exception {
		return playgroundManagementStatisticsDao.findGroupIncomeStatement(hql);
	}

	@Override
	public List<GoodsStatistics> findGoodsStatistics(String hql) throws Exception {
		return playgroundManagementStatisticsDao.findGoodsStatistics(hql);
	}

	@Override
	public List<CustomerCount> findCustomerCount(String hql) throws Exception {
		return playgroundManagementStatisticsDao.findCustomerCount(hql);
	}

	@Override
	public List<EquipmentCoinNumber> findEquipmentCoinNumber(String hql) throws Exception {
		List<EquipmentCoinNumber> equipmentCoinNumberList = new ArrayList<EquipmentCoinNumber>();
		List<Object[]> it = playgroundManagementStatisticsDao.findEquipmentCoinNumber(hql);
		for (Object[] object : it) {
			if (object != null) {
				EquipmentCoinNumber equipmentCoinNumber = new EquipmentCoinNumber();
				equipmentCoinNumber.setName((String) object[0]);
				equipmentCoinNumber.setSalesAmount((Double) object[1]);
				equipmentCoinNumberList.add(equipmentCoinNumber);
			}
		}
		return equipmentCoinNumberList;
	}

	@Override
	public List<ClassifiedRevenue> findClassifiedRevenue(String hql) throws Exception {
		return playgroundManagementStatisticsDao.findClassifiedRevenue(hql);
	}

	@Override
	public Double findSumAmountByHql(String hql) throws Exception {
		return playgroundManagementStatisticsDao.findSumAmount(hql);
	}

	@Override
	public List<GradeRatio> findGradeRatio(String hql) throws Exception {
		return playgroundManagementStatisticsDao.findGradeRatio(hql);
	}

	@Override
	public List<SexProportion> findSexProportionList(String hql) throws Exception {
		return playgroundManagementStatisticsDao.findSexProportionList(hql);
	}

	@Override
	public List<AgeStatistic> findAgeStatistic(String hql) throws Exception {
		return playgroundManagementStatisticsDao.findAgeStatisticList(hql);
	}
}
