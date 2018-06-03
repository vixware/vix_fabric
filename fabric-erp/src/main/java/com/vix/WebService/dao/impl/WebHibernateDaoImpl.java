/**
 * 
 */
package com.vix.WebService.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.vix.WebService.dao.WebHibernateDao;
import com.vix.WebService.vo.CustomerInformation;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

/**
 * com.vix.WebService.dao.impl.WebHibernateDaoImpl
 * 
 * @author zhanghaibing
 * 
 * @date 2014-6-22
 */
@Repository("webHibernateDao")
public class WebHibernateDaoImpl extends BaseHibernateDaoImpl implements WebHibernateDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Override
	public List<CustomerInformation> findCustomerInformation(String hql) throws Exception {
		List<CustomerInformation> customerInformationList = new ArrayList<CustomerInformation>();
		Session session = getSession();
		Iterator it = session.createQuery(hql).iterate();
		while (it.hasNext()) {
			Object[] oc = (Object[]) it.next();
			if (oc != null) {
				CustomerInformation customerInformation = new CustomerInformation();
				customerInformation.setVipcardCode((String) oc[0]);
				customerInformation.setIntegral((Long) oc[1]);
				customerInformation.setResidual((Double) oc[2]);
				customerInformationList.add(customerInformation);
			}
		}
		return customerInformationList;
	}
}
