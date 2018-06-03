package com.vix.ebusiness.itemcats.itemcatsdownload.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.GoodsType;
import com.vix.ebusiness.itemcats.itemcatsdownload.controller.ItemcatsDownloadController;
import com.vix.ebusiness.itemcats.itemcatsdownload.processor.ItemcatsDownloadProcessor;

@Controller
@Scope("prototype")
public class ItemcatsDownloadAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ItemcatsDownloadController itemcatsDownloadController;
	@Autowired
	private ItemcatsDownloadProcessor itemcatsDownloadProcessor;
	private String id;
	private String ids;
	private String pageNo;
	private String treeType;

	private String channelDistributorId;
	/**
	 * 商品
	 */
	private GoodsType goodsType;
	private List<GoodsType> goodsTypeList;
	private List<ChannelDistributor> channelDistributorList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			params.put("type," + SearchCondition.ANYLIKE, "5");
			channelDistributorList = itemcatsDownloadController.doListChannelDistributorList(params);
			goodsTypeList = itemcatsDownloadController.doListGoodsTypeList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 
	 * @return
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");

			//处理搜索条件
			String typeId = getRequestParameter("typeId");
			String typeName = getRequestParameter("typeName");
			if (typeId != null && !"".equals(typeId)) {
				params.put("typeId," + SearchCondition.EQUAL, Long.parseLong(typeId));
			}
			if (typeName != null && !"".equals(typeName)) {
				params.put("typeName," + SearchCondition.ANYLIKE, decode(typeName, "UTF-8"));
			}
			//处理搜索条件
			pager = itemcatsDownloadController.doListGoodsTypePager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 商品类目下载
	 * 
	 * @return
	 */
	public String goDownloadItemcats() {
		if (StringUtils.isNotEmpty(channelDistributorId) && !"0".equals(channelDistributorId)) {
			itemcatsDownloadProcessor.process(channelDistributorId);
		} else {
			try {
				Map<String, Object> params = getParams();
				params.put("type," + SearchCondition.ANYLIKE, "5");
				List<ChannelDistributor> channelDistributorList = itemcatsDownloadController.doListChannelDistributorList(params);
				if (channelDistributorList != null) {
					for (ChannelDistributor channelDistributor : channelDistributorList) {
						if (channelDistributor != null) {
							itemcatsDownloadProcessor.process(channelDistributor.getId());
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			GoodsType goodsType = itemcatsDownloadController.doListGoodsTypeById(id);
			if (null != goodsType) {
				itemcatsDownloadController.doDeleteByEntity(goodsType);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 批量处理删除操作 */
	public String deleteByIds() {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				itemcatsDownloadController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goUpLoadItem() {
		return "goUpLoadItem";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public GoodsType getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(GoodsType goodsType) {
		this.goodsType = goodsType;
	}

	public List<GoodsType> getGoodsTypeList() {
		return goodsTypeList;
	}

	public void setGoodsTypeList(List<GoodsType> goodsTypeList) {
		this.goodsTypeList = goodsTypeList;
	}

	public List<ChannelDistributor> getChannelDistributorList() {
		return channelDistributorList;
	}

	public void setChannelDistributorList(List<ChannelDistributor> channelDistributorList) {
		this.channelDistributorList = channelDistributorList;
	}

	/**
	 * @return the channelDistributorId
	 */
	public String getChannelDistributorId() {
		return channelDistributorId;
	}

	/**
	 * @param channelDistributorId
	 *            the channelDistributorId to set
	 */
	public void setChannelDistributorId(String channelDistributorId) {
		this.channelDistributorId = channelDistributorId;
	}

}
