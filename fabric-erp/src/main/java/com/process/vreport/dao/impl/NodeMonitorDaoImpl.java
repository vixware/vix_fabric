package com.process.vreport.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.e6soft.core.mybatis.BaseEntityDao;
import com.process.vreport.dao.NodeMonitorDao;
import com.process.vreport.model.NodeMonitor;
import com.vix.crm.business.entity.CouponDetail;
import com.vix.crm.business.entity.CouponSendLog;
import com.vix.crm.business.entity.CustomerAccountGroupListView;
import com.vix.crm.business.entity.MarketingActivities;
import com.vix.crm.business.entity.MembershipSubdivision;
import com.vix.crm.business.entity.NodesLog;
import com.vix.crm.business.entity.ProcessLog;
import com.vix.mdm.crm.entity.CustomerAccount;

@Transactional
@Repository("nodeMonitorDao")
public class NodeMonitorDaoImpl extends BaseEntityDao<NodeMonitor, String> implements NodeMonitorDao {

	public NodeMonitorDaoImpl() {
		super(NodeMonitor.class);
	}

	@Override
	public void updateByExchangeId(String exchangeId) {
		String statement = NodeMonitorDao.class.getName() + ".updateByExchangeId";
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("exchangeId", exchangeId);
		this.getSqlSession().update(statement, parameter);
	}

	@Override
	public void save(NodesLog nodesLog) {
		String statement = NodeMonitorDao.class.getName() + ".saveDataLog";
		this.getSqlSession().insert(statement, nodesLog);
	}

	@Override
	public List<CouponDetail> findCouponDetailList(Map<String, Object> params) {
		String statement = NodeMonitorDao.class.getName() + ".findCouponDetailList";
		return this.getSqlSession().selectList(statement, params);
	}

	@Override
	public MarketingActivities findMarketingActivitiesById(String id) {
		String statement = NodeMonitorDao.class.getName() + ".getMarketingActivitiesId";
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("id", id);
		return this.getSqlSession().selectOne(statement, parameter);
	}

	@Override
	public CustomerAccount findCustomerAccountById(String id) {
		String statement = NodeMonitorDao.class.getName() + ".getCustomerAccountId";
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("id", id);
		return this.getSqlSession().selectOne(statement, parameter);
	}

	@Override
	public List<CustomerAccountGroupListView> findCustomerAccountGroupListViewList(Map<String, Object> params) {
		String statement = NodeMonitorDao.class.getName() + ".findCustomerAccountGroupListViewList";
		return this.getSqlSession().selectList(statement, params);
	}

	@Override
	public void updateCouponDetail(Map<String, Object> params) {
		String statement = NodeMonitorDao.class.getName() + ".updateCouponDetailById";
		this.getSqlSession().update(statement, params);
	}

	@Override
	public void updateCustomerAccount(Map<String, Object> params) {

		String statement = NodeMonitorDao.class.getName() + ".updateCustomerAccountById";
		this.getSqlSession().update(statement, params);

	}

	@Override
	public void saveProcessLog(ProcessLog processLog) {
		String statement = NodeMonitorDao.class.getName() + ".saveProcessLog";
		this.getSqlSession().insert(statement, processLog);
	}

	@Override
	public void saveCouponSendLog(CouponSendLog couponSendLog) {
		String statement = NodeMonitorDao.class.getName() + ".saveCouponSendLog";
		this.getSqlSession().insert(statement, couponSendLog);
	}

	@Override
	public MembershipSubdivision findMembershipSubdivisionById(String id) {
		String statement = NodeMonitorDao.class.getName() + ".getMembershipSubdivisionById";
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("id", id);
		return this.getSqlSession().selectOne(statement, parameter);
	}
}
