/**
 * 
 */
package com.vix.WebService.dao;

import java.util.List;

import com.vix.WebService.vo.CustomerInformation;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

/**
 * com.vix.WebService.dao.WebHibernateDao
 * 
 * @author zhanghaibing
 * 
 * @date 2014-6-22
 */
public interface WebHibernateDao extends IBaseHibernateDao {

	public List<CustomerInformation> findCustomerInformation(String hql) throws Exception;
}
