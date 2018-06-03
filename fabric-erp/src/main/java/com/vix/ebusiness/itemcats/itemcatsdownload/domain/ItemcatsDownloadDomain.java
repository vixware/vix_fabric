/**
 * 
 */
package com.vix.ebusiness.itemcats.itemcatsdownload.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.GoodsType;
import com.vix.ebusiness.itemcats.itemcatsdownload.service.IItemcatsDownloadService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("itemcatsDownloadDomain")
@Transactional
public class ItemcatsDownloadDomain extends BaseDomain{

	@Autowired
	private IItemcatsDownloadService itemcatsDownloadService;

	/** 获取列表数据 */
	public Pager findGoodsTypePagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = itemcatsDownloadService.findPagerByHqlConditions(pager, GoodsType.class, params);
		return p;
	}

	/** 索引对象列表 */
	public List<GoodsType> findGoodsTypeList() throws Exception {
		return itemcatsDownloadService.findAllByConditions(GoodsType.class, null);
	}

	public List<ChannelDistributor> findChannelDistributorList(Map<String, Object> params) throws Exception {
		return itemcatsDownloadService.findAllByConditions(ChannelDistributor.class, params);
	}

	public void deleteGoodsTypeByEntity(GoodsType goodsType) throws Exception {
		itemcatsDownloadService.deleteByEntity(goodsType);
	}

	public GoodsType findGoodsTypeById(String id) throws Exception {
		return itemcatsDownloadService.findEntityById(GoodsType.class, id);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		itemcatsDownloadService.batchDelete(GoodsType.class, ids);
	}
}
