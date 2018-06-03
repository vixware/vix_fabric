package com.vix.system.enumeration.service.impl;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.system.enumeration.dao.IEnumerationDao;
import com.vix.system.enumeration.entity.Enumeration;
import com.vix.system.enumeration.service.IEnumerationService;

@Service("enumerationService")
@Transactional
public class EnumerationServiceImpl extends BaseHibernateServiceImpl implements IEnumerationService {

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.vix.system.enumeration.service.IEnumerationService#importWareHouse(java.io.File, java.lang.String)
	 */
	
	@Autowired
	private IEnumerationDao enumerationDao;
	
	@Override
	public int importFiles(File file, String fileName) throws Exception {
		if(null == file || null == fileName){
			return 0;
		}
		return enumerationDao.importFiles(file,fileName);
	}

	/* (non-Javadoc)
	 * @see com.vix.system.enumeration.service.IEnumerationService#findEnumerationByHql(java.lang.String, java.lang.String)
	 */
	@Override
	public Enumeration findEnumerationByHql(String hql, Map<String,Object> params) throws Exception {
		return enumerationDao.findEnumerationByHql(hql,params);
	}

}
