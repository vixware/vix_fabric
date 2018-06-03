package com.vix.common.securityDra.service.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.security.entity.DataResRowOwner;
import com.vix.common.security.entity.Role;
import com.vix.common.securityDra.dao.IDataResRowOwnerDao;
import com.vix.common.securityDra.service.IDataResRowOwnerService;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

/**
 * 数据权限主体的业务类
 * @author Administrator
 *
 */
@Service("dataResRowOwnerService")
@Transactional
public class DataResRowOwnerServiceImpl extends BaseHibernateServiceImpl implements IDataResRowOwnerService {

	@Resource(name="dataResRowOwnerDao")
	private IDataResRowOwnerDao dataResRowOwnerDao;
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowOwnerService#findDataResRowOwnerPager(com.vix.core.web.Pager, java.util.Map)
	 */
	@Override
	public Pager findDataResRowOwnerPager(Pager pager, Map<String, Object> params) throws Exception{
		StringBuilder sb = new StringBuilder();
		String ename ="dataResRowOwner";
		sb.append("select ").append(ename);
		sb.append(" from ").append(DataResRowOwner.class.getName()).append(" ").append(ename);
		sb.append(" where 1=1 ");
   
		if(params!=null){
			if(params.containsKey("ownerName")){
				sb.append("  and  ").append(ename).append(".ownerName like :ownerName ");
			}
		}
		pager = dataResRowOwnerDao.findPager2ByHql(pager, ename, sb.toString(), params);
		return pager;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowOwnerService#saveOrUpdate(java.lang.String, java.lang.String, com.vix.common.security.entity.DataResRowOwner)
	 */
	@Override
	public DataResRowOwner saveOrUpdate(String addRoleIds,String delRoleIds,DataResRowOwner resRowOwner)throws Exception{
		DataResRowOwner uaTmp = null;
		if(StrUtils.isNotEmpty(resRowOwner.getId())){
			uaTmp = dataResRowOwnerDao.findEntityById(DataResRowOwner.class, resRowOwner.getId());
		}
		if(uaTmp == null){
			uaTmp = new DataResRowOwner();
		}
		
		BeanUtils.copyProperties(resRowOwner, uaTmp, new String[]{});
		
		Set<Role> rSet=uaTmp.getRoles();
		if( rSet== null){
			rSet= new HashSet<Role>();
		}
		//userAccountDao.evict(uaTmp);
		
		if(StringUtils.isNotEmpty(addRoleIds)){
			for(String idS : addRoleIds.split(",")){
				if(null != idS && !"".equals(idS)){
					Role r = dataResRowOwnerDao.findEntityById(Role.class, idS);
					rSet.add(r);
				}
			}
		}
		if(StringUtils.isNotEmpty(delRoleIds)){
			for(String idS : delRoleIds.split(",")){
				if(null != idS && !"".equals(idS)){
					Role r = dataResRowOwnerDao.findEntityById(Role.class, idS);
					rSet.remove(r);
				}
			}
		}
		
		uaTmp.setRoles(rSet);
		dataResRowOwnerDao.merge(uaTmp);
		return uaTmp;
	}
}
