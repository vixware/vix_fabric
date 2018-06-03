package com.vix.system.enumeration.service;

import java.io.File;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.system.enumeration.entity.Enumeration;

public interface IEnumerationService extends IBaseHibernateService{
	
	/**
	 * 导入字典
	 * @param file           文件
	 * @param fileName       文件名称
	 * @return 导入数据的数量
	 * @throws Exception
	 */
	public int importFiles(File file,String fileName) throws Exception;

	/**
	 * @param hql
	 * @param params
	 * @return
	 */
	public Enumeration findEnumerationByHql(String hql, Map<String,Object> params) throws Exception;
	
}
