package com.vix.hr.hrmgr.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.hr.hrmgr.dao.IPersonnelContractDao;

@Repository("personnelContractDao")
public class PersonnelContractDaoImpl extends BaseHibernateDaoImpl implements IPersonnelContractDao {

	private static final long serialVersionUID = 1L;

}
