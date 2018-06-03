package com.vix.common.vixlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.security.util.SecurityUtil;
import com.vix.hr.organization.entity.Employee;
import com.vix.system.latestOperate.controller.OperateLog;

/**
 * 
 * com.vix.common.vixlog.VixOperateLog
 *
 * @author zhanghaibing
 *
 * @date 2014年9月5日
 */
@Controller("vixOperateLog")
@Scope("prototype")
public class VixOperateLog implements IOperateLog {
	@Autowired
	private OperateLog operateLog;

	@Override
	public void saveOperateLog(String objectType, String billCode, String url, String operate) throws Exception {
		String employeeName = null;
		// 获取当前员工信息
		if (SecurityUtil.getCurrentEmpId() != null) {
			Employee employee = operateLog.doListEmployeeById(SecurityUtil.getCurrentEmpId());
			if (employee != null) {
				employeeName = employee.getName();
			}
		}

		operateLog.saveOperateLog(objectType, billCode, url, operate, SecurityUtil.getCurrentUserTenantId(), SecurityUtil.getCurrentUserName(), employeeName, SecurityUtil.getCurrentUserAccountCompanyCode(), SecurityUtil.getCurrentUserAccountCompanyInnerCode());
	}
}
