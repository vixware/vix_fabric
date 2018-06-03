/**
 * 
 */
package com.vix.ebusiness.statisticalAnalysis.dao;

import java.util.List;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.drp.playgroundmanagementstatistics.vo.CustomerCount;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
public interface IStatisticalAnalysisDao extends IBaseHibernateDao {

	/**
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public Double findSumAmount(String hql) throws Exception;

	/**
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public List<CustomerCount> findCustomerCount(String hql) throws Exception;
}
