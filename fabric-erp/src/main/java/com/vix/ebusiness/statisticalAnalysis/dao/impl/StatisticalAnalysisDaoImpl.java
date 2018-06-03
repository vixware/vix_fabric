/**
 * 
 */
package com.vix.ebusiness.statisticalAnalysis.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.drp.playgroundmanagementstatistics.vo.CustomerCount;
import com.vix.ebusiness.statisticalAnalysis.dao.IStatisticalAnalysisDao;

/**
 * 
 * com.vix.ebusiness.statisticalAnalysis.dao.impl.StatisticalAnalysisDaoImpl
 *
 * @author bjitzhang
 *
 * @date 2015年3月6日
 */
@Service("statisticalAnalysisDao")
public class StatisticalAnalysisDaoImpl extends BaseHibernateDaoImpl implements IStatisticalAnalysisDao {

	private static final long serialVersionUID = 1L;



	@Override
	public List<CustomerCount> findCustomerCount(String hql) throws Exception {
		List<CustomerCount> customerCountList = new ArrayList<CustomerCount>();
		Session session = getSession();
		Iterator<?> it = session.createQuery(hql).iterate();
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



	@Override
	public Double findSumAmount(String hql) throws Exception {
		Session session = getSession();
		Iterator<?> it = session.createQuery(hql).iterate();
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

}
