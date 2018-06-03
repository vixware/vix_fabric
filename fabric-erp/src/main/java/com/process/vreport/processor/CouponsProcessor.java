package com.process.vreport.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;

import com.e6soft.core.util.ApplicationContextUtil;
import com.process.vreport.dao.NodeMonitorDao;
import com.process.vreport.model.NodeProcessor;
import com.vix.common.id.VixUUID;
import com.vix.crm.business.entity.CouponDetail;
import com.vix.crm.business.entity.CouponSendLog;
import com.vix.crm.business.entity.CustomerAccountGroupListView;
import com.vix.crm.business.entity.MembershipSubdivision;
import com.vix.crm.business.entity.ProcessLog;
import com.vix.mdm.crm.entity.CustomerAccount;

/**
 * 优惠券发送
 * 
 * com.process.vreport.processor.CouponsProcessor
 *
 * @author bjitzhang
 *
 * @date 2015年5月27日
 */
public class CouponsProcessor extends NodeProcessor {

	NodeMonitorDao nodeMonitorDao = ApplicationContextUtil.getContext().getBean(NodeMonitorDao.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("-----------开始优惠券发送 -----------");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("couponId", this.getNode().getParam());
		params.put("status", "2");
		List<CouponDetail> couponDetailList = nodeMonitorDao.findCouponDetailList(params);
		MembershipSubdivision membershipSubdivision = null;
		if (this.getFromNode() != null) {
			membershipSubdivision = nodeMonitorDao.findMembershipSubdivisionById(this.getFromNode().getParam());
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("membershipSubdivision_id", this.getFromNode().getParam());
			List<CustomerAccountGroupListView> customerAccountGroupListViewList = nodeMonitorDao.findCustomerAccountGroupListViewList(parameter);
			if (customerAccountGroupListViewList != null && customerAccountGroupListViewList.size() > 0) {
				List<CustomerAccount> customerAccountList = new ArrayList<CustomerAccount>();
				for (CustomerAccountGroupListView customerAccountGroupListView : customerAccountGroupListViewList) {
					if (customerAccountGroupListView.getCustomerAccountId() != null) {
						CustomerAccount customerAccount = nodeMonitorDao.findCustomerAccountById(customerAccountGroupListView.getCustomerAccountId());
						if (customerAccount != null) {
							customerAccountList.add(customerAccount);
						}
					}
				}
				couponing(couponDetailList, customerAccountList, membershipSubdivision);
			}
		}
		System.out.println("-----------结束优惠券发送 -----------");
	}

	public void couponing(List<CouponDetail> couponDetailList, List<CustomerAccount> customerAccountList, MembershipSubdivision membershipSubdivision) throws Exception {

		for (int i = 0; i < customerAccountList.size(); i++) {
			for (int j = i; j <= i; j++) {
				if (j < couponDetailList.size()) {
					CustomerAccount customerAccount = customerAccountList.get(j);
					CouponDetail couponDetail = couponDetailList.get(j);
					if (couponDetail != null) {
						Map<String, Object> parameter = new HashMap<String, Object>();
						parameter.put("id", couponDetail.getId());
						parameter.put("status", "1");
						parameter.put("customerAccount_id", customerAccount.getId());
						nodeMonitorDao.updateCouponDetail(parameter);

						Map<String, Object> params = new HashMap<String, Object>();
						params.put("id", couponDetail.getId());
						params.put("isCouponing", "1");
						nodeMonitorDao.updateCustomerAccount(params);

						System.out.println("给客户--------------------" + customerAccount.getName() + "--------------------发送优惠券成功!");
						ProcessLog processLog = new ProcessLog();
						processLog.setId(VixUUID.getUUID());
						processLog.setName("发送优惠券");
						processLog.setLogContent("给客户:" + customerAccount.getName() + "发送优惠券成功!");
						processLog.setTenantId(customerAccount.getTenantId());
						processLog.setCompanyCode(customerAccount.getCompanyCode());
						processLog.setCreateTime(new Date());
						processLog.setCompanyInnerCode(customerAccount.getCompanyInnerCode());
						nodeMonitorDao.saveProcessLog(processLog);

						// 保存发送优惠券明细
						CouponSendLog couponSendLog = new CouponSendLog();
						couponSendLog.setName("发送优惠券");
						couponSendLog.setId(VixUUID.getUUID());
						couponSendLog.setTenantId(customerAccount.getTenantId());
						couponSendLog.setCompanyCode(customerAccount.getCompanyCode());
						couponSendLog.setCreateTime(new Date());
						couponSendLog.setCompanyInnerCode(customerAccount.getCompanyInnerCode());
						couponSendLog.setCouponDetail(couponDetail);
						couponSendLog.setCustomerAccount(customerAccount);
						couponSendLog.setMembershipSubdivision(membershipSubdivision);
						nodeMonitorDao.saveCouponSendLog(couponSendLog);
					}
				}
			}
		}
	}
}