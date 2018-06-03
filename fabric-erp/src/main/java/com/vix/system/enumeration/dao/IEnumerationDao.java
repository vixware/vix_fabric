package com.vix.system.enumeration.dao;

import java.io.File;
import java.util.Map;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.system.enumeration.entity.Enumeration;

public interface IEnumerationDao extends IBaseHibernateDao {

	/**
	 * 字典仓库
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
