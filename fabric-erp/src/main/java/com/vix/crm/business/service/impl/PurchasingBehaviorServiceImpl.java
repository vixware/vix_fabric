/**
 * 
 */
package com.vix.crm.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.crm.business.dao.IPurchasingBehaviorDao;
import com.vix.crm.business.entity.StoreSalesInformation;
import com.vix.crm.business.service.IPurchasingBehaviorService;
import com.vix.crm.business.vo.CustomerAnalysis;
import com.vix.crm.business.vo.GoodsSaleInformation;
import com.vix.crm.business.vo.IdAndTitleVo;
import com.vix.crm.business.vo.SalesAnalysis;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Service("purchasingBehaviorService")
public class PurchasingBehaviorServiceImpl extends BaseHibernateServiceImpl implements IPurchasingBehaviorService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IPurchasingBehaviorDao purchasingBehaviorDao;

	@Override
	public List<StoreSalesInformation> findStoreSalesInformationList(String hql) throws Exception {
		return purchasingBehaviorDao.findStoreSalesInformationList(hql);
	}

	@Override
	public List<GoodsSaleInformation> findGoodsSaleInformationList(String hql) throws Exception {
		return purchasingBehaviorDao.findGoodsSaleInformationList(hql);
	}

	@Override
	public List<IdAndTitleVo> findIdAndTitleVoList(String hql) throws Exception {
		return purchasingBehaviorDao.findIdAndTitleVoList(hql);
	}

	@Override
	public CustomerAnalysis findCustomerAnalysis(String hql) throws Exception {
		return purchasingBehaviorDao.findCustomerAnalysis(hql);
	}

	@Override
	public SalesAnalysis findSalesAnalysis(String hql) throws Exception {
		return purchasingBehaviorDao.findSalesAnalysis(hql);
	}

}
