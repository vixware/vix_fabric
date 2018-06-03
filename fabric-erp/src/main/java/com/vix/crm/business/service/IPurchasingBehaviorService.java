/**
 * 
 */
package com.vix.crm.business.service;

import java.util.List;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
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
public interface IPurchasingBehaviorService extends IBaseHibernateService {

	public List<StoreSalesInformation> findStoreSalesInformationList(String hql) throws Exception;

	public List<GoodsSaleInformation> findGoodsSaleInformationList(String hql) throws Exception;

	public List<IdAndTitleVo> findIdAndTitleVoList(String hql) throws Exception;

	public CustomerAnalysis findCustomerAnalysis(String hql) throws Exception;

	public SalesAnalysis findSalesAnalysis(String hql) throws Exception;

}
