package com.vix.common.log.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.log.dao.ILogDao;
import com.vix.common.log.entity.LoginLog;
import com.vix.common.log.service.ILogService;
import com.vix.common.share.entity.BaseEmployee;
import com.vix.core.constant.BizConstant;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;


@Service("logService")
@Transactional
public class LogServiceImpl extends BaseHibernateServiceImpl implements ILogService {

	private static final long serialVersionUID = 1L;
	
	@Resource(name="logDao")
	private ILogDao logDao;
	
	@Override
	public void saveOrUpdateLoginLog(String loginName,String loginIp,String tenantId,String companyInnerCode,BaseEmployee emp,Boolean isSuccess,String loginFailMsg){
		Date now = new Date();
		
		LoginLog log = new LoginLog();
		log.setLoginName(loginName);
		log.setLoginTime(now);
		log.setLoginIp(loginIp);
		log.setTenantId(tenantId);
		log.setCompanyInnerCode(companyInnerCode);
		if(emp!=null){
			log.setEmployeeId(emp.getId());
			log.setEmployeeName(emp.getName());
			log.setEmployeeIdCard(emp.getIdNumber());
		}
		if(isSuccess){
			log.setLoginStatus(BizConstant.COMMON_BOOLEAN_YES);
		}else{
			log.setLoginStatus(BizConstant.COMMON_BOOLEAN_NO);
		}
		log.setLoginFailMsg(loginFailMsg);
		
		try {
			mergeOriginal(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
