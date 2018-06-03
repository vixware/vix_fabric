package com.vix.common.org.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.dao.IBaseOrganizationDao;
import com.vix.common.org.service.IBaseOrganizationChargeOfService;
import com.vix.common.share.entity.BaseEmployee;
import com.vix.common.share.entity.BaseOrganization;
import com.vix.common.share.entity.BaseOrganizationChargeOf;
import com.vix.common.share.entity.BaseOrganizationUnit;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;

/**
 * @ClassName: OrganizationChargeOfServiceImpl
 * @Description: 分管公司或者部门
 * @author wangmingchen
 * @date 2014-5-19 下午10:27:22
 * 
 */
@Service("baseOrganizationChargeOfService")
@Transactional
public class BaseOrganizationChargeOfServiceImpl extends BaseHibernateServiceImpl implements IBaseOrganizationChargeOfService {
    
	private static final long serialVersionUID = 1L;
	
	//@Autowired
	//private IBaseOrganizationChargeOfDao organizationChargeOfDao;
	@Resource(name="baseOrganizationDao")
    private IBaseOrganizationDao organizationDao;
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IOrganizationChargeOfService#findChargeOfByEmpId(java.lang.String)
	 */
	@Override
	public BaseOrganizationChargeOf findChargeOfByEmpId(String empolyeeId) throws Exception{
		StringBuilder hql = new StringBuilder();
		Map<String,Object> params = new HashMap<String, Object>();
		
		hql.append("select organizationChargeOf from ").append(BaseOrganizationChargeOf.class.getName()).append(" organizationChargeOf ");//.append(ename);
		hql.append(" where organizationChargeOf.employee.id = :empolyeeId ");
		params.put("empolyeeId", empolyeeId);
		
		List<BaseOrganizationChargeOf> empList = organizationDao.findAllByHql2NoTenantId(hql.toString(), params);
		
		BaseOrganizationChargeOf chargeOfEmp = null;
		if(empList!=null && !empList.isEmpty()){
			if(empList.size()==1){
				chargeOfEmp = empList.get(0);
			}
		}
		return chargeOfEmp;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IOrganizationChargeOfService#findChargeOfOrgByEmpId(java.lang.String)
	 */
	@Override
	public Set<BaseOrganization> findChargeOfOrgByEmpId(String empolyeeId) throws Exception{
		BaseOrganizationChargeOf chargeOfEmp = findChargeOfByEmpId(empolyeeId);
		if(chargeOfEmp!=null){
			return chargeOfEmp.getOrganizations();
		}
		return null;
		
	}
	@Override
	public List<BaseOrganization> findChargeOfOrgListByEmpId(String empolyeeId) throws Exception{
		Set<BaseOrganization> orgSet = findChargeOfOrgByEmpId(empolyeeId);
		List<BaseOrganization> resList = new LinkedList<BaseOrganization>();
		if(orgSet!=null){
			resList.addAll(orgSet);
		}
		return resList;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IOrganizationChargeOfService#findChargeOfOrgUnitByEmpId(java.lang.String)
	 */
	@Override
	public Set<BaseOrganizationUnit> findChargeOfOrgUnitByEmpId(String empolyeeId) throws Exception{
		BaseOrganizationChargeOf chargeOfEmp = findChargeOfByEmpId(empolyeeId);
		if(chargeOfEmp!=null){
			return chargeOfEmp.getOrganizationUnits();
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IOrganizationChargeOfService#findChargeOfOrgUnitListByEmpId(java.lang.String)
	 */
	@Override
	public List<BaseOrganizationUnit> findChargeOfOrgUnitListByEmpId(String empolyeeId) throws Exception{
		Set<BaseOrganizationUnit> orgUnitSet = findChargeOfOrgUnitByEmpId(empolyeeId);
		List<BaseOrganizationUnit> resList = new LinkedList<BaseOrganizationUnit>();
		if(orgUnitSet!=null){
			resList.addAll(orgUnitSet);
		}
		return resList;
	}

	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IOrganizationChargeOfService#saveChargeOf(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void saveChargeOf(String orgType,String empId,String orgId,String orgUnitIds)throws Exception{
		BaseOrganizationChargeOf chargeOfEmp = findChargeOfByEmpId(empId);
		//return chargeOfEmp.getOrganizations();
		if(chargeOfEmp==null){
			chargeOfEmp = new BaseOrganizationChargeOf();
			BaseEmployee emp = findEntityById(BaseEmployee.class, empId);
			chargeOfEmp.setEmployee(emp);
		}
		
		if(orgType.equals("C") && StringUtils.isNotEmpty(orgId)){
			BaseOrganization org = findEntityById(BaseOrganization.class, orgId);
			
			Set<BaseOrganization> orgs = chargeOfEmp.getOrganizations();
			if(orgs==null) orgs = new HashSet<BaseOrganization>();
			
			orgs.add(org);
			
			chargeOfEmp.setOrganizations(orgs);
			saveOrUpdate(chargeOfEmp);
		}else if(orgType.equals("O")){
			Set<BaseOrganizationUnit> orgUnits = chargeOfEmp.getOrganizationUnits();
			if(orgUnits==null) orgUnits = new HashSet<BaseOrganizationUnit>();
			
			if(StringUtils.isNotEmpty(orgUnitIds)){
				String[] orgUnitsArray = StringUtils.split(orgUnitIds, "\\,");
				BaseOrganizationUnit unit = null;
				for(String orgUnitId:orgUnitsArray){
					if(StringUtils.isNotEmpty(orgUnitId)){
						unit = findEntityById(BaseOrganizationUnit.class, orgUnitId);
						orgUnits.add(unit);
					}
				}
				
				chargeOfEmp.setOrganizationUnits(orgUnits);
				saveOrUpdate(chargeOfEmp);
			}
		}
			
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IOrganizationChargeOfService#deleteChargeOf(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void deleteChargeOf(String orgType,String empId,String orgOrUnitId)throws Exception{
		BaseOrganizationChargeOf chargeOfEmp = findChargeOfByEmpId(empId);
		if(chargeOfEmp!=null){
			if(orgType.equals("C")){
				BaseOrganization org = findEntityById(BaseOrganization.class, orgOrUnitId);
				Set<BaseOrganization> orgs = chargeOfEmp.getOrganizations();
				orgs.remove(org);
				
				chargeOfEmp.setOrganizations(orgs);
				update(chargeOfEmp);
			}else if(orgType.equals("O")){
				Set<BaseOrganizationUnit> orgUnits = chargeOfEmp.getOrganizationUnits();
				BaseOrganizationUnit unit = findEntityById(BaseOrganizationUnit.class, orgOrUnitId);
				orgUnits.remove(unit);
				
				chargeOfEmp.setOrganizationUnits(orgUnits);
				update(chargeOfEmp);
			}
		}
	}

}
