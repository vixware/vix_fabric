/**
 * 
 */
package com.vix.system.remindsCenter.base.task.inventory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vix.common.share.entity.AlertNotice;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.system.remindsCenter.base.basetask.VixAlertTask;
import com.vix.system.remindsCenter.base.service.IRemindsCenterService;

/**
 * 保质期提醒
 * 
 * @author zhanghaibing
 * 
 * @date 2014-5-22
 */
@Controller("qualityGuaranteePeriodTask")
public class QualityGuaranteePeriodTask extends VixAlertTask {

	public static final String TASK_METHOD_NAME = "vix.qualityGuaranteePeriodTask";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private IRemindsCenterService remindsCenterService;

	@Override
	public String getTaskMethodName() {
		return TASK_METHOD_NAME;
	}

	public int daysBetween(Date smdate, Date bdate) throws ParseException {
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 保质期提醒
	 */
	@Override
	public void doTask() throws Exception {
		List<InventoryCurrentStock> inventoryCurrentStockList = remindsCenterService.findRemindsList(InventoryCurrentStock.class);
		if (inventoryCurrentStockList != null && inventoryCurrentStockList.size() > 0) {
			for (InventoryCurrentStock inventoryCurrentStock : inventoryCurrentStockList) {

				if (inventoryCurrentStock != null) {
					int day = 0;
					if (inventoryCurrentStock.getMassunitEndTime() != null) {
						Date d1 = inventoryCurrentStock.getMassunitEndTime();
						Date d2 = new Date();
						day = daysBetween(d2, d1);
						if (0 < day && day < 10) {
							AlertNotice alertNotice = null;
							alertNotice = remindsCenterService.findEntityByAttributeNoTenantId(AlertNotice.class, "boCode", inventoryCurrentStock.getSkuCode() + inventoryCurrentStock.getMassunitEndTime());
							if (alertNotice != null) {
								alertNotice.setContent(inventoryCurrentStock.getItemname() + inventoryCurrentStock.getSkuCode() + simpleDateFormat.format(inventoryCurrentStock.getMassunitEndTime()) + "还剩" + day + "天就到期了.");
							} else {
								alertNotice = new AlertNotice();
								alertNotice.setSubject("保质期提醒");
								alertNotice.setBoCode(inventoryCurrentStock.getSkuCode() + inventoryCurrentStock.getMassunitEndTime());
								alertNotice.setContent(inventoryCurrentStock.getItemname() + inventoryCurrentStock.getSkuCode() + simpleDateFormat.format(inventoryCurrentStock.getMassunitEndTime()) + "还剩" + day + "天就到期了.");
								alertNotice.setRemindTime(new Date());
								alertNotice.setTime(new Date());
								alertNotice.setTenantId(inventoryCurrentStock.getTenantId());
								alertNotice.setCompanyCode(inventoryCurrentStock.getCompanyCode());
								alertNotice.setCompanyInnerCode(inventoryCurrentStock.getCompanyInnerCode());
							}
							remindsCenterService.mergeOriginal(alertNotice);
						} else if (0 > day) {
							AlertNotice alertNotice = null;
							alertNotice = remindsCenterService.findEntityByAttributeNoTenantId(AlertNotice.class, "boCode", inventoryCurrentStock.getSkuCode() + inventoryCurrentStock.getMassunitEndTime());
							if (alertNotice != null) {
							} else {
								alertNotice = new AlertNotice();
								alertNotice.setSubject("保质期提醒");
								alertNotice.setBoCode(inventoryCurrentStock.getSkuCode() + inventoryCurrentStock.getMassunitEndTime());
								alertNotice.setContent(inventoryCurrentStock.getItemname() + inventoryCurrentStock.getSkuCode() + simpleDateFormat.format(inventoryCurrentStock.getMassunitEndTime()) + "已经过期.");
								alertNotice.setRemindTime(new Date());
								alertNotice.setTime(new Date());
								alertNotice.setTenantId(inventoryCurrentStock.getTenantId());
								alertNotice.setCompanyCode(inventoryCurrentStock.getCompanyCode());
								alertNotice.setCompanyInnerCode(inventoryCurrentStock.getCompanyInnerCode());
								remindsCenterService.mergeOriginal(alertNotice);
							}
						}
					}
				}
			}
		}
	}
}
