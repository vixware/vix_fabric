package com.vix.common.securityDra.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.vix.common.meta.entity.BaseMetaData;
import com.vix.common.security.entity.DataResRowMethod;
import com.vix.common.security.entity.DataResRowMethodConfig;
import com.vix.common.securityDra.dao.IDataResRowMethodConfigDao;
import com.vix.common.securityDra.vo.row.DataResRowMethodConfigVo;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

/**
 * 元数据和hql提供着之间的配置关系
 * 
 * @author Administrator
 *
 */
@Repository("dataResRowMethodConfigDao")
public class DataResRowMethodConfigDaoImpl extends BaseHibernateDaoImpl implements IDataResRowMethodConfigDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.common.securityDra.dao.IDataResRowMethodConfigDao#
	 * findAllDataResRowMethod()
	 */
	@Override
	public List<DataResRowMethod> findAllDataResRowMethod() throws Exception {
		List<DataResRowMethod> methodList = findAllByEntityClassNoTenantId(DataResRowMethod.class);
		return methodList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.common.securityDra.dao.IDataResRowMethodConfigDao#
	 * findAllDataResRowMethodConfigVo()
	 */
	@Override
	public List<DataResRowMethodConfigVo> findAllDataResRowMethodConfigVo() throws Exception {
		List<DataResRowMethodConfigVo> voList = new ArrayList<DataResRowMethodConfigVo>();

		// List<DataResRowMethodConfig> methodConfigList =
		// findAllDataResRowMethodConfig();
		List<DataResRowMethodConfig> methodConfigList = findUserDataResRowMethodConfg();

		for (DataResRowMethodConfig dc : methodConfigList) {
			BaseMetaData dcMetaData = dc.getBaseMetaData();

			DataResRowMethodConfigVo vo = new DataResRowMethodConfigVo();
			vo.setBaseMetaData(dcMetaData);

			DataResRowMethod drMethod = dc.getDataResRowMethod();
			vo.setHqlProvider(drMethod.getHqlProvider());
			vo.setMethodList(drMethod.getMethodList());
			vo.setMetaDataName(dcMetaData.getMetaDataName());

			String methodL = drMethod.getMethodList();
			if (StringUtils.isNotEmpty(methodL)) {
				String[] methodArray = methodL.split("\\,");
				String hqlPro = drMethod.getHqlProvider();

				List<String> mdList = new ArrayList<String>();
				for (String ma : methodArray) {
					mdList.add(hqlPro + "." + ma);
				}
				vo.setMethodArrayList(mdList);
			}
			voList.add(vo);
		}
		return voList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.common.securityDra.dao.IDataResRowMethodConfigDao#
	 * findUserDataResRowMethodConfg()
	 */
	@Override
	public List<DataResRowMethodConfig> findUserDataResRowMethodConfg() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select dataResRowMethodConfig from ").append(DataResRowMethodConfig.class.getName());
		sb.append(" dataResRowMethodConfig where 1=1 ");

		List<DataResRowMethodConfig> resList = findAllByHql2(sb.toString(), new HashMap<String, Object>());
		return resList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.common.securityDra.dao.IDataResRowMethodConfigDao#
	 * findDataResRowMethodMap()
	 */
	/*
	 * private Map<String,Long> findDataResRowMethodMap()throws Exception{
	 * List<DataResRowMethod> methodList = findAllDataResRowMethod();
	 * Map<String,Long> resMap = new HashMap<String, Long>();
	 * 
	 * for(DataResRowMethod dc:methodList){ String methodL = dc.getMethodList();
	 * if(StringUtils.isNotEmpty(methodL)){ String[] methodArray =
	 * methodL.split("\\,"); String hqlPro = dc.getHqlProvider(); //
	 * if(dc.getBaseMetaData()!=null){ // Long metadataId =
	 * dc.getBaseMetaData().getId(); // // for(String ma:methodArray){ // String
	 * hqlMethodKey = hqlPro+"."+ma; // resMap.put(hqlMethodKey, metadataId); //
	 * } // } for(String ma:methodArray){ String hqlMethodKey = hqlPro+"."+ma;
	 * resMap.put(hqlMethodKey, dc.getId()); }
	 * 
	 * } }
	 * 
	 * return resMap; }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.common.securityDra.dao.IDataResRowMethodConfigDao#
	 * findRowMethodMap()
	 */
	@Override
	public BiMap<String, String> findRowMethodMap() throws Exception {
		List<DataResRowMethod> methodList = findAllDataResRowMethod();
		// Map<String,Long> resMap = new HashMap<String, Long>();
		BiMap<String, String> resMap = HashBiMap.create();

		for (DataResRowMethod dc : methodList) {
			String methodL = dc.getMethodList();
			if (StringUtils.isNotEmpty(methodL)) {
				String[] methodArray = methodL.split("\\,");
				String hqlPro = dc.getHqlProvider();
				for (String ma : methodArray) {
					String hqlMethodKey = hqlPro + "." + ma;
					// resMap.put(hqlMethodKey, dc.getId());
					resMap.put(hqlMethodKey, dc.getId());

				}

			}
		}

		return resMap;
	}

}
