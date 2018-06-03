package com.vix.crm.agenda.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.core.utils.DateUtil;
import com.vix.crm.agenda.dao.IDailyDao;
import com.vix.crm.agenda.entity.Daily;

@Repository("dailyDao")
public class DailyDaoImpl extends BaseHibernateDaoImpl implements IDailyDao {

	private static final long serialVersionUID = 1L;

	@Override
	@SuppressWarnings("unchecked")
	public List<Daily> findAllDailyByEmployee(Date startTime,Date endTime,String employeeCode) throws Exception{
		String hql = "from com.vix.crm.agenda.entity.Daily d where (d.startTime between '"+DateUtil.format(startTime, "yyyy-MM-dd")+"' and '"+DateUtil.format(endTime, "yyyy-MM-dd")+"') and d.employeeCode = '"+employeeCode+"'";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	@Override
	public Daily findEntityByEmployeeCodeAndDailyCode(String dailyCode,String employeeCode) throws Exception {
		String hql = "from com.vix.crm.agenda.entity.Daily d where d.employeeCode = '"+employeeCode+"' and d.dailyCode = '"+ dailyCode +"'";
		Query query = getSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Daily> list = query.list();
		if(null != list && list.size() >= 1){
			return list.get(0);
		}
		return null;
	}
}
