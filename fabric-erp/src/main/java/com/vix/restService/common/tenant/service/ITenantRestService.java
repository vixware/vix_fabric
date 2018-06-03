package com.vix.restService.common.tenant.service;

import java.util.List;

import com.vix.common.org.entity.Organization;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.system.base.compOperation.vo.CompanyOperationVO;

public interface ITenantRestService extends IBaseHibernateService{

	/**
	 * @Title: save4InitTenant
	 * @Description: 初始化承租户
	 * @param @param vo
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return Organization    返回类型
	 * @throws
	 */
	public Organization save4InitTenant(CompanyOperationVO vo)throws Exception;
	
	/**
	 * @Title: findOrgTreeByTenantId
	 * @Description: 根据承租户标识获取整个公司树
	 * @param @param tenantId
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return List<CompanyOperationVO>    返回类型
	 * @throws
	 */
	public List<CompanyOperationVO> findOrgTreeByTenantId(String tenantId)throws Exception;
}
