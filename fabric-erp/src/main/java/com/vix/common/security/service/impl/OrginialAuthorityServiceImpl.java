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

import com.vix.common.orginialMeta.entity.OrginialAuthority;
import com.vix.common.orginialMeta.entity.OrginialResource;
import com.vix.common.security.dao.IOrginialAuthorityDao;
import com.vix.common.security.hql.OrginialAuthorityHqlProvider;
import com.vix.common.security.service.IOrginialAuthorityService;
import com.vix.common.security.tree.TreeNode;
import com.vix.common.security.vo.OrginialAuthorityImpVo;
import com.vix.core.exception.BizException;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

@Service("orginialAuthorityService")
// @Transactional
public class OrginialAuthorityServiceImpl extends BaseHibernateServiceImpl implements IOrginialAuthorityService {

	@javax.annotation.Resource(name = "orginialAuthorityDao")
	private IOrginialAuthorityDao authorityDao;
	// @javax.annotation.Resource(name = "organizationDao")
	// private IOrganizationDao organizationDao;

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vix.common.security.service.IAuthorityService#findAuthorityPager(com.
	 * vix.core.web.Pager, java.util.Map)
	 */
	@Override
	public Pager findOrginialAuthorityPager(Pager pager, Map<String, Object> params) throws Exception {
		OrginialAuthorityHqlProvider oahp = new OrginialAuthorityHqlProvider();
		StringBuilder hql = oahp.findOrginialAuthorityPagerList(params, pager);

		HqlTenantIdUtil.handleParamMap4TenantId(params);// 不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);// 不处理CompanyInnerCode;

		Pager resPager = authorityDao.findPager2ByHql(pager, oahp.entityAsName(), hql.toString(), params);
		return resPager;
	}

	@Override
	@Transactional
	public void saveOrginialAuthority(String addResourceIds, String deleteResourceIds, OrginialAuthority entityForm) throws Exception {
		OrginialAuthority authorityTmp = null;
		if (StrUtils.isNotEmpty(entityForm.getId())) {
			authorityTmp = authorityDao.findEntityById(OrginialAuthority.class, entityForm.getId());
		}
		Date now = new Date();
		if (authorityTmp == null) {
			authorityTmp = new OrginialAuthority();
		}

		// 验证
		/*
		 * if(hasUa){ throw new BizException("账号重复，请重新填写！"); }
		 */

		BeanUtils.copyProperties(entityForm, authorityTmp, new String[]{"resources", "industryManagementModules"});// "employee",
		if (authorityTmp.getId() == null) {
			authorityTmp.setCreateTime(now);
		} else {
			authorityTmp.setUpdateTime(now);
		}

		Set<OrginialResource> rSet = authorityTmp.getResources();
		if (rSet == null) {
			rSet = new HashSet<OrginialResource>();
		}

		if (StringUtils.isNotEmpty(addResourceIds)) {
			for (String idS : addResourceIds.split(",")) {
				if (StringUtils.isNotEmpty(idS)) {
					OrginialResource r = authorityDao.findEntityById(OrginialResource.class, idS);
					rSet.add(r);
				}
			}
		}

		if (StringUtils.isNotEmpty(deleteResourceIds)) {
			for (String idS : deleteResourceIds.split(",")) {
				if (StringUtils.isNotEmpty(idS)) {
					OrginialResource r = authorityDao.findEntityById(OrginialResource.class, idS);
					rSet.remove(r);
				}
			}
		}

		authorityTmp.setResources(rSet);
		// authorityDao.merge(authorityTmp);
		// authorityDao.saveOrUpdateOriginal(authorityTmp);
		authorityDao.mergeOriginal(authorityTmp);
		// return userAccount;
		authorityDao.flush();

		/*
		 * //暂时注销 DB 20150411 String authorityCode =
		 * authorityTmp.getAuthorityCode(); String bizType =
		 * authorityTmp.getBizType(); String authType =
		 * authorityTmp.getAuthType();
		 * 
		 * StringBuilder sql = new StringBuilder(); List<Object> sqlParam = new
		 * LinkedList<Object>(); //同步该菜单数据到所有的承租户 //1 查询所有承租户
		 * List<BaseOrganization> orgList = authorityDao.findAllOrg();
		 * if(orgList!=null && !orgList.isEmpty()){ for(BaseOrganization
		 * org:orgList){ String tenantId = org.getTenantId();
		 * if(StringUtils.isEmpty(tenantId)){ throw new
		 * BizException("存在承租户为空的数据！"); } //2 分别对每个承租户下的菜单进行查询 是否已经有此编码的菜单 sql.
		 * append("select count(auth.ID) from CMN_SEC_AUTHORITY auth where auth.authorityCode=? and auth.TENANTID=? AND auth.BizType = ? AND auth.authType=?  "
		 * ); sqlParam.add(authorityCode); sqlParam.add(tenantId);
		 * sqlParam.add(bizType); sqlParam.add(authType); Integer count =
		 * authorityDao.queryForObject(sql.toString(),Integer.class,
		 * sqlParam.toArray()); sql.setLength(0);sqlParam.clear();
		 * 
		 * if(count>1){ throw new BizException("菜单数据存在逻辑错误，不能进行操作！"); }
		 * 
		 * if(count==1){ //2.1 有此菜单则进行update
		 * sql.append(" update CMN_SEC_AUTHORITY auth "); sql.
		 * append(" set auth.name = ?,auth.displayName=?,auth.menuHrefUrl=?, auth.sortOrder=?, auth.viewPos=?,auth.memo=? "
		 * ); sql.
		 * append(" where auth.authorityCode=? and auth.TENANTID=? AND auth.BizType = ? AND auth.authType=? "
		 * ); sqlParam.add(authorityTmp.getName());sqlParam.add(authorityTmp.
		 * getDisplayName());
		 * sqlParam.add(authorityTmp.getMenuHrefUrl());sqlParam.add(authorityTmp
		 * .getSortOrder());
		 * sqlParam.add(authorityTmp.getViewPos());sqlParam.add(authorityTmp.
		 * getMemo());
		 * sqlParam.add(authorityCode);sqlParam.add(tenantId);sqlParam.add(
		 * bizType);sqlParam.add(authType);
		 * 
		 * authorityDao.jdbcBatchUpdate(sql.toString(), sqlParam.toArray());
		 * sql.setLength(0); sqlParam.clear(); }else{ //2.2 没有此菜单则进行insert //2.1
		 * 有此菜单则进行update sql.append(" INSERT INTO cmn_sec_authority ("); sql.
		 * append(" Name,AuthorityCode,ParentAuthorityCode,DisplayName,Memo,AuthType,"
		 * ); sql.
		 * append(" MenuHrefUrl,SortOrder,BizType,ViewPos,TopParentCode,TENANTID) "
		 * ); sql.append(" values (?,?,?,?,?,?,  ?,?,?,?,?,? ) ");
		 * sqlParam.add(authorityTmp.getName());
		 * sqlParam.add(authorityTmp.getAuthorityCode());sqlParam.add(
		 * authorityTmp.getParentAuthorityCode());
		 * sqlParam.add(authorityTmp.getDisplayName());
		 * sqlParam.add(authorityTmp.getMemo());
		 * sqlParam.add(authorityTmp.getAuthType());
		 * sqlParam.add(authorityTmp.getMenuHrefUrl());sqlParam.add(authorityTmp
		 * .getSortOrder());sqlParam.add(bizType);sqlParam.add(authorityTmp.
		 * getViewPos());sqlParam.add(authorityTmp.getTopParentCode());sqlParam.
		 * add(tenantId);
		 * 
		 * authorityDao.jdbcBatchUpdate(sql.toString(), sqlParam.toArray());
		 * sql.setLength(0); sqlParam.clear(); }
		 * 
		 * 
		 * 
		 * } }
		 */
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrginialAuthority> findSubOrginialAuthority(String id, String bizType, String authType) throws Exception {
		StringBuilder sb = new StringBuilder();
		// Map<String,Object> params = new HashMap<String, Object>();
		List<Object> params = new LinkedList<Object>();
		sb.append("SELECT auth.ID,auth.name, auth.DisplayName, auth.authorityCode, auth.parentAuthorityCode, auth.bizType , auth.SortOrder  ");// .append(ename);
		sb.append(",(SELECT COUNT(sub.id) FROM CMN_SEC_ORGINIAL_AUTH sub WHERE sub.BizType= ?  and sub.parentAuthorityCode = auth.authorityCode ");
		params.add(bizType);
		//
		// params.add(oa.getAuthorityCode());
		if (StringUtils.isNotEmpty(authType)) {
			sb.append(" and sub.AuthType = ? ");
			params.add(authType);
		}
		sb.append(" ) subCount  ");

		sb.append("FROM CMN_SEC_ORGINIAL_AUTH auth where 1=1 ");

		if (StringUtils.isNotEmpty(id) && !id.equals("0")) {// if(id!=null &&
															// id>0){
			// fei根节点
			sb.append(" and auth.parentAuthorityCode = ? ");
			OrginialAuthority oa = findEntityById(OrginialAuthority.class, id);
			params.add(oa.getAuthorityCode());
		} else {
			// 查询根节点
			/*
			 * sb.
			 * append("SELECT auth.ID,auth.name, auth.DisplayName, auth.authorityCode, auth.parentAuthorityCode, auth.bizType , auth.SortOrder  "
			 * );//.append(ename); sb.
			 * append(",(SELECT COUNT(sub.id) FROM CMN_SEC_ORGINIAL_AUTH sub WHERE sub.BizType= ?  and sub.parentAuthorityCode is null "
			 * ); params.add(bizType);
			 */
			sb.append(" and auth.parentAuthorityCode is null ");

		}
		sb.append(" and auth.bizType= ? ");
		params.add(bizType);

		if (StringUtils.isNotEmpty(authType)) {
			sb.append(" and auth.authType = ? ");
			params.add(authType);
		}
		sb.append(" order by auth.sortOrder asc ");

		List<OrginialAuthority> auList = authorityDao.queryObjectListBySql(sb.toString(), OrginialAuthority.class, params.toArray());
		return auList;
	}

	@Override
	public OrginialAuthority findParentOrginialAuthority(String id) throws Exception {
		OrginialAuthority oa = findEntityById(OrginialAuthority.class, id);

		StringBuilder sb = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		sb.append("select au from OrginialAuthority au where au.bizType=:bizType ");
		params.put("bizType", oa.getBizType());

		String parentAuthCode = oa.getParentAuthorityCode();
		if (StringUtils.isNotEmpty(parentAuthCode)) {
			sb.append(" and au.authorityCode = :parentAuthCode ");
			params.put("parentAuthCode", parentAuthCode);
		} else {
			sb.append(" and au.parentAuthorityCode is null ");
			return null;
		}

		List<OrginialAuthority> auList = authorityDao.findAllByHql2NoTenantId(sb.toString(), params);
		if (auList == null) {
			return null;
		} else {
			if (auList.isEmpty()) {
				return null;
			} else if (auList.size() == 1) {
				return auList.get(0);
			} else {
				throw new BizException("数据不唯一！");
			}
		}
	}

	@Override
	public OrginialAuthority findOrginialAuthorityByCode(String authorityCode, String bizType) throws Exception {
		StringBuilder sb = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		sb.append("select au from OrginialAuthority au where au.authorityCode = :authorityCode and au.bizType=:bizType ");
		params.put("authorityCode", authorityCode);
		params.put("bizType", bizType);
		List<OrginialAuthority> auList = authorityDao.findAllByHql2NoTenantId(sb.toString(), params);
		if (auList == null) {
			return null;
		} else {
			if (auList.isEmpty()) {
				return null;
			} else if (auList.size() == 1) {
				return auList.get(0);
			} else {
				throw new BizException("数据不唯一！");
			}
		}
	}

	/*
	 * (非 Javadoc) <p>Title: findMenuAll</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see com.vix.security.service.IAuthorityService#findMenuAll()
	 */
	@Override
	@Transactional(readOnly = true)
	public Set<TreeNode> findMenuAll() throws Exception {
		Map<String, TreeNode> treeMap = new HashMap<String, TreeNode>();
		Set<TreeNode> resSet = new TreeSet<TreeNode>();
		/*
		 * for (Iterator<Authority> it = auList.iterator(); it.hasNext();) {
		 * Authority at = it.next();
		 * 
		 * Authority parentObj = at.getParentAuthority(); TreeNode node = new
		 * TreeNode(at.getId(), parentObj==null?-1L:parentObj.getId(),
		 * at.getName(),at.getDisplayName(),at.getId(), at.getMenuHrefUrl());
		 * treeMap.put(node.getId(), node); //at.getId(),
		 * parentObj==null?-1:parentObj.getId(),
		 * parentObj,at.getName(),at.getDisplayName(), }
		 */

		Set<Map.Entry<String, TreeNode>> treeMapEntrySet = treeMap.entrySet();
		for (Iterator<Map.Entry<String, TreeNode>> it = treeMapEntrySet.iterator(); it.hasNext();) {
			// TreeNode node = (TreeNode) ((Map.Entry) it.next()).getValue();
			TreeNode node = it.next().getValue();
			if (node.getParentId().equals("-1")) {
				resSet.add(node);
			} else {
				treeMap.get(node.getParentId()).addChildren(node);
			}
		}
		return resSet;
	}

	/**
	 * @return
	 * @throws Exception
	 * 
	 * @Transactional(readOnly=true) public List<OrginialAuthority>
	 *                               findAllOrginialAuthorityByType(String type)
	 *                               throws Exception{ StringBuilder hql = new
	 *                               StringBuilder(); Map<String,Object> params
	 *                               = new HashMap<String, Object>();
	 *                               params.put("authType", type);
	 * 
	 *                               hql.append("select authority from ");
	 *                               hql.append(OrginialAuthority.class.getName()).append("
	 *                               authority "); hql.append(" left join fetch
	 *                               authority.roles where
	 *                               authority.authType=:authType");
	 * 
	 *                               //HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
	 *                               //HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
	 * 
	 *                               List<OrginialAuthority> resList =
	 *                               authorityDao.findAllByHql2NoTenantId(hql.toString(),params);
	 *                               return resList; }
	 */

	/*
	 * @Transactional(readOnly=true) public List<OrginialAuthority>
	 * findAllOrginialAuthority() throws Exception{ StringBuilder hql = new
	 * StringBuilder(); Map<String,Object> params = new HashMap<String,
	 * Object>();
	 * 
	 * //HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
	 * //HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//
	 * 不处理CompanyInnerCode; hql.append("select authority from ");
	 * hql.append(OrginialAuthority.class.getName()).append(" authority ");
	 * hql.append(" left join fetch authority.roles where 1=1 ");
	 * 
	 * List<Authority> resList =
	 * authorityDao.findAllByHql2NoTenantId(hql.toString(), params); return
	 * resList; }
	 */

	@Override
	@Transactional
	public void saveForImportOrginialAuthority(List<OrginialAuthorityImpVo> voList) throws Exception {
		authorityDao.saveForImportOrginialAuthority(voList);
	}

}
