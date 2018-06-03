package com.vix.crm.analyse.service;

import java.util.List;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.crm.analyse.wraper.RfmWraper;

public interface IRfmAnalyseService extends IBaseHibernateService{

	
	public List<List<RfmWraper>> findRfmByDate(String year,String tenlantId) throws Exception;
}
