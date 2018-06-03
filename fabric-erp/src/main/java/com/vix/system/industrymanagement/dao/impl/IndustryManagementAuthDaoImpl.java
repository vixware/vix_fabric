package com.vix.system.industrymanagement.dao.impl;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.vix.common.orginialMeta.entity.OrginialAuthority;
import com.vix.core.constant.BizConstant;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.system.industrymanagement.dao.IIndustryManagementAuthDao;

@Repository("industryManagementAuthDao")
public class IndustryManagementAuthDaoImpl extends BaseHibernateDaoImpl implements IIndustryManagementAuthDao {

    private static final long serialVersionUID = 1L;
    
	    
    @Override
	public Set<OrginialAuthority> findAllOrginialAuthorityMWithIndustryMgtModule(String industryMgtModuleId,String bizType)throws Exception{
    	StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("SELECT auth.ID,auth.Name, auth.DisplayName, auth.authorityCode, auth.parentAuthorityCode,auth.SortOrder  ");//.append(ename); auth.Parent_id,
		sql.append(",im.IndustryManagementModule_ID immId, CASE WHEN im.IndustryManagementModule_ID IS NULL THEN 'N' ELSE 'Y' END isCheck ");
		sql.append(",(SELECT COUNT(sub.id) FROM CMN_SEC_ORGINIAL_AUTH sub WHERE sub.parentAuthorityCode = auth.authorityCode and sub.AuthType = ? and sub.BizType= ? ) subCount ");
		params.add(BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_M);
		params.add(bizType);//BizConstant.COMMON_SECURITY_RESTYPE_P
		
		sql.append("FROM CMN_SEC_ORGINIAL_AUTH auth ");
		sql.append("LEFT JOIN CMN_SEC_ORGINIAL_AUTH_IND_MODULE im ON im.IndustryManagementModule_ID =?  and im.AUTHORITY_ID = auth.id ");
		params.add(industryMgtModuleId);
		sql.append("WHERE auth.AuthType = ? AND auth.BizType= ? ");
		//sql.append(" and auth.id <= 13628 ");
		params.add(BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_M);
		params.add(bizType);	//params.add(BizConstant.COMMON_SECURITY_RESTYPE_P);
		
		List<OrginialAuthority> allMenuList = queryObjectListBySql(sql.toString(), OrginialAuthority.class, params.toArray());
    	
		Map<String,OrginialAuthority> treeMap = new ConcurrentHashMap<String,OrginialAuthority>();
    	Set<OrginialAuthority> resSet = new TreeSet<OrginialAuthority>();
    	
        for (OrginialAuthority au:allMenuList) {
        	au.setCheckId(au.getId());
        		//isCheck 
	        	//parentId
				//isParent Long subCount = au.getSubCount();
	        	//System.out.println(au.getParentId());
        	//treeMap.put(au.getId(), au);
        	treeMap.put(au.getAuthorityCode(), au);
        }
        
        
        for(Map.Entry<String,OrginialAuthority> entry:treeMap.entrySet()){
        	String key = entry.getKey();
        	OrginialAuthority node = entry.getValue();
        	
           /* if(node.getParentId()==null){
                resSet.add(node);
            }else{
                Long parentIdTemp = node.getParentId(); 
                if(treeMap.containsKey(parentIdTemp)){
                    treeMap.get(parentIdTemp).addChildren(node);
                }
            }*/
        	if(StringUtils.isEmpty(node.getParentAuthorityCode())){
        		resSet.add(node);
        	}else{
        		String parentNodeCode = node.getParentAuthorityCode();
        		 if(treeMap.containsKey(parentNodeCode)){
        			 treeMap.get(parentNodeCode).addChildren(node);
        		 }
        	}
        }
        
        return resSet;
    }
    
    
    @Override
	public List<OrginialAuthority> findSubOrginialAuthorityFByIndustryMgtModule(String industryMgtModuleId,String orginialAuthCode,String bizType)throws Exception{
    	StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("SELECT auth.ID,auth.Name, auth.DisplayName, auth.authorityCode, auth.parentAuthorityCode,auth.SortOrder   ");//.append(ename);
		sql.append(",im.IndustryManagementModule_ID immId, CASE WHEN im.IndustryManagementModule_ID IS NULL THEN 'N' ELSE 'Y' END isCheck ");
		
		sql.append("FROM CMN_SEC_ORGINIAL_AUTH auth ");
		sql.append("LEFT JOIN CMN_SEC_ORGINIAL_AUTH_IND_MODULE im ON im.AUTHORITY_ID = auth.id and im.IndustryManagementModule_ID =? ");
		sql.append("WHERE auth.parentAuthorityCode=? and auth.BizType= ? AND auth.AuthType = ? ");

		//sql.append(" and auth.id <= 13628 ");
		params.add(industryMgtModuleId);
		params.add(orginialAuthCode);
		params.add(bizType);//params.add(BizConstant.COMMON_SECURITY_RESTYPE_P);
		params.add(BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_F);
		
		sql.append(" order by auth.SortOrder ");
		
		List<OrginialAuthority> allFunList = queryObjectListBySql(sql.toString(), OrginialAuthority.class, params.toArray());
        return allFunList;
    }
    
}
