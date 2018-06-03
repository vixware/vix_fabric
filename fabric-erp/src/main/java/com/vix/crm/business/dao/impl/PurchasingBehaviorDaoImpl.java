/**
 * 
 */
package com.vix.crm.business.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.common.vixdata.dataappinterface.IDataAccuracy;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.business.dao.IPurchasingBehaviorDao;
import com.vix.crm.business.entity.StoreSalesInformation;
import com.vix.crm.business.vo.CustomerAnalysis;
import com.vix.crm.business.vo.GoodsSaleInformation;
import com.vix.crm.business.vo.IdAndTitleVo;
import com.vix.crm.business.vo.SalesAnalysis;

/**
 * 
 * com.vix.crm.business.dao.impl.PurchasingBehaviorDaoImpl
 *
 * @author bjitzhang
 *
 * @date 2014年8月6日
 */
@Service("purchasingBehaviorDao")
public class PurchasingBehaviorDaoImpl extends BaseHibernateDaoImpl implements IPurchasingBehaviorDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private IDataAccuracy dataAccuracy;

	@Override
	public List<StoreSalesInformation> findStoreSalesInformationList(String hql) throws Exception {
		List<StoreSalesInformation> storeSalesInformationList = new ArrayList<StoreSalesInformation>();
		Session session = getSession();
		List<?> it = session.createSQLQuery(hql).list();
		for (int i = 0; i < it.size(); i++) {
			Object[] oc = (Object[]) it.get(i);
			if (oc != null) {
				StoreSalesInformation storeSalesInformation = new StoreSalesInformation();
				Date buyDate = (Date) oc[0];
				Double totalAmount = (Double) oc[1];
				Long firstOrderNum = (Long) oc[2];
				Double firstOrderPrice = (Double) oc[3];
				storeSalesInformation.setBuyDate(buyDate);
				storeSalesInformation.setTotalAmount(dataAccuracy.getAmountDecimal(totalAmount) + dataAccuracy.getAmountDecimal((Double) oc[4]));
				storeSalesInformation.setFirstOrderAmount(dataAccuracy.getAmountDecimal(totalAmount));
				storeSalesInformation.setFirstOrderNum(firstOrderNum);
				storeSalesInformation.setFirstOrderPrice(dataAccuracy.getAmountDecimal(firstOrderPrice));
				storeSalesInformation.setBuyBackOrderAmount(dataAccuracy.getAmountDecimal((Double) oc[4]));
				storeSalesInformation.setBuyBackOrderNum((Long) oc[5]);
				storeSalesInformation.setBuyBackOrderPrice(dataAccuracy.getAmountDecimal((Double) oc[6]));
				storeSalesInformationList.add(storeSalesInformation);
			}

		}
		return storeSalesInformationList;
	}

	@Override
	public List<GoodsSaleInformation> findGoodsSaleInformationList(String hql) throws Exception {
		List<GoodsSaleInformation> goodsSaleInformationList = new ArrayList<GoodsSaleInformation>();
		Session session = getSession();
		Iterator<?> it = session.createQuery(hql).iterate();
		while (it.hasNext()) {
			Object[] oc = (Object[]) it.next();
			if (oc != null) {
				GoodsSaleInformation goodsSaleInformation = new GoodsSaleInformation();
				String goodsName = (String) oc[0];
				Double amount = (Double) oc[1];
				Long quantity = (Long) oc[2];
				Double unitPrice = (Double) oc[3];
				goodsSaleInformation.setGoodsName(goodsName);
				goodsSaleInformation.setAmount(dataAccuracy.getAmountDecimal(amount));
				goodsSaleInformation.setQuantity(quantity);
				goodsSaleInformation.setUnitPrice(dataAccuracy.getAmountDecimal(unitPrice));
				goodsSaleInformationList.add(goodsSaleInformation);
			}
		}
		return goodsSaleInformationList;
	}

	@Override
	public List<IdAndTitleVo> findIdAndTitleVoList(String hql) throws Exception {
		List<IdAndTitleVo> idAndTitleVoList = new ArrayList<IdAndTitleVo>();
		Session session = getSession();
		List<?> it = session.createSQLQuery(hql).list();
		for (int i = 0; i < it.size(); i++) {
			Object[] oc = (Object[]) it.get(i);
			if (oc != null) {
				IdAndTitleVo idAndTitleVo = new IdAndTitleVo();
				String titles = (String) oc[0];
				String ids = (String) oc[1];
				idAndTitleVo.setTitles(titles);
				idAndTitleVo.setIds(ids);
				idAndTitleVoList.add(idAndTitleVo);
			}

		}
		return idAndTitleVoList;
	}

	@Override
	public CustomerAnalysis findCustomerAnalysis(String hql) throws Exception {
		Session session = getSession();
		List<?> it = session.createSQLQuery(hql).list();
		CustomerAnalysis customerAnalysis = new CustomerAnalysis();
		for (int i = 0; i < it.size(); i++) {
			Object[] oc = (Object[]) it.get(i);
			if (oc != null) {
				Long customerNumber = (Long) oc[0];
				Double amount = (Double) oc[1];
				Double averageAmount = (Double) oc[2];
				if (customerNumber != null) {
					customerAnalysis.setCustomerNumber(dataAccuracy.getAmountDecimal(customerNumber.doubleValue()));
				}
				if (amount != null) {
					customerAnalysis.setAmount(dataAccuracy.getAmountDecimal(amount));
				}
				if (averageAmount != null) {
					customerAnalysis.setAverageAmount(dataAccuracy.getAmountDecimal(averageAmount));
				}
			}
		}
		return customerAnalysis;
	}

	@Override
	public SalesAnalysis findSalesAnalysis(String hql) throws Exception {
		Session session = getSession();
		List<?> it = session.createSQLQuery(hql).list();
		SalesAnalysis salesAnalysis = new SalesAnalysis();
		for (int i = 0; i < it.size(); i++) {
			Object[] oc = (Object[]) it.get(i);
			if (oc != null) {
				Double amount = (Double) oc[0];
				BigDecimal orderNum = (BigDecimal) oc[1];
				Double orderPrice = (Double) oc[2];
				BigDecimal orderAvgNum = (BigDecimal) oc[3];
				salesAnalysis.setAmount(amount);
				if (orderNum != null && !"".equals(orderNum)) {
					salesAnalysis.setOrderNum(orderNum.doubleValue());
				}
				salesAnalysis.setOrderPrice(orderPrice);
				if (orderAvgNum != null && !"".equals(orderAvgNum)) {
					salesAnalysis.setOrderAvgNum(orderAvgNum.doubleValue());
				}
			}
		}
		return salesAnalysis;
	}

}
