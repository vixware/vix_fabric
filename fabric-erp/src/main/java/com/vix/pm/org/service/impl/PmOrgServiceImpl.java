package com.vix.pm.org.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.hql.OrganizationUnitHqlProvider;
import com.vix.common.org.service.IOrganizationService;
import com.vix.common.security.entity.Role;
import com.vix.core.constant.BizConstant;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.pm.org.dao.IPmOrgDao;
import com.vix.pm.org.entity.PmOrg;
import com.vix.pm.org.entity.PmView;
import com.vix.pm.org.hql.PmOrgHqlProvider;
import com.vix.pm.org.service.IPmOrgService;

/**
 * 
 * @ClassName: PmOrgServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-2-10 下午3:24:15
 */
@Service("pmOrgService")
@Transactional
public class PmOrgServiceImpl extends BaseHibernateServiceImpl implements IPmOrgService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IOrganizationService organizationService;

	@Resource(name = "pmOrgDao")
	private IPmOrgDao pmOrgDao;

	@Resource(name = "pmOrgHqlProvider")
	private PmOrgHqlProvider pmOrgHqlProvider;

	@Resource(name = "organizationUnitHqlProvider")
	private OrganizationUnitHqlProvider organizationUnitHqlProvider;

	@Override
	public Pager findBusinessOrgPager(Pager pager, Map<String, Object> params) throws Exception {
		//StringBuilder hql = pmOrgHqlProvider.findBusinessOrgList(params, null);
		StringBuilder hql = pmOrgHqlProvider.findBusinessOrgList4Page(params, pager);
		pager = pmOrgDao.findPager2ByHql(pager, pmOrgHqlProvider.entityAsName(), hql.toString(), params);
		//Pager respager = pmOrgDao.findPagerByHql(pager,pmOrgHqlProvider.entityAsName(),hql.toString(), params);
		//Pager respager = organizationUnitDao.findPagerByHql(pager,hql.toString(), params);
		return pager;
	}

	/**
	 * 查询整个业务组织列表
	 */
	@Override
	public List<PmOrg> findAllBusinessOrg() throws Exception {
		//List<PmOrg> all = pmOrgDao.findAllByHql2("select bo from PmOrg bo order by ", new HashMap<String, Object>());
		List<PmOrg> all = pmOrgDao.findAllByEntityClass(PmOrg.class);
		return all;
	}

	/**
	 * 查询业务组织树形结构
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Set<PmOrg> findBusinessOrgAll() throws Exception {
		Set<PmOrg> resList = new HashSet<PmOrg>();
		List<PmOrg> all = findAllBusinessOrg();

		Map<String, PmOrg> map = new HashMap<String, PmOrg>();
		if (all != null && !all.isEmpty()) {
			for (PmOrg bo : all) {
				map.put(bo.getId(), bo);
			}

		}
		for (PmOrg bo : all) {
			PmOrg parent = bo.getParentBusinessOrg();
			if (parent == null) {
				resList.add(bo);
			} else {
				map.get(parent.getId()).addChildren(bo);
			}
		}

		/*for(PmOrg bi:resList){
			System.out.println(bi.getBusinessOrgName());
			if(bi.getSubBusinessOrgs()!=null && bi.getSubBusinessOrgs().size()>0){
				for(PmOrg bi2:bi.getSubBusinessOrgs()){
					System.out.println("LEVEL2:"+bi2.getBusinessOrgName());
					
					if(bi2.getSubBusinessOrgs()!=null && bi2.getSubBusinessOrgs().size()>0){
						for(PmOrg bi3:bi2.getSubBusinessOrgs()){
							System.out.println("LEVEL3:"+bi3.getBusinessOrgName());
						}
					}
					
				}
			}
		}*/
		return resList;
	}

	@Override
	public PmOrg saveUpdateBusinessOrg(String bizOrgIds, String bizOrgType, PmOrg boForm, char parentType, String parentId) throws Exception {
		PmOrg bo = null;

		if (boForm != null && boForm.getId() != null) {
			bo = pmOrgDao.findEntityById(PmOrg.class, boForm.getId());
		}
		if (bo == null) {
			bo = new PmOrg();
		}
		Date now = new Date();

		bo.setCreateTime(now);
		bo.setUpdateTime(now);

		bo.setBusinessOrgName(boForm.getBusinessOrgName());
		bo.setBusinessOrgCode(boForm.getBusinessOrgCode());
		bo.setParentBusinessOrg(boForm.getParentBusinessOrg());
		bo.setIsVirtualUnitNode(boForm.getIsVirtualUnitNode());
		bo.setBizOrgType(bizOrgType);

		if (parentType == 'V') {
			PmView view = pmOrgDao.findEntityById(PmView.class, parentId);
			bo.setPmView(view);
		} else if (parentType == 'O') {
			PmOrg org = pmOrgDao.findEntityById(PmOrg.class, parentId);
			bo.setParentBusinessOrg(org);
		}

		if (StringUtils.isNotEmpty(bizOrgType)) {
			if (bizOrgType.equalsIgnoreCase(BizConstant.COMMON_ORG_O)) {
				String orgUnitIdStr = bizOrgIds.substring(0, bizOrgIds.length() - 1);
				OrganizationUnit unit = pmOrgDao.findEntityById(OrganizationUnit.class, orgUnitIdStr);
				bo.setOrganizationUnit(unit);
				bo.setOrgUnitName(unit.getFs());
			} else if (bizOrgType.equalsIgnoreCase(BizConstant.COMMON_ORG_R)) {
				Role role = pmOrgDao.findEntityById(Role.class, bizOrgIds);
				bo.setRole(role);
				bo.setOrgUnitName(role.getName());
			} else if (bizOrgType.equalsIgnoreCase(BizConstant.COMMON_ORG_E)) {
				Employee emp = pmOrgDao.findEntityById(Employee.class, bizOrgIds);
				bo.setEmployee(emp);
				bo.setOrgUnitName(emp.getName());
			}
		}

		pmOrgDao.saveOrUpdate(bo);
		return bo;
	}

	@Override
	public String[] findBusinessOrgAllForOrgUnit(String orgUnitId) throws Exception {
		String[] resStr = new String[3];

		StringBuilder resBulder = new StringBuilder();

		Set<PmOrg> orgUnitBusinessOrgList = pmOrgDao.findBusinessOrgByOrgUnitId(orgUnitId);
		Set<String> orgUnitIdSet = new HashSet<String>();
		Set<String> orgUnitNameSet = new HashSet<String>();

		for (PmOrg bo : orgUnitBusinessOrgList) {
			orgUnitIdSet.add(bo.getId());
			orgUnitNameSet.add(bo.getBusinessOrgName());
		}

		Set<PmOrg> allBusinessOrgList = findBusinessOrgAll();
		resBulder.append("[");

		List<String> objStr = new LinkedList<String>();
		/** 递归方式 **/
		for (PmOrg bo : allBusinessOrgList) {
			StringBuilder oneStr = new StringBuilder();
			oneStr.append(makeBusinessOne(bo, orgUnitIdSet));
			objStr.add(oneStr.toString());
		}
		if (!objStr.isEmpty()) {
			resBulder.append(StringUtils.join(objStr.iterator(), ","));
		}

		resBulder.append("]");

		resStr[0] = resBulder.toString();
		resStr[1] = StringUtils.join(orgUnitIdSet, ",");
		resStr[2] = StringUtils.join(orgUnitNameSet, ",");
		return resStr;
	}

	private String makeBusinessOne(PmOrg bo, Set<String> orgUnitBoIdList) {
		StringBuilder oneStr = new StringBuilder("");

		PmOrg parent = bo.getParentBusinessOrg();
		Set<PmOrg> subs = bo.getSubBusinessOrgs();
		String pid = (parent == null ? "0" : parent.getId());
		String id = bo.getId();

		oneStr.append("{\"id\":");
		oneStr.append(id);
		oneStr.append(",\"pId\":");
		oneStr.append(pid);
		oneStr.append(",\"name\":\"");
		oneStr.append(bo.getBusinessOrgName());
		if (subs != null && !subs.isEmpty()) {
			oneStr.append("\",\"isParent\":true");// open:true,
		} else {
			oneStr.append("\",\"isParent\":false");// ,open:false
		}

		if (orgUnitBoIdList.contains(id)) {
			oneStr.append(",\"checked\":true");
		} else {
			oneStr.append(",\"checked\":false");
		}

		if (subs != null && !subs.isEmpty()) {
			oneStr.append(",\"children\":[");

			List<String> subString = new LinkedList<String>();
			for (PmOrg subBo : subs) {
				String str = makeBusinessOne(subBo, orgUnitBoIdList);
				subString.add(str);
			}
			oneStr.append(StringUtils.join(subString.iterator(), ","));
			oneStr.append("]");
		}

		oneStr.append("}");
		return oneStr.toString();
	}

}
