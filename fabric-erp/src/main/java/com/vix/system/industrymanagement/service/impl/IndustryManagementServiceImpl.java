package com.vix.system.industrymanagement.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.orginialMeta.entity.OrginialAuthority;
import com.vix.common.orginialMeta.entity.OrginialBaseMetaData;
import com.vix.common.security.dao.IOrginialAuthorityDao;
import com.vix.common.security.entity.Module;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.web.Pager;
import com.vix.system.industrymanagement.entity.IndustryManagement;
import com.vix.system.industrymanagement.entity.IndustryManagementModule;
import com.vix.system.industrymanagement.service.IIndustryManagementService;

@Service("industryManagementService")
@Transactional
public class IndustryManagementServiceImpl extends BaseHibernateServiceImpl implements IIndustryManagementService {

	@Resource(name = "orginialAuthorityDao")
	private IOrginialAuthorityDao authorityDao;

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.system.industrymanagement.service.IIndustryManagementService#
	 * findModulePagerByIndustryMgt(com.vix.core.web.Pager, java.util.Map)
	 */
	@Override
	public Pager findModulePagerByIndustryMgt(Pager pager, Map<String, Object> params) throws Exception {
		/*
		 * StringBuilder hql = new StringBuilder();
		 * 
		 * hql.append("select module from ").append(Module.class.getName()).
		 * append(" module ");//.append(ename);
		 * hql.append(" inner join module.industryManagementModules idModule");
		 * hql.append(" where 1=1 ");
		 * 
		 * if(params.containsKey("industryMgtId")){
		 * hql.append("  and  idModule.industryManagement.id =:industryMgtId "); }
		 * 
		 * if(params!=null && params.containsKey("moduleName")){
		 * hql.append(" and idModule.name like :moduleName "); }
		 * hql.append(" order by module.id ");
		 * 
		 * HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		 * HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//
		 * 不处理CompanyInnerCode;
		 * 
		 * pager = baseHibernateDao.findPager2ByHql(pager, "module", hql.toString(),
		 * params); return pager;
		 */
		StringBuilder hql = new StringBuilder();

		hql.append("select industryManagementModule from ").append(IndustryManagementModule.class.getName()).append(" industryManagementModule ");// .append(ename);
		hql.append(" inner join industryManagementModule.industryManagement industryManagement ");
		hql.append(" inner join industryManagementModule.module module ");
		hql.append(" where 1=1 ");

		if (params.containsKey("industryMgtId")) {
			hql.append("  and  industryManagement.id = :industryMgtId ");
			// hql.append(" and industryManagementModule.industryManagement.id =
			// :industryMgtId ");

		}

		if (params != null && params.containsKey("moduleName")) {
			hql.append(" and module.name like :moduleName ");
			// hql.append(" and industryManagementModule.module.name like :moduleName ");

		}
		hql.append(" order by industryManagementModule.id ");

		HqlTenantIdUtil.handleParamMap4TenantId(params);// 不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);// 不处理CompanyInnerCode;

		pager = baseHibernateDao.findPager2ByHql(pager, "industryManagementModule", hql.toString(), params);
		return pager;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.system.industrymanagement.service.IIndustryManagementService#
	 * saveForAddModule(java.lang.Long, java.lang.String)
	 */
	@Override
	public void saveForAddModule(String industryMgtId, String selectIds) throws Exception {
		IndustryManagement im = baseHibernateDao.findEntityById(IndustryManagement.class, industryMgtId);

		if (im != null) {

			Set<IndustryManagementModule> imModuleSet = im.getIndustryManagementModules();
			Set<Module> imHasModules = new HashSet<Module>();
			if (imModuleSet == null) {
				imModuleSet = new HashSet<IndustryManagementModule>();
			} else {
				for (IndustryManagementModule imTmp : imModuleSet) {
					imHasModules.add(imTmp.getModule());
				}
			}
			// userAccountDao.evict(uaTmp);

			if (StringUtils.isNotEmpty(selectIds)) {
				for (String idS : selectIds.split(",")) {
					if (null != idS && !"".equals(idS)) {
						Module module = baseHibernateDao.findEntityById(Module.class, idS);
						if (!imHasModules.contains(module)) {
							IndustryManagementModule addIm = new IndustryManagementModule();
							addIm.setIndustryManagement(im);
							addIm.setModule(module);
							baseHibernateDao.saveOrUpdateOriginal(addIm);
						}
					}
				}
			}

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.system.industrymanagement.service.IIndustryManagementService#
	 * findIndustrymanagementPager(com.vix.core.web.Pager, java.util.Map)
	 */
	@Override
	public Pager findIndustrymanagementPager(Pager pager, Map<String, Object> params) throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> param = new HashMap<String, Object>();
		hql.append("select industrymanagement from ").append(IndustryManagement.class.getName()).append(" industrymanagement ");// .append(ename);
		hql.append(" where 1=1 ");

		if (params != null && params.containsKey("name")) {
			hql.append(" and industrymanagement.name like :name ");
			params.put("name", "%" + params.get("name") + "%");
		}
		hql.append(" order by industrymanagement.id ");

		pager = baseHibernateDao.findPager2ByHql(pager, "industrymanagement", hql.toString(), param);
		return pager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.system.industrymanagement.service.IIndustryManagementService#
	 * findAllIndustrymanagement()
	 */
	@Override
	public List<IndustryManagement> findAllIndustrymanagement() throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> param = new HashMap<String, Object>();
		hql.append("select industrymanagement from ").append(IndustryManagement.class.getName()).append(" industrymanagement ");// .append(ename);
		hql.append(" where 1=1 ");

		hql.append(" order by industrymanagement.id ");

		HqlTenantIdUtil.handleParamMap4TenantId(param);// 不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(param);// 不处理CompanyInnerCode;

		List<IndustryManagement> industryMgtList = baseHibernateDao.findAllByHql2(hql.toString(), param);
		return industryMgtList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.system.industrymanagement.service.IIndustryManagementService#
	 * findSubAuthorityWithIndustryMgtModule(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<OrginialAuthority> findSubOrginialAuthorityWithIndustryMgtModule(String industryMgtModuleId, String bizType) throws Exception {
		IndustryManagementModule curretnIndustryMgt = baseHibernateDao.findEntityById(IndustryManagementModule.class, industryMgtModuleId);

		StringBuffer hql = new StringBuffer();
		Map<String, Object> param = new HashMap<String, Object>();
		hql.append("select distinct a from OrginialAuthority a ");
		hql.append(" left join fetch a.industryManagementModules ");

		hql.append(" where a.parentAuthorityCode is null  and  a.bizType = :bizType order by a.sortOrder ");
		param.put("bizType", bizType);

		// List<Authority> auList =
		// authorityDao.findAllByHql("authorityRoleFilter",filterParam,hql.toString(),
		// param);

		HqlTenantIdUtil.handleParamMap4TenantId(param);// 不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(param);// 不处理CompanyInnerCode;

		List<OrginialAuthority> auList = authorityDao.findAllByHql2(hql.toString(), param);
		// List<Authority> auList = authorityDao.findAllByHql(hql.toString(), param);
		if (auList != null) {
			for (OrginialAuthority au : auList) {
				au.setTreeId(au.getId());

				au.setCheckId(au.getId());
				if (au.getIndustryManagementModules() != null && au.getIndustryManagementModules().contains(curretnIndustryMgt)) {
					au.setIsCheck("Y");
				} else {
					au.setIsCheck("N");
				}

			}
		}
		return auList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.system.industrymanagement.service.IIndustryManagementService#
	 * findAuthorityWithIndustryMgt(java.lang.Long, java.lang.Long,
	 * java.lang.String)
	 * 
	 * public List<Authority> findAuthorityWithIndustryMgt(Long industryMgtId,Long
	 * authorityId,String bizType)throws Exception{ StringBuffer hql = new
	 * StringBuffer(); Map<String,Object> param = new HashMap<String, Object>();
	 * hql.append("select distinct a from Authority a "); hql.
	 * append(" left join fetch a.parentAuthority left join fetch a.subAuthoritys "
	 * ); hql.append(" left join fetch a.industryManagements ");
	 * 
	 * hql.append(" where a.bizType = :bizType "); param.put("bizType", bizType);
	 * if(authorityId==null){ hql.append(" and a.parentAuthority is null "); }else{
	 * hql.append(" and a.parentAuthority.id = :parentId "); param.put("parentId",
	 * authorityId); }
	 * 
	 * //Map<String,Object> filterParam = new HashMap<String, Object>();
	 * //filterParam.put("filterRoleId", roleId); //List<Authority> auList =
	 * authorityDao.findAllByHql("authorityRoleFilter",filterParam,hql.toString(),
	 * param);
	 * 
	 * HqlTenantIdUtil.handleParamMap4TenantId(param);//不增加tenantId
	 * HqlTenantIdUtil.handleParamMap4CompanyInnerCode(param);//不处理CompanyInnerCode;
	 * 
	 * List<Authority> auList = authorityDao.findAllByHql2(hql.toString(), param);
	 * //List<Authority> auList = authorityDao.findAllByHql(hql.toString(), param);
	 * return auList; }
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.system.industrymanagement.service.IIndustryManagementService#
	 * findAuthorityWithIndustryMgtForTreeGrid(java.lang.Long, java.lang.Long,
	 * java.lang.String)
	 * 
	 * public List<Authority> findAuthorityWithIndustryMgtForTreeGrid(Long
	 * industryMgtId,Long authorityId,String bizType)throws Exception{ //Role
	 * currentRole = authorityDao.findEntityById(Role.class, roleId);
	 * IndustryManagement curretnIndustryMgt =
	 * baseHibernateDao.findEntityById(IndustryManagement.class, industryMgtId);
	 * 
	 * List<Authority> auList = findAuthorityWithIndustryMgt(industryMgtId,
	 * authorityId,bizType); if(auList!=null){ for(Authority au:auList){
	 * au.setTreeId(au.getId()); au.setParentId(authorityId);
	 * 
	 * au.setCheckId(au.getId()); if(au.getIndustryManagements()!=null &&
	 * au.getIndustryManagements().contains(curretnIndustryMgt) ){
	 * au.setIsCheck("Y"); }else{ au.setIsCheck("N"); }
	 * 
	 * Authority parent = au.getParentAuthority();
	 * au.setParentId(parent==null?0L:parent.getId());
	 * if(au.getSubAuthoritys()!=null &&au.getSubAuthoritys().size()>0){
	 * au.setState("closed"); }else{ au.setState("open"); }
	 * 
	 * } }
	 * 
	 * //authorityDao.clear(); List<Authority> resAuList = new
	 * LinkedList<Authority>(); for(Authority au:auList){ Authority resAu = new
	 * Authority(); BeanUtils.copyProperties(au, resAu,new
	 * String[]{"parentAuthority","subAuthoritys","roles","resources",
	 * "industryManagements"}); resAuList.add(resAu); } return resAuList; }
	 */

	private List<OrginialAuthority> findAllOrginialAuthorityWithIndustryMgtModule(String topParentCode, String bizType) throws Exception {
		StringBuffer hql = new StringBuffer();
		Map<String, Object> param = new HashMap<String, Object>();
		hql.append("select distinct a from OrginialAuthority a ");
		// hql.append(" left join fetch a.roles left join fetch a.resources ");
		hql.append(" left join fetch a.industryManagementModules ");
		/*
		 * hql.append(" left join  a.roles role with role.id = :roleId ");
		 * param.put("roleId", roleId);
		 */
		hql.append(" where a.bizType = :bizType ");
		param.put("bizType", bizType);

		hql.append(" and a.topParentCode = :topParentCode ");
		param.put("topParentCode", topParentCode);

		// List<Authority> auList =
		// authorityDao.findAllByHql("authorityRoleFilter",filterParam,hql.toString(),
		// param);
		HqlTenantIdUtil.handleParamMap4TenantId(param);// 不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(param);// 不处理CompanyInnerCode;

		List<OrginialAuthority> auList = authorityDao.findAllByHql2(hql.toString(), param);
		// List<Authority> auList = authorityDao.findAllByHql(hql.toString(), param);
		return auList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.system.industrymanagement.service.IIndustryManagementService#
	 * findAllAuthorityWithIndustryMgtModuleForTreeGrid(java.lang.Long,
	 * java.lang.Long, java.lang.String)
	 * 
	 * public Set<OrginialAuthority>
	 * findAllOrginialAuthorityWithIndustryMgtModuleForTreeGrid(Long
	 * industryMgtModuleId,Long authorityId,String bizType)throws Exception{
	 * 
	 * //IndustryManagement curretnIndustryMgt =
	 * baseHibernateDao.findEntityById(IndustryManagement.class, industryMgtId);
	 * IndustryManagementModule curretnIndustryMgt =
	 * baseHibernateDao.findEntityById(IndustryManagementModule.class,
	 * industryMgtModuleId);
	 * 
	 * OrginialAuthority topAuthority =
	 * authorityDao.findEntityById(OrginialAuthority.class, authorityId); String
	 * authorityCode = topAuthority.getAuthorityCode();
	 * 
	 * //查询出所有权限 List<OrginialAuthority> auList =
	 * findAllOrginialAuthorityWithIndustryMgtModule(authorityCode, bizType);
	 * 
	 * 
	 * Map<Long,OrginialAuthority> treeMap = new HashMap<Long, OrginialAuthority>();
	 * Set<OrginialAuthority> resSet = new TreeSet<OrginialAuthority>();
	 * 
	 * 
	 * for (OrginialAuthority au:auList) { au.setCheckId(au.getId());
	 * if(au.getIndustryManagementModules()!=null &&
	 * au.getIndustryManagementModules().contains(curretnIndustryMgt) ){
	 * au.setIsCheck("Y"); }else{ au.setIsCheck("N"); }
	 * 
	 * OrginialAuthority parent = au.getParentAuthority();//肯定不为空 Long parentIdTmp =
	 * parent.getId(); //au.setParentId(parent==null?0L:parent.getId());
	 * if(parentIdTmp.equals(authorityId)){ au.setParentId(null); }else{
	 * au.setParentId(parentIdTmp); }
	 * 
	 * if(au.getSubAuthoritys()!=null &&au.getSubAuthoritys().size()>0){
	 * au.setState("closed"); }else{ au.setState("open"); }
	 * //System.out.println(au.getParentId()); treeMap.put(au.getId(), au); }
	 * 
	 * 
	 * Set<Map.Entry<Long,OrginialAuthority>> treeMapEntrySet = treeMap.entrySet();
	 * for(Iterator<Map.Entry<Long,OrginialAuthority>> it =
	 * treeMapEntrySet.iterator(); it.hasNext();){ //TreeNode node = (TreeNode)
	 * ((Map.Entry) it.next()).getValue(); OrginialAuthority node =
	 * it.next().getValue(); if(node.getParentId()==null){ resSet.add(node); }else{
	 * Long parentIdTemp = node.getParentId();
	 * if(treeMap.containsKey(parentIdTemp)){
	 * treeMap.get(parentIdTemp).addChildren(node); } } }
	 * 
	 * return resSet; }
	 */

	/*
	 * public void saveForAuthority(Long industryMgtModuleId,String bizType,Long
	 * topParentAuId,String topAuthorityIds,List<Long> authorityIdList)throws
	 * Exception{ StringBuffer sql = new StringBuffer(); Map<String,Object> params =
	 * new HashMap<String, Object>(); //1、保存左侧的节点
	 * if(StringUtils.isNotEmpty(topAuthorityIds) ){ String[] topAuthorityIdsArray =
	 * topAuthorityIds.split("\\,"); sql.
	 * append("delete from CMN_SEC_ORGINIAL_AUTH_IND_MODULE where IndustryManagementModule_ID=:industryMgtId "
	 * ); params.put("industryMgtId", industryMgtModuleId); sql.
	 * append(" and Authority_ID in (select a.id from CMN_SEC_ORGINIAL_AUTH a where a.Parent_id is null  and a.BizType=:bizType )"
	 * );//and a.authType='M' params.put("bizType", bizType);
	 * //authorityDao.jdbcBatchUpdate(sql.toString(), roleId);
	 * authorityDao.batchUpdateBySql(sql.toString(), params);
	 * 
	 * 
	 * sql.setLength(0); params.clear(); sql.
	 * append("insert into CMN_SEC_ORGINIAL_AUTH_IND_MODULE(IndustryManagementModule_ID,Authority_ID) values (?,?)"
	 * ); List<Object[]> saveArrayParam = new LinkedList<Object[]>(); for(String
	 * aid:topAuthorityIdsArray){ saveArrayParam.add(new
	 * Object[]{industryMgtModuleId,Long.parseLong(aid)}); }
	 * 
	 * //authorityDao.jdbcBatchUpdate(sql.toString(), saveArrayParam);
	 * authorityDao.batchUpdateBySql(sql.toString(), saveArrayParam); }else{ sql.
	 * append("delete from CMN_SEC_ORGINIAL_AUTH_IND_MODULE where IndustryManagementModule_ID=:industryMgtId "
	 * ); params.put("industryMgtId", industryMgtModuleId);
	 * //authorityDao.jdbcBatchUpdate(sql.toString(), roleId);
	 * authorityDao.batchUpdateBySql(sql.toString(), params); }
	 * 
	 * //2、保存 右侧的节点 sql.setLength(0); params.clear(); if(topParentAuId!=null){
	 * Authority topAu = authorityDao.findEntityById(Authority.class,
	 * topParentAuId); sql.
	 * append("delete from CMN_SEC_ORGINIAL_AUTH_IND_MODULE where IndustryManagementModule_ID=:industryMgtId "
	 * ); params.put("industryMgtId", industryMgtModuleId); sql.
	 * append(" and Authority_ID in (select a.id from CMN_SEC_ORGINIAL_AUTH a where a.TopParentCode = :topParentCode )"
	 * );//and a.authType='M' params.put("topParentCode", topAu.getAuthorityCode());
	 * //authorityDao.jdbcBatchUpdate(sql.toString(), roleId);
	 * authorityDao.batchUpdateBySql(sql.toString(), params); }
	 * 
	 * if(authorityIdList!=null){ sql.setLength(0); params.clear(); sql.
	 * append("insert into CMN_SEC_ORGINIAL_AUTH_IND_MODULE(IndustryManagementModule_ID,Authority_ID) values (?,?)"
	 * ); List<Object[]> saveArrayParam = new LinkedList<Object[]>();
	 * if(authorityIdList!=null){ for(Long aid:authorityIdList){
	 * saveArrayParam.add(new Object[]{industryMgtModuleId,aid}); } }
	 * 
	 * //authorityDao.jdbcBatchUpdate(sql.toString(), saveArrayParam);
	 * authorityDao.batchUpdateBySql(sql.toString(), saveArrayParam); }
	 * 
	 * }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.system.industrymanagement.service.IIndustryManagementService#
	 * findMetaDataPagerByIndustryMgt(com.vix.core.web.Pager, java.util.Map)
	 */
	@Override
	public Pager findMetaDataPagerByIndustryMgt(Pager pager, Map<String, Object> params) throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> param = new HashMap<String, Object>();
		hql.append("select orginialBaseMetaData from ").append(OrginialBaseMetaData.class.getName()).append(" orginialBaseMetaData ");
		hql.append("left join orginialBaseMetaData.industryManagementModules industryManagementModule ");
		hql.append(" where industryManagementModule.id = :industryMgtModuleId ");
		param.put("industryMgtModuleId", params.get("industryMgtModuleId"));

		if (params != null && params.containsKey("metadataDisplayName")) {
			hql.append(" and orginialBaseMetaData.metaDataDisplayName like :name ");
			param.put("name", "%" + params.get("metadataDisplayName") + "%");
		}
		hql.append(" order by orginialBaseMetaData.id ");

		// HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		// HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;

		pager = baseHibernateDao.findPagerOrginialByHql(pager, "orginialBaseMetaData", hql.toString(), param);
		return pager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.system.industrymanagement.service.IIndustryManagementService#
	 * findSelectMetaDataPagerByIndustryMgt(com.vix.core.web.Pager, java.util.Map)
	 */
	@Override
	public Pager findSelectMetaDataPagerByIndustryMgt(Pager pager, Map<String, Object> params) throws Exception {

		StringBuilder sb = new StringBuilder();
		Map<String, Object> param = new HashMap<String, Object>();

		sb.append("select orginialBaseMetaData from ");
		sb.append(OrginialBaseMetaData.class.getName()).append(" orginialBaseMetaData ");
		sb.append(" left join orginialBaseMetaData.industryManagementModules module ");// with module.id != :industryMgtId
		sb.append(" where 1=1 ");

		sb.append(" and (module is null  or (module.id != :industryMgtModuleId ))");
		param.put("industryMgtModuleId", params.get("industryMgtModuleId"));
		if (params.containsKey("categoryId")) {
			sb.append("  and  orginialBaseMetaData.baseMetaDataCategory.id =:categoryId ");
			param.put("categoryId", params.get("categoryId"));
		}
		if (params.containsKey("metaDataName")) {
			sb.append("  and  orginialBaseMetaData.metaDataName like :metaDataName ");
			param.put("metaDataName", params.get("metaDataName"));
		}
		sb.append(" order by orginialBaseMetaData.id ");
		pager = baseHibernateDao.findPagerOrginialByHql(pager, "orginialBaseMetaData", sb.toString(), param);
		return pager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.system.industrymanagement.service.IIndustryManagementService#
	 * saveForAddMetaData(java.lang.Long, java.lang.String)
	 */
	@Override
	public void saveForAddMetaData(String industryMgtModuleId, String addMetaDataIds) throws Exception {
		IndustryManagementModule industryMgtModule = baseHibernateDao.findEntityById(IndustryManagementModule.class, industryMgtModuleId);
		Set<OrginialBaseMetaData> orginialBaseMetaDatas = industryMgtModule.getOrginialBaseMetaDatas();
		if (orginialBaseMetaDatas == null) {
			orginialBaseMetaDatas = new HashSet<OrginialBaseMetaData>();
		}

		if (StringUtils.isNotEmpty(addMetaDataIds)) {
			String[] metaArray = addMetaDataIds.split("\\,");
			for (String metaId : metaArray) {
				OrginialBaseMetaData meta = baseHibernateDao.findEntityById(OrginialBaseMetaData.class, metaId);
				orginialBaseMetaDatas.add(meta);
			}
			baseHibernateDao.update(industryMgtModule);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.system.industrymanagement.service.IIndustryManagementService#
	 * deleteModuleMetaDataByImIdAndMdId(java.lang.Long, java.lang.String)
	 */
	@Override
	public void deleteModuleMetaDataByImIdAndMdId(String industryMgtModuleId, String metadataIds) throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		sql.append("delete from CMN_MET_ORGINIAL_METADATA_MD where IndustryManagementModule_ID = :industryMgtModuleId ");
		params.put("industryMgtModuleId", industryMgtModuleId);
		sql.append(" and ORGINIAL_METADATA_ID in (");// and a.authType='M'
		sql.append(metadataIds);
		sql.append(")");
		// authorityDao.jdbcBatchUpdate(sql.toString(), roleId);
		authorityDao.batchUpdateBySql(sql.toString(), params);
	}

}
