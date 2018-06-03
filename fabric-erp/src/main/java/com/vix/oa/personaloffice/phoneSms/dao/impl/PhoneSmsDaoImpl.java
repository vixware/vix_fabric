package com.vix.oa.personaloffice.phoneSms.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.oa.personaloffice.phoneSms.dao.IPhoneSmsDao;


@Repository("phoneSmsDao")
public class PhoneSmsDaoImpl extends BaseHibernateDaoImpl implements IPhoneSmsDao {

	private static final long serialVersionUID = 1L;

}
