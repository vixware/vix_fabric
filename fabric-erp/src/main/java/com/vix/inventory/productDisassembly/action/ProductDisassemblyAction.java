package com.vix.inventory.productDisassembly.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.inventory.productDisassembly.controller.ProductDisassemblyController;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;

import flexjson.JSONSerializer;

/**
 * 商品拆装
 * 
 * @author zhanghaibing
 * 
 * @date 2013-12-11
 */
@Controller
@Scope("prototype")
public class ProductDisassemblyAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ProductDisassemblyAction.class);
	@Autowired
	private ProductDisassemblyController productDisassemblyController;
	private String id;
	private String ids;
	private String pageNo;
	/**
	 * 存货档案清单(现存量汇总表)
	 */
	private InventoryCurrentStock inventoryCurrentStock;
	private List<InventoryCurrentStock> inventoryCurrentStockList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
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
			inventoryCurrentStockList = productDisassemblyController.doListInventoryCurrentStockList(params);
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
			/*params.put("isBinding," + SearchCondition.NOEQUAL, "1");*/
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
			pager = productDisassemblyController.doListInventoryCurrentStockPager(params, pager);
			setPager(pager);
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
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				inventoryCurrentStock = productDisassemblyController.doListInventoryCurrentStockById(id);
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
			inventoryCurrentStock = productDisassemblyController.doSaveInventoryCurrentStock(inventoryCurrentStock);
			if (isSave) {
				setMessage("拆装成功");
			} else {
				setMessage("拆装成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				this.setMessage("拆装失败");
			} else {
				this.setMessage("拆装失败");
			}
		}
		return UPDATE;
	}

	/**
	 * 弹出显示库存信息列表
	 * 
	 * @return
	 */
	public String goInventoryCurrentStock() {
		return "goInventoryCurrentStock";
	}

	/**
	 * 加载显示库存信息列表数据内容
	 * 
	 * @return
	 */
	public String goInventoryCurrentStockList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			/* 过滤已绑定商品 */
			params.put("isBinding," + SearchCondition.EQUAL, "1");
			Pager pager = productDisassemblyController.doListInventoryCurrentStockPager(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goInventoryCurrentStockList";
	}

	/**
	 * 获取选择后的库存信息
	 */
	public void getItemListJson() {
		try {
			String inventoryCurrentStockId = getRequestParameter("inventoryCurrentStockId");
			if (null != inventoryCurrentStockId && !"".equals(inventoryCurrentStockId)) {
				InventoryCurrentStock inventoryCurrentStock = productDisassemblyController.doListInventoryCurrentStockById(inventoryCurrentStockId);
				if (inventoryCurrentStock != null) {
					String json = new JSONSerializer().exclude("class").serialize(inventoryCurrentStock);
					renderJson(json);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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
				InventoryCurrentStock inventoryCurrentStock = productDisassemblyController.doListInventoryCurrentStockById(id);
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			InventoryCurrentStock inventoryCurrentStock = productDisassemblyController.doListInventoryCurrentStockById(id);
			if (null != inventoryCurrentStock) {
				productDisassemblyController.doDeleteByEntity(inventoryCurrentStock);
				logger.info("");
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
				productDisassemblyController.doDeleteByIds(delIds);
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

}
