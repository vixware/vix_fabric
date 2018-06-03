package com.vix.drp.marketInformationManagement.action;

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
import com.vix.drp.competitiveproducts.controller.CompetitiveProductsController;
import com.vix.drp.competitiveproducts.entity.CompetingProducts;
import com.vix.mdm.item.entity.ItemCatalog;

@Controller
@Scope("prototype")
public class MarketInformationManagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(MarketInformationManagementAction.class);
	@Autowired
	private CompetitiveProductsController competitiveProductsController;
	private String id;
	private String ids;
	private String pageNo;
	/**
	 * 竞争产品
	 */
	private CompetingProducts competingProducts;
	private List<CompetingProducts> competingProductsList;

	@Override
	public String goList() {
		try {
			competingProductsList = competitiveProductsController.doListCompetingProductsList(null);
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
			// code 编码
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			Pager pager = competitiveProductsController.doListCompetingProductsPager(params, getPager());
			setPager(pager);
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	// 搜索
	public String goSearchBasicInformationList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = competitiveProductsController.doListCompetingProductsPager(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				competingProducts = competitiveProductsController.doListBasicInformationById(id);
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
			if (null != competingProducts.getId() && !"".equals(competingProducts.getId())) {
				isSave = false;
			}
			competingProducts = competitiveProductsController.doSaveOrUpdateBasicInformation(competingProducts);
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
			CompetingProducts basicInformation = competitiveProductsController.doListBasicInformationById(id);
			if (null != basicInformation) {
				competitiveProductsController.doDeleteByEntity(basicInformation);
				logger.info("");
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<ItemCatalog> listItemCatalog = new ArrayList<ItemCatalog>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listItemCatalog = competitiveProductsController.findAllItemCatalog("parentItemCatalog.id", id, params);
			} else {
				listItemCatalog = competitiveProductsController.findAllItemCatalog("parentItemCatalog.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			strSb = loadAllItemCatalog(strSb, listItemCatalog);
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private StringBuilder loadAllItemCatalog(StringBuilder strSb, List<ItemCatalog> listItemCatalog) throws Exception {
		for (int i = 0; i < listItemCatalog.size(); i++) {
			ItemCatalog ic = listItemCatalog.get(i);
			if (ic.getSubItemCatalogs().size() > 0) {
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getName());
				strSb.append("\",open:true,isParent:true,children:[");
				loadAllItemCatalog(strSb, new ArrayList<ItemCatalog>(ic.getSubItemCatalogs()));
				strSb.append("]}");
			} else {
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getName());
				strSb.append("\",open:false,isParent:false}");
			}
			if (i < listItemCatalog.size() - 1) {
				strSb.append(",");
			}
		}
		return strSb;
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
				competitiveProductsController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
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

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public CompetingProducts getCompetingProducts() {
		return competingProducts;
	}

	public void setCompetingProducts(CompetingProducts competingProducts) {
		this.competingProducts = competingProducts;
	}

	public List<CompetingProducts> getCompetingProductsList() {
		return competingProductsList;
	}

	public void setCompetingProductsList(List<CompetingProducts> competingProductsList) {
		this.competingProductsList = competingProductsList;
	}

}
