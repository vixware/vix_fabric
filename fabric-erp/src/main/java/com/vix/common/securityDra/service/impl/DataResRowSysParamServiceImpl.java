package com.vix.common.securityDra.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.security.entity.DataResRowPredata;
import com.vix.common.security.entity.DataResRowSystemParams;
import com.vix.common.security.entity.Role;
import com.vix.common.securityDra.dao.IDataResRowSysParamDao;
import com.vix.common.securityDra.service.IDataResRowSysParamService;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;

/**
 * 数据权限预处理配置
 * @author Administrator
 *
 */
@Service("dataResRowSysParamService")
@Transactional
public class DataResRowSysParamServiceImpl extends BaseHibernateServiceImpl implements IDataResRowSysParamService {

	@Resource(name="dataResRowSysParamDao")
	private IDataResRowSysParamDao dataResRowSysParamDao;
	
	
	@Override
	public DataResRowSystemParams saveOrUpdateParam(DataResRowSystemParams sysParam)throws Exception{
		if(sysParam.getBaseMetaData()!=null && sysParam.getBaseMetaData().getId()==null){
			sysParam.setBaseMetaData(null);
		}
		DataResRowSystemParams resObj = dataResRowSysParamDao.merge(sysParam);
		return resObj;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowSysParamService#findSysParamRoleList(java.lang.String)
	 */
	@Override
	public List<Role> findSysParamRoleList(String id)throws Exception{
		StringBuilder hql = new StringBuilder();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		//hql.append("select rowParam from DataResRowSystemParams rowParam inner join rowParam.roles role where obj.id=:id ");
		//DataResRowSystemParams
		hql.append("select role from Role role inner join role.dataResRowSystemParams rowSysParam where rowSysParam.id =:id ");
		List<Role> objList = dataResRowSysParamDao.findAllByHql2(hql.toString(), params);
		return objList;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowSysParamService#findSysParamPreDataList(java.lang.String)
	 */
	@Override
	public List<DataResRowPredata> findSysParamPreDataList(String id)throws Exception{
		StringBuilder hql = new StringBuilder();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		//hql.append("select rowParam from DataResRowSystemParams rowParam inner join rowParam.roles role where obj.id=:id ");
		//DataResRowSystemParams
		hql.append("select preData from DataResRowPredata preData where preData.dataResRowSystemParams.id =:id ");
		List<DataResRowPredata> objList = dataResRowSysParamDao.findAllByHql2(hql.toString(), params);
		return objList;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowSysParamService#saveOrUpdateParamRole(java.lang.String, java.lang.String)
	 */
	@Override
	public void saveOrUpdateParamRole(String sysParamId,String roleId)throws Exception{
		DataResRowSystemParams paramDp = dataResRowSysParamDao.findEntityById(DataResRowSystemParams.class, sysParamId);
		Set<Role> roles = paramDp.getRoles();
		if(roles==null){
			roles = new HashSet<Role>();
		}
		
		if(StringUtils.isNotEmpty(roleId)){
			for(String rid : roleId.split(",")){
				if(StringUtils.isNotEmpty(rid)){
					Role r = dataResRowSysParamDao.findEntityById(Role.class, rid);
					roles.add(r);
				}
			}
		}
		paramDp.setRoles(roles);
		dataResRowSysParamDao.update(paramDp);
	}
	
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowSysParamService#deleteRoleForParam(java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteRoleForParam(String sysParamId,String roleId)throws Exception{
		DataResRowSystemParams paramDp = dataResRowSysParamDao.findEntityById(DataResRowSystemParams.class, sysParamId);
		Set<Role> roles = paramDp.getRoles();
		if(roles==null){
			roles = new HashSet<Role>();
		}
		
		if(StringUtils.isNotEmpty(roleId)){
			for(String rid : roleId.split(",")){
				if(StringUtils.isNotEmpty(rid)){
					Role r = dataResRowSysParamDao.findEntityById(Role.class, rid);
					roles.remove(r);
				}
			}
		}
		paramDp.setRoles(roles);
		dataResRowSysParamDao.update(paramDp);
	}
	
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowSysParamService#saveOrUpdateParamPredata(com.vix.common.security.entity.DataResRowPredata)
	 */
	/*public DataResRowPredata saveOrUpdateParamPredata(DataResRowPredata predata)throws Exception{
		//dataResRowSysParamDao.saveOrUpdate(predata);
		DataResRowPredata dp = null;
		Long id = predata.getId();
		if(id!=null){
			dp = dataResRowSysParamDao.findEntityById(DataResRowPredata.class, id);
		}else{
			dp = new DataResRowPredata();
		}
		dp.setDataResRowSystemParams(predata.getDataResRowSystemParams());
		dp.setPriority(predata.getPriority());
		dp.setPreData(predata.getPreData());
		
		dataResRowSysParamDao.saveOrUpdate(dp);
		return predata;
	}*/
}
