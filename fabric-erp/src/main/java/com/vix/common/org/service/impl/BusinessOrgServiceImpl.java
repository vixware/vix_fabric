package com.vix.common.org.service.impl;

import java.util.Date;
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

import com.vix.common.org.dao.IBusinessOrgDao;
import com.vix.common.org.entity.BusinessOrg;
import com.vix.common.org.entity.BusinessOrgDetail;
import com.vix.common.org.entity.BusinessView;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.hql.BusinessOrgHqlProvider;
import com.vix.common.org.hql.OrganizationUnitHqlProvider;
import com.vix.common.org.service.IBusinessOrgService;
import com.vix.common.security.entity.Role;
import com.vix.core.constant.BizConstant;
import com.vix.core.exception.BizException;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;

/**
 * @ClassName: BusinessOrgServiceImpl
 * @Description: 业务组织业务层
 * @author wangmingchen
 * @date 2013-5-23 下午10:27:22
 * 
 */
@Service("businessOrgService")
@Transactional
public class BusinessOrgServiceImpl extends BaseHibernateServiceImpl implements IBusinessOrgService {
    
    
	private static final long serialVersionUID = 1L;

	@Resource(name="businessOrgDao")
	private IBusinessOrgDao businessOrgDao;
	 
	@Resource(name="businessOrgHqlProvider")
	private BusinessOrgHqlProvider businessOrgHqlProvider;
	
	@Resource(name="organizationUnitHqlProvider")
	private OrganizationUnitHqlProvider organizationUnitHqlProvider;
	 

	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IBusinessOrgService#findBusinessOrgPager(com.vix.core.web.Pager, java.util.Map)
	 */
	@Override
	public Pager findBusinessOrgPager(Pager pager,Map<String, Object> params) throws Exception {
		/*
		List<Employee> empList = businessOrgDao.findBusinessOrgDetailBo4ReportLine(null, "lg", true, true,
				"a9fec7a0-511e-1e24-8151-1ef337b00000", BizConstant.COMMON_ORG_E, BizConstant.COMMON_ORG_E);
		//小弟 c0a80067-5089-1f9e-8150-898ece480004
		//如花 c0a80067-5089-1f9e-8150-898624bc0001
		 *
		for(Employee emp : empList){
			System.out.println(emp.getId() + "-------------"+emp.getName());
		}
		List<Employee> empList = businessOrgDao.findBusinessOrgDetailBo4ReportLine(null, "lg", false, true,
				"a9fec7a0-5156-15d1-8151-5632b797000f", BizConstant.COMMON_ORG_E, BizConstant.COMMON_ORG_E);
		List<Employee> empList = businessOrgDao.findBusinessOrgDetailBo4ReportLine(null, "lg", false, false,
				"a9fec7a0-5132-1b9c-8151-32df8f6b0001", BizConstant.COMMON_ORG_E, BizConstant.COMMON_ORG_E);
		//小弟 c0a80067-5089-1f9e-8150-898ece480004
		//如花 c0a80067-5089-1f9e-8150-898624bc0001
		//卢彦 
		for(Employee emp : empList){
			System.out.println(emp.getId() + "-------------"+emp.getName());
		}
		*/
		
		//StringBuilder hql = businessOrgHqlProvider.findBusinessOrgList(params, null);
		StringBuilder hql = businessOrgHqlProvider.findBusinessOrgList4Page(params, pager);
		pager = businessOrgDao.findPager2ByHql(pager,businessOrgHqlProvider.entityAsName(),hql.toString(), params);
		//Pager respager = businessOrgDao.findPagerByHql(pager,businessOrgHqlProvider.entityAsName(),hql.toString(), params);
		//Pager respager = organizationUnitDao.findPagerByHql(pager,hql.toString(), params);
		return pager;
	}
	
	
	/**
	 * 查询整个业务组织列表
	 */
	@Override
	public List<BusinessOrg> findAllBusinessOrg() throws Exception{
		//List<BusinessOrg> all = businessOrgDao.findAllByHql2("select bo from BusinessOrg bo order by ", new HashMap<String, Object>());
		List<BusinessOrg> all = businessOrgDao.findAllByEntityClass(BusinessOrg.class);
		return all;
	}
	
	/**
	 * 查询业务组织树形结构
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Set<BusinessOrg> findBusinessOrgAll() throws Exception{
		Set<BusinessOrg> resList = new HashSet<BusinessOrg>();
		List<BusinessOrg> all = findAllBusinessOrg();
		
		Map<String,BusinessOrg> map = new HashMap<String, BusinessOrg>();
		if(all!=null && !all.isEmpty()){
			for(BusinessOrg bo:all){
				map.put(bo.getId(), bo);
			}
			
		}
		for(BusinessOrg bo:all){
			BusinessOrg parent = bo.getParentBusinessOrg();
			if(parent == null ){
				resList.add(bo);
			}else{
				map.get(parent.getId()).addChildren(bo);
			}
		}
		
		
		/*for(BusinessOrg bi:resList){
			System.out.println(bi.getBusinessOrgName());
			if(bi.getSubBusinessOrgs()!=null && bi.getSubBusinessOrgs().size()>0){
				for(BusinessOrg bi2:bi.getSubBusinessOrgs()){
					System.out.println("LEVEL2:"+bi2.getBusinessOrgName());
					
					if(bi2.getSubBusinessOrgs()!=null && bi2.getSubBusinessOrgs().size()>0){
						for(BusinessOrg bi3:bi2.getSubBusinessOrgs()){
							System.out.println("LEVEL3:"+bi3.getBusinessOrgName());
						}
					}
					
				}
			}
		}*/
		return resList;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IBusinessOrgService#saveUpdateBusinessOrg(com.vix.common.org.entity.BusinessOrg, char, java.lang.String)
	 */
	@Override
	public BusinessOrg saveUpdateBusinessOrg(BusinessOrg boForm,char parentType,String parentId) throws Exception{
		BusinessOrg bo = null;
		
		if(boForm!=null && StrUtils.isNotEmpty(boForm.getId())){
			bo = businessOrgDao.findEntityById(BusinessOrg.class, boForm.getId());
		}
		if(bo == null){
			bo = new BusinessOrg();
		}
		Date now = new Date();
		
		bo.setCreateTime(now);
		bo.setUpdateTime(now);
		
		bo.setBusinessOrgName(boForm.getBusinessOrgName());
		bo.setBusinessOrgCode(boForm.getBusinessOrgCode());
		bo.setParentBusinessOrg(boForm.getParentBusinessOrg());
		bo.setIsVirtualUnitNode(boForm.getIsVirtualUnitNode());
		//bo.setBizOrgType(bizOrgType);
		
		if(parentType == 'V'){
			BusinessView view = businessOrgDao.findEntityById(BusinessView.class, parentId);
			bo.setBusinessView(view);
		}else if(parentType == 'O'){
			BusinessOrg org = businessOrgDao.findEntityById(BusinessOrg.class, parentId);
			bo.setParentBusinessOrg(org);
			
			BusinessView view = businessOrgDao.findEntityById(BusinessView.class, org.getBusinessView().getId());
			if(view!=null){
				bo.setBusinessView(view);
			}
		}
		
		/*
		if(StringUtils.isNotEmpty(bizOrgType)){
			if(bizOrgType.equalsIgnoreCase(BizConstant.COMMON_ORG_O)){
				String orgUnitIdStr = bizOrgIds.substring(0, bizOrgIds.length()-1);
				OrganizationUnit unit = businessOrgDao.findEntityById(OrganizationUnit.class, Long.parseLong(orgUnitIdStr));
				bo.setOrganizationUnit(unit);
				bo.setOrgUnitName(unit.getFs());
			}else if(bizOrgType.equalsIgnoreCase(BizConstant.COMMON_ORG_R)){
				Role role = businessOrgDao.findEntityById(Role.class, Long.parseLong(bizOrgIds));
				bo.setRole(role);
				bo.setOrgUnitName(role.getName());
			}else if(bizOrgType.equalsIgnoreCase(BizConstant.COMMON_ORG_E)){
				Employee emp = businessOrgDao.findEntityById(Employee.class, Long.parseLong(bizOrgIds));
				bo.setEmployee(emp);
				bo.setOrgUnitName(emp.getName());
			}
		}
		*/
		businessOrgDao.saveOrUpdate(bo);
		return bo;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IBusinessOrgService#findBusinessOrgAllForOrgUnit(java.lang.Long)
	
	public String[] findBusinessOrgAllForOrgUnit(Long orgUnitId) throws Exception{
		String[] resStr = new String[3];
		
		StringBuilder resBulder = new StringBuilder();
		
		Set<BusinessOrg> orgUnitBusinessOrgList = businessOrgDao.findBusinessOrgByOrgUnitId(orgUnitId);
		Set<Long> orgUnitIdSet = new HashSet<Long>();
		Set<String> orgUnitNameSet = new HashSet<String>();
		
		for(BusinessOrg bo:orgUnitBusinessOrgList){
			orgUnitIdSet.add(bo.getId());
			orgUnitNameSet.add(bo.getBusinessOrgName());
		}
		
		
		Set<BusinessOrg> allBusinessOrgList = findBusinessOrgAll();
		resBulder.append("[");

		List<String> objStr = new LinkedList<String>();
		for (BusinessOrg bo : allBusinessOrgList) {
			StringBuilder oneStr = new StringBuilder();
			oneStr.append(makeBusinessOne(bo,orgUnitIdSet));
			objStr.add(oneStr.toString());
		}
		if (!objStr.isEmpty()) {
			resBulder.append(StringUtils.join(objStr.iterator(), ","));
		}

		resBulder.append("]");
		
		resStr[0] = resBulder.toString();
		resStr[1] = StringUtils.join(orgUnitIdSet, ",");
		resStr[2] = StringUtils.join(orgUnitNameSet, ",");
		return resStr;
	}
	 */
	
	/**
	 * @param bo
	 * @param orgUnitBoIdList
	 * @return
	private String makeBusinessOne(BusinessOrg bo,Set<Long> orgUnitBoIdList) {
		StringBuilder oneStr = new StringBuilder("");

		BusinessOrg parent = bo.getParentBusinessOrg();
		Set<BusinessOrg> subs = bo.getSubBusinessOrgs();
		Long pid = (parent == null ? 0L : parent.getId());
		Long id = bo.getId();
		
		oneStr.append("{\"id\":");
		oneStr.append(id);
		oneStr.append(",\"pId\":");
		oneStr.append(pid);
		oneStr.append(",\"name\":\"");
		oneStr.append(bo.getBusinessOrgName());
		if (subs != null && !subs.isEmpty()) {
			oneStr.append("\",\"isParent\":true");// open:true,
		} else {
			oneStr.append("\",\"isParent\":false");// ,open:false
		}
		
		if(orgUnitBoIdList.contains(id)){
			oneStr.append(",\"checked\":true");
		}else{
			oneStr.append(",\"checked\":false");
		}

		if (subs != null && !subs.isEmpty()) {
			oneStr.append(",\"children\":[");

			List<String> subString = new LinkedList<String>();
			for (BusinessOrg subBo : subs) {
				String str = makeBusinessOne(subBo,orgUnitBoIdList);
				subString.add(str);
			}
			oneStr.append(StringUtils.join(subString.iterator(), ","));
			oneStr.append("]");
		}

		oneStr.append("}");
		return oneStr.toString();
	}
	*/
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IBusinessOrgService#findBusinessOrgDetailByBoId(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<BusinessOrgDetail> findBusinessOrgDetailByBoId(String boId,String bizOrgType)throws Exception{
		StringBuilder hql = new StringBuilder();
		Map<String,Object> params = new HashMap<String, Object>();
		
		hql.append("select businessOrgDetail from ").append(BusinessOrgDetail.class.getName()).append(" businessOrgDetail ");//.append(ename);
		hql.append(" where businessOrgDetail.businessOrg.id = :businessOrgId ");
		params.put("businessOrgId", boId);
		
		if(StringUtils.isNotEmpty(bizOrgType)){
			hql.append(" and businessOrgDetail.bizOrgType = :bizOrgType ");
			params.put("bizOrgType", bizOrgType);
		}
		
		hql.append(" order by businessOrgDetail.id");
		
		List<BusinessOrgDetail> resList = businessOrgDao.findAllByHql2(hql.toString(), params);
		return resList;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IBusinessOrgService#findBoOrgUnitDetailByBoId(java.lang.String)
	 */
	@Override
	public List<OrganizationUnit> findBoOrgUnitDetailByBoId(String boId)throws Exception{
		List<BusinessOrgDetail> resList = findBusinessOrgDetailByBoId(boId, BizConstant.COMMON_ORG_O);
		
		List<OrganizationUnit> unitList = new LinkedList<OrganizationUnit>();
		if(resList!=null){
			for(BusinessOrgDetail bo:resList){
				OrganizationUnit ou = bo.getOrganizationUnit();
				if(ou!=null){
					unitList.add(ou);
				}
			}
		}
		return unitList;
		
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IBusinessOrgService#findBoEmpDetailByBoId(java.lang.String)
	 */
	@Override
	public List<Employee> findBoEmpDetailByBoId(String boId)throws Exception{
		List<BusinessOrgDetail> resList = findBusinessOrgDetailByBoId(boId, BizConstant.COMMON_ORG_E);
		
		List<Employee> empList = new LinkedList<Employee>();
		if(resList!=null){
			for(BusinessOrgDetail bo:resList){
				Employee emp = bo.getEmployee();
				if(emp!=null){
					empList.add(emp);
				}
			}
		}
		return empList;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IBusinessOrgService#findBoRoleDetailByBoId(java.lang.String)
	 */
	@Override
	public List<Role> findBoRoleDetailByBoId(String boId)throws Exception{
		List<BusinessOrgDetail> resList = findBusinessOrgDetailByBoId(boId, BizConstant.COMMON_ORG_R);
		
		List<Role> roleList = new LinkedList<Role>();
		if(resList!=null){
			for(BusinessOrgDetail bo:resList){
				Role role = bo.getRole();
				if(role!=null){
					roleList.add(role);
				}
			}
		}
		return roleList;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IBusinessOrgService#saveBoDetail(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void saveBoDetail(String boId,String[] bizOrgIdArray,String bizOrgType)throws Exception{
		BusinessOrg bo = businessOrgDao.findEntityById(BusinessOrg.class, boId);
		BusinessView boView = bo.getBusinessView();
		
		for(String bizOrgIdStr:bizOrgIdArray){
			String bizOrgId = bizOrgIdStr;
			
			BusinessOrgDetail detail = findForBoDetailCheck(boId, bizOrgId, bizOrgType);
			
			if(detail==null){
				detail = new BusinessOrgDetail();
				detail.setBusinessOrg(bo);
				detail.setBusinessView(boView);
				
				if(bizOrgType.equalsIgnoreCase(BizConstant.COMMON_ORG_O)){
					//String orgUnitIdStr = bizOrgIds.substring(0, bizOrgIds.length()-1);
					OrganizationUnit unit = businessOrgDao.findEntityById(OrganizationUnit.class, bizOrgId);
					detail.setOrganizationUnit(unit);
				}else if(bizOrgType.equalsIgnoreCase(BizConstant.COMMON_ORG_R)){
					Role role = businessOrgDao.findEntityById(Role.class, bizOrgId);
					detail.setRole(role);
				}else if(bizOrgType.equalsIgnoreCase(BizConstant.COMMON_ORG_E)){
					Employee emp = businessOrgDao.findEntityById(Employee.class, bizOrgId);
					detail.setEmployee(emp);
				}else{
					throw new BizException("未知类型！");
				}
				
				detail.setBizOrgType(bizOrgType);
				businessOrgDao.saveOrUpdate(detail);
			}
			
		}
		
	}
	
	/**
	 * 验证在某业务组织下 是否有某具体组织 员工
	 * @param boId
	 * @param bizOrgIdId
	 * @param bizOrgType
	 * @return
	 * @throws Exception
	 */
	private BusinessOrgDetail findForBoDetailCheck(String boId,String bizOrgIdId,String bizOrgType)throws Exception{
		StringBuilder hql = new StringBuilder();
		Map<String,Object> params = new HashMap<String, Object>();
		
		hql.append("select businessOrgDetail from ").append(BusinessOrgDetail.class.getName()).append(" businessOrgDetail ");//.append(ename);
		hql.append(" where businessOrgDetail.businessOrg.id = :businessOrgId ");
		params.put("businessOrgId", boId);
		
		hql.append(" and businessOrgDetail.bizOrgType = :bizOrgType ");
		params.put("bizOrgType", bizOrgType);
		
		if(bizOrgType.equalsIgnoreCase(BizConstant.COMMON_ORG_O)){
			hql.append(" and businessOrgDetail.organizationUnit.id = :bizOrgIdId ");
			params.put("bizOrgIdId", bizOrgIdId);
		}else if(bizOrgType.equalsIgnoreCase(BizConstant.COMMON_ORG_R)){
			hql.append(" and businessOrgDetail.role.id = :bizOrgIdId ");
			params.put("bizOrgIdId", bizOrgIdId);
		}else if(bizOrgType.equalsIgnoreCase(BizConstant.COMMON_ORG_E)){
			hql.append(" and businessOrgDetail.employee.id = :bizOrgIdId ");
			params.put("bizOrgIdId", bizOrgIdId);
		}
		
		
		List<BusinessOrgDetail> resList = businessOrgDao.findAllByHql2(hql.toString(), params);
		if(resList!=null){
			if(resList.size()==1){
				return resList.get(0);
			}else if(resList.size()>1){
				throw new BizException("重复选择业务组织明细数据！");
			}
		}
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IBusinessOrgService#deleteDetail(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteDetail(String id,String detailBizOrgId,String bizOrgType)throws Exception{
		BusinessOrgDetail detail = findForBoDetailCheck(id, detailBizOrgId, bizOrgType);
		if(detail!=null){
			businessOrgDao.deleteByEntity(detail);
		}
	}
}
