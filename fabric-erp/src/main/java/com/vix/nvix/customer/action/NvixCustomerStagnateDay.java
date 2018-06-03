package com.vix.nvix.customer.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.DateUtil;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.nvix.common.base.service.IVixntBaseService;

@Component
public class NvixCustomerStagnateDay{

	@Autowired
	IVixntBaseService vixntBaseService;
	
	/**
	 * 客户停滞天数
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	//@Scheduled(cron = "0/5 * * * * ?")
	public void setCustomerStagnateDay(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("isTemp," + SearchCondition.EQUAL, "0");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			params.put("type," + SearchCondition.EQUAL, "enterPrise");
			List<CustomerAccount> list = vixntBaseService.findAllDataByConditions(CustomerAccount.class, params);
			if(list != null && list.size() > 0){
				for (CustomerAccount customerAccount : list) {
					if(customerAccount.getUpdateTime() != null){
						long from = DateUtil.praseSqlDate(customerAccount.getUpdateTimeStr()).getTime();
						long to = DateUtil.praseSqlDate(DateUtil.format(new Date())).getTime();
						int day = (int)((to-from)/(24*60*60*1000));
						customerAccount.setStagnateDay(day);
						customerAccount = vixntBaseService.mergeOriginal(customerAccount);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
