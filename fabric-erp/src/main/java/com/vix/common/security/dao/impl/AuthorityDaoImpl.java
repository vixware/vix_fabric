package com.vix.common.security.dao.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.vix.common.org.dao.IBaseOrganizationDao;
import com.vix.common.security.dao.IAuthorityDao;
import com.vix.common.security.entity.Authority;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.BizConstant;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

@Repository("authorityDao")
public class AuthorityDaoImpl extends BaseHibernateDaoImpl implements IAuthorityDao {

    private static final long serialVersionUID = 1L;
    @Resource(name = "baseOrganizationDao")
	private IBaseOrganizationDao organizationDao;
    /*
     * (非 Javadoc)
     * <p>Title: findAllMenuAuthorityList</p>
     * <p>Description: </p>
     * @return
     * @see com.vix.security.dao.IAuthorityDao#findAllMenuAuthorityList()
    
    public List<Authority> findAllMenuAuthorityList() throws Exception {
        StringBuffer hql = new StringBuffer();
        hql.append("select au from Authority au where au.isMenu=:isMenu ");// and au.menuHrefUrl is not null 
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("isMenu", 1);
        
        //HqlTenantIdUtil.handleParamMap4TenantId(param);//不增加tenantId
        HqlTenantIdUtil.handleParamMap4CompanyInnerCode(param);//不处理CompanyInnerCode;
        List<Authority> auList = findAllByHql(hql.toString(), param);
        
        return auList;
    }
     */
    /*
     * (非 Javadoc)
     * <p>Title: findRoleMenuAuthorityByUserId</p>
     * <p>Description: </p>
     * @param userId
     * @return
     * @throws Exception
     * @see com.vix.security.dao.IAuthorityDao#findRoleMenuAuthorityByUserId(java.lang.String)
     */
    @Override
	public List<Authority> findRoleMenuAuthorityByUserId(String userId,String bizType,String tenantId) throws Exception{
    	//PC  PAD MOBILE 用户的登录方式
    	/*String bizType =ContextUtil.getUserMenuContextType();
    	
        StringBuffer hql = new StringBuffer();
        Map<String,Object> param = new HashMap<String,Object>();
        hql.append("select auth from UserAccount account inner join account.roles role inner join role.authoritys auth left join fetch auth.parentAuthority ");
        hql.append(" where auth.authType=:authType and account.id=:userAccountId ");
        param.put("authType", BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_M);
        param.put("userAccountId", userId);
        
        if(StringUtils.isNotEmpty(bizType)){
        	hql.append(" and auth.bizType=:bizType ");
        	param.put("bizType", bizType);
        }
        
        //HqlTenantIdUtil.handleParamMap4TenantId(param);//不增加tenantId
        HqlTenantIdUtil.handleParamMap4CompanyInnerCode(param);//不处理CompanyInnerCode;
        
        List<Authority>  auList = findAllByHql2(hql.toString(), param);
        
        return auList;*/
    	
    	//String bizType =ContextUtil.getUserMenuContextType();
    	StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("SELECT auth.ID,auth.Name, auth.DisplayName, auth.authorityCode,auth.parentAuthorityCode, auth.authType, ");
		sql.append("auth.menuHrefUrl, auth.BizType, auth.ViewPos,auth.TopParentCode,auth.SortOrder,auth.iconClass ");
		//sql.append("  , pa.id parentAuthId ");
		sql.append("  , pa.id parentId ");
		sql.append(" FROM CMN_SEC_AUTHORITY auth INNER JOIN CMN_SEC_ROLE_AUTHORITY sra ON sra.Authority_ID = auth.ID  ");
		sql.append(" INNER JOIN CMN_SEC_ROLE sr ON sr.id = sra.Role_ID  INNER JOIN CMN_SEC_USERACCOUNT_ROLE sur ON sur.Role_ID = sr.id");
		sql.append(" LEFT JOIN CMN_SEC_AUTHORITY pa ON pa.authorityCode = auth.parentAuthorityCode and pa.TENANTID = auth.TENANTID ");
		sql.append(" WHERE IFNULL(sr.StartTime,NOW()) <= NOW() AND IFNULL(sr.EndTime,NOW())>=NOW() ");
		sql.append(" and sur.UserAccount_ID = ? AND auth.BizType = ? AND auth.authType=? ");
		params.add(userId);
		params.add(bizType);
		params.add(BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_M);

		List<Authority> userAllMenuList = queryObjectListBySql(sql.toString(), Authority.class, params.toArray());
    	return userAllMenuList;
    }
    
    @Override
	public List<Authority> findRoleFuncAuthorityByUserId(String userId,String bizType,String tenantId){
    	//String bizType =ContextUtil.getUserMenuContextType();
    	StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("SELECT auth.ID,auth.Name, auth.DisplayName,auth.authorityCode,auth.parentAuthorityCode, auth.authType, ");
		sql.append("auth.menuHrefUrl, auth.BizType, auth.ViewPos,auth.TopParentCode,auth.SortOrder,auth.iconClass ");
		sql.append(" FROM CMN_SEC_AUTHORITY auth INNER JOIN CMN_SEC_ROLE_AUTHORITY sra ON sra.Authority_ID = auth.ID  ");
		sql.append(" INNER JOIN CMN_SEC_ROLE sr ON sr.id = sra.Role_ID  INNER JOIN CMN_SEC_USERACCOUNT_ROLE sur ON sur.Role_ID = sr.id");
		sql.append(" WHERE IFNULL(sr.StartTime,NOW()) <= NOW() AND IFNULL(sr.EndTime,NOW())>=NOW() ");
		sql.append(" and sur.UserAccount_ID = ? AND auth.BizType = ? AND auth.authType=? ");
		params.add(userId);
		params.add(bizType);
		params.add(BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_F);

		List<Authority> userAllFunList = queryObjectListBySql(sql.toString(), Authority.class, params.toArray());
    	return userAllFunList;
    }
    /**
     * 导入权限信息
     * @param voList
     * @throws Exception
    public void saveForImportAuthority(List<AuthorityImpVo> voList)throws Exception{
    	Set<Authority> boList = new HashSet<Authority>();
    	if(voList!=null && !voList.isEmpty()){
    		transferToBo(voList, boList);
    	}
    	//System.out.println("Start:" + boList.size());
		handlerAuthorityTree(boList,null,null);
    }
    
    private void transferToBo(List<AuthorityImpVo> voList,Set<Authority> boList){
    	Map<String,Authority> voMap = new HashMap<String, Authority>();
    	
    	//按照编码分组  记录每个编码的子节点
    	HashMultimap<String, Authority> codeChildMap = HashMultimap.create();
    	
    	List<Authority> boListTmp = new ArrayList<Authority>();
    	for(AuthorityImpVo vo:voList){
    		Authority bo = new Authority();
    		
    		if(StrUtils.isEmpty(vo.getMenuHrefUrl())){
    			vo.setMenuHrefUrl(null);
    		}
    		
    		vo.setAuthorityCode(StrUtils.isNotEmpty(vo.getAuthorityCode())?vo.getAuthorityCode().toLowerCase():null);
    		vo.setParentAuthorityCode(StrUtils.isNotEmpty(vo.getParentAuthorityCode())?vo.getParentAuthorityCode().toLowerCase():null);
    		
    		BeanUtils.copyProperties(vo, bo, new String[]{});
    		
    		voMap.put(bo.getAuthorityCode(), bo);
    		
    		String parentCode = bo.getParentAuthorityCode();

    		if(StrUtils.isNotEmpty(parentCode)){
    			codeChildMap.put(parentCode, bo);//按照父节点分组
    		}
    		
    		boListTmp.add(bo);
    	}
    	
    	
    	for(Authority bo:boListTmp){
    		String authorityCode = bo.getAuthorityCode();
    		String parentCode = bo.getParentAuthorityCode();
    		
    		bo.setSubAuthoritys(codeChildMap.get(authorityCode));
    		
    		bo.setSubAuthoritys(codeChildMap.get(authorityCode));
    		
    		if(StrUtils.isEmpty(parentCode)){
    			boList.add(bo);
    		}
    	}
    }
    
    private void handlerAuthorityTree(Set<Authority> boList,Authority parent,String topAuthorityCode) throws Exception{
		for (Authority bo : boList) {
			String topAuthorityCodeTmp = null;
			//持久化bo
			bo.setParentAuthority(parent);
			
			if(parent!=null){
				//topAuthorityCodeTmp = bo.getTopParentCode();
				topAuthorityCodeTmp = topAuthorityCode;
				bo.setTopParentCode(topAuthorityCode);
				//topAuthorityCodeTmp = bo.getAuthorityCode();
			}else{
				//topAuthorityCodeTmp = topAuthorityCode;
				topAuthorityCodeTmp = bo.getAuthorityCode();
				bo.setTopParentCode(null);
			}
			
			//System.out.println(bo.getName());
			//saveOrUpdate(bo);
			saveOrUpdateOriginal(bo);
			
			Set<Authority> subBo = bo.getSubAuthoritys();
			if(subBo!=null && !subBo.isEmpty()){
				handlerAuthorityTree(subBo,bo,topAuthorityCodeTmp);
			}
			clear();
		}
	}
     */

	/* (non-Javadoc)
	 * @see com.vix.common.security.dao.IAuthorityDao#findRoleMenuAuthorityByUserIdForShop(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Set<Authority> findRoleMenuAuthorityByUserIdForShop(String roleId,String userId, String bizType, String tenantId) throws Exception {

		String compAdminRoleId = organizationDao.findOrgByUserAccountId(SecurityUtil.getCurrentEmpId(), SecurityUtil.getCurrentUserId());

		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("SELECT auth.ID,auth.Name,auth.authorityCode, auth.parentAuthorityCode,auth.SortOrder  ");// .append(ename);
		sql.append(",ra.Role_ID rId, CASE WHEN ra.Role_ID IS NULL THEN 'N' ELSE 'Y' END isCheck ");

		sql.append(",(SELECT COUNT(sub.id) FROM CMN_SEC_AUTHORITY sub ");
		sql.append(" INNER JOIN CMN_SEC_ROLE_AUTHORITY cra2 ON cra2.Role_ID =? and cra2.AUTHORITY_ID = sub.id ");
		params.add(compAdminRoleId);
		sql.append(" WHERE sub.parentAuthorityCode = auth.authorityCode and sub.TENANTID =auth.TENANTID and sub.AuthType = ?  and sub.BizType= ? ) subCount ");
		params.add(BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_M);
		params.add(BizConstant.COMMON_SECURITY_RESTYPE_P);

		sql.append("FROM CMN_SEC_AUTHORITY auth ");
		/*
		 * sql.append(
		 * "INNER JOIN CMN_SEC_ROLE_AUTHORITY cra ON cra.Role_ID =?  and cra.AUTHORITY_ID = auth.id "
		 * ); params.add(compAdminRoleId);
		 */
		sql.append(" INNER JOIN CMN_SEC_ROLE_AUTHORITY ra ON ra.Authority_ID = auth.ID  ");
		sql.append(" INNER JOIN CMN_SEC_ROLE sr ON sr.id = ra.Role_ID  INNER JOIN CMN_SEC_USERACCOUNT_ROLE sur ON sur.Role_ID = sr.id");
		sql.append(" WHERE  auth.TENANTID = ? and auth.AuthType = ?  and auth.BizType= ?  and sur.UserAccount_ID = ? ");
		// sql.append(" and auth.id <= 13628 ");
		params.add(tenantId);
		params.add(BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_M);
		params.add(bizType); // params.add(BizConstant.COMMON_SECURITY_RESTYPE_P);
		params.add(userId);
		List<Authority> allMenuList = queryObjectListBySql(sql.toString(), Authority.class, params.toArray());

		Map<String, Authority> treeMap = new ConcurrentHashMap<String, Authority>();
		Set<Authority> resSet = new TreeSet<Authority>();

		for (Authority au : allMenuList) {
			au.setCheckId(au.getId());
			// isCheck
			// parentId
			// isParent Long subCount = au.getSubCount();
			// System.out.println(au.getParentId());
			treeMap.put(au.getAuthorityCode(), au);
		}

		for (Map.Entry<String, Authority> entry : treeMap.entrySet()) {
			// Long key = entry.getKey();
			Authority node = entry.getValue();

			if (StringUtils.isEmpty(node.getParentAuthorityCode())) {
				resSet.add(node);
			} else {
				String parentIdCode = node.getParentAuthorityCode();
				if (treeMap.containsKey(parentIdCode)) {
					treeMap.get(parentIdCode).addChildren(node);
				}
			}
		}
		return resSet;
    }
    
}
