package com.vix.common.org.dao;

import java.util.List;

import com.vix.common.common.select.bizOrg.vo.BizOrgEmpVO;
import com.vix.common.org.entity.BusinessOrgView;
import com.vix.common.org.entity.BusinessView;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

public interface IBusinessViewDao extends IBaseHibernateDao {

	/**
	 * 查询业务组织视图和业务组织的 联合视图
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<BusinessOrgView> findOrgViewList(String id) throws Exception;
	
	//根据编码查询业务组织视图
	BusinessView findBizOrgViewByCode(String bizCode)throws Exception;
	//查询直接上级
	List<BizOrgEmpVO> findLeaderByEmpId(String empId )throws Exception;
	//查询直接下属
	List<BizOrgEmpVO> findSubByEmpId(String empId )throws Exception;
	//查询所有下属
	List<BizOrgEmpVO> findAllSubByEmpId(String empId )throws Exception;
	//根据职员id 查询 承租户默认业务视图
	BusinessView findDefaultBizViewByEmpId(String empId) throws Exception;
	//根据承租户id获取默认承租户视图
	BusinessView findDefaultBizViewByTenantId(String tenantId) throws Exception;
}
