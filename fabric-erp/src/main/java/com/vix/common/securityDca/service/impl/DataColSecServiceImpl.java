package com.vix.common.securityDca.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.meta.entity.BaseMetaData;
import com.vix.common.meta.entity.BaseMetaDataDefine;
import com.vix.common.meta.entity.BaseMetaDataExtend;
import com.vix.common.security.entity.DataResColConfig;
import com.vix.common.security.entity.DataResColPolicy;
import com.vix.common.securityDca.dao.IDataColDao;
import com.vix.common.securityDca.hql.DataColSecHqlProvider;
import com.vix.common.securityDca.service.IDataColSecService;
import com.vix.common.securityDca.vo.MetaDataProperty;
import com.vix.common.securityDca.vo.UserAccountColConfigInfo;
import com.vix.core.constant.DataColConstant;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.web.Pager;

@Service("dataColSecService")
@Transactional
public class DataColSecServiceImpl extends BaseHibernateServiceImpl implements IDataColSecService {

	@Resource(name="dataColDao")
	private IDataColDao dataColDao;

	@Resource(name="dataColSecHqlProvider")
	private DataColSecHqlProvider dataColSecHqlProvider;
	
	@Override
	public Pager findMetaDataPagerByHqlConditions(Pager pager,Map<String, Object> params) throws Exception {
        StringBuilder hql = dataColSecHqlProvider.findBaseMetaData(params, pager.getOrderField(), pager.getOrderBy());
        pager = dataColDao.findPager2ByHql(pager, "baseMetaData", hql.toString(), params);
		/*StringBuilder sql = new StringBuilder();
		List<Object> sqlParams = new LinkedList<Object>();
		sql.append("");*/
		return pager;
	}
	
	@Override
	public BaseMetaData findMetaDataDefine(String mddId)throws Exception{
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("id", mddId);
		List<BaseMetaData> mdbList  =  dataColDao.findAllByHql("select mdb from BaseMetaData mdb left join fetch mdb.baseMetaDataDefines left join fetch mdb.baseMetaDataExtends where mdb.id=:id ", param);
		if(mdbList!=null && mdbList.size()>0){
			return mdbList.get(0);
		}
		return null;
	}
	
	@Override
	public List<DataResColPolicy> findDataColPolicyList(String colConfigId,String baseMetaDataId)throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("configId", colConfigId);
		params.put("baseMetaDataId", baseMetaDataId);
		StringBuffer hql = new StringBuffer();
		hql.append("select ply from DataResColPolicy ply left join fetch ply.baseMetaDataDefine left join fetch ply.baseMetaDataExtend where ply.dataResColConfig.id=:configId  and ply.baseMetaData.id=:baseMetaDataId ");
		List<DataResColPolicy> resList = dataColDao.findAllByHql(hql.toString(), params);
		return resList;
	}
	
	@Override
	public void saveColPly(String metaDataObjId,String rangeId,List<MetaDataProperty> dataList)throws Exception{
		if(dataList==null || dataList.size()==0){
			return;
		}
		
		//元数据对象id
		BaseMetaData metaData = new BaseMetaData();metaData.setId(metaDataObjId);
		//列级权限配置id
		DataResColConfig drc = new DataResColConfig();drc.setId(rangeId);
		
		/*List<BaseMetaDataDefine> defineAddList = new LinkedList<BaseMetaDataDefine>();
		List<BaseMetaDataDefine> defineUpdateList = new LinkedList<BaseMetaDataDefine>();
		
		List<BaseMetaDataExtend> extAddList = new LinkedList<BaseMetaDataExtend>();
		List<BaseMetaDataExtend> extUpdateList = new LinkedList<BaseMetaDataExtend>();
		
		List<Long> defineUppIdList = new LinkedList<Long>();
		List<Long> extUpdateIdList = new LinkedList<Long>();*/
		
		List<DataResColPolicy> drcpAddList = new LinkedList<DataResColPolicy>();
		List<DataResColPolicy> drcpUpdateList = new LinkedList<DataResColPolicy>();
		
		for(MetaDataProperty mp:dataList){
			//配置权限的id DataResColPolicy的id  如果此id不为空 则是新建 否则进行更新操作
			String id = mp.getId();
			//元数据定义属性id  可能是基本属性  也可能是扩展属性
			String mdpId = mp.getMdpId();
			
			BaseMetaDataDefine mdd = new BaseMetaDataDefine();
			BaseMetaDataExtend mde = new BaseMetaDataExtend();
			
			DataResColPolicy drcp = null;
			if(mp.getType().endsWith("D")){
				//主属性
				mdd.setId(mdpId);
				if(id==null){
					//需要新建DataResColPolicy
					drcp = new DataResColPolicy();
					drcp.setDataResColConfig(drc);
					drcp.setBaseMetaData(metaData);
					drcp.setBaseMetaDataDefine(mdd);
					
					drcp.setViewListStatus(mp.getViewListStatus());
					drcp.setViewDetailStatus(mp.getViewDetailStatus());
					
					//drcp.setBaseMetaDataExtend(baseMetaDataExtend);
					//drcp.setBaseMetaDataDefine(baseMetaDataDefine);
					drcpAddList.add(drcp);
				}else{
					drcp = dataColDao.findEntityById(DataResColPolicy.class, id);
					drcp.setViewListStatus(mp.getViewListStatus());
					drcp.setViewDetailStatus(mp.getViewDetailStatus());
					drcpUpdateList.add(drcp);
				}
			}else if(mp.getType().endsWith("E")){
				//扩展属性
				mde.setId(mdpId);
				if(id==null){
					//需要新建DataResColPolicy
					drcp = new DataResColPolicy();
					drcp.setDataResColConfig(drc);
					drcp.setBaseMetaData(metaData);
					drcp.setBaseMetaDataExtend(mde);
					
					drcp.setViewListStatus(mp.getViewListStatus());
					drcp.setViewDetailStatus(mp.getViewDetailStatus());
					
					drcpAddList.add(drcp);
				}else{
					drcp = dataColDao.findEntityById(DataResColPolicy.class, id);
					drcp.setViewListStatus(mp.getViewListStatus());
					drcp.setViewDetailStatus(mp.getViewDetailStatus());
					drcpUpdateList.add(drcp);
					
				}
			}
		}
		
		if(drcpAddList.size()>0){
			dataColDao.batchSave(drcpAddList);
		}
		if(drcpUpdateList.size()>0){
			dataColDao.batchUpdateByList(drcpUpdateList);
		}
		
	}
	
	
	@Override
	public Pager findColConfigPagerByRoleId(Pager pager,Map<String,Object> params) throws Exception{
		//StringBuilder hql = dataColSecHqlProvider.findBaseMetaData(params, pager.getOrderField(), pager.getOrderBy());
        //pager = dataColDao.findPager2ByHql(pager, "baseMetaData", hql.toString(), params);
		StringBuilder hql = new StringBuilder();
		Map<String,Object> hqlParams = new HashMap<String, Object>();
		hql.append("select colConfig from ").append(DataResColConfig.class.getName()).append(" colConfig ");
		hql.append("inner join colConfig.roles role ");
		hql.append("where role.id = :roleId ");
		hqlParams.put("roleId",params.get("roleId"));
		if(params!=null && params.containsKey("colConfigName")){
			hql.append(" and colConfig.configName like :colConfigName ");
			hqlParams.put("roleId",params.get("colConfigName"));
		}
		
		HqlTenantIdUtil.handleParamMap4TenantId(hqlParams);//不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(hqlParams);//不处理CompanyInnerCode;
		
		pager = dataColDao.findPager2ByHql(pager, "colConfig", hql.toString(), hqlParams);
		return pager;
	}
	
	
	/* 
	 * @see com.vix.common.securityDca.service.IDataColSecService#findColMetaInfoByUserAccount(java.lang.String)
	 */ 
	@Override
	public Map<String,Map<String,Set<String>>> findColMetaInfoByUserAccount(String userAccountId){
		/**
		 key  元数据全称
	 	value    key  列表：List   详情 :Detail        编辑 :Edit 暂不用
         		 value  元数据属性的Set
		 */
		Map<String,Map<String,Set<String>>> resMap = new ConcurrentHashMap<String, Map<String,Set<String>>>();
		
		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("SELECT dp.ID,dp.ViewListStatus,dp.ViewDetailStatus,cmet.MetaDataName,cmetDef.property ");
		sql.append("FROM CMN_SEC_DATARESCOLPOLICY dp ");
		sql.append("INNER JOIN CMN_SEC_DATARESCOLCONFIG dpc ON dp.DataResColConfig_ID = dpc.ID  ");
		sql.append("INNER JOIN CMN_SEC_DATARESCOLCONFIG_ROLE dpcRole ON dpcRole.DATARESCOLCONFIG_ID = dpc.ID ");
		sql.append("INNER JOIN CMN_MET_METADATA cmet ON cmet.ID = dp.BaseMetaData_ID ");
		sql.append("INNER JOIN CMN_MET_METADATADEFINE cmetDef ON cmetDef.ID = dp.BaseMetaDataDefine_ID ");
		sql.append("WHERE dpc.Flag = 1 ");
		sql.append("AND ( dp.ViewListStatus= 'N' OR dp.ViewDetailStatus = 'N' ) ");
		sql.append("AND  dpcRole.ROLE_ID IN (");
			sql.append("SELECT uaRole.role_id FROM CMN_SEC_USERACCOUNT_ROLE uaRole WHERE uaRole.UserAccount_ID = ? ");
		sql.append(") ");
		params.add(userAccountId);

		List<UserAccountColConfigInfo> uaColList = dataColDao.queryObjectListBySql(sql.toString(), UserAccountColConfigInfo.class, params.toArray());
		if(uaColList!=null && !uaColList.isEmpty()){
			for(UserAccountColConfigInfo colConfig : uaColList){
				String metaDataName = colConfig.getMetaDataName();
				String property  = colConfig.getProperty();
			    String viewListStatus = colConfig.getViewListStatus();
			    String viewDetailStatus = colConfig.getViewDetailStatus();
			    
			    Map<String,Set<String>> metaMap = null ;
			    if(resMap.containsKey(metaDataName)){
			    	//已经有改元数据
			    	metaMap = resMap.get(metaDataName);//DataColConstant.COL_MDP_VIEW_TYPE_LIST
			    	
			    	if(viewListStatus.equalsIgnoreCase(DataColConstant.VIEW_STATUS_N)){
			    		//列表
			    		if(metaMap.containsKey(DataColConstant.COL_MDP_VIEW_TYPE_LIST)){
			    			metaMap.get(DataColConstant.COL_MDP_VIEW_TYPE_LIST).add(property);
			    		}else{
			    			Set<String> propertySet = new HashSet<String>();
			    			propertySet.add(property);
			    			metaMap.put(DataColConstant.COL_MDP_VIEW_TYPE_LIST,propertySet);
			    		}
			    	}else if(viewDetailStatus.equalsIgnoreCase(DataColConstant.VIEW_STATUS_N)){
			    		//详情页 COL_MDP_VIEW_TYPE_DETAIL
			    		if(metaMap.containsKey(DataColConstant.COL_MDP_VIEW_TYPE_DETAIL)){
			    			metaMap.get(DataColConstant.COL_MDP_VIEW_TYPE_DETAIL).add(property);
			    		}else{
			    			Set<String> propertySet = new HashSet<String>();
			    			propertySet.add(property);
			    			metaMap.put(DataColConstant.COL_MDP_VIEW_TYPE_DETAIL,propertySet);
			    		}
			    		
			    	}else if(metaMap.containsKey(DataColConstant.COL_MDP_VIEW_TYPE_EDIT)){
			    		
			    	}
			    	
			    }else{
			    	metaMap = new HashMap<String, Set<String>>();
			    	if(viewListStatus.equalsIgnoreCase(DataColConstant.VIEW_STATUS_N)){
		    			Set<String> propertySet = new HashSet<String>();
		    			propertySet.add(property);
		    			metaMap.put(DataColConstant.COL_MDP_VIEW_TYPE_LIST,propertySet);
			    	}else if(viewDetailStatus.equalsIgnoreCase(DataColConstant.VIEW_STATUS_N)){
		    			Set<String> propertySet = new HashSet<String>();
		    			propertySet.add(property);
		    			metaMap.put(DataColConstant.COL_MDP_VIEW_TYPE_DETAIL,propertySet);
			    		
			    	}else if(metaMap.containsKey(DataColConstant.COL_MDP_VIEW_TYPE_EDIT)){
			    		
			    	}
			    	resMap.put(metaDataName, metaMap);
			    }
			}
		}
		return resMap;
	}
}
