package com.vix.common.securityDra.service;

import java.util.Map;

import com.vix.common.security.entity.DataResRowPolicy;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IDataResRowPolicyService extends IBaseHibernateService {

	/**
	 * 分页查询
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findDataResRowPolicyPager(Pager pager, Map<String, Object> params) throws Exception;
	/**
	 * 保存策略
	 * @param addPlyObjIds
	 * @param deletePlyObjIds
	 * @param plyObj
	 * @return
	 * @throws Exception
	 */
	public DataResRowPolicy saveOrUpdate(String addPlyObjIds, String deletePlyObjIds,DataResRowPolicy plyObj) throws Exception;
}
