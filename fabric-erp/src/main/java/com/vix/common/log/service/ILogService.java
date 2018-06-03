package com.vix.common.log.service;

import com.vix.common.share.entity.BaseEmployee;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

public interface ILogService extends IBaseHibernateService{

	/**
	 * @Title: saveOrUpdateLoginLog
	 * @Description: 保存登录日志
	 * @param @param loginName
	 * @param @param loginIp
	 * @param @param tenantId
	 * @param @param companyInnerCode
	 * @param @param isSuccess
	 * @param @param loginFailMsg    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void saveOrUpdateLoginLog(String loginName,String loginIp,String tenantId,String companyInnerCode,BaseEmployee emp,Boolean isSuccess,String loginFailMsg);
}
