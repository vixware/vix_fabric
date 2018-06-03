package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.IComplaintTypeDao;

@Repository("complaintTypeDao")
public class ComplaintTypeDaoImpl extends BaseHibernateDaoImpl implements IComplaintTypeDao {

	private static final long serialVersionUID = 1L;

}
