/**
 * 
 */
package com.vix.crm.business.dao;

import java.util.List;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.crm.business.entity.StoreSalesInformation;
import com.vix.crm.business.vo.CustomerAnalysis;
import com.vix.crm.business.vo.GoodsSaleInformation;
import com.vix.crm.business.vo.IdAndTitleVo;
import com.vix.crm.business.vo.SalesAnalysis;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
public interface IPurchasingBehaviorDao extends IBaseHibernateDao {

	/**
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public List<StoreSalesInformation> findStoreSalesInformationList(String hql) throws Exception;

	public List<GoodsSaleInformation> findGoodsSaleInformationList(String hql) throws Exception;

	public List<IdAndTitleVo> findIdAndTitleVoList(String hql) throws Exception;

	public CustomerAnalysis findCustomerAnalysis(String hql) throws Exception;

	public SalesAnalysis findSalesAnalysis(String hql) throws Exception;

}
