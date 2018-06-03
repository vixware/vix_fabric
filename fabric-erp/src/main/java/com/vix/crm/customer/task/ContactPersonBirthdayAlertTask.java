package com.vix.crm.customer.task;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vix.common.share.entity.AlertNotice;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.DateUtil;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.system.remindsCenter.base.basetask.VixAlertTask;
import com.vix.system.remindsCenter.base.service.IRemindsCenterService;

/** 客户生日提醒 */
@Controller("contactPersonBirthdayAlertTask")
public class ContactPersonBirthdayAlertTask extends VixAlertTask {

	public static final String TASK_METHOD_NAME = "vix.crm";

	@Autowired
	private IRemindsCenterService remindsCenterService;

	@Override
	public String getTaskMethodName() {
		return TASK_METHOD_NAME;
	}

	@Override
	public void doTask() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 2);
		String sDate = DateUtil.format(DateUtil.getFirstDayOfMonth(cal.getTime()), "yyyy-MM-dd");
		cal.add(Calendar.DATE, 1);
		String eDate = DateUtil.format(DateUtil.getLastDayOfMonth(cal.getTime()), "yyyy-MM-dd");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("birthday," + SearchCondition.BETWEENAND, sDate + " 00:00:01!" + eDate + " 23:59:59");
		List<ContactPerson> contactPersonList = remindsCenterService.findRemindsList(ContactPerson.class);
		if (contactPersonList != null && contactPersonList.size() > 0) {
			for (ContactPerson cp : contactPersonList) {
				AlertNotice notice = new AlertNotice();
				notice.setSubject("生日提醒，" + cp.getLastName() + cp.getFirstName());
				notice.setTime(new Date());
				/*if (null != cp.getCustomerAccount() && null != cp.getCustomerAccount().getType() && "enterPrise".equals(cp.getCustomerAccount().getType())) {
					notice.setContent("企业客户" + cp.getCustomerAccount().getShorterName() + "下的联系人" + cp.getLastName() + cp.getFirstName() + "于" + DateUtil.format(cp.getBirthday()) + "过生日。");
				} else {
					notice.setContent("个人客户" + cp.getLastName() + cp.getFirstName() + "于" + DateUtil.format(cp.getBirthday()) + "过生日。");
				}*/
				remindsCenterService.mergeOriginal(notice);
			}
		}
	}
}
