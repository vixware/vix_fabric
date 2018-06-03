package com.vix.sales.order.controller;


import com.vix.common.base.BizObjectProcessFactory;
import com.vix.common.base.IBizObjectProcess;

/**
 * 
 * @author Jackie
 *
 */
public class GeneralIndustryProcessFactory extends BizObjectProcessFactory {

	@Override
	public IBizObjectProcess getPurchaseOderAction() {
		return new GeneralSalesOrderProcess();
	}

	@Override
	public IBizObjectProcess getSaleOderAction() {
		return new GeneralSalesOrderProcess();
	}
}
