package com.vix.ebusiness.item.itemdownload.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.vo.OrgUnit;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channel.entity.StoreType;
import com.vix.ebusiness.entity.Goods;
import com.vix.ebusiness.entity.Products;
import com.vix.ebusiness.item.itemdownload.controller.ItemDownLoadController;
import com.vix.ebusiness.item.itemdownload.processor.ItemDownloadProcessor;
import com.vix.ebusiness.item.itemdownload.service.IItemDownLoadService;

@Controller
@Scope("prototype")
public class ItemDownLoadAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ItemDownLoadController itemDownLoadController;
	@Autowired
	private ItemDownloadProcessor itemDownloadProcessor;
	@Autowired
	private IItemDownLoadService itemDownLoadService;
	private String id;
	private String treeType;
	private String ids;
	private String channelId;
	/**
	 * 商品
	 */
	private Goods goods;
	private List<Goods> goodsList;
	private List<ChannelDistributor> channelDistributorList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			Map<String, Object> gparams = new HashMap<String, Object>();
			params.put("type," + SearchCondition.ANYLIKE, "5");
			channelDistributorList = itemDownLoadController.doListChannelDistributor(params);
			goodsList = itemDownLoadController.doListGoodsList(gparams);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				goods = itemDownLoadController.doListGoodsById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public void getGoodsDetailJson() {
		try {
			String json = "";
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Goods goods = itemDownLoadController.doListGoodsById(id);
				if (goods != null) {
					json = convertListToJson(new ArrayList<Products>(goods.getSubProducts()), goods.getSubProducts().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (id != null && !"".equals(id)) {
				if (treeType != null && "CH".equals(treeType)) {
					params.put("channelDistributor.id," + SearchCondition.EQUAL, id);
				}
			}
			// 是否上架
			params.put("isMarketable," + SearchCondition.EQUAL, 1);

			//处理搜索条件
			String searchContent = getRequestParameter("searchContent");
			String goodsCode = getRequestParameter("goodsCode");
			String goodsName = getDecodeRequestParameter("goodsName");
			if (searchContent != null && !"".equals(searchContent)) {
				params.put("goodsCode," + SearchCondition.ANYLIKE, searchContent.trim());
			}
			if (goodsCode != null && !"".equals(goodsCode)) {
				params.put("goodsCode," + SearchCondition.ANYLIKE, goodsCode.trim());
			}
			if (goodsName != null && !"".equals(goodsName)) {
				params.put("goodName," + SearchCondition.ANYLIKE, goodsName.trim());
			}
			//处理搜索条件

			pager = itemDownLoadController.doListGoodsPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 获取树形结构
	 */
	public void findStoreTypeTreeToJson() {
		try {
			loadStoreType(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadStoreType(String nodeId, String nodeTreeType) {
		try {
			List<OrgUnit> orgUnitList = null;
			List<StoreType> storeTypeList = null;
			if (null != nodeId && !"".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					if (nodeTreeType.equals("S")) {
						orgUnitList = itemDownLoadService.findOrgAndUnitTreeList(nodeTreeType, nodeId);
					}
				}
			} else {
				storeTypeList = itemDownLoadService.findAllByEntityClass(StoreType.class);
			}

			if (orgUnitList == null) {
				orgUnitList = new LinkedList<OrgUnit>();
			}

			if (storeTypeList != null) {
				for (StoreType storeType : storeTypeList) {
					OrgUnit ou1 = new OrgUnit(storeType.getId(), "S", storeType.getName());
					if (storeType.getChannelDistributors().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (ChannelDistributor channelDistributor : storeType.getChannelDistributors()) {
							ou2List.add(new OrgUnit(channelDistributor.getId(), "CH", channelDistributor.getName()));
						}
						ou1.setSubOrgUnits(ou2List);
					}
					orgUnitList.add(ou1);
				}
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = orgUnitList.size();
			for (int i = 0; i < count; i++) {
				OrgUnit org = orgUnitList.get(i);
				if (org.getSubOrgUnits() != null && org.getSubOrgUnits().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 商品下载
	 * 
	 * @return
	 */
	public String goDownloadItem() {
		try {
			if (channelId != null) {
				itemDownloadProcessor.process(channelId);
			} else {
				Map<String, Object> params = getParams();
				params.put("type," + SearchCondition.ANYLIKE, "5");
				List<ChannelDistributor> channelDistributorList = itemDownLoadController.doListChannelDistributor(params);
				for (ChannelDistributor channelDistributor : channelDistributorList) {
					if (channelDistributor != null) {
						itemDownloadProcessor.process(channelDistributor.getId());
					}
				}
			}
			setMessage("商品下载成功");
		} catch (Exception e) {
			setMessage("商品下载失败");
		}
		return UPDATE;
	}

	public String goSearch() {
		return "goSearch";
	}

	/**
	 * 获取上一条数据
	 * 
	 * @return
	 */
	public String goBeforeGoods() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				goods = itemDownLoadController.doListBeforeGoodsById(id);
				if (goods == null) {
					goods = itemDownLoadController.doListGoodsById(id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 获取下一条数据
	 * 
	 * @return
	 */
	public String goAfterGoods() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				goods = itemDownLoadController.doListAfterGoodsById(id);
				if (goods == null) {
					goods = itemDownLoadController.doListGoodsById(id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			Goods goods = itemDownLoadController.doListGoodsById(id);
			if (null != goods) {
				itemDownLoadController.doDeleteByEntity(goods);
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
				itemDownLoadController.doDeleteByIds(delIds);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	/**
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * @return the channelId
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * @param channelId
	 *            the channelId to set
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public List<ChannelDistributor> getChannelDistributorList() {
		return channelDistributorList;
	}

	public void setChannelDistributorList(List<ChannelDistributor> channelDistributorList) {
		this.channelDistributorList = channelDistributorList;
	}

}
