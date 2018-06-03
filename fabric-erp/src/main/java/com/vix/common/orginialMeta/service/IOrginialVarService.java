package com.vix.common.orginialMeta.service;

import java.util.Map;

import com.vix.common.orginialMeta.entity.OrginialVar;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IOrginialVarService extends IBaseHibernateService{

	/**
	 * @Title: findOrginialVarPager
	 * @Description: 系统变量的分页查询
	 * @param @param pager
	 * @param @param params
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return Pager    返回类型
	 * @throws
	 */
	Pager findOrginialVarPager(Pager pager, Map<String, Object> params)throws Exception;

	/**
	 * @Title: saveOrginialVar
	 * @Description: 系统变量的保存
	 * @param @param entityForm
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return OrginialVar    返回类型
	 * @throws
	 */
	OrginialVar saveOrginialVar(OrginialVar entityForm)throws Exception;
}
