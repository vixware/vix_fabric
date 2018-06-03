package com.vix.common.base;


/**
 * 
 * @author Jackie
 *
 */
public class GeneralIndustryProcessFactory extends BizObjectProcessFactory {

	@Override
	public IBizObjectProcess getPurchaseOderAction() {
		// TODO Auto-generated method stub
		return new GeneralPurchaseOrderProcess();
	}

	@Override
	public IBizObjectProcess getSaleOderAction() {
		// TODO Auto-generated method stub
		return null;
	}

}
