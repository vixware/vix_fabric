package com.vix.common.securityDra.dao;

import java.util.List;

import com.vix.common.securityDra.vo.row.DataResRowSystemParamsVO;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

public interface IDataResRowSysParamDao  extends IBaseHibernateDao{

	/**
	 * 查询所有系统定义常量
	 * @return
	 * @throws Exception
	 */
	public List<DataResRowSystemParamsVO> findResRowSysParams()throws Exception;
}
