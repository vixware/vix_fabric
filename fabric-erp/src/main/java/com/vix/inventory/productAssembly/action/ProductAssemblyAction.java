package com.vix.inventory.productAssembly.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.inventory.productAssembly.controller.ProductAssemblyController;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.mdm.item.entity.Item;

/**
 * 商品组装
 * 
 * @author zhanghaibing
 * 
 * @date 2013-12-11
 */
@Controller
@Scope("prototype")
public class ProductAssemblyAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ProductAssemblyAction.class);
	@Autowired
	private ProductAssemblyController productAssemblyController;
	private String id;
	private String ids;
	private String pageNo;
	/**
	 * 存货档案清单(现存量汇总表)
	 */
	private InventoryCurrentStock inventoryCurrentStock;
	private List<InventoryCurrentStock> inventoryCurrentStockList;
	/**
	 * 计量单位
	 */
	private List<MeasureUnit> measureUnitList;
	/**
	 * 绑定商品的子商品
	 */
	private InventoryCurrentStock subinventoryCurrentStock;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			/* 过滤已绑定商品 */
			params.put("isBinding," + SearchCondition.EQUAL, "1");

			String name = getDecodeRequestParameter("name");
			if (null != name && !"".equals(name)) {
				params.put("itemname," + SearchCondition.ANYLIKE, name.trim());
			}
			String selectCode = getRequestParameter("selectCode");
			if (null != selectCode && !"".equals(selectCode)) {
				params.put("itemcode," + SearchCondition.ANYLIKE, selectCode.trim());
			}
			String selectName = getDecodeRequestParameter("selectName");
			if (null != selectName && !"".equals(selectName)) {
				params.put("itemname," + SearchCondition.ANYLIKE, selectName.trim());
			}
			inventoryCurrentStockList = productAssemblyController.doListInventoryCurrentStockList(params);
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
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			/* 过滤已绑定商品 */
			params.put("isBinding," + SearchCondition.EQUAL, "1");

			String name = getDecodeRequestParameter("name");
			if (null != name && !"".equals(name)) {
				params.put("itemname," + SearchCondition.ANYLIKE, name.trim());
			}
			String selectCode = getRequestParameter("selectCode");
			if (null != selectCode && !"".equals(selectCode)) {
				params.put("itemcode," + SearchCondition.ANYLIKE, selectCode.trim());
			}
			String selectName = getDecodeRequestParameter("selectName");
			if (null != selectName && !"".equals(selectName)) {
				params.put("itemname," + SearchCondition.ANYLIKE, selectName.trim());
			}
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}

			pager = productAssemblyController.doListInventoryCurrentStock(params, pager);
			setPager(pager);
			logger.info("获取服务订单列表数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			measureUnitList = productAssemblyController.doListMeasureUnitList(params);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				inventoryCurrentStock = productAssemblyController.doListInventoryCurrentStockById(id);
			} else {
				inventoryCurrentStock = new InventoryCurrentStock();
				inventoryCurrentStock.setIsTemp("1");
				inventoryCurrentStock.setItemcode(VixUUID.createCode(12));
				inventoryCurrentStock = productAssemblyController.doSaveInventoryCurrentStock(inventoryCurrentStock);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != inventoryCurrentStock.getId()) {
				isSave = false;
			}
			inventoryCurrentStock.setIsTemp("");
			inventoryCurrentStock.setIsBinding("1");
			inventoryCurrentStock.setIsQualfied(1);
			/* 将绑定的商品信息回填到商品表 */
			Item item = new Item();
			item.setCode(inventoryCurrentStock.getItemcode());
			item.setName(inventoryCurrentStock.getItemname());
			item.setPrice(inventoryCurrentStock.getPrice());
			item.setGoodsClass("1");
			//处理修改留痕
			billMarkProcessController.processMark(inventoryCurrentStock, updateField);
			inventoryCurrentStock = productAssemblyController.doSaveInventoryCurrentStock(inventoryCurrentStock);
			/* 将绑定的商品回填到商品表 */
			item = productAssemblyController.doSaveItem(item);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/**
	 * 保存绑定商品之间的关系
	 */
	public String saveOrUpdateItem() {
		boolean isSave = true;
		try {
			if (subinventoryCurrentStock != null) {
				if (null != subinventoryCurrentStock.getId() && !"".equals(subinventoryCurrentStock.getId())) {
					isSave = false;
				}
				if (id != null && !"".equals(id)) {
					inventoryCurrentStock = productAssemblyController.doListInventoryCurrentStockById(id);
					subinventoryCurrentStock.setInventoryCurrentStock(inventoryCurrentStock);
					//绑定商品子项不显示
					subinventoryCurrentStock.setIsTemp("1");
					subinventoryCurrentStock.setIsBinding("1");
					subinventoryCurrentStock = productAssemblyController.doSaveInventoryCurrentStock(subinventoryCurrentStock);
				}
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/**
	 * 获取绑定商品的子列表
	 * 
	 * @return
	 */
	public void getItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				InventoryCurrentStock inventoryCurrentStock = productAssemblyController.doListInventoryCurrentStockById(id);
				if (inventoryCurrentStock != null) {
					json = convertListToJson(new ArrayList<InventoryCurrentStock>(inventoryCurrentStock.getInventoryCurrentStockList()), inventoryCurrentStock.getInventoryCurrentStockList().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goItemList() {
		try {
			if (id != null) {
				subinventoryCurrentStock = productAssemblyController.doListInventoryCurrentStockById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goItemList";
	}

	public String goItemListContent() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = getPager();
			pager.setPageSize(6);
			pager = productAssemblyController.doListInventoryCurrentStock(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goItemListContent";
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			InventoryCurrentStock inventoryCurrentStock = productAssemblyController.doListInventoryCurrentStockById(id);
			if (null != inventoryCurrentStock) {
				productAssemblyController.doDeleteByEntity(inventoryCurrentStock);
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
			if (StringUtils.isNotEmpty(ids) && !"0".equals(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				productAssemblyController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goSearch() {
		return "goSearch";
	}

	/**
	 * @return the measureUnitList
	 */
	public List<MeasureUnit> getMeasureUnitList() {
		return measureUnitList;
	}

	/**
	 * @param measureUnitList
	 *            the measureUnitList to set
	 */
	public void setMeasureUnitList(List<MeasureUnit> measureUnitList) {
		this.measureUnitList = measureUnitList;
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

	public InventoryCurrentStock getInventoryCurrentStock() {
		return inventoryCurrentStock;
	}

	public void setInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) {
		this.inventoryCurrentStock = inventoryCurrentStock;
	}

	public List<InventoryCurrentStock> getInventoryCurrentStockList() {
		return inventoryCurrentStockList;
	}

	public void setInventoryCurrentStockList(List<InventoryCurrentStock> inventoryCurrentStockList) {
		this.inventoryCurrentStockList = inventoryCurrentStockList;
	}

	public InventoryCurrentStock getSubinventoryCurrentStock() {
		return subinventoryCurrentStock;
	}

	public void setSubinventoryCurrentStock(InventoryCurrentStock subinventoryCurrentStock) {
		this.subinventoryCurrentStock = subinventoryCurrentStock;
	}

}
