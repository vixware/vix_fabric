/**
 * 
 */
package com.vix.ebusiness.order.orderProcess.processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vix.ebusiness.entity.OrderAndGoods;
import com.vix.system.remindsCenter.base.service.IRemindsCenterService;

/**
 * 库存解除锁定
 * 
 * @author zhanghaibing
 * 
 * @date 2014-5-22
 */

@Component
public class OrderUnLockTask {

	@Autowired
	private IRemindsCenterService remindsCenterService;
	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 库存解锁
	 * 
	 * @throws Exception
	 */
	@Scheduled(cron = "0 50/5 14 * * ?*")
	public void orderUnLock() throws Exception {
		List<OrderAndGoods> orderAndGoodsList = remindsCenterService.findRemindsList(OrderAndGoods.class);
		if (orderAndGoodsList != null && orderAndGoodsList.size() > 0) {
			for (OrderAndGoods orderAndGoods : orderAndGoodsList) {
				if (orderAndGoods != null) {
					long hour = getQuot(ft.format(new Date()), ft.format(orderAndGoods.getCreateTime()));
					if (hour > 24) {
						orderAndGoods.setStatus("2");
						remindsCenterService.mergeOriginal(orderAndGoods);
						System.out.println("测试库存解除锁定!");
					}
				}
			}
		}
	}

	public long getQuot(String time1, String time2) {
		long quot = 0;
		try {
			Date date1 = ft.parse(time1);
			Date date2 = ft.parse(time2);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}

}
