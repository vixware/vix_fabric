package com.vix.common.securityDra.service.impl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.meta.entity.BaseMetaData;
import com.vix.common.meta.entity.BaseMetaDataDefine;
import com.vix.common.security.entity.DataResRowMethodConfig;
import com.vix.common.security.entity.DataResRowPolicyObj;
import com.vix.common.securityDca.vo.MetaDataProperty;
import com.vix.common.securityDra.dao.IDataResRowMethodConfigDao;
import com.vix.common.securityDra.dao.IDataResRowPolicyObjDao;
import com.vix.common.securityDra.dao.IDataResRowSysParamDao;
import com.vix.common.securityDra.hql.DataResRowPolicyObjHqlProvider;
import com.vix.common.securityDra.service.IDataResRowPolicyObjService;
import com.vix.common.securityDra.util.HandleVixSecurityContext;
import com.vix.common.securityDra.vo.row.DataResRowBizProperty;
import com.vix.common.securityDra.vo.row.DataResRowSystemParamsVO;
import com.vix.common.securityDra.vo.rule.RowDataGroup;
import com.vix.common.securityDra.vo.rule.RowDataRule;
import com.vix.core.constant.DataRowConstant;
import com.vix.core.constant.DataRowOperator;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.JSonUtils;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

import flexjson.JSONSerializer;

@Service("dataResRowPolicyObjService")
@Transactional
public class DataResRowPolicyObjServiceImpl extends BaseHibernateServiceImpl implements IDataResRowPolicyObjService {

	@Resource(name="dataResRowPolicyObjDao")
	private IDataResRowPolicyObjDao dataResRowPolicyObjDao;
	
	@Resource(name="dataResRowSysParamDao")
	private IDataResRowSysParamDao dataResRowSysParamDao;
	

	@Resource(name="dataResRowMethodConfigDao")
	private IDataResRowMethodConfigDao dataResRowMethodConfigDao;
	
	@Resource(name="dataResRowPolicyObjHqlProvider")
	private DataResRowPolicyObjHqlProvider dataResRowPolicyObjHqlProvider;
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowPolicyObjService#findPolicyObjPager(com.vix.core.web.Pager, java.util.Map)
	 */
	@Override
	public Pager findPolicyObjPager(Pager pager,Map<String,Object> params) throws Exception{
		 StringBuilder hql = dataResRowPolicyObjHqlProvider.findPolicyObjList(params, pager.getOrderField(), pager.getOrderBy());
		 //pager = dataResRowPolicyObjDao.findPagerByHql2(pager,dataResRowPolicyObjHqlProvider.entityAsName(),hql.toString(), params);
		 pager = dataResRowPolicyObjDao.findPager2ByHql(pager, dataResRowPolicyObjHqlProvider.entityAsName(), hql.toString(), params);
		 //pager = dataResRowPolicyObjDao.findPagerByHql(pager,hql.toString(), params);
		 return pager;
	}
	
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowPolicyObjService#saveOrUpdate(java.lang.String, com.vix.common.security.entity.DataResRowPolicyObj)
	 */
	@Override
	public DataResRowPolicyObj saveOrUpdate(String addMetaDataIds, DataResRowPolicyObj policyObj) throws Exception{
		
		DataResRowPolicyObj obj = null;
		if(policyObj!=null && StrUtils.isNotEmpty(policyObj.getId())){
			obj = dataResRowPolicyObjDao.findEntityById(DataResRowPolicyObj.class, policyObj.getId());
		}
		if(obj==null){
			obj = new DataResRowPolicyObj();
		}
		//BeanUtils.copyProperties(policyObj, obj, new String[]{"metaDataSrcName","dataResRowPolicys","baseMetaData"});
		obj.setMetaDataViewName(policyObj.getMetaDataViewName());
		obj.setPriory(policyObj.getPriory());
		
		BaseMetaData metaData = null;
		if(StringUtils.isNotEmpty(addMetaDataIds)){
			//String mid = addMetaDataIds.split("\\,")[1];
			metaData = dataResRowPolicyObjDao.findEntityById(BaseMetaData.class, addMetaDataIds);
			
			obj.setMetaDataSrcName(metaData.getMetaDataName());
			obj.setBaseMetaData(metaData);
		}
		
		dataResRowPolicyObjDao.merge(obj);
		return obj;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowPolicyObjService#saveOrUpdateObjConfig(java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public DataResRowPolicyObj saveOrUpdateObjConfig(String plyObjId,List<String> propertyName,List<String> propertyOperator,List<String> mdpValue,List<String> mdpRealValue,List<String> propertyType,List<String> ruleOpertator) throws Exception{
		DataResRowPolicyObj plyObj = dataResRowPolicyObjDao.findEntityById(DataResRowPolicyObj.class, plyObjId);
		BaseMetaData plyObjMetaData = plyObj.getBaseMetaData();
		
		/*
		List<DataResRowMethodConfig> configList = findDataResRowMethodConfigListByMetaDataId(plyObjMetaData.getId());
		DataResRowMethodConfig configOne = null;
		if(configList!=null && !configList.isEmpty()){
			Assert.isTrue(configList.size()==1, "一个HQL拦截器不能绑定多个元数据对象!");
			configOne = configList.get(0);
		}else{
			return plyObj;
		}
		
		//String hqlPovider = configOne.getHqlProvider();
		DataResRowMethod dc = configOne.getDataResRowMethod();
		String hqlPovider = dc.getHqlProvider();
		
		Class clz = Class.forName(hqlPovider);
		Method alilasMethod = clz.getDeclaredMethod("entityAsName");
		Object res = ReflectionUtils.invokeMethod(alilasMethod, clz.newInstance());
		String masterAlilasName = res.toString();
		*/
		
		/*String mdName = plyObjMetaData.getMetaDataName();
		String masterAlilasName = "hentity";
		if(StrUtils.isNotEmpty(mdName)){
			String[] mdArray = StringUtils.split(mdName, "\\.");
			masterAlilasName = mdArray[mdArray.length-1];
		}
		masterAlilasName = WordUtils.uncapitalize(masterAlilasName);*/
		
		String masterAlilasName = "entityAsName";
		
		
		//09 02 构造业务对象属性map  key 属性名  value basemetadataDefine
		Map<String,BaseMetaDataDefine> bizPropertyMap = findMdPropertyMap(plyObjMetaData.getId());
		
		
		RowDataGroup rdg = new RowDataGroup();
		
		String lastOp = "";
		List<RowDataRule> ruleList = new LinkedList<RowDataRule>();
		if(propertyName!=null && !propertyName.isEmpty()){
			for(int i=0;i<propertyName.size();i++){
				String propertyNameTmp = propertyName.get(i);
				String propertyOperatorTmp = propertyOperator.get(i);
				String mdpValueTmp = mdpValue.get(i);
				String mdpRealValueTmp = mdpRealValue.get(i);
				String propertyTypeTmp = propertyType.get(i);
				String ruleOpertatorTmp = ruleOpertator.get(i);
				
				
				
				RowDataRule rdr = new RowDataRule();
				rdr.setFields(propertyNameTmp);
				rdr.setOp(propertyOperatorTmp);
				rdr.setValue(mdpValueTmp);
				rdr.setRealValue(mdpRealValueTmp);
				rdr.setValueType(propertyTypeTmp);
				rdr.setRuleOp(ruleOpertatorTmp);
				
				//如果propertyNameTmp 是业务对象属性  这里需要处理 1 业务对象属性  2 集合业务对象属性
				//BaseMetaData plyObjMetaData = plyObj.getBaseMetaData();
				if(bizPropertyMap.containsKey(propertyNameTmp)){
					BaseMetaDataDefine define = bizPropertyMap.get(propertyNameTmp);
					String defineProTypeTmp = define.getIsCollectionType();
					rdr.setPropertyType(defineProTypeTmp);
					
					//handlerBizProperty(plyObj,lastOp,plyObjMetaData,define, configOne, masterAlilasName, rdr);
					handlerBizProperty(plyObj,lastOp,plyObjMetaData,define, masterAlilasName, rdr);
				}else{
					rdr.setPropertyType("0");
				}
				
				ruleList.add(rdr);
					
				lastOp = ruleOpertatorTmp;
				
			}
		}
		
		rdg.setRules(ruleList);
		
		
		//dataResRowPolicyObjDao.clear();
		
		plyObj.setDataConfigRule(new JSONSerializer().exclude("class","rules.class").include("rules").serialize(rdg));
		
		//编译hql生成到属性   拦截hql时候直接反序列化变量即可
		saveForGenernalHqlCondition(plyObj,plyObjMetaData, masterAlilasName, ruleList);
		
		dataResRowPolicyObjDao.update(plyObj);
		
		//RowDataGroup rdgTest = new JSONDeserializer<RowDataGroup>().deserialize(plyObj.getDataConfigRule(),RowDataGroup.class);
		//System.out.println(rdgTest.getRules().size());
		return plyObj;
	}
	/**
	 * 保存json的时候直接编辑该条件为hql的附加条件 
	 * @throws Exception 
	 */
	private void saveForGenernalHqlCondition(DataResRowPolicyObj plyObj,BaseMetaData plyObjMetaData,String objAlilasName,List<RowDataRule> ruleList) throws Exception{
		//1 根绝 plyObj的 BaseMetadata 查询出其对应的hqlProvider,通过反射 得到查询的别名
		StringBuilder wheres = new StringBuilder();
		Map<String,Object> params = new HashMap<String, Object>();
		
		if(ruleList!=null && ruleList.size()>0){
			wheres.append(" and ( ");
			
			int wheresLengthFlag = wheres.length();
			
			for(int i=0;i<ruleList.size();i++){
				RowDataRule rule = ruleList.get(i);
				
				String fields = rule.getFields();
				String fieldsRealValue = StringUtils.isEmpty(rule.getRealValue())?rule.getValue():rule.getRealValue();
				String op = rule.getOp();
				String valueType = rule.getValueType();
				String ruleOp = rule.getRuleOp();
				
				String propertyType = rule.getPropertyType();
				if(StringUtils.isEmpty(propertyType)){
					propertyType = "";
				}
				//System.out.println(StringUtils.isNotEmpty(fieldsRealValue));
				if(StringUtils.isNotEmpty(fieldsRealValue) && propertyType.endsWith("0")){
					
					//操作符号前面
					if(fields.startsWith("{")){
						String fieldsPam = fields.replace("{", "_").replace("}", "_");
						wheres.append(" :").append(fieldsPam);
						params.put(fieldsPam, HandleVixSecurityContext.getContextValue(fields));
						
					}else{
						wheres.append(" ").append(objAlilasName).append(".").append(fields);
					}
					
					//操作符 最后处理
					//wheres.append(" ").append(op);
					
					//操作符号后面
					StringBuilder afterOpSb = new StringBuilder();
					if(fieldsRealValue.startsWith("{")){
						String fieldsRealValuePam = fieldsRealValue.replace("{", "_").replace("}", "_");
						afterOpSb.append(" :").append(fieldsRealValuePam);
						params.put(fieldsRealValuePam, HandleVixSecurityContext.getContextValue(fieldsRealValue));
						
					}else{
						
						/*if(op.equals(DataRowOperator.LIKE)){
							afterOpSb.append(" %").append(fieldsRealValue).append("%");
						}else{
							afterOpSb.append(" ").append(fieldsRealValue);
						}*/
						if(valueType.equals(DataRowOperator.DATATYPE_INTEGER) || valueType.equals(DataRowOperator.DATATYPE_LONG) || valueType.equals(DataRowOperator.DATATYPE_DOUBLE)){
							afterOpSb.append(" ").append(fieldsRealValue);
						}else{
							if(op.equals(DataRowOperator.LIKE)){
								afterOpSb.append(" '%").append(fieldsRealValue).append("%' ");
							}else{
								afterOpSb.append(" '").append(fieldsRealValue).append("' ");
							}
						}
						
					}
					
					
					//操作符
					if(op.equals(DataRowOperator.IN) || op.equals(DataRowOperator.NOT_IN)){
						wheres.append(" ").append(op).append(" ( ");
						wheres.append(afterOpSb);
						wheres.append(" ) ");
					}else{
						wheres.append(" ").append(op).append(" ").append(afterOpSb).append(" ");
					}
					
					
					if( (i+1)<ruleList.size() ){
						wheres.append(ruleOp);
					}
				}
				
				
			}
			
			if(wheresLengthFlag == wheres.length()){
				wheres.append(" 1=1 ");
			}
			
			//String wheresStr = wheres.toString().trim();
			String whereTailStr = StrUtils.trimLastSubstring(wheres);
			if((whereTailStr.length()-4) == StringUtils.lastIndexOfIgnoreCase(whereTailStr, " and")){
				wheres.setLength(StrUtils.trimLastSubstring(wheres).length()-3);
			}
			
			wheres.append(" ) ");
		}
		
		
		//拼装wheres 和 param 完毕  保存此两个变量
		plyObj.setWheres(wheres.toString());
		if(!params.isEmpty()){
			plyObj.setParamsMap(JSonUtils.toJSon(params));
		}
			
		//ReflectionUtils.
	}
	
	/**
	 * 处理业务对象属性
	 * @param metaData Map<String,BaseMetaDataDefine> bizDataMap,
	 * @param metaDataDefine
	 * @param config
	 * @param rule
	 */
	private void handlerBizProperty(DataResRowPolicyObj plyObj,String lastOp,BaseMetaData metaData,BaseMetaDataDefine metaDataDefine,String masterAlilasName, RowDataRule rule){
		String fields = rule.getFields();
		String fieldsRealValue = StringUtils.isEmpty(rule.getRealValue())?rule.getValue():rule.getRealValue();
		String op = rule.getOp();
		String valueType = rule.getValueType();
		String ruleOp = rule.getRuleOp();
		
		StringBuilder wheres = new StringBuilder();
		//wheres.append(" ").append(lastOp).append(" ");
		//Map<String,Object> params = new HashMap<String, Object>();
		
		if(StringUtils.isNotEmpty(fieldsRealValue)){
			StringBuilder innerJoinHql = new StringBuilder(); 
			
			String childProperty = metaDataDefine.getProperty();//属性
			/*String childClass = metaDataDefine.getPropertyType();//属性的业务对象类型  
			
			int childClassSimpleNameIndex = StringUtils.lastIndexOf(childClass, ".");
			String childClassSimpleName = StringUtils.substring(childClass, childClassSimpleNameIndex+1);*/
			
			innerJoinHql.append(" ").append(masterAlilasName).append(".").append(childProperty);//.append(" ");//后空格不要了   方便在拦截器中截取别名
			//String childClasseAlilasName = StrUtils.toLowerCaseInitial(childClassSimpleName, false);
			String childClasseAlilasName = childProperty;
				
			String  isCollectionType = metaDataDefine.getIsCollectionType();
			boolean isBiz = isCollectionType.endsWith("1")?true:false;//1 业务对象属性   2 非业务对象属性
			
			//操作符号前面
			/*if(fields.startsWith("{")){
				String fieldsPam = fields.replace("{", "_").replace("}", "_");
				wheres.append(" :").append(fieldsPam);
				params.put(fieldsPam, HandleVixSecurityContext.getContextValue(fields));
				
			}else{*/
			StringBuilder headerhql = new StringBuilder();
			headerhql.append(" ").append(masterAlilasName).append(".").append(fields).append(" ");
			
			if(!isBiz){
				//集合
				
			}else{
				//非集合
				//wheres.append(" ").append(masterAlilasName).append(".").append(fields).append(".id ");
			}
			
			
			//操作符 最后处理
			//wheres.append(" ").append(op);
			
			//操作符号后面
			StringBuilder afterOpSb = new StringBuilder();
			/*if(fieldsRealValue.startsWith("{")){
				String fieldsRealValuePam = fieldsRealValue.replace("{", "_").replace("}", "_");
				afterOpSb.append(" :").append(fieldsRealValuePam);
				params.put(fieldsRealValuePam, HandleVixSecurityContext.getContextValue(fieldsRealValue));
				
			}else{*/
				
			if(valueType.equals(DataRowOperator.DATATYPE_INTEGER) || valueType.equals(DataRowOperator.DATATYPE_LONG) || valueType.equals(DataRowOperator.DATATYPE_DOUBLE )  ){
				afterOpSb.append(" ").append(fieldsRealValue);
			}else if(valueType.equals(DataRowOperator.DATATYPE_HQL)){
				if(fieldsRealValue.contains("{")){
					//处理嵌套hql  把 {SysUserAccount} 转换成:_SysUserAccount_

					/*for(Map.Entry<String, String> bizValEntry:DataRowConstant.sysparamMap.entrySet()){
						
					}*/
					List<String> bizHqlMapList = new ArrayList<String>();
					for(Map.Entry<String, String> bizValEntry:DataRowConstant.sysparamMap.entrySet()){
						String sysValKey = bizValEntry.getKey();
						
						//System.out.println(DataRowConstant.sysparamValueMap);
						//String replaceKeyVal = DataRowConstant.sysparamValueMap.get(sysValKey);
						if(fieldsRealValue.contains(sysValKey)){
							bizHqlMapList.add(sysValKey);
							//fieldsRealValue.replaceFirst(sysValKey, replaceKeyVal);
							//StringUtils.replaceOnce(fieldsRealValue, sysValKey, replaceKeyVal);
						}
					}
					if(!bizHqlMapList.isEmpty()){
						plyObj.setBizHqlMap(StringUtils.join(bizHqlMapList.iterator(), ","));
					}
					
				}
				//else{
				afterOpSb.append(" ").append(fieldsRealValue);
				//}
			}else{
				if(op.equals(DataRowOperator.LIKE)){
					afterOpSb.append(" '%").append(fieldsRealValue).append("%' ");
				}else{
					afterOpSb.append(" '").append(fieldsRealValue).append("' ");
				}
			}
			
			
			//操作符
			if(op.equals(DataRowOperator.IN) || op.equals(DataRowOperator.NOT_IN)){
				wheres.append(" ").append(op).append(" ( ");
				wheres.append(afterOpSb);
				wheres.append(" ) ");
			}else{
				wheres.append(" ").append(op).append(" ").append(afterOpSb).append(" ");
			}
			
			DataResRowBizProperty bizPropertyTmp = new DataResRowBizProperty();
			bizPropertyTmp.setBizProperty(childProperty);
			bizPropertyTmp.setMasterAlilasName(masterAlilasName);
			bizPropertyTmp.setBizAlilasName(childClasseAlilasName);
			bizPropertyTmp.setBizType(isBiz);
			bizPropertyTmp.setInnerJoinBizCondition(headerhql.toString());
			
			//bizPropertyTmp.setOp(lastOp);
			bizPropertyTmp.setOp(ruleOp);
			
			bizPropertyTmp.setHqlBizCondition(wheres.toString());
			bizPropertyTmp.setHqlBizSetCondition(wheres.toString());
			
			String jsonTmp = JSonUtils.makeBeanToJson(bizPropertyTmp);
			
			plyObj.setBizAndSetParamsJson(jsonTmp);
			//System.out.println(bizPropertyTmp);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowPolicyObjService#findDataResRowMethodConfigListByMetaDataId(java.lang.Long)
	 */
	@Override
	public List<DataResRowMethodConfig> findDataResRowMethodConfigListByMetaDataId(String metaDataId) throws Exception{
		StringBuilder hql = new StringBuilder();
		hql.append("select drc from DataResRowMethodConfig drc where drc.baseMetaData.id = :id ");
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", metaDataId);
		
		//HqlTenantIdUtil.handleParamMap4TenantId(params);
		
		List<DataResRowMethodConfig> configList = dataResRowPolicyObjDao.findAllByHql2(hql.toString(), params);
		return configList;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowPolicyObjService#findPolicyObj(java.lang.String)
	 */
	@Override
	public DataResRowPolicyObj findPolicyObj(String id)throws Exception{
		StringBuilder hql = new StringBuilder();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		hql.append("select obj from DataResRowPolicyObj obj left join fetch obj.baseMetaData where obj.id=:id ");
		List<DataResRowPolicyObj> objList = dataResRowPolicyObjDao.findAllByHql(hql.toString(), params);
		if(objList!=null && objList.size()>0){
			return objList.get(0);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowPolicyObjService#findMdbPropertyList(java.lang.String)
	 */
	@Override
	public List<MetaDataProperty> findMdbPropertyList(String metadataId)throws Exception{
		List<MetaDataProperty> resList = new LinkedList<MetaDataProperty>();
		StringBuilder hql = new StringBuilder();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", metadataId);
		hql.append("select md from BaseMetaDataDefine md where md.baseMetaData.id=:id ");
		List<BaseMetaDataDefine> mdList = dataResRowPolicyObjDao.findAllByHql(hql.toString(), params);
		if(mdList!=null){
			for(BaseMetaDataDefine md:mdList){
				MetaDataProperty mdp = new MetaDataProperty();
				mdp.setId(md.getId());
				mdp.setType("D");
				//只用到下面两个属性
				mdp.setPropertyName(md.getPropertyName());
				mdp.setProperty(md.getProperty());
				
				
				//mdp.setIsCollectionType(md.getIsCollectionType());
				resList.add(mdp);
			}
		}
		return resList;
	}
	
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowPolicyObjService#findMetaDataProperty()
	 */
	@Override
	public List<MetaDataProperty> findMetaDataProperty()throws Exception{
		List<DataResRowSystemParamsVO> paramList = dataResRowSysParamDao.findResRowSysParams();
		List<MetaDataProperty> mpList = new ArrayList<MetaDataProperty>();
		
		for(DataResRowSystemParamsVO pa:paramList){
			MetaDataProperty mp = new MetaDataProperty("S",pa.getKeyPropertyName(),pa.getKeyProperty(),pa.getId(),pa.getParamUrl());
			mpList.add(mp);
		}
		return mpList;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowPolicyObjService#findPolicyObjMapByUserId(java.lang.String)
	 */
	@Override
	public Map<String,DataResRowPolicyObj> findPolicyObjMapByUserId(String userId) throws Exception{
		Map<String,DataResRowPolicyObj> resMap = dataResRowPolicyObjDao.findPolicyObjMapByUserId(userId);
		return resMap;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowPolicyObjService#findPolicyObjMetadataStrMapByUserId(java.lang.String)
	 */
	@Override
	public Map<String,DataResRowPolicyObj> findPolicyObjMetadataStrMapByUserId(String userId) throws Exception{
		Map<String,DataResRowPolicyObj> resMap = dataResRowPolicyObjDao.findPolicyObjMapStrByUserId(userId);
		return resMap;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowPolicyObjService#findPolicyObjMetadataMapByUserId(java.lang.String)
	 */
	@Override
	public Map<String,DataResRowPolicyObj> findPolicyObjMetadataMapByUserId(String userId) throws Exception{
		//得到系统的所有hql拦截配置  hql方法和metadataid的映射 
		
		//Map<String,String> allHqlMetadaMap = ApplicationSecurityCode.hqlMethodMetadataMap;
		
		
		/*if(hqlMetadaMap==null){
			hqlMetadaMap = dataResRowMethodConfigDao.findDataResRowMethodMap();
		}*/
		
		/*
		BiMap<String,String> allHqlMetadaMap = ApplicationSecurityCode.hqlMethodMetadataMap;//key 拦截的类方法全称    value ,和 本身的hqlMethod的 id
		
		if(allHqlMetadaMap==null){
			allHqlMetadaMap = dataResRowMethodConfigDao.findRowMethodMap();
		}
		*/
		//得到用户相关信息   metatadaid 和  DataResRowPolicyObj的映射 
		Map<String,DataResRowPolicyObj> userHqlMap = findPolicyObjMapByUserId(userId);//metadataId  key
		
		
		//返回结果
		Map<String,DataResRowPolicyObj> resMap = new HashMap<String, DataResRowPolicyObj>();//key 拦截的类方法全称    value hqlMethod的 id
		
		
		List<DataResRowMethodConfig> userMetaDataConfigList =  dataResRowMethodConfigDao.findUserDataResRowMethodConfg();
//		/*if(hqlMetadaMap!=null && !hqlMetadaMap.isEmpty()){
//			if(userHqlMap!=null && !userHqlMap.isEmpty()){
//				
//			}
//			for(Map.Entry<String,Long> entry:hqlMetadaMap.entrySet()){
//				String key = entry.getKey();
//				Long value = entry.getValue();
//				if(userHqlMap.containsKey(value)){
//					resMap.put(key, userHqlMap.get(value));
//				}
//			}
//		}*/
		
		
		/*
		if(userMetaDataConfigList!=null && userMetaDataConfigList.size()>0){
			
			BiMap<Long,String> inverseAllHqlMetadaMap = allHqlMetadaMap.inverse();//hqlMethod的 id   拦截的类方法全称    value
			
			for(DataResRowMethodConfig dc:userMetaDataConfigList){
				//Long dcId = dc.getId();
				Long dcMetaId = dc.getBaseMetaData().getId();
				Long hqlMethodId = dc.getDataResRowMethod().getId();
				
				if(userHqlMap.containsKey(dcMetaId) && inverseAllHqlMetadaMap.containsKey(hqlMethodId)){
					String key = inverseAllHqlMetadaMap.get(hqlMethodId);//key 拦截的类方法全称
					resMap.put(key, userHqlMap.get(dcMetaId));
				}
				
			}
		}*/
		return resMap;
		
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowPolicyObjService#findMddByMdId(java.lang.Long, java.lanStringring)
	 */
	@Override
	public BaseMetaDataDefine findMddByMdId(String mdId,String mdPropertye) throws Exception{
		BaseMetaDataDefine resMetadata = null;
		StringBuilder hql = new StringBuilder();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", mdId);
		params.put("property", mdPropertye);
		hql.append("select md from BaseMetaDataDefine md where md.baseMetaData.id=:id and md.property = :property ");
		List<BaseMetaDataDefine> mdList = dataResRowPolicyObjDao.findAllByHql2(hql.toString(), params);
		if(mdList!=null){
			if(mdList.size()>0){
				resMetadata = mdList.get(0);
				String metadataName = resMetadata.getPropertyType();
				if(StrUtils.isNotEmpty(metadataName)){
					BaseMetaData metadata = dataResRowPolicyObjDao.findBaseMetaDataByMetadataName(metadataName);
					resMetadata.setPropertyMetadataId(metadata.getId());
				}
				
			}
		}
		return resMetadata;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowPolicyObjService#findCommonBizSelectList(com.vix.core.web.Pager, java.lang.String)
	 */
	@Override
	public Pager findCommonBizSelectList(Pager pager,String metadataId)throws Exception{
		BaseMetaData metadata = dataResRowPolicyObjDao.findEntityById(BaseMetaData.class, metadataId);
		String clazzName = metadata.getMetaDataName();
		
		List<BaseMetaDataDefine> mddList = dataResRowPolicyObjDao.findBaseMetadataDefineList(metadataId);
		List<String> mdPropertyList = new LinkedList<String>();
		
		if(mddList!=null && !mddList.isEmpty()){
			for(BaseMetaDataDefine mdd:mddList){
				String isCollection = mdd.getIsCollectionType();
				if(StringUtils.isEmpty(isCollection) || (StringUtils.isNotEmpty(isCollection) && isCollection.equals("0"))){
					mdPropertyList.add(mdd.getProperty());
				}
				
			}
		}else{
			return pager;
		}
		
		
		StringBuilder hql = new StringBuilder();
		hql.append("select cmnObj from ").append(clazzName).append(" cmnObj ");
		
		//List<Object> objectList = dataResRowPolicyObjDao.findAllByHql2(hql.toString(), null);
		Pager pagerTmp = dataResRowPolicyObjDao.findPager2ByHql(pager, "cmnObj", hql.toString(), new HashMap<String, Object>());
		
		List<Object> objectList = pagerTmp.getResultList();
		
		//List<Map<String,String>> pzMapList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> resMapList = new LinkedList<Map<String,String>>();
		for(Object obj:objectList){
			Map<String,String> map = new HashMap<String, String>();
			
			for(String mddProperty:mdPropertyList){
				/*String ss = org.apache.commons.beanutils.BeanUtils.getProperty(obj, mddProperty);
				if(StringUtils.isNotEmpty(ss)){
					System.out.println(ss.toString());
				}*/
				PropertyDescriptor proClassType = BeanUtils.getPropertyDescriptor(obj.getClass(), mddProperty);
				String dataTypeName = proClassType.getPropertyType().getName();
				if(dataTypeName.contains("java.lang.")){
					map.put(mddProperty, org.apache.commons.beanutils.BeanUtils.getProperty(obj, mddProperty));
				}
				
			}
			resMapList.add(map);
		}
		
		pagerTmp.setResultList(resMapList);
		return pagerTmp;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowPolicyObjService#findMdPropertyNotId(java.lang.String)
	 */
	@Override
	public List<BaseMetaDataDefine> findMdPropertyNotId(String metadataId)throws Exception{
		List<BaseMetaDataDefine> mddList =  dataResRowPolicyObjDao.findBaseMetadataDefineListNoId(metadataId);
		
		List<BaseMetaDataDefine> resMddList = new LinkedList<BaseMetaDataDefine>();
		if(mddList!=null && !mddList.isEmpty()){
			for(BaseMetaDataDefine mdd:mddList){
				String isCollection = mdd.getIsCollectionType();
				if(StringUtils.isEmpty(isCollection) || (StringUtils.isNotEmpty(isCollection) && isCollection.equals("0"))){
					resMddList.add(mdd);
				}
			}
		}
		return resMddList;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowPolicyObjService#findMdPropertyMap(java.lang.String)
	 */
	@Override
	public Map<String,BaseMetaDataDefine> findMdPropertyMap(String metadataId)throws Exception{
		List<BaseMetaDataDefine> mddList =  dataResRowPolicyObjDao.findBaseMetadataDefineList(metadataId);
		
		Map<String,BaseMetaDataDefine> resMap = new HashMap<String, BaseMetaDataDefine>();
		if(mddList!=null && !mddList.isEmpty()){
			for(BaseMetaDataDefine mdd:mddList){
				String isCollection = mdd.getIsCollectionType();
				if(!StringUtils.isEmpty(isCollection) && (isCollection.equals("1") || isCollection.equals("2"))){
					resMap.put(mdd.getProperty(), mdd);
				}
			}
		}
		return resMap;
	}
}
