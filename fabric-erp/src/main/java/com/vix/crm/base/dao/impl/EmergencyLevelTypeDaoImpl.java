package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.IEmergencyLevelTypeDao;

@Repository("emergencyLevelTypeDao")
public class EmergencyLevelTypeDaoImpl extends BaseHibernateDaoImpl implements IEmergencyLevelTypeDao {

	private static final long serialVersionUID = 1L;

}
