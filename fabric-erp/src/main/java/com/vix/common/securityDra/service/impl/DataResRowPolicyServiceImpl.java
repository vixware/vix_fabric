package com.vix.common.securityDra.service.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.security.entity.DataResRowPolicy;
import com.vix.common.security.entity.DataResRowPolicyObj;
import com.vix.common.securityDra.dao.IDataResRowPolicyDao;
import com.vix.common.securityDra.service.IDataResRowPolicyService;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

/**
 * 数据权限策略的业务类
 * @author Administrator
 *
 */
@Service("dataResRowPolicyService")
@Transactional
public class DataResRowPolicyServiceImpl extends BaseHibernateServiceImpl implements IDataResRowPolicyService {

	@Resource(name="dataResRowPolicyDao")
	private IDataResRowPolicyDao dataResRowPolicyDao;
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowPolicyService#findDataResRowPolicyPager(com.vix.core.web.Pager, java.util.Map)
	 */
	@Override
	public Pager findDataResRowPolicyPager(Pager pager, Map<String, Object> params) throws Exception{
		
		StringBuilder sb = new StringBuilder();
		String ename ="dataResRowPolicy";
		sb.append("select ").append(ename);
		sb.append(" from ").append(DataResRowPolicy.class.getName()).append(" ").append(ename);
		sb.append(" where 1=1 ");
   
		if(params!=null){
			if(params.containsKey("policyName")){
				sb.append("  and  ").append(ename).append(".policyName like :policyName ");
			}
		}
		pager = dataResRowPolicyDao.findPager2ByHql(pager, ename, sb.toString(), params);
		return pager;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowPolicyService#saveOrUpdate(java.lang.String, java.lang.String, com.vix.common.security.entity.DataResRowPolicy)
	 */
	@Override
	public DataResRowPolicy saveOrUpdate(String addPlyObjIds, String deletePlyObjIds,DataResRowPolicy plyObj) throws Exception{
		DataResRowPolicy uaTmp = null;
		
		if(StrUtils.isNotEmpty(plyObj.getId())){
			uaTmp = dataResRowPolicyDao.findEntityById(DataResRowPolicy.class, plyObj.getId());
		}
		if(uaTmp == null){
			uaTmp = new DataResRowPolicy();
		}
		
		BeanUtils.copyProperties(plyObj, uaTmp, new String[]{"dataResRowOwners","dataResRowPolicyObjs"});
		
		Set<DataResRowPolicyObj> rSet=uaTmp.getDataResRowPolicyObjs();
		if( rSet== null){
			rSet= new HashSet<DataResRowPolicyObj>();
		}
		
		//Set<DataResRowPolicyObj> rSet = new HashSet<DataResRowPolicyObj>();
		if(StringUtils.isNotEmpty(addPlyObjIds)){
			for(String idS : addPlyObjIds.split(",")){
				if(null != idS && !"".equals(idS)){
					DataResRowPolicyObj r = dataResRowPolicyDao.findEntityById(DataResRowPolicyObj.class,idS);
					rSet.add(r);
				}
			}
		}
		if(StringUtils.isNotEmpty(deletePlyObjIds)){
			for(String idS : deletePlyObjIds.split(",")){
				if(null != idS && !"".equals(idS)){
					DataResRowPolicyObj r = dataResRowPolicyDao.findEntityById(DataResRowPolicyObj.class, idS);
					rSet.remove(r);
				}
			}
		}
		
		plyObj.setDataResRowPolicyObjs(rSet);
		dataResRowPolicyDao.merge(plyObj);
		return plyObj;
	}
}
