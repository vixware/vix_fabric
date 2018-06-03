package com.vix.common.security.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.vix.common.security.dao.IAuthorityDao;
import com.vix.common.security.entity.Authority;
import com.vix.common.security.entity.Resource;
import com.vix.common.security.hql.AuthorityHqlProvider;
import com.vix.common.security.service.IAuthorityService;
import com.vix.common.security.tree.TreeNode;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.BizConstant;
import com.vix.core.exception.BizException;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

@Service("authorityService")
//@Transactional
public class AuthorityServiceImpl extends BaseHibernateServiceImpl implements IAuthorityService {

    @javax.annotation.Resource(name = "authorityDao")
    private IAuthorityDao authorityDao;

    //@javax.annotation.Resource(name="authorityHqlProvider")
	//private AuthorityHqlProvider authorityHqlProvider;
    
    //@javax.annotation.Resource(name="organizationDao")
    //private IOrganizationDao organizationDao;
    
    private static final long serialVersionUID = 1L;

    /* (non-Javadoc)
     * @see com.vix.common.security.service.IAuthorityService#findAuthorityPager(com.vix.core.web.Pager, java.util.Map)
     */
    @Override
	public Pager findAuthorityPager(Pager pager,Map<String,Object> params)throws Exception{
    	AuthorityHqlProvider ahp = new AuthorityHqlProvider();
    	StringBuilder hql = ahp.findAuthorityPagerList(params, pager);
		
		//HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		
		Pager resPager = authorityDao.findPager2ByHql(pager, ahp.entityAsName(), hql.toString(), params);
    	return resPager;
    }
    
    @Override
	@Transactional
    public void saveAuthority(String addResourceIds, String deleteResourceIds,Authority entityForm)throws Exception{
    	Authority authorityTmp = null;
		Boolean isEdit = false;
		if(StrUtils.isNotEmpty(entityForm.getId())){
			authorityTmp = authorityDao.findEntityById(Authority.class, entityForm.getId());
			isEdit = true;
		}
		Date now = new Date();
		if(authorityTmp == null){
			authorityTmp = new Authority();
		}
		
		//验证
		/*if(hasUa){
			throw new BizException("账号重复，请重新填写！");
		}*/
		
		BeanUtils.copyProperties(entityForm, authorityTmp, new String[]{"roles"});//"employee",
		if(authorityTmp.getId()==null){
			authorityTmp.setCreateTime(now);
		}else{
			authorityTmp.setUpdateTime(now);
		}
		
		Set<Resource> rSet=authorityTmp.getResources();
		if( rSet== null){
			rSet= new HashSet<Resource>();
		}
		
		if(StringUtils.isNotEmpty(addResourceIds)){
			for(String idS : addResourceIds.split(",")){
				if(StringUtils.isNotEmpty(idS)){
					Resource r = authorityDao.findEntityById(Resource.class, idS);
					rSet.add(r);
				}
			}
		}
		
		if(StringUtils.isNotEmpty(deleteResourceIds)){
			for(String idS : deleteResourceIds.split(",")){
				if(StringUtils.isNotEmpty(idS)){
					Resource r = authorityDao.findEntityById(Resource.class, idS);
					rSet.remove(r);
				}
			}
		}
		
		authorityTmp.setResources(rSet);
		//authorityDao.merge(authorityTmp);
		//authorityDao.saveOrUpdateOriginal(authorityTmp);
		authorityDao.merge(authorityTmp);
		//return userAccount;
    }
    
    @Override
	@Transactional(readOnly=true)
    public List<Authority> findSubAuthority(String id,String bizType,String authType,String tenantId)throws Exception{
    	/*StringBuilder sb = new StringBuilder();
    	Map<String,Object> params = new HashMap<String, Object>();
    	//HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
    	HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
    	
    	sb.append("select au from Authority au where ");
    	if(id!=null && id>0){
    		sb.append(" au.parentAuthority.id=:parentId and au.bizType=:bizType ");
    		params.put("parentId", id);
    		params.put("bizType", bizType);
    	}else{
    		sb.append(" au.parentAuthority.id is null  and au.bizType=:bizType ");
    		params.put("bizType", bizType);
    	}
    	
    	if(StringUtils.isNotEmpty(authType)){
    		sb.append(" and au.authType = :authType ");
    		params.put("authType", authType);
    	}
    	sb.append(" order by au.sortOrder asc ");
    	List<Authority> auList = authorityDao.findAllByHql2(sb.toString(), params);
    	return auList;*/
    	StringBuilder sb = new StringBuilder();
    	List<Object> params = new LinkedList<Object>();
    	
    	sb.append("SELECT auth.ID,auth.Name, auth.DisplayName, auth.authorityCode, auth.parentAuthorityCode, auth.authType, ");
		sb.append("auth.menuHrefUrl, auth.BizType, auth.ViewPos,auth.TopParentCode, auth.SortOrder ");
    	sb.append(",(SELECT COUNT(sub.id) FROM CMN_SEC_AUTHORITY sub WHERE sub.TENANTID = ? and sub.BizType= ?  and sub.parentAuthorityCode = auth.authorityCode ");
		params.add(tenantId);
    	params.add(bizType);
		//params.add(oa.getAuthorityCode());
		if(StringUtils.isNotEmpty(authType)){
    		sb.append(" and sub.AuthType = ? ");
    		params.add(authType);
    	}
    	sb.append(" ) subCount  ");
    	
    	sb.append("FROM CMN_SEC_AUTHORITY auth where auth.TENANTID = ? ");
    	params.add(tenantId);
    	if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(id!=null && id>0){
    		//fei根节点
        	sb.append(" and auth.parentAuthorityCode = ? ");
        	Authority oa = findEntityById(Authority.class, id);
    		params.add(oa.getAuthorityCode());
    	}else{
        	sb.append("  and auth.parentAuthorityCode is null ");
    	}
    	
    	sb.append(" and auth.bizType= ? ");
    	params.add(bizType);
    	
    	if(StringUtils.isNotEmpty(authType)){
    		sb.append(" and auth.authType = ? ");
    		params.add(authType);
    	}
    	sb.append(" order by auth.sortOrder asc ");
    	List<Authority> auList = authorityDao.queryObjectListBySql(sb.toString(), Authority.class, params.toArray());
    	return auList;
    }
    
    @Override
	public Authority findParentAuthority(String id,String tenantId)throws Exception{
    	Authority au = findEntityById(Authority.class, id);
    	
    	StringBuilder sb = new StringBuilder();
    	Map<String,Object> params = new HashMap<String, Object>();
    	sb.append("select au from Authority au where au.bizType=:bizType ");
    	params.put("bizType",  au.getBizType());
    	
    	String parentAuthCode = au.getParentAuthorityCode();
    	if(StringUtils.isNotEmpty(parentAuthCode)){
    		sb.append(" and au.authorityCode = :parentAuthCode ");
    		params.put("parentAuthCode", parentAuthCode);
    	}else{
    		sb.append(" and 1=2 ");
    	}
    	
    	if(StringUtils.isNotEmpty(tenantId)){
    		sb.append(" and au.tenantId = :tenantId ");
    		params.put("tenantId", tenantId);
    	}
    	
    	List<Authority> auList = authorityDao.findAllByHql2NoTenantId(sb.toString(), params);
    	if(auList==null){
    		return null;
    	}else{
    		if(auList.isEmpty()){
    			return null;
    		}else if(auList.size()==1){
    			return auList.get(0);
    		}else{
    			throw new BizException("数据不唯一！");
    		}
    	}
    }
    
    public Authority findAuthorityByCode(String authorityCode,String bizType, String tenantId) throws Exception{
		StringBuilder sb = new StringBuilder();
    	Map<String,Object> params = new HashMap<String, Object>();
    	sb.append("select au from Authority au where au.authorityCode = :authorityCode and au.bizType=:bizType ");
    	params.put("authorityCode", authorityCode);
    	params.put("bizType", bizType);
    	
    	if(StringUtils.isNotEmpty(tenantId)){
    		sb.append(" and au.tenantId = :tenantId ");
    		params.put("tenantId", tenantId);
    	}
    	
    	List<Authority> auList = authorityDao.findAllByHql2NoTenantId(sb.toString(), params);
    	if(auList==null){
    		return null;
    	}else{
    		if(auList.isEmpty()){
    			return null;
    		}else if(auList.size()==1){
    			return auList.get(0);
    		}else{
    			throw new BizException("数据不唯一！");
    		}
    	}
	}
    
    /*@Transactional(readOnly=true)
    public List<Authority> findSubAuthorityWithRole(Long roleId,String bizType)throws Exception{
    	Role currentRole = authorityDao.findEntityById(Role.class, roleId);
    	
    	StringBuffer hql = new StringBuffer();
    	Map<String,Object> param = new HashMap<String, Object>();
    	hql.append("select distinct a from Authority a ");
    	//hql.append(" left join fetch a.parentAuthority left join fetch a.subAuthoritys ");
    	hql.append(" left join fetch a.roles left join fetch a.resources ");
    	
    	hql.append(" where a.parentAuthority.id is null  and  a.bizType = :bizType order by a.sortOrder ");
    	param.put("bizType", bizType);
    		
    	//List<Authority> auList = authorityDao.findAllByHql("authorityRoleFilter",filterParam,hql.toString(), param);
    	
    	//HqlTenantIdUtil.handleParamMap4TenantId(param);//不增加tenantId
    	HqlTenantIdUtil.handleParamMap4CompanyInnerCode(param);//不处理CompanyInnerCode;
    	
    	List<Authority> auList = authorityDao.findAllByHql2(hql.toString(), param);
    	//List<Authority> auList = authorityDao.findAllByHql(hql.toString(), param);
    	if(auList!=null){
    		for(Authority au:auList){
    			au.setTreeId(au.getId());
    			
    			au.setCheckId(au.getId());
    			if(au.getRoles()!=null && au.getRoles().contains(currentRole) ){
    				au.setIsCheck("Y");
    			}else{
    				au.setIsCheck("N");
    			}
    			
    			//Authority parent = au.getParentAuthority();
    			
    			authorityDao.evict(au);
    			au.setParentAuthority(null);
    			au.setSubAuthoritys(null);
    			au.setRoles(null);
    			au.setResources(null);
    		}
    	}
    	return auList;
    	
    }*/
    
    /*
     * (非 Javadoc) <p>Title: findMenuAll</p> <p>Description: </p>
     * 
     * @return
     * 
     * @see com.vix.security.service.IAuthorityService#findMenuAll()
     */
    @Override
	@Transactional(readOnly=true)
    public Set<TreeNode> findMenuAll() throws Exception {
        //List<Authority> auList = authorityDao.findAllMenuAuthorityList();

        Map<String,TreeNode> treeMap = new HashMap<String, TreeNode>();
        Set<TreeNode> resSet = new TreeSet<TreeNode>();

       /* 
        for (Iterator<Authority> it = auList.iterator(); it.hasNext();) {
            Authority at = it.next();
            
            Authority parentObj = at.getParentAuthority();
            TreeNode node = new TreeNode(at.getId(), parentObj==null?-1L:parentObj.getId(), at.getName(),at.getDisplayName(),at.getId(), at.getMenuHrefUrl());
            treeMap.put(node.getId(), node);
            //at.getId(), parentObj==null?-1:parentObj.getId(), parentObj,at.getName(),at.getDisplayName(),
        }*/
        
        Set<Map.Entry<String,TreeNode>> treeMapEntrySet = treeMap.entrySet();
        for(Iterator<Map.Entry<String,TreeNode>> it = treeMapEntrySet.iterator(); it.hasNext();){
            //TreeNode node = (TreeNode) ((Map.Entry) it.next()).getValue();
            TreeNode node = it.next().getValue();
            if(node.getParentId().endsWith("-1")){
                resSet.add(node);
            }else{
                treeMap.get(node.getParentId()).addChildren(node);
            }
        }
        return resSet;
    }
    
    /**
     * 正常的读取菜单方式
     * <p>Title: findMenuByUser</p>
     * <p>Description: </p>
     * @param userId
     * @return
     * @throws Exception
     * @see com.vix.security.service.IAuthorityService#findMenuByUser(java.lang.String)
     */
    @Override
	@Transactional(readOnly=true)
    public Set<TreeNode> findMenuByUser(String userId,String bizType) throws Exception{
        List<Authority> auList = authorityDao.findRoleMenuAuthorityByUserId(userId,bizType,SecurityUtil.getCurrentUserTenantId());
        
        Map<String,TreeNode> treeMap = new HashMap<String, TreeNode>();
        TreeSet<TreeNode> resSet = new TreeSet<TreeNode>();
        //Set<TreeNode> resSet = new HashSet<TreeNode>();

        
        for (Iterator<Authority> it = auList.iterator(); it.hasNext();) {
            Authority at = it.next();
            
            String parentAuthId = at.getParentId();
           /* if(parentObj==null){
            	System.out.println(at.getDisplayName());
            	System.out.println(at.getId());
            	
            }*/
            TreeNode node = new TreeNode(at.getId(),StringUtils.isEmpty(parentAuthId)?"-1":parentAuthId, at.getName(),at.getDisplayName(),at.getAuthorityCode(),  at.getSortOrder()==null?0:at.getSortOrder(), at.getMenuHrefUrl(),at.getIconClass());
            treeMap.put(node.getId(), node);
            //at.getId(), parentObj==null?-1:parentObj.getId(), parentObj,at.getName(),at.getDisplayName(),
            //System.out.println(node.getId() +"         "+node.getDisplayName() + "      " + node.getParentId());
        }
        Set<Map.Entry<String,TreeNode>> treeMapEntrySet = treeMap.entrySet();
        for(Iterator<Map.Entry<String,TreeNode>> it = treeMapEntrySet.iterator(); it.hasNext();){
            //TreeNode node = (TreeNode) ((Map.Entry) it.next()).getValue();
            TreeNode node = it.next().getValue();
            if(node.getParentId().equals("-1")){
                resSet.add(node);
                //printTree(node);
                //System.out.println("添加根节点：" + node.getId() +" "+node.getDisplayName());
            }else{
                String parentIdTemp = node.getParentId(); 
                if(treeMap.containsKey(parentIdTemp)){
                	//System.out.println("添加子节点：" + treeMap.get(parentIdTemp).getDisplayName() +"        " +node.getDisplayName());
                    treeMap.get(parentIdTemp).addChildren(node);
                }
            }
        }
        
      /*for(TreeNode tree:resSet){
        	System.out.println("C  Parent: " + tree.getId()+"      " + tree.getDisplayName() +" "+tree.getMenuUrl() +tree.getIconClass());
        }*/
        return resSet;
    }
    
    private void printTree(TreeNode tree){
    	//System.out.println("Parent: " + tree.getId()+"      " + tree.getDisplayName());
    	
    	TreeSet<TreeNode> childTmp = tree.getChildren();
    	
    	if(childTmp!=null && !childTmp.isEmpty()){
    		for(TreeNode ch:childTmp){
    			printTree(tree);
    		}
    	}
    }
    
    /**
     * 读取二级菜单到一级菜单方式
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
	@Transactional(readOnly=true)
    public Set<TreeNode> findMenuByUser2(String userId,String bizType) throws Exception{
        List<Authority> auList = authorityDao.findRoleMenuAuthorityByUserId(userId,bizType,SecurityUtil.getCurrentUserTenantId());
        
        if(auList!=null){
        	for(Authority a:auList){
            	String viewPos = a.getViewPos();
            	if( StringUtils.isNotEmpty(viewPos) && viewPos.equalsIgnoreCase(BizConstant.COMMON_SECURITY_USERMENUTYPE_U)){
            		//a.setParentAuthority(null);
            		a.setParentId(null);
            	}
            }
        }
        
        Map<String,TreeNode> treeMap = new HashMap<String, TreeNode>();
        Set<TreeNode> resSet = new TreeSet<TreeNode>();

        for (Iterator<Authority> it = auList.iterator(); it.hasNext();) {
            Authority at = it.next();
            
            //Authority parentObj = at.getParentAuthority();
            String parentAuthId = at.getParentId();
            TreeNode node = new TreeNode(at.getId(), StringUtils.isEmpty(parentAuthId)?"-1":parentAuthId, at.getName(),at.getDisplayName(), at.getAuthorityCode(),at.getSortOrder()==null?0:at.getSortOrder(), at.getMenuHrefUrl(),at.getIconClass());
            treeMap.put(node.getId(), node);
            
           /* if( StringUtils.isNotEmpty(viewPos) && viewPos.equalsIgnoreCase(BizConstant.COMMON_SECURITY_USERMENUTYPE_U)){
                //TreeNode node = new TreeNode(at.getId(), parentObj==null?-1L:parentObj.getId(), at.getName(),at.getDisplayName(),at.getId(), at.getMenuHrefUrl());
            	TreeNode node = new TreeNode(at.getId(), -1L, at.getName(),at.getDisplayName(),at.getId(), at.getMenuHrefUrl());
            	treeMap.put(node.getId(), node);
                //把所有升级到一级菜单的权限读取出来
            }*/
            //at.getId(), parentObj==null?-1:parentObj.getId(), parentObj,at.getName(),at.getDisplayName(),
        }
        
        
        Set<Map.Entry<String,TreeNode>> treeMapEntrySet = treeMap.entrySet();
        for(Iterator<Map.Entry<String,TreeNode>> it = treeMapEntrySet.iterator(); it.hasNext();){
            //TreeNode node = (TreeNode) ((Map.Entry) it.next()).getValue();
            TreeNode node = it.next().getValue();
            if(node.getParentId().equals("-1")){
                resSet.add(node);
            }else{
                String parentIdTemp = node.getParentId(); 
                if(treeMap.containsKey(parentIdTemp)){
                    treeMap.get(parentIdTemp).addChildren(node);
                }
            }
        }
       /* 
        for(TreeNode tree:resSet){
        	System.out.println("U  Parent: " + tree.getId()+"      " + tree.getDisplayName());
        }*/
        return resSet;
    }
    
    
    @Override
	public Set<String> findAuthFunCodeSetByUserAccount(String userId,String bizType,String tenantId){
    	List<Authority> allUserFunList = authorityDao.findRoleFuncAuthorityByUserId(userId, bizType,tenantId);
    	Builder<String> iset = ImmutableSet.builder();
    	if(allUserFunList!=null){
    		for(Authority au : allUserFunList){
    			iset.add(au.getAuthorityCode());
    		}
    	}
    	return iset.build();
    }

    /* (non-Javadoc)
     * @see com.vix.common.security.service.IAuthorityService#findAuthorityWithRole(java.lang.String, java.lang.String)
     */
    /*@Transactional(readOnly=true)
    public List<Authority> findAuthorityWithRole(Long roleId,Long authorityId,String bizType)throws Exception{
    	StringBuffer hql = new StringBuffer();
    	Map<String,Object> param = new HashMap<String, Object>();
    	hql.append("select distinct a from Authority a ");
    	hql.append(" left join fetch a.roles left join fetch a.resources ");
    	hql.append(" left join  a.roles role with role.id = :roleId ");
    	param.put("roleId", roleId);
    	
    	hql.append(" where a.bizType = :bizType ");
    	param.put("bizType", bizType);
    	if(authorityId==null){
    		hql.append(" and a.parentAuthority is null ");
    	}else{
    		hql.append(" and a.parentAuthority.id = :parentId ");
    		param.put("parentId", authorityId);
    	}
    	
    	Map<String,Object> filterParam = new HashMap<String, Object>();
    	filterParam.put("filterRoleId", roleId);
    	
    	//List<Authority> auList = authorityDao.findAllByHql("authorityRoleFilter",filterParam,hql.toString(), param);
    	
    	//HqlTenantIdUtil.handleParamMap4TenantId(param);//不增加tenantId
    	HqlTenantIdUtil.handleParamMap4CompanyInnerCode(param);//不处理CompanyInnerCode;
    	
    	List<Authority> auList = authorityDao.findAllByHql2(hql.toString(), param);
    	//List<Authority> auList = authorityDao.findAllByHql(hql.toString(), param);
    	return auList;
    }
    */
   /* 
    //@Transactional(readOnly=true)
    private List<Authority> findAllAuthorityWithRole(Long roleId,String topParentCode,String bizType)throws Exception{
    	StringBuffer hql = new StringBuffer();
    	Map<String,Object> param = new HashMap<String, Object>();
    	hql.append("select distinct a from Authority a ");
    	hql.append(" left join fetch a.parentAuthority left join fetch a.subAuthoritys ");
    	hql.append(" left join fetch a.roles left join fetch a.resources ");
    	hql.append(" left join  a.roles role with role.id = :roleId ");
    	param.put("roleId", roleId);
    	
    	hql.append(" where a.bizType = :bizType ");
    	param.put("bizType", bizType);
    		
    	hql.append(" and a.topParentCode = :topParentCode ");
    	param.put("topParentCode", topParentCode);
    	
    	
    	//List<Authority> auList = authorityDao.findAllByHql("authorityRoleFilter",filterParam,hql.toString(), param);
    	
    	//HqlTenantIdUtil.handleParamMap4TenantId(param);//不增加tenantId
    	HqlTenantIdUtil.handleParamMap4CompanyInnerCode(param);//不处理CompanyInnerCode;
    	
    	List<Authority> auList = authorityDao.findAllByHql2(hql.toString(), param);
    	//List<Authority> auList = authorityDao.findAllByHql(hql.toString(), param);
    	return auList;
    }
   
    private List<Authority> findAllAuthorityWithRoleByCompSuperAdmin(Long roleId,String topParentCode,String bizType,Long compSuperAdminRoleId)throws Exception{
    	StringBuffer hql = new StringBuffer();
    	Map<String,Object> param = new HashMap<String, Object>();
    	hql.append("select distinct a from Authority a ");
    	hql.append(" left join a.roles role left join a.resources ");
    	hql.append(" where a.bizType = :bizType ");
    	param.put("bizType", bizType);
    	
    	hql.append(" and role.id = :compSuperRoleId ");
    	param.put("compSuperRoleId", compSuperAdminRoleId);
    	
    	hql.append(" and a.topParentCode = :topParentCode ");
    	param.put("topParentCode", topParentCode);
    	
    	//List<Authority> auList = authorityDao.findAllByHql("authorityRoleFilter",filterParam,hql.toString(), param);
    	
    	//HqlTenantIdUtil.handleParamMap4TenantId(param);//不增加tenantId
    	HqlTenantIdUtil.handleParamMap4CompanyInnerCode(param);//不处理CompanyInnerCode;
    	
    	List<Authority> auList = authorityDao.findAllByHql2(hql.toString(), param);
    	//List<Authority> auList = authorityDao.findAllByHql(hql.toString(), param);
    	return auList;
    }
	*/
    /*
    public Set<Authority> findAllAuthorityWithRoleForTreeGrid(Long roleId,Long authorityId,String bizType)throws Exception{
    	
    	Role currentRole = authorityDao.findEntityById(Role.class, roleId);
    	Authority topAuthority = authorityDao.findEntityById(Authority.class, authorityId);
    	String authorityCode = topAuthority.getAuthorityCode();
    	
    	//查询出所有权限
    	List<Authority> auList = findAllAuthorityWithRole(roleId, authorityCode, bizType);
    	
    	
    	Map<Long,Authority> treeMap = new HashMap<Long, Authority>();
    	Set<Authority> resSet = new TreeSet<Authority>();

    	
        for (Authority au:auList) {
        	au.setCheckId(au.getId());
			if(au.getRoles()!=null && au.getRoles().contains(currentRole) ){
				au.setIsCheck("Y");
			}else{
				au.setIsCheck("N");
			}
			
			Authority parent = au.getParentAuthority();//肯定不为空
			Long parentIdTmp = parent.getId();
			//au.setParentId(parent==null?0L:parent.getId());
			if(parentIdTmp.equals(authorityId)){
				au.setParentId(null);
			}else{
				au.setParentId(parentIdTmp);
			}
			
			if(au.getSubAuthoritys()!=null &&au.getSubAuthoritys().size()>0){
				au.setState("closed");
			}else{
				au.setState("open");
			}
        	//System.out.println(au.getParentId());
        	treeMap.put(au.getId(), au);
        }
        
        
        Set<Map.Entry<Long,Authority>> treeMapEntrySet = treeMap.entrySet();
        for(Iterator<Map.Entry<Long,Authority>> it = treeMapEntrySet.iterator(); it.hasNext();){
            //TreeNode node = (TreeNode) ((Map.Entry) it.next()).getValue();
        	Authority node = it.next().getValue();
            if(node.getParentId()==null){
                resSet.add(node);
            }else{
                Long parentIdTemp = node.getParentId(); 
                if(treeMap.containsKey(parentIdTemp)){
                    treeMap.get(parentIdTemp).addChildren(node);
                }
            }
        }
        
        return resSet;
    	
    }
    */
    
    /* (non-Javadoc)
     * @see com.vix.common.security.service.IAuthorityService#saveForAuthority(java.lang.Long, java.util.List)
     
    public void saveForAuthority(Long roleId,List<Long> authorityIdList)throws Exception{
    	
    	StringBuffer sql = new StringBuffer();
    	Map<String,Object> params = new HashMap<String, Object>();
    	sql.append("delete from CMN_SEC_ROLE_AUTHORITY where Role_ID=:roleId");
    	params.put("roleId", roleId);
    	//authorityDao.jdbcBatchUpdate(sql.toString(), roleId);
    	authorityDao.batchUpdateBySql(sql.toString(), params);
    	params.clear();
    	
    	sql.setLength(0);
    	sql.append("insert into CMN_SEC_ROLE_AUTHORITY(Role_ID,Authority_ID) values (?,?)");
    	List<Object[]> saveArrayParam = new LinkedList<Object[]>();
    	if(authorityIdList!=null){
    		for(Long aid:authorityIdList){
    			saveArrayParam.add(new Object[]{roleId,aid});
        	}
    	}
    	
    	//authorityDao.jdbcBatchUpdate(sql.toString(), saveArrayParam);
    	authorityDao.batchUpdateBySql(sql.toString(), saveArrayParam);
    	//if(1==1){
    		//System.out.println(1/0);
    		//throw new Exception("aaaaaaaa");
    	//}
    }
    */
    /*
    public void saveForAuthority(Long roleId,String bizType,Long topParentAuId,String topAuthorityIds,List<Long> authorityIdList)throws Exception{
    	StringBuffer sql = new StringBuffer();
    	Map<String,Object> params = new HashMap<String, Object>();
    	//1、保存左侧的节点
    	if(StringUtils.isNotEmpty(topAuthorityIds) ){
    		String[] topAuthorityIdsArray = topAuthorityIds.split("\\,");
    		sql.append("delete from CMN_SEC_ROLE_AUTHORITY where Role_ID=:roleId ");
        	params.put("roleId", roleId);
        	sql.append(" and Authority_ID in (select a.id from cmn_sec_authority a where a.Parent_id is null  and a.BizType=:bizType )");//and a.authType='M'
        	params.put("bizType", bizType);
        	//authorityDao.jdbcBatchUpdate(sql.toString(), roleId);
        	authorityDao.batchUpdateBySql(sql.toString(), params);
        	
        	
        	sql.setLength(0);
        	params.clear();
        	sql.append("insert into CMN_SEC_ROLE_AUTHORITY(Role_ID,Authority_ID) values (?,?)");
        	List<Object[]> saveArrayParam = new LinkedList<Object[]>();
    		for(String aid:topAuthorityIdsArray){
    			saveArrayParam.add(new Object[]{roleId,Long.parseLong(aid)});
        	}
        	
        	//authorityDao.jdbcBatchUpdate(sql.toString(), saveArrayParam);
        	authorityDao.batchUpdateBySql(sql.toString(), saveArrayParam);
    	}else{
    		sql.append("delete from CMN_SEC_ROLE_AUTHORITY where Role_ID=:roleId ");
        	params.put("roleId", roleId);
        	//authorityDao.jdbcBatchUpdate(sql.toString(), roleId);
        	authorityDao.batchUpdateBySql(sql.toString(), params);
    	}
    	
    	//2、保存 右侧的节点
    	sql.setLength(0);
    	params.clear();
    	if(topParentAuId!=null){
    		Authority topAu = authorityDao.findEntityById(Authority.class, topParentAuId);
        	sql.append("delete from CMN_SEC_ROLE_AUTHORITY where Role_ID=:roleId ");
        	params.put("roleId", roleId);
        	sql.append(" and Authority_ID in (select a.id from cmn_sec_authority a where a.TopParentCode = :topParentCode )");//and a.authType='M'
        	params.put("topParentCode", topAu.getAuthorityCode());
        	//authorityDao.jdbcBatchUpdate(sql.toString(), roleId);
        	authorityDao.batchUpdateBySql(sql.toString(), params);
    	}
    	
        if(authorityIdList!=null){
        	sql.setLength(0);
        	params.clear();
        	sql.append("insert into CMN_SEC_ROLE_AUTHORITY(Role_ID,Authority_ID) values (?,?)");
        	List<Object[]> saveArrayParam = new LinkedList<Object[]>();
        	if(authorityIdList!=null){
        		for(Long aid:authorityIdList){
        			saveArrayParam.add(new Object[]{roleId,aid});
            	}
        	}
        	
        	//authorityDao.jdbcBatchUpdate(sql.toString(), saveArrayParam);
        	authorityDao.batchUpdateBySql(sql.toString(), saveArrayParam);
        	
        }
    	
    }
    */
    /**
     * @return
     * @throws Exception
    
    @Transactional(readOnly=true)
    public List<Authority> findAllAuthorityByType(String type) throws Exception{
		StringBuilder hql = new StringBuilder();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("authType", type);
		
		hql.append("select authority from ");
		hql.append(Authority.class.getName()).append(" authority ");
		hql.append(" left join fetch authority.roles where authority.authType=:authType");
		
		//HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		
		//List<Authority> resList = authorityDao.findAllByHql2NoTenantId(hql.toString(),params);
		List<Authority> resList = authorityDao.findAllByHql2(hql.toString(),params);
		return resList;
	} */
    /**
    @Transactional(readOnly=true)
    public List<Authority> findAllAuthority() throws Exception{
    	StringBuilder hql = new StringBuilder();
		Map<String,Object> params = new HashMap<String, Object>();
		
		//HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		
		hql.append("select authority from ");
		hql.append(Authority.class.getName()).append(" authority ");
		hql.append(" left join fetch authority.roles where 1=1 ");
		
		//List<Authority> resList = authorityDao.findAllByHql2NoTenantId(hql.toString(), params);
		List<Authority> resList = authorityDao.findAllByHql2(hql.toString(), params);
		return resList;
	}*/
    
}
