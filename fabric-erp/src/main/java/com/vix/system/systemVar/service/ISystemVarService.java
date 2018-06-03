package com.vix.system.systemVar.service;

import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;
import com.vix.system.systemVar.entity.SystemVar;

public interface ISystemVarService extends IBaseHibernateService{

	/**
	 * @Title: findSystemVarPager
	 * @Description: 系统变量的分页查询
	 * @param @param pager
	 * @param @param params
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return Pager    返回类型
	 * @throws
	 */
	Pager findSystemVarPager(Pager pager, Map<String, Object> params)throws Exception;

	/**
	 * @Title: saveSystemVar
	 * @Description: 系统变量的保存
	 * @param @param entityForm
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return SystemVar    返回类型
	 * @throws
	 */
	SystemVar saveSystemVar(SystemVar entityForm)throws Exception;
}
