package com.vix.chain.storeInformation.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.chain.storeInformation.service.IStoreService;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.hql.OrganizationUnitHqlProvider;
import com.vix.common.org.service.IOrganizationService;
import com.vix.common.org.vo.OrgUnit;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.distributermanagement.hql.ChannelDistributorHqlProvider;

@Service("storeService")
public class StoreServiceImpl extends BaseHibernateServiceImpl implements IStoreService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7909516154323478474L;
	@Autowired
	private IBaseHibernateDao baseHibernateDao;
	@Autowired
	private IOrganizationService organizationService;
	@Resource(name = "organizationUnitHqlProvider")
	private OrganizationUnitHqlProvider organizationUnitHqlProvider;
	@Resource(name = "channelDistributorHqlProvider")
	private ChannelDistributorHqlProvider channelDistributorHqlProvider;

	public List<OrganizationUnit> findSubOrganizationUnitListByOrgId(String orgId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgId", orgId);
		StringBuilder hql = organizationUnitHqlProvider.findOrgUnitList(params);
		List<OrganizationUnit> orgUnitList = baseHibernateDao.findAllByHql(hql.toString(), params);
		return orgUnitList;
	}

	/**
	 * 拼装公司和部门的树结构
	 */
	private List<OrgUnit> makeOrgAndUnitTree(List<Organization> compList, List<ChannelDistributor> channelDistributorList) {
		List<OrgUnit> orgUnitList = new LinkedList<OrgUnit>();

		if (compList != null) {
			for (Organization cp : compList) {
				OrgUnit orgUnit = new OrgUnit(cp.getId(), "C", cp.getOrgName());
				if (cp.getSubOrganizations().size() > 0) {
					List<OrgUnit> subUnitList = new LinkedList<OrgUnit>();
					for (Organization ot : cp.getSubOrganizations()) {
						subUnitList.add(new OrgUnit(ot.getId(), "C", ot.getOrgName()));
					}
					orgUnit.setSubOrgUnits(subUnitList);
				}
				if (cp.getChannelDistributors().size() > 0) {
					List<OrgUnit> subUnitList = new LinkedList<OrgUnit>();
					for (ChannelDistributor ou : cp.getChannelDistributors()) {
						subUnitList.add(new OrgUnit(ou.getId(), "CH", ou.getName()));
					}
					orgUnit.setSubOrgUnits(subUnitList);
				}
				orgUnitList.add(orgUnit);
			}
		}

		if (channelDistributorList != null) {
			for (ChannelDistributor channelDistributor : channelDistributorList) {
				OrgUnit orgUnit = new OrgUnit(channelDistributor.getId(), "CH", channelDistributor.getName());

				if (channelDistributor.getSubChannelDistributors().size() > 0) {

					List<OrgUnit> subUnitList = new LinkedList<OrgUnit>();
					for (ChannelDistributor ou : channelDistributor.getSubChannelDistributors()) {
						subUnitList.add(new OrgUnit(ou.getId(), "CH", ou.getName()));
					}
					orgUnit.setSubOrgUnits(subUnitList);
				}
				orgUnitList.add(orgUnit);
			}
		}

		return orgUnitList;
	}

	/**
	 * 根据公司ID获取其下的分销体系结构
	 * 
	 * @param orgUnitId
	 * @return
	 * @throws Exception
	 */
	public List<ChannelDistributor> findChannelDistributorList(String orgId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgId", orgId);
		StringBuilder hql = channelDistributorHqlProvider.findChannelDistributorList(params);
		List<ChannelDistributor> orgUnitList = baseHibernateDao.findAllByHql(hql.toString(), params);
		return orgUnitList;
	}

	/**
	 * 还没写好
	 * 
	 * @param orgUnitId
	 * @return
	 * @throws Exception
	 */
	public List<ChannelDistributor> findChannelDistributorListById(String channelDistributorId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("channelDistributorId", channelDistributorId);
		StringBuilder hql = channelDistributorHqlProvider.findChannelDistributorListById(params);
		List<ChannelDistributor> orgUnitList = baseHibernateDao.findAllByHql(hql.toString(), params);
		return orgUnitList;
	}

	@Override
	public List<OrgUnit> findOrgAndUnitTreeList(String treeType, String id) throws Exception {
		List<OrgUnit> resList = new LinkedList<OrgUnit>();
		if (treeType.equals("C")) {
			// 查找子公司和子部门信息
			List<Organization> orgList = organizationService.findSubOrganizationList(id);
			List<ChannelDistributor> channelDistributorList = findChannelDistributorList(id);
			resList = makeOrgAndUnitTree(orgList, channelDistributorList);
		} else if (treeType.equals("CH")) {
			List<ChannelDistributor> channelDistributorList = findChannelDistributorListById(id);
			resList = makeOrgAndUnitTree(null, channelDistributorList);
		}

		return resList;
	}

	/**
	 * 查询渠道,分销商,经销商,门店的信息
	 */
	@Override
	public Pager findOrganizationPager(Pager pager, Map<String, Object> reqParams) throws Exception {
		StringBuilder hql = channelDistributorHqlProvider.findOrgPager(reqParams, pager);
		pager = baseHibernateDao.findPager2ByHql(pager, channelDistributorHqlProvider.entityAsName(), hql.toString(), reqParams);
		return pager;
	}

}
