package com.vix.system.industrymanagement.service;

import java.util.List;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.system.industrymanagement.entity.IndustryManagementModule;

/**
 * 行业模块业务接口
 * @author shadow
 *
 */
public interface IIndustryManagementModuleService extends IBaseHibernateService {
	
	/**
	 * 根据行业id 查询行业模块列表
	 * @param industryMgtId
	 * @return
	 * @throws Exception
	 */
	public List<IndustryManagementModule> findIndustryManagementModuleByIndustryMgtId(String industryMgtId) throws Exception;
}
