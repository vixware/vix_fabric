package com.vix.common.org.dao.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.vix.common.common.select.bizOrg.vo.BizOrgEmpVO;
import com.vix.common.org.dao.IBusinessViewDao;
import com.vix.common.org.entity.BusinessOrgView;
import com.vix.common.org.entity.BusinessView;
import com.vix.common.org.hql.BusinessViewHqlProvider;
import com.vix.core.constant.BizConstant;
import com.vix.core.exception.BizException;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.hr.organization.entity.Employee;

@Repository("businessViewDao")
public class BusinessViewDaoImpl extends BaseHibernateDaoImpl implements IBusinessViewDao {

	//@Resource(name = "businessViewHqlProvider")
	//private BusinessViewHqlProvider businessViewHqlProvider;

	/**
	 * 查询业务组织视图和业务组织的 联合视图
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<BusinessOrgView> findOrgViewList(String id) throws Exception {
		BusinessViewHqlProvider businessViewHqlProvider = new BusinessViewHqlProvider();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", id);
		StringBuilder hql = businessViewHqlProvider.findBusinessOrgViewList(params);
		List<BusinessOrgView> orgViewList = findAllByHql2(hql.toString(),params);
		return orgViewList;
	}
	
	@Override
	public BusinessView findBizOrgViewByCode(String bizCode) throws Exception{
        StringBuilder hqlBuilder =new StringBuilder();
        Map<String,Object> params = new HashMap<String, Object>();
        hqlBuilder.append("select bz from ");
        hqlBuilder.append(BusinessView.class.getName()).append(" bz where bz.code = :code ");
        params.put("code", bizCode);
	
        BusinessView bv = findObjectByHql(hqlBuilder.toString(), params);
        return bv;
	}
	//查询直接上级
	@Override
	public List<BizOrgEmpVO> findLeaderByEmpId(String empId) throws Exception{
		BusinessView bv = findDefaultBizViewByEmpId(empId);
		if(bv==null){
			throw new BizException("没有职员上下级关系！");
		}
		String bvId = bv.getId();
		
		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append(" SELECT  emp1.ID empId,emp1.Name treeName,obd1.BusinessOrg_ID bizOrgId ");
		sql.append("FROM HR_ORG_EMPLOYEE emp1  ");
		sql.append("INNER JOIN MDM_ORG_BUSINESSORG_DETAIL obd1 ON obd1.Employee_ID = emp1.ID AND obd1.BizOrgType= ? ");
		params.add(BizConstant.COMMON_ORG_E);
		sql.append(" WHERE obd1.BusinessOrg_ID IN (");
			sql.append("SELECT ob.Parent_ID FROM MDM_ORG_BUSINESSORG_DETAIL obd ");
			sql.append("INNER JOIN MDM_ORG_BUSINESSORG ob ON ob.ID = obd.BusinessOrg_ID ");
			sql.append("INNER JOIN HR_ORG_EMPLOYEE emp ON emp.ID = obd.Employee_ID ");
			sql.append("WHERE obd.Employee_ID = ? AND obd.BizOrgType= ? AND obd.BusinessView_ID = ? ");
			params.add(empId);
			params.add(BizConstant.COMMON_ORG_E);
			params.add(bvId);
		sql.append(" ) ");
		sql.append(" ORDER BY emp1.ID DESC ");
		List<BizOrgEmpVO> empList = queryObjectListBySql(sql.toString(), BizOrgEmpVO.class, params.toArray());
    	return empList;
	}
	
	//查询直接下属
	@Override
	public List<BizOrgEmpVO> findSubByEmpId(String empId )throws Exception{
		BusinessView bv = findDefaultBizViewByEmpId(empId);
		if(bv==null){
			throw new BizException("没有职员上下级关系！");
		}
		String bvId = bv.getId();
		
		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		
		sql.append(" SELECT  emp1.ID empId,emp1.Name treeName,obd1.BusinessOrg_ID bizOrgId ");
		sql.append("FROM HR_ORG_EMPLOYEE emp1 INNER JOIN MDM_ORG_BUSINESSORG_DETAIL obd1 ON obd1.Employee_ID = emp1.ID AND obd1.BizOrgType= ? ");
		params.add(BizConstant.COMMON_ORG_E);
		sql.append("INNER JOIN MDM_ORG_BUSINESSORG ob1 ON ob1.ID = obd1.BusinessOrg_ID ");
		sql.append("WHERE ob1.Parent_ID IN (");
			sql.append("SELECT ob.ID  FROM MDM_ORG_BUSINESSORG_DETAIL obd ");
			sql.append("INNER JOIN MDM_ORG_BUSINESSORG ob ON ob.ID = obd.BusinessOrg_ID ");
			sql.append("INNER JOIN HR_ORG_EMPLOYEE emp ON emp.ID = obd.Employee_ID ");
			sql.append("WHERE obd.Employee_ID = ? AND obd.BizOrgType= ? AND obd.BusinessView_ID = ? ");
			params.add(empId);
			params.add(BizConstant.COMMON_ORG_E);
			params.add(bvId);
		sql.append(")");
		sql.append(" ORDER BY emp1.ID DESC ");
		List<BizOrgEmpVO> empList = queryObjectListBySql(sql.toString(), BizOrgEmpVO.class, params.toArray());
    	return empList;
	}
	
	//查询所有下属
	@Override
	public List<BizOrgEmpVO> findAllSubByEmpId(String empId )throws Exception{
		BusinessView bv = findDefaultBizViewByEmpId(empId);
		if(bv==null){
			throw new BizException("没有职员上下级关系！");
		}
		String bvId = bv.getId();
		
		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		
		sql.append(" SELECT  emp1.ID empId,emp1.Name treeName,obd1.BusinessOrg_ID bizOrgId ");
		sql.append("FROM HR_ORG_EMPLOYEE emp1 ");
		sql.append("INNER JOIN MDM_ORG_BUSINESSORG_DETAIL obd1 ON obd1.Employee_ID = emp1.ID AND obd1.BizOrgType=? ");
		params.add(BizConstant.COMMON_ORG_E);
		sql.append("INNER JOIN MDM_ORG_BUSINESSORG ob1 ON ob1.ID = obd1.BusinessOrg_ID ");
		
		sql.append("INNER JOIN (  ");
			sql.append("SELECT ob.id , ob.BusinessOrgCode  FROM MDM_ORG_BUSINESSORG_DETAIL obd   ");
			sql.append("INNER JOIN MDM_ORG_BUSINESSORG ob ON ob.ID = obd.BusinessOrg_ID   ");
			sql.append("INNER JOIN HR_ORG_EMPLOYEE emp ON emp.ID = obd.Employee_ID  ");
			sql.append("WHERE obd.Employee_ID =? AND obd.BizOrgType= ? AND obd.BusinessView_ID = ?   ");
			params.add(empId);
			params.add(BizConstant.COMMON_ORG_E);
			params.add(bvId);
		sql.append(" ) MBC ON ob1.BusinessOrgCode LIKE CONCAT(MBC.BusinessOrgCode,'%')  ");
		
		sql.append("WHERE ob1.id!=?  ");
		params.add(empId);
		sql.append(" ORDER BY emp1.ID DESC ");
		List<BizOrgEmpVO> empList = queryObjectListBySql(sql.toString(), BizOrgEmpVO.class, params.toArray());
    	return empList;
	}
	
	//查询承租户的默认业务视图
	@Override
	public BusinessView findDefaultBizViewByEmpId(String empId) throws Exception{
		Employee emp = findEntityById(Employee.class, empId);
		String tenantId = emp.getTenantId();
		if(StringUtils.isEmpty(tenantId)){
			return null;
		}
		String defaultTenantBizViewCode = BizConstant.COMMON_DEFAULT_FLAG+tenantId;
		BusinessView bv = findBizOrgViewByCode(defaultTenantBizViewCode);
		return bv;
	}
	@Override
	public BusinessView findDefaultBizViewByTenantId(String tenantId) throws Exception{
		if(StringUtils.isEmpty(tenantId)){
			return null;
		}
		String defaultTenantBizViewCode = BizConstant.COMMON_DEFAULT_FLAG+tenantId;
		BusinessView bv = findBizOrgViewByCode(defaultTenantBizViewCode);
		return bv;
	}
	
}
