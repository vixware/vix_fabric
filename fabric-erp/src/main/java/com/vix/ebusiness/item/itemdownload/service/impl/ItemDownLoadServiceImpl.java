/**
 * 
 */
package com.vix.ebusiness.item.itemdownload.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vix.common.org.vo.OrgUnit;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.item.itemdownload.dao.IItemProcessDao;
import com.vix.ebusiness.item.itemdownload.service.IItemDownLoadService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Service("itemDownLoadService")
public class ItemDownLoadServiceImpl extends BaseHibernateServiceImpl implements IItemDownLoadService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8910089932784964013L;
	@Resource(name = "itemProcessDao")
	private IItemProcessDao itemProcessDao;

	@Override
	public List<OrgUnit> findOrgAndUnitTreeList(String treeType, String id) throws Exception {
		List<OrgUnit> orgUnitList = new LinkedList<OrgUnit>();
		if (treeType.equals("S")) {
			List<ChannelDistributor> channelDistributorList = findWarningTypeList(id);
			orgUnitList = makeTree(channelDistributorList);
		}
		return orgUnitList;
	}

	public List<ChannelDistributor> findWarningTypeList(String id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (null == id || "0".equals(id)) {
			params.put("storeType.id," + SearchCondition.IS, "NULL");
		} else {
			params.put("storeType.id," + SearchCondition.EQUAL, id);
		}
		List<ChannelDistributor> channelDistributorList = this.findAllByConditions(ChannelDistributor.class, params);
		return channelDistributorList;
	}

	private List<OrgUnit> makeTree(List<ChannelDistributor> channelDistributorList) {
		List<OrgUnit> orgUnitList = new LinkedList<OrgUnit>();
		if (channelDistributorList != null) {
			for (ChannelDistributor channelDistributor : channelDistributorList) {
				OrgUnit orgUnit = new OrgUnit(channelDistributor.getId(), "CH", channelDistributor.getName());
				orgUnitList.add(orgUnit);
			}
		}
		return orgUnitList;
	}

	@Override
	public <T> T findItemByHql(String hql, Map<String, Object> params) throws Exception {
		return itemProcessDao.findItemByHql(hql, params);
	}
}
