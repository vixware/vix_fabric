/**
 * 
 */
package com.vix.system.remindsCenter.base.adapter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vix.system.remindsCenter.base.adapter.VixAdapter;
import com.vix.system.remindsCenter.base.basetask.VixAlertTask;
import com.vix.system.remindsCenter.base.factory.VixTaskFactory;

/**
 * @author zhanghaibing
 * 
 * @date 2014-5-22
 */
@Controller("defaultVixAdapter")
public class DefaultVixAdapter implements VixAdapter {
	@Autowired
	private VixTaskFactory vixTaskFactory;

	@Override
	public void toDoTask(String classUniqueId) throws Exception {
		VixAlertTask vixAlertTask = vixTaskFactory.getTask(classUniqueId);
		// VixAlertTask vixAlertTask =
		// vixTaskFactory.getInstance().getTask(classUniqueId);
		vixAlertTask.doTask();
	}
}
