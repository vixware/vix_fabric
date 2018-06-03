package com.vix.common.securityDra.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.BiMap;
import com.vix.common.meta.entity.BaseMetaData;
import com.vix.common.security.entity.DataResRowMethod;
import com.vix.common.security.entity.DataResRowMethodConfig;
import com.vix.common.securityDra.dao.IDataResRowMethodConfigDao;
import com.vix.common.securityDra.service.IDataResRowMethodConfigService;
import com.vix.core.exception.BizException;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;

/**
 * 行集权限的元数据和拦截方法配置业务层
 * @author Administrator
 *
 */
@Service("dataResRowMethodConfigService")
@Transactional
public class DataResRowMethodConfigServiceImpl extends BaseHibernateServiceImpl	implements IDataResRowMethodConfigService {

	@Resource(name="dataResRowMethodConfigDao")
	private IDataResRowMethodConfigDao dataResRowMethodConfigDao;
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowMethodConfigService#saveOrUpdate(java.lang.String, com.vix.common.security.entity.DataResRowMethodConfig)
	 */
	@Override
	public DataResRowMethodConfig saveOrUpdate(DataResRowMethodConfig methodConfig) throws Exception{
		DataResRowMethodConfig methodConfigold = null;
		if(methodConfig!=null && StrUtils.isNotEmpty(methodConfig.getId())){
			methodConfigold = dataResRowMethodConfigDao.findEntityById(DataResRowMethodConfig.class, methodConfig.getId());
		}else{
			methodConfigold = new DataResRowMethodConfig();
		}
		BeanUtils.copyProperties(methodConfig, methodConfigold, new String[]{"id"});

		//BaseMetaData metaData = methodConfigold.getBaseMetaData();
		/*if(StringUtils.isNotEmpty(addMetaDataIds)){
			//String mid = addMetaDataIds.split("\\,")[1];
			metaData = dataResRowMethodConfigDao.findEntityById(BaseMetaData.class, new Long(addMetaDataIds));
		}
		
		methodConfigold.setBaseMetaData(metaData);*/
		
		//验证是否唯一
		BaseMetaData bd = methodConfigold.getBaseMetaData();
		if( bd==null || bd.getId()==null){
			throw new BizException("元数据没有选择！");
		}
		
		DataResRowMethod dd = methodConfigold.getDataResRowMethod();
		if( dd==null || dd.getId()==null){
			throw new BizException("拦截方法配置没有选择！");
		}
		
		String ename = "dataResRowMethodConfig";
		StringBuilder hql = new StringBuilder();
		Map<String,Object> param = new HashMap<String,Object>();
		
		hql.append("select count(").append(ename).append(".id) from ").append(DataResRowMethodConfig.class.getName());
		hql.append(" ").append(ename).append(" where 1=1 ");
		if(StringUtils.isNotEmpty(methodConfigold.getId()) && !methodConfigold.getId().equals("0")){//if(methodConfigold.getId()!=null && methodConfigold.getId()>0){
			hql.append(" and ");
			hql.append(ename).append(".id != :id ");
			param.put("id", methodConfigold.getId());
		}
		
		hql.append(" and (").append(ename).append(".baseMetaData.id = :baseMetaDataId ");
		param.put("baseMetaDataId", bd.getId());
		
		hql.append(" or ").append(ename).append(".dataResRowMethod.id = :dataResRowMethodId ) and ");
		param.put("dataResRowMethodId", dd.getId());
		
		//.append(ename).append(".baseMetaData.id");
		Long l = dataResRowMethodConfigDao.findDataCountByHql(hql, ename, param);
		if(l>0){
			throw new BizException("拦截方法和元数据不能重复！");
		}
		dataResRowMethodConfigDao.merge(methodConfigold);
		return methodConfigold;
	}

	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowMethodConfigService#findDataResRowMethodMap()
	 */
	@Override
	public BiMap<String,String> findDataResRowMethodMap()throws Exception{
		//Map<String,String> hqlMetadaMap = dataResRowMethodConfigDao.findDataResRowMethodMap();
		BiMap<String,String> hqlMetadaMap = dataResRowMethodConfigDao.findRowMethodMap();
		return hqlMetadaMap;
	}

}
