package com.process.vreport.processor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;

import com.e6soft.core.util.ApplicationContextUtil;
import com.process.vreport.dao.NodeMonitorDao;
import com.process.vreport.model.NodeProcessor;
import com.vix.crm.business.entity.CustomerAccountGroupListView;
import com.vix.crm.business.entity.MarketingActivities;
import com.vix.crm.business.entity.ProcessLog;

/**
 * 短信发送 com.process.vreport.processor.MessageProcessor
 *
 * @author bjitzhang
 *
 * @date 2015年5月27日
 */
public class MessageProcessor extends NodeProcessor {

	@Override
	public void process(Exchange exchange) throws Exception {
		NodeMonitorDao nodeMonitorDao = ApplicationContextUtil.getContext().getBean(NodeMonitorDao.class);

		MarketingActivities marketingActivities = null;
		if (this.getFromNode().getParam() != null) {
			System.out.println("-----------开始发送短信----------");
			marketingActivities = nodeMonitorDao.findMarketingActivitiesById(this.getFromNode().getParam());
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("membershipSubdivision_id", Long.parseLong(this.getFromNode().getParam()));
			List<CustomerAccountGroupListView> customerAccountGroupListViewList = nodeMonitorDao.findCustomerAccountGroupListViewList(parameter);
			if (customerAccountGroupListViewList != null && customerAccountGroupListViewList.size() > 0) {
				for (CustomerAccountGroupListView customerAccount : customerAccountGroupListViewList) {
					System.out.println("给客户--------------------" + customerAccount.getCustomerName() + "--------------------发送短信成功!");
					ProcessLog processLog = new ProcessLog();
					processLog.setName("发送短信");
					processLog.setCreateTime(new Date());
					processLog.setTenantId(customerAccount.getTenantId());
					processLog.setCompanyCode(customerAccount.getCompanyCode());
					processLog.setCompanyInnerCode(customerAccount.getCompanyInnerCode());
					processLog.setLogContent("给客户--------------------" + customerAccount.getCustomerName() + "--------------------发送短信成功!");
					nodeMonitorDao.saveProcessLog(processLog);
				}
			}
			System.out.println("-----------结束发送短信----------");
		} else {
			marketingActivities = nodeMonitorDao.findMarketingActivitiesById(exchange.getFromRouteId());
		}
		if (marketingActivities != null && marketingActivities.getSubMembershipSubdivisions() != null && marketingActivities.getSubMembershipSubdivisions().size() > 0) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("membershipSubdivision_id", Long.parseLong(this.getFromNode().getParam()));
			List<CustomerAccountGroupListView> customerAccountGroupListViewList = nodeMonitorDao.findCustomerAccountGroupListViewList(parameter);
			if (customerAccountGroupListViewList != null && customerAccountGroupListViewList.size() > 0) {
				for (CustomerAccountGroupListView customerAccount : customerAccountGroupListViewList) {
					System.out.println("给客户--------------------" + customerAccount.getCustomerName() + "--------------------发送短信成功!");
					ProcessLog processLog = new ProcessLog();
					processLog.setTenantId(customerAccount.getTenantId());
					processLog.setName("发送短信");
					processLog.setCreateTime(new Date());
					processLog.setCompanyCode(customerAccount.getCompanyCode());
					processLog.setCompanyInnerCode(customerAccount.getCompanyInnerCode());
					processLog.setLogContent("给客户--------------------" + customerAccount.getCustomerName() + "--------------------发送短信成功!");
					nodeMonitorDao.saveProcessLog(processLog);
				}
			}
		}
	}
}
