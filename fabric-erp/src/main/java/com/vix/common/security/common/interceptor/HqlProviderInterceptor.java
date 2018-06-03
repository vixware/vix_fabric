package com.vix.common.security.common.interceptor;

import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.vix.common.securityDra.util.HandleVixSecurityContext;
import com.vix.core.constant.DataRowConstant;


@Service("hqlProviderInterceptor")
public class HqlProviderInterceptor implements MethodInterceptor {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(HqlProviderInterceptor.class);
	
	
	
	@SuppressWarnings({ "unchecked","unused" })//
	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		
		/**
		 * Object aaa = ContextUtil.getSessionAttr(SecurityScope.HQL_USER_OBJ);//.setSessionAttr("aaa", 111);
            System.out.println(aaa);
		 */
		/*UserAccount ua = null;
		try {
			ua =  SecurityUtil.getCurrentUserAccount();
		} catch (AccessDeniedException e) {
			 return  (String)methodInvocation.proceed() ;
		}
		if(ua==null){
			 return  (String)methodInvocation.proceed();
		}*/
		
		
		//String tokenKey = methodInvocation.getThis().getClass().getName()+"."+methodInvocation.getMethod().getName();
		
		//BiMap<String,Long> hqlMethodMetadataMap = ApplicationSecurityCode.hqlMethodMetadataMap;
		
		Object obj = methodInvocation.proceed();
		/*
		if(obj instanceof StringBuilder){
			StringBuilder oldSb = new StringBuilder((StringBuilder)obj);
			if(hqlMethodMetadataMap!=null){
				Object userHqlObj = ContextUtil.getSessionAttr(SecurityScope.HQL_USER_OBJ);
				if(userHqlObj!=null){
					Map<String,DataResRowPolicyObj> userHqlObjMap = (Map<String,DataResRowPolicyObj>)userHqlObj;
					if(userHqlObjMap.containsKey(tokenKey)){
						DataResRowPolicyObj plyObj = userHqlObjMap.get(tokenKey);
						
						
						
						//hql
						oldSb.append(plyObj.getWheres());
						
						//参数 方法的参数
						//Object[] oldParam2 = methodInvocation.getArguments();
						Map<String,Object> paramObjMap=null;;
						Object[] oldParam = methodInvocation.getArguments();
						for(Object paramObj:oldParam){
							if(paramObj!=null && paramObj instanceof Map){
								if(paramObj==null){
									paramObj = new HashMap<String,Object>();
								}
								paramObjMap = (Map<String,Object>)paramObj;
							}
						}
						if(paramObjMap==null){
							paramObjMap = new HashMap<String, Object>();
						}	
						
						
						String paramMapStr = plyObj.getParamsMap();
						if(StringUtils.isNotEmpty(paramMapStr)){
							ConcurrentMap<String, Object> paramMap = JSonUtils.readValue(paramMapStr, ConcurrentHashMap.class);
							
							for(ConcurrentMap.Entry<String, Object> entry:paramMap.entrySet()){
								String key = entry.getKey();
								String keyVal = ":"+key;
								if(oldSb.indexOf(keyVal)==-1){
									paramMap.remove(key);
								}
							}
							//得到约束的map
							
							if(paramMap !=null && !paramMap.isEmpty()){
								Map<String,Object> paramObjMap = (Map<String,Object>)paramObj;
								paramObjMap.putAll(paramMap);
								paramObjMap.putAll(paramMap);
							}
							
						}
						
						
						//2 处理业务对象属性（集合）类型的约束
						String bizJsonStr = plyObj.getBizAndSetParamsJson();
						if(StringUtils.isNotEmpty(bizJsonStr)){
							DataResRowBizProperty dataResRowBizProperty = JSonUtils.readValue(bizJsonStr, DataResRowBizProperty.class) ;
							
							String masterAlilasName = dataResRowBizProperty.getMasterAlilasName();
							String bizAlilasName = dataResRowBizProperty.getBizAlilasName();
							String bizProperty = dataResRowBizProperty.getBizProperty();
							
							boolean isBizType = dataResRowBizProperty.isBizType();
							String innerJoinBizCondition = dataResRowBizProperty.getInnerJoinBizCondition();
							//String op = dataResRowBizProperty.getOp();
							String ruleOp = dataResRowBizProperty.getOp();
							
							String hqlBizCondition = dataResRowBizProperty.getHqlBizCondition();
							//String hqlBizSetCondition = dataResRowBizProperty.getHqlBizSetCondition();
							String  bizHqlMapStr = plyObj.getBizHqlMap();
							
							
							oldSb.append(" ").append(ruleOp);//op
							
							//处理嵌入hql问题
							hqlBizCondition = handlerBizHqlCondition(hqlBizCondition, bizHqlMapStr, paramObjMap);
							
							
							//{"bizAlilasName":"subOrgPositions","bizProperty":"subOrgPositions",
							//"bizType":false,"hqlBizCondition":" in (  7,8 ) ","hqlBizSetCondition":" in (  7,8 ) ",
							//"innerJoinBizCondition":" orgPosition.subOrgPositions ",
							//"masterAlilasName":"orgPosition","op":"and"}
							
							int length = oldSb.length();
							int joinIndex = oldSb.indexOf(innerJoinBizCondition);
							if(joinIndex!=-1){
								//集合和业务对象类型    因为原sql中已经包含此inner join  都需要需要截取其别名  并在where后添加条件
								//System.out.println(sb.substring(joinIndex, length));
								//System.out.println(sb.substring(joinIndex+innerTest.length(), length));
								String objAlilasNameStr = oldSb.substring(joinIndex+innerJoinBizCondition.length(), length);

								String objAlilasNameStrTrim = objAlilasNameStr.trim();
								//System.out.println(objAlilasNameStrTrim);
								
								int firstBlanIndex = objAlilasNameStrTrim.indexOf(" ");
								//System.out.println(firstBlanIndex);
								//System.out.println("alilasName:"+StringUtils.substring(objAlilasNameStrTrim, 0, firstBlanIndex));
								String oldAlilasName = StringUtils.substring(objAlilasNameStrTrim, 0, firstBlanIndex);
								
								oldSb.append(" ").append(oldAlilasName).append(".id ").append(hqlBizCondition);
							}else{
								if(!isBizType){
									//集合类型    因为原sql中不含此inner join  需要where前 插入inner join   并在where后添加条件
									
									
									String innerJoinHql = " inner join "+masterAlilasName+"."+bizProperty+" "+bizAlilasName;
									
									int whereIndex = oldSb.indexOf(" where ");
									oldSb.insert(whereIndex, innerJoinHql);

									oldSb.append(" ").append(bizAlilasName).append(".id ").append(hqlBizCondition);//hqlBizSetCondition
								}else{
									//业务对象类型 因为原sql中不含此inner join  所以可以直接在 where后添加条件
									oldSb.append(" ").append(masterAlilasName).append(".").append(bizAlilasName).append(".id ").append(hqlBizCondition);//hqlBizSetCondition
									
								}
							}
							//System.out.println(oldSb);
						}
						
						
					}
				}
				
				//oldSb = methodInvocation.proceed();
			}
			//else{
				//oldSb = methodInvocation.proceed();
			//}
			return oldSb;
		}*/
		
		return obj; 
		/*StringBuilder oldSb = new StringBuilder((StringBuilder)methodInvocation.proceed());
		
		if(tokenKey.equals("com.vix.common.security.hql.RoleHqlProvider.findSelectRoleForUser")){
			log.debug("数据拦截AOP HQL ResultBefore: "+oldSb.toString());
			
			oldSb.append(" and 1=1 and 2=2 ");
			
			//Map<String, Object>
			Object[] oldParam = methodInvocation.getArguments();
			for(Object paramObj:oldParam){
				if(paramObj instanceof Map){
					((Map) paramObj).put("haha", new Date());
				}
			}
			
			
			
			
			Map<String, Object> paramMap = JSonUtils.readValue(paramMapStr, Map.class);
						
						Map<String, Object> paramMapTmp = new HashMap<String, Object>();
						paramMapTmp.put(key, value);
						
						for(Map.Entry<String, Object> entry:paramMap.entrySet()){
							String key = entry.getKey();
							if(){
								
							}
						}
		}*/

		
		//log.info("数据拦截AOP HQL ResultBefore: "+result.toString());
		
	}
	
	
	/**
	 * 处理嵌入HQl查询问题
	 * @return
	 */
	private String handlerBizHqlCondition(String bizCondition,String  bizHqlMapStr,Map<String,Object> paramMap){
		
		String[] bizHqlMapStrArray = bizHqlMapStr.split("\\,");
		for(String oneParam:bizHqlMapStrArray){
			
			//String sysValKey = oneParam;
			String replaceKeyVal =DataRowConstant.sysparamValueMap.get(oneParam);
			bizCondition = StringUtils.replaceOnce(bizCondition, oneParam, replaceKeyVal);
			paramMap.put(replaceKeyVal.substring(1), HandleVixSecurityContext.getContextValue(oneParam));
		}
		return bizCondition;
	}
}
