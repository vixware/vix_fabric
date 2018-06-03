package com.vix.nvix.customer.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vix.core.utils.DateUtil;
import com.vix.crm.customer.entity.MemorialDay;
import com.vix.nvix.common.base.service.IVixntBaseService;

@Component
public class NvixMemorialDay{

	@Autowired
	IVixntBaseService vixntBaseService;
	
	/**
	 * 纪念日即时更新
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	//@Scheduled(cron = "0/5 * * * * ?")
	public void setMemorialDay(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			List<MemorialDay> list = vixntBaseService.findAllDataByConditions(MemorialDay.class, params);
			if(list != null && list.size() > 0){
				for (MemorialDay memorialDay : list) {
					if(memorialDay.getDate() != null){
						SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
						int year = DateUtil.getYear();
						String time = year + "-" + sdf.format(memorialDay.getDate());
						long start = DateUtil.praseSqlDate(time).getTime();
						long end = DateUtil.praseSqlDate(DateUtil.format(new Date())).getTime();
						if(start < end){
							memorialDay.setNextDateStr((year + 1) + "-" + sdf.format(memorialDay.getDate()));
							memorialDay = vixntBaseService.mergeOriginal(memorialDay);
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
