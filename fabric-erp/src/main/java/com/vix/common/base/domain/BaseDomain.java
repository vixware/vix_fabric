package com.vix.common.base.domain;

import org.springframework.beans.factory.annotation.Autowired;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

public class BaseDomain {
	@Autowired
	public IBaseHibernateService baseHibernateService;

	public BaseDomain() {
		super();
	}

}