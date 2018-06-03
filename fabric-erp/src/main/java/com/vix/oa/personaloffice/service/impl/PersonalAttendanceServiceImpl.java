package com.vix.oa.personaloffice.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.nvix.oa.attendance.entity.PunchCardRecord;
import com.vix.oa.personaloffice.dao.IPersonalAttendanceDao;
import com.vix.oa.personaloffice.service.IPersonalAttendanceService;

@Service("personalAttendanceService")
public class PersonalAttendanceServiceImpl extends BaseHibernateServiceImpl implements IPersonalAttendanceService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IPersonalAttendanceDao personalAttendanceDao;
	
	@Override
	public <T> List<T> findAllTopEntityByCount(Class<T> entityClass, String orderField, String orderBy, int count) throws Exception {
		return personalAttendanceDao.findAllTopEntityByCount(entityClass, orderField, orderBy, count);
	}

	@Override
	public <T> List<T> findAllTopEntityByCount(Class<T> entityClass, String orderField, String orderBy, int count,String companyCode) throws Exception {
		return personalAttendanceDao.findAllTopEntityByCount(entityClass, orderField, orderBy, count,companyCode);
	}

	@Override
	public List<PunchCardRecord> findAllTopEntityByCountAndConditions(Class<PunchCardRecord> entityClass, String orderField, String orderBy, int count,Map<String, Object> params) throws Exception {
		return personalAttendanceDao.findAllTopEntityByCountAndConditions(entityClass, orderField, orderBy, count, params);
	}

	@Override
	public <T> List<T> findAllTopEntityByHql(String hql, int count) throws Exception {
		return personalAttendanceDao.findAllTopEntityByHql(hql, count);
	}
}
