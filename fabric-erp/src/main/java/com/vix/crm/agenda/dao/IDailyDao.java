package com.vix.crm.agenda.dao;

import java.util.Date;
import java.util.List;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.crm.agenda.entity.Daily;

public interface IDailyDao extends IBaseHibernateDao {

	public List<Daily> findAllDailyByEmployee(Date startTime,Date endTime,String employeeCode) throws Exception;
	
	public Daily findEntityByEmployeeCodeAndDailyCode(String dailyCode,String employeeCode) throws Exception;
}
