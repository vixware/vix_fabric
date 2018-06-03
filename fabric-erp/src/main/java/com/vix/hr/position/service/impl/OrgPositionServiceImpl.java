package com.vix.hr.position.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.dao.IOrganizationDao;
import com.vix.common.org.entity.OrgJob;
import com.vix.common.org.entity.OrgPosition;
import com.vix.common.org.entity.OrgView;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.hr.position.dao.IOrgPositionDao;
import com.vix.hr.position.hql.OrgPositionHqlProvider;
import com.vix.hr.position.service.IOrgPositionService;

@Service("orgPositionService")
@Transactional
public class OrgPositionServiceImpl extends BaseHibernateServiceImpl implements	IOrgPositionService {
	
	@Resource(name="orgPositionDao")
	private IOrgPositionDao orgPositionDao;
	
	@Resource(name="organizationDao")
	private IOrganizationDao organizationDao;
	
	@Resource(name="orgPositionHqlProvider")
	private OrgPositionHqlProvider orgPositionHqlProvider;
	
	@Override
	public Pager findOrgPositionPager(Pager pager,String orgUnitId,String posName) throws Exception{
		//StringBuilder hql = employeeHrHqlProvider.findBaseMetaData(params, pager.getOrderField(), pager.getOrderBy());
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("orgUnitId", orgUnitId);
//		params.put("posName," + SearchCondition.NOEQUAL, "null");
		
		if(StringUtils.isNotEmpty(posName)){
			params.put("posName", "%"+posName+"%");
		}
		
		StringBuilder hql = orgPositionHqlProvider.findOrgPosition(params,pager);
        pager = orgPositionDao.findPager2ByHql(pager,orgPositionHqlProvider.entityAsName(),hql.toString(), params);
        
        
        //List<OrgPosition> pp = orgPositionDao.findAllByHql2("select op from OrgPosition op where op.subOrgPositions.id = 4 ", null);
        return pager;
	}
	
	@Override
	public OrgPosition findOrgPositionById(String id) throws Exception{
		OrgPosition op = orgPositionDao.findEntityById(OrgPosition.class, id);
		op.getOrgJob();
		//op.getOrganizationUnit();
		return op;
	}
	
	@Override
	public List<OrgJob> findOrgJobListByCompId(String orgUnitId) throws Exception{
		List<OrgJob> orgJob = orgPositionDao.findAllByEntityClass(OrgJob.class);
		return orgJob;
	}
	
	
	@Override
	public OrgPosition saveOrUpdateOrgPosition(OrgPosition orgPositionForm) throws Exception{
		OrgPosition orgPosition = null;
		if (orgPositionForm != null && StrUtils.isNotEmpty(orgPositionForm.getId())
				&& !orgPositionForm.getId().equals("0")) {
			orgPosition = findEntityById(OrgPosition.class, orgPositionForm.getId());
		}
		Date now = new Date();
		if(orgPosition==null){
			orgPosition = new OrgPosition();
			orgPosition.setCreateTime(now);
		}
		
		OrgPosition parentOrgPosition = orgPositionForm.getParentOrgPosition();
		if(parentOrgPosition==null || StrUtils.isEmpty(parentOrgPosition.getId())){
			orgPosition.setParentOrgPosition(null);
			
			OrganizationUnit parentOu = orgPositionDao.findEntityById(OrganizationUnit.class, orgPositionForm.getOrganizationUnit().getId());
			orgPosition.setOrganizationUnit(parentOu);
			
			String companInnerCode = parentOu.getCompanyInnerCode();
			String tenantId = parentOu.getTenantId();
			
			OrgView ov = organizationDao.findOrgByInnerCode(companInnerCode);
			orgPosition.setOrganization(new Organization(ov.getRealId()));
			
			orgPosition.setTenantId(tenantId);
			orgPosition.setCompanyInnerCode(companInnerCode);
		}else{
			OrgPosition parentOp = orgPositionDao.findEntityById(OrgPosition.class, orgPositionForm.getParentOrgPosition().getId());
			
			if(parentOp!=null){
				orgPosition.setOrganizationUnit(parentOp.getOrganizationUnit());
				orgPosition.setParentOrgPosition(parentOp);
				String companInnerCode = parentOp.getCompanyInnerCode();
				String tenantId = parentOp.getTenantId();
				OrgView ov = organizationDao.findOrgByInnerCode(companInnerCode);
				orgPosition.setOrganization(new Organization(ov.getRealId()));
				
				orgPosition.setTenantId(tenantId);
				orgPosition.setCompanyInnerCode(companInnerCode);
			}
			
			
		}
		
		
		//orgPosition.setTenantId(SecurityUtil.getCurrentUserTenantId());
		//OrgPosition newPos = orgPositionDao.merge(orgPosition);
		orgPosition.setUpdateTime(now);
		orgPosition.setCode(orgPositionForm.getCode());
		orgPosition.setPosName(orgPositionForm.getPosName());
		orgPosition.setPostNumber(orgPositionForm.getPostNumber());
		orgPosition.setJobNature(orgPositionForm.getJobNature());
		orgPosition.setPersonAmount(orgPositionForm.getPersonAmount());
		
		orgPosition.setOrgJob(orgPositionForm.getOrgJob());
		
		orgPosition.setPostSalaryScale(orgPositionForm.getPostSalaryScale());
		orgPosition.setStandardPayPoint(orgPositionForm.getStandardPayPoint());
		
		orgPosition.setStandardPointValue(orgPositionForm.getStandardPointValue());
		orgPosition.setPostPayScale(orgPositionForm.getPostPayScale());
		orgPosition.setToxicAndHazardousTypes(orgPositionForm.getToxicAndHazardousTypes());
		orgPosition.setPaymentStandards(orgPositionForm.getPaymentStandards());
		orgPosition.setToxicAndHarmfulToDamageType(orgPositionForm.getToxicAndHarmfulToDamageType());
		orgPosition.setRanksOfCadres(orgPositionForm.getRanksOfCadres());
		orgPosition.setWhetherParticularTypesOfWorkStatus(orgPositionForm.getWhetherParticularTypesOfWorkStatus());
		orgPosition.setArePoisonedJobs(orgPositionForm.getArePoisonedJobs());
		orgPosition.setWhetherPointInspectionPosts(orgPositionForm.getWhetherPointInspectionPosts());
		orgPosition.setWhetherFictitiousJobs(orgPositionForm.getWhetherFictitiousJobs());
		orgPosition.setWhetherToRunTheJob(orgPositionForm.getWhetherToRunTheJob());
		orgPosition.setWhetherCapacityPosts(orgPositionForm.getWhetherCapacityPosts());
		orgPosition.setWhetherMaintenanceJobs(orgPositionForm.getWhetherMaintenanceJobs());
		orgPosition.setWhetherTheTeamLeader(orgPositionForm.getWhetherTheTeamLeader());
		orgPosition.setIsChargeDep(orgPositionForm.getIsChargeDep());
		orgPosition.setCapacityCoding(orgPositionForm.getCapacityCoding());
		orgPosition.setMemo(orgPositionForm.getMemo());
		
		OrgPosition newPos = orgPositionDao.mergeOriginal(orgPosition);
		return newPos;
	}
	
	@Override
	public List<OrgPosition> findOrgPositionListByOrgId(String orgId,String posParentId) throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("orgId", orgId);
		params.put("parentId", posParentId);
		StringBuilder hql = orgPositionHqlProvider.findOrgPositionByOrgId(params);
		List<OrgPosition> orgPosList = orgPositionDao.findAllByHql2(hql.toString(), params);
		return orgPosList;
	}
	
	@Override
	public void deleteOrgPosition(String id) throws Exception{
		orgPositionDao.deleteById(OrgPosition.class, id);
	}
	
	/**
	 * 根据员工查询 员工的岗位列表
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<OrgPosition> findOrgPositionListByEmpId(String empId) throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("empId", empId);
		StringBuilder hql = orgPositionHqlProvider.findOrgPositionByEmpId(params);
		List<OrgPosition> orgPosList = orgPositionDao.findAllByHql2(hql.toString(), params);
		orgPositionDao.clear();
		return orgPosList;
	}
	
	/**
	 * 根据部门id得到岗位列表
	
	public List<OrgPosition> findOrgPositionListByOrgUnitId(String orgUnitId) throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("orgUnitId", orgUnitId);
		StringBuilder hql = orgPositionHqlProvider.findOrgPosition(params);
		
	} */
}
