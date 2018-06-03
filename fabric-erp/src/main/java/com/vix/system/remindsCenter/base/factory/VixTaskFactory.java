/**
 * 
 */
package com.vix.system.remindsCenter.base.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vix.crm.customer.task.ContactPersonBirthdayAlertTask;
import com.vix.system.remindsCenter.base.basetask.VixAlertTask;
import com.vix.system.remindsCenter.base.task.inventory.InventoryTask;
import com.vix.system.remindsCenter.base.task.inventory.QualityGuaranteePeriodTask;

/**
 * TODO
 * 
 * @author zhanghaibing
 * 
 * @date 2014-5-23
 */
@Controller("vixTaskFactory")
public class VixTaskFactory {
	@Autowired
	private InventoryTask inventoryTask;
	@Autowired
	private QualityGuaranteePeriodTask qualityGuaranteePeriodTask;
	@Autowired
	private ContactPersonBirthdayAlertTask contactPersonBirthdayAlertTask;

	public VixAlertTask getTask(String taskMethodName) throws Exception {
		VixAlertTask vixAlertTask = null;

		if (taskMethodName.equalsIgnoreCase(InventoryTask.TASK_METHOD_NAME)) {
			// 处理库存预警任务
			vixAlertTask = inventoryTask;
		} else if (taskMethodName.equalsIgnoreCase(QualityGuaranteePeriodTask.TASK_METHOD_NAME)) {
			// 处理保质期提醒
			vixAlertTask = qualityGuaranteePeriodTask;
		} else if (taskMethodName.equalsIgnoreCase(ContactPersonBirthdayAlertTask.TASK_METHOD_NAME)) {
			// 会员生日提醒
			vixAlertTask = contactPersonBirthdayAlertTask;
		} else {
			throw new Exception("error");
		}
		return vixAlertTask;
	}

}
