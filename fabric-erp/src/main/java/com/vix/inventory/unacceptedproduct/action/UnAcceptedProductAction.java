package com.vix.inventory.unacceptedproduct.action;

import java.util.ArrayList;
import java.util.Date;
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
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.inventory.unacceptedproduct.controller.NonconformingProductController;
import com.vix.inventory.unacceptedproduct.entity.NonconformingProduct;
import com.vix.inventory.unacceptedproduct.entity.NonconformingProductDetails;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.service.IItemService;

@Controller
@Scope("prototype")
public class UnAcceptedProductAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(UnAcceptedProductAction.class);
	@Autowired
	private NonconformingProductController nonconformingProductController;
	@Autowired
	private IItemService itemService;
	@Autowired
	private OrderProcessController orderProcessController;
	private String id;
	private String categoryId;
	private String ids;
	private String pageNo;
	/**
	 * 不合格品记录单
	 */
	private NonconformingProduct nonconformingProduct;
	private List<NonconformingProduct> nonconformingProductList;

	private NonconformingProductDetails nonconformingProductDetails;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			nonconformingProductList = nonconformingProductController.doListNonconformingProductList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 根据条件查询入库单信息
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			//处理搜索条件
			String selectCode = getRequestParameter("selectCode");
			String code = getRequestParameter("code");
			String name = getDecodeRequestParameter("name");
			if (selectCode != null && !"".equals(selectCode)) {
				params.put("code," + SearchCondition.EQUAL, selectCode.trim());
			}
			if (code != null && !"".equals(code)) {
				params.put("code," + SearchCondition.EQUAL, code.trim());
			}
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name.trim());
			}
			//处理搜索条件

			//搜索加强
			Map<String, Object> stockRecordLinesparams = new HashMap<String, Object>();
			//根据商品信息获取订单ID
			String itemname = getDecodeRequestParameter("itemname");
			String itemcode = getDecodeRequestParameter("itemcode");
			String stockRecordsIds = "";
			if (StringUtils.isNotEmpty(itemname)) {
				stockRecordLinesparams.put("itemname," + SearchCondition.ANYLIKE, itemname);
			}
			if (StringUtils.isNotEmpty(itemcode)) {
				stockRecordLinesparams.put("itemcode," + SearchCondition.ANYLIKE, itemcode);
			}
			List<NonconformingProductDetails> nonconformingProductDetailsList = nonconformingProductController.doListNonconformingProductDetailsList(stockRecordLinesparams);
			if (nonconformingProductDetailsList != null && nonconformingProductDetailsList.size() > 0) {
				for (NonconformingProductDetails nonconformingProductDetails : nonconformingProductDetailsList) {
					if (nonconformingProductDetails != null && nonconformingProductDetails.getNonconformingProduct() != null) {
						stockRecordsIds += nonconformingProductDetails.getNonconformingProduct().getId() + ",";
					}
				}
			}
			if (StringUtils.isNotEmpty(stockRecordsIds)) {
				params.put("id," + SearchCondition.IN, stockRecordsIds);
			}
			//搜索加强

			pager = nonconformingProductController.doListNonconformingProduct(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至新增修改不合格品 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				nonconformingProduct = nonconformingProductController.doListNonconformingProductById(id);
				logger.info("");
			} else {
				nonconformingProduct = new NonconformingProduct();
				nonconformingProduct.setCode(VixUUID.createCode(10));
				nonconformingProduct.setIsTemp("1");
				nonconformingProduct.setCreator(SecurityUtil.getCurrentUserName());
				nonconformingProduct.setCreateTime(new Date());
				nonconformingProduct = nonconformingProductController.doSaveNonconformingProduct(nonconformingProduct);
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
			if (StringUtils.isNotEmpty(nonconformingProduct.getId())) {
				isSave = false;
			}
			nonconformingProduct.setIsTemp("");
			nonconformingProduct.setStatus("0");
			//处理中文拼音化
			if (nonconformingProduct.getName() != null) {
				String name = ChnToPinYin.getPYString(nonconformingProduct.getName());
				nonconformingProduct.setChineseCharacter(name.toUpperCase());
			}
			initEntityBaseController.initEntityBaseAttribute(nonconformingProduct);

			billMarkProcessController.processMark(nonconformingProduct, updateField);
			nonconformingProduct = nonconformingProductController.doSaveNonconformingProduct(nonconformingProduct);
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			NonconformingProduct nonconformingProduct = nonconformingProductController.doListNonconformingProductById(id);
			if (null != nonconformingProduct) {
				nonconformingProductController.doDeleteByEntity(nonconformingProduct);
				logger.info("");
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取明细的json数据 */

	public void getNonconformingProductDetailsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				NonconformingProduct nonconformingProduct = nonconformingProductController.doListNonconformingProductById(id);
				if (nonconformingProduct != null) {
					json = convertListToJson(new ArrayList<NonconformingProductDetails>(nonconformingProduct.getNonconformingProductDetailss()), nonconformingProduct.getNonconformingProductDetailss().size());
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
				nonconformingProductController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 
	 * @return
	 */
	public String goListItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				nonconformingProductDetails = nonconformingProductController.doListNonconformingProductDetailsById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListItem";
	}

	public String goSingleListItem() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("itemClass," + SearchCondition.IN, "goods,finishedgoods");
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("id");
				getPager().setOrderBy("desc");
			}
			getPager().setPageSize(6);
			if (null != categoryId && !"".equals(categoryId)) {
				Pager pager = itemService.findPagerByItemCatalogId(getPager(), categoryId, params);
				setPager(pager);
			} else {
				itemService.findPagerByHqlConditions(getPager(), Item.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSingleListItem";
	}

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdateNonconformingProductDetails() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(nonconformingProductDetails.getId()) && !"0".equals(nonconformingProductDetails.getId())) {
				isSave = false;
			}
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				nonconformingProduct = nonconformingProductController.doListNonconformingProductById(id);
				if (nonconformingProduct != null) {
					nonconformingProductDetails.setNonconformingProduct(nonconformingProduct);
				}
				nonconformingProductController.doSaveNonconformingProductDetails(nonconformingProductDetails);
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
	 * 处理删除操作
	 * 
	 * @return
	 */

	public String deleteNonconformingProductDetailsById() {
		try {
			NonconformingProductDetails nonconformingProductDetails = nonconformingProductController.doListNonconformingProductDetailsById(id);
			if (null != nonconformingProductDetails) {
				nonconformingProductController.deleteNonconformingProductDetails(nonconformingProductDetails);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goShowUnAcceptedProduct() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				nonconformingProduct = nonconformingProductController.doListNonconformingProductById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowUnAcceptedProduct";
	}

	public String goSearch() {
		return "goSearch";
	}

	/**
	 * 获取上一条数据
	 * 
	 * @return
	 */
	public String goBeforeStockTaking() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				nonconformingProduct = nonconformingProductController.doListNonconformingProductById(id);
				if (nonconformingProduct != null && nonconformingProduct.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(nonconformingProduct.getCreateTime()));
					// 过滤临时数据
					params.put("isTemp", "");
					params.put("tenantId", nonconformingProduct.getTenantId());
					params.put("companyInnerCode", nonconformingProduct.getCompanyInnerCode());
					params.put("creator", nonconformingProduct.getCreator());
					nonconformingProduct = (NonconformingProduct) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(nonconformingProduct.getCreateTime()), params, nonconformingProduct, "before");
					if (nonconformingProduct == null || StringUtils.isEmpty(nonconformingProduct.getId())) {
						nonconformingProduct = nonconformingProductController.doListNonconformingProductById(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowTakeStock";
	}

	/**
	 * 
	 * @return
	 */
	public String goAfterStockTaking() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				nonconformingProduct = nonconformingProductController.doListNonconformingProductById(id);
				if (nonconformingProduct != null && nonconformingProduct.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(nonconformingProduct.getCreateTime()));
					params.put("isTemp", "");
					params.put("tenantId", nonconformingProduct.getTenantId());
					params.put("companyInnerCode", nonconformingProduct.getCompanyInnerCode());
					params.put("creator", nonconformingProduct.getCreator());
					nonconformingProduct = (NonconformingProduct) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(nonconformingProduct.getCreateTime()), params, nonconformingProduct, "after");
					if (nonconformingProduct == null || StringUtils.isEmpty(nonconformingProduct.getId())) {
						nonconformingProduct = nonconformingProductController.doListNonconformingProductById(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowTakeStock";
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public NonconformingProduct getNonconformingProduct() {
		return nonconformingProduct;
	}

	public void setNonconformingProduct(NonconformingProduct nonconformingProduct) {
		this.nonconformingProduct = nonconformingProduct;
	}

	public List<NonconformingProduct> getNonconformingProductList() {
		return nonconformingProductList;
	}

	public void setNonconformingProductList(List<NonconformingProduct> nonconformingProductList) {
		this.nonconformingProductList = nonconformingProductList;
	}

	public NonconformingProductDetails getNonconformingProductDetails() {
		return nonconformingProductDetails;
	}

	public void setNonconformingProductDetails(NonconformingProductDetails nonconformingProductDetails) {
		this.nonconformingProductDetails = nonconformingProductDetails;
	}

}
