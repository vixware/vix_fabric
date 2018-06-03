package com.vix.common.securityDra.dao;

import java.util.List;

import com.google.common.collect.BiMap;
import com.vix.common.security.entity.DataResRowMethod;
import com.vix.common.security.entity.DataResRowMethodConfig;
import com.vix.common.securityDra.vo.row.DataResRowMethodConfigVo;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

public interface IDataResRowMethodConfigDao extends IBaseHibernateDao {

	/**
	 * 查询所有的配置方法
	 * @return
	 * @throws Exception
	 */
	List<DataResRowMethod> findAllDataResRowMethod() throws Exception;

	
	List<DataResRowMethodConfigVo> findAllDataResRowMethodConfigVo()throws Exception;
	
	/**
	 * 查找用户的元数据 待拦截配置项
	 * @return
	 * @throws Exception
	 */
	List<DataResRowMethodConfig> findUserDataResRowMethodConfg()throws Exception;
	
	/**
	 * 查询 hqlprovider（类名全称＋方法名）     metadata id
	 * 
	 * @return
	 * @throws Exception
	 */
	//Map<String,String> findDataResRowMethodMap()throws Exception;
	
	/**
	 * 查询 hqlprovider（类名全称＋方法名）   methodId id
	 * 
	 * @return
	 * @throws Exception
	 */
	BiMap<String,String> findRowMethodMap()throws Exception;
	
	
}
