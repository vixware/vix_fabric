package com.process.vreport.dao;

import java.util.List;
import java.util.Map;

import com.e6soft.core.mybatis.EntityDao;
import com.process.vreport.model.NodeMonitor;
import com.vix.crm.business.entity.CouponDetail;
import com.vix.crm.business.entity.CouponSendLog;
import com.vix.crm.business.entity.CustomerAccountGroupListView;
import com.vix.crm.business.entity.MarketingActivities;
import com.vix.crm.business.entity.MembershipSubdivision;
import com.vix.crm.business.entity.NodesLog;
import com.vix.crm.business.entity.ProcessLog;
import com.vix.mdm.crm.entity.CustomerAccount;

public interface NodeMonitorDao extends EntityDao<NodeMonitor, String> {

	public void updateByExchangeId(String exchangeId);

	public void save(NodesLog nodesLog);

	public void saveProcessLog(ProcessLog processLog);

	public void saveCouponSendLog(CouponSendLog couponSendLog);

	public void updateCouponDetail(Map<String, Object> params);

	public void updateCustomerAccount(Map<String, Object> params);

	public List<CouponDetail> findCouponDetailList(Map<String, Object> params);

	public List<CustomerAccountGroupListView> findCustomerAccountGroupListViewList(Map<String, Object> params);

	public MarketingActivities findMarketingActivitiesById(String id);

	public MembershipSubdivision findMembershipSubdivisionById(String id);

	public CustomerAccount findCustomerAccountById(String id);
}
