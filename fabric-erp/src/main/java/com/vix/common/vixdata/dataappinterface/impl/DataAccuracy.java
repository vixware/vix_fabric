/**
 * 
 */
package com.vix.common.vixdata.dataappinterface.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.common.security.util.SecurityUtil;
import com.vix.common.vixdata.dataappinterface.IDataAccuracy;
import com.vix.common.vixdata.util.VixDouble;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.system.precisionControl.entity.PrecisionControl;

/**
 * 返回精度控制后的数据
 * 
 * @author zhanghaibing
 * 
 * @date 2013-8-21
 */
@Service("dataAccuracy")
public class DataAccuracy implements IDataAccuracy {

	@Autowired
	private IBaseHibernateService baseHibernateService;

	@Override
	public Double getStocksAmount(Double data) throws Exception {
		VixDouble vixDouble = new VixDouble();
		PrecisionControl precisionControl;
		precisionControl = baseHibernateService.findEntityByAttribute(PrecisionControl.class, "tenantId", SecurityUtil.getCurrentUserTenantId());
		if (precisionControl == null) {
			return data;
		} else {
			return vixDouble.toDouble(data, precisionControl.getStockSizes());
		}
	}

	@Override
	public Double getStockPrice(Double data) throws Exception {
		VixDouble vixDouble = new VixDouble();
		PrecisionControl precisionControl;
		precisionControl = baseHibernateService.findEntityByAttribute(PrecisionControl.class, "tenantId", SecurityUtil.getCurrentUserTenantId());
		if (precisionControl == null) {
			return data;
		} else {
			// 小数位数要从配置数据库获取
			return vixDouble.toDouble(data, precisionControl.getStockPrice());
		}
	}

	@Override
	public Double getBillsPrice(Double data) throws Exception {
		VixDouble vixDouble = new VixDouble();
		PrecisionControl precisionControl;
		precisionControl = baseHibernateService.findEntityByAttribute(PrecisionControl.class, "tenantId", SecurityUtil.getCurrentUserTenantId());
		if (precisionControl == null) {
			return data;
		} else {
			// 小数位数要从配置数据库获取
			return vixDouble.toDouble(data, precisionControl.getNoteTheUnitPrice());
		}
	}

	@Override
	public Double getNumberOfUnits(Double data) throws Exception {
		VixDouble vixDouble = new VixDouble();
		PrecisionControl precisionControl;
		precisionControl = baseHibernateService.findEntityByAttribute(PrecisionControl.class, "tenantId", SecurityUtil.getCurrentUserTenantId());
		if (precisionControl == null) {
			return data;
		} else {
			return vixDouble.toDouble(data, precisionControl.getAmount());

		}
	}

	@Override
	public Double getConversionRate(Double data) throws Exception {
		VixDouble vixDouble = new VixDouble();
		PrecisionControl precisionControl;
		precisionControl = baseHibernateService.findEntityByAttribute(PrecisionControl.class, "tenantId", SecurityUtil.getCurrentUserTenantId());
		if (precisionControl == null) {
			return data;
		} else {
			return vixDouble.toDouble(data, precisionControl.getConversiorate());
		}
	}

	@Override
	public Double getRateOfTax(Double data) throws Exception {
		VixDouble vixDouble = new VixDouble();
		PrecisionControl precisionControl;
		precisionControl = baseHibernateService.findEntityByAttribute(PrecisionControl.class, "tenantId", SecurityUtil.getCurrentUserTenantId());
		if (precisionControl == null) {
			return data;
		} else {
			return vixDouble.toDouble(data, precisionControl.getTaxRate());
		}
	}

	@Override
	public Double getAmountDecimal(Double data) throws Exception {
		VixDouble vixDouble = new VixDouble();
		PrecisionControl precisionControl;
		precisionControl = baseHibernateService.findEntityByAttribute(PrecisionControl.class, "tenantId", SecurityUtil.getCurrentUserTenantId());
		if (precisionControl == null) {
			return data;
		} else {
			return vixDouble.toDouble(data, precisionControl.getNoteTheUnitPrice());
		}
	}

	@Override
	public Double getStockDimensionDecimal(Double data) throws Exception {
		VixDouble vixDouble = new VixDouble();
		PrecisionControl precisionControl;
		precisionControl = baseHibernateService.findEntityByAttribute(PrecisionControl.class, "tenantId", SecurityUtil.getCurrentUserTenantId());
		if (precisionControl == null) {
			return data;
		} else {
			return vixDouble.toDouble(data, precisionControl.getStockDimensionDecimal());
		}
	}

	@Override
	public Double getStockWeightDecimal(Double data) throws Exception {
		VixDouble vixDouble = new VixDouble();
		PrecisionControl precisionControl;
		precisionControl = baseHibernateService.findEntityByAttribute(PrecisionControl.class, "tenantId", SecurityUtil.getCurrentUserTenantId());
		if (precisionControl == null) {
			return data;
		} else {
			return vixDouble.toDouble(data, precisionControl.getStockWeightDecimal());
		}
	}

}
