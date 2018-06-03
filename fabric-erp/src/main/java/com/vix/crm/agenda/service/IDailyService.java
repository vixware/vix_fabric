package com.vix.crm.agenda.service;

import java.util.Date;
import java.util.List;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.crm.agenda.entity.Daily;


public interface IDailyService extends IBaseHibernateService{

	public List<Daily> findAllDailyByEmployee(Date startTime,Date endTime,String employeeCode) throws Exception;
	
	public Daily findEntityByEmployeeCodeAndDailyCode(String dailyCode,String employeeCode) throws Exception;
}
