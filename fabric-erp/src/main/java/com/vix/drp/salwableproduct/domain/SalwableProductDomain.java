/**
 * 
 */
package com.vix.drp.salwableproduct.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.org.entity.Organization;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.salwableproduct.service.ISalwableProductService;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.entity.Item;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("salwableProductDomain")
@Transactional
public class SalwableProductDomain extends BaseDomain{

	@Autowired
	private ISalwableProductService salwableProductService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = salwableProductService.findPagerByHqlConditions(pager, Item.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(ChannelDistributor channelDistributor, Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;

		Set<Item> items = channelDistributor.getMdmItem();
		List<Item> itemList = new ArrayList<Item>();
		if (items != null && items.size() > 0) {
			for (Item item : items) {
				itemList.add(item);
			}
		}
		pager.setTotalCount(items.size());
		pager.setResultList(itemList);
		if (pager.getResultList() != null) {
			p = pager;
		} else {
			p = salwableProductService.findPagerByHqlConditions(pager, Item.class, params);
		}
		return p;
	}

	public Employee findEmployeeById(String id) throws Exception {
		return salwableProductService.findEntityById(Employee.class, id);
	}

	public Item findEntityById(String id) throws Exception {
		return salwableProductService.findEntityById(Item.class, id);
	}

	public ChannelDistributor findChannelDistributorById(String id) throws Exception {
		return salwableProductService.findEntityById(ChannelDistributor.class, id);
	}

	public Organization findOrganizationById(String id) throws Exception {
		return salwableProductService.findEntityById(Organization.class, id);
	}

	public void saveOrUpdate(ChannelDistributor channelDistributor) throws Exception {
		salwableProductService.update(channelDistributor);
	}

	public void deleteByEntity(Item item) throws Exception {
		salwableProductService.deleteByEntity(item);
	}

	/** 索引对象列表 */
	public List<Item> findItemList(Map<String, Object> params) throws Exception {
		return salwableProductService.findAllByConditions(Item.class, params);
	}
}
