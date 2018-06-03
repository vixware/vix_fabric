package com.vix.common.security.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.vix.common.orginialMeta.entity.OrginialAuthority;
import com.vix.common.security.dao.IOrginialAuthorityDao;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.security.vo.OrginialAuthorityImpVo;
import com.vix.common.share.entity.BaseOrganization;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.utils.StrUtils;

@Repository("orginialAuthorityDao")
public class OrginialAuthorityDaoImpl extends BaseHibernateDaoImpl implements IOrginialAuthorityDao {

    private static final long serialVersionUID = 1L;

    @Override
	public List<OrginialAuthority> findAllMenuOrginialAuthorityList() throws Exception {
        StringBuffer hql = new StringBuffer();
        hql.append("select au from OrginialAuthority au where au.isMenu=:isMenu ");// and au.menuHrefUrl is not null 
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("isMenu", 1);
        
        HqlTenantIdUtil.handleParamMap4TenantId(param);//不增加tenantId
        HqlTenantIdUtil.handleParamMap4CompanyInnerCode(param);//不处理CompanyInnerCode;
        
        List<OrginialAuthority> auList = findAllByHql(hql.toString(), param);
        
        return auList;
    }
    
    /**
     * 导入权限信息
     * @param voList
     * @throws Exception
     */
    @Override
	public void saveForImportOrginialAuthority(List<OrginialAuthorityImpVo> voList)throws Exception{
    	Set<OrginialAuthority> boList = new HashSet<OrginialAuthority>();
    	if(voList!=null && !voList.isEmpty()){
    		transferToBo(voList, boList);
    	}
    	//System.out.println("Start:" + boList.size());
		//handlerAuthorityTree(boList,null,null);
    }
    
    private void transferToBo(List<OrginialAuthorityImpVo> voList,Set<OrginialAuthority> boList) throws Exception{
    	//Map<String,OrginialAuthority> voMap = new HashMap<String, OrginialAuthority>();
    	
    	//按照编码分组  记录每个编码的子节点
    	//HashMultimap<String, OrginialAuthority> codeChildMap = HashMultimap.create();
    	
    	List<OrginialAuthority> boListTmp = new ArrayList<OrginialAuthority>();
    	for(OrginialAuthorityImpVo vo:voList){
    		OrginialAuthority bo = new OrginialAuthority();
    		
    		if(StrUtils.isEmpty(vo.getMenuHrefUrl())){
    			vo.setMenuHrefUrl(null);
    		}
    		
    		vo.setAuthorityCode(StrUtils.isNotEmpty(vo.getAuthorityCode())?vo.getAuthorityCode().toLowerCase():null);
    		vo.setParentAuthorityCode(StrUtils.isNotEmpty(vo.getParentAuthorityCode())?vo.getParentAuthorityCode().toLowerCase():null);
    		
    		BeanUtils.copyProperties(vo, bo, new String[]{});
    		
    		//voMap.put(bo.getAuthorityCode(), bo);
    		//String parentCode = bo.getParentAuthorityCode();
    		/*if(org.springframework.util.StringUtils.hasText(parentCode)){
    			int s1 = StringUtils.indexOf(parentCode, "_");
    			bo.setTopParentCode(StringUtils.substring(parentCode, 0, s1+4));
    		}*/
    		String topParentCode = SecurityUtil.getTopAuthorityCode(bo.getAuthorityCode());
    		bo.setTopParentCode(topParentCode);
    		/*if(StrUtils.isNotEmpty(parentCode)){
    			codeChildMap.put(parentCode, bo);//按照父节点分组
    		}*/
    		boListTmp.add(bo);
    	}
    	saveOrUpdateOriginalBatch(boListTmp);
    }
 
	@Override
	public List<BaseOrganization> findAllOrg()throws Exception{
    	StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
	
		sql.append("select distinct mo.* from MDM_ORG_ORGANIZATION mo where mo.TENANTID is not null ");
		
		List<BaseOrganization> allOrg = queryObjectListBySql(sql.toString(), BaseOrganization.class, params.toArray());
		return allOrg;
	}
}
