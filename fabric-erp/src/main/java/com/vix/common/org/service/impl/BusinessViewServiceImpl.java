package com.vix.common.org.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.common.select.bizOrg.vo.BizOrgEmpVO;
import com.vix.common.org.dao.IBusinessViewDao;
import com.vix.common.org.entity.BusinessOrgEmpView;
import com.vix.common.org.entity.BusinessOrgView;
import com.vix.common.org.entity.BusinessView;
import com.vix.common.org.hql.BusinessViewHqlProvider;
import com.vix.common.org.service.IBusinessViewService;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.BizConstant;
import com.vix.core.exception.BizException;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

@Service("businessViewService")
@Transactional
public class BusinessViewServiceImpl  extends BaseHibernateServiceImpl implements IBusinessViewService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="businessViewDao")
	private IBusinessViewDao businessViewDao;
	
	//@Resource(name="businessViewHqlProvider")
	//private BusinessViewHqlProvider businessViewHqlProvider;

	
	/**
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public Pager findBusinessViewPager(Pager pager, Map<String, Object> params) throws Exception{
		BusinessViewHqlProvider businessViewHqlProvider = new BusinessViewHqlProvider();
		StringBuilder hql = businessViewHqlProvider.findBusinessViewList(params, pager);
		pager = businessViewDao.findPager2ByHql(pager, businessViewHqlProvider.entityAsName(), hql.toString(), params);
		return pager;
	}
	
	@Override
	public BusinessView saveOrUpdateBusinessView(BusinessView entityForm)throws Exception{
		BusinessView entity = null;
		if(entityForm!=null && StrUtils.isNotEmpty(entityForm.getId())){
			entity = businessViewDao.findEntityById(BusinessView.class, entityForm.getId());
		}
		if(entity == null){
			entity = new BusinessView();
		}
		
		Date now = new Date();
		//Long currentUserAccountId = SecurityUtil.getCurrentUserId();
		if(entity.getId()==null){
			entity.setCreateTime(now);
			entity.setCode(entityForm.getCode());
		}
		entity.setUpdateTime(now);
		
		entity.setOrganization(entityForm.getOrganization());
		
		entity.setName(entityForm.getName());
		entity.setBizViewType(entityForm.getBizViewType());
		entity.setIsMatrixManagement(entityForm.getIsMatrixManagement());
		entity.setPmCode(entityForm.getPmCode());
		entity.setMetaDataCode(entityForm.getMetaDataCode());
		
		entity.setBizViewCreateDate(entityForm.getBizViewCreateDate());
		entity.setStartTime(entityForm.getStartTime());
		entity.setEndTime(entityForm.getEndTime());
		
		businessViewDao.saveOrUpdate(entity);
		return entity;
	}
	
	@Override
	public BusinessView findBizOrgViewByCode(String bizCode) throws Exception{
        return businessViewDao.findBizOrgViewByCode(bizCode);
	}
	
	/* 
	 * @see com.vix.common.org.service.IBusinessViewService#findEmpByBizOrg(java.lang.String)
	 */ 
	@Override
	public List<BizOrgEmpVO> findEmpByBizOrg(String bizOrgIdStr,String bizOrgViewId,String tenantId){
		/**
SELECT CONCAT(vb.id,'_', emp.ID) treeId, emp.ID empId,emp.Name treeName,ob.id bizOrgId,CONCAT(obv.id,'V') bizViewIdStr,
(SELECT COUNT(sub_obd.id) FROM MDM_ORG_BUSINESSORG_DETAIL sub_obd
INNER JOIN MDM_ORG_BUSINESSORG sub_ob ON sub_ob.id = sub_obd.BusinessOrg_ID
WHERE sub_obd.BizOrgType='E' AND sub_ob.Parent_id = ob.id ) subCount
FROM HR_ORG_EMPLOYEE emp
INNER JOIN MDM_ORG_BUSINESSORG_DETAIL obd ON obd.Employee_ID = emp.ID AND obd.BizOrgType='E'
INNER JOIN MDM_ORG_BUSINESSORG ob ON ob.ID = obd.BusinessOrg_ID
INNER JOIN MDM_ORG_BUSINESSVIEW obv ON obv.ID = ob.BusinessView_ID
--  inner join V_MDM_ORG_BIZORGVIEW vb on vb.realId = obd.BusinessOrg_ID and vb.viewType = 'O'
INNER JOIN V_MDM_ORG_BIZORGVIEW vb ON vb.realId =ob.ID AND vb.viewType = 'O'
WHERE vb.id='2O' AND  vb.tenantId = '123' 
AND IFNULL(obv.StartTime,NOW()) <= NOW() AND IFNULL(obv.EndTime,NOW())>=NOW()
ORDER BY emp.ID DESC
		 */
		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("SELECT CONCAT(vb.id,'_', emp.ID) treeId, emp.ID empId,emp.Name treeName,ob.id bizOrgId,CONCAT(obv.id,'V') bizViewIdStr, ");
		sql.append("(SELECT COUNT(sub_obd.id) FROM MDM_ORG_BUSINESSORG_DETAIL sub_obd INNER JOIN MDM_ORG_BUSINESSORG sub_ob ON sub_ob.id = sub_obd.BusinessOrg_ID ");
		sql.append("WHERE sub_obd.BizOrgType=? AND sub_ob.Parent_id = ob.id ) subCount ");
		params.add(BizConstant.COMMON_ORG_E);
		sql.append(" FROM HR_ORG_EMPLOYEE emp INNER JOIN MDM_ORG_BUSINESSORG_DETAIL obd ON obd.Employee_ID = emp.ID AND obd.BizOrgType= ? ");
		params.add(BizConstant.COMMON_ORG_E);
		sql.append(" INNER JOIN MDM_ORG_BUSINESSORG ob ON ob.ID = obd.BusinessOrg_ID ");
		sql.append(" INNER JOIN MDM_ORG_BUSINESSVIEW obv ON obv.ID = ob.BusinessView_ID ");
		sql.append(" INNER JOIN V_MDM_ORG_BIZORGVIEW vb ON vb.realId =ob.ID AND vb.viewType = ? ");
		params.add(BizConstant.COMMON_ORG_O);
		sql.append(" WHERE 1=1 ");
		if(StringUtils.isNotEmpty(bizOrgIdStr)){
			sql.append(" and vb.v_parent_id=? ");
			params.add(bizOrgIdStr);
		}else{
			sql.append(" and vb.v_parent_id= ?  ");
			params.add(bizOrgViewId);
		}
		sql.append(" and vb.tenantId = ? ");
		params.add(tenantId);
		
		sql.append(" AND IFNULL(obv.StartTime,NOW()) <= NOW() AND IFNULL(obv.EndTime,NOW())>=NOW() ");
		sql.append(" ORDER BY emp.ID DESC ");
		List<BizOrgEmpVO> empList = businessViewDao.queryObjectListBySql(sql.toString(), BizOrgEmpVO.class, params.toArray());
    	return empList;
	}
	
	//查询直接上级
	@Override
	public List<BizOrgEmpVO> findLeaderByEmpId(String empId) throws Exception{
		return businessViewDao.findLeaderByEmpId(empId);
	}
	
	//查询直接下属
	@Override
	public List<BizOrgEmpVO> findSubByEmpId(String empId )throws Exception{
		return businessViewDao.findSubByEmpId(empId);
	}
	
	//查询所有下属
	@Override
	public List<BizOrgEmpVO> findAllSubByEmpId(String empId )throws Exception{
		return businessViewDao.findAllSubByEmpId(empId);
	}
	
	//查询承租户的默认业务视图
	@Override
	public BusinessView findDefaultBizViewByEmpId(String empId) throws Exception{
		return businessViewDao.findDefaultBizViewByEmpId(empId);
	}
	
	@Override
	public BusinessView findDefaultBizViewByTenantId(String tenantId) throws Exception{
		return businessViewDao.findDefaultBizViewByTenantId(tenantId);
	}
	
	@Override
	public List<BusinessOrgView> findOrgViewList(String id) throws Exception{
		BusinessViewHqlProvider businessViewHqlProvider = new BusinessViewHqlProvider();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("parentId", id);
		StringBuilder hql = businessViewHqlProvider.findBusinessOrgViewList(params);
		List<BusinessOrgView> bizOrgViewList =  businessViewDao.findAllByHql2(hql.toString(), params);
		return bizOrgViewList;
	}
	
	@Override
	public void saveOrUpdateBizViewHasDefaultRelation() throws Exception{
		String tenantId = SecurityUtil.getCurrentUserTenantId();
		if(StringUtils.isEmpty(tenantId)){
			throw new BizException("不存在承租户编码！");
		}
		String bvCode = BizConstant.COMMON_DEFAULT_FLAG+tenantId;
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("tenantId", tenantId);
		params.put("bvLikeCode", bvCode);
		params.put("bvCode", bvCode);
		
		executeSql("call vix_pro_sys_bizorg(:tenantId, :bvLikeCode, :bvCode);", params);
	}

	@Override
	public List<BusinessOrgEmpView> findBusinessOrgEmpViewList(String id) throws Exception {
		BusinessViewHqlProvider businessViewHqlProvider = new BusinessViewHqlProvider();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("parentId", id);
		StringBuilder hql = businessViewHqlProvider.findBusinessOrgEmpViewList(params);
		List<BusinessOrgEmpView> bizOrgViewList =  businessViewDao.findAllByHql2(hql.toString(), params);
		return bizOrgViewList;
	}
}
