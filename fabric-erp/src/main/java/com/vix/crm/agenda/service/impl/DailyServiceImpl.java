package com.vix.crm.agenda.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.crm.agenda.dao.IDailyDao;
import com.vix.crm.agenda.entity.Daily;
import com.vix.crm.agenda.service.IDailyService;

@Service("dailyService")
public class DailyServiceImpl extends BaseHibernateServiceImpl implements IDailyService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IDailyDao dailyDao;
	
	@Override
	public List<Daily> findAllDailyByEmployee(Date startTime,Date endTime,String employeeCode) throws Exception{
		return dailyDao.findAllDailyByEmployee(startTime, endTime, employeeCode);
	}

	@Override
	public Daily findEntityByEmployeeCodeAndDailyCode(String dailyCode,
			String employeeCode) throws Exception {
		return dailyDao.findEntityByEmployeeCodeAndDailyCode(dailyCode, employeeCode);
	}
}