/**
 * 
 */
package com.vix.ebusiness.itemcats.itemcatsdownload.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.vix.common.org.vo.OrgUnit;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.itemcats.itemcatsdownload.service.IItemcatsDownloadService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Service("itemcatsDownloadService")
public class ItemcatsDownloadServiceImpl extends BaseHibernateServiceImpl implements IItemcatsDownloadService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8910089932784964013L;

	@Override
	public List<OrgUnit> findOrgAndUnitTreeList(String treeType, Long id) throws Exception {
		List<OrgUnit> orgUnitList = new LinkedList<OrgUnit>();
		if (treeType.equals("S")) {
			List<ChannelDistributor> channelDistributorList = findWarningTypeList(id);
			orgUnitList = makeTree(channelDistributorList);
		}
		return orgUnitList;
	}

	public List<ChannelDistributor> findWarningTypeList(Long id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (null == id || id == 0) {
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
				OrgUnit orgUnit = new OrgUnit(channelDistributor.getId(), "C", channelDistributor.getName());
				orgUnitList.add(orgUnit);
			}
		}
		return orgUnitList;
	}
}
