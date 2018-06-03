package com.vix.common.org.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.dao.IBusinessOrgDao;
import com.vix.common.org.dao.IOrganizationDao;
import com.vix.common.org.dao.IOrganizationUnitDao;
import com.vix.common.org.entity.OrgView;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.hql.BusinessOrgHqlProvider;
import com.vix.common.org.hql.OrganizationUnitHqlProvider;
import com.vix.common.org.service.IOrganizationService;
import com.vix.common.org.service.IOrganizationUnitService;
import com.vix.common.org.vo.OrgUnit;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.BizConstant;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.hr.position.entity.OrgPositionView;

/**
 * @ClassName: OrganizationUnitServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wangmingchen
 * @date 2013-5-23 下午10:27:22
 * 
 */
@Service("organizationUnitService")
@Transactional
public class OrganizationUnitServiceImpl extends BaseHibernateServiceImpl implements IOrganizationUnitService {
    
    
	private static final long serialVersionUID = 1L;

	 @Autowired
	 private IOrganizationService organizationService;
	 
	 @Resource(name="organizationDao")
	 private IOrganizationDao organizationDao;
	 
	 @Resource(name="organizationUnitDao")
	 private IOrganizationUnitDao organizationUnitDao;
	 
	 @Resource(name="organizationUnitHqlProvider")
	 private OrganizationUnitHqlProvider organizationUnitHqlProvider;
	 
	 
	 @Resource(name="businessOrgDao")
	 private IBusinessOrgDao businessOrgDao;
	 
	 @Resource(name="businessOrgHqlProvider")
	 private BusinessOrgHqlProvider businessOrgHqlProvider;
	 
	 /**
	  * 组织机构树的视图
	  */
	@Override
	public List<OrgView> findOrgViewList(String id) throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();
		if(StrUtils.isNotEmpty(id)){
			params.put("parentId", id);
		}else{
			params.put("parentId", null);
		}
		
		StringBuilder hql = businessOrgHqlProvider.findBusinessOrgViewList(params);
		
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		List<OrgView> orgViewList =  businessOrgDao.findAllByHql2(hql.toString(), params);
		return orgViewList;
	}
	//获取组织机构部门岗位视图
	@Override
	public List<OrgPositionView> findOrgPositionViewList(String id) throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();
		if(StrUtils.isNotEmpty(id)){
			params.put("parentId", id);
		}else{
			params.put("parentId", null);
		}
		
		StringBuilder hql = businessOrgHqlProvider.findOrgPositionViewList(params);
		
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		List<OrgPositionView> orgViewList =  businessOrgDao.findAllByHql2(hql.toString(), params);
		return orgViewList;
	}
	
	@Override
	public List<OrgView> findOrgViewList(String id,String unitType) throws Exception{
		Map<String,Object> paramsTmp = new HashMap<String, Object>();
		paramsTmp.put("parentId", id);
		
		if(StringUtils.isNotEmpty(unitType)){
			paramsTmp.put("unitType", unitType);
		}
		
		StringBuilder hql = businessOrgHqlProvider.findBusinessOrgViewList(paramsTmp);
		
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(paramsTmp);//不处理CompanyInnerCode;
		List<OrgView> orgViewList =  businessOrgDao.findAllByHql2(hql.toString(), paramsTmp);
		return orgViewList;
	}
	
	
	@Override
	public List<OrgView> findOrgViewList4CmnSelect(String id,String unitType) throws Exception{
		Map<String,Object> paramsTmp = new HashMap<String, Object>();
		paramsTmp.put("parentId", id);
		
		if(StringUtils.isNotEmpty(unitType)){
			if(unitType.equalsIgnoreCase("XS")){
				paramsTmp.put("unitType", BizConstant.COMMON_ORGUNIT_TYPE_XS_LIST);
			}else{
				paramsTmp.put("unitType", unitType);
			}
		}
		
		StringBuilder hql = businessOrgHqlProvider.findBusinessOrgViewList4CmnSelect(paramsTmp);
		
		//HqlTenantIdUtil.handleParamMap4CompanyInnerCode(paramsTmp);//不处理CompanyInnerCode;
		List<OrgView> orgViewList =  businessOrgDao.findAllByHql2(hql.toString(), paramsTmp);
		return orgViewList;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IOrganizationUnitService#findOrgView4OwnCompList(java.lang.String)
	 */
	@Override
	public List<OrgView> findOrgView4OwnCompList(String id,String unitType) throws Exception{
		if(StrUtils.isEmpty(id)){
			String innerCode = SecurityUtil.getCurrentEmpOrgInnerCode();
			if(StrUtils.isNotEmpty(innerCode)){
				OrgView orgViewTmp = organizationDao.findOrgByInnerCode(innerCode);
				List<OrgView> orgViewList = new ArrayList<OrgView>();
				orgViewList.add(orgViewTmp);
				return orgViewList;
			}
		}else{
			//List<OrgView> orgViewList = findOrgViewList(id);
			List<OrgView> orgViewList = findOrgViewList(id,unitType);
			return orgViewList;
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IOrganizationUnitService#saveOrUpdateOrgUnit(com.vix.common.org.entity.OrganizationUnit, java.lang.String)
	 */
	@Override
	public void saveOrUpdateOrgUnit(OrganizationUnit formUnit,String boIds) throws Exception{
		OrganizationUnit entityUnit = null;
		if(formUnit!=null && StrUtils.isNotEmpty(formUnit.getId())){
			entityUnit = businessOrgDao.findEntityById(OrganizationUnit.class, formUnit.getId());
		}
		if(entityUnit==null){
			entityUnit = new OrganizationUnit();
		}

		entityUnit.setOrgCode(formUnit.getOrgCode());
		entityUnit.setFs(formUnit.getFs());
		entityUnit.setUnitType(formUnit.getUnitType());
		entityUnit.setBizUnitType(formUnit.getBizUnitType());
		entityUnit.setFullName(formUnit.getFullName());
		entityUnit.setDescription(formUnit.getDescription());
		entityUnit.setStartUsingDate(formUnit.getStartUsingDate());
		entityUnit.setStopUsingDate(formUnit.getStopUsingDate());
		
		entityUnit.setOrganization(formUnit.getOrganization());
		entityUnit.setParentOrganizationUnit(formUnit.getParentOrganizationUnit());
		
		if(formUnit.getManager()!=null && StrUtils.isNotEmpty(formUnit.getManager().getId())){
			entityUnit.setManager(formUnit.getManager());
		}
		entityUnit.setCompanyInnerCode(formUnit.getCompanyInnerCode());
		//entityUnit.setTenantId(SecurityUtil.getCurrentUserTenantId());
		entityUnit.setTenantId(formUnit.getTenantId());
		
		/*
		d
		Set<BusinessOrg> bussinessOrgSet = new HashSet<BusinessOrg>();
		if(StringUtils.isNotEmpty(boIds)){
			String[] boIdsArray = boIds.split("\\,");
			for(String boId:boIdsArray){
				bussinessOrgSet.add(new BusinessOrg(Long.parseLong(boId)));
			}
			
		}
		entityUnit.setBusinessOrgs(bussinessOrgSet);
		*/
		
		//businessOrgDao.saveOrUpdate(entityUnit);
		organizationDao.saveOrUpdateOriginal(entityUnit);
	}
	
   /*
    * <p>Title: findOrgAndUnitTreeList</p>
    * <p>Description: </p>
    * @param treeType
    * @param id
    * @return
    * @throws Exception
    * @see com.vix.common.org.service.IOrganizationUnitService#findOrgAndUnitTreeList(java.lang.String, java.lang.Long)
    */
    @Override
	public List<OrgUnit> findOrgAndUnitTreeList(String treeType,String id) throws Exception {
        List<OrgUnit> resList = new LinkedList<OrgUnit>();
        if(treeType.equals("C")){
            //查找子公司和子部门信息
            List<Organization> compList = organizationService.findSubOrganizationList(id);
            List<OrganizationUnit> orgUnitList = findSubOrganizationUnitListByOrgId(id);
            resList = makeOrgAndUnitTree(compList, orgUnitList);
            
        }else if(treeType.equals("O")){
            List<OrganizationUnit> orgUnitList = findSubOrganizationUnitList(id);
            resList = makeOrgAndUnitTree(null, orgUnitList);
        }
        
        return resList;
    }

    /**
     * 拼装公司和部门的树结构
     */
    private List<OrgUnit> makeOrgAndUnitTree(List<Organization> compList, List<OrganizationUnit> unitList){
        List<OrgUnit> orgUnitList = new LinkedList<OrgUnit>();
        
        if(compList!=null){
            for(Organization cp:compList){
                OrgUnit orgUnit = new OrgUnit(cp.getId(),"C",cp.getOrgName());
                
                if(cp.getSubOrganizations().size()>0){
                    List<OrgUnit> subUnitList = new LinkedList<OrgUnit>();
                    for(Organization ot:cp.getSubOrganizations()){
                        subUnitList.add(new OrgUnit(ot.getId(),"C",ot.getOrgName()));
                    }
                    orgUnit.setSubOrgUnits(subUnitList);
                }
                
                if(cp.getOrganizationUnits().size()>0){
                    List<OrgUnit> subUnitList = new LinkedList<OrgUnit>();
                    for(OrganizationUnit ou:cp.getOrganizationUnits()){
                        subUnitList.add(new OrgUnit(ou.getId(),"O",ou.getFullName()));
                    }
                    orgUnit.setSubOrgUnits(subUnitList);
                }
                
                orgUnitList.add(orgUnit);
            }
        }
        
        if(unitList!=null){
            for(OrganizationUnit unit:unitList){
                OrgUnit orgUnit = new OrgUnit(unit.getId(),"O",unit.getFullName());
                
                if(unit.getSubOrganizationUnits().size()>0){
                    
                    List<OrgUnit> subUnitList = new LinkedList<OrgUnit>();
                    for(OrganizationUnit ou:unit.getSubOrganizationUnits()){
                        subUnitList.add(new OrgUnit(ou.getId(),"O",ou.getFullName()));
                    }
                    orgUnit.setSubOrgUnits(subUnitList);
                }
                
                orgUnitList.add(orgUnit);
            }
        }
        
        
        return orgUnitList;
    }
    
    
    /*
     * <p>Title: findSubOrganizationUnitListByOrgId</p>
     * <p>Description: </p>
     * @param orgId
     * @return
     * @throws Exception
     * @see com.vix.common.org.service.IOrganizationUnitService#findSubOrganizationUnitListByOrgId(java.lang.String)
     */
    @Override
	public List<OrganizationUnit> findSubOrganizationUnitListByOrgId(String orgId) throws Exception{
        Map<String,Object> params = new HashMap<String,Object>();
        //params.put("organization.id,"+SearchCondition.EQUAL, orgId);
        params.put("orgId", orgId);
        
        StringBuilder hql = organizationUnitHqlProvider.findOrgUnitList(params);
        
        HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
        
        List<OrganizationUnit> orgUnitList = organizationUnitDao.findAllByHql(hql.toString(), params);
        return orgUnitList;
    }
    
    /*
     * (非 Javadoc)
     * <p>Title: findSubOrganizationUnitList</p>
     * <p>Description: </p>
     * @param orgUnitId
     * @return
     * @throws Exception
     * @see com.vix.common.org.service.IOrganizationUnitService#findSubOrganizationUnitList(java.lang.String)
     */
    @Override
	public List<OrganizationUnit> findSubOrganizationUnitList(String orgUnitId) throws Exception{
        Map<String,Object> params = new HashMap<String,Object>();
        
        /*if(null == orgUnitId || orgUnitId == 0){
            params.put("parentOrganization.id,"+SearchCondition.IS, "NULL");
        }else{
            params.put("parentOrganizationUnit.id,"+SearchCondition.EQUAL, orgUnitId);
        }
        */
        if(StringUtils.isEmpty(orgUnitId) || orgUnitId.equals("0")){//if(null == orgUnitId || orgUnitId == 0){
            params.put("orgId", null);
        }else{
            params.put("orgUnitId", orgUnitId);
        }
        StringBuilder hql = organizationUnitHqlProvider.findOrgUnitList(params);
        HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
        List<OrganizationUnit> orgUnitList = organizationUnitDao.findAllByHql(hql.toString(), params);
        return orgUnitList;
    }

	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IOrganizationUnitService#findOrganizationUnitListByOrgId(com.vix.core.web.Pager, java.util.Map)
	 */
	@Override
	public Pager findOrganizationUnitListByOrgId(Pager pager,Map<String, Object> params) throws Exception {
		/*StringBuilder hql = organizationUnitHqlProvider.findOrgUnitList(params, null, null, null);
		Pager respager = organizationUnitDao.findPagerByHql(pager,organizationUnitHqlProvider.entityAsName(),hql.toString(), params);*/
		//Pager respager = organizationUnitDao.findPagerByHql(pager,hql.toString(), params);
		StringBuilder hql = organizationUnitHqlProvider.findOrgUnitList(params, pager);
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		Pager respager = organizationUnitDao.findPager2ByHql(pager, organizationUnitHqlProvider.entityAsName(), hql.toString(), params);
		return respager;
	}

	@Override
	public OrganizationUnit findOrganizationUnitAll(String id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id"+","+SearchCondition.EQUAL, id);
		StringBuilder hql = organizationUnitHqlProvider.findOrganizationUnitAll(params, null, null, null);
		
		params.clear();
		params.put("id", id);
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		OrganizationUnit ou = organizationUnitDao.findObjectByHql(hql.toString(), params);
		return ou;
	}
	
	 /**
     * 递归查询公司
     * @param oranizationUnitId
     * @return
     * @throws Exception
     */
    @Override
	public Organization getOrganizationByUnitId(String oranizationUnitId) throws Exception{
    	return organizationUnitDao.getTopOrganizationUnitByUnitId(oranizationUnitId);
    }
	
}
