/**
 * 
 */
package com.vix.drp.channelprice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.hql.OrganizationUnitHqlProvider;
import com.vix.common.org.service.IOrganizationService;
import com.vix.common.org.vo.OrgUnit;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channelprice.hql.ChannelPriceHqlProvider;
import com.vix.drp.channelprice.service.IChannelPriceService;
import com.vix.mdm.item.entity.Item;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Service("channelPriceService")
public class ChannelPriceServiceImpl extends BaseHibernateServiceImpl implements IChannelPriceService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1552756851697916752L;
	@Autowired
	private IBaseHibernateDao baseHibernateDao;
	@Autowired
	private IOrganizationService organizationService;
	@Resource(name = "organizationUnitHqlProvider")
	private OrganizationUnitHqlProvider organizationUnitHqlProvider;
	@Resource(name = "channelPriceHqlProvider")
	private ChannelPriceHqlProvider channelPriceHqlProvider;

	public List<OrganizationUnit> findSubOrganizationUnitListByOrgId(Long orgId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgId", orgId);
		StringBuilder hql = organizationUnitHqlProvider.findOrgUnitList(params);
		List<OrganizationUnit> orgUnitList = baseHibernateDao.findAllByHql(hql.toString(), params);
		return orgUnitList;
	}

	/**
	 * 拼装公司和部门的树结构
	 */
	private List<OrgUnit> makeOrgAndUnitTree(List<Organization> organizationList, List<ChannelDistributor> channelDistributorList, List<Item> itemList) {
		List<OrgUnit> orgUnitList = new LinkedList<OrgUnit>();

		if (organizationList != null) {
			for (Organization cp : organizationList) {
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
				if (channelDistributor.getMdmItem().size() > 0) {
					List<OrgUnit> subOrgUnitList = new LinkedList<OrgUnit>();
					for (Item item : channelDistributor.getMdmItem()) {
						subOrgUnitList.add(new OrgUnit(item.getId(), "I", item.getName(), item.getCode()));
					}
					orgUnit.setSubOrgUnits(subOrgUnitList);
				}
				orgUnitList.add(orgUnit);
			}
		}
		if (itemList != null) {
			for (Item item : itemList) {
				// 获取所有物料信息
				OrgUnit orgUnit = new OrgUnit(item.getId(), "I", item.getName(), item.getCode());
				orgUnitList.add(orgUnit);
			}
		}
		return orgUnitList;
	}

	public List<OrganizationUnit> findSubOrganizationUnitList(Long orgUnitId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();

		if (null == orgUnitId || orgUnitId == 0) {
			params.put("orgId", null);
		} else {
			params.put("orgUnitId", orgUnitId);
		}
		StringBuilder hql = organizationUnitHqlProvider.findOrgUnitList(params);
		List<OrganizationUnit> orgUnitList = baseHibernateDao.findAllByHql(hql.toString(), params);
		return orgUnitList;
	}

	/**
	 * 还没写好
	 * 
	 * @param orgUnitId
	 * @return
	 * @throws Exception
	 */
	public List<ChannelDistributor> findChannelDistributorList(String orgId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgId", orgId);
		StringBuilder hql = channelPriceHqlProvider.findChannelDistributorList(params);
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
	public List<ChannelDistributor> findChannelDistributorListById(Long channelDistributorId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("channelDistributorId", channelDistributorId);
		StringBuilder hql = channelPriceHqlProvider.findChannelDistributorListById(params);
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
			resList = makeOrgAndUnitTree(orgList, channelDistributorList, null);
		} else if (treeType.equals("CH")) {
			ChannelDistributor channelDistributor = baseHibernateDao.findEntityById(ChannelDistributor.class, id);

			List<Item> itemList = new ArrayList<Item>();
			Set<Item> items = channelDistributor.getMdmItem();
			if (items != null && items.size() > 0) {
				for (Item item : items) {
					itemList.add(item);
				}
			}
			List<ChannelDistributor> channelDistributorList = new ArrayList<ChannelDistributor>();
			if (channelDistributor.getSubChannelDistributors().size() > 0 && channelDistributor.getSubChannelDistributors() != null) {
				for (ChannelDistributor c : channelDistributor.getSubChannelDistributors()) {
					channelDistributorList.add(c);
				}
			}
			resList = makeOrgAndUnitTree(null, channelDistributorList, itemList);
		}

		return resList;
	}

	/**
	 * 查询渠道,分销商,经销商,门店的信息
	 */
	@Override
	public Pager findOrganizationPager(Pager pager, Map<String, Object> reqParams) throws Exception {
		StringBuilder hql = channelPriceHqlProvider.findOrgPager(reqParams, pager);
		pager = baseHibernateDao.findPager2ByHql(pager, channelPriceHqlProvider.entityAsName(), hql.toString(), reqParams);
		return pager;
	}
}
